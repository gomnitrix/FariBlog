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

export function postBlog (params) {
  var postUrl = process.env.VUE_APP_SERVER_API + '/fariWeb/index/blog'
  console.log('env: ' + process.env.NODE_ENV)
  console.log('WEB_API: ' + process.env.VUE_APP_SERVER_API)
  console.log('post blog url: ' + postUrl)
  return request({
    url: postUrl,
    method: 'post',
    data: params
  })
}

export function getBlog (blogId) {
  var blogUrl = process.env.VUE_APP_SERVER_API + `/fariWeb/index/article/${blogId}`
  return request({
    url: blogUrl,
    method: 'get'
  })
}
