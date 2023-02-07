const addr = "http://127.0.0.1:9999/vuews/book";

export default {
  template: `<div>
      <table border="1" style="border-collapse: collapse">
      <tbody>
      <tr>
        <th>ISBN</th>
        <th><input type="text" v-model="isbn" placeholder="ISBN을 입력하세요"></th>
      </tr>
      <tr>
        <th>제목</th>
        <th><input type="text" v-model="title" placeholder="제목을 입력하세요"></th>
        
        </tr>
        <tr>
        <th>저자</th>
        <th><input type="text" v-model="author" placeholder="작가를 입력하세요"></th>
        </tr>
        <tr>
        <th>가격</th>
        <th><input type="text" v-model="price" placeholder="가격을 입력하세요"></th> 
      </tr>
      </tbody>
          <hr>
          <input type="button" v-on:click="saveArticle" value="입력"><br> 
      </table>
      <hr>
      <router-link :to="{name: 'boardlist'}">목록</router-link>
    </div>
   `,
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
    getAllArticles(no) {
      axios
        .get(addr)
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
    saveArticle() {
      axios({
        method: "post",
        url: addr,
        data: { isbn: this.isbn, title: this.title, author: this.author, price: this.price },
      })
        .then((response) => {
          this.getAllArticles();
          this.$router.push({ name: "bookList" });
        })
        .catch((error) => {
          console.dir(error);
        });
      this.title = "";
      this.content = "";
    },
  },

  created() {
    console.dir(this.$route); // 현재 호출된 해당 라우터 정보
    this.no = this.$route.params.no;
  },
};
