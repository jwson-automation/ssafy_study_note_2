const url = "http://127.0.0.1:9999/vuews/book";

export default {
  template: `<div>
    {{no+1}}번 게시물 상세정보
    <hr>
    <table border="1" style="border-collapse: collapse">
      <tr>
      <th>번호</th>
      <th>ISBN</th>
      <th>제목</th>
      <th>저자</th>
      <th>가격</th>
      </tr>
    <tbody>
      <tr>
        <td>{{(no+1)}}</td>
        <td v-model:isbn>{{article[no].isbn}}</td>
        <td>{{article[no].title}}</td>
        <td>{{article[no].author}}</td>
        <td>{{article[no].price}}</td>
      </tr>
    </tbody>
    </table>
    <div>
      <router-link :to="{name: 'boardlist'}">목록</router-link>
      <router-link :to="'/board/update/' + no">수정</router-link>
      <a v-on:click="deleteArticle()" href ="#">삭제</a>
    </div>
   </div>`,
  data() {
    return {
      no: 0,
      article: this.article,
      isbn: this.isbn,
    };
  },
  methods: {
    getAllArticles(no) {
      axios
        .get(url)
        .then((response) => {
          console.log(response);
          this.article = response.data;
          console.log(this.article);
          this.isbn = this.article[no - 1].isbn;
          console.log(this.isbn);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    deleteArticle() {
      console.log(url + "/" + this.isbn);
      axios
        .delete(url + "/" + this.isbn)
        .then((response) => {
          this.$router.push({ name: "bookList" });
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
  created() {
    console.dir(this.$route); // 현재 호출된 해당 라우터 정보
    this.no = this.$route.params.no - 1;

    this.getAllArticles(this.no + 1);
  },
};
