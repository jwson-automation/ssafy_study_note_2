<template>
  <div class="mt-2" align="left">
    <h3>SSAFY {{mode}}</h3>
      <b-form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="도서번호" label-for="isbn-input" invalid-feedback="도서번호 필수입니다." :state="userState_isbn" >
          <b-form-input id="isbn-input" v-model="isbn" :state="userState_isbn" required disabled ></b-form-input>  
        </b-form-group>
        <b-form-group label="도서명" label-for="title-input" invalid-feedback="도서명은 필수입니다." :state="userState_title" >
          <b-form-input id="title-input" v-model="title" :state="userState_title" required disabled ></b-form-input>
        </b-form-group>
        <b-form-group label="저자" label-for="author-input" invalid-feedback="저자는 필수입니다." :state="userState_author" >
          <b-form-input id="author-input" v-model="author" :state="userState_author"  required disabled ></b-form-input>
        </b-form-group>
        <b-form-group label="가격" label-for="price-input" invalid-feedback="가격은 필수입니다." :state="userState_price" >
          <b-form-input id="price-input" v-model="price" :state="userState_price" required disabled ></b-form-input>
        </b-form-group>
        <b-form-group label="설명" label-for="content-input" invalid-feedback="설명는 필수입니다." :state="userState_content" >
          <b-form-input id="content-input" v-model="content" :state="userState_content" required disabled ></b-form-input>
        </b-form-group>
        
        <div align="right">
          <router-link :to="`/update/${isbn}`"><b-button variant="outline-primary mr-1">수정</b-button></router-link>
          <b-button variant="outline-danger mr-1"  @click="deleteBook(isbn)">삭제</b-button>
          <router-link :to="{name:'list'}"><b-button variant="outline-success">목록</b-button></router-link>

        </div>
      </b-form>
  </div>
</template>

<script>
import bus from "@/util/bus";
import axios from '@/util/http-common';

export default {
  props : {
    modifyisbn: {type:String},
  },
  data() {
    return {
      mode : {type:String},
      title:"",
      isbn:"",
      author:"",
      price:"",
      content:"",
      userState_title:null,
      userState_isbn:null, 
      userState_author:null, 
      userState_price:null, 
      userState_content:null,    
    }
  },
  methods: {
    deleteBook(isbn) {
      console.log('delete request to Bus : ' + isbn)
      bus.$emit("deleteBook", isbn)
    },
  },   
  
  created () {
    let id = this.$route.params.isbn
    console.log(id);
    if(id){
      this.mode = '도서확인'
    }else{
      this.mode = '도서없음'
    }
      axios //왜 파람스 붙는지? 어디에서 주는거야 저거
        .get(`/vuews/book/${this.$route.params.isbn}`)
        .then(({ data }) => {
          this.title = data.title
          this.isbn = data.isbn
          this.author = data.author
          this.price = data.price
          this.content = data.content
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
  }, 
}
</script>