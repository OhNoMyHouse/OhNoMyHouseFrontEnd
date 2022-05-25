<template>
  <aside class="filter">
    <select class="form-control" id="sido" @change="gugunList">
      <option value="*">시/도</option>
      <option v-for="(ss, index) in sido" :value="ss.sidoCode" :key="index">
        {{ ss.sidoName }}
      </option>
    </select>
    <!-- <b-form-select
      v-model="sidoCode"
      :options="sido"
      @change="gugunList"
    ></b-form-select> -->
    <select class="form-control" id="gugun">
      <option value="*">구/군</option>
      <option v-for="(gg, index) in gugun" :value="gg.gugunCode" :key="index">
        {{ gg.gugunName }}
      </option>
    </select>
    <select class="form-control" id="dong" @click="getDong()">
      <option value="*">동</option>
      <option v-for="(dd, index) in dong" :value="sid.dongCode" :key="index">
        {{ dd.sidoName }}
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
      console.log(this.$store.state.sido);
      return this.$store.state.sido;
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
      console.log(event.target.value);
      console.log(this);
      this.getGugun({ sido: event.target.value });
      // this.$store.dispatch(Constant.GET_GUGUN, { sido: event.target.value });
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
