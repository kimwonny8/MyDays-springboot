module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://3.35.236.95:9959',
        changeOrigin: true,
      }
    }
  },
  lintOnSave: false
};
