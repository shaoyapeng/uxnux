<template>
  <div class="leftnav">
    <ul>
      <li v-for="(firstMenuItem, firstMenuIndex) in leftMenuList" :key="firstMenuIndex">
        <div class="level1" @click="onLeftNavClick(firstMenuIndex)" :class="firstMenuActive === firstMenuIndex ? 'active' : ''">
          <i class="fn-inline iconfont iconlocalico"></i>
          <span class="fn-inline">菜单一</span>
          <em class="fn-inline" ></em>
        </div>
        <transition>
          <ul v-if="hasChildren(firstMenuItem)" v-show="firstMenuActive === firstMenuIndex && firstMenuOpen">
            <li v-for="(secondMenuItem, secondMenuIndex) in getChildren(firstMenuItem)"
            :key="firstMenuIndex + '-' + secondMenuIndex">
              <div class="level2" @click="onLeftNavClick(firstMenuIndex, secondMenuIndex)" :class="secondMenuActive === secondMenuIndex ? 'active' : ''">
                <i class="fn-inline iconfont"></i>
                <span class="fn-inline">菜单1-1</span>
                <em class="fn-inline" ></em>
              </div>
              <transition>
                <ul v-if="hasChildren(secondMenuItem)" v-show="secondMenuActive === secondMenuIndex && secondMenuOpen">
                  <li v-for="(thirdMenuItem, thirdMenuIndex) in getChildren(secondMenuItem)"
                  :key="firstMenuIndex + '-' + secondMenuIndex + '-' + thirdMenuIndex">
                    <div class="level3" @click="onLeftNavClick(firstMenuIndex, secondMenuIndex, thirdMenuIndex)" :class="thirdMenuActive === thirdMenuIndex ? 'active' : ''">
                      <em class="fn-inline"></em>
                      <span class="fn-inline">菜单1-1-1</span>
                    </div>
                  </li>
                </ul>
              </transition>
            </li>
          </ul>
        </transition>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: 'leftnav',
  props: {
    theme: {
      type: String,
      default () {
        return 's'
      }
    },
    leftActiveNav: {
      type: Array,
      default () {
        return [0, 0]
      }
    },
    isOpen: {
      type: Boolean,
      default () {
        return false
      }
    }
  },
  data () {
    return {
      leftMenuList: [
        { name: '菜单一', children: [ { name: '菜单1-1' }, { name: '菜单1-1' } ] },
        { name: '菜单一' }
      ],
      firstMenuActive: 0,
      secondMenuActive: 0,
      thirdMenuActive: 0,
      firstMenuOpen: true,
      secondMenuOpen: true
    }
  },
  methods: {
    onLeftNavClick (firstIndex, secondIndex, thirdIndex) {
      let self = this
      self.secondMenuOpen = thirdIndex >= 0 ? true : !self.secondMenuOpen
      self.firstMenuOpen = thirdIndex >= 0 || secondIndex >= 0 ? true : !self.firstMenuOpen
      self.firstMenuActive = firstIndex >= 0 ? firstIndex : self.firstMenuActive
      self.secondMenuActive = secondIndex >= 0 ? secondIndex : self.secondMenuActive
      self.thirdMenuActive = thirdIndex >= 0 ? thirdIndex : self.thirdMenuActive
    },
    hasChildren (item) {
      return item.hasOwnProperty('children') &&
      Array.isArray(item.children) &&
      item.children.length > 0
    },
    getChildren (item) {
      return item.children && Array.isArray(item.children) ? item.children : []
    },
    getDefaultActiveNav () {
      let self = this
      self.leftActiveNav.forEach(function (item, index) {
      })
    }
  },
  mounted () {
    let self = this
    this.getDefaultActiveNav()
    console.log(self.level1)
    console.log(self.level2)
    console.log(self.level3)
  }
}
</script>
<style lang="scss">
</style>
