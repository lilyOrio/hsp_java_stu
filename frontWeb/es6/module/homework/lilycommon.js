let cat = {
    name:"咪咪"
}

let dog = {
    name:"小泥鳅",
    hi(){

    }
}

// export {
//     cat,
//     dog
// }

//默认导出
// export default {
//     cat: {
//         name: "咪咪"
//     },
//     dog: {
//         name: "小泥鳅",
//         hi() {
//
//         }
//     }
// }
export default {
    cat: cat,
    dog: dog
}