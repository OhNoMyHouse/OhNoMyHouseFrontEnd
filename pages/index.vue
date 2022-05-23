<script>
import Vue from "vue";
import HouseList from "@/components/house/HouseList.vue";
import { mapGetters } from "vuex";

export default Vue.extend({
  components: { HouseList },
  data: () => ({
    map: null,
    markers: [],
    clusterer: null,
    ps: null,
    bounds: null,
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
    ...mapGetters(["houses"]),
  },
  watch: {
    houses(value) {
      this.updateMap(value);
    },
  },
  methods: {
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

      // 지도 이동 감지
      // kakao.maps.event.addListener(this.map, "center_changed", this.moveMap);

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places();

      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // 커스텀 오버레이를 생성합니다
      var customOverlay;
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평
        // minClusterSize: 1, // 1개 마커 클러스터링
      });
    },

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

      kakao.maps.event.addListener(marker, "click", function () {
        // select(data);
        // open();
        // console.log(marker);
      });
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

    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          displayMarker(data[i]);
        }
      }
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
        <b-button v-b-toggle.sidebar-right variant="black" style="padding: 5px"
          >거래 정보</b-button
        >
      </aside>
      <div class="overlay-end" />
    </section>
    <template>
      <div>
        <b-sidebar
          id="sidebar-right"
          no-header
          right
          shadow
          visible
          aria-expanded="true"
        >
          <house-list />
        </b-sidebar>
      </div>
    </template>
    <!-- height: calc(100vh - var(--nav-height)) -->
    <div id="map" style="width: 100%; height: calc(100vh - 125px)"></div>
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

    &.videos {
      padding: 24px;
      @include grid(4, $mb: 1);
      @include breakpoint {
        padding: 0;
        gap: 0;
      }
      @include breakpoint(min, 1920px) {
        @include grid(5);
      }
      @include breakpoint(min, 2250px) {
        @include grid(6);
      }
    }
  }
}
// 사이드바 위치
#sidebar-right {
  width: 520px;
  top: 123px;
  height: calc(100vh - 125px);
}
</style>
