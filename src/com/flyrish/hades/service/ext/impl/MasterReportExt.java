package com.flyrish.hades.service.ext.impl;

import java.io.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi2.hssf.usermodel.HSSFCell;
import org.apache.poi2.hssf.usermodel.HSSFCellStyle;
import org.apache.poi2.hssf.usermodel.HSSFFont;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;
import org.apache.poi2.hssf.util.Region;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.DataCountDto;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.RstudentDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SelfDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.util.DataSource;
import com.flyrish.hades.util.ExcelReader;
import com.flyrish.hades.util.NoServiceUtil;

public class MasterReportExt extends JdbcRootManager implements IMasterReportExt {

    @DataSource("read")
    public List<StudentDto> queryStudentBaseInfos(Map<String, Object> params) {
        try {
            return this.findList("IMasterReportExt.queryStudentBaseInfos.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    StudentDto sDto = new StudentDto();
                    sDto.setStudentid(rs.getString("studentid"));
                    sDto.setEduid(rs.getString("edu_id"));
                    sDto.setStudentno(rs.getString("studentno"));
                    sDto.setName(rs.getString("studentName"));
                    sDto.setSexname(rs.getString("sex"));
                    sDto.setAge(NestUtil.isNotEmpty(rs.getString("age")) ? Integer.parseInt(rs.getString("age")) : 0);
                    sDto.setEasyName(rs.getString("polityName"));
                    sDto.setGradenum(rs.getString("gradename"));
                    sDto.setClassname(rs.getString("classsname"));
                    sDto.setSchoolName(rs.getString("schoolname"));
                    sDto.setInschoolid(rs.getString("nummber"));
                    return sDto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryStudentBaseInfos(Map<String,Object>)", e);
        }
        return null;
    }

    @DataSource("read")
    public List<CampusDto> queryTeacherInfos(Map<String, Object> params) {
        try {
            //班主任课程评价信息
            return this.findList("IMasterReportExt.queryTeacherInfos.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    CampusDto dto = new CampusDto();
                    dto.setLevelId(rs.getString("levelid"));
                    dto.setLevelName(rs.getString("levelname"));
                    dto.setLevelNum(rs.getString("levelnum"));
                    dto.setGradeId(rs.getString("gradeid"));
                    dto.setGradeName(rs.getString("gradename"));
                    dto.setGradeNum(rs.getString("gradenum"));
                    dto.setClassId(rs.getString("classid"));
                    dto.setClassName(rs.getString("classsname"));
                    dto.setClassNum(rs.getString("classnum"));
                    dto.setTeacherName(rs.getString("name"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryTeacherInfos", e);
        }
        return null;
    }

    @DataSource("read")
    public List<AppraisalDto> queryPersonalAppral(Map<String, Object> params) {
        try {
            //获取个性发展数据哦
            return this.findList("IMasterReportExt.queryPersonalAppral.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    AppraisalDto dto = new AppraisalDto();
                    dto.setEduid(rs.getString("edu_id"));
                    dto.setSemesterid(rs.getInt("semesterid"));
                    dto.setApprasial(replaceEsc(rs.getString("apprasial")));
                    dto.setAppraisaltype(rs.getString("appraisaltypeid"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryPersonalAppral(Map<String, Object>)", e);
        }
        return null;
    }

    /**
     * 替换转义字符
     *
     * @param content 内容
     * @return
     */
    private String replaceEsc(String content) {
        if (NestUtil.isNotEmpty(content)) {
            content = content.replace("&lt;", "<").replace("&gt;", ">")
                    .replace("&amp;", "&").replaceAll("&quot;", "\"")
                    .replace("&times;", "×").replace("&divide;", "÷");
        }
        return content;
    }

    @DataSource("read")
    public List<AppraisalDto> queryStudyAppral(Map<String, Object> params) {
        try {
            //获取个性发展数据哦
            return this.findList("IMasterReportExt.queryStudyAppral.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    AppraisalDto dto = new AppraisalDto();
                    dto.setEduid(rs.getString("eduId"));
                    dto.setSemesterid(rs.getInt("termId"));
                    dto.setApprasial(replaceEsc(rs.getString("contents")));
                    dto.setTopic(replaceEsc(rs.getString("topic")));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryPersonalAppral(Map<String, Object>)", e);
        }
        return null;
    }

    public List<AppraisalDto> initSinglePersonalSelf(StudentDto sDto, List<AppraisalDto> personalAppral, String fag) {
        if (null != personalAppral && personalAppral.size() > 0) {
            List<AppraisalDto> personalAppraise = new ArrayList<AppraisalDto>();
            for (AppraisalDto aDto : personalAppral) {
                if (NestUtil.isNotEmpty(sDto.getEduid())//自我评价
                        && sDto.getEduid().equals(aDto.getEduid())
                        && "selef".equals(fag)
                        && Constant.TYPE_GXFZ_ZWPJ.equals(aDto.getAppraisaltype())) {
                    personalAppraise.add(aDto);
                } else if (NestUtil.isNotEmpty(sDto.getEduid())//成果与展现
                        && sDto.getEduid().equals(aDto.getEduid())
                        && "".equals(fag)
                        && Constant.TYPE_GXFZ_CGZS.equals(aDto.getAppraisaltype())) {
                    personalAppraise.add(aDto);
                }
            }
            return personalAppraise;
        }
        return null;
    }

    public List<AppraisalDto> initsingleStudyAppral(StudentDto sDto, List<AppraisalDto> studyAppral) {
        if (null != studyAppral && studyAppral.size() > 0) {
            List<AppraisalDto> studys = new ArrayList<AppraisalDto>();
            for (AppraisalDto aDto : studyAppral) {
                if (NestUtil.isNotEmpty(sDto.getEduid())
                        && sDto.getEduid().equals(aDto.getEduid())) {
                    studys.add(aDto);
                }
            }
            return studys;
        }
        return null;
    }

    public void writeAppraiseData(String excelFileUrl, String fileName, List<AppraisalDto> personalAppralSelf, List<AppraisalDto> personalAppralExtra, List<AppraisalDto> singleStudyAppral) {

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            //个性与发展自我评价
            HSSFSheet sheet0 = wb.createSheet();
            sheet0.setColumnWidth((short) 0, (short) 2000);
            sheet0.setColumnWidth((short) 1, (short) 99000);
            wb.setSheetName(0, "个性与发展自我评价", HSSFWorkbook.ENCODING_UTF_16);
            //个性与发展特长及成果展示
            HSSFSheet sheet1 = wb.createSheet();
            sheet1.setColumnWidth((short) 0, (short) 2000);
            sheet1.setColumnWidth((short) 1, (short) 99000);
            wb.setSheetName(1, "个性与发展特长及成果展示", HSSFWorkbook.ENCODING_UTF_16);
            //研究性学习
            HSSFSheet sheet2 = wb.createSheet();
            sheet2.setColumnWidth((short) 0, (short) 2000);
            sheet2.setColumnWidth((short) 1, (short) 9000);
            sheet2.setColumnWidth((short) 2, (short) 25000);
            wb.setSheetName(2, "研究性学习", HSSFWorkbook.ENCODING_UTF_16);

            //个性与发展自我评价—表头
            String[] headOfsheet0 = {"序号", "个性与发展自我评价"};
            //个性与发展特长及成果展示—表头
            String[] headOfsheet1 = {"序号", "特长与成果"};
            //研究性学习—表头
            String[] headOfsheet2 = {"序号", "标题", "研究性内容摘要"};

            //输出表头—个性与发展自我评价
            HSSFRow firstRowOfSheet0 = sheet0.createRow(0);
            for (int i = 0; i < headOfsheet0.length; i++) {
                HSSFCell cell = firstRowOfSheet0.createCell((short) i);
                setCellStyle(cell, HSSFCellStyle.ALIGN_CENTER);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(headOfsheet0[i]);
            }
            //输出表头—个性与发展特长及成果展示
            HSSFRow firstRowOfSheet1 = sheet1.createRow(0);
            for (int i = 0; i < headOfsheet1.length; i++) {
                HSSFCell cell = firstRowOfSheet1.createCell((short) i);
                setCellStyle(cell, HSSFCellStyle.ALIGN_CENTER);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(headOfsheet1[i]);
            }
            //输出表头—研究性学习
            HSSFRow firstRowOfSheet2 = sheet2.createRow(0);
            for (int i = 0; i < headOfsheet2.length; i++) {
                HSSFCell cell = firstRowOfSheet2.createCell((short) i);
                setCellStyle(cell, HSSFCellStyle.ALIGN_CENTER);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(headOfsheet2[i]);
            }

            //输出个性与发展自我评价
            if (personalAppralSelf != null && personalAppralSelf.size() > 0) {
                for (int i = 0; i < personalAppralSelf.size(); i++) {
                    AppraisalDto dto = personalAppralSelf.get(i);
                    HSSFRow row = sheet0.createRow(i + 1);
                    HSSFCell cell = row.createCell((short) (0));
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(i + 1 + "");
                    HSSFCell cell1 = row.createCell((short) (1));
                    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
                    if (StringUtil.isEmpty(dto.getApprasial())) {
                        cell1.setCellValue("");
                    } else {
                        cell1.setCellValue("" + change0D0A(dto.getApprasial()));
                    }
                }
            }
            //输出个性与发展特长及成果展示
            if (personalAppralExtra != null && personalAppralExtra.size() > 0) {
                for (int i = 0; i < personalAppralExtra.size(); i++) {
                    AppraisalDto dto = personalAppralExtra.get(i);
                    HSSFRow row = sheet1.createRow(i + 1);
                    HSSFCell cell = row.createCell((short) (0));
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(i + 1 + "");
                    HSSFCell cell1 = row.createCell((short) (1));
                    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
                    if (StringUtil.isEmpty(dto.getApprasial())) {
                        cell1.setCellValue("");
                    } else {
                        cell1.setCellValue("" + change0D0A(dto.getApprasial()));
                    }
                }
            }
            //输出研究性学习
            if (singleStudyAppral != null && singleStudyAppral.size() > 0) {
                for (int i = 0; i < singleStudyAppral.size(); i++) {
                    AppraisalDto dto = singleStudyAppral.get(i);
                    HSSFRow row = sheet2.createRow(i + 1);
                    HSSFCell cell = row.createCell((short) (0));
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(i + 1 + "");
                    HSSFCell cell1 = row.createCell((short) (1));
                    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
                    if (StringUtil.isEmpty(dto.getTopic())) {
                        cell1.setCellValue("");
                    } else {
                        cell1.setCellValue("" + dto.getTopic());
                    }
                    HSSFCell cell2 = row.createCell((short) (2));
                    cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
                    if (StringUtil.isEmpty(dto.getApprasial())) {
                        cell2.setCellValue("");
                    } else {
                        cell2.setCellValue("" + change0D0A(dto.getApprasial()));
                    }
                }
            }

            createExcel(wb, excelFileUrl, fileName);

        } catch (Exception e) {
            logger.error("写入评价数据错误writeData(String ,String ,List<AppraisalAndStudentDto> ,List<PersonalityDevelop> ,List<Practices> )："
                    + e.getMessage() + e.fillInStackTrace());
            throw new ManagerException();
        }
    }

    private void setCellStyle(HSSFCell cell, short algin) {
        HSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(algin);//左右居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        //设置自动换行
        cellStyle.setWrapText(true);
        cell.setCellStyle(cellStyle);
    }

    private void createExcel(HSSFWorkbook wb, String excelFileUrl, String fileName) throws ManagerException {
        FileOutputStream outputStream = null;
        try {
            File file = new File(excelFileUrl);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(excelFileUrl + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.error("生成评价数据excel文件错误createExcel((HSSFWorkbook ,String ,String ))：" + e.getMessage() + e.fillInStackTrace());
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e1) {
                    throw new ManagerException();
                }
            }
            throw new ManagerException();
        }
    }

    private String change0D0A(String str) {
        String ret = "";
        if (StringUtil.isEmpty(str)) {
            return ret;
        }
        //str = str.trim();
        str = str.replaceAll("\r\n", "\n");
        ret = str;
        return ret;
    }

    private HSSFCell getHSSFCell(HSSFSheet sheet, int row, int column, String cellValue) {
        HSSFCell cell = sheet.getRow(row).getCell((short) column);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        if (StringUtil.isNotEmpty(cellValue)) {
            cell.setCellValue(change0D0A(cellValue));
        } else {
            cell.setCellValue("");
        }
        return cell;
    }

    @Override
    public void writeReportHtml(File fileBGD, StudentDto sDto, List<AppraisalDto> masterAppral, AppraisalDto outSinglePersonalAppraiseSelf, List<AppraisalDto> outSinglePersonalAppraiseExtra, List<AppraisalDto> outSingleStudyAppral, List<HealthDataDto> singleHealthData, List<CheckItemInfoDto> sigleCheckItems, Map<String, Map<String, List<ModelScoreDto>>> singleScore, String flag, Map<String, String> hkScore) {
        FileInputStream inputs = null;
        String str = "";
        HSSFWorkbook wb = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputs = new FileInputStream(fileBGD);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputs));
            String tempStr = "";
            while ((tempStr = br.readLine()) != null) {
                str = str + tempStr;
            }
            str = str.replaceAll("###name###", sDto.getName());
            str = str.replaceAll("###sex###", sDto.getSexname());
            str = str.replaceAll("###political###", sDto.getEasyName());
            str = str.replaceAll("###schoolNo###", sDto.getStudentno());
            str = str.replaceAll("###biyexuexiao###", sDto.getSchoolName());
            if (outSinglePersonalAppraiseSelf != null) {
                str = str.replaceAll("###ziwopingjia###", outSinglePersonalAppraiseSelf.getApprasial());
            }else {
                str = str.replaceAll("###ziwopingjia###", "");

            }
            if (outSinglePersonalAppraiseExtra!=null) {
                if (outSinglePersonalAppraiseExtra.get(0) != null) {
                    str = str.replaceAll("###chengguo1###", outSinglePersonalAppraiseExtra.get(0).getApprasial());
                }else {
                    str = str.replaceAll("###chengguo1###", "");
                }
                if (outSinglePersonalAppraiseExtra.get(1) != null) {
                    str = str.replaceAll("###chengguo2###", outSinglePersonalAppraiseExtra.get(1).getApprasial());
                }else {
                    str = str.replaceAll("###chengguo2###", "");
                }
                if (outSinglePersonalAppraiseExtra.get(2) != null) {
                    str = str.replaceAll("###chengguo3###", outSinglePersonalAppraiseExtra.get(2).getApprasial());
                }else{
                    str = str.replaceAll("###chengguo3###", "");

                }
            }else {
                str = str.replaceAll("###chengguo1###", "");
                str = str.replaceAll("###chengguo2###", "");
                str = str.replaceAll("###chengguo3###", "");
            }
            if (masterAppral != null && masterAppral.size() > 0) {
                for (AppraisalDto aDto : masterAppral) {
                    if ("1".equals(aDto.getGradeNum())) {
                        str = str.replaceAll("###pingyu1###", aDto.getApprasial());
                    } else if ("2".equals(aDto.getGradeNum())) {
                        str = str.replaceAll("###pingyu2###", aDto.getApprasial());
                    } else if ("3".equals(aDto.getGradeNum())) {
                        str = str.replaceAll("###pingyu3###", aDto.getApprasial());
                    }
                }
            }
            if (outSingleStudyAppral != null && outSingleStudyAppral.size() > 0) {
                str = str.replaceAll("###biaoti1###", outSingleStudyAppral.get(0).getTopic());
                str = str.replaceAll("###neirongzhaiyao1###", outSingleStudyAppral.get(0).getApprasial());
                if (outSingleStudyAppral.get(1) != null) {
                    str = str.replaceAll("###biaoti2###", outSingleStudyAppral.get(1).getTopic());
                    str = str.replaceAll("###neirongzhaiyao2###", outSingleStudyAppral.get(1).getApprasial());
                }
                if (outSingleStudyAppral.get(2) != null) {
                    str = str.replaceAll("###biaoti3###", outSingleStudyAppral.get(2).getTopic());
                    str = str.replaceAll("###neirongzhaiyao3###", outSingleStudyAppral.get(2).getApprasial());
                }
            }
            //封面
            //this.fillCoverSheet(sDto, wb);
            //个性发展
            //this.fillPersonalAppraiseSheet(outSinglePersonalAppraiseSelf,outSinglePersonalAppraiseExtra, wb);
            //评语
            //this.fillMasterAppraiseSheet(masterAppral, wb);
            //成绩学分
            this.fillScoreHtml(singleScore, str,sDto,hkScore);
            //创建学生体质健康数据明细
            this.fillHealthDataHtml(singleHealthData, str);
            //创建北京市中学生健康体检表
            this.fillCheckItemsHtml(sigleCheckItems, str);
            //研究性学习
            //this.fillStudyAppraiseSheet(outSingleStudyAppral, wb);
            //fileOutputStream = new FileOutputStream(fileBGD);
            BufferedWriter o = new BufferedWriter(new FileWriter(fileBGD));
            o.write(str);
            o.flush();
            //wb.write(fileOutputStream);
            //fileOutputStream.write(str.getBytes());
            inputs.close();
            //fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入报告单数据错误writeReportCardExcel(String ,Student ,SelfDto ,List<SelfDto> ,List<HealthDataDto> ,List<SelfDto> ,List<AppraisalDto> ,List<CheckItemInfoDto> ,File )："
                    + e.getMessage());
            if (inputs != null) {
                try {
                    inputs.close();
                } catch (IOException e1) {
                    throw new ManagerException();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    throw new ManagerException();
                }
            }
            throw new ManagerException();
        }
    }

    @Override
    public void writeReportExcel(File fileBGD, StudentDto sDto,
                                 List<AppraisalDto> masterAppral,
                                 AppraisalDto outSinglePersonalAppraiseSelf,
                                 List<AppraisalDto> outSinglePersonalAppraiseExtra,
                                 List<AppraisalDto> outSingleStudyAppral,
                                 List<HealthDataDto> singleHealthData,
                                 List<CheckItemInfoDto> sigleCheckItems,
                                 Map<String, Map<String, List<ModelScoreDto>>> singleScore, String flag, Map<String, String> hkScore) {

        FileInputStream inputs = null;
        HSSFWorkbook wb = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputs = new FileInputStream(fileBGD);
            wb = new HSSFWorkbook(inputs);
            this.initExcelStyle(wb);
            //如果是导出报告单 删除书名
            this.deleteDirection(wb, flag);
            //封面
            this.fillCoverSheet(sDto, wb);
            //个性发展
            this.fillPersonalAppraiseSheet(outSinglePersonalAppraiseSelf, outSinglePersonalAppraiseExtra, wb);
            //评语
            this.fillMasterAppraiseSheet(masterAppral, wb);
            //成绩学分
            this.fillScoreSheet(singleScore, wb, sDto, hkScore);
            //创建学生体质健康数据明细
            this.fillHealthDataShhet(singleHealthData, wb);
            //创建北京市中学生健康体检表
            this.fillCheckItems(sigleCheckItems, wb);
            //研究性学习
            this.fillStudyAppraiseSheet(outSingleStudyAppral, wb);
            fileOutputStream = new FileOutputStream(fileBGD);
            wb.write(fileOutputStream);
            inputs.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入报告单数据错误writeReportCardExcel(String ,Student ,SelfDto ,List<SelfDto> ,List<HealthDataDto> ,List<SelfDto> ,List<AppraisalDto> ,List<CheckItemInfoDto> ,File )："
                    + e.getMessage());
            if (inputs != null) {
                try {
                    inputs.close();
                } catch (IOException e1) {
                    throw new ManagerException();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    throw new ManagerException();
                }
            }
            throw new ManagerException();
        }
    }

