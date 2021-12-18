<template>
    <div>
        <div>
            <el-input placeholder="添加职位" class="addPosInput" prefix-icon="el-icon-plus" size="mini" v-model="pos.name"> </el-input>
            <el-button icon="el-icon-plus" size="mini" type="primary" @click="addPosition">添加</el-button>
        </div>
        <div class="positionTable">
            <el-table :data="positions" style="width: 70%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="id" label="编号" width="150"></el-table-column>
                <el-table-column prop="name" label="职位名称" width="250"></el-table-column>
                <el-table-column prop="createDate" label="创建日期"></el-table-column>
                <el-table-column prop="createDate" label="是否启用">
                  <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                    <el-tag type="danger" v-else>未启用</el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="editEditView(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button type="danger" style="margin-top: 8px" size="small" @click="multiDelete" :disabled="multipleSelection.length==0">批量删除</el-button>
        </div>
        <!--修改职位对话框-->
        <el-dialog title="修改职位" :visible.sync="dialogFormVisible" width="30%">
          <div>
             <el-tag>职位名称</el-tag>
             <el-input class="editPosInput" size="small" v-model="updatePos.name"></el-input>
          </div>
          <div>
             <el-switch
                 v-model="updatePos.enabled" active-text="启用" inactive-text="未启用"
                 active-color="#13ce66"
                 inactive-color="#ff4949">
             </el-switch>
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="cancelUpdatePos">取 消</el-button>
            <el-button size="small" type="primary" @click="updatePosition">确 定</el-button>
          </div>
        </el-dialog>
    </div>
</template>

<script>
    import {deleteRequest, getRequest, postRequest, putRequest} from "../../../utils/api";

    export default {
        name: "PosManager",
        data() {
            return {
                pos: {
                    name: '',
                    enabled: false
                },
                positions: [],
                multipleSelection: [],
                /*用来控制编辑对话框是否展示*/
                dialogFormVisible: false,
                /*定义一个新的对象用来表示新的更新对象*/
                updatePos: {
                  name: '',
                  enabled: false
                }
            }

        },
        mounted() {
            this.initPositions();
        },
        methods: {
            /*点击职位的编辑按钮，弹出编辑对话框*/
            editEditView(index, data){
               Object.assign(this.updatePos, data);
               this.dialogFormVisible = true;
            },
            /*更新职位*/
            updatePosition() {
               putRequest("/system/basic/position/update", this.updatePos).then(resp => {
                 if (resp){
                   this.initPositions();
                   this.updatePos.name = '';
                   this.dialogFormVisible = false;
                 }
               })
            },
            cancelUpdatePos() {
              this.dialogFormVisible = false;
              this.updatePos.name = '';
            },
            initPositions(){
                getRequest("/system/basic/position/queryAll").then(resp=>{
                    this.positions = resp;
                })
            },
            /*编辑*/
            handleEdit(index, data){

            },
            /*删除*/
            handleDelete(index, data){
                this.$confirm('此操作将永久删除【'+ data.name +'】职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteRequest('/system/basic/position/delete/' + data.id).then(resp => {
                        this.initPositions();
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            /*添加*/
            addPosition(){
                if (this.pos.name){
                    postRequest('/system/basic/position/add', this.pos).then(resp=>{
                        if (resp){
                            // 重新调用初始化职位接口，刷新页面数据
                            this.initPositions();
                            this.pos.name = '';
                        }
                    })
                } else {
                    this.$message.error("职位名称不能为空！");
                }
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
                deleteRequest("/system/basic/position/multi_del" + ids, null).then(resp => {
                  if (resp) {
                    this.initPositions();
                  }
                })
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });
            },
            /*批量删除多选框点击事件*/
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        }
    }
</script>

<style>
    /*编辑输入框样式*/
    .editPosInput {
       width: 300px;
      margin-left: 6px;
    }
    /*添加输入框样式*/
    .addPosInput {
        width: 300px; margin-right: 8px
    }
    /*职位表格样式*/
    .positionTable {
        margin-top: 10px;
    }
</style>