<template>
  <div v-if="house.recentPrice != null">
    <b-card no-body class="overflow-hidden" style="max-width: 440px">
      <b-row no-gutters>
        <b-col md="4">
          <b-card-img :src="require(`assets/img/${num}.jpg`)" style="width: 120px; height: 100px" alt="Image" class="rounded-0"> </b-card-img>
        </b-col>
        <b-col md="8">
          <b-card-body>
            <b-card-text style="font-size: small">
              <!-- <div v-for="(favorite, index) in favorites" :key="index">
                <div v-if="(favoriteState = favorite.name == house.aptName ? true : false)">
                  {{ house.aptName }}<br />
                  실거래가 : {{ house.recentPrice }} 만원 <br />
              {{ house.sidoName | sido }} {{ house.gugunName }}
              {{ house.dongName }} <br />
              {{ house.aptName }}<br />
              실거래가 : {{ house.recentPrice }} 만원 <br />
              <div v-for="(favorite, index) in favorites" :key="index">
                <div
                  v-if="
                    (favoriteState =
                      favorite.name == house.aptName ? true : false)
                  "
                >
                  <button @click="setState(house.aptName)">
                    {{ favoriteState ? "★" : "☆" }}
                  </button>
                </div>
                <div v-if="favoriteState == false">
                  <button @click="setState(house.aptName)">
                    {{ favoriteState ? "★" : "☆" }}
                  </button>
                </div>
              </div> -->
              {{ house.sidoName | sido }} {{ house.gugunName }} {{ house.dongName }} <br />
              {{ house.aptName }}<br />
              실거래가 : {{ house.recentPrice }} 만원 <br />
              <button @click="setState(house.aptName)">
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
      favoriteState: false,
      num: Math.ceil(Math.random() * 984),
    };
  },
  props: {
    house: Object,
    favorites: Array,
  },
  methods: {
    ...mapGetters(["houses"]),
    selectHouse() {},
    colorChange(flag) {
      this.isColor = flag;
    },
    async setState(name) {
      let token = sessionStorage.getItem("access-token");
      if (this.$store.getters.checkUserInfo == null && token) {
        await this.getUserInfo(token);
      }
      if (this.$store.getters.checkUserInfo === null) {
        alert("로그인이 필요한 페이지입니다..");
        this.$router.push({ path: `/user/login` });
      } else {
        this.favoriteState = !this.favoriteState;
        if (this.favoriteState) {
          this.addFavorite();
        } else {
          this.getFavorite(name);
          this.deleteFavorite();
        }
      }
    },
    getFavorite(n) {
      this.$store.dispatch(Constant.GET_FAVORITE, n);
    },
    addFavorite() {
      const ads = this.house.sidoName.toString().substr(0, 2) + " " + this.house.gugunName + " " + this.house.dongName;

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
    deleteFavorite() {
      this.$store.dispatch(Constant.DELETE_FAVORITE, this.$store.state.idx);
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
