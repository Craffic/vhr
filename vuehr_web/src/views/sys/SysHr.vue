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
              <el-switch
                  v-model="hr.enabled"
                  active-text="启用"
                  inactive-text="禁用"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </div>
            <div>用户角色：
              <el-tag style="margin-right: 5px" type="success" size="mini" v-for="(role, indexj) in hr.roles" :key="indexj">{{role.nameZh}}</el-tag>
              <el-button icon="el-icon-more" size="mini" type="text"></el-button>
            </div>
            <div>备注：{{hr.remark}}</div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "SysHr",
  data(){
    return {
      keywords: '',
      /*查询返回所有的hr数组*/
      hrs: []
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