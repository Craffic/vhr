<template>
<div>
    <div style="display: flex;justify-content: space-between">
        <!--输入框、搜索、高级搜索-->
        <div>
            <el-input placeholder="请输入员工姓名进行搜索..." prefix-icon="el-icon-search" style="width: 300px;margin-right: 10px"
                      v-model="keyword" @keydown.enter.native="initEmps"
                      clearable @clear="initEmps"></el-input>
            <el-button type="primary" icon="el-icon-search" @click="initEmps">搜索</el-button>
            <el-button type="primary"><i class="fa fa-angle-double-down" style="margin-right: 5px" aria-hidden="true"></i>高级搜索</el-button>
        </div>
        <!--导出、导入、添加用户-->
        <div>
            <el-button type="success" @click="exportData"><i class="fa fa-arrow-down" style="margin-right: 5px" aria-hidden="true"></i>导出</el-button>
            <el-button type="success"><i class="fa fa-arrow-up" style="margin-right: 5px" aria-hidden="true"></i>导入</el-button>
            <el-button type="primary" prefix-icon="el-icon-plus" @click="showEmpAddDialog">添加用户</el-button>
        </div>
    </div>
    <div style="margin-top: 20px">
        <el-table :data="emps" border style="width: 100%"
                  v-loading="loading"
                  element-loading-text="正在加载..."
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="Transparent"
                  @cell-dblclick="tableDbEdit">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="name" label="姓名" width="90" fixed align="left"></el-table-column>
            <el-table-column prop="workID" label="工号" width="100" align="left"></el-table-column>
            <el-table-column prop="gender" label="性别" width="60" align="left"></el-table-column>
            <el-table-column prop="birthday" label="出生日期" width="100"></el-table-column>
            <el-table-column prop="idCard" label="身份证号码" width="180"></el-table-column>
            <el-table-column prop="wedlock" label="婚姻状况"></el-table-column>
            <el-table-column prop="nation.name" label="民族"></el-table-column>
            <el-table-column prop="nativePlace" label="籍贯"></el-table-column>
            <el-table-column prop="politicsstatus.name" label="政治面貌"></el-table-column>
            <el-table-column prop="email" label="电子邮箱" width="180"></el-table-column>
            <el-table-column prop="phone" label="电话号码" width="120"></el-table-column>
            <el-table-column prop="address" label="联系地址" width="300"></el-table-column>
            <el-table-column prop="department.name" label="所属部门" width="90"></el-table-column>
            <el-table-column prop="position.name" label="职位" width="120"></el-table-column>
            <el-table-column prop="jobLevel.name" label="职称"  width="140"></el-table-column>
            <el-table-column prop="engageForm" label="聘用形式" width="90"></el-table-column>
            <el-table-column prop="tiptopDegree" label="最高学历" width="90"></el-table-column>
            <el-table-column prop="specialty" label="专业" width="120"></el-table-column>
            <el-table-column prop="school" label="毕业院校" width="140"></el-table-column>
            <el-table-column prop="beginDate" label="入职日期" width="120"></el-table-column>
            <el-table-column prop="conversionTime" label="转正日期" width="120"></el-table-column>
            <el-table-column prop="workState" label="在职状态" width="120"></el-table-column>
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
                    <el-button size="mini" @click="showEmpEditDialog(scope.row)">编辑</el-button>
                    <el-button size="mini" type="primary">查看高级资料</el-button>
                    <el-button size="mini" type="danger" @click="deleteEmp(scope.row)">删除</el-button>
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
    <!--添加用户对话框-->
    <el-dialog :title="title" :visible.sync="empDialogVisible" width="60%">
        <div>
            <el-form :rules="rules" :model="emp" ref="empForm">
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="姓名：" prop="name">
                            <el-input placeholder="请输入员工姓名" v-model="emp.name" prefix-icon="el-icon-edit" style="width: 150px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="性别：" prop="gender">
                            <el-radio-group v-model="emp.gender">
                                <el-radio label="男">男</el-radio>
                                <el-radio label="女" style="margin-left: 0px">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="出生日期：" prop="birthday">
                            <el-date-picker v-model="emp.birthday" type="date" placeholder="出生日期" size="mini" value-format="yyyy-MM-dd" style="width: 150px"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item label="政治面貌：" prop="politicId">
                            <el-select v-model="emp.politicId" placeholder="政治面貌" style="width: 200px" size="mini">
                                <el-option v-for="item in politicsstatus" :key="item.id" :label="item.name" :value="item.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="民族：" prop="nationId">
                            <el-select v-model="emp.nationId" placeholder="民族" style="width: 150px" size="mini">
                                <el-option v-for="nation in nations" :key="nation.id" :label="nation.name" :value="nation.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="籍贯：" prop="nativePlace">
                            <el-input placeholder="请输入籍贯" v-model="emp.nativePlace" prefix-icon="el-icon-edit" style="width: 120px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="电子邮箱：" prop="email">
                            <el-input placeholder="请输入电子邮箱" v-model="emp.email" prefix-icon="el-icon-message" style="width: 150px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item label="联系地址：" prop="address">
                            <el-input placeholder="请输入联系地址" v-model="emp.address" prefix-icon="el-icon-message" style="width: 200px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="职位：" prop="posId">
                            <el-select v-model="emp.posId" placeholder="职位" style="width: 150px" size="mini">
                                <el-option v-for="position in positions" :key="position.id" :label="position.name" :value="position.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="职称：" prop="jobLevelId">
                            <el-select v-model="emp.jobLevelId" placeholder="职称" style="width: 150px" size="mini">
                                <el-option v-for="jl in joblevels" :key="jl.id" :label="jl.name" :value="jl.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="所属部门：" prop="departmentId">
                            <el-popover
                                    placement="right"
                                    title="请选择部门"
                                    width="200"
                                    trigger="manual"
                                    v-model="departmentVisable">
                                <div slot="reference"
                                     style="width: 150px;height: 26px;display: inline-flex;font-size: 13px;border: 1px solid #dedede;border-radius: 5px;
                                            cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box"
                                     @click="showDepView">{{inputDepName}}</div>
                                <el-tree :data="departmentTree" :props="defaultProps" @node-click="handleNodeClick" default-expand-all></el-tree>
                            </el-popover>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item label="电话号码：" prop="phone">
                            <el-input placeholder="请输入电话号码" v-model="emp.phone" prefix-icon="el-icon-phone" style="width: 200px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="工号：" prop="departmentId">
                            <el-input placeholder="工号" v-model="emp.workID" prefix-icon="el-icon-edit" style="width: 150px" size="mini" disabled></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="学历：" prop="tiptopDegree">
                            <el-select v-model="emp.tiptopDegree" placeholder="请选择学历" style="width: 150px" size="mini">
                                <el-option v-for="item in tiptopDegree" :key="item" :label="item" :value="item"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="毕业院校：" prop="school">
                            <el-input placeholder="请输入毕业院校" v-model="emp.school" prefix-icon="el-icon-edit" style="width: 150px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item label="专业名称：" prop="specialty">
                            <el-input placeholder="请输入专业名称" v-model="emp.specialty" prefix-icon="el-icon-edit" style="width: 200px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="入职日期：" prop="beginDate">
                            <el-date-picker v-model="emp.beginDate" type="date" placeholder="入职日期" size="mini" value-format="yyyy-MM-dd" style="width: 130px"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="转正日期：" prop="conversionTime">
                            <el-date-picker v-model="emp.conversionTime" type="date" placeholder="转正日期" size="mini" value-format="yyyy-MM-dd" style="width: 130px"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="合同起始日期：" prop="beginContract">
                            <el-date-picker v-model="emp.beginContract" type="date" placeholder="合同起始日期" size="mini" value-format="yyyy-MM-dd" style="width: 130px"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="合同终止日期：" prop="endContract">
                            <el-date-picker v-model="emp.endContract" type="date" placeholder="合同终止日期" size="mini" value-format="yyyy-MM-dd" style="width: 130px"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="身份证号：" prop="idCard">
                            <el-input placeholder="请输入身份证号" v-model="emp.idCard" prefix-icon="el-icon-edit" style="width: 180px" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="招聘形式：" prop="engageForm">
                            <el-radio-group v-model="emp.engageForm">
                                <el-radio label="劳动合同">劳动合同</el-radio>
                                <el-radio label="劳务合同" style="margin-left: 0px">劳务合同</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="婚姻状况：" prop="engageForm">
                            <el-radio-group v-model="emp.wedlock">
                                <el-radio label="已婚">已婚</el-radio>
                                <el-radio label="未婚">未婚</el-radio>
                                <el-radio label="离异">离异</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="empDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addOrUpdateEmp">确 定</el-button>
        </span>
    </el-dialog>
