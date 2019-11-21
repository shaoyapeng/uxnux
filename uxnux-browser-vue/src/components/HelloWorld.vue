<template>
  <div class="hello">
    <!-- <HomeHeader></HomeHeader>
    <div>
      <LeftNav></LeftNav>
      <div class="content"></div>
    </div> -->
    <Calender>
      <template slot="dateCell" slot-scope="{data}">
        <div>{{data.year}} -- {{data.month}} -- {{data.day}}</div>
        {{mrs}}
      </template>
    </Calender>
  </div>
</template>

<script>
// import HomeHeader from './frame/HomeHeader'
// import LeftNav from './frame/LeftNav'
import Calender from './Basic/calendar/Calendar'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      mrs: 'test'
    }
  },
  components: {
    // HomeHeader,
    // LeftNav,
    Calender
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
