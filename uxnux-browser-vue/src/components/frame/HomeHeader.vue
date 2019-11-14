<template>
  <div class="homeheader">
    <div class="logo fn-inline">
      <span>Uxnux</span>
    </div>
    <div class="menu fn-inline">

    </div>
    <div class="self fn-inline">

    </div>
  </div>
</template>
<script>
export default {
  name: 'homeheader',
  props: {
    theme: {
      type: String,
      default () {
        return 's'
      }
    }
  }
}
</script>
<style lang="scss">
</style>
