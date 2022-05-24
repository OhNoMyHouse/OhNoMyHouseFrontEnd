<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-card
      img-src="@/assets/img/house.png"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem"
      class="mb-2"
    >
      <b-card-text>
        <strong>{{ house.aptName }}</strong>
      </b-card-text>
      <b-card-text>
        <b-list-group flush>
          <b-list-group-item
            >{{ house.sidoName }} {{ house.gugunName }}
            {{ house.dongName }}</b-list-group-item
          >
          <b-list-group-item
            >실거래가 : {{ house.recentPrice }} 만원</b-list-group-item
          >
        </b-list-group>
      </b-card-text>
    </b-card>
    <div></div>
  </b-row>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapGetters(["houses"]),
    selectHouse() {},
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgba(0, 0, 0, 0.05882);
}
</style>
