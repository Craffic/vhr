<template>
    <div>
      <div class="permissManaTool">
        <el-input size="small" placeholder="请输入角色英文名" v-model="role.name">
          <template slot="prepend">ROLE_</template>
        </el-input>
        <el-input size="small" placeholder="请输入角色中文名" v-model="role.nameZh" @keydown.en.enter.native="addRoles"></el-input>
        <el-button size="small" icon="el-icon-plus" type="primary" @click="addRoles">添加角色</el-button>
      </div>
      <div class="permissManaMain">
        <el-collapse v-model="activeName" accordion @change="change">
          <el-collapse-item :title="role.nameZh" :name="role.id" v-for="(role, index) in roles" :key="index">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>可访问资源</span>
                <!--删除权限-->
                <el-button style="float: right; padding: 3px 0; color: red" icon="el-icon-delete" @click="deleteRole(role)"></el-button>
              </div>
              <!--展示菜单项-->
              <div>
                  <!--树形控件展示菜单结构-->
                  <el-tree ref="tree" show-checkbox :data="allMenus" :props="defaultProps" node-key="id" :default-checked-keys="selectedMenus"></el-tree>
                  <div style="display: flex;justify-content: flex-end">
                    <el-button size="small" @click="cancelUpdate">取消修改</el-button>
                    <el-button size="small" type="primary" @click="comfirmUpdate(role.id, index)">确认修改</el-button>
                  </div>
              </div>
            </el-card>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
</template>

<script>
import {deleteRequest, getRequest, postRequest, putRequest} from "@/utils/api";
    import {initMenu} from "@/utils/menu";

    export default {
        name: "PermissionManager",
        data(){
          return{
            role: {
              name: '',
              nameZh: ''
            },
            roles: [],
            allMenus: [],
            activeName: -1,
            /*定义树里面的children的key是什么，label的key是什么*/
            defaultProps: {
              children: 'children',
              label: 'name'
            },
            /*每个角色对应的菜单id*/
            selectedMenus: []
          }
        },
        mounted() {
            this.initRoles();
        },
        methods:{
            /*查询所有的角色*/
            initRoles(){
              getRequest("/system/basic/permiss/query/all_role").then(resp => {
                  if (resp) {
                    this.roles = resp;
                  }
              })
            },
            /*查询所有菜单数据*/
            initMenus(){
              getRequest('/system/basic/permiss/menus').then(resp => {
                if (resp) {
                  this.allMenus = resp;
                }
              })
            },
            /*点击折叠页顶部触发的事件*/
            change(rid){
              /*展开是name有值，折叠起来name无值*/
              if (rid) {
                // 展开时请求所有菜单数据
                this.initMenus();
                // 预选中角色对应的菜单
                this.initSelectedMenus(rid);
              }
            },
            /*查询具体角色对应的菜单*/
            initSelectedMenus(rid){
              getRequest('/system/basic/permiss/mids/' + rid).then(resp => {
                if (resp) {
                  this.selectedMenus = resp;
                }
              })
            },
            /*确认修改*/
            comfirmUpdate(rid, index){
              let tree = this.$refs.tree[index];
              let selectedKeys = tree.getCheckedKeys(true);// true表示只返回叶子节点
              var url = '/system/basic/permiss/update/menu_role?rid=' +rid;
              selectedKeys.forEach(key => {
                url += '&mids=' + key;
              })
              console.log(url);
              putRequest(url).then(resp =>{
                if (resp) {
                  this.activeName = -1;
                }
              })
            },
            /*取消修改*/
            cancelUpdate(){
              this.activeName = -1;
            },
            /*添加角色*/
            addRoles(){
                if (this.role.name && this.role.nameZh){
                  postRequest('/system/basic/permiss/role', this.role).then(resp => {
                    if (resp) {
                      this.role.name = '';
                      this.role.nameZh = '';
                      this.initRoles();
                    }
                  })
                } else {
                  this.$message.error("角色英文名或中文名不能为空！");
                }
            },
            /*删除角色*/
            deleteRole(role){
              this.$confirm('此操作将永久删除【' + role.nameZh + '】角色， 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                deleteRequest('/system/basic/permiss/role/' + role.id).then(resp => {
                  this.initRoles();
                  this.activeName = -1;
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
.permissManaTool{
  display: flex;
  justify-content: flex-start;
}
.permissManaTool .el-input {
  width: 300px;
  margin-right: 6px;
}
.permissManaMain{
  margin-top: 10px;
  width: 800px;
}
</style>