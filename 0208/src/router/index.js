import Vue from "vue";
import VueRouter from "vue-router";
import List from "../views/List.vue";
import SingleDetail from "../views/SingleDetail.vue";
import Join from "../views/Join.vue";
import Main from "../views/Main.vue";
import System from "../views/System.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/main",
    name: "main",
    component: Main,
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
