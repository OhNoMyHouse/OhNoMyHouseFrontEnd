<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    {{ houses.length }} 개의 검색 목록입니다.
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
      :favorites="favorites"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import Constant from "@/common/Constant";
import { mapGetters } from "vuex";

export default {
  name: "HouseList",
  components: { HouseListItem },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["houses"]),
    favorites() {
      return this.$store.state.favorites;
    },
  },
  created() {
    console.log("FavoriteCreated Comp.");
    this.getFavorites();
  },
  methods: {
    getFavorites() {
      this.$store.dispatch(Constant.GET_FAVORITES);
    },
  },
};
</script>

<style></style>
