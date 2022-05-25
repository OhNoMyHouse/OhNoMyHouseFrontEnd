import http from "@/api/http.js";
import Constant from "@/common/Constant.js";
import { login, findById } from "@/api/member.js";

export const strict = false;

export const state = () => ({
  notices: [],
  notice: {},
  //-----house
  houses: [],
  house: {},
  //-----filter
  sido: [],
  gugun: [],
  dong: [],
  apt: [],
  //-----favorite
  favorites: [],
  favorite: {},

  isLogin: false,
  isLoginError: false,
  userInfo: null,
});

export const getters = {
  //-----notice
  notices(state) {
    console.log("store getters notices.");
    return state.notices;
  },
  //-----house
  houses(state) {
    console.log("store getters houses.");
    return state.houses;
  },
  //-----filter
  sido(state) {
    console.log("store getters sido");
    return state.sido;
  },
  gugun(state) {
    console.log("store getters gugun");
    return state.gugun;
  },
  dong(state) {
    console.log("store getters dong");
    return state.dong;
  },
  apt(state) {
    console.log("store getters apt");
    return state.apt;
  },
  favorites() {
    console.log("store getters favorites");
    return state.favorites;
  },
  checkUserInfo: function (state) {
    return state.userInfo;
  },
};

export const mutations = {
  //-----notice
  [Constant.SET_NOTICES](state, payload) {
    state.notices = payload.notices;
  },
  [Constant.SET_NOTICE](state, payload) {
    state.notice = payload.notice;
  },
  //-----house
  [Constant.SET_HOUSES](state, payload) {
    state.houses = payload.houses;
  },
  //-----filter
  [Constant.SET_SIDO](state, payload) {
    state.sido = payload.sido;
  },
  [Constant.SET_GUGUN](state, payload) {
    state.gugun = payload.gugun;
  },
  [Constant.SET_DONG](state, payload) {
    state.dong = payload.dong;
  },
  [Constant.SET_APT](state, payload) {
    state.apt = payload.apt;
  },
  //-----favorite
  [Constant.SET_FAVORITE](state, payload) {
    state.favorite = payload.favorite;
  },
  [Constant.SET_FAVORITE](state, payload) {
    state.favorite = payload.favorite;
  },
  //-----user
  SET_IS_LOGIN: (state, isLogin) => {
    state.isLogin = isLogin;
  },
  SET_IS_LOGIN_ERROR: (state, isLoginError) => {
    state.isLoginError = isLoginError;
  },
  SET_USER_INFO: (state, userInfo) => {
    state.isLogin = true;
    state.userInfo = userInfo;
  },
};

export const actions = {
  //-----notice
  [Constant.GET_NOTICES](context) {
    http
      .get("notice")
      .then(({ data }) =>
        context.commit(Constant.SET_NOTICES, { notices: data })
      );
  },
  [Constant.GET_NOTICE](context, payload) {
    http
      .get(`notice/${payload.idx}`)
      .then(({ data }) =>
        context.commit(Constant.SET_NOTICE, { notice: data })
      );
  },
  [Constant.MODIFY_NOTICE](context, payload) {
    return http
      .put(`notice/${payload.notice.idx}`, {
        idx: payload.notice.idx,
        title: payload.notice.title,
        content: payload.notice.content,
      })
      .then(() => {
        console.log("store : notice 수정에 성공하였습니다.");
      });
  },
  [Constant.DELETE_NOTICE](context, payload) {
    return http.delete(`notice/${payload.idx}`).then(() => {
      console.log("store : notice 삭제에 성공하였습니다.");
    });
  },
  [Constant.REGIST_NOTICE](context, payload) {
    http
      .post("notice", {
        title: payload.notice.title,
        content: payload.notice.content,
      })
      .then(() => {
        console.log("store : notice 등록에 성공하였습니다.");
      });
  },
  //------house
  [Constant.GET_HOUSES](context, payload) {
    http.get(`map/search?word=${payload.word}`).then(({ data }) => {
      if (data.length != 0) {
        context.commit(Constant.SET_HOUSES, { houses: data });
        console.log("검색 결과 " + data.length + "개의 항목");
      } else {
        alert("등록된 정보가 없습니다.");
      }
    });
  },
  [Constant.GET_HOUSE](context, payload) {
    http.get(`map/search?word=${payload.word}`).then(({ data }) => {
      context.commit(Constant.SET_HOUSES, { houses: data });
      console.log("검색 결과 " + data.length + "개의 항목");
    });
  },
  //------filter
  [Constant.GET_SIDO](context) {
    http.get(`map/sido`).then(({ data }) => {
      context.commit(Constant.SET_SIDO, { sido: data });
    });
  },
  [Constant.GET_GUGUN](context, payload) {
    http.get(`map/gugun?sido=${payload.sido}`).then(({ data }) => {
      context.commit(Constant.SET_GUGUN, { gugun: data });
    });
  },
  [Constant.GET_DONG](context) {
    http.get(`map/dong`).then(({ data }) => {
      context.commit(Constant.SET_DONG, { dong: data });
    });
  },
  [Constant.GET_APT](context) {
    http.get(`map/apt`).then(({ data }) => {
      context.commit(Constant.SET_APT, { apt: data });
    });
  },
  //-----favorite
  [Constant.GET_FAVORITE](context) {
    http
      .get("favorite")
      .then(({ data }) =>
        context.commit(Constant.SET_FAVORITE, { favorite: data })
      );
  },
  [Constant.GET_FAVORITE](context, payload) {
    http
      .get(`favorite/${payload.idx}`)
      .then(({ data }) =>
        context.commit(Constant.SET_FAVORITE, { favorite: data })
      );
  },
  [Constant.DELETE_FAVORITE](context, payload) {
    return http.delete(`favorite/${payload.idx}`).then(() => {
      console.log("store : favorite 삭제에 성공하였습니다.");
    });
  },
  [Constant.REGIST_FAVORITE](context, payload) {
    http
      .post("favorite", {
        name: payload.favorite.name,
        address: payload.favorite.address,
        price: payload.favorite.price,
      })
      .then(() => {
        console.log("store : favorite 등록에 성공하였습니다.");
      });
  },
  //-----user
  async userConfirm({ commit }, user) {
    await login(
      user,
      (response) => {
        if (response.data.message === "success") {
          let token = response.data["access-token"];
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
          sessionStorage.setItem("access-token", token);
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true);
        }
      },
      () => {}
    );
  },
  getUserInfo({ commit }, token) {
    let decode_token = jwt_decode(token);
    findById(
      decode_token.userid,
      (response) => {
        if (response.data.message === "success") {
          commit("SET_USER_INFO", response.data.userInfo);
        } else {
          console.log("유저 정보 없음!!");
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },
};

export const modules = {};
