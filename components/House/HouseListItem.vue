<template>
  <div v-if="house.recentPrice != null">
    <b-card no-body class="overflow-hidden" style="max-width: 440px">
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
              {{ house.sidoName | sido }} {{ house.gugunName }}
              {{ house.dongName }} <br />
              {{ house.aptName }}<br />
              실거래가 : {{ house.recentPrice }} 만원 <br />
              <button @click="setState">
                {{ favoriteState ? "★" : "☆" }}
              </button>
            </b-card-text>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import Constant from "@/common/Constant";
import { mapGetters } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
      activeTag: "All",
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
    setState(envent) {
      this.favoriteState = !this.favoriteState;
      console.log(envent);
      if (this.favoriteState) {
        this.getFavorites();
      } else {
        this.deleteFavorites(envent);
      }
    },
    addFavorite() {
      const ads =
        this.house.sidoName.toString().substr(0, 2) +
        " " +
        this.house.gugunName +
        " " +
        this.house.dongName;
      console.log(ads + " " + this.house.recentPrice);
      this.$store
        .dispatch(Constant.REGIST_FAVORITE, {
          favorite: {
            name: this.house.aptName,
            address: ads,
            price: this.house.recentPrice,
          },
        })
        .then(() => {
          alert("favorite 등록에 성공하였습니다.");
        });
    },
    deleteFavorites(envent) {
      this.$store.dispatch(Constant.DELETE_FAVORITE, envent).then(() => {
        alert("삭제에 성공하였습니다.");
        this.getFavorites();
      });
    },
  },

  filters: {
    sido: function (value) {
      return value.toString().substr(0, 2);
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
