import request from '@/utils/request'

export function login (params) {
  return request({
    url: process.env.WEB_API + '/fariGateway/login',
    method: 'post',
    params
  })
}
