export const DateFn = { // 时间处理函数
  formatDate (date, format) {
    /* 通用化格式时间 */
    // new Date( year, month, date, hrs, min, sec)
    // new Date() ;     //参数可以为整数; 也可以为字符串; 但格式必须正确
    // examplenew Date(2009,1,1);       //正确  new Date("2009/1/1");    //正确
    // example  formatDate(new Date(), "当前日期为：YYYY-MM-DD，星期w，为第qq季度，时间为：hh:mm:ss:c")
    date = new Date(date)
    let o = {
      'M+': date.getMonth() + 1, // month  MM
      'D+': date.getDate(), // day  DD
      'h+': date.getHours(), // hour  hh
      'm+': date.getMinutes(), // minute mm
      's+': date.getSeconds(), // second ss
      'q+': Math.floor((date.getMonth() + 3) / 3), // quarter 季度 q
      'c': date.getMilliseconds(), // millisecond 毫秒 c
      'w': ['一', '二', '三', '四', '五', '六', '日'][date.getDay() - 1] // week 星期 w
    }
    if (/(Y+)/.test(format)) { // year  YYYY
      format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    for (let k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
      }
    }
    return format
  },
  parserDate (date) { // 如何将标准时间 Thu Mar 07 2019 12:00:00 GMT+0800 (中国标准时间)转换为2019-03-07 12:00:00
    date = new Date(date)
    let resDate = date.getFullYear() + '-' + (function (p) {
      return p < 10 ? '0' + p : p
    })(date.getMonth() + 1) + '-' + (function (p) {
      return p < 10 ? '0' + p : p
    })(date.getDate())
    let resTime = (function (p) {
      return p < 10 ? '0' + p : p
    })(date.getHours()) + ':' + (function (p) {
      return p < 10 ? '0' + p : p
    })(date.getMinutes()) + ':' + (function (p) {
      return p < 10 ? '0' + p : p
    })(date.getSeconds())
    return resDate + ' ' + resTime
  },
  parseSandardDate (date) { // 如何将2019-03-07 12:00:00转换为标准时间 Thu Mar 07 2019 12:00:00 GMT+0800 (中国标准时间)
    let t = Date.parse(date)
    if (!isNaN(t)) {
      return new Date(Date.parse(date.replace(/-/g, '/')))
    } else {
      return new Date()
    }
  },
  getmillisecond (time1) { // 时间格式‘2016 - 01 - 01 17: 22: 37’字符串转为时间戳（毫秒）
    let date = new Date(time1.replace(/-/g, '/')) // 开始时间
    return date.getTime()
  },
  betweenTime (date1, date2) {
    date1 = new Date(date1.replace(/-/g, '/'))
    date2 = new Date(date2.replace(/-/g, '/'))
    let date3 = date2.getTime() - date1.getTime() // 时间差的毫秒数
    // 计算出相差天数
    let days = Math.floor(date3 / (24 * 3600 * 1000))
    // 计算出小时数
    let leave1 = date3 % (24 * 3600 * 1000) // 计算天数后剩余的毫秒数
    let hours = Math.floor(leave1 / (3600 * 1000))
    // 计算相差分钟数
    let leave2 = leave1 % (3600 * 1000) // 计算小时数后剩余的毫秒数
    let minutes = Math.floor(leave2 / (60 * 1000))
    // 计算相差秒数
    let leave3 = leave2 % (60 * 1000) // 计算分钟数后剩余的毫秒数
    let seconds = Math.round(leave3 / 1000)
    //
    let str = ''
    if (days > 0) {
      str += days + '天 '
    }
    if (hours > 0) {
      str += hours + '小时 '
    }
    if (minutes > 0) {
      str += minutes + ' 分钟'
    }
    if (seconds > 0) {
      str += seconds + ' 秒'
    }
    return str
  },
  betweenTimeByLong (date1, date2) {
    let date3 = date2 - date1 // 时间差的毫秒数
    // 计算出相差天数
    let days = Math.floor(date3 / (24 * 3600 * 1000))
    // 计算出小时数
    let leave1 = date3 % (24 * 3600 * 1000) // 计算天数后剩余的毫秒数
    let hours = Math.floor(leave1 / (3600 * 1000))
    // 计算相差分钟数
    let leave2 = leave1 % (3600 * 1000) // 计算小时数后剩余的毫秒数
    let minutes = Math.floor(leave2 / (60 * 1000))
    // 计算相差秒数
    let leave3 = leave2 % (60 * 1000) // 计算分钟数后剩余的毫秒数
    let seconds = Math.round(leave3 / 1000)
    //
    let str = ''
    if (days > 0) {
      str += days + '天 '
    }
    if (hours > 0) {
      str += hours + '小时 '
    }
    if (minutes > 0) {
      str += minutes + ' 分钟'
    }
    if (seconds > 0) {
      str += seconds + ' 秒'
    }
    return str
  },
  formatDateValue (value) {
    /* 检测年月日时分秒20190609110000 返回yyyy-MM-dd hh:mm:ss */
    let me = this
    if (typeof (value) === 'undefined' || value === '') {
      return ''
    }
    if (value.length !== 14) { // 长度14
      return value
    } else if (!(value.indexOf('15') === 0 || value.indexOf('16') === 0 || value.indexOf('17') === 0 ||
        value.indexOf('19') === 0 || value.indexOf('20') === 0)) {
      // 不是18或者19或者20开头
      return value
    } else if (isNaN(value)) { // 是字符串（非数字）
      return value
    } else {
      let yue = me.strToInt(value.substring(4, 6))
      let day = me.strToInt(value.substring(6, 8))
      let hour = me.strToInt(value.substring(8, 10))
      let min = me.strToInt(value.substring(10, 12))
      let second = strToInt(value.substring(12, 14))
      if (yue < 1 || yue > 12) { // 月小于1大于12
        return value
      } else if (day < 1 || day > 31) { // 日小于1大于31
        return value
      } else if (hour < 0 || hour > 24) { // 时 小于0大于24
        return value
      } else if (min < 0 || min > 59) { // 分 小于0大于59
        return value
      } else if (second < 0 || second > 59) { // 秒 小于0大于59
        return value
      } else {
        return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(6, 8) +
          ' ' + value.substring(8, 10) + ':' + value.substring(10, 12) + ':' + value.substring(12, 14)
      }
    }
  },
  getMonths (time, len, direction) {
    /* 返回指定长度的月份集合 */
    /*
     * @param  {time} 时间
     * @param  {len} 长度
     * @param  {direction} 方向：  1: 前几个月;  2: 后几个月;  3:前后几个月  默认 3
     * @return {Array} 数组
     * @example   getMonths('2018-1-29', 6, 1)  // ->  ["2018-1", "2017-12", "2017-11", "2017-10", "2017-9", "2017-8", "2017-7"]
     */
    let mm = new Date(time).getMonth()
    let yy = new Date(time).getFullYear()
    direction = isNaN(direction) ? 3 : direction
    let index = mm
    let cutMonth = function (index) {
      if (index <= len && index >= -len) {
        return direction === 1 ? formatPre(index).concat(cutMonth(++index)) :
          direction === 2 ? formatNext(index).concat(cutMonth(++index)) : formatCurr(index).concat(cutMonth(++index))
      }
      return []
    }
    let formatNext = function (i) {
      let y = Math.floor(i / 12)
      let m = i % 12
      return [yy + y + '-' + (m + 1)]
    }
    let formatPre = function (i) {
      let y = Math.ceil(i / 12)
      let m = i % 12
      m = m === 0 ? 12 : m
      return [yy - y + '-' + (13 - m)]
    }
    let formatCurr = function (i) {
      let y = Math.floor(i / 12)
      let yNext = Math.ceil(i / 12)
      let m = i % 12
      let mNext = m === 0 ? 12 : m
      return [yy - yNext + '-' + (13 - mNext), yy + y + '-' + (m + 1)]
    }
    // 数组去重
    let unique = function (arr) {
      if (Array.hasOwnProperty('from')) {
        return Array.from(new Set(arr))
      } else {
        let n = {}
        let r = []
        for (let i = 0; i < arr.length; i++) {
          if (!n[arr[i]]) {
            n[arr[i]] = true
            r.push(arr[i])
          }
        }
        return r
      }
    }
    return direction !== 3 ? cutMonth(index) : unique(cutMonth(index).sort(function (t1, t2) {
      return new Date(t1).getTime() - new Date(t2).getTime()
    }))
  },
  getDays (time, len, diretion) {
    /* 返回指定长度的天数集合 */
    /**
     * @param  {time} 时间
     * @param  {len} 长度
     * @param  {direction} 方向： 1: 前几天;  2: 后几天;  3:前后几天  默认 3
     * @return {Array} 数组
     *
     * @example date.getDays('2018-1-29', 6) // -> ["2018-1-26", "2018-1-27", "2018-1-28", "2018-1-29", "2018-1-30", "2018-1-31", "2018-2-1"]
     */
    let tt = new Date(time)
    let getDay = function (day) {
      let t = new Date(time)
      t.setDate(t.getDate() + day)
      let m = t.getMonth() + 1
      return t.getFullYear() + '-' + m + '-' + t.getDate()
    }
    let arr = []
    if (diretion === 1) {
      for (let i = 1; i <= len; i++) {
        arr.unshift(getDay(-i))
      }
    } else if (diretion === 2) {
      for (let i = 1; i <= len; i++) {
        arr.push(getDay(i))
      }
    } else {
      for (let i = 1; i <= len; i++) {
        arr.unshift(getDay(-i))
      }
      arr.push(tt.getFullYear() + '-' + (tt.getMonth() + 1) + '-' + tt.getDate())
      for (let i = 1; i <= len; i++) {
        arr.push(getDay(i))
      }
    }
    return diretion === 1 ? arr.concat([tt.getFullYear() + '-' + (tt.getMonth() + 1) + '-' + tt.getDate()]) :
      diretion === 2 ? [tt.getFullYear() + '-' + (tt.getMonth() + 1) + '-' + tt.getDate()].concat(arr) : arr
  },
  formatHMS (s) {
    /* 秒转化为时分秒 */
    /**
     * @param  {s} 秒数
     * @return {String} 字符串
     *
     * @example formatHMS(3610) // -> 1h0m10s
     */
    let str = ''
    if (s > 3600) {
      str = Math.floor(s / 3600) + 'h' + Math.floor(s % 3600 / 60) + 'm' + s % 60 + 's'
    } else if (s > 60) {
      str = Math.floor(s / 60) + 'm' + s % 60 + 's'
    } else {
      str = s % 60 + 's'
    }
    return str
  },
  getweek (date) {
    /* 获取星期几 */
    date = new Date(date)
    return ['一', '二', '三', '四', '五', '六', '日'][date.getDay() - 1] // week 星期
  },
  getquarter (date) {
    /* 获取第几季度 */
    date = new Date(date)
    return ['第一季度', '第二季度', '第三季度', '第四季度'][Math.floor((data.getMonth() + 3) / 3) - 1] // quarter 季度 q
  },
  getMonthOfDay (time) {
    /* 获取某月有多少天 */
    let date = new Date(time)
    let year = date.getFullYear()
    let mouth = date.getMonth() + 1
    let days

    // 当月份为二月时，根据闰年还是非闰年判断天数
    if (mouth === 2) {
      days = (year % 4 === 0 && year % 100 === 0 && year % 400 === 0) || (year % 4 === 0 && year % 100 !== 0) ? 28 : 29
    } else if (mouth === 1 || mouth === 3 || mouth === 5 || mouth === 7 || mouth === 8 || mouth === 10 || mouth === 12) {
      // 月份为：1,3,5,7,8,10,12 时，为大月.则天数为31；
      days = 31
    } else {
      // 其他月份，天数为：30.
      days = 30
    }
    return days
  },
  getYearOfDay (time) {
    /* 获取某年有多少天 */
    let firstDayYear = this.getFirstDayOfYear(time)
    let lastDayYear = this.getLastDayOfYear(time)
    let numSecond = (new Date(lastDayYear).getTime() - new Date(firstDayYear).getTime()) / 1000
    return Math.ceil(numSecond / (24 * 3600))
  },
  getFirstDayOfYear (time) {
    /* 获取某年的第一天 */
    let year = new Date(time).getFullYear()
    return year + '-01-01 00:00:00'
  },
  getLastDayOfYear (time) {
    /* 获取某年最后一天 */
    let year = new Date(time).getFullYear()
    let dateString = year + '-12-01 00:00:00'
    let endDay = this.getMonthOfDay(dateString)
    return year + '-12-' + endDay + ' 23:59:59'
  },
  getDayOfYear (time) {
    /* 获取某个日期是当年中的第几天 */
    return Math.ceil(((new Date(time).getTime() - new Date(this.getFirstDayOfYear(time)).getTime()) / 1000) / (24 * 3600))
  },
  getDayOfYearWeek (time) {
    /* 获取某个日期在这一年的第几周 */
    return Math.ceil(this.getDayOfYear(time) / 7)
  },
  datedifferencedays (sDate1, sDate2) { // 获取两个日期之间的时间间隔 sDate1和sDate2默认是2019-6-8或标准时间格式
    let dateSpan = (new Date(sDate2.replace(/-/g, '/'))) - (new Date(sDate1.replace(/-/g, '/')))
    return Math.floor(Math.abs(dateSpan) / (24 * 3600 * 1000))
  },
  CurentTime () { // 返回"2019-04-18 15:49:04"
    let date = new Date()
    return this.formatDate(date, 'YYYY-MM-DD hh:mm:ss')
    // let now = new Date();
    // let year = now.getFullYear(); //年
    // let month = now.getMonth() + 1; //月
    // let day = now.getDate(); //日
    // let hh = now.getHours(); //时
    // let mm = now.getMinutes(); //分
    // let ss = now.getSeconds(); //秒
    // let clock = year + "-";
    // if (month < 10)
    //     clock += "0";
    // clock += month + "-";
    // if (day < 10)
    //     clock += "0";
    // clock += day + " ";
    // if (hh < 10)
    //     clock += "0";
    // clock += hh + ":";
    // if (mm < 10) clock += '0';
    // clock += mm + ":";
    // if (ss < 10) clock += '0';
    // clock += ss;
    // return (clock);
  },
  getDiffYear (nowValue) {
    // 20140102101010
    let reg1 = /^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)0229)(([0-2][0-3])|([0-1][0-9]))[0-5][0-9][0-5][0-9]$/
    // 2015-05-23T03:00:00.000Z
    let reg2 = /^\d{4}-\d\d-\d\dT\d\d:\d\d:\d\d.\d\d\dZ$/
    if (reg1.test(nowValue) || reg2.test(nowValue)) {
      let year = 0
      let month = 0
      let day = 0
      if (reg1.test(nowValue)) {
        year = nowValue.substring(0, 4)
        month = nowValue.substring(4, 6)
        day = nowValue.substring(6, 8)
      } else {
        year = nowValue.substring(0, 4)
        month = nowValue.substring(5, 7)
        day = nowValue.substring(8, 10)
      }
      let birthDate = new Date(year + '/' + month + '/' + day)
      let returnAge
      let birthYear = birthDate.getYear()
      let birthMonth = birthDate.getMonth() + 1
      let birthDay = birthDate.getDate()
      d = new Date()
      let nowYear = d.getYear()
      let nowMonth = d.getMonth() + 1
      let nowDay = d.getDate()

      if (nowYear === birthYear) {
        returnAge = 0 // 同年 则为0岁
      } else {
        let ageDiff = nowYear - birthYear // 年之差
        if (ageDiff > 0) {
          if (nowMonth === birthMonth) {
            let dayDiff = nowDay - birthDay // 日之差
            if (dayDiff < 0) {
              returnAge = ageDiff - 1
            } else {
              returnAge = ageDiff
            }
          } else {
            let monthDiff = nowMonth - birthMonth // 月之差
            if (monthDiff < 0) {
              returnAge = ageDiff - 1
            } else {
              returnAge = ageDiff
            }
          }
        } else {
          returnAge = -1 // 返回-1 表示出生日期输入错误 晚于今天
        }
      }
      return returnAge // 返回周岁年龄
    } else {
      return ''
    }
  },
  formatRemainTime (endTime) {
    /* 格式化现在距${endTime}（2030-10-5）的剩余时间. {Date} endTime  @return {String} */
    let startDate = new Date() // 开始时间
    let endDate = new Date(endTime) // 结束时间
    let t = endDate.getTime() - startDate.getTime() // 时间差
    let d = 0
    let h = 0
    let m = 0
    let s = 0
    if (t >= 0) {
      d = Math.floor(t / 1000 / 3600 / 24)
      h = Math.floor(t / 1000 / 60 / 60 % 24)
      m = Math.floor(t / 1000 / 60 % 60)
      s = Math.floor(t / 1000 % 60)
    }
    return (function (dd) {
      return Math.round(dd * 100) / 100
    })(d) + '天 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(h) + '小时 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(m) + '分钟 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(s) + '秒'
  },
  formatFrontTime(startTime) {
    /* 格式化startTime到现在的逝去时间. {Date} startTime  @return {String} */
    let startDate = new Date(startTime) // 开始时间
    let endDate = new Date() // 结束时间
    let t = endDate.getTime() - startDate.getTime() // 时间差
    let d = 0
    let h = 0
    let m = 0
    let s = 0
    if (t >= 0) {
      d = Math.floor(t / 1000 / 3600 / 24)
      h = Math.floor(t / 1000 / 60 / 60 % 24)
      m = Math.floor(t / 1000 / 60 % 60)
      s = Math.floor(t / 1000 % 60)
    }
    return (function (dd) {
      return Math.round(dd * 100) / 100
    })(d) + '天 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(h) + '小时 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(m) + '分钟 ' + (function (dd) {
      return Math.round(dd * 100) / 100
    })(s) + '秒'
  }
}