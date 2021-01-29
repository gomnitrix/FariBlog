import request from '@/utils/request'

export function login (params) {
  var loginURL = process.env.VUE_APP_SERVER_API + '/fariblog/login'
  return request({
    url: loginURL,
    method: 'post',
    params
  })
}

export function register (params) {
  var registerURL = process.env.VUE_AUTH_SERVER_API + '/oauth/register'
  return request({
    url: registerURL,
    method: 'post',
    data: params
  })
}

export function update (params) {
  var updateURL = process.env.VUE_WEB_SERVER_API + '/user'
  return request({
    url: updateURL,
    method: 'post',
    data: params
  })
}
