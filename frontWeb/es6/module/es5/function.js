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

//导出
//可以理解成以对象的形式导出
// module.exports = {
//     sum:sum,
//     sub:sub,
//     myname:name
// }
//简写
exports = {
    sum,
    sub,
    name,
    monster
}