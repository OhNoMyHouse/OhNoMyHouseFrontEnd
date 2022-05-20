<script>
import Vue from "vue";
// import { videos } from "@/assets/utils";

export default Vue.extend({
  data: () => ({
    map: null,
    // videos,
    activeTag: "All",
    tags: [
      "All",
      "Trending",
      "Music",
      "Gaming",
      "Sports",
      "Live",
      "News",
      "Piano",
      "Web development",
      "JavaScript",
      "Airplane",
      "Computer",
      "Animation",
      "Recently uploaded",
      "New to you",
      "Watched",
    ],
  }),
  mounted() {
    kakao.maps.load(this.initMap);
  },
  methods: {
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.5012743, 127.039585), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };
      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      this.map = map;
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
          :key="tag"
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
      </aside>
      <div class="overlay-end" />
    </section>
    <div
      id="map"
      style="width: 100%; height: calc(100vh - var(--nav-height))"
    ></div>
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
</style>
