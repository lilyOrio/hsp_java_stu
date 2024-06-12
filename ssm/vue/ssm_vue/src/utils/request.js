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

//response拦截器，可以在调用接口的响应后，统一处理返回结果
request.interceptors.response.use(
    response => {
        let res = response.data; //取出返回data 的内容

        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }

        // 如果返回的是string ,就转成json 对象
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },error=>{
        console.log('err' + error) // 输出错误信息
        return Promise.reject(error)
    }
)

//导出
export default request

