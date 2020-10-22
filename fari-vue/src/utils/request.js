/* eslint-disable prefer-promise-reject-errors */
import axios from 'axios'
import router from '@/router/index'
import { getCookie } from '@/utils/cookieUtils'

// 创建axios实例
const service = axios.create({
  baseURL: '', // api 的 base_url
  timeout: 10000 // 请求超时时间 10秒
})

service.defaults.headers.common.Authorization = getCookie('access_token')

// request拦截器
service.interceptors.request.use(
  config => {
    var token = getCookie('access_token')
    if (token != null) {
      config.headers.Authorization = token // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    // return response.data
    const res = response.data
    console.log('response data: \n')
    console.log(res)
    if (res.code === 401 || res.code === 400) {
      console.log('返回错误内容', res)
      router.push('404')
      return res
    } else if (res.code === 500) {
      router.push('500')
      return Promise.reject('error')
    } else if (res.code === 502) {
      router.push('502')
      return Promise.reject('error')
    } else {
      return res
    }
    // else {
    //   return Promise.reject('error')
    // }
  },
  error => {
    // 出现网络超时
    router.push('500')
    return Promise.reject(error)
  }
)

export default service
