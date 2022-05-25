<script>
import Vue from "vue";
import Constant from "@/common/Constant.js";
import HouseList from "@/components/house/HouseList.vue";
import FilterList from "@/components/filter/FilterList.vue";
import { mapGetters } from "vuex";

export default Vue.extend({
  components: {
    HouseList,
    FilterList,
  },
  data: () => ({
    map: null,
    markers: [],
    clusterer: null,
    ps: null,
    bounds: null,
    detailState: false,
    houseinfo: {},
    contentNode: null,
    placeOverlay: null,
    currCategory: null,
    // videos,
    activeTag: "All",
    tags: [
      // { key: "CS2", label: "편의점" },
      "All",
      "Trending",
      "Music",
      "Gaming",
      "Sports",
      "Live",
      "News",
      "Piano",
    ],
  }),
  mounted() {
    kakao.maps.load(this.initMap);
  },
  computed: {
    ...mapGetters(["houses", "house"]),
  },
  watch: {
    houses(value) {
      this.updateMap(value);
    },
  },
  methods: {
    // setHouse() {
    //   this.$store.commit(Constant.SET_HOUSE, { house: this.house });
    // },
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
        level: 5, // 지도의 확대 레벨
      };

      // 지도를 생성합니다
      this.map = new kakao.maps.Map(mapContainer, mapOption);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPLEFT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.LEFT);

      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      this.currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places();

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      this.addCategoryClickEvent();

      // // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // // 커스텀 오버레이를 생성합니다
      // var customOverlay;
      // // 주소-좌표 변환 객체를 생성합니다
      // var geocoder = new kakao.maps.services.Geocoder();

      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평
        minLevel: 5, // 클러스터 할 최소 지도 레벨
        // minClusterSize: 1, // 1개 마커 클러스터링
      });
    },
    // -----------------------------------category search-----------------------------------
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      console.log("searching... curuCategory:" + this.currCategory);
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addCategoryMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            this.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addCategoryMarker(position, order) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      var category = document.getElementById("category");
      var children = category.children;

      // for (var i = 0; i < children.length; i++) {
      //   children[i].onclick = this.onClickCategory;
      // }
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(id, className, event) {
      var id = id;
      console.log(id);
      var className = className;
      console.log(className);

      this.placeOverlay.setMap(null);

      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(event);
        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category");
      var children = category.children;
      var i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    },
    // -----------------------------------category search-----------------------------------
    updateMap(houses) {
      this.clearMarkers(null);
      this.clusterer.clear();
      this.bounds = new kakao.maps.LatLngBounds();
      var level = this.map.getLevel();
      console.log("현재 지도의 레벨 : " + this.map.getLevel());

      for (let i = 0; i < houses.length; i++) {
        const position = new kakao.maps.LatLng(houses[i].lat, houses[i].lng);
        this.addMarker(position, houses[i]);
      }

      this.clusterer.addMarkers(this.markers);
      // 지도 중심 이동
      // if (houses.length > 1) this.setCenter(houses[0].lat, houses[0].lng);
      this.setBounds();
    },
    //----------------------------

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, data) {
      // var imageSrc =
      //   "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png"; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      // var imageSize = new kakao.maps.Size(36, 37); // 마커 이미지의 크기
      // var imgOptions = {
      //   spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      //   offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      // };
      // var markerImage = new kakao.maps.MarkerImage(
      //   imageSrc,
      //   imageSize,
      //   imgOptions
      // );
      var marker = new kakao.maps.Marker({
        position: position,
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      // LatLngBounds 객체에 좌표를 추가합니다
      this.bounds.extend(position);

      var sethouse = this.setHouse;

      kakao.maps.event.addListener(marker, "click", () => {
        this.detailStateOn();
        this.houseinfo = data;
        console.log("마커클릭");
        // console.log(this.houseinfo);
        // setHouse();
        this.$store.state.house = data;
        console.log(this.$store.state.house);
      });
    },

    detailStateOn() {
      this.detailState = true;
    },

    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
    },

    clearMarkers() {
      this.setMarkers(null);
      this.markers = [];
    },

    setCenter(lat, lng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);
    },
    setBounds() {
      // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
      // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
      this.map.setBounds(this.bounds);
    },
  },
});
</script>

