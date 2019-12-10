import axios from 'axios'
// 引用默认的变量或者方法不需要用 {}
import { baseUrl } from '../api/url'

/** 添加默认axios配置 */
/**
 * 指定请求超时的毫秒数(0 表示无超时时间)
 * 如果请求话费了超过 timeout 的时间，请求将被中断
 */
axios.defaults.timeout = 60000
/**
 * 设置请求的路径，将自动加在 url 前面，除非 url 是一个绝对 URL
 */
axios.defaults.baseURL = baseUrl
/**
 * 设置Content-Type 为json 格式
 */
axios.defaults.headers.post['Content-Type'] = 'application/json'
/**
 * request 请求拦截
 */
axios.interceptors.request.use(function (config) {
  // JSON.stringify() 方法用于将 JavaScript 值转换为 JSON 字符串
  config.data = JSON.stringify(config.data)
  // 后台生成token，可以存到vuex，也可以存 localStorage
  let token = window.localStorage.getItem('token')
  // token 的前缀
  let tokenHead = window.localStorage.getItem('tokenHead')
  if (token) {
    config.headers.Authorization = tokenHead + ' ' + token
  }
  return config
}, function (error) {
  // 请求出错
  return Promise.reject(error)
})

/**
 * response 拦截器
 */
axios.interceptors.response.use(function (response) {
  /**
   * 根据后台响应定义，根据响应的数据对响应进行处理，
   * 例如后台定义统一错误码： 0001： 系统错误，那么可以直接在进行处理
   */
  if (response.data) {
    console.log(response.data)
  }
  return response
}, function (error) {
  // 错误的响应处理
  return Promise.reject(error)
})

/**
 * post请求方法
 * @param { 请求baseUrl后面要跟的请求路径 } url
 * @param { 请求的数据 } data
 */
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(error => {
        // 服务器响应的状态码不在 2xx 范围内
        reject(error)
      })
  })
}

/**
 * get请求方法
 * @param { 请求baseUrl后面要跟的请求路径 } url
 * @param { 请求的数据 } data
 */
export function get (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(error => {
        // 服务器响应的状态码不在 2xx 范围内
        reject(error)
      })
  })
}
