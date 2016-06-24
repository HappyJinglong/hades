<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<un:bind var="KG_COURSE_KIND" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_KIND"/>
<un:bind var="KG_COURSE_NEIZHI" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_NEIZHI"/>
<un:bind var="USER_MASTERROLE_TYPESTR" type="com.flyrish.hades.common.Constant"
	field="USER_MASTERROLE_TYPESTR"/>
<un:bind var="TYPE_SCHOOLADMIN" type="com.flyrish.hades.common.Constant"
	field="USER_TYPE_SCHOOLADMIN"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/handsontable.full.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script data-jsfiddle="common" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<script data-jsfiddle="common" src="${ctx}/js/handsontable.full.js"></script>

<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>

<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />


<title>成绩录入</title>
	<style>
		.li{
			margin-left:1.5%;
		}
		li{
		 float: left;
		}
		ul{
			list-style:none;
		}
		select{
			height: 30px;
			width: 90px	
		}
		.bt{
			background: none repeat scroll 0% 0% #279F46;
			border-bottom: 3px solid #1C7132;
			color: #FFF;
		}
		td {
		 text-align: center;
		} 
	</style>
	<script data-jsfiddle="example1">
		$(function(){
			
			
			$("#biaoge").hide();
			
			$("#export").click(function(){
				
			
				url = "${ctx}/score/ScoreAction.a?export";
				var courseType = $("#courseType").val();
				var gradeName1 = ""; 
				var className1 ="";
				if("${KG_COURSE_NEIZHI}"==courseType){
					gradeName1 = $("#gradeName option:selected").html();
					className1 = $("#className option:selected").html();
					url +="&&gradeName1="+encodeURI(encodeURI(gradeName1))+"&&className1="+encodeURI(encodeURI(className1));
				}
				
				var schoolyearValue = $("#year option:selected").html();
				var segmentValue = $("#segmentId option:selected").html();
				var subjectValue = $("#subjectId option:selected").html();
				var courseValue = $("#courseId option:selected").html();
				
				url +="&&schoolyearValue="+schoolyearValue+"&&segmentValue="+encodeURI(encodeURI(segmentValue))+"&&subjectValue="+encodeURI(encodeURI(subjectValue));
				url +="&&courseValue="+encodeURI(encodeURI(courseValue));
			  
				
				url = validate(url);  //验证
				window.location.href=url;
				
			})
				
			var isToBake ="${isToBake}";
			$("#startInput").click(function(){
			
				$(this).attr('disabled',true);
				//line-height: 36
				var str = "<div class='form-actions pagination alternate'  style='margin-top: 0px;margin-bottom: 0px;'>";
				str+="<span style='margin-right: 30px;float:right;margin-top: 0px ;'>";
				str+="<input onclick='scoreExport();' type='button' class='btn btn-success' style='width: 110px;' id='query'  value='导入模块成绩' />&nbsp;&nbsp;";
				var userrealtype='${sessionScope.sessionUser.userRealType}';
				if(userrealtype=='1502003'){
					str+="<input onclick='hScoreExport();' type='button' class='btn btn-success'  style='width: 110px;' id='query'  value='导入会考成绩' />&nbsp;&nbsp;";
				}
				str+="<input  type='button' class='btn btn-success' style='width: 70px;' id='save'  value='保 存' /></span></div>";
			    $("#example1").html(str);
				
				 $("#MyDiv").css("margin-top","44px");
				 $("#MyDiv").css("height","100%");
				ShowDiv();
				var url = "${ctx}/score/ScoreAction.a?queryScore";
				if("1"!=isToBake){
					url = validate(url);  //验证
				}
				var courseType = $("#courseType").val();
			
				
				if("1"==isToBake){  //导入返回
					url="${ctx}/score/ScoreAction.a?queryScore&&courseType=${courseType}&&classid=${className1}&&schoolyear=${schoolyear}&&segmentId=${segmentId}&&courseId=${courseId}&&subjectId=${subjectId}";
					$('#courseType').attr('value',"${courseType}"); 
					$('#gradeName').attr('value',"${gradeName1}");
					 changeGrades();
					getYear();
					getSegment();
					changeSubjectIdBySegmentId();
					changeCourseBySubjectIdAndCourseType();
					$("#tableTitle").html("${tableTitle2}");
					$("#tableTitle2").val("${tableTitle2}");
				}else{
					tableTitle(courseType);  //表格标题
				}
				isToBake = "";	            
			
				
				exportParameter();  //导入连接参数赋值
				
				$.post(url,null,function(data){
					$("#startInput").attr('disabled',false);
					
					  /* var credit_hour = data.vds.xf;//学分
					  var period_count =data.vds.xs;//学时  */
					   var credit_hour = data.vds.xfs;//学分
					  var period_count =data.vds.xss;//学时 
					  var teacherName = data.vds.teacherName;//任课教师
					  var tableView = "任课老师："+teacherName+"&nbsp;&nbsp;&nbsp;学时："+period_count+"&nbsp;&nbsp;&nbsp; 学分："+credit_hour+"分";
					  $("#viewDto").html(tableView);  
					
					 if(data.modelScores.length<=0){
						 $("#MyDiv").hide();
						 alert_KG_chooseFile_tip("没有对应的学生");
						 $("#biaoge").hide();
						 $("#viewDto").hide();
						 return ;
					 }
					 exportParameter();  //导入连接参数赋值
					 $("#MyDiv").hide();
					 
					 $("#biaoge").show();
					 $("#viewDto").show();
					 
					 
					  $("#class_model_id").val(data.class_model_id);
					  $("#segment_course_id").val(data.segment_course_id);
					  
					  
					  var container1 = document.getElementById('example1'),
		              hot1;
					  
					  var setDatas=[];
		            hot1 = new Handsontable(container1, {
		                data: data.modelScores,
		                minSpareRows: 1,
		                stretchH: 'all',
		                /*manualColumnResize:true,*/
		                /*colWidths: [40,80, 180, 70,70,68,70,70,70,80,70,70,80,50,50,50,73,80],*/		                
		               /*  rowHeights:[30], */
		                
		                maxRows: data.modelScores.length,
		               /*  mergeCells:data.getMergeCells, */
		                colHeaders:['序号','教育ID', '姓名', '平时成绩1', '平时成绩2', '平时成绩3','平时成绩4','平时成绩5','平时评定','日常表现','出勤率','缺勤学时','考试成绩','补考','学分','免修','学分不通过','会考成绩'],
		            	columns: [
							  {
							    data: 'xuhao',
							    readOnly: true
							  },
		                      {
		                        data: 'edu_id',
		                        readOnly: true
		                      },
		                      {
		                        data: 'studentName'
		                      },
		                      {
		                        data: 'peacetime1',
		                        validator: /^\S{0,4}$/
		                      },
		                      {
		                        data: 'peacetime2',
		                        validator: /^\S{0,4}$/
		                      },
		                      {
			                     data: 'peacetime3',
			                     validator: /^\S{0,4}$/
			                  },
		                      {
			                    data: 'peacetime4',
			                    validator: /^\S{0,4}$/
			                  },
		                      {
				                data: 'peacetime5',
				                validator: /^\S{0,4}$/
				              },
				              {
					             data: 'daily_behave',
					             validator: /^\S{0,4}$/
					             
					          },
					          {
						             data: 'peacetime16',
						             validator: /^\S{0,4}$/
						      },
						      {
						             data: 'cql'
						      },
						      {
						             data: 'qqxs',
						             validator: /^-?\d+\.?\d{0,2}$/,
						     
						      },
						      {
						             data: 'examine_result',
						             validator: /^\S{0,4}$/
						      },
						      {
						             data: 'peacetime18',
						             validator: /^\S{0,4}$/
						      },
						      {
						             data: 'credit_hour'
						      },
						      {
						             data: 'credit_source',
						             type: 'dropdown',
						             source: ['是', '否'],
						             validator: /^['是','否']{1}$/
						      },
						      {
						             data: 'is_pass',
						             type: 'dropdown',
						             source: ['是', '否'],
						             validator: /^['是','否']{1}$/
						      },
						      {
						             data: 'level_name',
						      }
						      
		                   ],
		                   manualColumnResize:true,
		                   manualRowResize:true,
		                   afterChange : function (changes, source) {
		                	   if(source!='loadData'&&changes!=null&&changes.length!=0){
		                		   for(var i=0;i<changes.length;i++){
		                			   if(changes[i][2]==changes[i][3]){
		                				   continue;
		                			   }
		                			   $("#save").attr('disabled',false);
		                			   
		                			   var setDatas=[];
		                			   if(changes[i][1]=="qqxs"){   //缺勤学改变出勤率
		                				   var cql='100';
		                				   if(null==changes[i][3]||''==changes[i][3]){
		                				   }else{
		                					   if(changes[i][3]>period_count){   //缺勤学时超过课程学时 赋值为课程学时
		                						   setDatas.push([changes[i][0],11,period_count]);
				                				   this.setDataAtCell(setDatas, null, null, 'populateFromArray');
			                				   }
		                					   cql = ((period_count-changes[i][3])/period_count*100).toFixed(0);
		                				   }
		                				   setDatas.push([changes[i][0],10,cql+'%']);
		                				   this.setDataAtCell(setDatas, null, null, 'populateFromArray');
		                			   }else if(changes[i][1]=="credit_source"){  //免修改变学分
		                				   if(changes[i][3]=="是"){
		                					   setDatas.push([changes[i][0],14,credit_hour]);
		                					  setDatas.push([changes[i][0],16,'否']);
			                				   this.setDataAtCell(setDatas, null, null, 'populateFromArray'); 
		                				   }
		                			   }else if(changes[i][1]=="is_pass"){
		                				   if(changes[i][3]=="是"){
		                						setDatas.push([changes[i][0],14,0]);
		                						setDatas.push([changes[i][0],15,'否']);
			                					this.setDataAtCell(setDatas, null, null, 'populateFromArray'); 
		                				   }else{
		                						setDatas.push([changes[i][0],14,credit_hour]);
			                					setDatas.push([changes[i][0],16,'否']);
				                				this.setDataAtCell(setDatas, null, null, 'populateFromArray'); 
		                				   }
		                			   }
		                			   
		                		   }
		                		   
		                	   }
			    			   
		                   }
		            });
		            
		            hot1.updateSettings({
		                cells: function (row, col, prop) {
		                  var cellProperties = {};
		                  if (prop == 'edu_id' || prop == 'studentName' || prop=='cql'  || prop=='level_name' || prop=='credit_hour') {   //设置某字段不能编辑
		                    cellProperties.readOnly = true;
		                  }
		                  
		             	 /*    for(var i = 0;i<data.getMergeCells.length;i++){  
		                	  if(row === data.getMergeCells[i].row){  //最高成绩  最低成绩 平均成绩  标准差不可编辑
		                		  cellProperties.readOnly = true;
			                  }
		                 	for(var j = 0;j<data.modelScores.length;j++){
		                		  if(0 === data.getMergeCells[i].row){  //最高成绩 
		                			  hot1.getData()[i][j]="21321";
				                  }
		                		  
		                	  }
	                	  
		                  } */	
		                
		                  return cellProperties;
		                }
		              })
		              	 $("#save").click(function(){
		              			$(this).attr('disabled',true);
							   var class_model_id =  $("#class_model_id").val();
							   var segment_course_id =  $("#segment_course_id").val();
							   var courseType = $("#courseType").val();
							   var err = 0;
							   $("td").each(function(){
								   if($(this).attr("class")=="htInvalid"){
									   err=1;
								   }
								   if($(this).attr("class")=="htInvalid htAutocomplete"){
									   err=1;
								   }
							   });
							   
							   if(err==0){
								   var scoreJson = JSON.stringify({datas:hot1.getData()});
				              		  /* document.write(scoreJson);  */
		           	  					$.post("${ctx}/score/ScoreAction.a?saveScore",{"scoreJson":scoreJson,"courseType":courseType,"class_model_id":class_model_id,"segment_course_id":segment_course_id},function(data){
		           	  						if("success" == data.success){
		           	  							alert_common('操作成功','gritter-item1');
		           	  						}else{
		           	  							 alert_KG_chooseFile_tip("操作失败");
		           	  						}
		           	  					});
							   }else{
								   alert_KG_chooseFile_tip("有错误信息，请修改后在保存");
							   }
		              	
		         		 })
		              
				})
				
			
			});
			
	
			/* 
			  settings1 = {
			    data: data1
			  },
			  hot1;

			hot1 = new Handsontable(container1, settings1);
			//data1[0][1] = 'Ford'; // change "Kia" to "Ford" programatically
			hot1.render(); */
			
              if("1"=="${isToBake}"){
            	  $("#startInput").click();
              }
             
		})
		
		var isToBake ="${isToBake}";
		function exportParameter(){
			var courseType = $("#courseType").val();
			var gradeName =	$("#gradeName").val();
			var className = $("#className").val();
			var year = $("#year").val();
			var segmentId = $("#segmentId").val();
			var subjectId = $("#subjectId").val();
			var courseId = $("#courseId").val();
			
			$("#courseType2").val(courseType);
			$("#gradeName2").val(gradeName);
			$("#className2").val(className);
			$("#year2").val(year);
			$("#segmentId2").val(segmentId);
			$("#subjectId2").val(subjectId);
			$("#courseId2").val(courseId);
		}
		
		function  tableTitle(courseType){
			
			var gradeName1 = ""; 
			var className1 =""
			if("${KG_COURSE_NEIZHI}"==courseType){
				gradeName1 = $("#gradeName option:selected").html();
				className1 = $("#className option:selected").html()
			}
			var tableTitle = $("#year option:selected").html()+"学年  "
			+$("#segmentId option:selected").html()+"  "+gradeName1+" "+className1
			+" "+$("#subjectId option:selected").html()+" "+$("#courseId option:selected").html();
			$("#tableTitle").html(tableTitle); 
			
			$("#tableTitle2").val(tableTitle);
		}
	    
		/*****验证条件 *********/
		function validate(url){
			var courseType = $("#courseType").val();
			var gradeName =	$("#gradeName").val();
			var className = $("#className").val();
			var year = $("#year").val();
			var segmentId = $("#segmentId").val();
			var subjectId = $("#subjectId").val();
			var courseId = $("#courseId").val();
			
			 url+= "&&courseType="+courseType;
			if("${KG_COURSE_NEIZHI}"==courseType){
				if("0"==gradeName){
					 $("#MyDiv").hide();
					alert_KG_chooseFile_tip("请选择年级");
					return;
				}
				if("0"==className){
					 $("#MyDiv").hide();
					alert_KG_chooseFile_tip("请选择班级");
					return;
				}
				url += "&&classid="+className+"&&schoolyear="+year;
			}
			
			if("0"==year){
				 $("#MyDiv").hide();
				alert_KG_chooseFile_tip("请选择学年");
				return;
			}
			
			if("0"==segmentId){
				 $("#MyDiv").hide();
				alert_KG_chooseFile_tip("请选择学段");
				return;
			}
			if("0"==subjectId){
				 $("#MyDiv").hide();
				alert_KG_chooseFile_tip("请选择学科");
				return;
			}
			if("0"==courseId){
				 $("#MyDiv").hide();
				alert_KG_chooseFile_tip("请选择模块");
				return;
			}
			url += "&&segmentId="+segmentId+"&&courseId="+courseId+"&subjectId="+subjectId; 
			return url;
		}
			
		function scoreExport(){
			var  courseType = $("#courseType2").val();
			var  gradeName = $("#gradeName2").val();
			var className = $("#className2").val();
			var year = $("#year2").val();
			var segmentId = $("#segmentId2").val();
			var subjectId = $("#subjectId2").val();
			var courseId =$("#courseId2").val();
			var tableTitle2 = $("#tableTitle2").val();
			window.location.href="${ctx }/score/ScoreAction.a?scoreExport&&courseType="+courseType+"&&gradeName1="+gradeName+"&&className1="
					+className+"&&schoolyear="+year+"&&segmentId="+segmentId+"&&subjectId="+subjectId+"&&courseId="+courseId+"&&tableTitle2="+encodeURI(encodeURI(tableTitle2));
		}
		function hScoreExport(){
			var  courseType = $("#courseType2").val();
			var  gradeName = $("#gradeName2").val();
			var className = $("#className2").val();
			var year = $("#year2").val();
			var segmentId = $("#segmentId2").val();
			var subjectId = $("#subjectId2").val();
			var courseId =$("#courseId2").val();
			var tableTitle2 = $("#tableTitle2").val();
			window.location.href="${ctx }/score/ScoreAction.a?hScoreExport&&courseType="+courseType+"&&gradeName1="+gradeName+"&&className1="
			+className+"&&schoolyear="+year+"&&segmentId="+segmentId+"&&subjectId="+subjectId+"&&courseId="+courseId+"&&tableTitle2="+encodeURI(encodeURI(tableTitle2));
		}
		
		//获取班级
		function changeGrades(){
			$.post("${ctx}/score/ScoreAction.a?queryClass",null,function(data){
				var html = ""; 
				var gradeId = $("#gradeName").val().split("_")[0];
				if(!(null == gradeId || "" == gradeId )){
					var cs = data;
					for(var i=0;i<data.length ;i++){
						if(gradeId == data[i].gradeId){
							if(data[i].classId=="${className1}"){
								html +="<option selected='selected' value='"+data[i].classId+"'>"+data[i].className+"</option>";
							}else{
								html +="<option value='"+data[i].classId+"'>"+data[i].className+"</option>";
							}
						}
					}
				} 
				
				$("#className").html(html);

				if("1"==isToBake ){  //导入返回
					/* $('#className').attr('value',"${className1}");*/
					exportParameter();  //导入连接参数赋值
				}
				
				changeSubjectIdBySegmentId();
				
			})
		}
		
		
		//课程类型的变化
		function changeType(){
			var courseType = $("#courseType").val();
			
			if(courseType=="${KG_COURSE_KIND }"){
				$("#gradeName1").hide();
				$("#className1").hide();
			}else if("${KG_COURSE_NEIZHI }"==courseType){
				$("#gradeName1").show();
				$("#className1").show();
				changeGrades();
			}
			getYear();
			changeSubjectIdBySegmentId();
		}
		
		
		//获取学年
		function getYear(){
			var courseType = $("#courseType").val();
			var manyYears= $("#gradeName").val().split("_")[1];
			if("${KG_COURSE_KIND }" == courseType){
				manyYears = "3";
			}
			
			$.post("${ctx}/score/ScoreAction.a?queryXueYear&&manyYears="+manyYears,null,function(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					if(data[i].split("@")[0]=="${schoolyear}"){
						html+="<option selected='selected' value='"+data[i].split("@")[0]+"'>"+data[i].split("@")[1]+"</option>";
					}else{
						html+="<option value='"+data[i].split("@")[0]+"'>"+data[i].split("@")[1]+"</option>";
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				$("#year").html(html);
				
				
				if("1"==isToBake ){  //导入返回
					/* $('#year').attr('value',"${schoolyear}"); */  
					exportParameter();  //导入连接参数赋值
				}
				
				getSegment();
				/* changeClassId(); */
				
			})
			
		}
		
		
		//获取学段
		function getSegment(){
			
			var schoolyear = $("#year").val();
			$.post("${ctx}/score/ScoreAction.a?querySegmentId&&schoolyear="+schoolyear,null,function(data){
				var html = ""; 
				for(var i=0;i<data.length;i++){
					if(data[i].segment_id=="${segmentId}"){
						html+="<option selected='selected' value='"+data[i].segment_id+"'>"+data[i].segment_name+"</option>";
					}else{
						html+="<option value='"+data[i].segment_id+"'>"+data[i].segment_name+"</option>";
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				
				$("#segmentId").html(html);
				
				
				if("1"==isToBake ){  //导入返回
					/* $('#segmentId').attr('value',"${segmentId}"); */ 
					exportParameter();  //导入连接参数赋值
				}
				
				changeSubjectIdBySegmentId();
				
			})
		}
/* 		
		function changeClassId(){  //学年改变班级id
			var year = $("#year").val();
			var classId = $("#className").val();
			$.post("${ctx}/score/ScoreAction.a?queryClassIdBySchoolyearAndClassid&&schoolyear="+year+"&&classid="+classId,null,function(data){
				$("#className").val(data);
			})	
		} */
		
		//学段改变学科
		function changeSubjectIdBySegmentId(){
			var segmentId = $("#segmentId").val();
			var courseType = $("#courseType").val();
			var className = $("#className").val();
			var schoolyear = $("#year").val();
			
			$.post("${ctx}/score/ScoreAction.a?querySubjectBySegmentId&&segmentId="+segmentId+"&&courseType="+courseType+"&&classid="+className+"&&schoolyear="+schoolyear,null,function(data){
				var html = ""; 
				for(var i=0;i<data.length;i++){
					if(data[i].subject_id=="${subjectId}"){
						html+="<option selected='selected' value='"+data[i].subject_id+"'>"+data[i].subject_name+"</option>"; 
					}else{
						html+="<option value='"+data[i].subject_id+"'>"+data[i].subject_name+"</option>"; 
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				$("#subjectId").html(html);
				
				if("1"==isToBake ){  //导入返回
					/* $('#subjectId').attr('value',"${subjectId}"); */  
					exportParameter();  //导入连接参数赋值
				}
				
				changeCourseBySubjectIdAndCourseType();
			})	
			
		}
		
		//学科变化改变模块
		function changeCourseBySubjectIdAndCourseType(){
			var segmentId = $("#segmentId").val();
			var subjectId = $("#subjectId").val();
			var courseType = $("#courseType").val();
			var className = $("#className").val();
			var schoolyear = $("#year").val();
			$.post("${ctx}/score/ScoreAction.a?queryCourseBySubjectIdAndCourseType&&subjectId="+subjectId+"&&courseType="+courseType+"&&segmentId="+segmentId+"&&classid="+className+"&&schoolyear="+schoolyear,null,function(data){
				var html = ""; 
				for(var i=0;i<data.length;i++){
					var course_id=data[i].course_id+"_"+data[i].isflag;
// 					if(course_id=="${courseId}"){
// 						html+="<option selected='selected' value='"+data[i].course_id+"_"+data[i].isflag+"'>"+data[i].course_name+"</option>";
// 					}else{
// 						html+="<option value='"+data[i].course_id+"_"+data[i].isflag+"'>"+data[i].course_name+"</option>";
// 					}
					if(course_id=="${courseId}"){
						html+="<option selected='selected' value='"+data[i].course_id+"'>"+data[i].course_name+"</option>";
					}else{
						html+="<option value='"+data[i].course_id+"'>"+data[i].course_name+"</option>";
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				$("#courseId").html(html);
				
				if("1"==isToBake ){  //导入返回
					/* $('#courseId').attr('value',"${courseId}");  */ 
					exportParameter();  //导入连接参数赋值
				}
			
			})	
			
		}
	
		
	</script>
	<%@ include file="/common/queryLoadDiv.jsp"%>
</head>
<body style="background: #EEE" onload="changeType();"> 
	<div style="width: 100%;">
	<!-- 常用功能  -->
	  			<div id="breadcrumb" style="margin-top: 9px;margin-bottom: 10px">
					<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>成绩录入</a>
				</div>
		<div style="width: 99%;height:50px;border: 1px solid #CDCDCD;line-height: 53px;margin-left: 5px">
			 <ul style="margin-top: 5px">
			 
			 	<li style="margin-left: 2%">
			 	   课程类型：
			 		<select onchange="changeType();"  id="courseType">
<%-- 			 				<option value="${KG_COURSE_NEIZHI }">内置课程</option>
			 				<c:if test="${userRealType ne USER_MASTERROLE_TYPESTR}">
			 					<option value="${KG_COURSE_KIND }">校本课程</option>
			 				</c:if> --%>
			 				<c:if test="${!empty courseTypes}">
			 					<c:forEach items="${courseTypes}" var="coursetype">
			 						<option value="${fn:split(coursetype,'@')[0]}">${fn:split(coursetype,'@')[1]}</option>
			 					</c:forEach>
			 				</c:if>
			 		</select>
			 	</li>
			 	
			 	<li class="li"  id="gradeName1">
			 	   年级：
			 		<select onchange="changeGrades();getYear();"  id="gradeName">
			 			<c:forEach items="${grades}" var="grade" varStatus="i">
			 				<c:if test="${i.index eq 0}">
			 					<c:set value="${grade.key }"  var="FirstgradeId"/>
			 				</c:if>
			 				<option value="${grade.key }">${grade.value }</option>
			 			</c:forEach>
			 			<c:if test="${fn:length(grades) eq 0}"><option value="0">无</option></c:if>
			 		</select>
			 	</li>
			 	<li class="li"  id="className1">
			 	   班级：
			 		<select id="className" onchange="changeSubjectIdBySegmentId();">
			 			<c:forEach items="${campus}" var="campu">
			 				  <c:if test="${fn:split(FirstgradeId, '_')[0] eq campu.gradeId}">
			 					<option value="${campu.classId }">${campu.className }</option>
			 				  </c:if>
			 			</c:forEach> 
			 			 <c:if test="${fn:length(campus) eq 0}"><option value="0">无</option></c:if>
			 		</select>
			 	</li>
			 	<li class="li">
			 	   学年：
						<select name="year" id="year" onchange="getSegment();">
               				<c:forEach var="xueYear" items="${xueYears }">
               					<option value="${fn:split(xueYear, '@')[0]}">${fn:split(xueYear, '@')[1]}</option>
               				</c:forEach>
               				<c:if test="${fn:length(xueYears) eq 0}"><option value="0">无</option></c:if>
              			</select>
			 	</li>
			 	<li class="li">
			 	   学段：
			 		<select id="segmentId" name="segmentId" onchange="changeSubjectIdBySegmentId();">
						<c:forEach var="segment" items="${segments }">
							<option value="${segment.segment_id}">${segment.segment_name}</option>
						</c:forEach>
						<c:if test="${fn:length(segments) eq 0}"><option value="0">无</option></c:if>
			 		</select>
			 	</li>
			 	<li class="li">
			 	   学科：
			 			<select name="subjectId" id="subjectId"  onchange="changeCourseBySubjectIdAndCourseType();">
               				<c:forEach var="subjectDto" items="${subjectDtos }">
               					<option value="${subjectDto.subject_id}">${subjectDto.subject_name}</option>
               				</c:forEach>
               				<c:if test="${fn:length(subjectDtos) eq 0}"><option value="0">无</option></c:if>
              			</select>
			 	</li>
			 	<li class="li">
			 	 模块：
			 		<select id="courseId">
			 			<c:forEach var="courseDto" items="${courseDtos }">
			 				<option value="${courseDto.course_id}">${courseDto.course_name}</option> 
<%-- 			 				<option value="${courseDto.course_id}_${courseDto.isflag}">${courseDto.course_name}</option> --%>
			 			</c:forEach>
			 			<c:if test="${fn:length(courseDtos) eq 0}"><option value="0">无</option></c:if>
			 		</select>
			 	</li>
			 	<li class="li" >
			 	
			 	<span>&nbsp;
			 	 	</span>
			 	    <input type="button" class="btn btn-success"  id="startInput"  value="开始录入" style="width: 70px;margin-bottom: 7px" />
			 	</li>
			 	 <li class="li"><span>&nbsp;
			 	 	</span>
			  		<input type="button" class="btn btn-success"  id="export"  value="导 出" style="width: 70px;margin-bottom: 7px" />
				 </li>
			 </ul>
		</div>
		<br />
		<span style="margin-left: 30px;" id="viewDto" >
		</span><!-- margin-left:-0.7%; -->
		<div style="width: 100%;margin-top: 13px;" id="biaoge">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div  style="background: #279F46;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								<center><span   id="tableTitle" ></span></center>
							</div>
							<div class="widget-content nopadding" id="example1" style="margin-bottom: 30px;height:0px;">
								
						<!-- 		<div class="form-actions pagination alternate"  style="margin-top: 0px;margin-bottom: 0px;">
									<ul style="margin-left: 20px;margin-top: 7px">
										<li class="disabled"><a href="#">上一页</a></li>
										<li class="active">
										   <a href="#">1</a>
										</li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">下一页</a></li>
									</ul>
										<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
										<input onclick="scoreExport();" type="button" class="btn btn-success" style="width: 110px;" id="query"  value="导入模块成绩" />
										<input onclick="hScoreExport();" type="button" class="btn btn-success"  style="width: 110px;" id="query"  value="导入会考成绩" />
										<input  type="button" class="btn btn-success" style="width: 70px;" id="save"  value="保 存" />
								     </span>
								</div> -->									
							</div>
						</div>
		</div>
	</div>
	<input type="hidden"  value=""  id="class_model_id"/>
	<input type="hidden" value=""  id="segment_course_id" />
	
	<input type="hidden"  value="" id="courseType2"/>
	<input type="hidden"  value="" id="gradeName2"/>
	<input type="hidden"  value="" id="className2"/>
	<input type="hidden"  value="" id="year2"/>
	<input type="hidden"  value="" id="segmentId2"/>
	<input type="hidden"  value="" id="subjectId2"/>
	<input type="hidden"  value="" id="courseId2"/>
	<input type="hidden" value="" id="tableTitle2" />
	
</body>
</html>