// 引入axios 包
import axios from 'axios'

// 通过axios 创建对象 用于发送请求到后端
const request = axios.create({
    timeout: 5000
})

// request 拦截器
// 1. 可以对请求做一些处理
// 2. 比如统一加token，Content-Type 等
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
});

//导出
export default request

