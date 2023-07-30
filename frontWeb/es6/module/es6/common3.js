
//演示默认导出
export default {
    sub (a, b) {
        return parseInt(a) - parseInt(b)
    },
    sum (a, b) {
        return parseInt(a) + parseInt(b)
    }
}

let name = "hsp教育"

const PI = 3.14;

//定义对象时直接导出
export const monster = {
    name:"牛魔王",
    age:500,
    hi(){
        console.log("hi~")
    }
}



