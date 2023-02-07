const url = "http://127.0.0.1:9999/vuews/book";
export default {
  template: `
  <div>
      {{no+1}}번 게시물 수정폼
      <hr>
      <div>
      <table border="1" style="border-collapse:collapse">
      <tr>
        <td>도서번호</td>
        <td><input type="text" v-model="isbn"></td>
      </tr>
      <tr>
        <td>도서명</td>
        <td><input type="text" v-model="title"></td>
      </tr>
      <tr>
        <td>저자</td>
        <td><input type="text" v-model="author"></td>
      </tr>
      <tr>
        <td>가격</td>
        <td><input type="number" v-model="price"></td>
      </tr>
      
    </table>
      <input type="button" v-on:click="updateArticle()" value="입력"><br> 
    </div>
  <hr>
  <router-link :to="{name: 'boardlist'}">목록</router-link>
 </div>`,
  data() {
    return {
      no: 0,
      isbn: this.isbn,
      title: this.title,
      author: this.author,
      price: this.price,
    };
  },
  methods: {
    updateArticle() {
      axios
        .put(url + "/" + this.isbn, {
          isbn: this.isbn,
          title: this.title,
          author: this.author,
          price: this.price,
        })
        .then((response) => {
          this.getAllArticles();
          this.$router.push({ name: "bookList" });
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    getAllArticles(no) {
      axios
        .get(url)
        .then((response) => {
          console.log(response);
          this.isbn = response.data[no].isbn;
          this.title = response.data[no].title;
          this.author = response.data[no].author;
          this.price = response.data[no].price;
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
  created() {
    this.no = this.$route.params.no;
    this.getAllArticles(this.no);
  },
};
