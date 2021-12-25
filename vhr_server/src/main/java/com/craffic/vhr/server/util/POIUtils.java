package com.craffic.vhr.server.util;

import com.craffic.vhr.server.domain.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {

    public static ResponseEntity<byte[]> exportEmpData(List<Employee> employeeList) {
        // 1. 创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2. 创建文档摘要
        workbook.createInformationProperties();
        // 3. 获取并配置文档摘要信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        // 3.1 文档类别
        docInfo.setCategory("员工信息");
        // 3.2 文档管理员
        docInfo.setManager("Craffic");
        // 3.3 设置公司信息
        docInfo.setCompany("com.Craffic.vhr");
        // 4. 获取文档摘要信息
        SummaryInformation summaryInformation = workbook.getSummaryInformation();
        summaryInformation.setTitle("员工信息表");
        summaryInformation.setAuthor("Craffic");
        summaryInformation.setComments("vhr管理系统导出员工信息");
        // 5. 创建sheet
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        // 6. 创建标题行，列
        HSSFRow headerRow = sheet.createRow(0);
        HSSFCell cell0 = headerRow.createCell(0);
        HSSFCell cell1 = headerRow.createCell(1);
        HSSFCell cell2 = headerRow.createCell(2);
        HSSFCell cell3 = headerRow.createCell(3);
        HSSFCell cell4 = headerRow.createCell(4);
        HSSFCell cell5 = headerRow.createCell(5);
        HSSFCell cell6 = headerRow.createCell(6);
        HSSFCell cell7 = headerRow.createCell(7);
        HSSFCell cell8 = headerRow.createCell(8);
        HSSFCell cell9 = headerRow.createCell(9);
        HSSFCell cell10 = headerRow.createCell(10);
        HSSFCell cell11 = headerRow.createCell(11);
        HSSFCell cell12 = headerRow.createCell(12);
        HSSFCell cell13 = headerRow.createCell(13);
        HSSFCell cell14 = headerRow.createCell(14);
        HSSFCell cell15 = headerRow.createCell(15);
        HSSFCell cell16 = headerRow.createCell(16);
        HSSFCell cell17 = headerRow.createCell(17);
        HSSFCell cell18 = headerRow.createCell(18);
        HSSFCell cell19 = headerRow.createCell(19);
        HSSFCell cell20 = headerRow.createCell(20);
        HSSFCell cell21 = headerRow.createCell(21);
        HSSFCell cell22 = headerRow.createCell(22);
        HSSFCell cell23 = headerRow.createCell(23);
        HSSFCell cell24 = headerRow.createCell(24);
        HSSFCell cell25 = headerRow.createCell(25);
        HSSFCell cell26 = headerRow.createCell(26);
        // 6.1 创建标题样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 6.2 填充样式
        cell0.setCellValue("编号");
        cell1.setCellValue("姓名");
        cell2.setCellValue("工号");
        cell3.setCellValue("性别");
        cell4.setCellValue("出生日期");
        cell5.setCellValue("身份证号码");
        cell6.setCellValue("婚姻状况");
        cell7.setCellValue("民族");
        cell8.setCellValue("籍贯");
        cell9.setCellValue("政治面貌");
        cell10.setCellValue("电子邮箱");
        cell11.setCellValue("电话号码");
        cell12.setCellValue("联系地址");
        cell13.setCellValue("所属部门");
        cell14.setCellValue("职位");
        cell15.setCellValue("职称");
        cell16.setCellValue("聘用形式");
        cell17.setCellValue("最高学历");
        cell18.setCellValue("专业");
        cell19.setCellValue("毕业院校");
        cell20.setCellValue("入职日期");
        cell21.setCellValue("转正日期");
        cell22.setCellValue("在职状态");
        cell23.setCellValue("合同起始日期");
        cell24.setCellValue("合同终止日期");
        cell25.setCellValue("合同期限（年）");
        cell0.setCellStyle(headerStyle);
        cell1.setCellStyle(headerStyle);
        cell2.setCellStyle(headerStyle);
        cell3.setCellStyle(headerStyle);
        cell4.setCellStyle(headerStyle);
        cell5.setCellStyle(headerStyle);
        cell6.setCellStyle(headerStyle);
        cell7.setCellStyle(headerStyle);
        cell8.setCellStyle(headerStyle);
        cell9.setCellStyle(headerStyle);
        cell10.setCellStyle(headerStyle);
        cell11.setCellStyle(headerStyle);
        cell12.setCellStyle(headerStyle);
        cell13.setCellStyle(headerStyle);
        cell14.setCellStyle(headerStyle);
        cell15.setCellStyle(headerStyle);
        cell16.setCellStyle(headerStyle);
        cell17.setCellStyle(headerStyle);
        cell18.setCellStyle(headerStyle);
        cell19.setCellStyle(headerStyle);
        cell20.setCellStyle(headerStyle);
        cell21.setCellStyle(headerStyle);
        cell22.setCellStyle(headerStyle);
        cell23.setCellStyle(headerStyle);
        cell24.setCellStyle(headerStyle);
        cell25.setCellStyle(headerStyle);
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 30 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 20 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 12 * 256);
        sheet.setColumnWidth(17, 10 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 20 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        // 遍历empList
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getWorkID());
            row.createCell(3).setCellValue(employee.getGender());
            row.createCell(4).setCellValue(employee.getBirthday());
            row.createCell(5).setCellValue(employee.getIdCard());
            row.createCell(6).setCellValue(employee.getWedlock());
            row.createCell(7).setCellValue(employee.getNation().getName());
            row.createCell(8).setCellValue(employee.getNativePlace());
            row.createCell(9).setCellValue(employee.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(employee.getEmail());
            row.createCell(11).setCellValue(employee.getPhone());
            row.createCell(12).setCellValue(employee.getAddress());
            row.createCell(13).setCellValue(employee.getDepartment().getName());
            row.createCell(14).setCellValue(employee.getPosition().getName());
            row.createCell(15).setCellValue(employee.getJobLevel().getName());
            row.createCell(16).setCellValue(employee.getEngageForm());
            row.createCell(17).setCellValue(employee.getTiptopDegree());
            row.createCell(18).setCellValue(employee.getSpecialty());
            row.createCell(19).setCellValue(employee.getSchool());
            row.createCell(20).setCellValue(employee.getBeginDate());
            row.createCell(21).setCellValue(employee.getConversionTime());
            row.createCell(22).setCellValue(employee.getWorkState());
            row.createCell(23).setCellValue(employee.getBeginContract());
            row.createCell(24).setCellValue(employee.getEndContract());
            Double contractTerm = employee.getContractTerm();
            row.createCell(25).setCellValue(contractTerm == null ? 0D : contractTerm);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工信息表.xls".getBytes(StandardCharsets.UTF_8), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<Employee> excel2Employee(MultipartFile file, List<Nation> allNations, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        if (cell == null){
                            System.out.println(cell);
                        }
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        employee.setName(cellValue);
                                        break;
                                    case 2:
                                        employee.setWorkID(cellValue);
                                        break;
                                    case 3:
                                        employee.setGender(cellValue);
                                        break;
                                    case 5:
                                        employee.setIdCard(cellValue);
                                        break;
                                    case 6:
                                        employee.setWedlock(cellValue);
                                        break;
                                    case 7:
                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
                                        employee.setNationId(allNations.get(nationIndex).getId());
                                        break;
                                    case 8:
                                        employee.setNativePlace(cellValue);
                                        break;
                                    case 9:
                                        int politicstatusIndex = allPoliticsstatus.indexOf(new Politicsstatus(cellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicstatusIndex).getId());
                                        break;
                                    case 10:
                                        employee.setEmail(cellValue);
                                        break;
                                    case 11:
                                        employee.setPhone(cellValue);
                                        break;
                                    case 12:
                                        employee.setAddress(cellValue);
                                        break;
                                    case 13:
                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
                                        employee.setDepartmentId(allDepartments.get(departmentIndex).getId());
                                        break;
                                    case 14:
                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());
                                        break;
                                    case 15:
                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
                                        employee.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());
                                        break;
                                    case 16:
                                        employee.setEngageForm(cellValue);
                                        break;
                                    case 17:
                                        employee.setTiptopDegree(cellValue);
                                        break;
                                    case 18:
                                        employee.setSpecialty(cellValue);
                                        break;
                                    case 19:
                                        employee.setSchool(cellValue);
                                        break;
                                    case 22:
                                        employee.setWorkState(cellValue);
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;
                                    case 20:
                                        employee.setBeginDate(cell.getDateCellValue());
                                        break;
                                    case 21:
                                        employee.setConversionTime(cell.getDateCellValue());
                                        break;
                                    case 23:
                                        employee.setBeginContract(cell.getDateCellValue());
                                        break;
                                    case 24:
                                        employee.setEndContract(cell.getDateCellValue());
                                        break;
                                    case 25:
                                        employee.setContractTerm(cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(employee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
