<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="8">
        <el-card shadow="never">
          <div v-for="(item, index) in messages" :key="index">
            {{ item }}
          </div>
        </el-card>
        <el-row :gutter="12">
          <el-col :span="8">
            <el-input v-model="msg" placeholder="请输入内容"></el-input>
          </el-col>
          <el-col :span="8">
            <el-button @click="initWebSocket">建立链接</el-button>
          </el-col>
          <el-col :span="8">
            <el-button @click="sendMessage">发送</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  data  () {
    return {
      socket: null,
      messages: [],
      msg: ''
    }
  },
  methods: {
    initWebSocket () {
      if (typeof (WebSocket) === 'undefined') {
        this.$message.error('您的浏览器不支持WebSocket')
      } else {
        const wsuri = 'ws://127.0.0.1:8888/imserver/123456'
        this.socket = new WebSocket(wsuri)
        console.log(this.socket)
        this.socket.onopen = this.onopen
        this.socket.onmessage = this.onmessage
        this.socket.onerror = this.onerror
        this.socket.onclose = this.onclose
      }
    },
    onopen () {
    },
    onmessage (e) {
      this.messages.push(e.data)
    },
    onerror () {},
    onclose () {},
    send (Data) {
      this.socket.send(Data)
    },
    sendMessage () {
      this.send(this.msg)
    }
  }

}
</script>
