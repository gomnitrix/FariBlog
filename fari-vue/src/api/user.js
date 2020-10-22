import request from '@/utils/request'

export function login (params) {
  var loginURL = process.env.VUE_APP_SERVER_API + '/fariblog/login'
  console.log('env: ' + process.env.NODE_ENV)
  console.log('WEB_API: ' + process.env.VUE_APP_SERVER_API)
  console.log('login url: ' + loginURL)
  return request({
    url: loginURL,
    method: 'post',
    params
  })
}

export function register (params) {
  var registerURL = process.env.VUE_APP_SERVER_API + '/fariAuth/oauth/register'
  console.log('env: ' + process.env.NODE_ENV)
  console.log('WEB_API: ' + process.env.VUE_APP_SERVER_API)
  console.log('register url: ' + registerURL)
  return request({
    url: registerURL,
    method: 'post',
    data: params
  })
}
