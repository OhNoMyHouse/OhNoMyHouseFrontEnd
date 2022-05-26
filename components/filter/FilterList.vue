<template>
  <aside class="filter">
    <select class="form-control" id="sido" @change="gugunList">
      <option value="*">시/도</option>
      <option v-for="(ss, index) in sido" :value="ss.sidoCode" :key="index">
        {{ ss.sidoName }}
      </option>
    </select>
    <select class="form-control" id="gugun" @change="dongList">
      <option value="*">구/군</option>
      <option v-for="(gg, index) in gugun" :value="gg.gugunCode" :key="index">
        {{ gg.gugunName }}
      </option>
    </select>
    <select class="form-control" id="dong" @change="aptList">
      <option value="*">동</option>
      <option v-for="(dd, index) in dong" :value="dd.dongCode" :key="index">
        {{ dd.dongName }}
      </option>
    </select>
  </aside>
</template>

<script>
import Constant from "@/common/Constant.js";
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapGetters(["sido", "gugun", "dong", "apt"]),
    sido() {
      return this.$store.state.sido;
    },
    gugun() {
      return this.$store.state.gugun;
    },
    dong() {
      return this.$store.state.dong;
    },
    apt() {
      console.log("aptlist" + this.$store.state.apt);
      return this.$store.state.apt;
    },
  },
  created() {
    this.getSido();
  },
  methods: {
    ...mapActions([
      Constant.GET_SIDO,
      Constant.GET_GUGUN,
      Constant.GET_DONG,
      Constant.GET_APT,
    ]),
    gugunList(event) {
      this.getGugun({ sido: event.target.value });
    },
    dongList(event) {
      this.getDong({ gugun: event.target.value });
    },
    aptList(event) {
      this.getApt({ dong: event.target.value });
      console.log("getAptList" + this.$store.state.apt);
    },
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
