// module.exports = {
//     // 基本路径
//     baseUrl: '/',
//     // 输出文件目录
//     outputDir: 'dist',
//     // eslint-loader 是否在保存的时候检查
//     lintOnSave: true,
//     // webpack配置
//     chainWebpack: () => { },
//     configureWebpack: () => { },
//     // 生产环境是否生成 sourceMap 文件
//     productionSourceMap: true,
//     // css相关配置
//     css: {
//         // 是否使用css分离插件 ExtractTextPlugin
//         extract: true,
//         // 开启 CSS source maps?
//         sourceMap: false,
//         // css预设器配置项
//         loaderOptions: {},
//         // 启用 CSS modules for all css / pre-processor files.
//         modules: false
//     },
//     parallel: require('os').cpus().length > 1,
//     pwa: {},
//     // webpack-dev-server 相关配置
//     devServer: {
//         open: true,                                 //配置自动启动浏览器
//         host: '0.0.0.0',
//         port: 3000,                                 // 端口号
//         https: false,
//         hotOnly: false,                             // https:{type:Boolean}
//         proxy: 'http://localhost:18888',                                           // 配置跨域处理,只有一个代理
//         before: app => { }
//     },
//     // 第三方插件配置
//     pluginOptions: {}
// }