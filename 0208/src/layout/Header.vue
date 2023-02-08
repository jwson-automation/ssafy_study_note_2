<template>
  <header>
    <nav>
      <router-link to="/main">Home</router-link> |
      <router-link to="/">도서목록</router-link> |
      <router-link to="/list">About</router-link>
    </nav>
    
    <img src="../assets/ssafy_logo.png" id="ssafy_logo">
      <h3>도서관리</h3>
      <hr>
  </header>
</template>

<script>
export default {
  name: 'AppHeader',

  
};
</script>