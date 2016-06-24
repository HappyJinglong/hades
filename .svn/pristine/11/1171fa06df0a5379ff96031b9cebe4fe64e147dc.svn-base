package com.flyrish.hades.webapp.action.viewAppraise;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.service.ext.ILearnprocessStaticsExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.service.ext.IViewAppraiseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class ViewAppraiseAction extends BaseAction {
	@Spring
	public IViewAppraiseExt viewAppraiseExt;

	@Spring
	public ITermServiceExt termServiceExt;

	@Spring
	public IOperationAppraiseServiceExt operationAppraiseServiceExt;

	@Spring
	private IMasterAppriseExt masterAppriseExt;

	@Spring
	private ILearnprocessStaticsExt learnprocessStaticsExt;

	@Spring
	private IAppraisalWritedStaticsExt appraisalWritedStaticsExt;

	@Spring
	public AppriseMasterAppriseExt appriseMasterAppriseExt;

	public Map<String, Map<String, List<AppraiseBaseDto>>> appraiseMaps1 = new LinkedHashMap<String, Map<String, List<AppraiseBaseDto>>>(
			0);

	public String cmis30id;

	public String graduateyear;

	public String levelid;

	public String currentTermId;

	public String cmis30Id;

	public String classid;

	public long index;

	public String termid;

	public String studentName;

	public long total;

	public String levelCode;

	public String discode;

	public String gradeid;

	public List<String> queryQXInfos;

	public String firstItem;

	public String secondItem;

	public Map<String, List<AppraiseBaseDto>> appraiseMaps = new LinkedHashMap<String, List<AppraiseBaseDto>>(
			0);

	@Before
	public void initDatas(HttpServletRequest req) {
		this.getLoginInfo(req);
		discode = NestUtil.isEmpty(discode) ? userDto.getDiscode() : discode;
		levelCode = userDto.getLevelcode();
		levelid = userDto.getLevelid();
		currentTermId = userDto.getTermId();
		cmis30Id = userDto.getCmis30id();
	}

	@DefaultAction
	public Object toViewAppraisePage() {
		// 市区级用户
		if (Constant.USER_KIND_CITY.equals(userDto.getUsertype())) {// 市级用户
			queryQXInfos = appraisalWritedStaticsExt.queryQXInfos(levelCode);
		}
		discode = NestUtil.isEmpty(discode) ? userDto.getDiscode() : discode;
		return "viewAppraise.jsp";
	}

	/**
	 * 条件查询 查询单个学校
	 * */
	public void querySchool(HttpServletRequest req, HttpServletResponse resp) {
		List<SchoolreportDto> list = viewAppraiseExt.querySchool(discode,
				levelCode);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		String string = jsonArray.toString();
		out.write(string);
		out.flush();
		out.close();
	}

	public Object toPersonPage(HttpServletRequest request,
			HttpServletRequest req) {
		StudentDto student = viewAppraiseExt.getStudentByPager(classid,
				cmis30id, discode, graduateyear, termid, levelCode, index);
		total = viewAppraiseExt.getStudentTotal(classid, cmis30id, discode,
				graduateyear, termid, levelCode);
		if (student == null) {
			return "empty.jsp";
		}
		studentName = student.getName();

		// （高中）如果对应的学期标识号为空，则查询出对应的学期标识号(11,12,21,22,31,32)
		if (NestUtil.isNotEmpty(levelCode)
				&& ((Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer
						.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))) {
			Integer currentYear = Integer.parseInt(new SimpleDateFormat("yyyy")
					.format(new Date()));
			termid = String
					.valueOf((4 - currentYear + Integer.parseInt(termid) / 10)
							* 10 + 2 - Integer.parseInt(termid) % 2);
		} else if (NestUtil.isNotEmpty(levelCode)
				&& NestUtil.isEmpty(termid)
				&& ((Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_CZ))) {
			termid = userDto.getTermId();
		}

		// 获取数据集合
		List<AppraiseBaseDto> appraiseBaseDtos = operationAppraiseServiceExt
				.queryAppraiseBaseDtoByCondition(student.getName(), student
						.getEduid(), termid, student.getLevelcode().toString(),
						cmis30id, discode);
		// 组装数据
		if (student.getLevelcode() == (Constant.DICT_TYPE_LEVELCODE_CZ)) {
			if (appraiseBaseDtos != null && appraiseBaseDtos.size() > 0)
				for (AppraiseBaseDto dto : appraiseBaseDtos) {
					if (dto == null || NestUtil.isEmpty(dto.getTwoPartId()))
						continue;
					// 组装数据集合
					List<AppraiseBaseDto> dtos = appraiseMaps.get(dto
							.getTwoPartId());
					if (dtos == null) {
						dtos = new ArrayList<AppraiseBaseDto>();
						appraiseMaps.put(dto.getTwoPartId(), dtos);
					}
					dtos.add(dto);
				}
			return "byPerson-cz.jsp";
		} else {
			if (appraiseBaseDtos != null && appraiseBaseDtos.size() > 0)
				for (AppraiseBaseDto dto : appraiseBaseDtos) {
					if (dto == null
							|| NestUtil.isEmpty(dto.getAppraisaltypeid()))
						continue;
					// 组装数据集合
					List<AppraiseBaseDto> dtos = appraiseMaps.get(dto
							.getAppraisaltypeid());
					if (dtos == null) {
						dtos = new ArrayList<AppraiseBaseDto>();
						appraiseMaps.put(dto.getAppraisaltypeid(), dtos);
					}
					dtos.add(dto);
				}
		}
		return "byPerson-gz.jsp";
	}

	public void getClassList(HttpServletRequest req, HttpServletResponse resp) {
		List<ClassDto> list = viewAppraiseExt.getClassList(cmis30id,
				graduateyear, discode, levelCode);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		String string = jsonArray.toString();
		out.write(string);
		out.flush();
		out.close();
	}

	public void getTermByClassId(HttpServletRequest req,
			HttpServletResponse resp) {
		List<TermDto> list = termServiceExt.queryHighSchoolTerms(classid,
				levelCode);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		String string = jsonArray.toString();
		out.write(string);
		out.flush();
		out.close();
	}

	// 根据一级栏目查询
	public Object toItemPage(HttpServletRequest request) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lid", classid);
		params.put("cmis30Id", cmis30id);
		params.put("discode", discode);
		List<SchoolTreeDto> stuIfos = masterAppriseExt.getStudentInfo(params);

		if (null != stuIfos) {
			List<String> eduIds = new ArrayList<String>();
			List<String> studentNames = new ArrayList<String>();
			for (SchoolTreeDto slt : stuIfos) {
				eduIds.add(slt.getEdusyId());
				studentNames.add(slt.getText());
			}
			if (eduIds.size() > 0) {
				// （高中）如果对应的学期标识号为空，则查询出对应的学期标识号(11,12,21,22,31,32)
				if (NestUtil.isNotEmpty(levelCode)
						&& ((Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer
								.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))) {
					Integer currentYear = Integer
							.parseInt(new SimpleDateFormat("yyyy")
									.format(new Date()));
					termid = String.valueOf((4 - currentYear + Integer
							.parseInt(termid) / 10)
							* 10
							+ 2
							- Integer.parseInt(termid) % 2);
				}
				// 获取数据集合
				String isStartAppraiseCache = constantBean
						.get("isStartAppraiseCache");
				List<AppraiseBaseDto> appraiseBaseDtos = new ArrayList<AppraiseBaseDto>();
				if ("1".equals(isStartAppraiseCache)) {
					appraiseBaseDtos = appriseMasterAppriseExt
							.queryAppraiseBaseDtoByConditionFromCache(
									studentNames, firstItem, eduIds, termid,
									levelCode, cmis30id, discode);
				} else {
					appraiseBaseDtos = appriseMasterAppriseExt
							.queryAppraiseBaseDtoByCondition(firstItem, eduIds,
									termid, levelCode, cmis30id, discode);
				}
				// 组装数据
				if (Integer.parseInt(levelCode) == (Constant.DICT_TYPE_LEVELCODE_CZ)) {
					for (SchoolTreeDto st : stuIfos) {
						appraiseMaps = new HashMap<String, List<AppraiseBaseDto>>(
								0);
						if (!(appraiseBaseDtos == null || appraiseBaseDtos
								.size() == 0)) {
							for (AppraiseBaseDto dto : appraiseBaseDtos) {
								if (st.getEdusyId().equals(dto.getEdu_id())) {
									if (dto == null
											|| NestUtil.isEmpty(dto
													.getTwoPartId()))
										continue;
									// 组装数据集合
									List<AppraiseBaseDto> dtos = null;
									dtos = appraiseMaps.get(dto.getTwoPartId());
									if (dtos == null) {
										dtos = new ArrayList<AppraiseBaseDto>();
										appraiseMaps.put(dto.getTwoPartId(),
												dtos);
									}
									dtos.add(dto);
								}
							}
						}
						appraiseMaps1.put(st.getText() + "_" + st.getEdusyId(),
								appraiseMaps);
					}
				} else {
					for (SchoolTreeDto st : stuIfos) {
						appraiseMaps = new HashMap<String, List<AppraiseBaseDto>>(
								0);
						if (!(appraiseBaseDtos == null || appraiseBaseDtos
								.size() == 0)) {
							for (AppraiseBaseDto dto : appraiseBaseDtos) {
								if (st.getEdusyId().equals(dto.getEdu_id())) {
									if (dto == null
											|| NestUtil.isEmpty(dto
													.getAppraisaltypeid()))
										continue;
									// 组装数据集合
									List<AppraiseBaseDto> dtos = null;
									dtos = appraiseMaps.get(dto
											.getAppraisaltypeid());
									if (dtos == null) {
										dtos = new ArrayList<AppraiseBaseDto>();
										appraiseMaps.put(
												dto.getAppraisaltypeid(), dtos);
									}
									dtos.add(dto);
								}
							}
						}
						appraiseMaps1.put(st.getText() + "_" + st.getEdusyId(),
								appraiseMaps);
					}
				}
			}
		}
		if (Integer.parseInt(levelCode) == (Constant.DICT_TYPE_LEVELCODE_CZ)) {
			return "byFirstItem-cz.jsp";
		}
		return "byFirstItem-gz.jsp";
	}

	// 根据一级栏目查询
	public Object toSecondItemPage(HttpServletRequest request) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lid", classid);
		params.put("cmis30Id", cmis30id);
		params.put("discode", discode);
		List<SchoolTreeDto> stuIfos = masterAppriseExt.getStudentInfo(params);

		if (null != stuIfos) {
			List<String> eduIds = new ArrayList<String>();
			List<String> studentNames = new ArrayList<String>();
			for (SchoolTreeDto slt : stuIfos) {
				eduIds.add(slt.getEdusyId());
				studentNames.add(slt.getText());
			}
			if (eduIds.size() > 0) {
				// （高中）如果对应的学期标识号为空，则查询出对应的学期标识号(11,12,21,22,31,32)
				if (NestUtil.isNotEmpty(levelCode)
						&& ((Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer
								.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))) {
					Integer currentYear = Integer
							.parseInt(new SimpleDateFormat("yyyy")
									.format(new Date()));
					termid = String.valueOf((4 - currentYear + Integer
							.parseInt(termid) / 10)
							* 10
							+ 2
							- Integer.parseInt(termid) % 2);
				}
				// 获取数据集合
				List<AppraiseBaseDto> appraiseBaseDtos = appriseMasterAppriseExt
						.queryAppraiseBaseDtoByCondition(firstItem, secondItem,
								eduIds, termid, levelCode, cmis30id, discode);
				// 组装数据
				if (Integer.parseInt(levelCode) == (Constant.DICT_TYPE_LEVELCODE_CZ)) {
					for (SchoolTreeDto st : stuIfos) {
						appraiseMaps = new HashMap<String, List<AppraiseBaseDto>>(
								0);
						if (!(appraiseBaseDtos == null || appraiseBaseDtos
								.size() == 0)) {
							for (AppraiseBaseDto dto : appraiseBaseDtos) {
								if (st.getEdusyId().equals(dto.getEdu_id())) {
									if (dto == null
											|| NestUtil.isEmpty(dto
													.getTwoPartId()))
										continue;
									// 组装数据集合
									List<AppraiseBaseDto> dtos = null;
									dtos = appraiseMaps.get(dto.getTwoPartId());
									if (dtos == null) {
										dtos = new ArrayList<AppraiseBaseDto>();
										appraiseMaps.put(dto.getTwoPartId(),
												dtos);
									}
									dtos.add(dto);
								}
							}
						}
						appraiseMaps1.put(st.getText() + "_" + st.getEdusyId(),
								appraiseMaps);
					}
				} else {
					for (SchoolTreeDto st : stuIfos) {
						appraiseMaps = new HashMap<String, List<AppraiseBaseDto>>(
								0);
						if (!(appraiseBaseDtos == null || appraiseBaseDtos
								.size() == 0)) {
							for (AppraiseBaseDto dto : appraiseBaseDtos) {
								if (st.getEdusyId().equals(dto.getEdu_id())) {
									if (dto == null
											|| NestUtil.isEmpty(dto
													.getAppraisaltypeid()))
										continue;
									// 组装数据集合
									List<AppraiseBaseDto> dtos = null;
									dtos = appraiseMaps.get(dto
											.getAppraisaltypeid());
									if (dtos == null) {
										dtos = new ArrayList<AppraiseBaseDto>();
										appraiseMaps.put(
												dto.getAppraisaltypeid(), dtos);
									}
									dtos.add(dto);
								}
							}
						}
						appraiseMaps1.put(st.getText() + "_" + st.getEdusyId(),
								appraiseMaps);
					}
				}
			}
		}
		if (Integer.parseInt(levelCode) == (Constant.DICT_TYPE_LEVELCODE_CZ)) {
			return "bySecondItem-cz.jsp";
		}
		return "bySecondItem-gz.jsp";
	}
}
