<template>
    <div>
      <el-input prefix-icon="el-icon-search"
          placeholder="输入部门名称进行搜索..."
          v-model="filterText" style="width: 500px">
      </el-input>

      <el-tree style="width: 500px" class="filter-tree" :data="depts"
          :props="defaultProps" :filter-node-method="filterNode" ref="tree"
          :expand-on-click-node="false"
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)">
        <!--编辑和删除按钮-->
        <span class="custom-tree-node" slot-scope="{ node, data }" style="display: flex;justify-content: space-between;width: 100%">
        <span>{{ node.label }}</span>
        <span>
          <el-button class="deptButton" type="primary" size="small" @click="() => showAddDept(data)" icon="el-icon-plus"></el-button>
          <el-button class="deptButton" type="danger" size="small" @click="() => deleteDept(data)" icon="el-icon-delete"></el-button>
        </span>
      </span>
      </el-tree>
      <!--添加部门对话框-->
      <el-dialog title="添加部门" :visible.sync="dialogVisible" width="30%">
          <div>
            <table>
              <tr>
                <td><el-tag>上级部门</el-tag></td>
                <td>{{parent_name}}</td>
              </tr>
              <tr>
                <td><el-tag>部门名称</el-tag></td>
                <td><el-input v-model="addDept.name" placeholder="请输入部门名称..."></el-input></td>
              </tr>
            </table>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addDeptMethod">确 定</el-button>
          </span>
      </el-dialog>
    </div>
</template>

<script>
import {deleteRequest, getRequest, postRequest} from "@/utils/api";

    export default {
        loading: false,
        name: "DepManager",
        data(){
          return {
            filterText: '',
            /*所有部门的信息集合*/
            depts: [],
            /*添加部门对象*/
            addDept: {
              name: '',
              parentId: -1
            },
            /*父部门的名称，只是用来作展示*/
            parent_name: '',
            defaultProps: {
              children: 'children',
              label: 'name'
            },
            dialogVisible: false
          }
        },
        watch: {
          filterText(val) {
            /*当filterText发生变化时，就会调用filter这个方法去处理搜索逻辑
            * filter方法也就是tree组件里的:filter-node-method="filterNode"配置，实际上是调用filterNode方法*/
            this.$refs.tree.filter(val);
          }
        },
      // 页面加载时调用mounted函数
      mounted() {
          this.initDepts();
      },
      methods: {
          filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
          },
          /*调用接口获取所有部门节点*/
          initDepts(){
            this.loading = true;
            getRequest('/system/basic/department/query/all_depts').then(reps => {
              this.loading = false;
              if (reps) {
                this.depts = reps;
              }
            })
          },
          /*添加部门弹出框*/
          showAddDept(data){
            // 显示对话框前，做变量赋值
            this.parent_name = data.name;
            this.addDept.parentId = data.id;
            // 显示对话框
            this.dialogVisible = true;
          },
          addDeptIntoDepts(depts, dept){
            // 遍历depts
            for (let i = 0; i < depts.length; i++) {
              let d = depts[i];
              if (d.id == dept.parentId) {
                d.children = d.children.concat(dept);
                return;
              } else {
                this.addDeptIntoDepts(d.children, dept);
              }
            }
          },
          /*添加部门*/
          addDeptMethod(){
            postRequest('/system/basic/department/add_dept', this.addDept).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                // 清空添加的addDept对象
                this.addDept.name = '';
                this.addDept.parentId = -1;
                this.parent_name = '';
                // 因为调用initDepts()方法刷新页面数据后，整个树会被收起来
                // 所以通过遍历的方式来把部门插入到正确的位置就不会收起来了
                // this.initDepts();
                this.addDeptIntoDepts(this.depts, resp.obj);
              }
            })
          },
          /*删除部门*/
          deleteDept(data){
            if (data.parent) {
              this.$message.error("父部门删除失败！");
            } else {
              this.$confirm('此操作将永久删除【' + data.name + '】部门, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                deleteRequest('/system/basic/department/del_dept/' + data.id).then(resp => {
                  if (resp) {
                    // 动态移除被删除的组
                    this.removeDeptFromList(null, this.depts, data.id);
                  }
                })
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });
            }
          },
          /*动态删除指定部门*/
        removeDeptFromList(p,deps, id) {
          for(let i=0;i<deps.length;i++){
            let d = deps[i];
            if (d.id == id) {
              deps.splice(i, 1);
              if (deps.length == 0) {
                p.parent = false;
              }
              return;
            }else{
              this.removeDeptFromList(d,d.children, id);
            }
          }
        }
        }
    }
</script>

<style scoped>
.deptButton {
  padding: 2px;
}
</style>