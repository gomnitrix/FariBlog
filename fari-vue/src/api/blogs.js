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
  return request({
    url: postUrl,
    method: 'put',
    data: params
  })
}

export function updateBlog (params) {
  var updateUrl = process.env.VUE_APP_SERVER_API + '/fariWeb/index/blog/'
  return request({
    url: updateUrl,
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

export function deleteBlog (blogId) {
  var deleteBlogUrl = process.env.VUE_APP_SERVER_API + `/fariWeb/index/blog/${blogId}`
  return request({
    url: deleteBlogUrl,
    method: 'delete'
  })
}

export function getQiniuToken () {
  var tokenUrl = process.env.VUE_APP_SERVER_API + '/fariWeb/oss/qiniu/upload/token'
  return request({
    url: tokenUrl,
    method: 'get'
  })
}
