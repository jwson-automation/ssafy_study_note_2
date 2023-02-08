<template>
  <div>
    <b-table-simple hover bordered>
      <b-tbody>
        <b-tr>
          <b-th> 아이디 </b-th>
          <b-td> {{user.userid}} </b-td>
          <b-th> 회원명 </b-th>
          <b-td> {{user.username}} </b-td>
        </b-tr>
        <b-tr>
          <b-th> 이메일 </b-th>
          <b-td> {{user.email}} </b-td>
          <b-th> 가입일 </b-th>
          <b-td> {{user.joindate}} </b-td>
        </b-tr>
        <b-tr>
          <b-th> 주소 </b-th>
          <b-td colspan="3"> {{user.address}} </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <div align="right">
      <router-link :to="{name:'list'}"><b-button variant="outline-success">목록</b-button></router-link>
    </div>
  </div>
</template>

<script>
import axios from '@/util/http-common';

export default {
  data () {
    return {
      user: {},
    }
  },
  created () {
      axios
        .get('/vues/book/'+this.$route.params.userid)
        .then(({ data }) => {
          this.user = data
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        })

  }
}
</script>