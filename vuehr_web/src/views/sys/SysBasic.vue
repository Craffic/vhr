<template>
  <div>
    <el-tabs type="card" v-model="activeName">
      <el-tab-pane label="部门管理" name="DepManager"><DepManager></DepManager></el-tab-pane>
      <el-tab-pane label="职位管理" name="PosManager"><PosManager></PosManager></el-tab-pane>
      <el-tab-pane label="职称管理" name="JobLevelManager"><JobLevelManager></JobLevelManager></el-tab-pane>
      <el-tab-pane label="奖惩规则" name="EcManager"><EcManager></EcManager></el-tab-pane>
      <el-tab-pane label="权限组" name="PermissionManager"><PermissionManager></PermissionManager></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  /*导入标签页的组件*/
  import DepManager from '../../components/sys/basic/DepManager'
  import EcManager from '../../components/sys/basic/EcManager'
  import JobLevelManager from '../../components/sys/basic/JobLevelManager'
  import PermissionManager from '../../components/sys/basic/PermissionManager'
  import PosManager from '../../components/sys/basic/PosManager'

export default {
  name: "SysBasic",
  data(){
    return{
      activeName : 'DepManager'
    }
  },
  components:{
    // 注册组件，key和value一样时，可以就写组件
    // 'DepManager': DepManager
    DepManager,
    EcManager,
    JobLevelManager,
    PermissionManager,
    PosManager
  }
}
</script>

<style scoped>

</style>