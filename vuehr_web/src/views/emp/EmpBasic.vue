<template>
<div>
    <div style="display: flex;justify-content: space-between">
        <!--输入框、搜索、高级搜索-->
        <div>
            <el-input placeholder="请输入员工姓名进行搜索..." prefix-icon="el-icon-search" style="width: 300px;margin-right: 10px"></el-input>
            <el-button type="primary" icon="el-icon-search">搜索</el-button>
            <el-button type="primary"><i class="fa fa-angle-double-down" style="margin-right: 5px" aria-hidden="true"></i>高级搜索</el-button>
        </div>
        <!--导出、导入、添加用户-->
        <div>
            <el-button type="success"><i class="fa fa-arrow-down" style="margin-right: 5px" aria-hidden="true"></i>导出数据</el-button>
            <el-button type="success"><i class="fa fa-arrow-up" style="margin-right: 5px" aria-hidden="true"></i>导入数据</el-button>
            <el-button type="primary" prefix-icon="el-icon-plus">添加用户</el-button>
        </div>
    </div>
    <div style="margin-top: 20px">
        <el-table :data="emps" border style="width: 100%"
                  v-loading="loading"
                  element-loading-text="正在加载..."
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="name" label="姓名" width="90" fixed align="left"></el-table-column>
            <el-table-column prop="workID" label="工号" width="100" align="left"></el-table-column>
            <el-table-column prop="birthday" label="出生日期" width="85"></el-table-column>
            <el-table-column prop="idCard" label="身份证号码" width="120"></el-table-column>
            <el-table-column prop="wedlock" label="婚姻状况"></el-table-column>
            <el-table-column prop="nation.name" label="民族"></el-table-column>
            <el-table-column prop="nativePlace" label="籍贯"></el-table-column>
            <el-table-column prop="politicId" label="政治面貌"></el-table-column>
            <el-table-column prop="email" label="电子邮件" width="180"></el-table-column>
            <el-table-column prop="phone" label="电话号码" width="120"></el-table-column>
            <el-table-column prop="address" label="联系地址" width="300"></el-table-column>
            <el-table-column prop="department.name" label="所属部门" width="90"></el-table-column>
            <el-table-column prop="position.name" label="职位" width="120"></el-table-column>
            <el-table-column prop="jobLevel.name" label="职称"  width="120"></el-table-column>
            <el-table-column prop="engageForm" label="聘用形式" width="90"></el-table-column>
            <el-table-column prop="beginDate" label="入职日期" width="120"></el-table-column>
            <el-table-column prop="conversionTime" label="转正日期" width="120"></el-table-column>
            <el-table-column prop="beginContract" label="合同起始日期" width="120"></el-table-column>
            <el-table-column prop="endContract" label="合同终止日期" width="120"></el-table-column>
            <el-table-column prop="contractTerm" label="合同期限"  width="100">
                <template slot-scope="scope">
                    <el-tag style="margin-right: 3px">{{scope.row.contractTerm}}</el-tag>年
                </template>
            </el-table-column>
            <el-table-column prop="tiptopDegree" label="最高学历" width="100"></el-table-column>
            <el-table-column prop="idCard" label="操作" fixed="right" width="300">
                <template slot-scope="scope">
                    <el-button size="mini">编辑</el-button>
                    <el-button size="mini" type="primary">查看高级资料</el-button>
                    <el-button size="mini" type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="display: flex;justify-content: flex-end">
            <el-pagination
                    background
                    @current-change="currentChange"
                    @size-change="sizeChange"
                    layout="sizes, prev, pager, next, jumper, ->, total, slot"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</div>
</template>

<script>
    import {getRequest} from "../../utils/api";

    export default {
      name: "EmpBasic",
      data() {
          return {
              emps: [],
              loading: false,
              total: 0,
              page:1,
              size: 10
          }
      },
      methods: {
          /*当前页*/
          currentChange(currentPage) {
              this.page = currentPage;
              this.initEmps();
          },
          /*改变每页大小*/
          sizeChange(currentSize){
              this.size = currentSize;
              this.initEmps();
          },
          initEmps() {
              this.loading = true;
              getRequest('/emp/basic/?page=' + this.page + '&size=' + this.size).then(resp => {
                  this.loading = false;
                  if (resp) {
                      this.emps = resp.data;
                      this.total = resp.total;
                  }
              })
          }
      },
      mounted() {
          this.initEmps();
      }
    }
</script>

<style scoped>

</style>