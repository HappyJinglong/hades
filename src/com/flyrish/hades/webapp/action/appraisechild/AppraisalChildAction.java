package com.flyrish.hades.webapp.action.appraisechild;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IAppraisalChildExt;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class AppraisalChildAction extends BaseAction{
    
	//评语
	public String apprasial;
	
	// 评价时间
	public String signDate;
	
	//被评价人姓名
	public String name;
	
	//评价类型id
	public String evaluatetypeid;
	
	//评价标识号id
	public String appraisalid;
	
	//教育id
	public String edu_id;
	
	public String showdiv;
	
	//评价孩子家长评语和期望列表
	public List<AppraisalDto> appraiselist1;
	
	//评价孩子思想道德列表
	public List<AppraisalDto> appraiselist2;
	
	//评价孩子学业成就列表
	public List<AppraisalDto> appraiselist3;
	
	//评价孩子合作与交流列表
	public List<AppraisalDto> appraiselist4;
	
	//评价孩子运动与健康列表
	public List<AppraisalDto> appraiselist5;
	
	//评价孩子审美与表现列表
	public List<AppraisalDto> appraiselist6;
	
	//评价孩子个性发展列表
	public List<AppraisalDto> appraiselist7;
	
	//评价学期开始的我家长的期望列表
	public List<AppraisalDto> appraiselist8;
	
	//学生id
	public String studentid;
	
	//班级id
	public String classid;
	public String evaluateTypename;
	
	//学生对象集合
	List<StudentDto> students;
	
	//选择的学期
	public String termid;
	
	//当前学期
	public String termid1;
	
	//默认显示当前时间
	public String date_content;
	
	//高中或者初中的levelcode1
	public String levelcode1;
	
	public String CMIS30ID;
	
	public String DISCODE;
	
	//年级
	public String grade;
	//年级id
	public String gradenum;
	//学段名
	public String levelname;
	//班级
	public String classname;
	//签名人
	public String parentname;
	//提前获取的插入id
	public String insertId;
	//评价人id
	public String appraserid;
	public UserDto userDto;
	//家长在各个栏目评价集合
	public List<StudentDto> sections=new ArrayList<StudentDto>();
	//评价孩子记录列表
	public List<AppraisalDto> apprasialist=new ArrayList<AppraisalDto>();
	public SpeekDto speekDto;
	@Spring
	public IAppraisalChildExt appraisalChildExt;
	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	
	@Before
	public Object doBefore(HttpServletRequest req,HttpServletResponse response,HttpSession session)
	{
		userDto = this.getLoginInfo(req);
		edu_id = userDto.getEduId();
		CMIS30ID = userDto.getCmis30id();
		DISCODE = userDto.getDiscode();
		levelcode1 = userDto.getLevelcode();
		name = userDto.getStudentName();
		classid = userDto.getClassid();
		classname = userDto.getClassName();
		grade = userDto.getGradeName();
		gradenum = userDto.getGradenum();
		levelname = userDto.getLevelName();
		studentid = userDto.getPersonid();
		appraserid = userDto.getUsername();
		termid1 = userDto.getTermId();
		session.setAttribute("termid1",termid1);
		Date date_now=new Date();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		date_content=simple.format(date_now);
		return null;
	}
	@DefaultAction
	public Object doQueryAppraisalChild() {

		if (NestUtil.isNotEmpty(showdiv)) {
			appraiselist1 = new ArrayList<AppraisalDto>();
			appraiselist2 = new ArrayList<AppraisalDto>();
			appraiselist3 = new ArrayList<AppraisalDto>();
			appraiselist4 = new ArrayList<AppraisalDto>();
			appraiselist5 = new ArrayList<AppraisalDto>();
			appraiselist6 = new ArrayList<AppraisalDto>();
			appraiselist7 = new ArrayList<AppraisalDto>();
			appraiselist8 = new ArrayList<AppraisalDto>();
			if ("0".equals(constantBean.get("isStartAppraiseCache"))) {
				parentname = appraisalChildExt.getParentInfo(studentid,
						CMIS30ID, DISCODE);
				try {
					if (Constant.DICT_TYPE_LEVELCODE_CZ == Integer
							.parseInt(levelcode1)) {
						cz_queryApprasialChild(); // 跳转初中评价孩子
					} else if (Constant.DICT_TYPE_LEVELCODE_GZ == Integer
							.parseInt(levelcode1)
							|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer
									.parseInt(levelcode1)) {

						gz_queryApprasialChild(); // 跳转高中评价孩子
					}
					return "jz_edit.jsp";
				} catch (NumberFormatException e) {
					logger.error("doQueryAppraisalChild()", e);
					return "error.jsp";
				}
			}else if("1".equals(constantBean.get("isStartAppraiseCache")))
			{
				
				parentname = appraisalChildExt.getParentInfo(studentid,
						CMIS30ID, DISCODE);
				try {
					if (Constant.DICT_TYPE_LEVELCODE_CZ == Integer
							.parseInt(levelcode1)) {
						querypartinfoForCache(); // 跳转初中评价孩子
					} else if (Constant.DICT_TYPE_LEVELCODE_GZ == Integer
							.parseInt(levelcode1)
							|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer
									.parseInt(levelcode1)) {

						queryApprasialforCache();// 跳转高中评价孩子
					}
					return "jz_edit.jsp";
				} catch (NumberFormatException e) {
					logger.error("doQueryAppraisalChild()", e);
					return "error.jsp";
				}
			}
		} else {
			return "showdiv.jsp";
		}
		return null;
	}
	
	/**
	 * 家长评价孩子(高中)
	 * @return 高中评价孩子
	 */
	private void gz_queryApprasialChild()
	{
		AppraisalDto appraisal = new AppraisalDto();
		appraisal.setEduid(edu_id);
		appraisal.setAppraseridentify(5);
		appraisal.setCmis30id(CMIS30ID == null ? null : Integer.parseInt(CMIS30ID));
		appraisal.setDiscode(DISCODE == null ? null : Integer.parseInt(DISCODE));
		if (termid != null) {
			appraisal.setSemesterid(Integer.parseInt(termid));
			apprasialist = appraisalChildExt.getAppraisalChildList(appraisal,
					Integer.parseInt(levelcode1));
		} else {
			String termid2 = gradenum+termid1.substring(4, 5);
			appraisal.setSemesterid(Integer.parseInt(termid2));
			apprasialist = appraisalChildExt.getAppraisalChildList(appraisal,
					Integer.parseInt(levelcode1));
		}
		totalToeveryForHigh();
	}
	/**
	 * 将一个整的链表划分为子链表(高中)
	 */
	private void totalToeveryForHigh(){
	
		
	if(apprasialist==null){
		 apprasialist=new ArrayList<AppraisalDto>();
	}
		for (AppraisalDto app : apprasialist) {
			String tt = app.getAppraisaltypeid().toString();
			// 评价孩子家长评语和期望列表2040
			if (Constant.TYPE_END_JZPYQW.equals(tt)) {
				appraiselist1.add(app);
			}
			// 评价孩子思想道德列表3020
			if (Constant.TYPE_SX_TRPJ.equals(tt)) {
				appraiselist2.add(app);
			}
			// 评价孩子合作与交流列表4020
			if (Constant.TYPE_HZ_TRPJ.equals(tt)) {
				appraiselist4.add(app);
			}
			// 评价孩子运动与健康列表 5020
			if (Constant.TYPE_YDJK_TRPJ.equals(tt)) {
				appraiselist5.add(app);
			}
			// 评价孩子审美与表现列表6020
			if (Constant.TYPE_SMYBX_TRPJ.equals(tt)) {
				appraiselist6.add(app);
			}
			// 评价孩子个性发展列表7030
			if (Constant.TYPE_GXFZ_TRPJ.equals(tt)) {
				appraiselist7.add(app);
			}
			// 评价孩子学业成就列表 8040
			if (Constant.TYPE_XY.equals(tt)) {
				appraiselist3.add(app);
			}
			// 新学期家长的期望列表1020
			if (Constant.TYPE_START_WDFZMB.equals(tt)) {
				appraiselist8.add(app);
			}
			 Collections.sort(appraiselist1,compareByAppraiseId);
			  Collections.sort(appraiselist2,compareByAppraiseId);
			  Collections.sort(appraiselist3,compareByAppraiseId);
			  Collections.sort(appraiselist4,compareByAppraiseId);
			  Collections.sort(appraiselist5,compareByAppraiseId);
			  Collections.sort(appraiselist6,compareByAppraiseId);
			  Collections.sort(appraiselist7,compareByAppraiseId);
			  Collections.sort(appraiselist8,compareByAppraiseId);
		}
		
		
		SetApprasialChildDto(Constant.TYPE_START_WDFZMB, Constant.TYPE_XXQQJZ,
				appraiselist8);
		SetApprasialChildDto(Constant.TYPE_END_JZPYQW, Constant.TYPE_XXQJS,
				appraiselist1);
		SetApprasialChildDto(Constant.TYPE_SX_TRPJ, Constant.TYPE_SXDD,
				appraiselist2);
		SetApprasialChildDto(Constant.TYPE_XY, Constant.TYPE_XYCJ,
				appraiselist3);
		SetApprasialChildDto(Constant.TYPE_HZ_TRPJ, Constant.TYPE_HZYJL,
				appraiselist4);
		SetApprasialChildDto(Constant.TYPE_YDJK_TRPJ, Constant.TYPE_YDYJK,
				appraiselist5);
		SetApprasialChildDto(Constant.TYPE_SMYBX_TRPJ, Constant.TYPE_SMYBX,
				appraiselist6);
		SetApprasialChildDto(Constant.TYPE_GXFZ_TRPJ, Constant.TYPE_GXYFZ,
				appraiselist7);
	}
	/**
	 * dto转换，AapprasialCacheDto转为apprasialDto   (高中)
	 */
	private void CacheDtoTdto()
	{
		List<String> high_list = new ArrayList<String>();
		high_list.add(Constant.TYPE_END_JZPYQW);
		high_list.add(Constant.TYPE_SX_TRPJ);
		high_list.add(Constant.TYPE_XY);
		high_list.add(Constant.TYPE_HZ_TRPJ);
		high_list.add(Constant.TYPE_YDJK_TRPJ);
		high_list.add(Constant.TYPE_SMYBX_TRPJ);
		high_list.add(Constant.TYPE_GXFZ_TRPJ);
		high_list.add(Constant.TYPE_START_WDFZMB);
		
		
		if (termid != null) {
			
		} else {
			 termid = gradenum+termid1.substring(4, 5);
		
		}
		
		for(String sectionId : high_list)
		{
			List<AapprasialCacheDto> cachelist = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, sectionId, "5", appraserid,AapprasialCacheDto.class);
			if(cachelist==null||cachelist.size()==0){
				 			
			}else{
				for (AapprasialCacheDto cacheDto : cachelist) {
					AppraisalDto aDto = new AppraisalDto();
					aDto.setApprasialid(cacheDto.getApprasialid());
					aDto.setAppraisaltypeid(cacheDto.getAppraisaltypeid() == null ? null
							: Integer.parseInt(cacheDto.getAppraisaltypeid()));
					aDto.setAppraser(cacheDto.getAppraser());
					aDto.setApprasial(cacheDto.getApprasial());
					//aDto.setSigndate(StringToDate(cacheDto.getSigndate()));
					aDto.setSigndate(StringToDatexie(cacheDto.getSigndate()));
					apprasialist.add(aDto);
				}
			
			}
			
			
		}
	}
	/**
	 * 从缓存查询数据(高中)
	 */
	private void queryApprasialforCache()
	{
		CacheDtoTdto();
		totalToeveryForHigh();
	}
	/**
	 * 家长评价孩子(初中)
	 * @return 初中评价孩子
	 */
	private void cz_queryApprasialChild()
	{
		AppraisalDto appraisal = new AppraisalDto();
		appraisal.setEduid(edu_id);
		appraisal.setAppraseridentity(Constant.APPRASER_PARENT);
		appraisal.setCmis30id(CMIS30ID==null?null:Integer.parseInt(CMIS30ID));
		appraisal.setDiscode(DISCODE==null?null:Integer.parseInt(DISCODE));
		if (termid != null) {
			appraisal.setSemesterid(Integer.parseInt(termid));
		     apprasialist = appraisalChildExt.getAppraisalChildList(appraisal,Integer.parseInt(levelcode1));
	    } else {
	    	appraisal.setSemesterid(Integer.parseInt(termid1));
		     apprasialist = appraisalChildExt.getAppraisalChildList(appraisal,Integer.parseInt(levelcode1));
	    }
		totalToeveryForJunior();
	}
	/**
	 * 将总的盘评价链表划分为子链表(初中)
	 */
	private void totalToeveryForJunior(){
		if(apprasialist!=null){
		
		for (AppraisalDto app: apprasialist) {
			String tt=app.getAppraisaltypeid().toString();
			 //评价孩子家长评语和期望列表23
			if(Constant.PRAENTS_APPRAISAL_EXPECT.equals(tt))
			{
				 appraiselist1.add(app);
			}
			//评价孩子思想道德列表34
			if(Constant.MORALITY_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist2.add(app);
			}
			//评价孩子合作与交流列表54
			if(Constant.COOPERATION_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist4.add(app);
			}
			//评价孩子运动与健康列表 64
			if(Constant.PLAY_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist5.add(app);
			}
			//评价孩子审美与表现列表74
			if(Constant.AESTHETIC_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist6.add(app);
			}
			//评价孩子个性发展列表94 
			if(Constant.INDIVIDUALITY_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist7.add(app);
			}
			//评价孩子学业成就列表 46
			if(Constant.WORKS_PARENT_APPRAISAL.equals(tt))
			{
				 appraiselist3.add(app);
			}
			  Collections.sort(appraiselist1,compareByAppraiseId);
			  Collections.sort(appraiselist2,compareByAppraiseId);
			  Collections.sort(appraiselist3,compareByAppraiseId);
			  Collections.sort(appraiselist4,compareByAppraiseId);
			  Collections.sort(appraiselist5,compareByAppraiseId);
			  Collections.sort(appraiselist6,compareByAppraiseId);
			  Collections.sort(appraiselist7,compareByAppraiseId);
		}
		SetApprasialChildDto(Constant.PRAENTS_APPRAISAL_EXPECT,Constant.TYPE_XXQJS,appraiselist1);
		SetApprasialChildDto(Constant.MORALITY_PARENT_APPRAISAL,Constant.TYPE_SXDD,appraiselist2);
		SetApprasialChildDto(Constant.WORKS_PARENT_APPRAISAL,Constant.TYPE_XYCJ,appraiselist3);
		SetApprasialChildDto(Constant.COOPERATION_PARENT_APPRAISAL,Constant.TYPE_HZYJL,appraiselist4);
		SetApprasialChildDto(Constant.PLAY_PARENT_APPRAISAL,Constant.TYPE_YDYJK,appraiselist5);
		SetApprasialChildDto(Constant.AESTHETIC_PARENT_APPRAISAL,Constant.TYPE_SMYBX,appraiselist6);
		SetApprasialChildDto(Constant.INDIVIDUALITY_PARENT_APPRAISAL,Constant.TYPE_GXYFZ,appraiselist7);
		}
		}
	/**
	 * dto转换，PartInfoCacheDto转为apprasialDto   (初中)
	 */
	private void partInfoCacheDtoTdto()
	{
	if (termid != null) {
			
		} else {
			 termid = termid1;
		
		}
		List<String> high_list = new ArrayList<String>();
		high_list.add(Constant.PRAENTS_APPRAISAL_EXPECT);
		high_list.add(Constant.MORALITY_PARENT_APPRAISAL);
		high_list.add(Constant.WORKS_PARENT_APPRAISAL);
		high_list.add(Constant.COOPERATION_PARENT_APPRAISAL);
		high_list.add(Constant.PLAY_PARENT_APPRAISAL);
		high_list.add(Constant.AESTHETIC_PARENT_APPRAISAL);
		high_list.add(Constant.INDIVIDUALITY_PARENT_APPRAISAL);
		for(String sectionId : high_list)
		{
			List<PartInfoCacheDto> cachelist = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, sectionId, Constant.APPRASER_PARENT, appraserid,PartInfoCacheDto.class);
			if(cachelist==null||cachelist.size()==0) continue;
			for (PartInfoCacheDto cacheDto : cachelist) {
				AppraisalDto aDto = new AppraisalDto();
				//aDto.setApprasialid(cacheDto.getUserid());
				aDto.setApprasialid(cacheDto.getPart_id());
				 String ss=cacheDto.getPart_id();
				aDto.setAppraisaltypeid(cacheDto.getTwo_part_id() == null ? null
						: Integer.parseInt(cacheDto.getTwo_part_id()));
				aDto.setAppraser(cacheDto.getSigner_name());
				aDto.setApprasial(cacheDto.getPart_info());
				aDto.setSigndate(StringToDatexie(cacheDto.getCreateDate()));
				apprasialist.add(aDto);
			}
		}
	}
	/**
	 * 从缓存查询数据(初中)
	 */
	private void querypartinfoForCache()
	{
		partInfoCacheDtoTdto();
		totalToeveryForJunior();
	}
	private void SetApprasialChildDto(String sectionId,String sectionName,List<AppraisalDto> appraisal)
	{
		StudentDto apprasialchilddto=new StudentDto();
		apprasialchilddto.setSectionId(sectionId);
		apprasialchilddto.setSectionName(sectionName);
		apprasialchilddto.setaInfos(appraisal);
		sections.add(apprasialchilddto);
	}
	
	//@Json
	/**
	 * 新增家长评价
	 * @param response
	 * @param session
	 */
	public void doInsertAppraisalChild(HttpServletResponse response,HttpSession session)
    {
		if("0".equals(constantBean.get("isStartAppraiseCache")))    //不先走缓存
		{
			insertApprasialforDB(response);
		}else if("1".equals(constantBean.get("isStartAppraiseCache"))){
			insertApprasialforCache(response,session);
		}
	}
	/**
	 * 增加家长评价记录，插入到数据库
	 */
	private void insertApprasialforDB(HttpServletResponse response)
	{
		try {
			String insertid="";
			AppraisalDto appraisal = new AppraisalDto();
			appraisal.setAppraisaltypeid(Integer.parseInt(evaluatetypeid));
			appraisal.setAppraser(parentname);
			appraisal.setApprasial(apprasial);
			appraisal.setEduid(edu_id);
			appraisal.setStudentid(studentid);
			appraisal.setApprasialid1(appraserid);
			appraisal.setSemesterid(Integer.parseInt(termid));
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
				appraisal.setAppraseridentity(Constant.APPRASER_PARENT);
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
				appraisal.setAppraseridentify(5);
			}
			appraisal.setSigndate(StringToDatexie(signDate));
			appraisal.setCmis30id(CMIS30ID==null?null:Integer.parseInt(CMIS30ID));
			appraisal.setDiscode(DISCODE==null?null:Integer.parseInt(DISCODE));
			insertid=appraisalChildExt.InsertAppraisalChild(appraisal, Integer.parseInt(levelcode1));
			response.getWriter().write(insertid);
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
				appraisal.setAppraseridentity(Constant.APPRASER_PARENT);
				 
			     Integer  discode=Integer.valueOf(DISCODE);
			     Integer  cmis30id=Integer.valueOf(CMIS30ID);
			
			     speekDto=czplayAndHealthExt.czfindSpeek(insertid,discode,cmis30id);
				  if(speekDto!=null){
					    String stuEduid =speekDto.getStuEduid();
					    String  studentName=speekDto.getStudentName(); 
					    String evaluateType=speekDto.getAppraisaltypeid();
					   
					    if( stuEduid!=null){
						   if("46".equals(evaluateType)){
					    		evaluateTypename="学业成就";
					    	}else if("34".equals(evaluateType)){
					    		evaluateTypename="思想道德";
					    	}else if("54".equals(evaluateType)){
					    		evaluateTypename="合作与交流";
					    	}else if("64".equals(evaluateType)){
					    		evaluateTypename="运动与健康";
					    	}else if("74".equals(evaluateType)){
					    		evaluateTypename="审美与表现";
					    	}else if("94".equals(evaluateType)){
					    		evaluateTypename="个性发展";
					    	}else if("23".equals(evaluateType)){
					    		evaluateTypename="学期结束家长评语和期望";
					    	}
						   String columnName=evaluateTypename;
						    Date date=new Date();
						    String proKey=insertid;
						   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
                      }
		        }
			     
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
				 Integer  apprasialid=Integer.valueOf(insertid); 
			     Integer  discode=Integer.valueOf(DISCODE);
			     Integer  cmis30id=Integer.valueOf(CMIS30ID);
			      speekDto=playAndHealthExt.findSpeek(apprasialid,discode,cmis30id);
			      if(speekDto!=null){
					    String stuEduid =speekDto.getStuEduid();
					    String  studentName=speekDto.getStudentName(); 
					    String evaluateType=speekDto.getAppraisaltypeid();
					   if(stuEduid!=null){
							
						   if("8040".equals(evaluateType)){
					    		evaluateTypename="学业成就";
					    	}else if("3020".equals(evaluateType)){
					    		evaluateTypename="思想道德";
					    	}else if("4020".equals(evaluateType)){
					    		evaluateTypename="合作与交流";
					    	}else if("5020".equals(evaluateType)){
					    		evaluateTypename="运动与健康";
					    	}else if("6020".equals(evaluateType)){
					    		evaluateTypename="审美与表现";
					    	}else if("7030".equals(evaluateType)){
					    		evaluateTypename="个性发展";
					    	}else if("1020".equals(evaluateType)){
					    		evaluateTypename="新学期家长的期望";
					    	}else if("2040".equals(evaluateType)){
					    		evaluateTypename="学期结束家长评语和期望";
					    	}
						   String columnName=evaluateTypename;
						    Date date=new Date();
						    String proKey=String.valueOf(apprasialid);
						   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
					   }
		      }
			}
			
		} catch (ManagerException e) {
            try {
				response.getWriter().write("false");
			} catch (IOException e1) {	
			}
            logger.error("insertApprasialforDB(HttpServletResponse)", e);
		}catch (IOException e) {}
	}
	/**
	 * 增加家长评价记录，插入到缓存中
	 */
	private void insertApprasialforCache(HttpServletResponse response,HttpSession session)
	{
		String insertid="";
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {     //高中
				insertid = baseInforManagerExt.queryProKey("partinfo");
				PartInfoCacheDto partinfo = getPartInfoCache();
				params.put("studentid", partinfo.getStudentid());  
			    params.put("appraisaltypeid", partinfo.getTwo_part_id());
			    params.put("semesterid", partinfo.getTermid());
			    params.put("appraser", partinfo.getSigner_name());
			    params.put("apprasial", partinfo.getPart_info());
			    params.put("signdate", partinfo.getCreateDate());
			    params.put("edu_id",partinfo.getEdu_id());
			    params.put("cmis30id", partinfo.getCmis30id());
			    params.put("discode", partinfo.getDiscode());
			    params.put("appraiserid", partinfo.getUserid());
			    params.put("appraseridentify", partinfo.getWrite_man());
			    params.put("insertId", insertid);
			    partinfo.setPart_id(insertid);
			    ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params, "AppraisalChildExtImpl.InsertAppraisalChildCade.add2");
				latestEvaluationRecordExt.addRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, Constant.APPRASER_PARENT, appraserid, insertid, partinfo, sqlDemo,partinfo.getCreateDate());
				response.getWriter().write(insertid);
			
		
					    String stuEduid =edu_id;
					    String  studentName=name; 
					   
					    if( stuEduid!=null){
					    	String evaluateType =partinfo.getTwo_part_id();
						   if("46".equals(evaluateType)){
					    		evaluateTypename="学业成就";
					    	}else if("34".equals(evaluateType)){
					    		evaluateTypename="思想道德";
					    	}else if("54".equals(evaluateType)){
					    		evaluateTypename="合作与交流";
					    	}else if("64".equals(evaluateType)){
					    		evaluateTypename="运动与健康";
					    	}else if("74".equals(evaluateType)){
					    		evaluateTypename="审美与表现";
					    	}else if("94".equals(evaluateType)){
					    		evaluateTypename="个性发展";
					    	}else if("23".equals(evaluateType)){
					    		evaluateTypename="学期结束家长评语和期望";
					    	}
						    String columnName=evaluateTypename;
						    Date date= new Date();
						    String proKey=insertid;
						   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
					    }
			
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {   //初中
				 insertid=baseInforManagerExt.queryProKey("a_apprasial");
				 AapprasialCacheDto appraisal = getAapprasialChacheDto(session);
				 params.put("studentid", appraisal.getStudentid());  
			     params.put("appraisaltypeid", appraisal.getAppraisaltypeid());
			     params.put("semesterid", appraisal.getSemesterid());
			     params.put("appraser", appraisal.getAppraser());
			     params.put("apprasial", appraisal.getApprasial());
			     params.put("signdate", appraisal.getSigndate());
			     params.put("edu_id",appraisal.getEdu_id());
			     params.put("cmis30id", appraisal.getCmis30id());
			     params.put("discode", appraisal.getDiscode());
			     params.put("appraiserid", appraisal.getAppraiserrid());
			     params.put("appraseridentify", appraisal.getAppraseridentify());
			     params.put("insertId", insertid);
			     appraisal.setApprasialid(insertid);
			     ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params, "AppraisalChildExtImpl.InsertAppraisalChildCade.add1");
				 latestEvaluationRecordExt.addRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, "5", appraserid, insertid, appraisal, sqlDemo,appraisal.getSigndate());
			
				 response.getWriter().write(insertid);
			
				 
				    String stuEduid =edu_id;
				    String  studentName=name; 
				    String evaluateType=appraisal.getAppraisaltypeid();
				   if(stuEduid!=null){
						
					   if("8040".equals(evaluateType)){
				    		evaluateTypename="学业成就";
				    	}else if("3020".equals(evaluateType)){
				    		evaluateTypename="思想道德";
				    	}else if("4020".equals(evaluateType)){
				    		evaluateTypename="合作与交流";
				    	}else if("5020".equals(evaluateType)){
				    		evaluateTypename="运动与健康";
				    	}else if("6020".equals(evaluateType)){
				    		evaluateTypename="审美与表现";
				    	}else if("7030".equals(evaluateType)){
				    		evaluateTypename="个性发展";
				    	}else if("1020".equals(evaluateType)){
				    		evaluateTypename="新学期家长的期望";
				    	}else if("2040".equals(evaluateType)){
				    		evaluateTypename="学期结束家长评语和期望";
				    	}
					   String columnName=evaluateTypename;
					    Date date= new Date();
					    String proKey=insertid;
					   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
				   }
				 
				 
				 
				 
			}
		
		
		
		} catch (IOException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {}
			logger.error("insertApprasialforCache(HttpServletResponse)", e);
		}
		
	}
	/**
	 * 组装高中家长评价信息
	 * @return 高中家长评价信息
	 */
	private AapprasialCacheDto getAapprasialChacheDto(HttpSession session)
	{
		AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
		apprasialCacheDto.setAppraisaltypeid(evaluatetypeid);
		apprasialCacheDto.setAppraser(parentname);
		apprasialCacheDto.setApprasial(apprasial);
		apprasialCacheDto.setEdu_id(edu_id);
		apprasialCacheDto.setStudentid(studentid);
		apprasialCacheDto.setAppraiserrid(appraserid);
		
		apprasialCacheDto.setSemesterid(termid);
		apprasialCacheDto.setAppraseridentify("5");
		apprasialCacheDto.setSigndate(signDate);
		apprasialCacheDto.setCmis30id(CMIS30ID);
		apprasialCacheDto.setDiscode(DISCODE);
		return apprasialCacheDto;
	}
	/**
	 * 组装初中家长评价信息
	 * @return 初中家长评价信息
	 */
	private PartInfoCacheDto getPartInfoCache()
	{
		PartInfoCacheDto partinfo = new PartInfoCacheDto();
		partinfo.setTwo_part_id(evaluatetypeid);
		partinfo.setSigner_name(parentname);
		partinfo.setPart_info(apprasial);
		partinfo.setEdu_id(edu_id);
		partinfo.setStudentid(studentid);
		partinfo.setUserid(appraserid);
		partinfo.setTermid(termid);
		partinfo.setWrite_man(Constant.APPRASER_PARENT);
		partinfo.setCreateDate(signDate);
		partinfo.setCmis30id(CMIS30ID);
		partinfo.setDiscode(DISCODE);
		return partinfo;
	}
	public Object doUpadateAppraisalChild(HttpServletResponse response){
		if("0".equals(constantBean.get("isStartAppraiseCache"))){
			doUpdateforDB(response);
		}else if("1".equals(constantBean.get("isStartAppraiseCache"))){
			doUpdateforCache(response);
		}
		return null;
	}
	/**
	 * 更新家长评价信息，不走缓存
	 * @param response
	 */
	private void doUpdateforDB(HttpServletResponse response)
	{
		try {
			AppraisalDto appraisal=new AppraisalDto();
			appraisal.setApprasialid(appraisalid);
			appraisal.setApprasial(apprasial);
			appraisal.setSigndate(StringToDate(signDate));
			appraisal.setCmis30id(CMIS30ID==null?null:Integer.parseInt(CMIS30ID));
			appraisal.setDiscode(DISCODE==null?null:Integer.parseInt(DISCODE));
			appraisalChildExt.UpdateAppraisalChildList(appraisal,Integer.parseInt(levelcode1));
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
			    Integer  discode=Integer.valueOf(DISCODE);
			    Integer  cmis30id=Integer.valueOf(CMIS30ID);
				speekDto=czplayAndHealthExt.czfindSpeek(appraisalid,discode,cmis30id);
				  if(speekDto!=null){
					    String stuEduid =speekDto.getStuEduid();
					    String  studentName=speekDto.getStudentName(); 
					    String evaluateType=speekDto.getAppraisaltypeid();
					    if( stuEduid!=null){
						   if("46".equals(evaluateType)){
					    		evaluateTypename="学业成就";
					    	}else if("34".equals(evaluateType)){
					    		evaluateTypename="思想道德";
					    	}else if("54".equals(evaluateType)){
					    		evaluateTypename="合作与交流";
					    	}else if("64".equals(evaluateType)){
					    		evaluateTypename="运动与健康";
					    	}else if("74".equals(evaluateType)){
					    		evaluateTypename="审美与表现";
					    	}else if("94".equals(evaluateType)){
					    		evaluateTypename="个性发展";
					    	}else if("23".equals(evaluateType)){
					    		evaluateTypename="学期结束家长评语和期望";
					    	}
						   String columnName=evaluateTypename;
						   Date date=new Date();
						   String proKey=appraisalid;
						   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
                    }
		        }
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
					     Integer  integerapprasialid=Integer.valueOf(appraisalid); 
					     Integer  discode=Integer.valueOf(DISCODE);
					     Integer  cmis30id=Integer.valueOf(CMIS30ID);
					      speekDto=playAndHealthExt.findSpeek(integerapprasialid,discode,cmis30id);
					      if(speekDto!=null){
							    String stuEduid =speekDto.getStuEduid();
							    String  studentName=speekDto.getStudentName(); 
							    String evaluateType=speekDto.getAppraisaltypeid();
							   if(stuEduid!=null){
									
								   if("8040".equals(evaluateType)){
							    		evaluateTypename="学业成就";
							    	}else if("3020".equals(evaluateType)){
							    		evaluateTypename="思想道德";
							    	}else if("4020".equals(evaluateType)){
							    		evaluateTypename="合作与交流";
							    	}else if("5020".equals(evaluateType)){
							    		evaluateTypename="运动与健康";
							    	}else if("6020".equals(evaluateType)){
							    		evaluateTypename="审美与表现";
							    	}else if("7030".equals(evaluateType)){
							    		evaluateTypename="个性发展";
							    	}else if("1020".equals(evaluateType)){
							    		evaluateTypename="新学期家长的期望";
							    	}else if("2040".equals(evaluateType)){
							    		evaluateTypename="学期结束家长评语和期望";
							    	}
								   String columnName=evaluateTypename;
								    Date date=new Date();
								    String proKey=String.valueOf(integerapprasialid);
								   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
							   }
				      }
						
			}
		}catch(ManagerException e){
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {
			}
			logger.error("doUpdateforDB(HttpServletResponse)", e);
		}
	}
	/**
	 * 更新家长评价记录，走缓存
	 */
	private void doUpdateforCache(HttpServletResponse response)
	{
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("apprasial", apprasial);
			params.put("signdate", signDate);
			params.put("apprasialid", appraisalid);
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
			
				PartInfoCacheDto partinfo = new PartInfoCacheDto();
				partinfo.setPart_info(apprasial);
				partinfo.setCreateDate(signDate);
				partinfo.setPart_id(appraisalid);
				ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params,"AppraisalChildExtImpl.UpdateAppraisalChildList.update4");
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, Constant.APPRASER_PARENT, appraserid, appraisalid, partinfo, sqlDemo,partinfo.getCreateDate());
			
			
				 String stuEduid =edu_id;
				    String  studentName=name; 
				   
				    if( stuEduid!=null){
				    	String evaluateType =evaluatetypeid;
					   if("46".equals(evaluateType)){
				    		evaluateTypename="学业成就";
				    	}else if("34".equals(evaluateType)){
				    		evaluateTypename="思想道德";
				    	}else if("54".equals(evaluateType)){
				    		evaluateTypename="合作与交流";
				    	}else if("64".equals(evaluateType)){
				    		evaluateTypename="运动与健康";
				    	}else if("74".equals(evaluateType)){
				    		evaluateTypename="审美与表现";
				    	}else if("94".equals(evaluateType)){
				    		evaluateTypename="个性发展";
				    	}else if("23".equals(evaluateType)){
				    		evaluateTypename="学期结束家长评语和期望";
				    	}
					    String columnName=evaluateTypename;
					    Date date= new Date();
					    String proKey=appraisalid;
					   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
				    }
			
			
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
			
			
				AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
				apprasialCacheDto.setApprasialid(appraisalid);
				apprasialCacheDto.setApprasial(apprasial);
				apprasialCacheDto.setSigndate(signDate);
				ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params,"AppraisalChildExtImpl.UpdateAppraisalChildList.update3");
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, "5", appraserid, appraisalid, apprasialCacheDto, sqlDemo,apprasialCacheDto.getSigndate());
			      
				 String stuEduid =edu_id;
				    String  studentName=name; 
				    String evaluateType=evaluatetypeid;
				   if(stuEduid!=null){
						
					   if("8040".equals(evaluateType)){
				    		evaluateTypename="学业成就";
				    	}else if("3020".equals(evaluateType)){
				    		evaluateTypename="思想道德";
				    	}else if("4020".equals(evaluateType)){
				    		evaluateTypename="合作与交流";
				    	}else if("5020".equals(evaluateType)){
				    		evaluateTypename="运动与健康";
				    	}else if("6020".equals(evaluateType)){
				    		evaluateTypename="审美与表现";
				    	}else if("7030".equals(evaluateType)){
				    		evaluateTypename="个性发展";
				    	}else if("1020".equals(evaluateType)){
				    		evaluateTypename="新学期家长的期望";
				    	}else if("2040".equals(evaluateType)){
				    		evaluateTypename="学期结束家长评语和期望";
				    	}
					   String columnName=evaluateTypename;
					    Date date= new Date();
					    String proKey=appraisalid;
					   latestEvaluationRecordExt.setparentRecordToCache(columnName, evaluateType, proKey, studentName, stuEduid, date);
				   }
				
			
			}
		}catch(ManagerException e){
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {
			}
			logger.error("doUpdateforCache(HttpServletResponse)", e);
		}
	}
	/**
	 * 删除家长评价
	 * @param response
	 * @return
	 */
	public Object doDeleteAppraisalChild(HttpServletResponse response)
	{
		if("0".equals(constantBean.get("isStartAppraiseCache")))
		{
			doDeleteforDB(response);
		}else if("1".equals(constantBean.get("isStartAppraiseCache"))){
			doDeleteforCache(response);
		}
		return null;
	}
	/**
	 * 删除家长评价记录，不走缓存
	 * @param response
	 */
	private void doDeleteforDB(HttpServletResponse response)
	{
		try {
			appraisalChildExt.DeleteAppraisalChild(appraisalid,Integer.parseInt(levelcode1));
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
				 
			     Integer  discode=Integer.valueOf(DISCODE);
			     Integer  cmis30id=Integer.valueOf(CMIS30ID);
				 speekDto=czplayAndHealthExt.czfindSpeek(appraisalid,discode,cmis30id);
				  if(speekDto!=null){
					    String stuEduid =speekDto.getStuEduid();
					    String evaluateType=speekDto.getAppraisaltypeid();
					    if( stuEduid!=null){
						    String proKey=appraisalid;
						   latestEvaluationRecordExt.deleteparentRecordToCache(stuEduid, evaluateType, proKey);
                   }
		        }
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
					     Integer  integerapprasialid=Integer.valueOf(appraisalid); 
					     Integer  discode=Integer.valueOf(DISCODE);
					     Integer  cmis30id=Integer.valueOf(CMIS30ID);
					     
					      speekDto=playAndHealthExt.findSpeek(integerapprasialid,discode,cmis30id);
					      if(speekDto!=null){
							    String stuEduid =speekDto.getStuEduid();
							    String evaluateType=speekDto.getAppraisaltypeid();
							   if(stuEduid!=null){
								    String proKey=String.valueOf(integerapprasialid);
								   latestEvaluationRecordExt.deleteparentRecordToCache(stuEduid, evaluateType, proKey);
							   }
				      }
			}
		}catch(ManagerException e){
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {
			}
			logger.error("doDeleteAppraisalChild(HttpServletResponse)", e);
		}
	}
	/**
	 * 删除家长评价记录，走缓存
	 * @param response
	 */
	private void doDeleteforCache(HttpServletResponse response)
	{
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("apprasialid", appraisalid);
			if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_CZ) {
				ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params, "AppraisalChildExtImpl.DeleteAppraisalChild.delete2");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, Constant.APPRASER_PARENT, appraserid, appraisalid, sqlDemo,PartInfoCacheDto.class);
				
			} else if (Integer.parseInt(levelcode1) == Constant.DICT_TYPE_LEVELCODE_GZ
					|| Constant.DICT_TYPE_LEVELCODE_GZYK == Integer.parseInt(levelcode1)) {
				ISqlElement sqlDemo = appraisalChildExt.getIsqlElement(params, "AppraisalChildExtImpl.DeleteAppraisalChild.delete1");
				
				latestEvaluationRecordExt.delRecodeInCacheByProKey(edu_id, termid, evaluatetypeid, "5", appraserid, appraisalid, sqlDemo,AapprasialCacheDto.class);
			
			}
		}catch(ManagerException e){
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {
			}
			logger.error("doDeleteAppraisalChild(HttpServletResponse)", e);
		}
	}
	/**
	 * 字符串转日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return new Date();
		}
	}
	
	
	
	private Date StringToDatexie(String d) {
		try {
			/*java.util.Date valueOf = java.sql.Date.valueOf(d);
			return valueOf;*/
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = this.dateToString(sdf.parse(d));
			return java.sql.Date.valueOf(date);
			} catch (Exception e) {
			String date = this.dateToString(new Date());
			java.util.Date valueOf = java.sql.Date.valueOf(date);
			return valueOf;
		}
	}
	
	private String dateToString(Date signDate){
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		String date =simple.format(signDate);
		return date;
	}
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AppraisalDto app11 = (AppraisalDto) app1;
            	AppraisalDto app22 = (AppraisalDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
}
