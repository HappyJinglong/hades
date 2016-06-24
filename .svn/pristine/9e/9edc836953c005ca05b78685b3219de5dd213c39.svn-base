package com.flyrish.hades.webapp.action.score;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.action.FileItem;

import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.fr.function.MAP;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class TYHKScoreImportAction extends HKScoreImportAction{
	
	/**
	 * 接受文件属性
	 */
	public FileItem importFile;
	
	
	public Object importExcelAndPaser(HttpServletRequest request){
		
		try {
			if(null!=importFile && importFile.getSize()>0){
				
				List<ModelScoreDto> excelCourseInfos =new ArrayList<ModelScoreDto>();
				//解析excel中的数据
				//没有教育Id为true,有教育id为false
				Boolean hasNotEduId = readDBF(excelCourseInfos);
			
				if(null!=excelCourseInfos && excelCourseInfos.size()>0){
					List<ModelScoreDto> warninfoMessages=new ArrayList<ModelScoreDto>();
					List<ModelScoreDto> errorMessages = checkImportDatas1(excelCourseInfos,hasNotEduId,warninfoMessages);
					if(null!=errorMessages && errorMessages.size()>0){
						
						Map<String, String> map = new LinkedHashMap<String, String>();
						
						for(ModelScoreDto errorMessage : errorMessages){
							map.put(errorMessage.getRownum() + 1,errorMessage.getStudentName() + errorMessage.getErrorInfo());
						}
						//数据导入失败，返回失败信息
						request.setAttribute("errorMessages",map);
						return "hScoreExport.jsp";
					}else{

						iScoreExt.hKScoreInsertOrUpdate(insertHKs, cmis30id, discode, true);
						iScoreExt.hKScoreInsertOrUpdate(updateHKs, cmis30id, discode, false);
						if(null!=warninfoMessages && warninfoMessages.size()>0){
							Map<String, String> map = new LinkedHashMap<String, String>();
							
							for(ModelScoreDto warninfoMessage : warninfoMessages){
								map.put(warninfoMessage.getRownum(),warninfoMessage.getErrorInfo());
							}
							//数据警告信息提醒
							request.setAttribute("errorMessages",map);
						}
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
					return "scoreExport.jsp";
				}
			}
		} catch (Exception e) {
			logger.error("importExcelAndPaser(HttpServletRequest)", e);
			return "error.jsp";
		}
		return null;
		
		
	}
	/**
	 * 验证
	 * @param excelCourseInfos
	 * @return
	 */
	public List<ModelScoreDto> checkImportDatas1(List<ModelScoreDto> excelCourseInfos,Boolean isTY,List<ModelScoreDto> warninfoMessages){
		
			List<ModelScoreDto> errorMessages = new ArrayList<ModelScoreDto>();
			
			List<ModelScoreDto> doubleDtos=new ArrayList<ModelScoreDto>();
			
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
				if(!(dto.getEdu_id().matches("[0-9]{8}"))){
					dto.setErrorInfo("教育ID填写，文本格式，填写八位数字，如12345678");
					errorMessages.add(dto);
				}
				if(!(dto.getClassNum().matches("^[0-9]*$"))){
					dto.setErrorInfo("班级填写，文本格式，填写班号数字，如高三1班则直接填写1即可");
					errorMessages.add(dto);
				}
				
				if(!("".equals(dto.getLevel_name()))&&!"EduId".equals(dto.getSubjectName())){
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
										md1.setErrorInfo("导入学生模块重复，仅该重复的学生不能导入");
									}else{
										md1.setErrorInfo("导入学生模块重复，仅该重复的学生不能导入，请填写教育ID");
									}
									warninfoMessages.add(md1);
									doubleDtos.add(dto2);
									doubleDtos.add(dto);
								}else{ 
									if(dto.getEdu_id().equals(dto2.getEdu_id())){
										repeatRowNums.add(dto.getRownum());
										repeatRowNums.add(dto2.getRownum());
										md2.setRownum(dto2.getRownum()+"、"+dto.getRownum());
										if(isTY){
											md2.setErrorInfo("导入学生模块重复，仅该重复的学生不能导入");
										}else{
											md2.setErrorInfo("导入学生模块重复，仅该重复的学生不能导入，请填写教育ID");
										}
										warninfoMessages.add(md2);
										doubleDtos.add(dto2);
										doubleDtos.add(dto);
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
				
			checkStudent1(warninfoMessages,excelCourseInfos,isTY);
			checkSubject(errorMessages, excelCourseInfos);
			if(errorMessages.size() > 0){
				return errorMessages;
			}
			checkInsertUpdate(excelCourseInfos);
		
		return errorMessages;
	}
    public  Boolean readDBF(List<ModelScoreDto> dbfScoreInfos){   
      
            try{   
                //根据输入流初始化一个DBFReader实例，用来读取DBF文件信息   
      
                DBFReader reader = new DBFReader(importFile.getInputStream());    
      
                //调用DBFReader对实例方法得到path文件中字段的个数   
      
                int fieldsCount = reader.getFieldCount();   
                
                Boolean hasNotEduId=true;
                
                Object[] rowValues;   
                
                int rowNum = 0;
      
                //一条条取出path文件中记录   
                while((rowValues = reader.nextRecord()) != null){   
                	DBFField field1= reader.getField(rowValues.length-1);
                	String lastStr = field1.getName();
                	int length=0;
                	if("edu_id".equalsIgnoreCase(lastStr)||"eduid".equalsIgnoreCase(lastStr)){
                		hasNotEduId=false;
                		length=rowValues.length-1;
                	}else{
                		length=rowValues.length;
                	}
                	rowNum++;
                    for(int j=7;j<length;j++){
                        //取出字段信息  
                  	   DBFField field = reader.getField(j);   
             		   ModelScoreDto modelScore= new ModelScoreDto();
            		   modelScore.setRownum(rowNum+"");
            		   modelScore.setCircle(this.trim(rowValues[5]+""));  //界别
            		   String classNum = rowValues[6]+"" ;
            		   if(classNum.substring(0,1).equals("0")){
            			   classNum = classNum.substring(1,classNum.length());
            		   }
            		   if("edu_id".equalsIgnoreCase(lastStr)||"eduid".equalsIgnoreCase(lastStr)){
            			   String edu_id = new String((this.trim(rowValues[rowValues.length-1]+"")).getBytes("ISO-8859-1"),"gbk");
//            			   if(edu_id != null && !edu_id.equals("")){
//            				   BigDecimal decimal = new BigDecimal(edu_id);
//            				   edu_id = decimal.toPlainString();
//            			   }
            			   modelScore.setEdu_id(edu_id.trim());
                   		}
            		   modelScore.setClassNum((this.trim(classNum)));  //班级
            		   
            		   String studentName = new String((this.trim(rowValues[1]+"")).getBytes("ISO-8859-1"),"gbk");
            		   modelScore.setStudentName(studentName);  //姓名
            		 
            		   String subjectName = field.getName();
            		   
            		   modelScore.setSubjectName(getSubjectName(subjectName)); //学科名称
            		   
            		   modelScore.setLevel_name((this.trim(rowValues[j]+"")));  //会考成绩
            		   
            		   dbfScoreInfos.add(modelScore);
                    }
      
                  }
                
                return hasNotEduId;
                
            
              }catch(Exception e){   
            	  e.printStackTrace();   
            	  return true;
              } finally{   
	              try{   
	      
	            	  importFile.getInputStream().close();   
	      
	              }catch(Exception e){}   
      
              }   
      
        }   
    
    private String getSubjectName(String subjectName){
    	 if("Ywdj".equalsIgnoreCase(subjectName)){
    		 subjectName="语文"; 
		   }else if("Sxdj".equalsIgnoreCase(subjectName)){
	    		 subjectName="数学"; 
		   }else if("Wydj".equalsIgnoreCase(subjectName)){
	    		 subjectName="英语"; 
		   }else if("Zzdj".equalsIgnoreCase(subjectName)){
		    		 subjectName="思想政治"; 
		   }else if("Wldj".equalsIgnoreCase(subjectName)){
	    		 subjectName="物理"; 
	       }else if("Hxdj".equalsIgnoreCase(subjectName)){
	    		 subjectName="化学"; 
	       }else if("Swdj".equalsIgnoreCase(subjectName)){
	    		 subjectName="生物"; 
	       }else if("Lsdj".equalsIgnoreCase(subjectName)){
	    		 subjectName="历史"; 
	       }else if("Dldj".equalsIgnoreCase(subjectName)){
	    		 subjectName="地理"; 
	       }          
    	return subjectName;
    }



}
