<template>
  <div class="ux-calender">
    <div class="calender-header" v-if="showHeader">
      <div>{{year}}年{{month}}月</div>
      <div>
        <span>上个月</span>
        <span>今天</span>
        <span>下个月</span>
      </div>
    </div>
    <div class="top-week" v-if="showTopWeek">
      <div class="ux-calender-cell">
        <span class="">周日</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周一</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周二</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周三</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周四</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周五</span>
      </div>
      <div class="ux-calender-cell">
        <span class="">周六</span>
      </div>
    </div>
    <div class="ux-calender-cell" v-for="(item, index) in calendarList" :key="index">
      <slot name="dateCell" :data="item"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ux-calender',
  props: {
    year: {
      type: Number,
      default () {
        return 2019
      }
    },
    month: {
      type: Number,
      default () {
        return 8
      }
    },
    header: {
      type: Boolean,
      default () {
        return true
      }
    },
    week: {
      type: Boolean,
      default () {
        return true
      }
    }
  },
  data () {
    return {
      weekList: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
      calendarList: [],
      showTopWeek: true,
      showHeader: this.header
    }
  },
  mounted () {
    this.init()
  },
  methods: {
    /**
     * 获得某一年的某一月有多少天
     */
    getDaysByYearAndMonth (year, month) {
      let date = new Date(year, month, 0)
      return date.getDate()
    },
    /**
     * 获某年某月的第一天是星期几
     */
    getWeekForFirstDay (year, month) {
      let date = new Date(year, month, 0)
      return date.getDay()
    },

    init () {
      let self = this
      let prevMonthDays = self.getDaysByYearAndMonth(self.year, self.month - 1, 0)
      let currentMonthDays = self.getDaysByYearAndMonth(self.year, self.month - 1, 1)
      let prevDays = self.getWeekForFirstDay(self.year, self.month - 1, 1)
      for (let i = (prevMonthDays - prevDays); i <= prevMonthDays; i++) {
        self.calendarList.push({
          year: self.year,
          month: self.month - 1,
          day: i,
          type: 'prev'
        })
      }
      for (let i = 1; i <= currentMonthDays; i++) {
        self.calendarList.push({
          year: self.year,
          month: self.month,
          day: i,
          type: 'current'
        })
      }
      for (let i = 1; i <= (42 - currentMonthDays - prevDays - 1); i++) {
        self.calendarList.push({
          year: self.year,
          month: self.month + 1,
          day: i,
          type: 'next'
        })
      }
    }
  }
}
</script>

<style lang="scss">
.ux-calender {
  font-size: 12px;
  padding: 20px;
  .calender-header {
    display: flex;
    justify-content: space-between;
    padding: 12px 20px;
    border-bottom: 1px solid #ebeef5;
    font-size: 18px;
  }
  .top-week {
    font-size: 20px;
    font-weight: 700;
    line-height: 60px;
    margin-top: 15px;
  }
  .ux-calender-cell {
    width: calc(((100% - 20px)/7));
    display: inline-block;
    border: 1px solid rgba(222,229,239,1);
  }
}
</style>
