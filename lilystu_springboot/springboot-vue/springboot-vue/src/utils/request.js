// 导入 axios
import axios from 'axios'

// 通过 axios 创建对象
const request = axios.create({
    timeout: 5000
})

//request 拦截器
// 1. 可以对发出的请求做一些处理
// 2. 比如统一加 token，Content-Type 等
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
});
//导出
export default request