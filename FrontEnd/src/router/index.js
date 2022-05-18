import Vue from "vue";
import VueRouter from "vue-router";
import MainContentView from "@/views/MainContentView.vue";
import QnaView from "@/views/QnaView.vue";
import BoardDelete from "@/components/board/BoardDelete.vue";
import BoardDetailView from "@/components/board/BoardDetailView.vue";
import BoardModify from "@/components/board/BoardModify.vue";
import BoardRegistView from "@/components/board/BoardRegistView.vue";
import UserLoginView from "@/components/user/UserLoginView.vue";
import UserRegistView from "@/components/user/UserRegistView.vue";
import UserSearchView from "@/components/user/UserSearchView.vue";
import UserUpdateView from "@/components/user/UserUpdateView.vue";
import QnaListView from "@/components/qna/QnaListView.vue";
import QnaRegistFormView from "@/components/qna/QnaRegistFormView.vue";
import QnaDetailView from "@/components/qna/QnaDetailView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainContentView,
  },
  {
    path: "/board",
    name: "board",
    component: BoardRegistView,
    redirect: "/board/makepoll",
    children: [
      {
        path: "/update_form",
        name: "boardUpdateForm",
        component: BoardDetailView,
      },
      {
        path: "/update",
        name: "boardUpdate",
        component: BoardModify,
      },
      {
        path: "/delete",
        name: "boardDelete",
        component: BoardDelete,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: UserUpdateView,
    redirect: "/user/update_form",
    children: [
      {
        path: "/login",
        name: "userLogin",
        component: UserLoginView,
      },
      {
        path: "/regist",
        name: "userRegist",
        component: UserRegistView,
      },
      {
        path: "/search",
        name: "userSearch",
        component: UserSearchView,
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: QnaView,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnaList",
        component: QnaListView,
      },
      {
        path: "makeqna_form",
        name: "qnaMakeqnaForm",
        component: QnaRegistFormView,
      },
      {
        path: "detail/:idx",
        name: "qnaDetail",
        component: QnaDetailView,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