</div>
</template>

<script>
import {deleteRequest, getRequest, postRequest, putRequest} from "../../utils/api";

    export default {
      name: "EmpBasic",
      data() {
          return {
              /*添加或修改弹窗标题*/
              title: '',
              emps: [],
              /*部门树结构体*/
              departmentTree:[],
              /*选中后的部门名称*/
              inputDepName: '',
              loading: false,
              total: 0,
              page:1,
              size: 10,
              keyword: '',
              empDialogVisible: false,
              /*下拉框集合*/
              nations: [],
              politicsstatus: [],
              joblevels: [],
              positions: [],
              tiptopDegree: ['博士后', '博士', '研究生', '本科', '大专', '高中', '中专', '初中', '小学', '其他'],
              // emp:{name: "姚森",
              //      gender: "男",
              //      birthday: "1991-02-05",
              //      idCard: "610122199102058952",
              //      wedlock: "已婚",
              //      nationId: 1,
              //      nativePlace: "河南",
              //      politicId: 3,
              //      email: "yaosen@qq.com",
              //      phone: "14785559936",
              //      address: "河南洛阳人民大道58号",
              //      departmentId: 92,
              //      jobLevelId: 15,
              //      posId: 34,
              //      engageForm: "劳动合同",
              //      tiptopDegree: "硕士",
              //      specialty: "室内装修设计",
              //      school: "西北大学",
              //      beginDate: "2017-01-02",
              //      workState: "在职",
              //      workID: '',
              //      contractTerm: 7,
              //      conversionTime: "2017-04-02",
              //      notWorkDate: null,
              //      beginContract: "2017-01-02",
              //      endContract: "2024-01-17",
              //      workAge: null,
              //      salary: null
              // },
              emp:{name: '',
              gender: '',
              birthday: '',
              idCard: '',
              wedlock: '',
              nationId: '',
              nativePlace: '',
              politicId: '',
              email: '',
              phone: '',
              address: '',
              departmentId: '',
              jobLevelId: '',
              posId: '',
              engageForm: '',
              tiptopDegree: '',
              specialty: '',
              school: '',
              beginDate: '',
              workState: '在职',
              workID: '',
              contractTerm: '',
              conversionTime: '',
              notWorkDate: null,
              beginContract: '',
              endContract: '',
              workAge: '',
              salary: null
            },
              /*添加页面 - 部门树弹框*/
              departmentVisable: false,
              defaultProps: {
                  children: 'children',
                  label: 'name'
              },
              /*校验规则*/
            rules: {
              name: [{required: true, message: '请输入用户名', trigger: 'blur'}],
              gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
              birthday: [{required: true, message: '请输入出生日期', trigger: 'blur'}],
              idCard: [{required: true, message: '请输入身份证号码', trigger: 'blur'}, {
                pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                message: '身份证号码格式不正确',
                trigger: 'blur'
              }],
              wedlock: [{required: true, message: '请输入婚姻状况', trigger: 'blur'}],
              nationId: [{required: true, message: '请输入您组', trigger: 'blur'}],
              nativePlace: [{required: true, message: '请输入籍贯', trigger: 'blur'}],
              politicId: [{required: true, message: '请输入政治面貌', trigger: 'blur'}],
              email: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
                type: 'email',
                message: '邮箱格式不正确',
                trigger: 'blur'
              }],
              phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
              address: [{required: true, message: '请输入员工地址', trigger: 'blur'}],
              departmentId: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
              jobLevelId: [{required: true, message: '请输入职称', trigger: 'blur'}],
              posId: [{required: true, message: '请输入职位', trigger: 'blur'}],
              engageForm: [{required: true, message: '请输入聘用形式', trigger: 'blur'}],
              tiptopDegree: [{required: true, message: '请输入学历', trigger: 'blur'}],
              specialty: [{required: true, message: '请输入专业', trigger: 'blur'}],
              school: [{required: true, message: '请输入毕业院校', trigger: 'blur'}],
              beginDate: [{required: true, message: '请输入入职日期', trigger: 'blur'}],
              workState: [{required: true, message: '请输入工作状态', trigger: 'blur'}],
              workID: [{required: true, message: '请输入工号', trigger: 'blur'}],
              contractTerm: [{required: true, message: '请输入合同期限', trigger: 'blur'}],
              conversionTime: [{required: true, message: '请输入转正日期', trigger: 'blur'}],
              notworkDate: [{required: true, message: '请输入离职日期', trigger: 'blur'}],
              beginContract: [{required: true, message: '请输入合同起始日期', trigger: 'blur'}],
              endContract: [{required: true, message: '请输入合同结束日期', trigger: 'blur'}],
              workAge: [{required: true, message: '请输入工龄', trigger: 'blur'}],
            }
          }
      },
      methods: {
        /*导出员工数据*/
        exportData() {
          window.open('/employee/basic/export/emp_info', '_parent');
        },
        /*双击行记录，打开编辑弹框*/
        tableDbEdit(row, column, cell, event) {
          this.showEmpEditDialog(row);
        },

        /*清空emp*/
        /*因为先进入编辑后，emp有数据，然后再进入添加emp需要清除掉信息*/
        setEmpEmpty(){
          this.emp = {
            name: '',
            gender: '',
            birthday: '',
            idCard: '',
            wedlock: '',
            nationId: '',
            nativePlace: '',
            politicId: '',
            email: '',
            phone: '',
            address: '',
            departmentId: '',
            jobLevelId: '',
            posId: '',
            engageForm: '',
            tiptopDegree: '',
            specialty: '',
            school: '',
            beginDate: '',
            workState: '在职',
            workID: '',
            contractTerm: '',
            conversionTime: '',
            notWorkDate: null,
            beginContract: '',
            endContract: '',
            workAge: '',
            salary: null
          }
        },
          /*添加或者修改员工方法*/
          addOrUpdateEmp(){
              if (this.emp.id) {
                // 有id就是修改
                this.$refs.empForm.validate((valid) => {
                  if (valid) {
                    putRequest('/employee/basic/', this.emp).then(resp => {
                      if (resp) {
                        this.empDialogVisible = false;
                        this.initEmps();
                      }
                    })
                  }
                })
              } else {
                // 无id就是新增
              this.$refs.empForm.validate((valid) => {
                if (valid) {
                  postRequest('/employee/basic/', this.emp).then(resp => {
                    if (resp) {
                      this.empDialogVisible = false;
                      this.initEmps();
                    }
                  })
                }
              })
            }
          },
          /*添加员工 - 加载下拉框数据*/
          initSelectionData(){
              // 从sessionStorage里拿下拉框数据，如果从sessionStorage里拿不到数据，则重新调用接口获取数据
              if (!window.sessionStorage.getItem("nations")) {
                  getRequest('/employee/basic/nations').then(resp => {
                      if (resp) {
                          this.nations = resp;
                      }
                  })
              } else {
                  // 从sessionStorage里有数据的，就从从sessionStorage里赋值给表单变量
                  this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
              }
              if (!window.sessionStorage.getItem("politicsstatus")) {
                  getRequest('/employee/basic/politicsstatus').then(resp => {
                      if (resp) {
                          this.politicsstatus = resp;
                      }
                  })
              } else {
                  this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"));
              }
              if (!window.sessionStorage.getItem("joblevels")) {
                  getRequest('/employee/basic/joblevels').then(resp => {
                      if (resp) {
                          this.joblevels = resp;
                      }
                  })
              } else {
                  this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
              }
              if (!window.sessionStorage.getItem("deps")) {
                  getRequest('/employee/basic/deps').then(resp => {
                      if (resp) {
                          this.departmentTree = resp;
                      }
                  })
              } else {
                  this.departmentTree = JSON.parse(window.sessionStorage.getItem("deps"));
              }
          },
          /*初始化职位下拉框数据，在弹出添加对话框时调用*/
          initPositions(){
              getRequest('/employee/basic/positions').then(resp => {
                  if (resp) {
                      this.positions = resp;
                  }
              })
          },
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
              getRequest('/employee/basic/?page=' + this.page + '&size=' + this.size + '&keyword=' + this.keyword).then(resp => {
                  this.loading = false;
                  if (resp) {
                      this.emps = resp.data;
                      this.total = resp.total;
                  }
              })
          },
          /*弹出添加用户对话框*/
          showEmpAddDialog() {
              /*添加之前要清空emp对象*/
              this.setEmpEmpty();
              this.initPositions();
              this.title = '添加员工';
              this.empDialogVisible = true;
              this.initPositions();
              this.inputDepName = '';
          },
          /*弹出修改员工对话框*/
          showEmpEditDialog(data) {
              this.title = '修改员工信息';
              this.initPositions();
              this.empDialogVisible = true;
              this.emp = data;
              this.inputDepName = data.department.name;
          },
          /*生成工号*/
          generateWorkID(){
              getRequest('/employee/basic/maxWorkID').then(resp => {
                  this.loading = false;
                  if (resp) {
                      this.emp.workID = resp.obj;
                  }
              })
          },
          /*弹出部门树弹框*/
          showDepView(){
              this.departmentVisable = !this.departmentVisable
          },
          /*点击部门书*/
          handleNodeClick(data) {
              this.inputDepName = data.name;
              this.emp.departmentId = data.id;
              this.departmentVisable = !this.departmentVisable;
          },
          /*删除员工*/
          deleteEmp(data){
            this.$confirm('此操作将永久删除【' +data.name+ '】员工, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              deleteRequest('/employee/basic/' + data.id).then(resp => {
                this.initEmps();
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });
          }
      },
      mounted() {
          this.initEmps();
          /*加载添加员工页面下拉框数据*/
          this.initSelectionData();
          /*打开弹框就显示工号*/
          this.generateWorkID();
      }
    }
</script>

<style scoped>

</style>