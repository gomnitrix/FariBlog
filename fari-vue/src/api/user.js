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
