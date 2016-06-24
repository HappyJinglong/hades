package com.flyrish.hades.webapp.action.total;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class TotalDatasNumAction extends BaseAction {
	
	public String levelCode;
	
	public String termId;
	
	public String termName;
	
	public String schoolName;
	
	public String gradeid;
	
	public String discode;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	@Spring
	public ITermServiceExt termServiceExt;
	
	public List<TermDto> termDtos;
	/**
	 * 数据容器：Map<{学校名称},Map<{二级栏目号},Map<类别，统计的数量>>>
	 * 
	 * 二级栏目号<=>levelNum
	 * 届别<=>graduateYear=>Map<graduateYear,届别>
	 * 学期<=>termName=>Map<termName,学期名称>
	 * 学生总人数<=>totalStudentNum
	 * 已完成学生数<=>overStudentNum
	 * 已评价条目数<=>overAppriseNum
	 * 附件数量<=>attchFileNum
	 * 完成百分比<=>percentOverStudentNum
	 * 
	 */
	public Map<String,Map<String,Map<String,String>>> datas=new TreeMap<String,Map<String,Map<String,String>>>();
	
	/**
	 * 统计数据容器
	 */
	public Map<String,Map<String,String>> tongjiDatas=new HashMap<String,Map<String,String>>();
	@DefaultAction
	public Object defalutAction(HttpServletRequest req){
		queryBaseData(req);
		return queryWhichOnePage();
	}
	private void queryBaseData(HttpServletRequest req) {
		getLoginInfo(req);
		levelCode=userDto.getLevelcode();
	}
	private Object queryWhichOnePage() {
		//获取届别
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			return "hightotal.jsp";
		}else{
			return "middletotal.jsp";
		}
	}
	public Object queryData(HttpServletRequest req){
		queryBaseData(req);
		datas=studentAppDetailExt.queryMapDataByConditionInDiscode1(termId,gradeid,userDto.getDiscode(),levelCode,schoolName,tongjiDatas);
		termDtos=queryTermDtoByGradeYear(gradeid,req);
		return queryWhichOnePage();
	}
	public List<TermDto> queryTermDtoByGradeYear(String gradeyear,HttpServletRequest req){
		if(NestUtil.isEmpty(gradeyear))return null;
		//获取登录用户信息
		userDto=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		String levelcode=userDto.getLevelcode();
		Integer levelLength=3;
		if("2012002".equals(levelcode)){
			levelLength=4;
		}
		Integer gradeYearInt=Integer.parseInt(gradeyear)-levelLength;
		List<TermDto>terms=termServiceExt.queryHighSchoolTerms(gradeYearInt);
		if(terms==null||terms.isEmpty())return null;
		return terms;
	}
}
