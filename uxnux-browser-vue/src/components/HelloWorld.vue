<template>
  <div id='raphael' class='hello'>
    <!-- <HomeHeader></HomeHeader>
    <div>
      <LeftNav></LeftNav>
      <div class='content'></div>
    </div>
    <Calender>
      <template slot='dateCell' slot-scope='{data}'>
        <div>{{data.year}} -- {{data.month}} -- {{data.day}}</div>
        {{mrs}}
      </template>
    </Calender> -->
    <Raphael width="1000" height="700" :ellipseOptionList="ellipseOptionList"></Raphael>
    <vue-particles
        class='particles'
        :particleOpacity='0.7'
        linesColor='#aaa'
        :particlesNumber='80'
        shapeType='circle'
        :particleSize='5'
        :linesWidth='2'
        :lineLinked='true'
        :lineOpacity='0.4'
        :linesDistance='150'
        :moveSpeed='3'
        :hoverEffect='true'
        hoverMode='grab'
        :clickEffect='true'
        clickMode='push'
      >
    </vue-particles>
  </div>
</template>

<script>

// import HomeHeader from './frame/HomeHeader'
// import LeftNav from './frame/LeftNav'
// import Calender from './Basic/calendar/Calendar'
import Raphael from './Basic/raphael/Raphael'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      ellipseOptionList: [{
        id: 0,
        'x': 100,
        'y': 100,
        'xr': 25,
        'yr': 25,
        'text': '',
        'fill': '#fdad00',
        'stroke': '#fd8613',
        'fillLiner': '90-#fd7600:20-#fde400',
        'strokeLiner': '90-#fd7600:20-#fde400',
        'textColor': '#666666',
        'fontSize': 14,
        lineList: [{ index: 1, path: 'M10 0L0 0' }, { index: 2, path: 'M10 0L0 0' }]
      }, {
        id: 1,
        'x': 200,
        'y': 200,
        'xr': 25,
        'yr': 25,
        'text': '',
        'fill': '#ef4799',
        'stroke': '#ef4799',
        'fillLiner': '90-#d316d8:20-#fe6375',
        'strokeLiner': '90-#d316d8:20-#fe6375',
        'textColor': '#666666',
        'fontSize': 14,
        lineList: []
      }, {
        id: 2,
        'x': 300,
        'y': 200,
        'xr': 25,
        'yr': 25,
        'text': '',
        'fill': '#43a1f7',
        'stroke': '#43a1f7',
        'fillLiner': '90-#2381ec:20-#39dafe',
        'strokeLiner': '90-#2381ec:20-#39dafe',
        'textColor': '#666666',
        'fontSize': 14,
        lineList: []
      }]
    }
  },
  components: {
    Raphael
  },
  mounted () {
  },
  methods: {
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
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
.particles {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: -1;
}
</style>
