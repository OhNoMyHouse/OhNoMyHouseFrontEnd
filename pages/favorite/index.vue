<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  // data: () => ({ videos }),
});
</script>

<template>
  <aside class="content" id="library">
    <section class="history container">
      <NuxtLink to="/library/history"
        ><Icon name="history" />
        <h1>History</h1></NuxtLink
      >
    </section>

    <section class="container">
      <NuxtLink to="/library/playlist"
        ><Icon name="playlist" />
        <h1>Playlists</h1></NuxtLink
      >

      <p>Playlists you create or save will show up here.</p>
    </section>

    <section class="container">
      <NuxtLink to="/library/playlist?list=WL"
        ><Icon name="watch-later" />
        <h1>Watch later</h1></NuxtLink
      >
    </section>

    <section class="container">
      <NuxtLink to="/library/playlist?list=LV"
        ><Icon name="like" />
        <h1>Liked videos</h1></NuxtLink
      >
    </section>
  </aside>
</template>

<style lang="scss">
@import "@/assets/css/mixins.scss";

aside.content#library {
  @include flex($dir: column);

  section.container {
    @include flex($dir: column, $gap: 16px);

    > a {
      @include flex($gap: 8px);
      svg path {
        fill: var(--fg);
      }
      h1 {
        font-size: 20px;
        font-weight: 500;
      }
    }

    &:not(.history) :is(section.videos, p) {
      @include breakpoint {
        display: none;
      }
    }
  }
}
</style>
