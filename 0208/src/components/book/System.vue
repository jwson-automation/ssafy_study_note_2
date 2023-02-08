<template>
  <router-view>
  <div>hi</div>
  </router-view>
</template>

<script>
import bus from "@/util/bus";
import axios from "@/util/http-common";

export default {  
  data() {
    return {
      users: [],
    };
  },
    created() {
      bus.$on("deleteBook", (isbn) => {
        this.deleteBook(isbn);
      });
    },
  methods: {
    selectAll() {
      axios
      .get('/vuews/book')
      .then(({ data }) => {
        this.users = data;
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
    }, 
    deleteBook(isbn) {
      console.log("deleteBook....." + isbn);
      let test = confirm("정말로 삭제하시겠습니까?");
      if (test) {
        axios
          .delete(`/vuews/book/${isbn}`)
          .then(() => {
            this.selectAll();
            
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      }
      
    },
  },
  destroyed() {
      bus.$off("deleteBook");
    },
};
</script>