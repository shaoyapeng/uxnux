<template>
  <div class="login">
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
    <Login></Login>
  </div>
</template>

<script>
import Login from '../components/login/Login'
export default {
  name: 'login',
  components: {
    Login
  }
}
</script>

<style lang="scss">
.login .particles {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: -1;
}
</style>
