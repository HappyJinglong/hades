/**
 * 
 */
package com.flyrish.hades.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 丁英俊
 *
 */
public class RstudentDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -326118610408130169L;

	public RstudentDto(){}
	
	public RstudentDto(String pid)
	{
		this.pid = pid;
	}
	/**
	 * 标识号。
	 */
	private String pid;
	
	private String eduId;
	/**
	 * 错误信息
	 */
	private String erroMeg;
	/**
	 * 姓名。
	 */
	private String name;
	
	/**
	 * 学籍号
	 */
	private String studentno;
	
	/**
	 * 学号
	 */
	private String inschoolid;
	
	/**
	 * 学段年级班级
	 */
	private String levelGradeClass;
	
	/**
	 * 照片路径
	 */
	private String photoPath;
	
	
	/**
	 * 班级序号.
	 */
	private String classno;
	
	/**
	 * 班级主键.
	 */
	private String classid;
	
	/**
	 * 毕业届别.
	 */
	private String graduateyear;
	
	/**
	 * 选择学年年度.
	 */
	private String selectedyear;
	
	private SelfDto selfDto = new SelfDto();
	
	private List<SelfDto> selfDtoList = new ArrayList<SelfDto>();
	
	private List<SelfDto>selfAppraisalDtoList = new ArrayList<SelfDto>();
	
	/**
	 * 性别ID
	 * @return
	 */
	private Integer sex;
	
	/**
	 * 性别
	 * @return
	 */
	private String sexname;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 学生在学状态
	 */
	private int state;
	
	/**
	 * 政治面貌
	 */
	private String easyName;
	/**
	 * 班级编号
	 */
	private Integer serial;
	
	//因为学生报告单需要批量打印，而帆软的批量打印是基于js的，所以要把哪个年级显示哪次考试带到学生界面上
	//因为批量打印的url需要控制长度，所以就这样命名变量了
	public Integer one = 1;//显示高一年级学期，1：显示上学期的体检数据；2：显示下学期的体检数据
	
	public Integer two = 1;//显示高二年级学期，1：显示上学期的体检数据；2：显示下学期的体检数据
	
	public Integer three = 1;//显示高三年级学期，1：显示上学期的体检数据；2：显示下学期的体检数据
	
	public Integer key = 0;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}

	public String getLevelGradeClass() {
		return levelGradeClass;
	}

	public void setLevelGradeClass(String levelGradeClass) {
		this.levelGradeClass = levelGradeClass;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getGraduateyear() {
		return graduateyear;
	}

	public void setGraduateyear(String graduateyear) {
		this.graduateyear = graduateyear;
	}

	public String getSelectedyear() {
		return selectedyear;
	}

	public void setSelectedyear(String selectedyear) {
		this.selectedyear = selectedyear;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getInschoolid() {
		return inschoolid;
	}

	public void setInschoolid(String inschoolid) {
		this.inschoolid = inschoolid;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getErroMeg() {
		return erroMeg;
	}

	public void setErroMeg(String erroMeg) {
		this.erroMeg = erroMeg;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEasyName() {
		return easyName;
	}

	public void setEasyName(String easyName) {
		this.easyName = easyName;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Integer getOne() {
		return one;
	}

	public void setOne(Integer one) {
		this.one = one;
	}

	public Integer getTwo() {
		return two;
	}

	public void setTwo(Integer two) {
		this.two = two;
	}

	public Integer getThree() {
		return three;
	}

	public void setThree(Integer three) {
		this.three = three;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getEduId() {
		return eduId;
	}

	public void setEduId(String eduId) {
		this.eduId = eduId;
	}

	public SelfDto getSelfDto() {
		return selfDto;
	}

	public void setSelfDto(SelfDto selfDto) {
		this.selfDto = selfDto;
	}

	public List<SelfDto> getSelfDtoList() {
		return selfDtoList;
	}

	public void setSelfDtoList(List<SelfDto> selfDtoList) {
		this.selfDtoList = selfDtoList;
	}

	public List<SelfDto> getSelfAppraisalDtoList() {
		return selfAppraisalDtoList;
	}

	public void setSelfAppraisalDtoList(List<SelfDto> selfAppraisalDtoList) {
		this.selfAppraisalDtoList = selfAppraisalDtoList;
	}
}

