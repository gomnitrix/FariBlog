import request from '@/utils/request'

export function getBlogsInfo (pageSize, pageIndex) {
  var infoUrl = process.env.VUE_WEB_SERVER_API + `/index/blogsInfo/${pageSize}/${pageIndex}`
  return request({
    url: infoUrl,
    method: 'get'
  })
}

export function getPagesNum (pageSize) {
  var remoteUrl = process.env.VUE_WEB_SERVER_API + `/index/pageNum/${pageSize}`
  return request({
    url: remoteUrl,
    method: 'get'
  })
}

export function postBlog (params) {
  var postUrl = process.env.VUE_WEB_SERVER_API + '/index/blog'
  return request({
    url: postUrl,
    method: 'put',
    data: params
  })
}

export function updateBlog (params) {
  var updateUrl = process.env.VUE_WEB_SERVER_API + '/index/blog/'
  return request({
    url: updateUrl,
    method: 'post',
    data: params
  })
}

export function getBlog (blogId) {
  var blogUrl = process.env.VUE_WEB_SERVER_API + `/index/article/${blogId}`
  return request({
    url: blogUrl,
    method: 'get'
  })
}

export function deleteBlog (blogId) {
  var deleteBlogUrl = process.env.VUE_WEB_SERVER_API + `/index/blog/${blogId}`
  return request({
    url: deleteBlogUrl,
    method: 'delete'
  })
}

export function getQiniuToken () {
  var tokenUrl = process.env.VUE_WEB_SERVER_API + '/oss/qiniu/upload/token'
  return request({
    url: tokenUrl,
    method: 'get'
  })
}
