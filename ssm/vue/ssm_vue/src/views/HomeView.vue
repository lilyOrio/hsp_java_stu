<template>
    <div class="home">
        <!--    <img alt="Vue logo" src="../assets/logo.png">-->
        <!--
            在HomeView.vue引入HelloWorld组件
            这里会显示HelloWorld组件的内容
            msg="Welcome to Your Vue.js App"表示给组件HelloWorld设置了一个组件之
        -->
        <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->

        <div style="margin: 10px 0">
            <el-button type="primary" @click="add">新增</el-button>
            <el-button>其它</el-button>
        </div>

        <!-- 搜索-->
        <div style="margin: 10px 0">
            <el-input v-model="search" placeholder=" 请 输 入 关 键 字 " style="width: 20%"></el-input>
            <el-button style="margin-left: 10px" type="primary" @click="list">查询</el-button>
        </div>

        <el-table :data="tableData" stripe style="width: 90%">
            <el-table-column prop="id" label="ID" sortable></el-table-column>
            <el-table-column prop="name" label="家居名"></el-table-column>
            <el-table-column prop="maker" label="厂家"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column prop="sales" label="销量"></el-table-column>
            <el-table-column prop="stock" label="库存"></el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <!--handleEdit(scope.row)可以将当前行数据传递给handleEdit-->
                <template #default="scope">
                    <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
                    <!-- 增加popcomfirm 控件，确认删除-->
                    <el-popconfirm
                            title="确定删除吗？" @confirm="handleDel(scope.row.id)">
                        <template #reference>
                            <el-button size="small" type="danger">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>

        </el-table>

        <!-- 添加家居的弹窗
        说明:
        1. el-dialog ：v-model="dialogVisible" 表示对话框, 和dialogVisible 变量双向绑定,控制是否显示对话框
        2. el-form :model="form" 表示表单,数据和form 数据变量双向绑定
        3. el-input v-model="form.name" 表示表单的input 空间，名字为name 需要和后台Javabean 属性名一致
        4.前端中，对象的属性可以动态添加的
        -->
        <el-dialog title="提示" v-model="dialogVisible" width="30%">
            <el-form :model="form" label-width="120px">
                <el-form-item label="家居名">
                    <el-input v-model="form.name" style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item label="厂商">
                    <el-input v-model="form.maker" style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item label="价格">
                    <el-input v-model="form.price" style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item label="销量">
                    <el-input v-model="form.sales" style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item label="库存">
                    <el-input v-model="form.stock" style="width: 80%"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="save">确定</el-button>
                </span>
            </template>
        </el-dialog>
        <!--//增加element-plus 分页控件-->
        <div style="margin: 10px 0">
            <el-pagination
                    @size-change="handlePageSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5,10,15]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: 'HomeView',
        components: {},
        data() {
            return {
                //当前页
                currentPage: 1,
                //每页几条数据
                pageSize: 5,
                //总共多少条数据
                total: 10,
                search: '',
                dialogVisible: false,
                form: {},
                tableData: []
            }
        },
        created(){
            this.list()
        },
        methods: {
            handleEdit(row) {//回显数据
                console.log("row=",row);
                //方法一
                // this.form = JSON.parse(JSON.stringify(row));//将row转换成json字符串后再转成json对象
                // this.dialogVisible = true;
                //方法二
                request.get("/api/find/"+ row.id).then(res => {
                    //绑定tableData, 显示在表格
                    console.log("res=",res)
                    // this.tableData = res.data.extend.furnsList
                    //已经对返回值res做过处理了，返回的是res.data
                    this.form = res.extend.furn
                })

            },
            save() {//修改（数据回显）和添加（数据清空）
                console.log("this.form.id",this.form.id);
                if (this.form.id){//修改
                    request.put("/api/update", this.form).then(res => {
                        console.log("res",res)
                        if (res.code === 200){
                            this.$message({ //弹出更新成功的消息框
                                type: "success",
                                message: "更新成功"
                            })
                        }else {
                            this.$message({//弹出更新失败信息
                                type: "error",
                                message: res.msg
                            })
                        }
                        this.dialogVisible = false
                        //添加完家具再刷新显示
                        this.list()//必须放在ajax请求回调函数内，因为ajax请求是异步函数函数未执行完也会继续下面的指令
                    })
                }else{
                    //将添加的数据发送到后端
                    request.post("/api/save", this.form).then(res => {
                        console.log("res",res)
                        this.dialogVisible = false
                        //添加完家具再刷新显示
                        this.list()
                    })
                }
            },
            add() {
                //显示对话框
                this.dialogVisible = true;
                //清空表单
                this.form = {};
            },
            list(){
                // request.get("/api/furns").then(res => {
                //     //绑定tableData, 显示在表格
                //     console.log("res=",res)
                //     // this.tableData = res.data.extend.furnsList
                //     //已经对返回值res做过处理了，返回的是res.data
                //     this.tableData = res.extend.furnsList
                // })

                //请求分页
                request.get("/api/furnsByConditionPage",{
                    params:{//携带参数
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    //绑定tableData, 显示在表格
                    this.tableData = res.extend.pageInfo.list//拿到的显示数据
                    this.total = res.extend.pageInfo.total
                })
            },
            handleDel(id){
                console.log("id=",id);
                request.delete("/api/del/" + id).then(res => {
                    if (res.code === 200) {
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.list() // 刷新列表
                })
            },
            handlePageSizeChange(pageSize){//处理分页请求
                this.pageSize = pageSize
                this.list()
            },
            //处理当前页变化, 比如点击分页连接,或者go to 第几页
            handleCurrentChange(pageNum) {
                this.currentPage = pageNum
                this.list()
            }
        }
    }
</script>
