// eslint-disable-next-line @typescript-eslint/no-var-requires
const fs = require("fs");

module.exports = {
  publicPath: process.env.NODE_ENV === 'production'
      ? '/' + process.env.CI_PROJECT_NAME + '/'
      : '/',
  lintOnSave: false,
  pluginOptions: {
    electronBuilder: {
      nodeIntegration: true
    }
  }
  // devServer: {
  //   https: {
  //     key: fs.readFileSync('./certs/example.com+5-key.pem'),
  //     cert: fs.readFileSync('./certs/example.com+5.pem'),
  //   },
  //   public: 'https://localhost:8081/'
  // }
};
