'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',

  FARI_VUE_WEB: '"http://localhost:8080"',
  PICTURE_API: '"http://localhost:8602"',
  WEB_API: '"http://58.60.1.94:6002"',
  ELASTICSEARCH: '"http://localhost:8605"',

})
