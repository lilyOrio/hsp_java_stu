const sum = function (a, b) {
    return parseInt(a) + parseInt(b)
}

const sub = function (a, b) {
    return parseInt(a) - parseInt(b)
}

let name = "hsp教育"

const PI = 3.14;

const monster = {
    name:"牛魔王",
    age:500,
    hi(){
        console.log("hi~")
    }
}

//导出,可以全部导出，也可以部分导出
export {
    sum,
    sub,
    name
}

