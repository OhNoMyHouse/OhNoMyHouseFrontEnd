import http from "@/api/http.js";
import Constant from "@/common/Constant.js";

export const state = () => ({
  notices: [],
  notice: {},
  //-----house
  houses: [],
  //-----filter
  sido: [],
  gugun: [],
  dong: [],
  apt: [],
  //-----favorite
  favorites: [],
  favorite: {},
})

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
}

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
}

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
      .then(({ data }) => context.commit(Constant.SET_NOTICE, { notice: data }));
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
    http
      .get(`map/search?word=${payload.word}`)
      .then(({ data }) => {
        if (data.length != 0) {
          context.commit(Constant.SET_HOUSES, { houses: data });
          console.log("검색 결과 " + data.length + "개의 항목");
        } else {
          alert("등록된 정보가 없습니다.");
        }
      });
  },
  [Constant.GET_HOUSE](context, payload) {
    http
      .get(`map/search?word=${payload.word}`)
      .then(({ data }) => {
        context.commit(Constant.SET_HOUSES, { houses: data });
        console.log("검색 결과 " + data.length + "개의 항목");
      });
  },
  //------filter
  [Constant.GET_SIDO](context) {
    http
      .get(`map/sido`)
      .then(({ data }) => {
        context.commit(Constant.SET_SIDO, { sido: data });
        console.log(data);
      });
  },
  [Constant.GET_GUGUN](context) {
    http
      .get(`map/gugun`)
      .then(({ data }) => {
        context.commit(Constant.SET_GUGUN, { gugun: data });
      });
  },
  [Constant.GET_DONG](context) {
    http
      .get(`map/dong`)
      .then(({ data }) => {
        context.commit(Constant.SET_DONG, { dong: data });
      });
  },
  [Constant.GET_APT](context) {
    http
      .get(`map/apt`)
      .then(({ data }) => {
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
      .then(({ data }) => context.commit(Constant.SET_FAVORITE, { favorite: data }));
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
}

export const modules = {}