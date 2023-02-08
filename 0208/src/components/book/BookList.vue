<template>
  <div>
    <div align="right">
      <router-link :to="`/join`">
        <b-button class="btn btn-secondary" style="margin-right :7px">등록</b-button>
      </router-link>
    </div>    
    <div class="m-2">
      <b-table-simple>
        <colgroup>
          <col width="5%">
          <col width="20%">
          <col width="50%">
          <col width="15%">          
          <col width="10%">          
        </colgroup>
        <b-thead head-variant="light">
          <b-tr>
            <b-th>번호</b-th>
            <b-th>ISBN</b-th>
            <b-th>제목</b-th>
            <b-th>저자</b-th>
            <b-th>가격</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <list-detail v-for="(user,index) in users" :key="index" :user="user" :tmp="index" ></list-detail>
        </b-tbody>
        <b-tfoot><b-tr><b-td colspan="7" ></b-td></b-tr></b-tfoot>
      </b-table-simple>

    </div>
  </div>
</template>

<script>
import ListDetail from '../book/ListDetail.vue'
import axios from '@/util/http-common';
import bus from "@/util/bus.js";

export default {
	components: { ListDetail,  },
  data() {
    return {
      users :[],
      selectedisbn : "",
      modifyisbn : "",
      mode:"도서등록"
    }
  },
  methods : {
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
      console.log("deleteBook....."+isbn)
      let test = confirm("정말로 삭제하시겠습니까?");
      if(test){
        axios
        .delete(`/vues/book/${isbn}`)
        .then(() => {
          this.selectAll()
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
      }
    },
  }, 
  created() {
    this.selectAll()
    bus.$on("deleteBook", (isbn) => {
      this.deleteBook(isbn)
    })
  },
  destroyed() {
    bus.$off('deleteBook');
  },
}
</script>


<style>
#ssafy_logo{
	width: 150px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
