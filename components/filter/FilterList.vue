<template>
  <aside class="filter">
    <select class="form-control" id="sido" @click="getSido()">
      <option value="*">시/도</option>
      <option v-for="(ss, index) in sido" value="ss.sidoCode" :key="index">
        {{ ss.sidoName }}
      </option>
    </select>
    <select class="form-control" id="gugun" @click="getGugun()">
      <option value="*">구/군</option>
      <option v-for="(gg, index) in gugun" value="gg.gugunCode" :key="index">
        {{ gg.gugunName }}
      </option>
    </select>
    <select class="form-control" id="dong" @click="getDong()">
      <option value="*">동</option>
      <option v-for="(dd, index) in dong" value="sid.dongCode" :key="index">
        {{ dd.sidoName }}
      </option>
    </select>
  </aside>
</template>

<script>
import Constant from "@/common/Constant.js";
import { mapGetters, mapActions } from "vuex";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["sido", "gugun", "dong", "apt"]),
  },
  methods: {
    ...mapActions(
      [Constant.GET_SIDO],
      [Constant.GET_GUGUN],
      [Constant.GET_DONG],
      [Constant.GET_APT]
    ),
  },
};
</script>

<style lang="scss">
@import "@/assets/css/mixins.scss";

aside {
  &.filter {
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
}
</style>
