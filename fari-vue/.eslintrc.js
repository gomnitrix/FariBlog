module.exports = {
  "extends": [
    "standard",
    "plugin:vue/recommended"
  ],
  "env": {
    "browser": true,
    "commonjs": true,
    "es6": true
  },
  "parser": "vue-eslint-parser",// 使用babel进行编译,解决import报错：error  Parsing error: Unexpected token import
  "plugins": [
    "html"
  ],//插件，此插件用于识别文件中的js代码，没有MIME类型标识没有script标签也可以识别到，因此拿来识别.vue文件中的js代码
  "parserOptions": {
    "sourceType": "module",
    "ecmaVersion": 8
  }
}