<script lang="ts">
import Vue from "vue";
import Constant from "@/common/Constant";

export default Vue.extend({
  data: () => ({
    favoriteState: true,
    key: null,
    value: null,
  }),

  methods: {
    setState(event: any) {
      this.deleteFavorites(event);
    },
    getFavorites() {
      this.$store.dispatch(Constant.GET_FAVORITES);
    },
    deleteFavorites(event: any) {
      this.$store.dispatch(Constant.DELETE_FAVORITE, event).then(() => {
        alert("삭제에 성공하였습니다.");
        this.getFavorites();
      });
    },
  },
  computed: {
    favorites() {
      return this.$store.state.favorites;
    },
  },
  created() {
    console.log("FavoriteList Comp.");
    this.getFavorites();
  },
});
</script>

<template>
  <aside class="content" id="library">
    <section class="container">
      <h1>Favorite List</h1>
      <div v-if="favorites && favorites.length != 0">
        <b-card
          v-for="favorite in favorites"
          :key="favorite.idx"
          :house="favorite"
          no-body
          class="overflow-hidden"
          style="max-width: 800px"
          :value="favorite.idx"
        >
          <b-row no-gutters>
            <b-col md="4">
              <b-card-img
                src="@/assets/img/house.png"
                style="width: 120px; height: 100px"
                alt="Image"
                class="rounded-0"
              ></b-card-img>
            </b-col>
            <b-col md="8">
              <b-card-body>
                <b-card-text style="font-size: small">
                  {{ favorite.name }} <br />
                  {{ favorite.address }}<br />
                  실거래가 : {{ favorite.price }} 만원 <br />
                  <button @click="setState(favorite.idx)">★</button>
                </b-card-text>
              </b-card-body>
            </b-col>
          </b-row>
        </b-card>
      </div>
      <div v-else>
        <b-alert show>주택 목록이 없습니다.</b-alert>
      </div>
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
  }
}
</style>
