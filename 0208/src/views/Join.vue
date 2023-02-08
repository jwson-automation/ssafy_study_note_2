<template>
  <div class="mt-2" align="left">
    <h3>SSAFY {{mode}}</h3>
      <b-form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="도서번호" label-for="isbn-input" invalid-feedback="도서번호 필수입니다." :state="userState_isbn" >
          <b-form-input id="isbn-input" v-model="isbn" :state="userState_isbn" required disabled v-if="mode == '도서수정'"></b-form-input>
          <b-form-input id="isbn-input" v-model="isbn" :state="userState_isbn" required v-else ></b-form-input>
        </b-form-group>
        <b-form-group label="도서명" label-for="title-input" invalid-feedback="도서명은 필수입니다." :state="userState_title" >
          <b-form-input id="title-input" v-model="title" :state="userState_title" required></b-form-input>
        </b-form-group>
        <b-form-group label="저자" label-for="author-input" invalid-feedback="저자는 필수입니다." :state="userState_author" >
          <b-form-input id="author-input" v-model="author" :state="userState_author"  required></b-form-input>
        </b-form-group>
        <b-form-group label="가격" label-for="price-input" invalid-feedback="가격은 필수입니다." :state="userState_price" >
          <b-form-input id="price-input" v-model="price" :state="userState_price" required></b-form-input>
        </b-form-group>
        <b-form-group label="설명" label-for="content-input" invalid-feedback="설명는 필수입니다." :state="userState_content" >
          <b-form-input id="content-input" v-model="content" :state="userState_content" required></b-form-input>
        </b-form-group>
        
        <div align="right">
          <b-button variant="outline-primary mr-1"  @click="handleOk">저장</b-button>
          <b-button variant="outline-danger mr-1"  @click="deleteBook(isbn)">삭제</b-button>
          <router-link :to="{name:'list'}"><b-button variant="outline-success">목록</b-button></router-link>

        </div>
      </b-form>
  </div>
</template>

<script>
import axios from '@/util/http-common';
import bus from '@/util/bus';

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
    checkFormValidity() {
      this.userState_title = this.$refs.form[0].checkValidity()
      this.userState_isbn = this.$refs.form[1].checkValidity()
      this.userState_author = this.$refs.form[2].checkValidity()
      this.userState_price = this.$refs.form[3].checkValidity()
      this.userState_content = this.$refs.form[4].checkValidity() 
      
      // console.log(this.userState.content);
      const valid = this.$refs.form.checkValidity() //전부 통과면 true리턴
      return valid;
    },
    resetModal() {
      this.userState_title=null,
      this.userState_isbn=null, 
      this.userState_author=null, 
      this.userState_price=null, 
      this.userState_content=null
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }

      if(this.mode == "도서가입"){
        //저장.
        axios
        .post('/vuews/book', {
          isbn : this.isbn,
          title : this.title,
          author : this.author,
          price: this.price,
          content : this.content
        })
        .then(() => {
          this.makeToast('success','저장 성공')
        })
        .catch(() => {
          this.makeToast('warning','에러가 발생했습니다.')
        });    
      }else{
        //수정  
        axios
        .put(`/vuews/book/${this.isbn}`, {
          isbn : this.isbn,
          title : this.title,
          author : this.author,
          price: this.price,
          content : this.content
        })
        .then(() => {
          this.makeToast('success','저장 성공')
        })
        .catch(() => {
          this.makeToast('warning','에러가 발생했습니다.')
        });
      }
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    makeToast(variant = null, message) {
        this.$bvToast.toast(message, {
          title: `Result: ${variant || 'default'}`,
          variant: variant,
          solid: true
        })
      }
  },
  created () {
    let id = this.$route.params.isbn
    console.log(id);
    if(id){
      this.mode = '도서수정'
    }else{
      this.mode = '도서가입'
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