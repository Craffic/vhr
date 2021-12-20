<template>
    <div>
        <div>
          <el-input size="small" style="width: 300px;margin-right: 6px" v-model="jl.name" prefix-icon="el-icon-plus" placeHolder="添加职称"></el-input>
          <el-select size="small" v-model="jl.titleLevel" placeholder="职称等级" style="margin-left: 5px; margin-right: 5px">
            <el-option v-for="item in titleLevels" :key="item" :label="item" :value="item">
            </el-option>
          </el-select>
          <el-button icon="el-icon-plus" size="small" type="primary" @click="addJobLevel">添加</el-button>
        </div>
        <!--表格数据-->
        <div style="margin-top: 10px">
          <el-table :data="jls" border style="width: 70%" size="small" @selection-change="handleSelectionChange"
                    v-loading="loading"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="编号" width="120" size="small"></el-table-column>
            <el-table-column prop="name" label="职称名称" width="250" size="small"></el-table-column>
            <el-table-column prop="titleLevel" label="职称级别" width="200" size="small"></el-table-column>
            <el-table-column prop="createDate" label="创建时间" width="200" size="small"></el-table-column>
            <el-table-column prop="createDate" label="是否启用">
              <template slot-scope="scope">
                <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                <el-tag type="danger" v-else>未启用</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="small" @click="showEditJobLevel(scope.row)">编 辑</el-button>
                <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删 除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button size="small" type="danger" style="margin-top: 5px" @click="multiDelete" :disabled="multipleSelection.length==0">批量删除</el-button>
        </div>
        <!--修改职称对话框-->
        <el-dialog title="修改职称" :visible.sync="dialogFormVisible" width="30%">
        <div>
          <table>
            <tr>
              <td><el-tag>职称名</el-tag></td>
              <td><el-input size="small" v-model="updateJl.name"></el-input></td>
            </tr>
            <tr>
              <td><el-tag>职称级别</el-tag></td>
              <td>
                <el-select size="small" v-model="updateJl.titleLevel" placeholder="职称等级" style="margin-left: 5px; margin-right: 5px">
                  <el-option v-for="item in titleLevels" :key="item" :label="item" :value="item">
                  </el-option>
                </el-select>
              </td>
            </tr>
            <tr>
              <td><el-tag>是否启用</el-tag></td>
              <td>
                <el-switch
                    v-model="updateJl.enabled" active-text="启用" inactive-text="未启用"
                    active-color="#13ce66"
                    inactive-color="#ff4949">
                </el-switch>
              </td>
            </tr>
          </table>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="cancelUpdateJl">取 消</el-button>
          <el-button size="small" type="primary" @click="updateJobLevel">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
import {deleteRequest, getRequest, postRequest, putRequest} from "@/utils/api";

    export default {
        name: "JobLevelManager",
        data(){
          return{
            loading: false,
            jl:{
              name: '',
              titleLevel: '',
            },
            titleLevels: ['正高级', '副高级', '高级', '中级', '初级'],
            /*职称的表格数据*/
            jls: [],
            /*修改职称对话框标志*/
            dialogFormVisible: false,
            updateJl: {
              name: '',
              titleLevel: '',
              enabled: false,
            },
            /*多选框选中的数据*/
            multipleSelection: []
          }
        },
      mounted() {
          // 加载页面时，调用查询所有职称数据接口
          this.initJobLevels();
      },
      methods: {
          /*添加职称*/
          addJobLevel(){
            if (this.jl.name && this.jl.titleLevel){
              // 添加字段不为空
              postRequest("/system/basic/job_level/add", this.jl).then(resp => {
                if (resp){
                  // 添加成功重新调用接口刷新页面数据
                  this.initJobLevels();
                  // this.jl.name = '';
                  // this.jl.titleLevel = '';
                  // 清空字段可以这样写
                  this.jl = {
                    name: '',
                    titleLevel: '',
                  }
                }
              })
            } else {
              // 如果字段为空，弹出错误提示框
              this.$message.error("职称名字和职称不能为空！");
            }
          },
          /*单个删除*/
        deleteHandler(data){
          this.$confirm('此操作将永久删除【'+ data.name +'】职称, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteRequest('/system/basic/job_level/delete/' + data.id).then(resp => {
              if (resp) {
                this.initJobLevels();
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        /*查询所有职称*/
        initJobLevels(){
          this.loading = true;
          getRequest("/system/basic/job_level/queryAll").then(resp => {
            this.loading = false;
            if (resp){
              // 请求成功
              this.jls = resp;
            }
          })
        },
        /*弹出修改职称对话框*/
        showEditJobLevel(data){
          // 拷贝变量
          Object.assign(this.updateJl, data);
          this.dialogFormVisible = true;
        },
        /*修改职称*/
        updateJobLevel(){
          putRequest("/system/basic/job_level/update", this.updateJl).then(resp => {
            if (resp) {
              this.initJobLevels();
              this.dialogFormVisible = false;
            }
          })
        },
        /*取消修改*/
        cancelUpdateJl(){
          this.dialogFormVisible = false;
        },
        /*点击多选框事件*/
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        /*批量删除*/
        multiDelete() {
          this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let ids = '?';
            this.multipleSelection.forEach(item => {
              ids += 'ids=' + item.id + '&';
            })
            deleteRequest("/system/basic/job_level/multi_del" + ids, null).then(resp => {
              if (resp) {
                this.initJobLevels();
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
      }
    }
</script>

<style scoped>

</style>