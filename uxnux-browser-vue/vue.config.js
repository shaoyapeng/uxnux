module.exports = {
  publicPath: process.env.NODE_ENV === 'production'
  ? '/'
  : '/',
  outputDir: 'dist', // 打包后的路径
  assetsDir: '', // 放置生成的静态资源
  indexPath: 'index.html', // index.html 的路径
  lintOnSave: process.env.NODE_ENV !== 'production', // 保存的时候使用lint
  productionSourceMap: false, //
  devServer: {
    proxy: 'http://127.0.0.1:8088',
    overlay: {
      warnings: true,
      errors: true
    }
  }
}