    //获取会考成绩
    private String getSunjectHKScore(String eduId, String subject, Map<String, String> hkScore) {
        if (null != hkScore && hkScore.size() > 0) {
            return hkScore.get(eduId + "_" + subject);
        }
        return "";
    }

    private void fillScoreSheet(Map<String, Map<String, List<ModelScoreDto>>> singleScore, HSSFWorkbook wb, StudentDto sDto, Map<String, String> hkScore) {
        HSSFSheet sheetGP11 = wb.getSheet("成绩学分登记表");//还需要样式调整 TODO
        totalCreditHour1 = 0;
        totalCreditHour2 = 0;
        totalCreditHour3 = 0;
        allTotalCreditHour = 0;
        xxTotalCreditHour = 0;
        bxTotalCreditHour = 0;
        xbTotalCreditHour = 0;
        maxRowNum = 6;
        if (singleScore != null && singleScore.size() > 0) {
            List<String> subjects = this.initSubjects();
            for (int i = 0; i < subjects.size(); i++) {
                Map<String, List<ModelScoreDto>> scores = singleScore.get(subjects.get(i));
                if (null != scores && scores.size() > 0) {
                    if ("校本课程".equals(subjects.get(i))) {
                        //System.out.println(singleScore.get(subjects.get(i)));
                    }
                    this.createSubjectRow(scores, sheetGP11, subjects.get(i), maxRowNum + 1, i + 1, hkScore, sDto);
                } else {
                    if (i + 1 == 3 || i + 1 == 7 || i + 1 == 8 || i + 1 == 9) {
                        if (i + 1 == 3) {//英语
                            this.fillSpecailData(sheetGP11, i, "1", "2");
                            sheetGP11.addMergedRegion(new Region(maxRowNum - 1, (short) 0, maxRowNum, (short) 0));
                            getHSSFCell(sheetGP11, maxRowNum - 1, 0, "外语");
                            this.fillHKscore(sDto, hkScore, sheetGP11, subjects.get(i), maxRowNum - 1, maxRowNum, (short) 29);
                        } else {//生物、物理、化学
                            this.fillSpecailData(sheetGP11, i, "课程", "实验");
                            sheetGP11.addMergedRegion(new Region(maxRowNum - 1, (short) 0, maxRowNum, (short) 0));
                            getHSSFCell(sheetGP11, maxRowNum - 1, 0, subjects.get(i));
                            this.fillHKscore(sDto, hkScore, sheetGP11, subjects.get(i), maxRowNum - 1, maxRowNum, (short) 29);
                        }
                    } else {
                        HSSFRow row = sheetGP11.getRow(maxRowNum + 1);
                        if (null == row) {
                            row = sheetGP11.createRow(maxRowNum + 1);
                            this.createCells(row, i + 1);
                        }
                        sheetGP11.addMergedRegion(new Region(maxRowNum + 1, (short) 0, maxRowNum + 1, (short) 1));
                        getHSSFCell(sheetGP11, maxRowNum + 1, 0, subjects.get(i));
                        this.fillHKscore(sDto, hkScore, sheetGP11, subjects.get(i), maxRowNum + 1, maxRowNum + 1, (short) 29);
                        maxRowNum += 1;
                    }
                }
            }
        }
        this.endExcel(sheetGP11, sDto);
    }private void fillScoreHtml(Map<String, Map<String, List<ModelScoreDto>>> singleScore, String str, StudentDto sDto, Map<String, String> hkScore) {
        totalCreditHour1 = 0;
        totalCreditHour2 = 0;
        totalCreditHour3 = 0;
        allTotalCreditHour = 0;
        xxTotalCreditHour = 0;
        bxTotalCreditHour = 0;
        xbTotalCreditHour = 0;
        maxRowNum = 6;
        if (singleScore != null && singleScore.size() > 0) {
            List<String> subjects = this.initSubjects();
            for (int i = 0; i < subjects.size(); i++) {
                Map<String, List<ModelScoreDto>> scores = singleScore.get(subjects.get(i));
                if (null != scores && scores.size() > 0) {
                    this.createSubjectRowHtml(scores, str, subjects.get(i), hkScore, sDto);
                }
            }
        }
        this.endExcelHtml(str, sDto);
    }

    private void fillHKscore(StudentDto sDto, Map<String, String> hkScore,
                             HSSFSheet sheetGP11, String subject, int j, int h,
                             int cellNum) {
        sheetGP11.addMergedRegion(new Region(j, (short) 29, h, (short) cellNum));
        getHSSFCell(sheetGP11, j, cellNum, this.getSunjectHKScore(sDto.getEduid(), subject, hkScore));
    }

    private void fillSpecailData(HSSFSheet sheetGP11, int i, String arg1, String arg2) {
        for (int j = 0; j < 2; j++) {
            HSSFRow row = sheetGP11.getRow(maxRowNum + 1);
            if (null == row) {
                row = sheetGP11.createRow(maxRowNum + 1);
                this.createCells(row, i + 1);
            }
            if (j == 0) {
                getHSSFCell(sheetGP11, maxRowNum + 1, 1, arg1);
            } else {
                getHSSFCell(sheetGP11, maxRowNum + 1, 1, arg2);
            }
            maxRowNum += 1;
        }
    }

    private void endExcel(HSSFSheet sheetGP11, StudentDto sDto) {
        //姓名等信息填写
        getHSSFCell(sheetGP11, 1, 2, sDto.getName());
        getHSSFCell(sheetGP11, 1, 8, "高" + sDto.getGradenum() + sDto.getClassname());
        getHSSFCell(sheetGP11, 1, 12, sDto.getStudentno());
        //总学分 总学分：   /144
        allTotalCreditHour = xbTotalCreditHour + xxTotalCreditHour + bxTotalCreditHour;
        getHSSFCell(sheetGP11, 2, 2, this.loseZero(String.valueOf(allTotalCreditHour)));
        getHSSFCell(sheetGP11, 2, 9, this.loseZero(String.valueOf(bxTotalCreditHour)));
        getHSSFCell(sheetGP11, 2, 13, this.loseZero(String.valueOf(xxTotalCreditHour)));
        getHSSFCell(sheetGP11, 2, 17, this.loseZero(String.valueOf(xbTotalCreditHour)));
        //学分总计栏
        HSSFRow rowXF = sheetGP11.createRow(maxRowNum + 1);
        this.createCells(rowXF, 2);
        sheetGP11.addMergedRegion(new Region(maxRowNum + 1, (short) 0, maxRowNum + 1, (short) 1));
        getHSSFCell(sheetGP11, maxRowNum + 1, 0, "学分总计");
        //填充总学分
        getHSSFCell(sheetGP11, maxRowNum + 1, 10, this.loseZero(String.valueOf(totalCreditHour1)));
        getHSSFCell(sheetGP11, maxRowNum + 1, 19, this.loseZero(String.valueOf(totalCreditHour2)));
        getHSSFCell(sheetGP11, maxRowNum + 1, 28, this.loseZero(String.valueOf(totalCreditHour3)));
        //班主任签字栏
        HSSFRow rowBZR = sheetGP11.createRow(maxRowNum + 2);
        this.createCells(rowBZR, 1);
        sheetGP11.addMergedRegion(new Region(maxRowNum + 2, (short) 0, maxRowNum + 2, (short) 1));
        sheetGP11.addMergedRegion(new Region(maxRowNum + 2, (short) 2, maxRowNum + 2, (short) 10));
        sheetGP11.addMergedRegion(new Region(maxRowNum + 2, (short) 11, maxRowNum + 2, (short) 19));
        sheetGP11.addMergedRegion(new Region(maxRowNum + 2, (short) 20, maxRowNum + 2, (short) 29));
        getHSSFCell(sheetGP11, maxRowNum + 2, 0, "班主任签字");
        //说明
        HSSFRow rowSM = sheetGP11.createRow(maxRowNum + 3);
        rowSM.setHeightInPoints(12);
        this.createCellsEnd(rowSM, 4);
        sheetGP11.addMergedRegion(new Region(maxRowNum + 3, (short) 0, maxRowNum + 3, (short) 29));
        getHSSFCell(sheetGP11, maxRowNum + 3, 0, "说明:");
        //说明内容
        HSSFRow rowSMNR = sheetGP11.createRow(maxRowNum + 4);
        rowSMNR.setHeightInPoints(25);
        this.createCellsEnd(rowSMNR, 4);
        sheetGP11.addMergedRegion(new Region(maxRowNum + 4, (short) 0, maxRowNum + 4, (short) 29));
        getHSSFCell(sheetGP11, maxRowNum + 4, 0, "     依据教育部《普通高中课程改革方案（实验）》，" +
                "北京市研制了《普通高中课程管理系统》，此登记表是该系统中的重要组成部分，集中反映学生高中三年" +
                "的学习状况，即各学科必修及选修模块内容、考试成绩、学分获得情况，其中还包含研究性学习、社区服务、" +
                "社会实践及校本课程的修习情况，按照高中三个学年分别汇总，且须班主任签字确认。该表数据信息可直接从" +
                "普通高中课程管理系统中导出。");
    }
    private void endExcelHtml(String str, StudentDto sDto) {
        //姓名等信息填写
        str=str.replaceAll("###banji###",sDto.getGradenum() + sDto.getClassname());
        //总学分 总学分：   /144
        allTotalCreditHour = xbTotalCreditHour + xxTotalCreditHour + bxTotalCreditHour;
        str=str.replaceAll("###zongxuefen###",this.loseZero(String.valueOf(allTotalCreditHour)));
        str=str.replaceAll("###bixiu###",this.loseZero(String.valueOf(bxTotalCreditHour)));
        str=str.replaceAll("###xuanxiu###",this.loseZero(String.valueOf(xxTotalCreditHour)));
        str=str.replaceAll("###xiaoben###",this.loseZero(String.valueOf(xbTotalCreditHour)));
    }

    private void createCellsEnd(HSSFRow row, int flag) {
        for (short i = 0; i < 30; i++) {
            HSSFCell cell = row.createCell(i);
            //样式设置待续
            if (4 == flag) {
                cell.setCellStyle(endRowStyle);
            }
        }
    }

    //三年学分统计(单个科目统计)
    private double creditHour1;
    private double creditHour2;
    private double creditHour3;
    //三个学年所有学分统计
    private double totalCreditHour1;
    private double totalCreditHour2;
    private double totalCreditHour3;
    //分别统计 必修 选修 校本课程的学分
    private double allTotalCreditHour;
    private double xxTotalCreditHour;
    private double bxTotalCreditHour;
    private double xbTotalCreditHour;

