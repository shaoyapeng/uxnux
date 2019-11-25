<template>
  <div id="raphael" class="raphael" :style="{'width': width, 'height': height}"></div>
</template>

<script>
import Raphael from 'raphael'
import _ from 'lodash'
export default {
  name: 'raphael',
  props: {
    width: {
      type: String,
      default () {
        return '100%'
      }
    },
    height: {
      type: String,
      default () {
        return '100%'
      }
    },
    ellipseOptionList: {
      type: Array,
      default () {
        return []
      }
    }
  },
  data () {
    return {
      ellipseList: [],
      lineList: [],
      raphael: {},
      alongIndex: 0,
      defaultOption: {
        x: 10, // 横坐标
        y: 10, // 纵坐标
        xr: 10, // 横向半径
        yr: 10, // 纵向半径
        text: '圆形', // 文本，位置在圆的下面
        fill: '#fcf', // 填充颜色
        stroke: '#ffc', // 边框颜色
        fillLiner: '#fcf', //
        strokeLiner: '#ffc',
        textColor: '#cff', // 文本颜色
        fontSize: 12 // 文本大小
      }
    }
  },
  methods: {
    /**
     * 画圆
     * @param  {[type]} option [description]
     * @return {[type]}        [description]
     */
    drawEllipsePoint (option) {
      let self = this
      let ellipseOption = self.getOption(option)
      // 创建大椭圆/圆 x、y是坐标、xr、yr是左右半径
      let ellipseLarge = self.raphael.ellipse(ellipseOption.x, ellipseOption.y, ellipseOption.xr + 5, ellipseOption.yr + 5)
      // 创建默认大小椭圆/圆 x、y是坐标、xr、yr是左右半径
      let ellipseMiddle = self.raphael.ellipse(ellipseOption.x, ellipseOption.y, ellipseOption.xr, ellipseOption.yr)
      // 给默认大小椭圆添加填充色
      ellipseMiddle.attr('fill', ellipseOption.fill)
      // 添加样式 鼠标手型
      ellipseMiddle.attr('cursor', 'pointer')
      // 边框
      ellipseMiddle.attr('stroke', ellipseOption.stroke)
      // 画文字
      let ellipseText = self.raphael.text(ellipseOption.x, ellipseOption.y + ellipseOption.yr + 20, ellipseOption.text)
      ellipseText.attr({
        font: ellipseOption.fontSize + 'px Fontin-Sans, Arial',
        fill: ellipseOption.textColor
      })
      // 大椭圆边框
      ellipseLarge.attr('stroke', ellipseOption.stroke)
      // 添加图片
      let image = self.raphael.image(ellipseOption.imgSrc, ellipseOption.x - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4), ellipseOption.y - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4), Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2, Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2)
      image.attr('cursor', 'pointer')

      // 鼠标移入
      ellipseMiddle.mouseover(function () {
        this.attr('fill', ellipseOption.fillLiner)
        this.animate({
          'rx': ellipseOption.xr + 5,
          'ry': ellipseOption.yr + 5
        }, 1000, 'elastic')
        image.animate({
          'width': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2 + 5,
          'height': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2 + 5,
          'x': ellipseOption.x - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) - 2.5,
          'y': ellipseOption.y - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) - 2.5
        }, 1000, 'elastic')
        ellipseLarge.animate({
          'rx': ellipseOption.xr + 10,
          'ry': ellipseOption.yr + 10
        }, 1000, 'elastic')
        self.$emit('mouseover', ellipseOption)
      })
      ellipseMiddle.click(function () {
        self.$emit('click', ellipseOption)
      })
      ellipseMiddle.mouseout(function () {
        this.attr('fill', ellipseOption.fill)
        this.animate({
          'rx': ellipseOption.xr,
          'ry': ellipseOption.yr
        }, 1000, 'elastic')
        image.animate({
          'width': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2,
          'height': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2,
          'x': ellipseOption.x - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4),
          'y': ellipseOption.y - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4)
        }, 1000, 'elastic')
        ellipseLarge.animate({
          'rx': ellipseOption.xr + 5,
          'ry': ellipseOption.yr + 5
        }, 1000, 'elastic')
        self.$emit('mouseout', ellipseOption)
      })
      image.mouseover(function () {
        ellipseMiddle.attr('fill', ellipseOption.fillLiner)
        ellipseMiddle.animate({
          'rx': ellipseOption.xr + 5,
          'ry': ellipseOption.yr + 5
        }, 1000, 'elastic')
        image.animate({
          'width': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2 + 5,
          'height': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2 + 5,
          'x': ellipseOption.x - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) - 2.5,
          'y': ellipseOption.y - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) - 2.5
        }, 1000, 'elastic')
        ellipseLarge.animate({
          'rx': ellipseOption.xr + 10,
          'ry': ellipseOption.yr + 10
        }, 1000, 'elastic')
        self.$emit('mouseover', ellipseOption)
      })
      image.mouseout(function () {
        ellipseMiddle.attr('fill', ellipseOption.fill)
        ellipseMiddle.animate({
          'rx': ellipseOption.xr,
          'ry': ellipseOption.yr
        }, 1000, 'elastic')
        image.animate({
          'width': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2,
          'height': Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4) * 2,
          'x': ellipseOption.x - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4),
          'y': ellipseOption.y - Math.sqrt(ellipseOption.xr * ellipseOption.xr / 4)
        }, 1000, 'elastic')
        ellipseLarge.animate({
          'rx': ellipseOption.xr + 5,
          'ry': ellipseOption.yr + 5
        }, 1000, 'elastic')
        self.$emit('mouseout', ellipseOption)
      })
      image.click(function () {
        self.$emit('click', ellipseOption)
      })
      self.ellipseList.push({
        id: ellipseOption.id,
        ellipseLarge: ellipseLarge,
        ellipseMiddle: ellipseMiddle
      })
    },
    /**
     *
     * @param  {[type]} option [description]
     * @return {[type]}        [description]
     */
    drawLinesConnection (option) {
      let self = this
      let fromPoint = option.fromPoint
      let toPoint = option.toPoint
      let lineColor = option.lineColor
      let fromBox = fromPoint.getBBox()
      let toBox = toPoint.getBBox()
      let pathDataList = []
      pathDataList.push({
        x: fromBox.x + fromBox.width / 2,
        y: fromBox.y - 1
      })
      pathDataList.push({
        x: fromBox.x + fromBox.width / 2,
        y: fromBox.y + fromBox.height + 1
      })
      pathDataList.push({
        x: fromBox.x - 1,
        y: fromBox.y + fromBox.height / 2
      })
      pathDataList.push({
        x: fromBox.x + fromBox.width + 1,
        y: fromBox.y + fromBox.height / 2
      })
      pathDataList.push({
        x: toBox.x + toBox.width / 2,
        y: toBox.y - 1
      })
      pathDataList.push({
        x: toBox.x + toBox.width / 2,
        y: toBox.y + toBox.height + 1
      })
      pathDataList.push({
        x: toBox.x - 1,
        y: toBox.y + toBox.height / 2
      })
      pathDataList.push({
        x: toBox.x + toBox.width + 1,
        y: toBox.y + toBox.height / 2
      })
      let d = []
      let dis = []
      let dx = 0
      let dy = 0
      for (var i = 0; i < 4; i++) {
        for (var j = 4; j < 8; j++) {
          // 取两个坐标点的横向距离和纵向距离
          dx = Math.abs(pathDataList[i].x - pathDataList[j].x)
          dy = Math.abs(pathDataList[i].y - pathDataList[j].y)
          if (((i === j - 4) || ((i !== 3 && j !== 6) || pathDataList[i].x < pathDataList[j].x)) && ((i !== 2 && j !== 7) || pathDataList[i].x > pathDataList[j].x) && ((i !== 0 && j !== 5) || pathDataList[i].y > pathDataList[j].y) && ((i !== 1 && j !== 4) || pathDataList[i].y < pathDataList[j].y)) {
            dis.push(dx + dy)
            d[dis[dis.length - 1]] = [i, j]
          }
        }
      }
      let res = []
      if (dis.length === 0) {
        res = [0, 4]
      } else {
        res = d[Math.min.apply(Math, dis)]
      }
      let x1 = pathDataList[res[0]].x
      let y1 = pathDataList[res[0]].y
      let x4 = pathDataList[res[1]].x
      let y4 = pathDataList[res[1]].y
      dx = Math.max(Math.abs(x1 - x4) / 2, 10)
      dy = Math.max(Math.abs(y1 - y4) / 2, 10)
      let x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3)
      let y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3)
      let x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3)
      let y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3)
      let path = ['M', x1.toFixed(3), y1.toFixed(3), 'C', x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(',')
      let color = typeof lineColor === 'string' ? lineColor : '#000'
      let lineObj = {
        bg: self.raphael.path(path).attr({
          stroke: color,
          fill: 'none',
          'stroke-width': 3
        }),
        line: self.raphael.path(path).attr({
          stroke: color,
          fill: 'none'
        }),
        from: fromPoint,
        to: toPoint
      }
      self.lineList.push(lineObj)
    },
    along (v) {
      let self = this
      let len = self.lineList[self.alongIndex].line.getTotalLength()
      let point = self.lineList[self.alongIndex].line.getPointAtLength(v * len)
      return {
        transform: 't' + [point.x - 5, point.y] + 'r' + point.alpha
      }
    },
    getMovePoint (path) {
      let self = this
      return self.raphael.path(path).attr({
        'arrow-end': 'block-wide-long',
        'stroke': '#fff',
        'stroke-width': 2
      })
    },
    run (index, option) {
      let self = this
      self.alongIndex = index
      self.raphael.customAttributes['along' + index] = self.along
      let movePoint = self.getMovePoint(option.path)
      let alongObj = {}
      alongObj['along' + index] = 0
      movePoint.attr(alongObj)
      alongObj['along' + index] = 1
      movePoint.animate(alongObj, 1000, function () {
        alongObj['along' + index] = 0
        movePoint.attr(alongObj)
        self.run(index, option)
      })
    },
    getOption (option) {
      return _.defaults(option, this.defaultOption)
    },
    init () {
      let self = this
      self.raphael = new Raphael('raphael', self.width, self.height)
      self.ellipseOptionList.forEach((option, index) => {
        self.drawEllipsePoint(option)
      })
      let runIndex = 0
      self.ellipseOptionList.forEach((option, index) => {
        option.lineList.forEach(item => {
          self.drawLinesConnection({
            fromPoint: self.ellipseList[option.id].ellipseLarge,
            toPoint: self.ellipseList[item.index].ellipseLarge,
            lineColor: option.lineColor
          })
          self.run(runIndex, item)
          runIndex++
        })
      })
    }
  },
  mounted () {
    this.init()
  }
}
</script>

<style lang="scss">
.raphael {
  display: block;
}
</style>
