import request from '@/utils/request'

export function getBlogsInfo (pageSize, pageIndex) {
  var infoUrl = process.env.VUE_APP_SERVER_API + `/fariWeb/index/blogsInfo/${pageSize}/${pageIndex}`
  return request({
    url: infoUrl,
    method: 'get'
  })
}

export function getPagesNum (pageSize) {
  var remoteUrl = process.env.VUE_APP_SERVER_API + `/fariWeb/index/pageNum/${pageSize}`
  return request({
    url: remoteUrl,
    method: 'get'
  })
}