    private void createSubjectRow(Map<String, List<ModelScoreDto>> singleScore, HSSFSheet sheetGP11, String subject, int rowStartNum, int num, Map<String, String> hkScore, StudentDto sDto) {
        creditHour1 = 0;
        creditHour2 = 0;
        creditHour3 = 0;
        //一年级
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_1_1230101"), (short) 2, rowStartNum, num, 1);//第一学期第一学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_1_1230110"), (short) 4, rowStartNum, num, 1);//第一学期第二学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_1_1230120"), (short) 6, rowStartNum, num, 1);//第二学期第三学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_1_1230130"), (short) 8, rowStartNum, num, 1);//第二学期第四学段
        //二年级
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_2_1230101"), (short) 11, rowStartNum, num, 2);//第一学期第一学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_2_1230110"), (short) 13, rowStartNum, num, 2);//第一学期第二学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_2_1230120"), (short) 15, rowStartNum, num, 2);//第二学期第三学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_2_1230130"), (short) 17, rowStartNum, num, 2);//第二学期第四学段
        //三年级
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_3_1230101"), (short) 20, rowStartNum, num, 3);//第一学期第一学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_3_1230110"), (short) 22, rowStartNum, num, 3);//第一学期第二学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_3_1230120"), (short) 24, rowStartNum, num, 3);//第二学期第三学段
        this.fillSubjectScore(sheetGP11, singleScore.get(subject + "_3_1230130"), (short) 26, rowStartNum, num, 3);//第二学期第四学段
        //合并科目栏单元格
        this.mergedCell(sheetGP11, subject, rowStartNum, num, sDto, hkScore);
        //填充统计学分
        this.fillEacheCreditHours(sheetGP11, rowStartNum);
    }
    private void createSubjectRowHtml(Map<String, List<ModelScoreDto>> singleScore, String str, String subject,  Map<String, String> hkScore, StudentDto sDto) {
        creditHour1 = 0;
        creditHour2 = 0;
        creditHour3 = 0;
        //1，2，3年级
        this.fillSubjectScore1(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore2(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore3(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore4(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore5(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore6(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore7(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore8(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore9(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore10(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore11(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段
        this.fillSubjectScore12(str, singleScore.get(subject + "_1_1230101"), 1, subject);//第一学期第一学段

        //合并科目栏单元格
        //填充统计学分
        this.fillEacheCreditHours(sheetGP11, rowStartNum);
    }

    //学分去0
    private String loseZero(String creditHour) {
        if (NestUtil.isNotEmpty(creditHour) && creditHour.endsWith(".0")) {
            return creditHour.replace(".0", "");
        }
        return creditHour;
    }

    private void fillEacheCreditHours(HSSFSheet sheetGP11, int rowStartNum) {
        totalCreditHour1 += creditHour1;
        totalCreditHour2 += creditHour2;
        totalCreditHour3 += creditHour3;
        //1年级
        if (creditHour1 != 0) {
            sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 10, maxRowNum, (short) 10));
            getHSSFCell(sheetGP11, rowStartNum, 10, this.loseZero(String.valueOf(creditHour1)));
        }
        //2年级
        if (creditHour2 != 0) {
            sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 19, maxRowNum, (short) 19));
            getHSSFCell(sheetGP11, rowStartNum, 19, this.loseZero(String.valueOf(creditHour2)));
        }
        //3年级
        if (creditHour3 != 0) {
            sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 28, maxRowNum, (short) 28));
            getHSSFCell(sheetGP11, rowStartNum, 28, this.loseZero(String.valueOf(creditHour3)));
        }
    }

    private void mergedCell(HSSFSheet sheetGP11, String subject, int rowStartNum, int num, StudentDto sDto, Map<String, String> hkScore) {
        if (num == 3 || num == 7 || num == 8 || num == 9) {
            if (num == 3) {
                sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 0, maxRowNum, (short) 0));
                getHSSFCell(sheetGP11, rowStartNum, 0, "外语");
                this.fillHKscore(sDto, hkScore, sheetGP11, subject, rowStartNum, maxRowNum, (short) 29);
            } else {
                sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 0, maxRowNum, (short) 0));
                getHSSFCell(sheetGP11, rowStartNum, 0, subject);
                this.fillHKscore(sDto, hkScore, sheetGP11, subject, rowStartNum, maxRowNum, (short) 29);
            }
        } else {
            sheetGP11.addMergedRegion(new Region(rowStartNum, (short) 0, maxRowNum, (short) 1));
            getHSSFCell(sheetGP11, rowStartNum, 0, subject);
            this.fillHKscore(sDto, hkScore, sheetGP11, subject, rowStartNum, maxRowNum, (short) 29);
        }
    }

    private int maxRowNum;

    private void fillSubjectScore(HSSFSheet sheetGP11, List<ModelScoreDto> sxzzList, short cellStartNum, int rowStartNum, int num, int grade) {
        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (num == 3 || num == 7 || num == 8 || num == 9) {
                    if (num == 3) {
                        rowStartNum = initSpecialDataTwo(sheetGP11, cellStartNum, rowStartNum, num, msDto, "1", "2");
                    } else {
                        rowStartNum = initSpecialDataTwo(sheetGP11, cellStartNum, rowStartNum, num, msDto, "课程", "实验");
                    }
                } else {
                    rowStartNum = fillDataToCell(sheetGP11, cellStartNum, rowStartNum, num, msDto);
                }
            }
            //每填充完一个科目的数据  自增加一行数据
            if (rowStartNum > maxRowNum) {
                maxRowNum = rowStartNum - 1;
            }
        }
    }
    private void fillSubjectScore1(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###11zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###11zhengzhichengji###", msDto.getNzScore());
                    str = str.replaceAll("###1zzxuefenxiaoji###", creditHour);
                }
                if (subject == "语文") {
                    str = str.replaceAll("###11yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11yuwencj###", msDto.getNzScore());
                    str = str.replaceAll("###1ywxuefenxiaoji###", creditHour);

                }
                if (subject == "英语") {
                    str = str.replaceAll("###11waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###11waiyucj###", msDto.getNzScore());
                    str = str.replaceAll("###1wyxfxj###", creditHour);
                }
                if (subject == "历史") {
                    str = str.replaceAll("###11lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###11lishicj###", msDto.getNzScore());
                    str = str.replaceAll("###1lsxfxj###", creditHour);
                }
                if (subject == "地理") {
                    str = str.replaceAll("###11dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###11dilicj###", msDto.getNzScore());
                    str = str.replaceAll("###1dlxfxj###", creditHour);
                }
                if (subject == "数学") {
                    str = str.replaceAll("###11mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11mathscore###", msDto.getNzScore());
                    str = str.replaceAll("###1mathxfxj###", creditHour);
                }
                if (subject == "物理") {
                    str = str.replaceAll("###11wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###11wulicj###", msDto.getNzScore());
                    str = str.replaceAll("###1wulixfxj###", creditHour);
                }
                if (subject == "化学") {
                    str = str.replaceAll("###11huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###11huaxuecj###", msDto.getNzScore());
                    str = str.replaceAll("###1huaxuexfxj###", creditHour);
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                    str = str.replaceAll("###1shengwuxfxj###", creditHour);
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###11xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11xxjscj###", msDto.getNzScore());
                    str = str.replaceAll("###1xxjsxfxj###", creditHour);
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###11tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11ttjscj###", msDto.getNzScore());
                    str = str.replaceAll("###1tyjsxfxj###", creditHour);
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###11yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###11yishucj###", msDto.getNzScore());
                    str = str.replaceAll("###1yishuxfxj###", creditHour);
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###11musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11musiccj###", msDto.getNzScore());
                    str = str.replaceAll("###1musicxfxj###", creditHour);
                }
                if (subject == "美术") {
                    str = str.replaceAll("###11meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###11meishucj###", msDto.getNzScore());
                    str = str.replaceAll("###1meishuxfxj###", creditHour);
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###11yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###11yanjuecj###", msDto.getNzScore());
                    str = str.replaceAll("###1yanjuexfxj###", creditHour);
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###11shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11shequcj###", msDto.getNzScore());
                    str = str.replaceAll("###1shequxfxj###", creditHour);
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###11shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11shijiancj###", msDto.getNzScore());
                    str = str.replaceAll("###1shijianxfxj###", creditHour);
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###11xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###11xiaobencj###", msDto.getNzScore());
                    str = str.replaceAll("###1xiaobenxfxj###", creditHour);
                }
            }

        }
    }private void fillSubjectScore2(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###12zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###12zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###12yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###12waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###12waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###12lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###12lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###12dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###12dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###12mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###12wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###12wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###12huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###12huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###12xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###12tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###12yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###12yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###12musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###12meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###12meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###12yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###12yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###12shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###12shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###12xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###12xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore3(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###13zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###13zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###13yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###13waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###13waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###13lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###13lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###13dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###13dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###13mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###13wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###13wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###13huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###13huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###13xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###13tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###13yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###13yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###13musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###13meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###13meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###13yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###13yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###13shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###13shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###13xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###13xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore4(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###14zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###14zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###14yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###14waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###14waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###14lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###14lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###14dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###14dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###14mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###14wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###14wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###14huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###14huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###14xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###14tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###14yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###14yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###14musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###14meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###14meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###14yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###14yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###14shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###14shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###14xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###14xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore5(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###21zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###21zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###21yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###21waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###21waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###21lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###21lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###21dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###21dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###21mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###21wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###21wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###21huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###21huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###21xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###21tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###21yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###21yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###21musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###21meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###21meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###21yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###21yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###21shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###21shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###21xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###21xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore6(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###22zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###22zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###22yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###22waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###22waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###22lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###22lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###22dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###22dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###22mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###22wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###22wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###22huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###22huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###22xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###22tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###22yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###22yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###22musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###22meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###22meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###22yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###22yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###22shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###22shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###22xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###22xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore7(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###23zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###23zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###23yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###23waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###23waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###23lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###23lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###23dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###23dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###23mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###23wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###23wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###23huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###23huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###23xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###23tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###23yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###23yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###23musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###23meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###23meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###23yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###23yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###23shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###23shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###23xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###23xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore8(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###24zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###24zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###24yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###24waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###24waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###24lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###24lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###24dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###24dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###24mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###24wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###24wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###24huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###24huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###24xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###24tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###24yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###24yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###24musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###24meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###24meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###24yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###24yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###24shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###24shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###24xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###24xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore9(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###31zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###31zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###31yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###31waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###31waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###31lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###31lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###31dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###31dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###31mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###31wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###31wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###31huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###31huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###31xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###31tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###31yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###31yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###31musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###31meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###31meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###31yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###31yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###31shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###31shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###31xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###31xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore10(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###32zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###32zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###32yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###32waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###32waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###32lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###32lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###32dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###32dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###32mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###32wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###32wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###32huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###32huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###32xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###32tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###32yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###32yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###32musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###32meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###32meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###32yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###32yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###32shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###32shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###32xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###32xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore11(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###33zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###33zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###33yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###33waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###33waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###33lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###33lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###33dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###33dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###33mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###33wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###33wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###33huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###33huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###33xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###33tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###33yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###33yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###33musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###33meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###33meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###33yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###33yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###33shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###33shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###33xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###33xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }private void fillSubjectScore12(String str, List<ModelScoreDto> sxzzList, int grade,String subject) {

        if (null != sxzzList && sxzzList.size() > 0) {
            for (ModelScoreDto msDto : sxzzList) {
                String creditHour = msDto.getCredit_hour();
                if (NestUtil.isEmpty(creditHour)) {
                    creditHour = "0";
                }
                //各学科各学年学分统计
                this.eachSubCreditHourCount(grade, msDto, creditHour);
                //各类课程学分统计
                this.eachTypeSubCreditHourCount(msDto, creditHour);
                if (subject == "思想政治") {
                    str = str.replaceAll("###34zhengzhimokuai###", msDto.getCourse_name());
                    str = str.replaceAll("###34zhengzhichengji###", msDto.getNzScore());
                }
                if (subject == "语文") {
                    str = str.replaceAll("###34yuwenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34yuwencj###", msDto.getNzScore());
                }
                if (subject == "英语") {
                    str = str.replaceAll("###34waiyumk###", msDto.getCourse_name());
                    str = str.replaceAll("###34waiyucj###", msDto.getNzScore());
                }
                if (subject == "历史") {
                    str = str.replaceAll("###34lishimk###", msDto.getCourse_name());
                    str = str.replaceAll("###34lishicj###", msDto.getNzScore());
                }
                if (subject == "地理") {
                    str = str.replaceAll("###34dilimk###", msDto.getCourse_name());
                    str = str.replaceAll("###34dilicj###", msDto.getNzScore());
                }
                if (subject == "数学") {
                    str = str.replaceAll("###34mathmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34mathscore###", msDto.getNzScore());
                }
                if (subject == "物理") {
                    str = str.replaceAll("###34wulimk###", msDto.getCourse_name());
                    str = str.replaceAll("###34wulicj###", msDto.getNzScore());
                }
                if (subject == "化学") {
                    str = str.replaceAll("###34huaxuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###34huaxuecj###", msDto.getNzScore());
                }
                if (subject == "生物") {
                    str = str.replaceAll("###1shengwumk###", msDto.getCourse_name());
                    str = str.replaceAll("###1shengwucj###", msDto.getNzScore());
                }
                if (subject == "信息技术") {
                    str = str.replaceAll("###34xxjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34xxjscj###", msDto.getNzScore());
                }
                if (subject == "通用技术") {
                    str = str.replaceAll("###34tyjsmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34ttjscj###", msDto.getNzScore());
                }
                if (subject == "艺术") {
                    str = str.replaceAll("###34yishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###34yishucj###", msDto.getNzScore());
                }
                if (subject == "音乐") {
                    str = str.replaceAll("###34musicmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34musiccj###", msDto.getNzScore());
                }
                if (subject == "美术") {
                    str = str.replaceAll("###34meishumk###", msDto.getCourse_name());
                    str = str.replaceAll("###34meishucj###", msDto.getNzScore());
                }
                if (subject == "研究性学习活动") {
                    str = str.replaceAll("###34yanjuemk###", msDto.getCourse_name());
                    str = str.replaceAll("###34yanjuecj###", msDto.getNzScore());
                }
                if (subject == "社区服务") {
                    str = str.replaceAll("###34shequmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34shequcj###", msDto.getNzScore());
                }
                if (subject == "社会实践") {
                    str = str.replaceAll("###34shijianmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34shijiancj###", msDto.getNzScore());
                }
                if (subject == "校本课程") {
                    str = str.replaceAll("###34xiaobenmk###", msDto.getCourse_name());
                    str = str.replaceAll("###34xiaobencj###", msDto.getNzScore());
                }
            }

        }
    }

    private void eachTypeSubCreditHourCount(ModelScoreDto msDto, String creditHour) {
        if ((Constant.KG_COURSE_BX.equals(msDto.getCourse_kind()) || Constant.KG_COURSE_BX1.equals(msDto.getCourse_kind()))) {
//			if(NestUtil.isNotEmpty(msDto.getNzScore())){
            bxTotalCreditHour += Double.parseDouble(msDto.getCredit_hour());
//			}
        } else if (Constant.KG_COURSE_XX.equals(msDto.getCourse_kind())) {
            xxTotalCreditHour += Double.parseDouble(msDto.getCredit_hour());
        } else if (Constant.KG_COURSE_KIND.equals(msDto.getCourse_kind())) {
            xbTotalCreditHour += Double.parseDouble(msDto.getCredit_hour());
        }
    }

    private void eachSubCreditHourCount(int grade, ModelScoreDto msDto,
                                        String creditHour) {
        if (1 == grade) {
//			if(NestUtil.isNotEmpty(msDto.getNzScore())){
            creditHour1 += Double.parseDouble(creditHour);
//			}
        } else if (2 == grade) {
//			if(NestUtil.isNotEmpty(msDto.getNzScore())){
            creditHour2 += Double.parseDouble(creditHour);
//			}
        } else if (3 == grade) {
//			if(NestUtil.isNotEmpty(msDto.getNzScore())){
            creditHour3 += Double.parseDouble(creditHour);
//			}
        }
    }

    private int initSpecialDataTwo(HSSFSheet sheetGP11, short cellStartNum,
                                   int rowStartNum, int num, ModelScoreDto msDto, String ff, String ee) {
        for (int i = 0; i < 2; i++) {
            HSSFRow row = sheetGP11.getRow(rowStartNum);
            if (null == row) {
                row = sheetGP11.createRow(rowStartNum);
                this.createCells(row, num);
            }
            if (i == 0) {
//				if(NestUtil.isNotEmpty(msDto.getNzScore())){
                getHSSFCell(sheetGP11, rowStartNum, cellStartNum, msDto.getCourse_name());
                getHSSFCell(sheetGP11, rowStartNum, cellStartNum + 1, msDto.getNzScore());
//					getHSSFCell(sheetGP11,rowStartNum, 29, msDto.getHkScore());
//				}
                getHSSFCell(sheetGP11, rowStartNum, 1, ff);
            } else {
                getHSSFCell(sheetGP11, rowStartNum, 1, ee);
            }
            rowStartNum++;
        }
        return rowStartNum;
    }

    private int fillDataToCell(HSSFSheet sheetGP11, short cellStartNum,
                               int rowStartNum, int num, ModelScoreDto msDto) {
        HSSFRow row = sheetGP11.getRow(rowStartNum);
        if (null == row) {
            row = sheetGP11.createRow(rowStartNum);
            this.createCells(row, num);
        }
//		if(NestUtil.isNotEmpty(msDto.getNzScore())){
        getHSSFCell(sheetGP11, rowStartNum, cellStartNum, msDto.getCourse_name());
        getHSSFCell(sheetGP11, rowStartNum, cellStartNum + 1, msDto.getNzScore());
//			getHSSFCell(sheetGP11,rowStartNum, 29, msDto.getHkScore());
        rowStartNum++;
//		}
        return rowStartNum;
    }

    //设置表格样式
    private HSSFCellStyle cellStyle = null;
    //设置表格结束语样式
    private HSSFCellStyle endRowStyle = null;
    //变色行
    private HSSFCellStyle cellStyle2 = null;
    //字体
    private HSSFFont font = null;

    /**
     * 初始化表格的一些样式(隔行变色)
     *
     * @param workbook
     */
    private void initExcelStyle(HSSFWorkbook workbook) {
        //设置字体
        font = workbook.createFont();
        font.setFontHeightInPoints((short) 8);
        font.setFontName("SimSun");
        //大众化样式
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFont(font);
        //结束语样式
        endRowStyle = workbook.createCellStyle();
        endRowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
        endRowStyle.setWrapText(true);
        endRowStyle.setFont(font);
        //变色行
        cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
        cellStyle2.setWrapText(true);
        cellStyle2.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle2.setFont(font);
    }

    private void createCells(HSSFRow row, Integer flag) {
        for (short i = 0; i < 30; i++) {
            HSSFCell cell = row.createCell(i);
            if (flag % 2 == 0) {
                cell.setCellStyle(cellStyle2);
            } else {
                cell.setCellStyle(cellStyle);
            }
        }
    }

    private void deleteDirection(HSSFWorkbook wb, String flag) {
        if (!"1".equals(flag)) {
            wb.removeSheetAt(wb.getSheetIndex("报告单说明"));
        }
    }

    private void fillCheckItems(List<CheckItemInfoDto> sigleCheckItems,
                                HSSFWorkbook wb) {
        HSSFSheet sheetGP4 = wb.getSheet("北京市中学生健康体检表");
        if (sigleCheckItems != null && sigleCheckItems.size() > 0) {
            CheckItemInfoDto dto1 = sigleCheckItems.get(0);//高一
            if (dto1 != null) {
                //发育及营养状况
                getHSSFCell(sheetGP4, 3, 3, dto1.getResultStr());
                //身高
                getHSSFCell(sheetGP4, 4, 3, dto1.getItemResultCm());
                //体重
                getHSSFCell(sheetGP4, 5, 3, dto1.getItemResultKg());
                //肺活量
                getHSSFCell(sheetGP4, 6, 3, dto1.getItemResultMl());
                //血压（低）/血压（高）
                getHSSFCell(sheetGP4, 7, 3, (dto1.getItemResultMmHgDown() != null ? dto1.getItemResultMmHgDown() : "") + "/" + (dto1.getItemResultMmHgUp() != null ? dto1.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,6,dto1.getItemResultMmHgUp());
                //脉搏
                getHSSFCell(sheetGP4, 8, 3, dto1.getItemResultMb());
                //上学期裸眼视力右
                getHSSFCell(sheetGP4, 10, 4, dto1.getItemResultLySlRightUp());
                //上学期裸眼视力左
                getHSSFCell(sheetGP4, 10, 3, dto1.getItemResultLySlLeftUp());
                //上学期近视力右
                getHSSFCell(sheetGP4, 11, 4, dto1.getItemResultJSlRightUp());
                //上学期近视力左
                getHSSFCell(sheetGP4, 11, 3, dto1.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                getHSSFCell(sheetGP4, 12, 4, dto1.getItemResultLySlRightDown());
                //下学期裸眼视力左
                getHSSFCell(sheetGP4, 12, 3, dto1.getItemResultLySlLeftDown());
                //下学期近视力右
                getHSSFCell(sheetGP4, 13, 4, dto1.getItemResultJSlRightDown());
                //下学期近视力左
                getHSSFCell(sheetGP4, 13, 3, dto1.getItemResultJSlLeftDown());
                //色觉
                getHSSFCell(sheetGP4, 14, 3, dto1.getItemResultSj());
                //沙眼
                getHSSFCell(sheetGP4, 15, 3, dto1.getItemResultSy());
                //心脏
                getHSSFCell(sheetGP4, 17, 3, dto1.getItemResultXz());
                //肺
                getHSSFCell(sheetGP4, 18, 3, dto1.getItemResultF());
                String gp1 = "";
                if (dto1.getItemResultGan() != null && dto1.getItemResultGan().trim().length() > 0 && dto1.getItemResultPi() != null && dto1.getItemResultPi().trim().length() > 0) {
                    gp1 = dto1.getItemResultGan().trim() + "/" + dto1.getItemResultPi().trim();
                } else if (dto1.getItemResultGan() != null && dto1.getItemResultGan().trim().length() > 0) {
                    gp1 = dto1.getItemResultGan().trim();
                } else if (dto1.getItemResultPi() != null && dto1.getItemResultPi().trim().length() > 0) {
                    gp1 = dto1.getItemResultPi().trim();
                }
                //肝 脾
                getHSSFCell(sheetGP4, 19, 3, gp1);
//					//脾
//					getHSSFCell(sheetGP4,19,6,dto1.getItemResultPi());
                //胸部
                getHSSFCell(sheetGP4, 21, 3, dto1.getItemResultXb());
                //脊柱
                getHSSFCell(sheetGP4, 22, 3, dto1.getItemResultJz());
                //四肢
                getHSSFCell(sheetGP4, 23, 3, dto1.getItemResultSz());
                //皮肤
                getHSSFCell(sheetGP4, 24, 3, dto1.getItemResultPf());
                //结膜炎
                getHSSFCell(sheetGP4, 16, 3, dto1.getItemResultJmy());
                //头颈
                getHSSFCell(sheetGP4, 20, 3, dto1.getItemResultTj());
                //血色素
                getHSSFCell(sheetGP4, 25, 3, dto1.getItemResultXss());
                //便检
                getHSSFCell(sheetGP4, 26, 3, dto1.getItemResultBj());
                //肝功能
                getHSSFCell(sheetGP4, 27, 3, dto1.getItemResultGg());
                //PPD
                getHSSFCell(sheetGP4, 28, 3, dto1.getItemResultPpd());
                //既往病史
                getHSSFCell(sheetGP4, 29, 3, dto1.getItemResultBs());
                //体验机构
                getHSSFCell(sheetGP4, 30, 3, dto1.getItemResultJg());
            }

            CheckItemInfoDto dto2 = sigleCheckItems.get(1);//高二
            if (dto2 != null) {
                //发育及营养状况
                getHSSFCell(sheetGP4, 3, 5, dto2.getResultStr());
                //身高
                getHSSFCell(sheetGP4, 4, 5, dto2.getItemResultCm());
                //体重
                getHSSFCell(sheetGP4, 5, 5, dto2.getItemResultKg());
                //肺活量
                getHSSFCell(sheetGP4, 6, 5, dto2.getItemResultMl());
                //血压（低）
                getHSSFCell(sheetGP4, 7, 5, (dto2.getItemResultMmHgDown() != null ? dto2.getItemResultMmHgDown() : "") + "/" + (dto2.getItemResultMmHgUp() != null ? dto2.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,10,dto2.getItemResultMmHgUp());
                //脉搏
                getHSSFCell(sheetGP4, 8, 5, dto2.getItemResultMb());
                //上学期裸眼视力右
                getHSSFCell(sheetGP4, 10, 6, dto2.getItemResultLySlRightUp());
                //上学期裸眼视力左
                getHSSFCell(sheetGP4, 10, 5, dto2.getItemResultLySlLeftUp());
                //上学期近视力右
                getHSSFCell(sheetGP4, 11, 6, dto2.getItemResultJSlRightUp());
                //上学期近视力左
                getHSSFCell(sheetGP4, 11, 5, dto2.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                getHSSFCell(sheetGP4, 12, 6, dto2.getItemResultLySlRightDown());
                //下学期裸眼视力左
                getHSSFCell(sheetGP4, 12, 5, dto2.getItemResultLySlLeftDown());
                //下学期近视力右
                getHSSFCell(sheetGP4, 13, 6, dto2.getItemResultJSlRightDown());
                //上学期近视力左
                getHSSFCell(sheetGP4, 13, 5, dto2.getItemResultJSlLeftDown());
                //色觉
                getHSSFCell(sheetGP4, 14, 5, dto2.getItemResultSj());
                //沙眼
                getHSSFCell(sheetGP4, 15, 5, dto2.getItemResultSy());
                //心脏
                getHSSFCell(sheetGP4, 17, 5, dto2.getItemResultXz());
                //肺
                getHSSFCell(sheetGP4, 18, 5, dto2.getItemResultF());
                String gp2 = "";
                if (dto2.getItemResultGan() != null && dto2.getItemResultGan().trim().length() > 0 && dto2.getItemResultPi() != null && dto2.getItemResultPi().trim().length() > 0) {
                    gp2 = dto2.getItemResultGan().trim() + "/" + dto2.getItemResultPi().trim();
                } else if (dto2.getItemResultGan() != null && dto2.getItemResultGan().trim().length() > 0) {
                    gp2 = dto2.getItemResultGan().trim();
                } else if (dto2.getItemResultPi() != null && dto2.getItemResultPi().trim().length() > 0) {
                    gp2 = dto2.getItemResultPi().trim();
                }
                //肝
                getHSSFCell(sheetGP4, 19, 5, gp2);
//					//脾
//					getHSSFCell(sheetGP4,19,10,dto2.getItemResultPi());
                //胸部
                getHSSFCell(sheetGP4, 21, 5, dto2.getItemResultXb());
                //脊柱
                getHSSFCell(sheetGP4, 22, 5, dto2.getItemResultJz());
                //四肢
                getHSSFCell(sheetGP4, 23, 5, dto2.getItemResultSz());
                //皮肤
                getHSSFCell(sheetGP4, 24, 5, dto2.getItemResultPf());
                //结膜炎
                getHSSFCell(sheetGP4, 16, 5, dto2.getItemResultJmy());
                //头颈
                getHSSFCell(sheetGP4, 20, 5, dto2.getItemResultTj());
                //血色素
                getHSSFCell(sheetGP4, 25, 5, dto2.getItemResultXss());
                //便检
                getHSSFCell(sheetGP4, 26, 5, dto2.getItemResultBj());
                //肝功能
                getHSSFCell(sheetGP4, 27, 5, dto2.getItemResultGg());
                //PPD
                getHSSFCell(sheetGP4, 28, 5, dto2.getItemResultPpd());
                //体验机构
                getHSSFCell(sheetGP4, 30, 5, dto2.getItemResultJg());
            }


            CheckItemInfoDto dto3 = sigleCheckItems.get(2);//高三
            if (dto3 != null) {
                //发育及营养状况
                getHSSFCell(sheetGP4, 3, 7, dto3.getResultStr());
                //身高
                getHSSFCell(sheetGP4, 4, 7, dto3.getItemResultCm());
                //体重
                getHSSFCell(sheetGP4, 5, 7, dto3.getItemResultKg());
                //肺活量
                getHSSFCell(sheetGP4, 6, 7, dto3.getItemResultMl());
                //血压（低）
                getHSSFCell(sheetGP4, 7, 7, (dto3.getItemResultMmHgDown() != null ? dto3.getItemResultMmHgDown() : "") + "/" + (dto3.getItemResultMmHgUp() != null ? dto3.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,14,dto3.getItemResultMmHgUp());
                //脉搏
                getHSSFCell(sheetGP4, 8, 7, dto3.getItemResultMb());
                //上学期裸眼视力右
                getHSSFCell(sheetGP4, 10, 8, dto3.getItemResultLySlRightUp());
                //上学期裸眼视力左
                getHSSFCell(sheetGP4, 10, 7, dto3.getItemResultLySlLeftUp());
                //上学期近视力右
                getHSSFCell(sheetGP4, 11, 8, dto3.getItemResultJSlRightUp());
                //上学期近视力左
                getHSSFCell(sheetGP4, 11, 7, dto3.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                getHSSFCell(sheetGP4, 12, 8, dto3.getItemResultLySlRightDown());
                //下学期裸眼视力左
                getHSSFCell(sheetGP4, 12, 7, dto3.getItemResultLySlLeftDown());
                //下学期近视力右
                getHSSFCell(sheetGP4, 13, 8, dto3.getItemResultJSlRightDown());
                //下学期近视力左
                getHSSFCell(sheetGP4, 13, 7, dto3.getItemResultJSlLeftDown());
                //色觉
                getHSSFCell(sheetGP4, 14, 7, dto3.getItemResultSj());
                //沙眼
                getHSSFCell(sheetGP4, 15, 7, dto3.getItemResultSy());
                //心脏
                getHSSFCell(sheetGP4, 17, 7, dto3.getItemResultXz());
                //肺
                getHSSFCell(sheetGP4, 18, 7, dto3.getItemResultF());
                String gp3 = "";
                if (dto3.getItemResultGan() != null && dto3.getItemResultGan().trim().length() > 0 && dto3.getItemResultPi() != null && dto3.getItemResultPi().trim().length() > 0) {
                    gp3 = dto3.getItemResultGan().trim() + "/" + dto3.getItemResultPi().trim();
                } else if (dto3.getItemResultGan() != null && dto3.getItemResultGan().trim().length() > 0) {
                    gp3 = dto3.getItemResultGan().trim();
                } else if (dto3.getItemResultPi() != null && dto3.getItemResultPi().trim().length() > 0) {
                    gp3 = dto3.getItemResultPi().trim();
                }
                //肝
                getHSSFCell(sheetGP4, 19, 7, gp3);
//					//脾
//					getHSSFCell(sheetGP4,19,14,dto3.getItemResultPi());
                //胸部
                getHSSFCell(sheetGP4, 21, 7, dto3.getItemResultXb());
                //脊柱
                getHSSFCell(sheetGP4, 22, 7, dto3.getItemResultJz());
                //四肢
                getHSSFCell(sheetGP4, 23, 7, dto3.getItemResultSz());
                //皮肤
                getHSSFCell(sheetGP4, 24, 7, dto3.getItemResultPf());
                //结膜炎
                getHSSFCell(sheetGP4, 16, 7, dto3.getItemResultJmy());
                //头颈
                getHSSFCell(sheetGP4, 20, 7, dto3.getItemResultTj());
                //血色素
                getHSSFCell(sheetGP4, 25, 7, dto3.getItemResultXss());
                //便检
                getHSSFCell(sheetGP4, 26, 7, dto3.getItemResultBj());
                //肝功能
                getHSSFCell(sheetGP4, 27, 7, dto3.getItemResultGg());
                //PPD
                getHSSFCell(sheetGP4, 28, 7, dto3.getItemResultPpd());
                //体验机构
                getHSSFCell(sheetGP4, 30, 7, dto3.getItemResultJg());
            }
        }
    }private void fillCheckItemsHtml(List<CheckItemInfoDto> sigleCheckItems,
                                String str) {
        if (sigleCheckItems != null && sigleCheckItems.size() > 0) {
            CheckItemInfoDto dto1 = sigleCheckItems.get(0);//高一
            if (dto1 != null) {
                //发育及营养状况
                str=str.replaceAll("###1fayuyingyang###",dto1.getResultStr());
                //身高
                str=str.replaceAll("###1shengao###",dto1.getItemResultCm());
                //体重
                str=str.replaceAll("###1tizhong###",dto1.getItemResultKg());
                //肺活量
                str=str.replaceAll("###1feihuoliang###",dto1.getItemResultMl());
                //血压（低）/血压（高）
                str=str.replaceAll("###1xueya###",(dto1.getItemResultMmHgDown() != null ? dto1.getItemResultMmHgDown() : "") + "/" + (dto1.getItemResultMmHgUp() != null ? dto1.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,6,dto1.getItemResultMmHgUp());
                //脉搏
                str=str.replaceAll("###1maibo###",dto1.getItemResultMb());
                //上学期裸眼视力右
                str=str.replaceAll("###1shangyouluoyan###",dto1.getItemResultLySlRightUp());
                //上学期裸眼视力左
                str=str.replaceAll("###1shangzuoluoyan###",dto1.getItemResultLySlLeftUp());
                //上学期近视力右
                str=str.replaceAll("###1shangyoujinshi###",dto1.getItemResultJSlRightUp());
                //上学期近视力左
                str=str.replaceAll("###1shangzuojinshi###",dto1.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                str=str.replaceAll("###1xiayouluoyan###",dto1.getItemResultLySlRightDown());
                //下学期裸眼视力左
                str=str.replaceAll("###1xiazuoluoyan###",dto1.getItemResultLySlLeftDown());
                //下学期近视力右
                str=str.replaceAll("###1xiayoujinshi###",dto1.getItemResultJSlRightDown());
                //下学期近视力左
                str=str.replaceAll("###1xiazuojinshi###",dto1.getItemResultJSlLeftDown());
                //色觉
                str=str.replaceAll("###1sejue###",dto1.getItemResultSj());
                //沙眼
                str=str.replaceAll("###1shayan###",dto1.getItemResultSy());
                //心脏
                str=str.replaceAll("###1xinzang###",dto1.getItemResultSj());
                //肺
                str=str.replaceAll("###1fei###",dto1.getItemResultF());
                String gp1 = "";
                if (dto1.getItemResultGan() != null && dto1.getItemResultGan().trim().length() > 0 && dto1.getItemResultPi() != null && dto1.getItemResultPi().trim().length() > 0) {
                    gp1 = dto1.getItemResultGan().trim() + "/" + dto1.getItemResultPi().trim();
                } else if (dto1.getItemResultGan() != null && dto1.getItemResultGan().trim().length() > 0) {
                    gp1 = dto1.getItemResultGan().trim();
                } else if (dto1.getItemResultPi() != null && dto1.getItemResultPi().trim().length() > 0) {
                    gp1 = dto1.getItemResultPi().trim();
                }
                //肝 脾
                str=str.replaceAll("###1ganpi###",gp1);
//					//脾
//					getHSSFCell(sheetGP4,19,6,dto1.getItemResultPi());
                //胸部
                str=str.replaceAll("###1xiongbu###",dto1.getItemResultXb());
                //脊柱
                str=str.replaceAll("###1jizhu###",dto1.getItemResultJz());
                //四肢
                str=str.replaceAll("###1sizhi###",dto1.getItemResultSz());
                //皮肤
                str=str.replaceAll("###1pifuliba###",dto1.getItemResultPf());
                //结膜炎
                str=str.replaceAll("###1jiemoyan###",dto1.getItemResultJmy());
                //头颈
                str=str.replaceAll("###1toujin###",dto1.getItemResultTj());
                //血色素
                str=str.replaceAll("###1xuesesu###",dto1.getItemResultXss());
                //便检
                str=str.replaceAll("###1bianjian###",dto1.getItemResultBj());
                //肝功能
                str=str.replaceAll("###1gangongneng###",dto1.getItemResultGg());
                //PPD
                str=str.replaceAll("###1ppd###",dto1.getItemResultPpd());
                //既往病史
                str=str.replaceAll("###bingshi###",dto1.getItemResultBs());
                //体验机构
                str=str.replaceAll("###1tijianjigou###",dto1.getItemResultJg());
            }

            CheckItemInfoDto dto2 = sigleCheckItems.get(1);//高二
            if (dto2 != null) {
                //发育及营养状况
                str=str.replaceAll("###2fayuyingyang###",dto2.getResultStr());
                //身高
                str=str.replaceAll("###2shengao###",dto2.getItemResultCm());
                //体重
                str=str.replaceAll("###2tizhong###",dto2.getItemResultKg());
                //肺活量
                str=str.replaceAll("###2feihuoliang###",dto2.getItemResultMl());
                //血压（低）/血压（高）
                str=str.replaceAll("###2xueya###",(dto2.getItemResultMmHgDown() != null ? dto2.getItemResultMmHgDown() : "") + "/" + (dto2.getItemResultMmHgUp() != null ? dto2.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,6,dto2.getItemResultMmHgUp());
                //脉搏
                str=str.replaceAll("###2maibo###",dto2.getItemResultMb());
                //上学期裸眼视力右
                str=str.replaceAll("###2shangyouluoyan###",dto2.getItemResultLySlRightUp());
                //上学期裸眼视力左
                str=str.replaceAll("###2shangzuoluoyan###",dto2.getItemResultLySlLeftUp());
                //上学期近视力右
                str=str.replaceAll("###2shangyoujinshi###",dto2.getItemResultJSlRightUp());
                //上学期近视力左
                str=str.replaceAll("###2shangzuojinshi###",dto2.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                str=str.replaceAll("###2xiayouluoyan###",dto2.getItemResultLySlRightDown());
                //下学期裸眼视力左
                str=str.replaceAll("###2xiazuoluoyan###",dto2.getItemResultLySlLeftDown());
                //下学期近视力右
                str=str.replaceAll("###2xiayoujinshi###",dto2.getItemResultJSlRightDown());
                //下学期近视力左
                str=str.replaceAll("###2xiazuojinshi###",dto2.getItemResultJSlLeftDown());
                //色觉
                str=str.replaceAll("###2sejue###",dto2.getItemResultSj());
                //沙眼
                str=str.replaceAll("###2shayan###",dto2.getItemResultSy());
                //心脏
                str=str.replaceAll("###2xinzang###",dto2.getItemResultSj());
                //肺
                str=str.replaceAll("###2fei###",dto2.getItemResultF());
                String gp2 = "";
                if (dto2.getItemResultGan() != null && dto2.getItemResultGan().trim().length() > 0 && dto2.getItemResultPi() != null && dto2.getItemResultPi().trim().length() > 0) {
                    gp2 = dto2.getItemResultGan().trim() + "/" + dto2.getItemResultPi().trim();
                } else if (dto2.getItemResultGan() != null && dto2.getItemResultGan().trim().length() > 0) {
                    gp2 = dto2.getItemResultGan().trim();
                } else if (dto2.getItemResultPi() != null && dto2.getItemResultPi().trim().length() > 0) {
                    gp2 = dto2.getItemResultPi().trim();
                }
                //肝 脾
                str=str.replaceAll("###2ganpi###",gp2);
//					//脾
//					getHSSFCell(sheetGP4,29,6,dto2.getItemResultPi());
                //胸部
                str=str.replaceAll("###2xiongbu###",dto2.getItemResultXb());
                //脊柱
                str=str.replaceAll("###2jizhu###",dto2.getItemResultJz());
                //四肢
                str=str.replaceAll("###2sizhi###",dto2.getItemResultSz());
                //皮肤
                str=str.replaceAll("###2pifuliba###",dto2.getItemResultPf());
                //结膜炎
                str=str.replaceAll("###2jiemoyan###",dto2.getItemResultJmy());
                //头颈
                str=str.replaceAll("###2toujin###",dto2.getItemResultTj());
                //血色素
                str=str.replaceAll("###2xuesesu###",dto2.getItemResultXss());
                //便检
                str=str.replaceAll("###2bianjian###",dto2.getItemResultBj());
                //肝功能
                str=str.replaceAll("###2gangongneng###",dto2.getItemResultGg());
                //PPD
                str=str.replaceAll("###2ppd###",dto2.getItemResultPpd());
                //既往病史
                str=str.replaceAll("###bingshi###",dto2.getItemResultBs());
                //体验机构
                str=str.replaceAll("###2tijianjigou###",dto2.getItemResultJg());
            }


            CheckItemInfoDto dto3 = sigleCheckItems.get(2);//高三
            if (dto3 != null) {
                //发育及营养状况
                str=str.replaceAll("###3fayuyingyang###",dto3.getResultStr());
                //身高
                str=str.replaceAll("###3shengao###",dto3.getItemResultCm());
                //体重
                str=str.replaceAll("###3tizhong###",dto3.getItemResultKg());
                //肺活量
                str=str.replaceAll("###3feihuoliang###",dto3.getItemResultMl());
                //血压（低）/血压（高）
                str=str.replaceAll("###3xueya###",(dto3.getItemResultMmHgDown() != null ? dto3.getItemResultMmHgDown() : "") + "/" + (dto3.getItemResultMmHgUp() != null ? dto3.getItemResultMmHgUp() : ""));
//					//血压（高）
//					getHSSFCell(sheetGP4,7,6,dto3.getItemResultMmHgUp());
                //脉搏
                str=str.replaceAll("###3maibo###",dto3.getItemResultMb());
                //上学期裸眼视力右
                str=str.replaceAll("###3shangyouluoyan###",dto3.getItemResultLySlRightUp());
                //上学期裸眼视力左
                str=str.replaceAll("###3shangzuoluoyan###",dto3.getItemResultLySlLeftUp());
                //上学期近视力右
                str=str.replaceAll("###3shangyoujinshi###",dto3.getItemResultJSlRightUp());
                //上学期近视力左
                str=str.replaceAll("###3shangzuojinshi###",dto3.getItemResultJSlLeftUp());
                //下学期裸眼视力右
                str=str.replaceAll("###3xiayouluoyan###",dto3.getItemResultLySlRightDown());
                //下学期裸眼视力左
                str=str.replaceAll("###3xiazuoluoyan###",dto3.getItemResultLySlLeftDown());
                //下学期近视力右
                str=str.replaceAll("###3xiayoujinshi###",dto3.getItemResultJSlRightDown());
                //下学期近视力左
                str=str.replaceAll("###3xiazuojinshi###",dto3.getItemResultJSlLeftDown());
                //色觉
                str=str.replaceAll("###3sejue###",dto3.getItemResultSj());
                //沙眼
                str=str.replaceAll("###3shayan###",dto3.getItemResultSy());
                //心脏
                str=str.replaceAll("###3xinzang###",dto3.getItemResultSj());
                //肺
                str=str.replaceAll("###3fei###",dto3.getItemResultF());
                String gp3 = "";
                if (dto3.getItemResultGan() != null && dto3.getItemResultGan().trim().length() > 0 && dto3.getItemResultPi() != null && dto3.getItemResultPi().trim().length() > 0) {
                    gp3 = dto3.getItemResultGan().trim() + "/" + dto3.getItemResultPi().trim();
                } else if (dto3.getItemResultGan() != null && dto3.getItemResultGan().trim().length() > 0) {
                    gp3 = dto3.getItemResultGan().trim();
                } else if (dto3.getItemResultPi() != null && dto3.getItemResultPi().trim().length() > 0) {
                    gp3 = dto3.getItemResultPi().trim();
                }
                //肝 脾
                str=str.replaceAll("###3ganpi###",gp3);
//					//脾
//					getHSSFCell(sheetGP4,39,6,dto3.getItemResultPi());
                //胸部
                str=str.replaceAll("###3xiongbu###",dto3.getItemResultXb());
                //脊柱
                str=str.replaceAll("###3jizhu###",dto3.getItemResultJz());
                //四肢
                str=str.replaceAll("###3sizhi###",dto3.getItemResultSz());
                //皮肤
                str=str.replaceAll("###3pifuliba###",dto3.getItemResultPf());
                //结膜炎
                str=str.replaceAll("###3jiemoyan###",dto3.getItemResultJmy());
                //头颈
                str=str.replaceAll("###3toujin###",dto3.getItemResultTj());
                //血色素
                str=str.replaceAll("###3xuesesu###",dto3.getItemResultXss());
                //便检
                str=str.replaceAll("###3bianjian###",dto3.getItemResultBj());
                //肝功能
                str=str.replaceAll("###3gangongneng###",dto3.getItemResultGg());
                //PPD
                str=str.replaceAll("###3ppd###",dto3.getItemResultPpd());
                //既往病史
                str=str.replaceAll("###bingshi###",dto3.getItemResultBs());
                //体验机构
                str=str.replaceAll("###3tijianjigou###",dto3.getItemResultJg());
            }
        }
    }

    private void fillHealthDataShhet(List<HealthDataDto> singleHealthData, HSSFWorkbook wb) {
        HSSFSheet sheetGP3 = wb.getSheet("学生体质健康数据明细");
        if (singleHealthData != null && singleHealthData.size() > 0) {
            String yearGrade = "";
            int mtcount = 0;
            Double yearScore1 = 0.00;
            Double yearScore2 = 0.00;
            Double yearScore3 = 0.00;
            int count = 0;
            for (HealthDataDto hhDto : singleHealthData) {
                if ("一".equals(hhDto.getGradeNum())) {
                    //身高(第一学年)
                    getHSSFCell(sheetGP3, 5, 1, hhDto.getSgItemMark());//成绩
                    getHSSFCell(sheetGP3, 6, 1, hhDto.getTzItemMark());//体重成绩
                    getHSSFCell(sheetGP3, 5, 2, hhDto.getSgItemScore());//得分
                    getHSSFCell(sheetGP3, 5, 3, hhDto.getSgItemGrade());//等级
                    //肺活量(第一学年)
                    getHSSFCell(sheetGP3, 7, 1, hhDto.getFhlItemMark());//成绩
                    getHSSFCell(sheetGP3, 7, 2, hhDto.getFhlItemScore());//得分
                    getHSSFCell(sheetGP3, 7, 3, hhDto.getFhlItemGrade());//等级
                    //测试项1(第一学年)
                    getHSSFCell(sheetGP3, 9, 0, hhDto.getCs1ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 9, 1, hhDto.getCs1ItemMark());//成绩
                    getHSSFCell(sheetGP3, 9, 2, hhDto.getCs1ItemScore());//得分
                    getHSSFCell(sheetGP3, 9, 3, hhDto.getCs1ItemGrade());//等级
                    //测试项2(第一学年)
                    getHSSFCell(sheetGP3, 10, 0, hhDto.getCs2ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 10, 1, hhDto.getCs2ItemMark());//成绩
                    getHSSFCell(sheetGP3, 10, 2, hhDto.getCs2ItemScore());//得分
                    getHSSFCell(sheetGP3, 10, 3, hhDto.getCs2ItemGrade());//等级
                    //测试项3(第一学年)
                    getHSSFCell(sheetGP3, 11, 0, hhDto.getCs3ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 11, 1, hhDto.getCs3ItemMark());//成绩
                    getHSSFCell(sheetGP3, 11, 2, hhDto.getCs3ItemScore());//得分
                    getHSSFCell(sheetGP3, 11, 3, hhDto.getCs3ItemGrade());//等级
                    //测试项4(第一学年)
                    getHSSFCell(sheetGP3, 12, 0, hhDto.getCs4ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 12, 1, hhDto.getCs4ItemMark());//成绩
                    getHSSFCell(sheetGP3, 12, 2, hhDto.getCs4ItemScore());//得分
                    getHSSFCell(sheetGP3, 12, 3, hhDto.getCs4ItemGrade());//等级
                    //测试项5(第一学年)
                    getHSSFCell(sheetGP3, 13, 0, hhDto.getCs5ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 13, 1, hhDto.getCs5ItemMark());//成绩
                    getHSSFCell(sheetGP3, 13, 2, hhDto.getCs5ItemScore());//得分
                    getHSSFCell(sheetGP3, 13, 3, hhDto.getCs5ItemGrade());//等级
                    //奖励的分
                    getHSSFCell(sheetGP3, 14, 1, hhDto.getRewardScore());
                    //学年总分
                    getHSSFCell(sheetGP3, 15, 1, hhDto.getYearScore());
                    //等级评定
                    getHSSFCell(sheetGP3, 16, 1, hhDto.getYearGrade());
                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore1 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                } else if ("二".equals(hhDto.getGradeNum())) {
                    //身高(第二学年)
                    getHSSFCell(sheetGP3, 5, 5, hhDto.getSgItemMark());//成绩
                    getHSSFCell(sheetGP3, 6, 5, hhDto.getTzItemMark());//体重成绩
                    getHSSFCell(sheetGP3, 5, 6, hhDto.getSgItemScore());//得分
                    getHSSFCell(sheetGP3, 5, 7, hhDto.getSgItemGrade());//等级
                    //肺活量(第二学年)
                    getHSSFCell(sheetGP3, 7, 5, hhDto.getFhlItemMark());//成绩
                    getHSSFCell(sheetGP3, 7, 6, hhDto.getFhlItemScore());//得分
                    getHSSFCell(sheetGP3, 7, 7, hhDto.getFhlItemGrade());//等级
                    //测试项1(第二学年)
                    getHSSFCell(sheetGP3, 9, 4, hhDto.getCs1ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 9, 5, hhDto.getCs1ItemMark());//成绩
                    getHSSFCell(sheetGP3, 9, 6, hhDto.getCs1ItemScore());//得分
                    getHSSFCell(sheetGP3, 9, 7, hhDto.getCs1ItemGrade());//等级
                    //测试项2(第二学年)
                    getHSSFCell(sheetGP3, 10, 4, hhDto.getCs2ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 10, 5, hhDto.getCs2ItemMark());//成绩
                    getHSSFCell(sheetGP3, 10, 6, hhDto.getCs2ItemScore());//得分
                    getHSSFCell(sheetGP3, 10, 7, hhDto.getCs2ItemGrade());//等级
                    //测试项3(第二学年)
                    getHSSFCell(sheetGP3, 11, 4, hhDto.getCs3ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 11, 5, hhDto.getCs3ItemMark());//成绩
                    getHSSFCell(sheetGP3, 11, 6, hhDto.getCs3ItemScore());//得分
                    getHSSFCell(sheetGP3, 11, 7, hhDto.getCs3ItemGrade());//等级
                    //测试项4(第二学年)
                    getHSSFCell(sheetGP3, 12, 4, hhDto.getCs4ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 12, 5, hhDto.getCs4ItemMark());//成绩
                    getHSSFCell(sheetGP3, 12, 6, hhDto.getCs4ItemScore());//得分
                    getHSSFCell(sheetGP3, 12, 7, hhDto.getCs4ItemGrade());//等级
                    //测试项5(第二学年)
                    getHSSFCell(sheetGP3, 13, 4, hhDto.getCs5ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 13, 5, hhDto.getCs5ItemMark());//成绩
                    getHSSFCell(sheetGP3, 13, 6, hhDto.getCs5ItemScore());//得分
                    getHSSFCell(sheetGP3, 13, 7, hhDto.getCs5ItemGrade());//等级
                    //奖励的分
                    getHSSFCell(sheetGP3, 14, 4, hhDto.getRewardScore());
                    //学年总分
                    getHSSFCell(sheetGP3, 15, 4, hhDto.getYearScore());
                    //等级评定
                    getHSSFCell(sheetGP3, 16, 4, hhDto.getYearGrade());
                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore2 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                } else if ("三".equals(hhDto.getGradeNum())) {
                    //身高(第三学年)
                    getHSSFCell(sheetGP3, 5, 9, hhDto.getSgItemMark());//成绩
                    getHSSFCell(sheetGP3, 6, 9, hhDto.getTzItemMark());//体重成绩
                    getHSSFCell(sheetGP3, 5, 10, hhDto.getSgItemScore());//得分
                    getHSSFCell(sheetGP3, 5, 11, hhDto.getSgItemGrade());//等级
                    //肺活量(第三学年)
                    getHSSFCell(sheetGP3, 7, 9, hhDto.getFhlItemMark());//成绩
                    getHSSFCell(sheetGP3, 7, 10, hhDto.getFhlItemScore());//得分
                    getHSSFCell(sheetGP3, 7, 11, hhDto.getFhlItemGrade());//等级
                    //测试项1(第三学年)
                    getHSSFCell(sheetGP3, 9, 8, hhDto.getCs1ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 9, 9, hhDto.getCs1ItemMark());//成绩
                    getHSSFCell(sheetGP3, 9, 10, hhDto.getCs1ItemScore());//得分
                    getHSSFCell(sheetGP3, 9, 11, hhDto.getCs1ItemGrade());//等级
                    //测试项2(第三学年)
                    getHSSFCell(sheetGP3, 10, 8, hhDto.getCs2ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 10, 9, hhDto.getCs2ItemMark());//成绩
                    getHSSFCell(sheetGP3, 10, 10, hhDto.getCs2ItemScore());//得分
                    getHSSFCell(sheetGP3, 10, 11, hhDto.getCs2ItemGrade());//等级
                    //测试项3(第三学年)
                    getHSSFCell(sheetGP3, 11, 8, hhDto.getCs3ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 11, 9, hhDto.getCs3ItemMark());//成绩
                    getHSSFCell(sheetGP3, 11, 10, hhDto.getCs3ItemScore());//得分
                    getHSSFCell(sheetGP3, 11, 11, hhDto.getCs3ItemGrade());//等级
                    //测试项4(第三学年)
                    getHSSFCell(sheetGP3, 12, 8, hhDto.getCs4ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 12, 9, hhDto.getCs4ItemMark());//成绩
                    getHSSFCell(sheetGP3, 12, 10, hhDto.getCs4ItemScore());//得分
                    getHSSFCell(sheetGP3, 12, 11, hhDto.getCs4ItemGrade());//等级
                    //测试项5(第三学年)
                    getHSSFCell(sheetGP3, 13, 8, hhDto.getCs5ItemName());//测试项名称
                    getHSSFCell(sheetGP3, 13, 9, hhDto.getCs5ItemMark());//成绩
                    getHSSFCell(sheetGP3, 13, 10, hhDto.getCs5ItemScore());//得分
                    getHSSFCell(sheetGP3, 13, 11, hhDto.getCs5ItemGrade());//等级
                    //奖励的分
                    getHSSFCell(sheetGP3, 14, 8, hhDto.getRewardScore());
                    //学年总分
                    getHSSFCell(sheetGP3, 15, 8, hhDto.getYearScore());
                    //等级评定
                    getHSSFCell(sheetGP3, 16, 8, hhDto.getYearGrade());
                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore3 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                }
            }
            if (count == 3 && !yearGrade.contains("免体")) {
                //计算成绩
                Double totalScore = 0.5 * yearScore3 + 0.5 * (yearScore1 + yearScore2) / 2;
                BigDecimal b = new BigDecimal(totalScore);
                totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //四舍五入后的值
                //总得分
                getHSSFCell(sheetGP3, 5, 12, String.valueOf(totalScore));
                //等级
                String totalGrade = "";
                if (totalScore >= 90.0) {
                    totalGrade = "优秀";
                } else if (totalScore >= 75.0 && totalScore < 90.0) {
                    totalGrade = "良好";
                } else if (totalScore >= 60.0 && totalScore < 75.0) {
                    totalGrade = "及格";
                } else if (totalScore < 60.0) {
                    totalGrade = "不及格";
                }
                getHSSFCell(sheetGP3, 5, 13, totalGrade);
            } else if (count == 3 && mtcount == 1) {
                //计算成绩
                Double totalScore = 0.5 * (yearScore3 + yearScore1 + yearScore2);
                BigDecimal b = new BigDecimal(totalScore);
                totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //四舍五入后的值
                //总得分
                getHSSFCell(sheetGP3, 5, 12, String.valueOf(totalScore));
                //等级
                String totalGrade = "";
                if (totalScore >= 90.0) {
                    totalGrade = "优秀";
                } else if (totalScore >= 75.0 && totalScore < 90.0) {
                    totalGrade = "良好";
                } else if (totalScore >= 60.0 && totalScore < 75.0) {
                    totalGrade = "及格";
                } else if (totalScore < 60.0) {
                    totalGrade = "不及格";
                }
                getHSSFCell(sheetGP3, 5, 13, totalGrade);
            }
        }
    }
    private void fillHealthDataHtml(List<HealthDataDto> singleHealthData, String str) {
        if (singleHealthData != null && singleHealthData.size() > 0) {
            String yearGrade = "";
            int mtcount = 0;
            Double yearScore1 = 0.00;
            Double yearScore2 = 0.00;
            Double yearScore3 = 0.00;
            int count = 0;
            for (HealthDataDto hhDto : singleHealthData) {
                if ("一".equals(hhDto.getGradeNum())) {
                    //身高(第一学年)
                    str=str.replaceAll("###1highs###",hhDto.getSgItemMark());
                    str=str.replaceAll("###1weight###",hhDto.getTzItemMark());
                    str=str.replaceAll("###1score###",hhDto.getSgItemScore());
                    str=str.replaceAll("###1dengji###",hhDto.getSgItemGrade());
                    //肺活量(第一学年)
                    str=str.replaceAll("###1feihuochengji###",hhDto.getFhlItemMark());
                    str=str.replaceAll("###1feihuofenshu###",hhDto.getFhlItemScore());
                    str=str.replaceAll("###1feihuodengji###",hhDto.getFhlItemGrade());
                    //测试项1(第一学年)
                    str=str.replaceAll("###1tiaoyuanchengji###",hhDto.getCs1ItemMark());
                    str=str.replaceAll("###1tiaoyuanfenshu###",hhDto.getCs1ItemScore());
                    str=str.replaceAll("###1tiaoyuandengji###",hhDto.getCs1ItemGrade());
                    //奖励的分
                    str=str.replaceAll("###1jianglidefen###",hhDto.getRewardScore());
                    //学年总分
                    str=str.replaceAll("###1xuenianzongfen###",hhDto.getYearScore());
                    //等级评定
                    str=str.replaceAll("###1dengjipingding###",hhDto.getYearGrade());
                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore1 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                } else if ("二".equals(hhDto.getGradeNum())) {
                    //身高(第二学年)
                    //身高(第一学年)
                    str=str.replaceAll("###2highs###",hhDto.getSgItemMark());
                    str=str.replaceAll("###2weight###",hhDto.getTzItemMark());
                    str=str.replaceAll("###2score###",hhDto.getSgItemScore());
                    str=str.replaceAll("###2dengji###",hhDto.getSgItemGrade());
                    //肺活量(第一学年)
                    str=str.replaceAll("###2feihuochengji###",hhDto.getFhlItemMark());
                    str=str.replaceAll("###2feihuofenshu###",hhDto.getFhlItemScore());
                    str=str.replaceAll("###2feihuodengji###",hhDto.getFhlItemGrade());
                    //测试项1(第一学年)
                    str=str.replaceAll("###2tiaoyuanchengji###",hhDto.getCs1ItemMark());
                    str=str.replaceAll("###2tiaoyuanfenshu###",hhDto.getCs1ItemScore());
                    str=str.replaceAll("###2tiaoyuandengji###",hhDto.getCs1ItemGrade());
                    //奖励的分
                    str=str.replaceAll("###2jianglidefen###",hhDto.getRewardScore());
                    //学年总分
                    str=str.replaceAll("###2xuenianzongfen###",hhDto.getYearScore());
                    //等级评定
                    str=str.replaceAll("###2dengjipingding###",hhDto.getYearGrade());
                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore2 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                } else if ("三".equals(hhDto.getGradeNum())) {
                    //身高(第三学年)
                    //身高(第一学年)
                    str=str.replaceAll("###3highs###",hhDto.getSgItemMark());
                    str=str.replaceAll("###3weight###",hhDto.getTzItemMark());
                    str=str.replaceAll("###3score###",hhDto.getSgItemScore());
                    str=str.replaceAll("###3dengji###",hhDto.getSgItemGrade());
                    //肺活量(第一学年)
                    str=str.replaceAll("###3feihuochengji###",hhDto.getFhlItemMark());
                    str=str.replaceAll("###3feihuofenshu###",hhDto.getFhlItemScore());
                    str=str.replaceAll("###3feihuodengji###",hhDto.getFhlItemGrade());
                    //测试项1(第一学年)
                    str=str.replaceAll("###3tiaoyuanchengji###",hhDto.getCs1ItemMark());
                    str=str.replaceAll("###3tiaoyuanfenshu###",hhDto.getCs1ItemScore());
                    str=str.replaceAll("###3tiaoyuandengji###",hhDto.getCs1ItemGrade());
                    //奖励的分
                    str=str.replaceAll("###3jianglidefen###",hhDto.getRewardScore());
                    //学年总
                    str=str.replaceAll("###3xuenianzongfen###",hhDto.getYearScore());
                    //等级评定
                    str=str.replaceAll("###3dengjipingding###",hhDto.getYearGrade());

                    yearGrade += hhDto.getYearGrade();
                    if (NestUtil.isNotEmpty(hhDto.getYearGrade()) && hhDto.getYearGrade().equals("免体")) {
                        mtcount++;
                    }
                    if (NestUtil.isNotEmpty(hhDto.getYearScore())) {
                        yearScore3 = Double.parseDouble(hhDto.getYearScore());
                    }
                    count++;
                }
            }
            if (count == 3 && !yearGrade.contains("免体")) {
                //计算成绩
                Double totalScore = 0.5 * yearScore3 + 0.5 * (yearScore1 + yearScore2) / 2;
                BigDecimal b = new BigDecimal(totalScore);
                totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //四舍五入后的值
                //总得分
                str=str.replaceAll("###avescore###",String.valueOf(totalScore));
                //等级
                String totalGrade = "";
                if (totalScore >= 90.0) {
                    totalGrade = "优秀";
                } else if (totalScore >= 75.0 && totalScore < 90.0) {
                    totalGrade = "良好";
                } else if (totalScore >= 60.0 && totalScore < 75.0) {
                    totalGrade = "及格";
                } else if (totalScore < 60.0) {
                    totalGrade = "不及格";
                }
                str=str.replaceAll("###avedegree###",totalGrade);
            } else if (count == 3 && mtcount == 1) {
                //计算成绩
                Double totalScore = 0.5 * (yearScore3 + yearScore1 + yearScore2);
                BigDecimal b = new BigDecimal(totalScore);
                totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //四舍五入后的值
                //总得分
                str=str.replaceAll("###avescore###",String.valueOf(totalScore));
                //等级
                String totalGrade = "";
                if (totalScore >= 90.0) {
                    totalGrade = "优秀";
                } else if (totalScore >= 75.0 && totalScore < 90.0) {
                    totalGrade = "良好";
                } else if (totalScore >= 60.0 && totalScore < 75.0) {
                    totalGrade = "及格";
                } else if (totalScore < 60.0) {
                    totalGrade = "不及格";
                }
                str=str.replaceAll("###avedegree###",totalGrade);
            }
        }
    }

    private void fillStudyAppraiseSheet(
            List<AppraisalDto> outSingleStudyAppral, HSSFWorkbook wb) {
        HSSFSheet sheetGP5 = wb.getSheet("研究性学习");
        if (outSingleStudyAppral != null && outSingleStudyAppral.size() > 0) {
            int tCount = 1;
            int aCount = 2;
            for (AppraisalDto aDto : outSingleStudyAppral) {
                getHSSFCell(sheetGP5, tCount, 5, aDto.getTopic());
                tCount += 3;
                getHSSFCell(sheetGP5, aCount, 5, aDto.getApprasial());
                aCount += 3;
            }
        }
    }

    private void fillPersonalAppraiseSheet(AppraisalDto outSinglePersonalAppraiseSelf, List<AppraisalDto> outSinglePersonalAppraiseExtra, HSSFWorkbook wb) {
        //创建个性发展
        HSSFSheet sheetGP1 = wb.getSheet("个性发展");
        if (null != outSinglePersonalAppraiseSelf) {
            //个性发展自我评价
            getHSSFCell(sheetGP1, 2, 3, outSinglePersonalAppraiseSelf.getApprasial());
        }
        //特长成果
        if (outSinglePersonalAppraiseExtra != null && outSinglePersonalAppraiseExtra.size() > 0) {
            int count = 10;
            for (AppraisalDto aDto : outSinglePersonalAppraiseExtra) {
                getHSSFCell(sheetGP1, count, 3, aDto.getApprasial());
                count += 3;
            }
        }
    }

    private void fillMasterAppraiseSheet(List<AppraisalDto> masterAppraisal, HSSFWorkbook wb) {
        HSSFSheet sheetGP11 = wb.getSheet("评语表");
        if (masterAppraisal != null && masterAppraisal.size() > 0) {
            int Count = 5;
            for (AppraisalDto aDto : masterAppraisal) {
                if ("1".equals(aDto.getGradeNum())) {
                    getHSSFCell(sheetGP11, 5, 3, aDto.getApprasial());
                } else if ("2".equals(aDto.getGradeNum())) {
                    getHSSFCell(sheetGP11, 13, 3, aDto.getApprasial());
                } else if ("3".equals(aDto.getGradeNum())) {
                    getHSSFCell(sheetGP11, 21, 3, aDto.getApprasial());
                }
            }
        }
    }

    private void fillCoverSheet(StudentDto sDto, HSSFWorkbook wb) {
        //创建报告单封面
        HSSFSheet sheetGP0 = wb.getSheet("报告单封面");
        //编号
        getHSSFCell(sheetGP0, 1, 6, sDto.getInschoolid());
        //姓名
        getHSSFCell(sheetGP0, 21, 2, sDto.getName());
        //性别
        getHSSFCell(sheetGP0, 23, 2, sDto.getSexname());
        //年龄
        if (sDto.getAge() != null) {
            getHSSFCell(sheetGP0, 23, 4, sDto.getAge().toString());
        }
        //政治面目
        getHSSFCell(sheetGP0, 23, 6, sDto.getEasyName());
        //班级
        if (sDto.getClassname() != null) {
            getHSSFCell(sheetGP0, 25, 2, sDto.getClassname());
        }
        //学籍号
        getHSSFCell(sheetGP0, 25, 4, sDto.getStudentno());
        //毕业学校
        getHSSFCell(sheetGP0, 27, 2, sDto.getSchoolName());
    }

    @DataSource("read")
    public List<AppraisalDto> queryMasterAppral(Map<String, Object> params) {
        try {
            //班主任评语信息
            return this.findList("IMasterReportExt.queryMasterAppral.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    AppraisalDto dto = new AppraisalDto();
                    dto.setApprasial(replaceEsc(rs.getString("assesscontent")));
                    dto.setEduid(rs.getString("edu_id"));
                    dto.setGradeNum(rs.getString("gradenum"));
                    dto.setTermType(rs.getString("term"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryMasterAppral(Map<String,Object>)", e);
        }
        return null;
    }

    @Override
    public List<AppraisalDto> initSingleMasterAppraise(StudentDto sDto, List<AppraisalDto> masterAppraise) {
        if (null != masterAppraise && masterAppraise.size() > 0) {
            List<AppraisalDto> singleMasterAppraise = new ArrayList<AppraisalDto>();

            Map<String, AppraisalDto> map = new HashMap<String, AppraisalDto>();
            for (AppraisalDto aDto : masterAppraise) {
                String grade = aDto.getGradeNum(), term = aDto.getTermType();
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(aDto.getEduid())) {
                    if (aDto.getApprasial() != null && !aDto.getApprasial().equals(""))
                        map.put(grade + term, aDto);
                }
            }
            if (map.get("12") != null && !map.get("12").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("12"));
            } else if (map.get("11") != null && !map.get("11").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("11"));
            }
            if (map.get("22") != null && !map.get("22").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("22"));
            } else if (map.get("21") != null && !map.get("21").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("21"));
            }
            if (map.get("32") != null && !map.get("32").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("32"));
            } else if (map.get("31") != null && !map.get("31").getApprasial().equals("")) {
                singleMasterAppraise.add(map.get("31"));
            }
            return singleMasterAppraise;
        }
        return null;
    }

    private void getFirstTerm(StudentDto sDto, List<AppraisalDto> masterAppraise, List<AppraisalDto> singleMasterAppraise) {
        if (singleMasterAppraise.size() < 3) {
            for (AppraisalDto aDto : masterAppraise) {
                String termId = aDto.getGradeNum() + aDto.getTermType();
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(aDto.getEduid())) {
                    //获取高一上学期数据
                    if ("11".equals(termId) && !this.containsSecondTerm(singleMasterAppraise, "12")) {
                        singleMasterAppraise.add(aDto);
                    } else if ("21".equals(termId) && !this.containsSecondTerm(singleMasterAppraise, "22")) {//获取高二上学期数据
                        singleMasterAppraise.add(aDto);
                    } else if ("31".equals(termId) && !this.containsSecondTerm(singleMasterAppraise, "32")) {//获取高三上学期数据
                        singleMasterAppraise.add(aDto);
                    }
                    if (singleMasterAppraise.size() == 3) break;
                }
            }
        }
    }

    /**
     * 判断是否已经有了第二学期的数据
     *
     * @param singleMasterAppraise
     * @param termType
     * @return
     */
    private boolean containsSecondTerm(List<AppraisalDto> singleMasterAppraise, String termType) {
        boolean isContans = false;
        if (singleMasterAppraise != null && singleMasterAppraise.size() > 0) {
            for (AppraisalDto aDto : singleMasterAppraise) {
                if (termType.equals(aDto.getGradeNum() + aDto.getTermType())) {
                    isContans = true;
                    break;
                }
            }
        }
        return isContans;
    }

    private void getSecondTerm(StudentDto sDto, List<AppraisalDto> masterAppraise, List<AppraisalDto> singleMasterAppraise) {
        for (AppraisalDto aDto : masterAppraise) {
            String termId = aDto.getGradeNum() + aDto.getTermType();
            if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(aDto.getEduid())) {
                //获取高一下学期数据
                if ("12".equals(termId)) {
                    singleMasterAppraise.add(aDto);
                } else if ("22".equals(termId)) {//获取高二下学期数据
                    singleMasterAppraise.add(aDto);
                } else if ("32".equals(termId)) {//获取高三下学期数据
                    singleMasterAppraise.add(aDto);
                }
                if (singleMasterAppraise.size() == 3) break;
            }
        }
    }

    public List<AppraisalDto> queryOutPersonalAppral(Map<String, Object> params, String flag) {
        try {
            //获取个性发展(输出数据)
            String preSQL = "";
            if ("self".equals(flag)) {
                preSQL = "IMasterReportExt.queryOutPersonalAppralSelf.query";
            } else {
                preSQL = "IMasterReportExt.queryOutPersonalAppralExtra.query";
            }
            return this.findList(preSQL, params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    AppraisalDto dto = new AppraisalDto();
                    dto.setEduid(rs.getString("eduid"));
                    dto.setApprasial(replaceEsc(replaceEsc(rs.getString("description"))));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryOutPersonalAppral(Map<String, Object>)", e);
        }
        return null;
    }

    @DataSource("read")
    public List<AppraisalDto> queryOutStudyAppral(Map<String, Object> params) {
        try {
            //研究性学习(输出数据)
            return this.findList("IMasterReportExt.queryOutStudyAppral.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    AppraisalDto dto = new AppraisalDto();
                    dto.setEduid(rs.getString("eduid"));
                    dto.setApprasial(replaceEsc(rs.getString("description")));
                    dto.setTopic(replaceEsc(rs.getString("title")));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryOutStudyAppral(Map<String, Object>)", e);
        }
        return null;
    }

    @Override
    public AppraisalDto initOutSinglePersonalAppraiseSelf(List<AppraisalDto> outPersonalAppralSelf, StudentDto sDto) {
        if (null != outPersonalAppralSelf && outPersonalAppralSelf.size() > 0) {
            for (AppraisalDto aDto : outPersonalAppralSelf) {
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(aDto.getEduid())) {
                    return aDto;
                }
            }
        }
        return null;
    }

    @Override
    public List<AppraisalDto> initOutSinglePersonalAppraiseExtra(List<AppraisalDto> outPersonalAppralSelf, StudentDto sDto) {
        if (null != outPersonalAppralSelf && outPersonalAppralSelf.size() > 0) {
            List<AppraisalDto> retData = new ArrayList<AppraisalDto>();
            for (AppraisalDto aDto : outPersonalAppralSelf) {
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(aDto.getEduid())) {
                    retData.add(aDto);
                }
                if (retData.size() == 3) break;
            }
            return retData;
        }
        return null;
    }

    @DataSource("read")
    public List<HealthDataDto> queryHealthDdatas(Map<String, Object> params) {
        try {
            //研究性学习(输出数据)
            return this.findList("IMasterReportExt.queryHealthDdatas.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    HealthDataDto dto = new HealthDataDto();
                    dto.setGradeNum(rs.getString("gradenum"));
                    dto.setGradeName(rs.getString("gradename"));
                    dto.setEduId(rs.getString("edu_id"));
                    dto.setName(rs.getString("name"));
                    dto.setRewardScore(rs.getString("reward_score"));
                    dto.setYearScore(rs.getString("year_score"));
                    dto.setYearGrade(rs.getString("year_grade"));
                    dto.setAllContent(rs.getString("dataDetails"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryHealthDdatas(Map<String, Object>)", e);
        }
        return null;
    }

    @Override
    public List<HealthDataDto> querySingleHealthData(StudentDto sDto, List<HealthDataDto> healthDatas) {
        if (null != healthDatas && healthDatas.size() > 0) {
            List<HealthDataDto> singleHealthData = new ArrayList<HealthDataDto>();
            for (HealthDataDto hhDto : healthDatas) {
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(hhDto.getEduId())) {
                    String allDatas = hhDto.getAllContent();
                    String[] singleItems = allDatas.split(",");
                    if (null != singleItems && singleItems.length > 0) {
                        HealthDataDto hDto = new HealthDataDto();
                        for (String item : singleItems) {//单项
                            String[] items = item.split("@");//单项详细信息
                            if (null != items && items.length == 5) {
                                String sg = "";
                                String tz = "";
                                String itemName = !"-1".equals(items[0]) ? items[0] : "";
                                String itemType = !"-1".equals(items[1]) ? items[1] : "";
                                String itemMark = !"-1".equals(items[2]) ? items[2] : "";
                                String itemScore = !"-1".equals(items[3]) ? items[3] : "";
                                String itemGrade = !"-1".equals(items[4]) ? items[4] : "";

                                if ("1".equals(itemType) && NestUtil.isNotEmpty(itemMark)) {//身高标准体重
                                    if (StringUtil.isNotEmpty(itemMark) && itemMark.lastIndexOf("/") != -1) {
                                        sg = itemMark.substring(0, itemMark.lastIndexOf("/"));
                                        tz = itemMark.substring(itemMark.lastIndexOf("/") + 1, itemMark.length());
                                    }
                                    hDto.setSgItemMark(sg);
                                    hDto.setTzItemMark(tz);
                                    hDto.setSgItemScore(itemScore);
                                    hDto.setSgItemGrade(itemGrade);
                                } else if ("2".equals(itemType)) {//肺活量体重指数
                                    hDto.setFhlItemMark(itemMark);
                                    hDto.setFhlItemScore(itemScore);
                                    hDto.setFhlItemGrade(itemGrade);
                                } else if (!"2".equals(itemType)
                                        && !"1".equals(itemType)
                                        && !NestUtil.isNotEmpty(hDto.getCs1ItemName())) {
                                    hDto.setCs1ItemName(itemName);
                                    hDto.setCs1ItemMark(itemMark);
                                    hDto.setCs1ItemScore(itemScore);
                                    hDto.setCs1ItemGrade(itemGrade);
                                } else if (!"2".equals(itemType)
                                        && !"1".equals(itemType)
                                        && !NestUtil.isNotEmpty(hDto.getCs2ItemName())) {
                                    hDto.setCs2ItemName(itemName);
                                    hDto.setCs2ItemMark(itemMark);
                                    hDto.setCs2ItemScore(itemScore);
                                    hDto.setCs2ItemGrade(itemGrade);
                                } else if (!"2".equals(itemType)
                                        && !"1".equals(itemType)
                                        && !NestUtil.isNotEmpty(hDto.getCs3ItemName())) {
                                    hDto.setCs3ItemName(itemName);
                                    hDto.setCs3ItemMark(itemMark);
                                    hDto.setCs3ItemScore(itemScore);
                                    hDto.setCs3ItemGrade(itemGrade);
                                } else if (!"2".equals(itemType)
                                        && !"1".equals(itemType)
                                        && !NestUtil.isNotEmpty(hDto.getCs4ItemName())) {
                                    hDto.setCs4ItemName(itemName);
                                    hDto.setCs4ItemMark(itemMark);
                                    hDto.setCs4ItemScore(itemScore);
                                    hDto.setCs4ItemGrade(itemGrade);
                                } else if (!"2".equals(itemType)
                                        && !"1".equals(itemType)
                                        && !NestUtil.isNotEmpty(hDto.getCs5ItemName())) {
                                    hDto.setCs5ItemName(itemName);
                                    hDto.setCs5ItemMark(itemMark);
                                    hDto.setCs5ItemScore(itemScore);
                                    hDto.setCs5ItemGrade(itemGrade);
                                }
                                hDto.setRewardScore(hhDto.getRewardScore());
                                hDto.setYearScore(hhDto.getYearScore());
                                hDto.setYearGrade(hhDto.getYearGrade());
                                hDto.setGradeNum(hhDto.getGradeNum());
                            }
                        }
                        singleHealthData.add(hDto);
                    }
                }
            }
            return singleHealthData;
        }
        return null;
    }

    @DataSource("read")
    public List<CheckItemInfoDto> queryCheckItems(Map<String, Object> params) {
        try {
            //研究性学习(输出数据)
            return this.findList("IMasterReportExt.queryCheckItems.query1", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    CheckItemInfoDto dto = new CheckItemInfoDto();
                    dto.setEduId(rs.getString("edu_id"));
                    dto.setTermId(rs.getString("termId"));
                    dto.setItemResultJg(rs.getString("unit"));
                    dto.setItemResult(rs.getString("itemresult"));
                    dto.setResultStr(rs.getString("result"));
                    dto.setSubItemSort(rs.getString("subitemsort"));
                    dto.setItemResultBs(rs.getString("medicalrecord"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryCheckItems(Map<String, Object>)", e);
        }
        return null;
    }

    @Override
    public List<CheckItemInfoDto> querySigleCheckItems(StudentDto sDto, List<CheckItemInfoDto> checkItems) {
        int itemCount = 3;
        //健康体检表
        List<CheckItemInfoDto> checkDtoList = new ArrayList<CheckItemInfoDto>();
        //下学期
        CheckItemInfoDto downCheckDto = this.querySingleTermCheckItems(sDto, checkItems, "1", "2");
        if (downCheckDto == null || downCheckDto.getCountValues() < itemCount) {//上学期
            CheckItemInfoDto upCheckDto = this.querySingleTermCheckItems(sDto, checkItems, "1", "1");
            if (upCheckDto != null && upCheckDto.getCountValues() > 0) {
                upCheckDto = this.findCheckItemSl(sDto, checkItems, "1", "2", upCheckDto != null ? upCheckDto : new CheckItemInfoDto());
                checkDtoList.add(upCheckDto);
            } else {
                downCheckDto = this.findCheckItemSl(sDto, checkItems, "1", "1", downCheckDto != null ? downCheckDto : new CheckItemInfoDto());
                checkDtoList.add(downCheckDto);
            }
        } else {
            downCheckDto = this.findCheckItemSl(sDto, checkItems, "1", "1", downCheckDto != null ? downCheckDto : new CheckItemInfoDto());
            checkDtoList.add(downCheckDto);
        }

        CheckItemInfoDto downCheckDto2 = this.querySingleTermCheckItems(sDto, checkItems, "2", "2");
        if (downCheckDto2 == null || downCheckDto2.getCountValues() < itemCount) {//上学期
            CheckItemInfoDto upCheckDto2 = this.querySingleTermCheckItems(sDto, checkItems, "2", "1");
            if (upCheckDto2 != null && upCheckDto2.getCountValues() > 0) {
                upCheckDto2 = this.findCheckItemSl(sDto, checkItems, "2", "2", upCheckDto2 != null ? upCheckDto2 : new CheckItemInfoDto());
                checkDtoList.add(upCheckDto2);
            } else {
                downCheckDto2 = this.findCheckItemSl(sDto, checkItems, "2", "1", downCheckDto2 != null ? downCheckDto2 : new CheckItemInfoDto());
                checkDtoList.add(downCheckDto2);
            }
        } else {
            downCheckDto2 = this.findCheckItemSl(sDto, checkItems, "2", "1", downCheckDto2 != null ? downCheckDto2 : new CheckItemInfoDto());
            checkDtoList.add(downCheckDto2);
        }

        CheckItemInfoDto downCheckDto3 = this.querySingleTermCheckItems(sDto, checkItems, "3", "2");
        if (downCheckDto3 == null || downCheckDto3.getCountValues() < itemCount) {//上学期
            CheckItemInfoDto upCheckDto3 = this.querySingleTermCheckItems(sDto, checkItems, "3", "1");
            if (upCheckDto3 != null && upCheckDto3.getCountValues() > 0) {
                upCheckDto3 = this.findCheckItemSl(sDto, checkItems, "3", "2", upCheckDto3 != null ? upCheckDto3 : new CheckItemInfoDto());
                checkDtoList.add(upCheckDto3);
            } else {
                downCheckDto3 = this.findCheckItemSl(sDto, checkItems, "3", "1", downCheckDto3 != null ? downCheckDto3 : new CheckItemInfoDto());
                checkDtoList.add(downCheckDto3);
            }
        } else {
            downCheckDto3 = this.findCheckItemSl(sDto, checkItems, "3", "1", downCheckDto3 != null ? downCheckDto3 : new CheckItemInfoDto());
            checkDtoList.add(downCheckDto3);
        }
        return checkDtoList;
    }

    private CheckItemInfoDto querySingleTermCheckItems(StudentDto sDto, List<CheckItemInfoDto> checkItems, String flag, String termNum) {
        if (null != checkItems && checkItems.size() > 0) {
            CheckItemInfoDto tempDto = new CheckItemInfoDto();
            int countValues = 0;
            for (CheckItemInfoDto ciDto : checkItems) {//获取每一个单项数据   组装成一个学段数据
                if (NestUtil.isNotEmpty(sDto.getEduid())
                        && sDto.getEduid().equals(ciDto.getEduId())
                        && NestUtil.isNotEmpty(ciDto.getTermId())
                        && (flag + termNum).equals(ciDto.getTermId())) {

                    Integer subItemSort = NestUtil.isNotEmpty(ciDto.getSubItemSort()) ? Integer.parseInt(ciDto.getSubItemSort()) : 0;
                    String itemResult = ciDto.getItemResult();
                    tempDto.setResultStr(ciDto.getResultStr());
                    tempDto.setItemResultJg(ciDto.getItemResultJg());
                    tempDto.setItemResultBs(ciDto.getItemResultBs());
                    if (subItemSort.compareTo(Integer.valueOf(8138001)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//身高
                        countValues++;
                        tempDto.setItemResultCm(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8138003)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//体重
                        countValues++;
                        tempDto.setItemResultKg(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8138011)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//肺活量
                        countValues++;
                        tempDto.setItemResultMl(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8138007)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//血压（低）
                        countValues++;
                        tempDto.setItemResultMmHgDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8138009)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//血压（高）
                        countValues++;
                        tempDto.setItemResultMmHgUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8138005)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//脉搏
                        countValues++;
                        tempDto.setItemResultMb(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132001)) == 0 && "1".equals(termNum)) {//上学期裸眼视力（左）
                        tempDto.setItemResultLySlLeftUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132003)) == 0 && "1".equals(termNum)) {//上学期裸眼视力（右）
                        tempDto.setItemResultLySlRightUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132001)) == 0 && "2".equals(termNum)) {//下学期裸眼视力（左）
                        tempDto.setItemResultLySlLeftDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132003)) == 0 && "2".equals(termNum)) {//下学期裸眼视力（右）
                        tempDto.setItemResultLySlRightDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132005)) == 0 && "1".equals(termNum)) {//上学期近视力（左）
                        tempDto.setItemResultJSlLeftUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132007)) == 0 && "1".equals(termNum)) {//上学期近视力（右）
                        tempDto.setItemResultJSlRightUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132005)) == 0 && "2".equals(termNum)) {//下学期近视力（左）
                        tempDto.setItemResultJSlLeftDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132007)) == 0 && "2".equals(termNum)) {//下学期近视力（右）
                        tempDto.setItemResultJSlRightDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132004)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//色觉
                        countValues++;
                        tempDto.setItemResultSj(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132002)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//沙眼
                        countValues++;
                        tempDto.setItemResultSy(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8130001)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//心脏
                        countValues++;
                        tempDto.setItemResultXz(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8130005)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//肺
                        countValues++;
                        tempDto.setItemResultF(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8130009)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//肝
                        countValues++;
                        tempDto.setItemResultGan(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8130002)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//脾
                        countValues++;
                        tempDto.setItemResultPi(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8137002)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//胸部
                        countValues++;
                        tempDto.setItemResultXb(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8131007)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//脊柱
                        countValues++;
                        tempDto.setItemResultJz(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8131009)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//四肢
                        countValues++;
                        tempDto.setItemResultSz(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8131005)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//皮肤
                        countValues++;
                        tempDto.setItemResultPf(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132012)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//结膜炎
                        countValues++;
                        tempDto.setItemResultJmy(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8136019)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//便检
                        countValues++;
                        tempDto.setItemResultBj(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8136020)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//肝功能
                        countValues++;
                        tempDto.setItemResultGg(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8136021)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//PPD
                        countValues++;
                        tempDto.setItemResultPpd(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8136004)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//血色素
                        countValues++;
                        tempDto.setItemResultXss(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8131012)) == 0 && itemResult != null && itemResult.trim().length() > 0) {//头颈
                        countValues++;
                        tempDto.setItemResultTj(itemResult);
                    }
                }
            }
            tempDto.setCountValues(Integer.valueOf(countValues));
            return tempDto;
        }
        return null;
    }

    private CheckItemInfoDto findCheckItemSl(StudentDto sDto, List<CheckItemInfoDto> checkItems, String flag, String termNum, CheckItemInfoDto dto) {
        if (null != checkItems && checkItems.size() > 0) {
            for (CheckItemInfoDto ciDto : checkItems) {//获取每一个单项数据   组装成一个学段数据
                if (NestUtil.isNotEmpty(sDto.getEduid())
                        && sDto.getEduid().equals(ciDto.getEduId())
                        && NestUtil.isNotEmpty(ciDto.getTermId())
                        && (flag + termNum).equals(ciDto.getTermId())) {
                    Integer subItemSort = NestUtil.isNotEmpty(ciDto.getSubItemSort()) ? Integer.parseInt(ciDto.getSubItemSort()) : 0;
                    String itemResult = ciDto.getItemResult();
                    if (subItemSort.compareTo(Integer.valueOf(8132001)) == 0 && "1".equals(termNum)) {//上学期裸眼视力（左）
                        dto.setItemResultLySlLeftUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132003)) == 0 && "1".equals(termNum)) {//上学期裸眼视力（右）
                        dto.setItemResultLySlRightUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132001)) == 0 && "2".equals(termNum)) {//下学期裸眼视力（左）
                        dto.setItemResultLySlLeftDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132003)) == 0 && "2".equals(termNum)) {//下学期裸眼视力（右）
                        dto.setItemResultLySlRightDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132005)) == 0 && "1".equals(termNum)) {//上学期近视力（左）
                        dto.setItemResultJSlLeftUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132007)) == 0 && "1".equals(termNum)) {//上学期近视力（右）
                        dto.setItemResultJSlRightUp(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132005)) == 0 && "2".equals(termNum)) {//下学期近视力（左）
                        dto.setItemResultJSlLeftDown(itemResult);
                    } else if (subItemSort.compareTo(Integer.valueOf(8132007)) == 0 && "2".equals(termNum)) {//下学期近视力（右）
                        dto.setItemResultJSlRightDown(itemResult);
                    }
                }
            }
            return dto;
        }
        return null;
    }

    public RstudentDto checkExcelFile(File ff, List<SchoolTreeDto> studentInfo) throws ManagerException {
        HSSFWorkbook wb = null;
        FileInputStream in = null;
        RstudentDto student = null;
        try {
            String fileName = ff.getName();
            //判断该文件是否为Excel文件
            int pointLen = fileName.lastIndexOf(".");
            if (pointLen != -1) {
                String kzName = fileName.substring(pointLen, fileName.length());
                if (!".xls".equals(kzName)) {
                    return null;
                }
            } else {
                return null;
            }
            int firstLen = fileName.lastIndexOf("（");
            int lastLen = fileName.lastIndexOf("）");
            String studentName = fileName.substring(0, firstLen);
            String studentNo = fileName.substring(firstLen + 1, lastLen);
            student = this.getStudent(studentName, studentNo, studentInfo);
            if (student != null) {
                in = new FileInputStream(ff);
                wb = new HSSFWorkbook(in);
                HSSFSheet sheetGP0 = wb.getSheetAt(0);
                HSSFCell cell = sheetGP0.getRow(1).getCell((short) 2);
                String versonNum = cell.getStringCellValue();
                if ((StringUtil.isNotEmpty(versonNum) && !"GPQAS1.0".equals(versonNum))
                        || StringUtil.isEmpty(versonNum)) {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("检查报告单excel文件错误：checkExcelFile(File)" + e.getMessage() + e.fillInStackTrace());
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return student;
    }

    public RstudentDto getStudent(String name, String studentNo, List<SchoolTreeDto> studentInfo) throws ManagerException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("getStudent(String,String) - start");
        }
        RstudentDto student = null;
        try {
            if (null != studentInfo && studentInfo.size() > 0) {
                for (SchoolTreeDto stDto : studentInfo) {
                    if (NestUtil.isNotEmpty(name)
                            && name.equals(stDto.getText())
                            && NestUtil.isNotEmpty(studentNo)
                            && studentNo.equals(stDto.getStudentno())) {
                        student = new RstudentDto();
                        student.setPid(stDto.getId());
                        student.setEduId(stDto.getEdusyId());
                    }
                }
            }
        } catch (Exception e) {
            throw new ManagerException();
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("getStudent(String,String) - end");
        }
        return student;
    }

    public RstudentDto getStudentInfos(File ff, RstudentDto student) throws ManagerException {
        HSSFWorkbook wb = null;
        FileInputStream in = null;
        try {
            if (student != null && StringUtil.isNotEmpty(student.getPid())) {
                in = new FileInputStream(ff);
                wb = new HSSFWorkbook(in);
                HSSFSheet sheetGP1 = wb.getSheet("个性发展");
                //个性发展自我评价
                SelfDto selfDto = new SelfDto();
                HSSFCell cell = sheetGP1.getRow(2).getCell((short) 3);
                selfDto.setDescription(ExcelReader.readStringExcelCell(cell));
                selfDto.setEduId(student.getEduId());
                selfDto.setStudentId(student.getPid());
                if (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell))
                        && ExcelReader.readStringExcelCell(cell).length() > 600) {
                    student.setErroMeg("个性发展自我评价内容超过了600字");
                }
                //个性发展特长及成果
                List<SelfDto> selfDtoList = new ArrayList<SelfDto>();
                SelfDto selfDto1 = new SelfDto();
                HSSFCell selfDto1Cell = sheetGP1.getRow(10).getCell((short) 3);
                selfDto1.setDescription(ExcelReader.readStringExcelCell(selfDto1Cell));
                selfDto1.setOrderby(1);
                selfDto1.setEduId(student.getEduId());
                selfDto1.setStudentId(student.getPid());
                selfDtoList.add(selfDto1);
                SelfDto selfDto2 = new SelfDto();
                HSSFCell selfDto2Cell = sheetGP1.getRow(13).getCell((short) 3);
                selfDto2.setDescription(ExcelReader.readStringExcelCell(selfDto2Cell));
                selfDto2.setOrderby(2);
                selfDto2.setEduId(student.getEduId());
                selfDto2.setStudentId(student.getPid());
                selfDtoList.add(selfDto2);
                SelfDto selfDto3 = new SelfDto();
                HSSFCell selfDto3Cell = sheetGP1.getRow(16).getCell((short) 3);
                selfDto3.setDescription(ExcelReader.readStringExcelCell(selfDto3Cell));
                selfDto3.setOrderby(3);
                selfDto3.setEduId(student.getEduId());
                selfDto3.setStudentId(student.getPid());
                selfDtoList.add(selfDto3);
                if ((NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(selfDto1Cell)) && ExcelReader.readStringExcelCell(selfDto1Cell).length() > 300)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(selfDto2Cell)) && ExcelReader.readStringExcelCell(selfDto2Cell).length() > 300)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(selfDto3Cell)) && ExcelReader.readStringExcelCell(selfDto3Cell).length() > 300)) {
                    student.setErroMeg(NestUtil.isNotEmpty(student.getErroMeg()) ? ",个性发展特长及成果内容超过了300字" : "个性发展特长及成果内容超过了300字");
                }
                HSSFSheet sheetGP5 = wb.getSheet("研究性学习");
                //研究性学习
                List<SelfDto> selfAppraisalDtoList = new ArrayList<SelfDto>();
                SelfDto selfAppraisalDto = new SelfDto();
                HSSFCell cell1 = sheetGP5.getRow(1).getCell((short) 5);
                selfAppraisalDto.setTitle(ExcelReader.readStringExcelCell(cell1));
                HSSFCell cell2 = sheetGP5.getRow(2).getCell((short) 5);
                selfAppraisalDto.setDescription(ExcelReader.readStringExcelCell(cell2));
                selfAppraisalDto.setOrderby(1);
                selfAppraisalDto.setEduId(student.getEduId());
                selfAppraisalDto.setStudentId(student.getPid());
                selfAppraisalDtoList.add(selfAppraisalDto);
                SelfDto selfAppraisalDto1 = new SelfDto();
                HSSFCell cell3 = sheetGP5.getRow(4).getCell((short) 5);
                selfAppraisalDto1.setTitle(ExcelReader.readStringExcelCell(cell3));
                HSSFCell cell4 = sheetGP5.getRow(5).getCell((short) 5);
                selfAppraisalDto1.setDescription(ExcelReader.readStringExcelCell(cell4));
                selfAppraisalDto1.setOrderby(2);
                selfAppraisalDto1.setEduId(student.getEduId());
                selfAppraisalDto1.setStudentId(student.getPid());
                selfAppraisalDtoList.add(selfAppraisalDto1);
                SelfDto selfAppraisalDto2 = new SelfDto();
                HSSFCell cell5 = sheetGP5.getRow(7).getCell((short) 5);
                selfAppraisalDto2.setTitle(ExcelReader.readStringExcelCell(cell5));
                HSSFCell cell6 = sheetGP5.getRow(8).getCell((short) 5);
                selfAppraisalDto2.setDescription(ExcelReader.readStringExcelCell(cell6));
                selfAppraisalDto2.setOrderby(3);
                selfAppraisalDto2.setEduId(student.getEduId());
                selfAppraisalDto2.setStudentId(student.getPid());
                selfAppraisalDtoList.add(selfAppraisalDto2);
                if ((NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell1)) && ExcelReader.readStringExcelCell(cell1).length() > 30)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell3)) && ExcelReader.readStringExcelCell(cell3).length() > 30)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell5)) && ExcelReader.readStringExcelCell(cell5).length() > 30)) {
                    student.setErroMeg(NestUtil.isNotEmpty(student.getErroMeg()) ? ",研究性学习标题内容超过了30字" : "研究性学习标题内容超过了30字");
                }
                if ((NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell2)) && ExcelReader.readStringExcelCell(cell2).length() > 700)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell4)) && ExcelReader.readStringExcelCell(cell4).length() > 700)
                        || (NestUtil.isNotEmpty(ExcelReader.readStringExcelCell(cell6)) && ExcelReader.readStringExcelCell(cell6).length() > 700)) {
                    student.setErroMeg(NestUtil.isNotEmpty(student.getErroMeg()) ? ",研究性学习内容超过了700字" : "研究性学习内容超过了700字");
                }
                in.close();
                return this.saveImportData(student, selfDto, selfDtoList, selfAppraisalDtoList);
            }
        } catch (ManagerException e) {
            throw new ManagerException();
        } catch (Exception e) {
            throw new ManagerException();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public RstudentDto saveImportData(RstudentDto student, SelfDto selfDto, List<SelfDto> selfDtoList, List<SelfDto> selfAppraisalDtoList) throws ManagerException {
        if (null != student) {
            student.setSelfDto(selfDto);
            student.setSelfDtoList(selfDtoList);
            student.setSelfAppraisalDtoList(selfAppraisalDtoList);
        }
        return student;
    }

    @Override
    public void saveImportData(List<String> eduIds, List<SelfDto> selfDtos, List<SelfDto> selfExtraDtos, List<SelfDto> appraiseDtos) {
        try {
            if (null != eduIds && eduIds.size() > 0) {
                Map<String, Object> params = new HashMap<String, Object>();
                //1.导入前先干掉表里的数据
                this.bachDeleteDatas(eduIds, params);
                //2.重新插入数据
                this.bachInsertDatas(params, selfDtos, selfExtraDtos, appraiseDtos);
            }
        } catch (Exception e) {
            logger.error("saveImportData(List<String>,List<SelfDto>,List<SelfDto>,List<SelfDto>)", e);
            //当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
            throw new ManagerException(e);
        }
    }

    private void bachInsertDatas(Map<String, Object> params, List<SelfDto> selfDtos, List<SelfDto> selfExtraDtos, List<SelfDto> appraiseDtos) throws Exception {
        //2.1个性自我评价
        this.bachInsertSelfData(params, selfDtos);
        //2.2个性成果展示
        this.bachInsertSelfExtraData(params, selfExtraDtos);
        //2.3研究性学习
        this.bachInsertSelfStudyData(params, appraiseDtos);
    }

    private void bachInsertSelfStudyData(Map<String, Object> params, final List<SelfDto> appraiseDtos) throws Exception {
        ISqlElement selfDelete = this.processSql(params, "IMasterReportExt.insertStudy.insert");
        getJdbcTemplate().batchUpdate(selfDelete.getSql(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SelfDto sDto = appraiseDtos.get(i);
                ps.setString(1, sDto.getStudentId());
                ps.setString(2, sDto.getTitle());
                ps.setString(3, sDto.getDescription());
                ps.setString(4, String.valueOf(sDto.getOrderby()));
                ps.setString(5, sDto.getEduId());
            }

            public int getBatchSize() {
                return appraiseDtos.size();
            }
        });
    }

    private void bachInsertSelfExtraData(Map<String, Object> params, final List<SelfDto> selfExtraDtos) throws Exception {
        ISqlElement selfDelete = this.processSql(params, "IMasterReportExt.insertPersonalSelfExtra.insert");
        getJdbcTemplate().batchUpdate(selfDelete.getSql(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SelfDto sDto = selfExtraDtos.get(i);
                ps.setString(1, sDto.getStudentId());
                ps.setString(2, sDto.getDescription());
                ps.setString(3, sDto.getEduId());
                ps.setString(4, String.valueOf(sDto.getOrderby()));
            }

            public int getBatchSize() {
                return selfExtraDtos.size();
            }
        });
    }

    private void bachInsertSelfData(Map<String, Object> params, final List<SelfDto> selfDtos) throws Exception {
        ISqlElement selfDelete = this.processSql(params, "IMasterReportExt.insertPersonalSelf.insert");
        getJdbcTemplate().batchUpdate(selfDelete.getSql(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SelfDto sDto = selfDtos.get(i);
                ps.setString(1, sDto.getStudentId());
                ps.setString(2, sDto.getDescription());
                ps.setString(3, sDto.getEduId());
            }

            public int getBatchSize() {
                return selfDtos.size();
            }
        });

    }

    private void bachDeleteDatas(final List<String> eduIds, Map<String, Object> params) throws Exception {
        String preSql = "";
        for (int i = 0; i < 3; i++) {
            if (i == 0) {//1.1个性自我评价
                preSql = "IMasterReportExt.deletePersonalSelf.delete";
            } else if (i == 1) {//1.2个性成果展示
                preSql = "IMasterReportExt.deletePersonalSelfExtra.delete";
            } else if (i == 2) {//1.3研究性学习
                preSql = "IMasterReportExt.deleteStudy.delete";
            }
            ISqlElement selfDelete = this.processSql(params, preSql);
            getJdbcTemplate().batchUpdate(selfDelete.getSql(), new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    String eduId = eduIds.get(i);
                    ps.setString(1, eduId);
                }

                public int getBatchSize() {
                    return eduIds.size();
                }
            });
        }
    }

    @Override
    public List<ModelScoreDto> queryAllScore(Map<String, Object> params) {
        try {
            ISqlElement selfDelete = this.processSql(params, "IMasterReportExt.queryAllScore.query");
            return this.findList("IMasterReportExt.queryAllScore.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    ModelScoreDto msDto = new ModelScoreDto();
                    msDto.setEdu_id(rs.getString("edu_id"));
                    msDto.setTermId(rs.getString("gradenum"));
                    msDto.setXbXDname(rs.getString("name"));
                    msDto.setXuhao(rs.getString("code"));
                    msDto.setNzOrXbSub(rs.getString("subject_name"));
                    msDto.setCourse_name(rs.getString("course_name"));
                    msDto.setCourse_kind(rs.getString("course_kind"));
                    msDto.setNzScore(rs.getString("examine_result"));
                    msDto.setCredit_hour(rs.getString("credit_hour"));
                    return msDto;
                }
            });
        } catch (Exception e) {
            logger.error("IMasterReportExt.queryAllScore(Map<String,Object>)", e);
        }
        return null;
    }

    @Override
    public Map<String, Map<String, List<ModelScoreDto>>> initSingleScore(StudentDto sDto, List<ModelScoreDto> allScore) {
        Map<String, Map<String, List<ModelScoreDto>>> singleScore = new HashMap<String, Map<String, List<ModelScoreDto>>>();
        if (null != allScore && allScore.size() > 0) {
            for (ModelScoreDto msDto : allScore) {
                String gradeNum = msDto.getTermId();
                if (NestUtil.isEmpty(gradeNum)) continue;
                if (Integer.parseInt(gradeNum) <= 0) continue;
                if (NestUtil.isNotEmpty(sDto.getEduid()) && sDto.getEduid().equals(msDto.getEdu_id())) {
                    if (Constant.KG_COURSE_BX.equals(msDto.getCourse_kind())
                            || Constant.KG_COURSE_BX1.equals(msDto.getCourse_kind())
                            || Constant.KG_COURSE_XX.equals(msDto.getCourse_kind())) {
                        this.putNZScore(msDto, singleScore);
                    } else if (Constant.KG_COURSE_KIND.equals(msDto.getCourse_kind())) {
                        this.putXBScore(msDto, singleScore);
                    }
                }
            }
        }
        this.initOtherDatas(singleScore);
        return singleScore;
    }

    private void initOtherDatas(Map<String, Map<String, List<ModelScoreDto>>> singleScore) {
        if (null != singleScore && singleScore.size() > 0) {
            List<String> subjects = this.initSubjects();
            Set<String> keySet = singleScore.keySet();
            for (String subject : subjects) {
                if (!keySet.contains(subject)) {
                    this.fillData(singleScore, subject);
                }
            }
        } else {
            List<String> subjects = this.initSubjects();
            for (String subject : subjects) {
                this.fillData(singleScore, subject);
            }
        }
    }

    private List<String> initSubjects() {
        List<String> subjects = new ArrayList<String>();
        subjects.add("思想政治");
        subjects.add("语文");
        subjects.add("英语");
        subjects.add("历史");
        subjects.add("地理");
        subjects.add("数学");
        subjects.add("物理");
        subjects.add("化学");
        subjects.add("生物");
        subjects.add("信息技术");
        subjects.add("通用技术");
        subjects.add("艺术");
        subjects.add("音乐");
        subjects.add("美术");
        subjects.add("体育与健康");
        subjects.add("研究性学习活动");
        subjects.add("社区服务");
        subjects.add("社会实践");
        subjects.add("校本课程");
        return subjects;
    }

    private void fillData(Map<String, Map<String, List<ModelScoreDto>>> singleScore, String subject) {
        singleScore.put(subject, null);
    }

    private void putNZScore(ModelScoreDto msDto, Map<String, Map<String, List<ModelScoreDto>>> singleScore) {
        if ("思想政治".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "思想政治");
        } else if ("语文".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "语文");
        } else if ("英语".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "英语");
        } else if ("历史".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "历史");
        } else if ("地理".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "地理");
        } else if ("数学".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "数学");
        } else if ("物理".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "物理");
        } else if ("化学".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "化学");
        } else if ("生物".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "生物");
        } else if ("信息技术".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "信息技术");
        } else if ("通用技术".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "通用技术");
        } else if ("艺术".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "艺术");
        } else if ("音乐".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "音乐");
        } else if ("美术".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "美术");
        } else if ("体育与健康".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "体育与健康");
        } else if ("研究性学习活动".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "研究性学习活动");
        } else if ("社区服务".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "社区服务");
        } else if ("社会实践".equals(msDto.getNzOrXbSub())) {
            this.putInfoIntoView(msDto, singleScore, "社会实践");
        }
    }

    private void putInfoIntoView(ModelScoreDto msDto, Map<String, Map<String, List<ModelScoreDto>>> singleScore, String subject) {
        //key1:subject
        //key:sub_termId_segMentOrder
        String key1 = subject;
        String key2 = subject + "_" + msDto.getTermId() + "_" + msDto.getXuhao();
        List<ModelScoreDto> modelScoreDto = null;
        Map<String, List<ModelScoreDto>> subjects = singleScore.get(key1);
        if (subjects != null && subjects.size() > 0) {
            modelScoreDto = subjects.get(key2);
            if (null != modelScoreDto && modelScoreDto.size() > 0) {
                modelScoreDto.add(msDto);
                //removeDoubleScore(modelScoreDto,msDto);
            } else {
                modelScoreDto = new ArrayList<ModelScoreDto>();
                modelScoreDto.add(msDto);
                subjects.put(key2, modelScoreDto);
            }
        } else {
            subjects = new HashMap<String, List<ModelScoreDto>>();
            modelScoreDto = new ArrayList<ModelScoreDto>();
            modelScoreDto.add(msDto);
            subjects.put(key2, modelScoreDto);
            singleScore.put(key1, subjects);
        }
    }

    private void removeDoubleScore(List<ModelScoreDto> modelSocreDtos, ModelScoreDto dto1) {
        List<ModelScoreDto> remodelSocreDtos = new ArrayList<ModelScoreDto>();
        if ("英语1".equals(dto1.getCourse_name()) || "历史Ⅰ".equals(dto1.getCourse_name())) {
            System.out.println("Aaa");
        }
        boolean flag = false;
        for (ModelScoreDto dto : modelSocreDtos) {
            if (NestUtil.isNotEmpty(dto.getXbXDname()) && dto.getXbXDname().equals(dto1.getXbXDname())
                    && NestUtil.isNotEmpty(dto.getCourse_name()) && dto.getCourse_name().equals(dto1.getCourse_name())
                    && NestUtil.isNotEmpty(dto.getNzScore()) && NestUtil.isNotEmpty(dto1.getNzScore())) {
                try {
                    if (Double.parseDouble(dto1.getNzScore()) >= Double.parseDouble(dto.getNzScore()))
                        remodelSocreDtos.add(dto);
                    else
                        flag = true;
                } catch (Exception e) {
                    if (dto1.getNzScore().equals(dto.getNzScore()))
                        flag = true;
                }
            }
        }
        if (!flag)
            modelSocreDtos.add(dto1);
        if (remodelSocreDtos.size() > 0)
            modelSocreDtos.removeAll(remodelSocreDtos);
    }

    private void putXBScore(ModelScoreDto msDto, Map<String, Map<String, List<ModelScoreDto>>> singleScore) {
        this.putInfoIntoView(msDto, singleScore, "校本课程");
    }

    @DataSource("read")
    public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params) {
        try {
            //学生信息
            ISqlElement sqlDemo = this.processSql(params, "MasterAppriseExt.getStudentInfo.query");
            return this.findList("MasterAppriseExt.getStudentInfo1.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    SchoolTreeDto dto = new SchoolTreeDto();
                    dto.setId(rs.getString("studentid"));
                    dto.setText(rs.getString("name"));
                    dto.setKey(NoServiceUtil.md53(rs.getString("studentid")));
                    dto.setEdusyId(rs.getString("edu_id"));
                    dto.setName(rs.getString("photo"));
                    dto.setStudentno(rs.getString("studentno"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("getClasssInfo(Map<String,Object>)", e);
        }
        return null;
    }

    public Map<String, String> queryHKScore(Map<String, Object> params) {
        try {
            String flag = (String) params.get("flag");
            String prSql = "";
            if (NestUtil.isNotEmpty(flag)) {
                prSql = "IMasterReportExt.queryHKScore1.query";
            } else {
                prSql = "IMasterReportExt.queryHKScore.query";
            }
            ISqlElement hk = this.processSql(params, prSql);
            List<ModelScoreDto> HKScores = this.findList(prSql, params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    ModelScoreDto msDto = new ModelScoreDto();
                    msDto.setEdu_id(rs.getString("edu_id"));
                    msDto.setNzOrXbSub(rs.getString("subject_name"));
                    msDto.setHkScore(rs.getString("level_name"));
                    return msDto;
                }
            });
            if (null != HKScores && HKScores.size() > 0) {
                Map<String, String> hkScore = new HashMap<String, String>();
                for (ModelScoreDto msDto : HKScores) {
                    hkScore.put(msDto.getEdu_id() + "_" + msDto.getNzOrXbSub(), msDto.getHkScore());
                }
                return hkScore;
            }
        } catch (Exception e) {
            logger.error("queryHKScore(Map<String,Object>)", e);
        }
        return null;
    }

    @DataSource("read")
    public List<DataCountDto> queryReportZoneDatas(String discode, String graduYear) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            if (NestUtil.isEmpty(graduYear)) return null;
            params.put("graduYear", Integer.parseInt(graduYear) + 1);
            params.put("discode", discode);
            ISqlElement preSql = this.processSql(params, "IMasterReportExt.queryReportZoneDatas.query");
            return this.findList("IMasterReportExt.queryReportZoneDatas.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    DataCountDto dto = new DataCountDto();
                    dto.setDiscode(rs.getString("discode"));
                    dto.setName(rs.getString("name"));
                    dto.setSchoolTotalCount(rs.getString("schoolTotalCount"));
                    dto.setReportSchoolCount(rs.getString("reportSchoolCount"));
                    dto.setNoReportSchoolCount(rs.getString("noReportSchoolCount"));
                    dto.setReportStudentCount(rs.getString("reportStudentCount"));
                    dto.setCheckStudentCount(rs.getString("checkStudentCount"));
                    dto.setErrorCheckStudentCount(rs.getString("errorCheckStudentCount"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("queryReportZoneDatas(String,String)", e);
        }
        return null;
    }

    @DataSource("read")
    public List<DataCountDto> queryReportZoneInfos(String discode) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("discode", discode);
            ISqlElement preSql = this.processSql(params, "IMasterReportExt.queryReportZoneInfos.query");
            return this.findList("IMasterReportExt.queryReportZoneInfos.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    DataCountDto dto = new DataCountDto();
                    dto.setDiscode(rs.getString("discode"));
                    dto.setName(rs.getString("name"));
                    dto.setSchoolTotalCount(rs.getString("schoolTotalCount"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("queryReportZoneInfos(String)", e);
        }
        return null;
    }

    @DataSource("read")
    public List<DataCountDto> queryReportSchoolInfos(String discode, String graduYear) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("discode", discode);
            params.put("graduYear", graduYear);
            ISqlElement preSql = this.processSql(params, "IMasterReportExt.queryReportSchoolInfos.query");
            return this.findList("IMasterReportExt.queryReportSchoolInfos.query", params, new RowMapper() {
                public Object mapRow(ResultSet rs, int num)
                        throws SQLException {
                    DataCountDto dto = new DataCountDto();
                    dto.setDiscode(rs.getString("discode"));
                    dto.setName(rs.getString("name"));
                    dto.setSchoolTotalCount(rs.getString("reportSchoolCount"));
                    return dto;
                }
            });
        } catch (Exception e) {
            logger.error("queryReportSchoolInfos(String,String)", e);
        }
        return null;
    }


}
