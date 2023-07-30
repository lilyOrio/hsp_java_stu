//全部导入
const m = require("./function.js")
//部分导入
const {sub,monster} = require("./function.js")//相当于对象结构

//只要idea可以识别到变量就说明导入是成功的
// console.log(m.myname)
console.log(m.name)
console.log(m.sum("100","200"))
console.log(monster.name)
console.log(sub(100,20))

