<template>
  <b-container v-if="this.$store.state.house" class="bv-example-row mt-3">
    <b-card :img-src="require(`assets/img/${num}.jpg`)" img-alt="Image" img-top style="max-width: 20rem" class="mb-2">
      <b-card-text>
        <strong>{{ this.$store.state.house.aptName }}</strong>
      </b-card-text>
      <b-list-group flush>
        <b-list-group-item
          >{{ this.$store.state.house.sidoName }}
          {{ this.$store.state.house.gugunName }}
          {{ this.$store.state.house.dongName }}</b-list-group-item
        >
        <b-list-group-item>건축 연도 : {{ this.$store.state.house.buildYear }} 년</b-list-group-item>
        <b-list-group-item>실거래가 : {{ this.$store.state.house.recentPrice }} 만원</b-list-group-item>
      </b-list-group>
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: "HouseDetail",
  data() {
    return {
      num: Math.ceil(Math.random() * 1000),
    };
  },
};
</script>

<style></style>
