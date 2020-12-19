/**
 * CookieUtil常用的一些工具类
 */

export function setCookie (name, value) {
  document.cookie = name + '=' + value + ';'
}

export function getCookie (name) {
  var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
  var arr = document.cookie.match(reg)
  if (arr != null) {
    console.log(arr)
    return unescape(arr[2])
  } else {
    return null
  }
}
