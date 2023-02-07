const url = "http://127.0.0.1:9999/vuews/book";

export default {
  template: `<div>
  <table border="1" style="border-collapse: collapse">
  <tr>
  <th>번호</th>
  <th>ISBN</th>
  <th>제목</th>
  <th>저자</th>
  <th>가격</th>
  </tr>
  <tbody id="studentinfo">
    <tr v-for="article,i in articles">
      <td>{{i+1}}</td>
      <td>{{article.isbn}}</td>
      <td><router-link :to="'/board/detail/' + (i+1)">{{article.title}}</router-link></td>
      <td>{{article.author}}</td>
      <td>{{article.price}}</td>
    </tr>
  </tbody>
</table>
        <router-link to="/board/write">등록</router-link>
  </div>`,

  data() {
    return {
      articles: [],
    };
  },

  methods: {
    getAllArticles() {
      axios
        .get(url)
        .then((response) => {
          console.log(response);
          this.articles = response.data;
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },

  created() {
    this.getAllArticles();
  },
};
