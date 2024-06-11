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
            <el-input v-model="search" placeholder=" 请 输 入 关 键 字 " style="width: 30%"></el-input>
            <el-button style="margin-left: 10px" type="primary">查询</el-button>
        </div>

        <el-table :data="tableData" stripe style="width: 90%">
            <el-table-column prop="date" label="日期"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>

            <el-table-column fixed="right" label="操作" width="100">
                <template #default="scope">
                    <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
                    <el-button type="text">删除</el-button>
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
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: 'HomeView',
        components: {},
        data() {
            return {
                search: '',
                dialogVisible: false,
                form: {},
                tableData: [{date: '2016-05-02', name: '王小虎', address: '上海市普陀区金沙江路 1518 弄',},
                    {date: '2016-05-04', name: '王小虎', address: '上海市普陀区金沙江路 1517 弄',},
                    {date: '2016-05-01', name: '王小虎', address: '上海市普陀区金沙江路 1519 弄',}]
            }
        },
        methods: {
            handleEdit() {
            },
            save() {
                //将添加的数据发送到后端
                // =======说明====...
                request.post("api/save", this.form).then(res => {
                    console.log(res)
                    this.dialogVisible = false
                })
            },
            add() {
                //显示对话框
                this.dialogVisible = true;
                //清空表单
                this.form = {};

            }
        }
    }
</script>
