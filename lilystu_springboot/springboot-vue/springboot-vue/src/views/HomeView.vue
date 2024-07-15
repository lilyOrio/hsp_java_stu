<template>
    <div>
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add">新增</el-button>
            <el-button>其它</el-button>
        </div>
        <!-- 搜索-->
        <div style="margin: 10px 0">
            <el-input v-model="search" placeholder=" 请 输 入 关 键 字 " style="width: 30%"></el-input>
            <el-button style="margin-left: 10px" type="primary" @click="list">查询</el-button>
        </div>
        <!-- <img alt="Vue logo" src="../assets/logo.png">--> <!-- <HelloWorld msg="Welcome to Your Vue.js App"/>-->
        <!-- <el-button>我的按钮</el-button> --> <!-- 去掉字段的 width, 让其自适应 -->
        <el-table :data="tableData" stripe style="width: 90%">
            <el-table-column
                    prop="id" label="ID" sortable></el-table-column>
            <el-table-column prop="name" label="家居名"></el-table-column>
            <el-table-column prop="maker" label="厂家"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column prop="sales"
                             label="销量"></el-table-column>
            <el-table-column prop="stock" label="库存"></el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <template #default="scope">
                    <el-button @click="handleEdit2(scope.row)" type="text">编辑</el-button>
                    <!--                    <el-button type="text">删除</el-button>-->
                    <!-- 增加 popcomfirm 控件，确认删除 -->
                    <el-popconfirm title="确定删除吗？" @confirm="handleDel(scope.row.id)">
                        <template #reference>
                            <el-button type="text">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin: 10px 0">
            <el-pagination @size-change="handlePageSizeChange" @current-change="handleCurrentChange"
                           :current-page="currentPage" :page-sizes="[5,10]" :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
        </div>
        <!-- 添加家居的弹窗
 说明: 1. el-dialog ：v-model="dialogVisible" 表示对话框, 和 dialogVisible 变量双向 绑定,控制是否显示对话框
      2. el-form :model="form" 表示表单数据和 form 数据变量双向绑定 3. el-input v-model="form.name"
        表示表单的 input 控件，名字为 name 需要和 后台 Javabean 属性一致 -->
        <el-dialog title="提示" v-model="dialogVisible" width="30%">
            <el-form :model="form" :rules="rules" res="form" label-width="120px">
                <el-form-item label="家居名" prop="name">
                    <el-input v-model="form.name" style="width: 50%"></el-input>
                    {{ serverValidErrors.name }}
                </el-form-item>
                <el-form-item label="厂商" prop="maker">
                    <el-input v-model="form.maker" style="width: 50%"></el-input>
                    {{ serverValidErrors.maker }}
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input v-model="form.price" style="width: 50%"></el-input>
                    {{ serverValidErrors.price }}
                </el-form-item>
                <el-form-item label="销量" prop="sales">
                    <el-input v-model="form.sales" style="width: 50%"></el-input>
                    {{ serverValidErrors.sales }}
                </el-form-item>
                <el-form-item label="库存" prop="stock">
                    <el-input v-model="form.stock" style="width: 50%"></el-input>
                    {{ serverValidErrors.stock }}
                </el-form-item>
            </el-form>
            <template #footer><span class="dialog-footer"> <el-button @click="dialogVisible = false">取 消</el-button> <el-button
                    type="primary" @click="save">确 定</el-button> </span></template>
        </el-dialog>
    </div>
</template>