<template>
  <aside class="content" id="home">
    <section class="tags">
      <div class="overlay-start" />
      <filter-list />
      <aside class="track" ref="tags">
        <button
          @click="activeTag = tag"
          :class="{ active: activeTag === tag }"
          v-for="tag in tags"
          :key="tag.label"
        >
          {{ tag }}
        </button>
      </aside>
      <aside class="controls">
        <button @click="$refs.tags.scrollLeft -= 120">
          <Icon name="chevron-left" />
        </button>
        <button @click="$refs.tags.scrollLeft += 120">
          <Icon name="chevron-right" />
        </button>
        <b-button
          v-b-toggle.sidebar-right
          variant="black"
          style="padding: 5px; display: none"
          >거래 정보</b-button
        >
      </aside>
      <div class="overlay-end" />
    </section>
    <template>
      <div>
        <b-sidebar
          id="sidebar-right"
          no-close-on-esc
          no-header
          right
          shadow
          visible
          aria-expanded="true"
          bg-variant="white"
          class="sidebar"
        >
          <house-list />
        </b-sidebar>
      </div>
    </template>
    <!-- height: calc(100vh - var(--nav-height)) -->
    <div class="map_wrap">
      <div
        id="map"
        style="width: calc(100vw - 452px); height: calc(100vh - 125px)"
      ></div>
      <ul id="category">
        <li
          id="BK9"
          data-order="0"
          @click="onClickCategory('BK9', 'category_bg bank', $event)"
        >
          <span class="category_bg bank"></span>
          은행
        </li>
        <li
          id="MT1"
          data-order="1"
          @click="onClickCategory('MT1', 'category_bg mart', $event)"
        >
          <span class="category_bg mart"></span>
          마트
        </li>
        <li
          id="PM9"
          data-order="2"
          @click="onClickCategory('PM9', 'category_bg pharmacy', $event)"
        >
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li
          id="OL7"
          data-order="3"
          @click="onClickCategory('OL7', 'category_bg oil', $event)"
        >
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li
          id="CE7"
          data-order="4"
          @click="onClickCategory('CE7', 'category_bg cafe', $event)"
        >
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li
          id="CS2"
          data-order="5"
          @click="onClickCategory('CS2', 'category_bg store', $event)"
        >
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
    <b-sidebar
      id="sidebar-right-detail"
      v-model="detailState"
      title=""
      right
      shadow
      bg-variant="white"
    >
      <div v-show="detailState">{{ houseinfo }}</div>
    </b-sidebar>
  </aside>
</template>

<style lang="scss">
@import "@/assets/css/mixins.scss";

aside.content#home {
  section {
    &.tags {
      position: relative;
      border-bottom: 1px solid var(--gray);

      button {
        flex: 0 0 auto;
        cursor: pointer;
        padding: 6px 16px;
        border-radius: 9999px;
        background: var(--gray);
        border: 1px solid var(--hover);
        &:hover {
          background: var(--active);
        }
        &.active {
          color: var(--red);
          background: var(--bg-red);
          border-color: var(--bg-red);
        }
      }

      aside {
        &.track {
          overflow-x: auto;
          padding: 12px 24px;
          padding-right: 124px;
          @include flex(center, $gap: 8px);
          @include breakpoint {
            padding: 12px;
          }

          &::-webkit-scrollbar {
            display: none;
          }
        }

        &.controls {
          z-index: 1;
          height: 100%;
          position: absolute;
          top: 0;
          right: 24px;
          @include flex(center, $gap: 4px);
          @include breakpoint {
            display: none;
          }

          button {
            padding: 6px;
          }
        }
      }

      div.overlay-start {
        width: 24px;
        height: 100%;
        position: absolute;
        left: 0;
        background: linear-gradient(to right, var(--bg), transparent);
        @include breakpoint {
          width: 12px;
        }
      }

      div.overlay-end {
        width: 124px;
        height: 100%;
        position: absolute;
        right: 0;
        top: 0;
        background: linear-gradient(to right, transparent, var(--bg) 16% 100%);
        @include breakpoint {
          width: 12px;
          background: linear-gradient(to right, transparent, var(--bg));
        }
      }
    }
  }
}

// 사이드바 위치
#sidebar-right,
#sidebar-right-detail {
  width: 380px;
  top: 123px;
  height: calc(100vh - 125px);
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
}
#category {
  position: absolute;
  top: 10px;
  left: 130px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
