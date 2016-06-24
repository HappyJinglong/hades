package com.flyrish.hades.webapp.action.score;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;

import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.service.ext.IScoreExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class HKScoreImportAction extends BaseAction{
	/**
	 * 接受文件属性
	 */
	public FileItem importFile;
	
	/**
	 * 区县代码
	 */
	public String discode;
	
	public String cmis30id;
	
	@Spring
	public IScoreExt iScoreExt;
	
	public List<String> studentNames = new ArrayList<String>();  //学生姓名
	public List<String> graduateyears = new ArrayList<String>(); //界别
	public List<String> classnums = new ArrayList<String>();     //班级号
	public List<String> subjectNames = new ArrayList<String>();     //科目名称
	public List<String> subjectIds = new ArrayList<String>();     //科目id
	public List<String> studentids =   new ArrayList<String>();   //学生id
	
	
	public List<ModelScoreDto> updateHKs = new ArrayList<ModelScoreDto>();  //导入会考成绩—更新
	public List<ModelScoreDto> insertHKs = new ArrayList<ModelScoreDto>();  //导入会考成绩—插入
	
	public String courseType;
	public String gradeName1;
	public String className1;
	public String schoolyear;
	public String segmentId;
	public String subjectId;
	public String courseId;
	public String tableTitle2;

	
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
		cmis30id=userDto.getCmis30id();//"250014";
		discode = userDto.getDiscode();
		
		//isLockButton = "2014";
		//isLockButton = userDto.getTermId().substring(0, 4);
	}
	
	
	/**
	 * 导入模板并解析数据
	 */
	public Object importExcelAndPaser(HttpServletRequest request){
		try {
			if(null!=importFile && importFile.getSize()>0){
				//解析excel中的数据
				List<ModelScoreDto> excelCourseInfos = this.parseExcelToList(importFile,"自主会考");
				
				if(null!=excelCourseInfos && excelCourseInfos.size()>0){
					if("请导入正确模板".equals(excelCourseInfos.get(0).getErrorInfo())){
						Map<String, String> map = new LinkedHashMap<String, String>();
						map.put("1",excelCourseInfos.get(0).getErrorInfo());
						//数据导入失败，返回失败信息
						request.setAttribute("errorMessages", map);
						return "hScoreExport.jsp";
					}
				}
			
				if(null!=excelCourseInfos && excelCourseInfos.size()>0){
					List<ModelScoreDto> errorMessages = checkImportDatas(excelCourseInfos,false);
					
					if(null!=errorMessages && errorMessages.size()>0){
						
						Map<String, String> map = new LinkedHashMap<String, String>();
						
						for(ModelScoreDto errorMessage : errorMessages){
							map.put(errorMessage.getRownum() + 1,errorMessage.getStudentName() + errorMessage.getErrorInfo());
						}
						
						//数据导入失败，返回失败信息
						request.setAttribute("errorMessages", map);
						return "hScoreExport.jsp";
					}else{

						iScoreExt.hKScoreInsertOrUpdate(insertHKs, cmis30id, discode, true);
						iScoreExt.hKScoreInsertOrUpdate(updateHKs, cmis30id, discode, false);
						request.setAttribute("successFlag", "OK");
						return "hScoreExport.jsp";
					}
				}else{
					//导入模板失败 空数据
					List<KcourseDto> errorMessages = new ArrayList<KcourseDto>();
					KcourseDto kd = new KcourseDto();
					kd.setRownum(2);
					kd.setErrorInfo("您导入的模板没有信息！");
					errorMessages.add(kd);
					request.setAttribute("errorMessages", errorMessages);
					return "hScoreExport.jsp";
				}
			}
		} catch (Exception e) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("1","请导入正确模板");
			//数据导入失败，返回失败信息
			request.setAttribute("errorMessages", map);
			return  "hScoreExport.jsp";
		}
		return null;
	}
	
	
	/**
	 * 解析excel
	 * @param importFile2
	 */
	public List<ModelScoreDto> parseExcelToList(FileItem importFile,String str) {
		try {
			List<ModelScoreDto>coursList = new ArrayList<ModelScoreDto>();
			InputStream inputStream = importFile.getInputStream();
			Workbook workBook = WorkbookFactory.create(inputStream);
			Sheet courseSheet = workBook.getSheet(str);
			
			coursList.addAll(this.parseExcelInfo(courseSheet));
			return coursList;
		} catch (Exception e) {
			logger.error("parseExcelToList(FileItem,String)", e);
		}
		return null;
	}
	
	/**
	 * 解析内容
	 * @param courseSheet
	 * @return
	 */
	public List<ModelScoreDto> parseExcelInfo(Sheet courseSheet) {
		List<ModelScoreDto> courseDtoList = new ArrayList<ModelScoreDto>();
		List<ModelScoreDto> kcourseDtos = null;
		try{
			int rowsNum = courseSheet.getLastRowNum();
			for(int i = 1; i <=rowsNum; i++){
				Row row = courseSheet.getRow(i);
				kcourseDtos = new ArrayList<ModelScoreDto>();
				this.parseRow((i+1)+"",row, kcourseDtos,courseSheet);
				courseDtoList.addAll(kcourseDtos);
			}
		} catch (Exception e) {
			List<ModelScoreDto> errorMessages = new ArrayList<ModelScoreDto>();
			ModelScoreDto kd = new ModelScoreDto();
			kd.setRownum("");
			kd.setErrorInfo("请导入正确模板");
			errorMessages.add(kd);
			return errorMessages;
		}
		return courseDtoList;
	}

	/**
	 * 解析行
	 * @param row
	 * @param lietou
	 * @return
	 */
   protected List<ModelScoreDto> parseRow(String rownum,Row rowData, List<ModelScoreDto> modelScores,Sheet courseSheet) {
	   
	   int colsNum  =courseSheet.getRow(0).getPhysicalNumberOfCells();
	  
	   for(int i=4;i<colsNum;i++){
		   ModelScoreDto modelScore= new ModelScoreDto();
		   modelScore.setRownum(rownum);
		   modelScore.setCircle((this.trim(this.safeStringValue(rowData, 0))));  //界别
		   modelScore.setClassNum((this.trim(this.safeStringValue(rowData, 1))));  //班级
		   modelScore.setStudentName((this.trim(this.safeStringValue(rowData, 2))));  //姓名
		   modelScore.setEdu_id((this.trim(this.safeStringValue(rowData, 3))));  //教育id
		   modelScore.setSubjectName(courseSheet.getRow(0).getCell(i).toString()); 
		   modelScore.setLevel_name((this.trim(this.safeStringValue(rowData, i))));
		   
		   modelScores.add(modelScore);
	   }
	   
	return modelScores;
}
   
   
   
   /**
	 * 数据检验
	 * @param rowData
	 * @param index
	 * @return
	 */
    public String safeStringValue(Row rowData, int index) {
        Cell cell = rowData.getCell(index);
        if (cell == null)
            return StringUtils.EMPTY;

        return this.getStringCellValue(cell);
    }
    
    /**
     * 获取单元格信息
     * @param cell
     * @return
     */
	private String getStringCellValue(Cell cell) {
        DecimalFormat fmt = new DecimalFormat("#.####");
        if (null == cell) {
            return null;
        } else {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC :
                    return fmt.format(cell.getNumericCellValue());
                case Cell.CELL_TYPE_STRING :
                    return cell.getStringCellValue();
                case Cell.CELL_TYPE_FORMULA :
                    if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
                        return cell.getStringCellValue();
                    } else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                        return String.valueOf(cell.getNumericCellValue());
                    } else {
                        return null;
                    }
                default:
                    return null;
            }
        }
	}
	
	/**
	 * 去除字符串中的空字符串
	 * @param str
	 * @return
	 */
	public String trim(String str){
		
		 if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(str);
	            str = m.replaceAll("");
	        }
		return StringUtils.isEmpty(str)?"":str.replace(" ", "");
	}
	
	
	//去重
	public void quchong(List<String> strs){
		 List<String> list = new ArrayList<String>();
		  for(int i=0; i<strs.size(); i++){   //去重
	        	String str =strs.get(i);  //获取传入集合对象的每一个元素
	            if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
	            	list.add(str);
	            }
	        }
		 strs.removeAll(strs);
		 strs.addAll(list);
	}
	
	
	/**
	 * 验证
	 * @param excelCourseInfos
	 * @return
	 */
	public List<ModelScoreDto> checkImportDatas(List<ModelScoreDto> excelCourseInfos,Boolean isTY){
		
			List<ModelScoreDto> errorMessages = new ArrayList<ModelScoreDto>();
			
			List<String> repeatRowNums = new ArrayList<String>();  //模块重复行集合
			for(int i=0;i<excelCourseInfos.size();i++){
				ModelScoreDto dto = excelCourseInfos.get(i); 
				if( "".equals(dto.getCircle()) 
						|| "".equals(dto.getClassNum())|| "".equals(dto.getStudentName())){
					
					dto.setErrorInfo("必填项没有填写，不能导入");
					errorMessages.add(dto);
					
				}
				if(!(dto.getCircle().matches("[0-9]{4}"))){
					dto.setErrorInfo("界别填写，文本格式，填写四位数字，如2016");
					errorMessages.add(dto);
				}
				if(!(dto.getClassNum().matches("^[0-9]*$"))){
					dto.setErrorInfo("班级填写，文本格式，填写班号数字，如高三1班则直接填写1即可");
					errorMessages.add(dto);
				}
				
				if(!("".equals(dto.getLevel_name()))&&!"edu_id".equalsIgnoreCase(dto.getSubjectName())){
					if(dto.getLevel_name().length()>4){
						dto.setErrorInfo("会考成绩超长，不导入");
						errorMessages.add(dto);
					}
				}
				
				studentNames.add(dto.getStudentName());
				graduateyears.add(dto.getCircle());
				classnums.add(dto.getClassNum());
				String subject = "";
				if("政治".equals(dto.getSubjectName())){
					dto.setSubjectName("思想政治");
				}else if("外语".equals(dto.getSubjectName())){
					dto.setSubjectName("英语");
				}
				if(!"".equals(dto.getStudentName()))
					subjectNames.add(dto.getSubjectName());
				
				String repeatRowNum = "";
				if(repeatRowNums.size()>0){   //判断该行是否在重复行集合中 
					for(String num : repeatRowNums){
						if(num.equals(dto.getRownum())){
							repeatRowNum =dto.getRownum();
						}
					}
				}
				ModelScoreDto md1 = new ModelScoreDto();
				ModelScoreDto md2 = new ModelScoreDto();
				//判断有哪些是重复导入
				if("".equals(repeatRowNum)){   
					for(int j=0;j<excelCourseInfos.size();j++){
						ModelScoreDto dto2 = excelCourseInfos.get(j);
							if(dto.getSubjectName().equals(dto2.getSubjectName()) && dto.getCircle().equals(dto2.getCircle())
									&& dto.getClassNum().equals(dto2.getClassNum())
									&& dto.getStudentName().equals(dto2.getStudentName())
									  && i!=j){
								if((null ==dto.getEdu_id() || "".equals(dto.getEdu_id())||null==dto2.getEdu_id()||"".equals(dto2.getEdu_id()))){
									repeatRowNums.add(dto.getRownum());
									repeatRowNums.add(dto2.getRownum());
									md1.setRownum(dto2.getRownum()+"、"+dto.getRownum());
									if(isTY){
										md1.setErrorInfo("导入学生模块重复，不能导入");
									}else{
										md1.setErrorInfo("导入学生模块重复，请填写教育ID");
									}
									errorMessages.add(md1);
								}else{ 
									if(dto.getEdu_id().equals(dto2.getEdu_id())){
										repeatRowNums.add(dto.getRownum());
										repeatRowNums.add(dto2.getRownum());
										md2.setRownum(dto2.getRownum()+"、"+dto.getRownum());
										md2.setErrorInfo("导入学生模块重复，不导入");
										errorMessages.add(md2);
									}
								}
								
						}
					}
				}
				
			}
			
			
			quchong(studentNames);
			quchong(graduateyears);
			quchong(classnums);
			quchong(subjectNames);
			
			if(errorMessages.size() > 0){
				 List<ModelScoreDto> list = new ArrayList<ModelScoreDto>();
			        for(int i=0; i<errorMessages.size(); i++){   //去重
			        	ModelScoreDto str =errorMessages.get(i);  //获取传入集合对象的每一个元素
			            if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
			                list.add(str);
			            }
			        }
				return list;
			}
				
			checkStudent(errorMessages,excelCourseInfos,isTY);
			//checkStudent1(errorMessages,excelCourseInfos,isTY);
			if(errorMessages.size() > 0){
				return errorMessages;
			}
			checkSubject(errorMessages, excelCourseInfos);
			if(errorMessages.size() > 0){
				return errorMessages;
			}
			
			checkInsertUpdate(excelCourseInfos);
		
		return errorMessages;
	}
	
	/**
	 * 学生验证
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkStudent1(List<ModelScoreDto> warninfoMessages,List<ModelScoreDto> excelCourseInfos,Boolean isTY){
		List<ModelScoreDto> datas = iScoreExt.getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(cmis30id, discode, studentNames, graduateyears, classnums,null);
		//过滤不满足条件的数据
		List<ModelScoreDto> errordtos=new ArrayList<ModelScoreDto>();
		for(ModelScoreDto excelCourseInfo : excelCourseInfos){
			
			if(datas.size()<=0){
				excelCourseInfo.setErrorInfo("学生在系统中不存在，仅该学生不能导入");
				errordtos.add(excelCourseInfo);
				warninfoMessages.add(excelCourseInfo);
			}else{
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();
				
				//数据对比
				for(ModelScoreDto data : datas){
					if(data.getCircle().substring(0,4).equals(excelCourseInfo.getCircle())&&data.getClassNum().equals(excelCourseInfo.getClassNum()) && data.getStudentName().equals(excelCourseInfo.getStudentName()) ){
						md.add(data);
					}
				}
				if(md.size()<=0){
					excelCourseInfo.setErrorInfo("学生在系统中不存在，仅该学生不能导入");
					errordtos.add(excelCourseInfo);
					warninfoMessages.add(excelCourseInfo);
				}else if(md.size()>1){
					if(excelCourseInfo.getEdu_id()==null || "".equals(excelCourseInfo.getEdu_id())){
						if(isTY){
							excelCourseInfo.setErrorInfo("学生在系统中重复，仅该学生不能导入");
							errordtos.add(excelCourseInfo);
						}else{
							excelCourseInfo.setErrorInfo("学生在系统中重复，请填写教育ID，仅该学生不能导入");
							errordtos.add(excelCourseInfo);
						}
						warninfoMessages.add(excelCourseInfo);
					}else{
						List<ModelScoreDto> md2 = new ArrayList<ModelScoreDto>();
						for(ModelScoreDto data : datas){
							if(data.getCircle().substring(0,4).equals(excelCourseInfo.getCircle())&&data.getClassNum().equals(excelCourseInfo.getClassNum()) && data.getStudentName().equals(excelCourseInfo.getStudentName()) && data.getEdu_id().equals(excelCourseInfo.getEdu_id())){
								md2.add(data);
							}
						}
						if(md2.size()<=0){
							excelCourseInfo.setErrorInfo("学生在系统中不存在，仅该学生不能导入");
							errordtos.add(excelCourseInfo);
							warninfoMessages.add(excelCourseInfo);
						}else{
							excelCourseInfo.setEdu_id(md2.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md2.get(0).getStudentid());
							excelCourseInfo.setClassid(md2.get(0).getClassid());
							studentids.add(excelCourseInfo.getStudentid());
						}
					}
				}else{
					if(null==excelCourseInfo.getEdu_id()||"".equals(excelCourseInfo.getEdu_id())){
						excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
						excelCourseInfo.setStudentid(md.get(0).getStudentid());
						excelCourseInfo.setClassid(md.get(0).getClassid());
						studentids.add(excelCourseInfo.getStudentid());
					}else{
						if(excelCourseInfo.getEdu_id().equals(md.get(0).getEdu_id())){
							excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md.get(0).getStudentid());
							excelCourseInfo.setClassid(md.get(0).getClassid());
							studentids.add(excelCourseInfo.getStudentid());
						}else{
							excelCourseInfo.setErrorInfo("学生在系统中不存在，仅该学生不能导入");
							errordtos.add(excelCourseInfo);
							warninfoMessages.add(excelCourseInfo);
						}
					}
					
				}
			}
		}
		excelCourseInfos.removeAll(errordtos);
	}	
	/**
	 * 学生验证
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkStudent(List<ModelScoreDto> errorMessages,List<ModelScoreDto> excelCourseInfos,Boolean isTY){
		List<ModelScoreDto> datas = iScoreExt.getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(cmis30id, discode, studentNames, graduateyears, classnums,null);
		for(ModelScoreDto excelCourseInfo : excelCourseInfos){
			
			if(datas.size()<=0){
				excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
				errorMessages.add(excelCourseInfo);
			}else{
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();
				
				//数据对比
				for(ModelScoreDto data : datas){
					if(data.getCircle().substring(0,4).equals(excelCourseInfo.getCircle())&&data.getClassNum().equals(excelCourseInfo.getClassNum()) && data.getStudentName().equals(excelCourseInfo.getStudentName()) ){
						md.add(data);
					}
				}
				if(md.size()<=0){
					excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
					errorMessages.add(excelCourseInfo);
				}else if(md.size()>1){
					if(excelCourseInfo.getEdu_id()==null || "".equals(excelCourseInfo.getEdu_id())){
						if(isTY){
							excelCourseInfo.setErrorInfo("学生在系统中重复，不能导入");
						}else{
							excelCourseInfo.setErrorInfo("学生在系统中重复，请填写教育ID");
						}
						errorMessages.add(excelCourseInfo);
					}else{
						List<ModelScoreDto> md2 = new ArrayList<ModelScoreDto>();
						for(ModelScoreDto data : datas){
							if(data.getCircle().substring(0,4).equals(excelCourseInfo.getCircle())&&data.getClassNum().equals(excelCourseInfo.getClassNum()) && data.getStudentName().equals(excelCourseInfo.getStudentName()) && data.getEdu_id().equals(excelCourseInfo.getEdu_id())){
								md2.add(data);
							}
						}
						if(md2.size()<=0){
							excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
							errorMessages.add(excelCourseInfo);
						}else{
							excelCourseInfo.setEdu_id(md2.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md2.get(0).getStudentid());
							excelCourseInfo.setClassid(md2.get(0).getClassid());
							studentids.add(excelCourseInfo.getStudentid());
						}
					}
				}else{
					if(null==excelCourseInfo.getEdu_id()||"".equals(excelCourseInfo.getEdu_id())){
						excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
						excelCourseInfo.setStudentid(md.get(0).getStudentid());
						excelCourseInfo.setClassid(md.get(0).getClassid());
						studentids.add(excelCourseInfo.getStudentid());
					}else{
						if(excelCourseInfo.getEdu_id().equals(md.get(0).getEdu_id())){
							excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md.get(0).getStudentid());
							excelCourseInfo.setClassid(md.get(0).getClassid());
							studentids.add(excelCourseInfo.getStudentid());
						}else{
							excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
							errorMessages.add(excelCourseInfo);
						}
					}
					
				}
			}
		}
	}
	
	
	/**
	 * 学科验证
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkSubject(List<ModelScoreDto> errorMessages,List<ModelScoreDto> excelCourseInfos){
		List<ModelScoreDto> datas = iScoreExt.getSubjectName(subjectNames);
		for(ModelScoreDto excelCourseInfo : excelCourseInfos){
			if("edu_id".equalsIgnoreCase(excelCourseInfo.getSubjectName()))
				continue;
			if(datas.size()<=0){
				excelCourseInfo.setErrorInfo("学科名称在系统中不存在，不能导入");
				errorMessages.add(excelCourseInfo);
			}else{
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();
				//学科模块数据对比
				for(ModelScoreDto data : datas){
					if(excelCourseInfo.getSubjectName().equals(data.getSubjectName())){
						md.add(data);
					}
				}
				if(md.size()<=0){
					excelCourseInfo.setErrorInfo("学科名称在系统中不存在，不能导入");
					errorMessages.add(excelCourseInfo);
				}else{
					excelCourseInfo.setSubjectId(md.get(0).getSubjectId());
					subjectIds.add(md.get(0).getSubjectId());
				}
			}
		}
	}
	
	
	/**
	 * 判断更新还是插入
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkInsertUpdate(List<ModelScoreDto> excelCourseInfos){
		List<ModelScoreDto> datas = iScoreExt.getGENERAL_EXAMINATION_SCORE(studentids, subjectIds, cmis30id, discode);
		for(ModelScoreDto excelCourseInfo : excelCourseInfos){
			if(datas.size()<=0){
				insertHKs.add(excelCourseInfo);
			}else{
				int count = 0;
				for(ModelScoreDto data : datas){
						if(excelCourseInfo.getSubjectId().equals(data.getSubjectId()) && excelCourseInfo.getStudentid().equals(data.getStudentid())){
							excelCourseInfo.setGeneralExaminationScoreId(data.getGeneralExaminationScoreId());
							count = 1;
						}
				}
				if(count==0){
					insertHKs.add(excelCourseInfo);
				}else{
					updateHKs.add(excelCourseInfo);
				}
			}
			
		}
	}
    
	
	
	
}
