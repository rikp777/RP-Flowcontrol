// eslint-disable-next-line @typescript-eslint/no-var-requires
const fs = require("fs");

module.exports = {
  lintOnSave: false,
  devServer: {
    https: {
      key: fs.readFileSync('./certs/example.com+5-key.pem'),
      cert: fs.readFileSync('./certs/example.com+5.pem'),
    },
    public: 'https://localhost:8081/'
  }
};
