import Vuex from "vuex";
import Constant from "@/common/Constant.js";
import axios from "axios";

const store = () =>
  new Vuex.Store({
    state: {
      counter: 0,
      qnas: [],
      qna: {},
      //-----house
      houses: [],
    },
    getters: {
      qnas(state) {
        console.log("store getters qnas.");
        return state.qnas;
      },
      houses(state) {
        console.log("store getters houses.");
        return state.houses;
      },
      //-----house
    },
    mutations: {
      [Constant.SET_QNAS](state, payload) {
        state.qnas = payload.qnas;
      },
      [Constant.SET_QNA](state, payload) {
        state.qna = payload.qna;
      },
      //-----house
      [Constant.SET_HOUSES](state, payload) {
        state.houses = payload.houses;
      },
    },
    actions: {
      [Constant.GET_QNAS](context) {
        axios
          .get("http://127.0.0.1:8080/notice")
          .then(({ data }) =>
            context.commit(Constant.SET_QNAS, { qnas: data })
          );
      },
      [Constant.GET_QNA](context, payload) {
        axios
          .get(`http://127.0.0.1:8080/qna/${payload.idx}`)
          .then(({ data }) => context.commit(Constant.SET_QNA, { qna: data }));
      },
      [Constant.MODIFY_QNA](context, payload) {
        return axios
          .put(`http://127.0.0.1:8080/qna/${payload.qna.idx}`, {
            idx: payload.qna.idx,
            question: payload.qna.question,
            answer: payload.qna.answer,
          })
          .then(() => {
            console.log("store : qna 수정에 성공하였습니다.");
          });
      },
      [Constant.DELETE_QNA](context, payload) {
        return axios.delete(`http://127.0.0.1:8080/qna/${payload}`).then(() => {
          console.log("store : qna 삭제에 성공하였습니다.");
        });
      },
      [Constant.REGIST_QNA](context, payload) {
        axios
          .post("http://127.0.0.1:8080/qna", {
            question: payload.qna.question,
            answer: payload.qna.answer,
          })
          .then(() => {
            console.log("store : qna 등록에 성공하였습니다.");
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
