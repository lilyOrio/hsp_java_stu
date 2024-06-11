const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
module.exports = {
    devServer: {
        port: 10000, // 启动端口
        proxy: { //设置代理，必须填
            '/api': { //设置拦截器拦截器格式斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:8080/ssm', // 代理的目标地址, 就是/api 代替http://localhost:10001/
                changeOrigin: true, //是否设置同源，输入是的
                pathRewrite: { //路径重写
                    '/api': '' //选择忽略拦截器里面的单词
                }
            }

        }
    }

}
