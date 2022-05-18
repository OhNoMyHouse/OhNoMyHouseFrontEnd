<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNav from "./components/HeaderNav.vue";
// import MainContentView from "./views/MainContentView.vue";

export default {
  components: {
    HeaderNav,
    // MainContentView,
  },
};
</script>
