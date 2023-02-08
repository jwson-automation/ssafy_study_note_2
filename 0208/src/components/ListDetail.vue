<template>
  <tr>
    <td>{{ tmp + 1 }}</td>
    <td>{{ user.isbn }}</td>
    <td><router-link :to="`/update/${user.isbn}`">{{ user.title }}</router-link></td>
    <td>{{ user.author }}</td>
    <td>{{ user.price }}</td>    
    
  </tr>
</template>

<script>
export default {
  props: 
  {
    user: { type: Object },
    tmp: { type: Number }
    },
  methods : {
  }
}
</script>