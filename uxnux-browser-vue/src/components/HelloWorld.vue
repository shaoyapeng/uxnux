<template>
  <div class="hello">
    <HomeHeader></HomeHeader>
    <div>
      <LeftNav></LeftNav>
      <div class="content"></div>
    </div>
  </div>
</template>

<script>
import HomeHeader from './frame/HomeHeader'
import LeftNav from './frame/LeftNav'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {}
  },
  components: {
    HomeHeader,
    LeftNav
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
