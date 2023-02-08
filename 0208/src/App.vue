<template>
  <div id="app" class="container">
    <nav-header></nav-header>
    <router-view></router-view>
  </div>
</template>

<script>
import NavHeader from './layout/Header.vue';

export default {
  name: 'App',
  components: {
    NavHeader,
  },
};
</script>

<style></style>