<script>

    import request from "@/utils/request";

    export default {
        name: 'HomeView',
        components: {},
        data() {
            return {
                //存放错误信息
                serverValidErrors: {},
                tableData: [],
                search: '',
                form: '',
                dialogVisible: false,
                currentPage: 1,
                pageSize: 5,
                total: 10,
                rules: {
                    name: [{required: true, message: '请输入称家居名', trigger: 'blur'}],
                    maker: [{required: true, message: '请输入称制造商', trigger: 'blur'}],
                    price: [{required: true, message: '请输入价格', trigger: 'blur'}, {
                        pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/,
                        message: '请输入数字',
                        trigger: 'blur'
                    }],
                    sales: [{required: true, message: '请输入销量', trigger: 'blur'}, {
                        pattern: /^(([1-9]\d*)|(0))$/,
                        message: '请输入数字',
                        trigger: 'blur'
                    }],
                    stock: [{required: true, message: '请输入库存', trigger: 'blur'}, {
                        pattern: /^(([1-9]\d*)|(0))$/,
                        message: '请输入数字',
                        trigger: 'blur'
                    }]
                }
            }
        },
        created() {//钩子函数
            this.list();
        },
        methods: {
            handleEdit(row) {
                // console.log("row1=", row)
                // console.log("row2=", JSON.stringify(row))
                // console.log("row3=", JSON.parse(JSON.stringify(row)))
                this.form = JSON.parse(JSON.stringify(row));
                this.dialogVisible = true;
            },
            handleEdit2(row) {
                request.get("/api/find/" + row.id).then(
                    res => {
                        if (res.code === "200") {
                            this.form = res.data;
                            this.dialogVisible = true;
                        } else {
                            this.$message({//弹出更新失败信息
                                type: "error", message: res.msg
                            })
                        }
                    }
                )
            },
            add() {
                this.dialogVisible = true
                this.form = {}
                this.$refs['form'].resetFields()//将上传验证消息，清空
                this.serverValidErrors = {}
            },
            save() {//提交添加请求
                if (this.form.id) {//修改家具
                    request.put("/api/update", this.form).then(
                        res => {
                            if (res.code === "200") {//如果 code 为 200, 注意是字符串 200
                                this.$message({ //弹出更新成功的消息框
                                    type: "success", message: "更新成功"
                                })
                            } else {
                                this.$message({//弹出更新失败信息
                                    type: "error", message: res.msg
                                })
                            }
                            this.dialogVisible = false;
                            this.list()
                        }
                    )
                } else {//添加家具
                    //表单验证是否通过
                    this.$refs['form'].validate((valid) => {
                        valid = true;//后端验证
                        if (valid) {
                            //=======说明======
                            //1. 将form 表单提交给/api/save 的接口
                            //2. /api/save 等价http://localhost:10001/save
                            //3. 如果成功，就进入then 方法
                            //4. res 就是返回的信息
                            //5. 查看Mysql 看看数据是否保存
                            //将添加的数据发送到后端
                            request.post("/api/save", this.form).then(res => {
                                if (res.code === 200) {
                                    this.dialogVisible = false
                                    this.list()
                                } else if (res.code === 400) {
                                    this.serverValidErrors.name = res.extend.errorMsg.name;
                                    this.serverValidErrors.sales = res.extend.errorMsg.sales;
                                    this.serverValidErrors.price = res.extend.errorMsg.price;
                                    this.serverValidErrors.maker = res.extend.errorMsg.maker;
                                    this.serverValidErrors.stock = res.extend.errorMsg.stock;
                                }
                            })
                        } else {
                            this.$message({//弹出更新失败信息
                                type: "error",
                                message: "验证失败，不提交"
                            })
                            return false;
                        }
                    })
                }
            },
            list() {//显示家具信息
                // request.get("/api/furns").then(
                //     res => {
                //         this.tableData = res.data;
                //     }
                // )
                //分页显示
                request.get("/api/furnsBySearchPage", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(
                    res => {
                        //绑定 tableData, 显示在表格
                        this.tableData = res.data.records
                        this.total = res.data.total
                    }
                )
            },
            handleDel(id) {
                request.delete("/api/del/" + id).then(res => {
                    if (res.code === "200") {
                        this.$message({type: "success", message: "删除成功"})
                    } else {
                        this.$message({type: "error", message: res.msg})
                    }
                    this.list()
                })
            },
            handlePageSizeChange(pageSize) {
                this.pageSize = pageSize
                this.list()
            },
            handleCurrentChange(pageNum) { //处理每页显示多少条记录变化
                this.currentPage = pageNum
                this.list()
            }
        }
    }
</script>
