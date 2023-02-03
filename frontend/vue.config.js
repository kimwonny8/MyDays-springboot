module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9959',
        changeOrigin: true,
      }
    }
  },
  lintOnSave: false
};
