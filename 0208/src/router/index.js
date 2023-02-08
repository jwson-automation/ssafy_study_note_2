import Vue from "vue";
import VueRouter from "vue-router";
import List from "../components/book/BookList.vue";
import SingleDetail from "../components/book/BookView.vue";
import Join from "../components/book/BookCreateModify.vue";
import Main from "../views/Home.vue";
import System from "../components/book/System.vue";
import About from "../views/About.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/main",
    name: "main",
    component: Main,
  },
  {
    path: "/about",
    name: "about",
    component: About,
  },
  {
    path: "/",
    name: "system",
    component: System,
    redirect: "/list",
    children: [
      {
        path: "/list",
        name: "list",
        component: List,
      },
      {
        path: "/detail/:isbn",
        name: "detail",
        component: SingleDetail,
      },
      {
        path: "/update/:isbn",
        name: "update",
        component: Join,
      },
      {
        path: "/join",
        name: "join",
        component: Join,
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
