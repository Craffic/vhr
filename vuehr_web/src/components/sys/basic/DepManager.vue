<template>
    <div>
      <el-input prefix-icon="el-icon-search"
          placeholder="输入部门名称进行搜索..."
          v-model="filterText" style="width: 500px">
      </el-input>

      <el-tree style="width: 500px"
          class="filter-tree"
          :data="depts"
          :props="defaultProps"
          :filter-node-method="filterNode"
          ref="tree">
      </el-tree>
    </div>
</template>

<script>
    import {getRequest} from "@/utils/api";

    export default {
        name: "DepManager",
        data(){
          return {
            filterText: '',
            /*所有部门的信息集合*/
            depts: [],
            defaultProps: {
              children: 'children',
              label: 'name'
            }
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
            getRequest('/system/basic/department/query/all_depts').then(reps => {
              if (reps) {
                this.depts = reps;
              }
            })
          }
        }
    }
</script>

<style scoped>

</style>