import Vuex from "vuex";
import Constant from "@/common/Constant.js";
import axios from "axios";

const store = () =>
  new Vuex.Store({
    state: {
      counter: 0,
      notices: [],
      notice: {},
      //-----house
      houses: [],
    },
    getters: {
      notices(state) {
        console.log("store getters notices.");
        return state.notices;
      },
      houses(state) {
        console.log("store getters houses.");
        return state.houses;
      },
      //-----house
    },
    mutations: {
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
    },
    actions: {
      //-----notice
      [Constant.GET_NOTICES](context) {
        axios
          .get("http://127.0.0.1:8080/notice")
          .then(({ data }) =>
            context.commit(Constant.SET_NOTICES, { notices: data })
          );
      },
      [Constant.GET_NOTICE](context, payload) {
        axios
          .get(`http://127.0.0.1:8080/notice/${payload.idx}`)
          .then(({ data }) => context.commit(Constant.SET_NOTICE, { notice: data }));
      },
      [Constant.MODIFY_NOTICE](context, payload) {
        return axios
          .put(`http://127.0.0.1:8080/notice/${payload.notice.idx}`, {
            idx: payload.notice.idx,
            question: payload.notice.question,
            answer: payload.notice.answer,
          })
          .then(() => {
            console.log("store : notice 수정에 성공하였습니다.");
          });
      },
      [Constant.DELETE_NOTICE](context, payload) {
        return axios.delete(`http://127.0.0.1:8080/notice/${payload}`).then(() => {
          console.log("store : notice 삭제에 성공하였습니다.");
        });
      },
      [Constant.REGIST_NOTICE](context, payload) {
        axios
          .post("http://127.0.0.1:8080/notice", {
            question: payload.notice.question,
            answer: payload.notice.answer,
          })
          .then(() => {
            console.log("store : notice 등록에 성공하였습니다.");
          });
      },
      //------house
      [Constant.GET_HOUSES](context, payload) {
        axios
          .get(`http://127.0.0.1:8080/map/search?word=${payload.word}`)
          .then(({ data }) => {
            context.commit(Constant.SET_HOUSES, { houses: data });
            console.log("검색 결과 " + data.length + "개의 항목");
          });
      },
    },
    modules: {},
  });

export default store;
