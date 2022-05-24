import http from "@/api/http.js";
import Constant from "@/common/Constant.js";

export const state = () => ({
  notices: [],
  notice: {},
  //-----house
  houses: [],
})

export const getters = {
  notices(state) {
    console.log("store getters notices.");
    return state.notices;
  },
  houses(state) {
    console.log("store getters houses.");
    return state.houses;
  },
  //-----house
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
}

export const modules = {}