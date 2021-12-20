<template>
  <div>
<!--    搜索栏-->
    <div style="margin-top: 10px; display: flex;justify-content: center">
      <el-input v-model="keywords" size="small" placeholder="通过用户名搜索..." prefix-icon="el-icon-search" style="width: 400px;margin-right: 10px"></el-input>
      <el-button icon="el-icon-search" @click="search" size="small" type="primary">搜索</el-button>
    </div>
<!--    hr信息卡片-->
    <div class="hr-container">
      <el-card class="hr-card" v-for="(hr, index) in hrs" :key="index">
        <div slot="header" class="clearfix">
          <span>{{hr.name}}</span>
          <el-button style="float: right; padding: 3px 0;color: red" type="text" icon="el-icon-delete">操作按钮</el-button>
        </div>
        <div>
          <div class="img-container">
            <img :src="hr.userface" :alt="hr.name" :title="hr.name" class="userface-img">
          </div>
          <div class="userinfo-container">
            <div>用户名：{{hr.name}}</div>
            <div>手机号码：{{hr.phone}}</div>
            <div>电话号码：{{hr.telephone}}</div>
            <div>地址：{{hr.address}}</div>
            <div>用户状态：
              <el-switch @change="enabledHr(hr)"
                  v-model="hr.enabled"
                  active-text="启用"
                  inactive-text="禁用"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </div>
            <div>用户角色：
              <el-tag style="margin-right: 5px" type="success" size="mini" v-for="(role, indexj) in hr.roles" :key="indexj">{{role.nameZh}}</el-tag>
              <!--修改角色弹出框-->
              <el-popover
                      placement="bottom"
                      title="角色列表"
                      width="200"
                      trigger="click"
                      @show="showPop(hr)"
                      @hide="hidePop(hr)">
                <!--角色多选框-->
                <el-select v-model="selectedRoles" placeholder="请选择" multiple>
                  <el-option
                          v-for="(role, indexk) in all_roles"
                          :key="indexk"
                          :label="role.nameZh"
                          :value="role.id"><!--role.id是提交到服务器的数据-->
                  </el-option>
                </el-select>
                <el-button  slot="reference" icon="el-icon-more" size="mini" type="text"></el-button>
              </el-popover>
            </div>
            <div>备注：{{hr.remark}}</div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {getRequest, putRequest} from "@/utils/api";

export default {
  name: "SysHr",
  data(){
    return {
      keywords: '',
      /*查询返回所有的hr数组*/
      hrs: [],
      /*所有角色集合，供角色多选框用*/
      all_roles: [],
      /*用户对应的角色*/
      selectedRoles: []
    }
  },
  mounted(){
    this.initHrs();
  },
  methods: {
    /*搜索*/
    search(){

    },
    /*查询所有的hr用户*/
    initHrs(){
      getRequest('/system/hr/query/all_hr').then(resp => {

        if (resp) {
          this.hrs = resp;
        }
      })
    },
    /*更新用户状态*/
    enabledHr(hr){
      delete hr.roles;
      putRequest("/system/hr/update", hr).then(resp => {
        if (resp) {
          this.initHrs();
        }
      })
    },
    /*查询所有角色列表*/
    initRoles(){
      getRequest('/system/hr/query/all_role').then(resp => {
        if (resp) {
          this.all_roles = resp;
        }
      })
    },
    /*点击pop弹框调用的方法*/
    showPop(hr){
      this.initRoles();
      /*先清空一下历史数据*/
      this.selectedRoles = [];
      /*根据hr.roles遍历role，放到selectedRoles里*/
      let roles = hr.roles;
      roles.forEach(r => {
        this.selectedRoles.push(r.id);
      })
    },
    /*隐藏弹框，调用修改用户角色接口*/
    hidePop(hr) {
      let changeFlag = false;
      let roles = [];
      Object.assign(roles, hr.roles);
      /*如果用户没修改过角色集合，再离开弹框的话还是会更新成功*/
      /*所以要比对hr.roles和this.selectedRoles是否一致，才做更新操作*/
      /*1. 如果两边集合长度都不一致，肯定做过修改*/
      if (this.selectedRoles.length != hr.roles.length){
        changeFlag = true;
      } else {
        /*2. 遍历两个集合，相同的就移除一个集合里的数据，到最后没数据就说明两个集合一致*/
        for (let i = 0; i < roles.length; i++) {
          let role = roles[i];
          for (let j = 0; j < this.selectedRoles.length; j++) {
            let sr = this.selectedRoles[j];
            if (role.id == sr) {
              roles.splice(i, 1);
              i--;
              break;
            }
          }
        }
        if (roles.length != 0) {
          changeFlag = true;
        }
      }


      if (changeFlag){
        let url = '/system/hr/update/roles?hrid=' + hr.id;
        this.selectedRoles.forEach(rid => {
          url += '&rids=' + rid;
        })
        putRequest(url).then(resp => {
          if (resp) {
            this.initHrs();
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.hr-card {
  width: 400px;
  margin-bottom: 20px;
}
.hr-container {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
.userface-img {
  width: 72px;
  height: 72px;
  border-radius: 72px;
}
.img-container {
  width: 100%;
  display: flex;
  justify-content: center;
}
.userinfo-container {
  margin-top: 20px;
}
</style>