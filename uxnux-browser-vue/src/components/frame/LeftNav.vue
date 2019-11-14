<template>
  <div class="leftnav">
    <ul>
      <li v-for="(firstMenuItem, firstMenuIndex) in leftMenuList" :key="firstMenuIndex">
        <div class="level1" :class="firstMenuItem.isActive ? 'active': ''" @click="onLeftNavClick(firstMenuItem)">
          <i class="fn-inline iconfont iconlocalico"></i>
          <span class="fn-inline">菜单一</span>
          <em class="fn-inline" :class="hasChildren(firstMenuItem) ? (firstMenuItem.isOpen ? 'down': 'up') : ''"></em>
        </div>
        <transition>
          <ul v-if="hasChildren(firstMenuItem)" v-show="firstMenuItem.isOpen">
            <li v-for="(secondMenuItem, secondMenuIndex) in getChildren(firstMenuItem)"
            :key="firstMenuIndex + '-' + secondMenuIndex">
              <div class="level2" :class="secondMenuItem.isActive ? 'active': ''" @click="onLeftNavClick(secondMenuItem)">
                <i class="fn-inline iconfont"></i>
                <span class="fn-inline">菜单1-1</span>
                <em class="fn-inline" :class="hasChildren(secondMenuItem) ? (secondMenuItem.isOpen ? 'down': 'up') : ''"></em>
              </div>
              <transition>
                <ul v-if="hasChildren(secondMenuItem)" v-show="secondMenuItem.isOpen">
                  <li v-for="(thirdMenuItem, thirdMenuIndex) in getChildren(secondMenuItem)"
                  :key="firstMenuIndex + '-' + secondMenuIndex + '-' + thirdMenuIndex">
                    <div class="level3">
                      <em class="fn-inline"></em>
                      <span class="fn-inline">菜单1-1</span>
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
    }
  },
  data () {
    return {
      leftMenuList: [
        { name: '菜单一', children: [ { name: '菜单1-1' }, { name: '菜单1-1' } ], isOpen: true, isActive: true },
        { name: '菜单一' } ]
    }
  },
  methods: {
    onLeftNavClick (item) {
      let self = this
      item.isActive = true
      if (self.hasChildren(item)) {
        item.isOpen = !item.isOpen
        let itemChildren = self.getChildren(item)
        if (self.hasChildren(itemChildren)) {}
      }
    },
    hasChildren (item) {
      return item.hasOwnProperty('children') &&
      Array.isArray(item.children) &&
      item.children.length > 0
    },
    getChildren (item) {
      return item.children && Array.isArray(item.children) ? item.children : []
    }
  }
}
</script>
<style lang="scss">
</style>
