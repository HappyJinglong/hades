package com.flyrish.hades.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flyrish.hades.service.ext.IRedisServiceExt;

/**
 * 配置整个项目中用到的常量
 * @author zengdeqiang
 *
 */
public class Constant {
	
	//初中栏目
	public static String SXDD_TWO_PART_ID[] = {"31","32","33","34","35"};
	public static String KCPY_TWO_PART_ID[] = {"44"};//,"42","43","44","45","46"};
	public static String HZYJL_TWO_PART_ID[] = {"51","52","53","54","55"};
	public static String YDYJK_TWO_PART_ID[] = {"61","62","63","64","65"};
	public static String SMYBX_TWO_PART_ID[] = {"71","72","73","74","75"};
	public static String GXFZ_TWO_PART_ID[] = {"91","92","93","94","95"};
	public static String TOTAL_TWO_PART_ID_CZ[] = {"11","12","21","22","23","31","32","33","34","35","41","42","43","44","45","46","51","52","53","54","55","61","62","63","64","65","71","72","73","74","75","81","82","83","84","85","91","92","93","94","95"};
	//高中栏目
	public static String SXDD_TWO_PART_ID_H[] = {"3010","3020"};
	public static String KCPY_TWO_PART_ID_H[] = {"9999"};//,"8010","8020","8030","8040"};
	public static String HZYJL_TWO_PART_ID_H[] = {"4010","4020"};
	public static String YDYJK_TWO_PART_ID_H[] = {"5010","5020"};
	public static String SMYBX_TWO_PART_ID_H[] = {"6010","6020"};
	public static String GXFZ_TWO_PART_ID_H[] = {"7030","7040","7010","7020","7050"};
	public static String TOTAL_TWO_PART_ID_GZ[] = {"9999","1010","1020","2010","2020","2030","2040","3010","3020","4010","4020","5010","5020","6010","6020","7010","7020","7030","3030","3040","4030","4040","5030","5040","5050","6030","6040","7040","7050","8010","8020","8030","9010","9020","9030","8040"};
	//总体评价栏目号
	//a_appraisal表里的相关栏目号
	public static String ALL_APPRAISER_TPYE[]={"1","2","3","5"};
	public static String ALL_STATICS_APPRAISAL_TWO_PART_ID[] ={"1010",	"1020",	"2010",	"2020",	"2040",	"3010",	"3020",	"4010",	"4020",	"5010",	"5020",	"6010",	"6020",	"7020",	"7030",	"7040",	"7050",	"8020",	"8040"};
	public static String ALL_STATICS_RECORD_TWO_PART_ID[] ={"3030","4030","6030"};
	public static String ALL_STATICS_PRACTICE_TWO_PART_ID[]={"9010","9020","9030"};
	public static ApplicationContext ctx =new ClassPathXmlApplicationContext("../applicationContext-common.xml");
	public static ConstantReBean constantBean=(ConstantReBean)ctx.getBean("constantreBean");

	public static final String CZ_SECTION_INFO = "cz_one_and_two_section_info";
	
	public static final String GZ_SECTION_INFO = "gz_two_section_info";
	//缓存需要的表名8
	public static final String Pra= "a_practiceappraisal";
	//高中附件表名
	public static final String GZ_attach = "a_attach";
	//初中附件表名
	public static final String CZ_attach = "Attachment";
	
	public static final String NGINX_SERVER=constantBean.get("nginx_server");
	/**
	 * 学期类型
	 */
	public static final int DIC_TERM_TYPE = 20142;
	/**
	 * 定义放在redis缓存中的常用功能对应key
	 */
	public static final String REDIS_COMMON_MENU = "user_common_menu";
	/**
	 * 定义放在redis缓存中的所有学期值所对应的Key
	 */
	public static final String REDIS_TERM_ALLVALUE="term_values";
	/**
	 * 定义放在redis缓存中的课改学科所对应的Key
	 */
	public static final String REDIS_SYS_SUBJECT="sys_subject";
	
	/**
	 * 当前有效学期KEY
	 */
	public static final String TYPE_CURRENT_TERMID="current_termid";
	/**
	 * 当前有效学期名称KEY
	 */
	public static final String TYPE_CURRENT_TERMNAME="current_termname";
	/**
	 * whs
	 */
	// 登录用户的信息
	public static final String KEY_LOGIN_USER = "sessionUser";
	
//	public static final String KEY_LOGININFO_USER = "sessionLogin";
	
	//用户登录存验证码的key
	public static final String KEY_LOGIN_CHECKCODE = "loginCheckCode";
	
	public static final String USER_LEVEL = "sessionUserLevel";
	
	public static final String USER_LOGIN_ID = "sessionUserId";
	
	//session里存放cmis30和discode值的Key
	public static final String CMIS30="cmis30";
	public static final String DISCODE ="discode";
	
	public static final String LOGIN_FAILED="用户认证失败！";
	
	public static final String LOGIN_FAILED_CODE="108";
	//本人
	public static final String me_apprasialidentify="本人";
	
	public static final String high_me_apprasialidentify="本人";
	
	public static final String me_apprasial = "自己";
	public static final String me = "我";
	//用户类别 市级
	public static final String USER_KIND_CITY = "1503001";
	//用户类别 区县
	public static final String USER_KIND_COUNTY = "1503002";
	
	//校级类别（学校管理员）
	public static final String USER_KIND_SCHOOLGROUP="1503004";
	//校级类别（学校教师）
	public static final String USER_KIND_SCHOOLTEACHER="1503005";
	//校级类别（学校学生）
	public static final String USER_KIND_SCHOOLSTUDENT="1503006";
	//校级类别（学校家长）
	public static final String USER_KIND_SCHOOLFAM="1503007";
	//班主任类型
	public static final Integer USER_MASTERROLE_TYPE=1502006;
	
	public static final String USER_MASTERROLE_TYPESTR="1502006";
	
	
	public static final String LEVEL_ROLE_ADMIN = "1501001";// 角色级别-市级

	public static final String LEVEL_ROLE_CONSTRY = "1501002";// 角色级别-区级

	public static final String LEVEL_ROLE_SCHOOL = "1501003";// 角色级别-校级
	
	
	public static final String USER_TYPE_SCHOOLADMIN="1502003";//教务老师
	
	public static final String USER_TYPE_CLASSMASTER="1502006";//班主任
	
	public static final String USER_TYPE_COURSEMASTER="1502007";//任课老师
	
	public static final String USER_TYPE_COURSEMASTER_SCHOOLCODE="1502032";//校本任课老师角色
	
	public static final String USER_TYPE_SPORTSEMASTER="1502005";//德育老师
	
	public static final String USER_TYPE_STUDENT="1502030"; //学生
	
	public static final String USER_TYPE_PARENT="1502031";  //家长
	
	
	public static final int USER_KIND_STUDNET = 1503006;//用户类别-学生
	
	public static final String TYPE_CAMPUSEID_ROLE_LEVELCODE="campuseidAndRoleAndLevelCode";
	
	//字典id--levelcode(学段类型-小学)
	public static final int DICT_TYPE_LEVELCODE_XX = 2012001;

	//字典id--levelcode(学段类型-初中)
	public static final int DICT_TYPE_LEVELCODE_CZ = 2012002;
	
	//字典id--levelcode(学段类型-初中)
	public static final String DICT_TYPE_LEVELCODE_CZSTR ="2012002";

	//字典id--levelcode(学段类型-高中)
	public static final int DICT_TYPE_LEVELCODE_GZ = 2012003;
	
	public static final String DICT_TYPE_LEVELCODE_GZSTR ="2012003";

	//字典id--levelcode(学段类型-高中预科)
	public static final int DICT_TYPE_LEVELCODE_GZYK = 2012004;
	
	public static final String DICT_TYPE_LEVELCODE_GZYKSTR ="2012004";
	//高中（只有）校本课程任课老师角色所对应的学段
	public static final String DICT_TYPE_LEVELCODE_GZKGTR="99999";
    
	//家长评价评价人字段
	public static final String APPRASER_PARENT="家长";
	
	public static final String HIGH_APPRASER_PARENT="5";
	
	public static final String APPRASER_STUDENT="同学";
	
	public static final String HIGH_APPRASER_STUDENT="2";
	
	public static final String APPRASER_TEACHER="教师";
	
	public static final String APPRASER_MASTER="班主任";
	
	public static final String APPRASER_TEACHER_Z="任课老师";
	
	public static final String APPRASER_STUDENT1="学生";
	
	public static final String APPRASER_DISCODE="区县";
	
	public static final String APPRASER_SCHOOL="学校";
	
	public static final String MASTER_DICT = "4";
	
	public static final String TEACHER_DICT = "3";
	
	public static final String TEACHER_DICT_CZ = "老师";
	
	public static final String MASTER_DICT_CZ = "班主任";
	
	//固定的变量，下载的文件名
	public static final String DOWNLOAD_FILENAME="上传操作说明.docx";
	//下载的文件路径
	public static final String DOWNLOAD_FILEPATH="/file/上传操作说明.docx";
	
	/**
	 * wx
	 */
	//页面跳转标示
	public static final int PAGE_XXQKSDW=1001;//新学期开始的我
	
	public static final int PAGE_XQJSSDW=1002;//学期结束时的我

	public static final int PAGE_SXDD=1003;//思想道德

	public static final int PAGE_XYCJ=1004;//学业成就

	public static final int PAGE_HZJL=1005;//合作交流

	public static final int PAGE_YDYJK=1006;//运动与健康
	
	public static final int PAGE_SMYBX=1007;//审美与表现

	public static final int PAGE_ZHSJHD=1008;//综合实践活动

	public static final int PAGE_GXFZ=1009;//个性发展
	
	public static final String TYPE_DEVELOP_TATGET="我的发展目标";
	
	public static final String TYPE_ZWPJ="自我评价";
	
	public static final String TYPE_BZRPY="班主任评语";
	
	public static final String TYPE_SXDD="思想道德";
	
	public static final String TYPE_SXDD_BAG="思想道德记录袋";
	
	public static final String TYPE_HZYJL="合作与交流";
	
	public static final String TYPE_HZYJL_BAG="合作与交流记录袋";
	
	public static final String TYPE_YDYJK="运动与健康";
	
	public static final String TYPE_YDYJK_BAG="运动与健康记录袋";
	
	public static final String TYPE_SMYBX="审美与表现";
	
	public static final String TYPE_SMYBX_BAG="审美与表现记录袋";
	
	public static final String TYPE_GXYFZ="个性发展";
	
	public static final String TYPE_PROCESS = "个性发展过程";
	
	public static final String TYPE_TCYCG_SHOW="特长与成果展示";
	
	public static final String TYPE_KCPY="课程评语";
	
	public static final String TYPE_ZHSJ="综合实践活动";
	
	public static final String TYPE_STUDY = "研究型学习";
	
	public static final String TYPE_COMMIUNITY = "社区服务";
	
	public static final String TYPE_PRACTICE = "社会实践活动";
	
	public static final String TYPE_BASE = "基本情况";
	
	public static final String TYPE_SOCIAL = "社区服务与社会实践";
	
	public static final String TYPE_XYCJ="学业成就";
	
	public static final String TYPE_XYCJ_SHOW="学科作品展示";
	
	public static final String TYPE_XXQQJZ="新学期家长的期望";
	
	public static final String TYPE_XXQJS="学期结束家长评语和期望";
	
	public static final String TYPE_QWJY="家长期望和寄语";
	
	public static final String TYPT_NEW_ME="新学期伊始的我";
	
	public static final String TYPE_END_ME="学期结束时的我";
	
	public static final String TYPE_BEGIN_EXPECT="家长的期望";
	
	public static final String TYPE_END_EXPECT="家长的评语和期望";
	
	public static final String TYPE_APPRAISAL_OTHER="他人评价";
	
	public static final String attach="附件";
	//高中综合素质type类型标示
	//新学期开始的我
	public static final String TYPE_START_ZWPJ="1010";//自我评价
	
	public static final String TYPE_START_WDFZMB="1020";//我的发展目标 
	/**
	 * 学期开始的我 本人
	 */
	public static final String TYPE_START_WDFZMB_MYSELF="1020_0";
	/**
	 * 学期开始的我 家长
	 */
	public static final String TYPE_START_WDFZMB_PRATENT="1020_1";
	
	//学期结束时的我
	public static final String TYPE_END_ZWPJ="2010";//自我评价

	public static final String TYPE_END_WDFZMB="2020";//我的发展目标
	
	public static final String TYPE_END_BZRPY="2030";//班主任评语
	
	public static final String TYPE_END_JZPYQW="2040";//家长评语及期望

	//思想道德
	public static final String TYPE_SX_ZWPJ="3010";//自我评价
	
	public static final String TYPE_SX_TRPJ="3020";//他人评价思想道德
	
	public static final String TYPE_SX_TRPJ_C="3020_1";//他人评价(同学)思想道德
	
	public static final String TYPE_SX_TRPJ_T="3020_2";//他人评价(教师)思想道德
	
	public static final String TYPE_SX_TRPJ_P="3020_3";//他人评价(家长)思想道德

	public static final String TYPE_SXJLD="3030";//思想道德事迹记录袋
	
	public static final String TYPE_SXDDGJPJ="3040";//思想道德工具评价

	
	//合作交流
	public static final String TYPE_HZ_ZWPJ="4010";//自我评价
	
	public static final String TYPE_HZ_TRPJ="4020";//他人评价
	
	public static final String TYPE_HZ_TRPJ_C="4020_1";//同学评价
	
	public static final String TYPE_HZ_TRPJ_T="4020_2";//教师评价
	
	public static final String TYPE_HZ_TRPJ_P="4020_3";//家长评价

	public static final String TYPE_HZ_JLD="4030";//合作与交流行为记录袋
	
	public static final String TYPE_HZJLGJPJ="4040";//合作与交流工具评价

	//运动与健康
	public static final String TYPE_YDJK_ZWPJ="5010";//自我评价
	
	public static final String TYPE_YDJK_TRPJ="5020";//他人评价运动与健康
	
	public static final String TYPE_YDJK_TRPJ_C="5020_1";//他人评价（同学）运动与健康
	
	public static final String TYPE_YDJK_TRPJ_T="5020_2";//他人评价（教师）运动与健康
	
	public static final String TYPE_YDJK_TRPJ_P="5020_3";//他人评价（家长）运动与健康
	
	public static final String TYPE_YDJKGJPJ="5030";//运动与健康工具评价
	
	public static final String TYPE_YDJKTZJK="5555";//运动与健康--体质健康
	
	//审美与表现
	public static final String TYPE_SMYBX_ZWPJ="6010";//自我评价
	
	public static final String TYPE_SMYBX_TRPJ="6020";//他人评价审美与表现
	
	public static final String TYPE_SMYBX_TRPJ_C="6020_1";//他人评价(同学)审美与表现
	
	public static final String TYPE_SMYBX_TRPJ_T="6020_2";//他人评价（教师）审美与表现
	
	public static final String TYPE_SMYBX_TRPJ_P="6020_3";//他人评价（家长）审美与表现

	public static final String TYPE_SMYBX_JLD="6030";//审美与表现记录袋 
	
	public static final String TYPE_SMYBXGJPJ="6040";//审美与表现工具评价

	//综合实践活动-研究性学习9010
	public static final String TYPE_YJXX_ZWPJ="1008";//自我评价

	public static final String TYPE_YJXX_XXNR="1002";//研究性学习内容
	//综合实践活动-研究性学习
	public static final String TYPE_YJXX="9010";
	//综合实践活动-社区服务
	public static final String TYPE_SQFU="9020";
	//综合实践活动-社会实践活动
	public static final String TYPE_SHSJHD="9030";
	
	//综合实践活动-研究性学习自我评价
		public static final String TYPE_YJXX_ZWPJ1="9010001";
		//综合实践活动-社区服务自我评价
		public static final String TYPE_SQFU_ZWPJ1="9020001";
		//综合实践活动-社会实践活动自我评价
		public static final String TYPE_SHSJHD_ZWPJ1="9030001";
	
	//综合实践活动-社区服务9020
	public static final String TYPE_SQFU_ZWPJ="1008";//自我评价

	public static final String TYPE_SQFU_FWNR="1002";//社区服务内容
	
	//综合实践活动-社会实践活动9030
	public static final String TYPE_SJHD_ZWPJ="1008";//自我评价

	public static final String TYPE_SJHD_JBQK="1002";//基本情况

	//个性发展
	public static final String TYPE_GXFZ_JBQK="7010";//个性发展基本情况
	
	public static final String TYPE_GXFZ_ZWPJ="7020";//自我评价
	
	public static final String TYPE_GXFZ_TRPJ="7030";//他人评价个性发展
	
	public static final String TYPE_GXFZ_TRPJ_C="7030_1";//他人评价(同学)个性发展
	
	public static final String TYPE_GXFZ_TRPJ_T="7030_2";//他人评价（教师）个性发展
	
	public static final String TYPE_GXFZ_TRPJ_P="7030_3";//他人评价（家长）个性发展
	
	public static final String TYPE_GXFZGC="7040";//个性发展过程
	
	public static final String TYPE_GXFZ_CGZS="7050";//特长与成果展示
	
	//学业成就
    public static final String TYPE_XY_ZWPJ="8020";//自我评价

    public static final String TYPE_XY_GCJL="8010";//学科学习过程记录
   
    public static final String TYPE_XY_XFRD="8030";//学分认定
	
    public static final String TYPE_XY="8040";//学业
    
    public static final String TYPE_XY_C="8040_1";//学业(同学评价)
    
    public static final String TYPE_XY_T="8040_2";//学业(教师评价)
    
    public static final String TYPE_XY_P="8040_3";//学业(家长评价)
    
    public static final String TYPE_KE_CHENG_PINGYU="9999";//课程评语
    /**
	 * 刚开学时的我(新学期伊始的我)(初中综素二级栏目名称)
	 */
	public final static String TERMS_BEGIN_ME="11";
	
	/**
	 * 我的发展目标(新学期伊始的我)(初中综素二级栏目名称)
	 */
	public final static String DEVELOP_TARGET_ME="12";
	
	/**
	 * 学期末的我(学期结束时的我)(初中综素二级栏目名称)
	 */
	public final static String TERMS_END_ME="21";
	
	/**
	 * 班主任评语(学期结束时的我)(初中综素二级栏目名称)
	 */
	public final static String CHARGE_TEACHER_APPRAISAL="22";
	
	/**
	 * 家长评语和期望(学期结束时的我)(初中综素二级栏目名称)
	 */
	public final static String PRAENTS_APPRAISAL_EXPECT="23";
	
	/**
	 * 自我评价(思想道德)(初中综素二级栏目名称)
	 */
	public final static String MORALITY_SELF_APPRAISAL="31";
	
	/**
	 * 同学评价(思想道德)(初中综素二级栏目名称)
	 */
	public final static String MORALITY_CLASSMATES_APPRAISAL="32";
	
	/**
	 * 老师评价(思想道德)(初中综素二级栏目名称)
	 */
	public final static String MORALITY_TEACHER_APPRAISAL="33";
	
	/**
	 * 家长评价(思想道德)(初中综素二级栏目名称)
	 */
	public final static String MORALITY_PARENT_APPRAISAL="34";
	
	/**
	 * 思想道德事迹记录袋(思想道德)(初中综素二级栏目名称)
	 */
	public final static String MORALITY_RECORD_BAG="35";
	
	/**
	 * 自我评价(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_SELF_APPRAISAL="41";
	
	/**
	 * 学习成绩(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_SCHOOL_RECORD="42";
	
	/**
	 * 学科作品展示(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_SUBJECT_SHOW="43";
	
	/**
	 * 课程评语(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_SUBJECT_APPRAISAL="44";
	
	/**
	 * 同学评价(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_CLASSMATES_APPRAISAL="45";
	
	/**
	 * 家长评价(学业成就)(初中综素二级栏目名称)
	 */
	public final static String WORKS_PARENT_APPRAISAL="46";
	
	/**
	 * 自我评价(合作与交流)(初中综素二级栏目名称)
	 */
	public final static String COOPERATION_SELF_APPRAISAL="51";
	
	/**
	 * 同学评价(合作与交流)(初中综素二级栏目名称)
	 */
	public final static String COOPERATION_CLASSMATES_APPRAISAL="52";
	
	/**
	 * 教师评价(合作与交流)(初中综素二级栏目名称)
	 */
	public final static String COOPERATION_TEACHER_APPRAISAL="53";
	
	/**
	 * 家长评价(合作与交流)(初中综素二级栏目名称)
	 */
	public final static String COOPERATION_PARENT_APPRAISAL="54";
	
	/**
	 * 合作与交流行为记录袋(合作与交流)(初中综素二级栏目名称)
	 */
	public final static String COOPERATION_RECORD_BAG="55";
	
	/**
	 * 自我评价(运动与健康)(初中综素二级栏目名称)
	 */
	public final static String PLAY_SELF_APPRAISAL="61";
	
	/**
	 * 同学评价(运动与健康)(初中综素二级栏目名称)
	 */
	public final static String PLAY_CLASSMATES_APPRAISAL="62";
	
	/**
	 * 教师评价(运动与健康)(初中综素二级栏目名称)
	 */
	public final static String PLAY_TEACHER_APPRAISAL="63";
	
	/**
	 * 家长评价(运动与健康)(初中综素二级栏目名称)
	 */
	public final static String PLAY_PARENT_APPRAISAL="64";
	
	/**
	 * 体质健康(运动与健康)(初中综素二级栏目名称)
	 */
	public final static String PLAY_PHYSCIAL_HEALTH="65";
	
	/**
	 * 自我评价(审美与表现)(初中综素二级栏目名称)
	 */
	public final static String AESTHETIC_SELF_APPRAISAL="71";
	
	/**
	 * 同学评价(审美与表现)(初中综素二级栏目名称)
	 */
	public final static String AESTHETIC_CLASSMATES_APPRAISAL="72";
	
	/**
	 * 教师评价(审美与表现)(初中综素二级栏目名称)
	 */
	public final static String AESTHETIC_TEACHER_APPRAISAL="73";
	
	/**
	 * 家长评价(审美与表现)(初中综素二级栏目名称)
	 */
	public final static String AESTHETIC_PARENT_APPRAISAL="74";
	
	/**
	 * 审美与表现记录袋(审美与表现)(初中综素二级栏目名称)
	 */
	public final static String AESTHETIC_RECORD_BAG="75";
	
	/**
	 * 基本情况(综合实践活动(研究性学习))(初中综素二级栏目名称)
	 */
	public final static String ACTIVITY_BASEINFO_1="81";
	
	/**
	 * 研究成果(综合实践活动(研究性学习))(初中综素二级栏目名称)
	 */
	public final static String ACTIVITY_RESEARCH_RESULT="82";
	
	/**
	 * 自我评价(综合实践活动(研究性学习))(初中综素二级栏目名称)
	 */
	public final static String ACTIVITY_SELF_APPRAISAL_1="83";
	
	/**
	 * 基本情况(综合实践活动(社区服务与社会实践))(初中综素二级栏目名称)
	 */
	public final static String ACTIVITY_BASEINFO_2="84";
	
	/**
	 * 自我评价(综合实践活动(社区服务与社会实践))(初中综素二级栏目名称)
	 */
	public final static String ACTIVITY_SELF_APPRAISAL_2="85";
	
	/**
	 * 自我评价(个性发展)(初中综素二级栏目名称)
	 */
	public final static String INDIVIDUALITY_SELF_APPRAISAL="91";
	
	/**
	 * 同学评价(个性发展)(初中综素二级栏目名称)
	 */
	public final static String INDIVIDUALITY_CLASSMATES_APPRAISAL="92";
	
	/**
	 * 教师评价(个性发展)(初中综素二级栏目名称)
	 */
	public final static String INDIVIDUALITY_TEACHER_APPRASIAL="93";
	
	/**
	 * 家长评价(个性发展)(初中综素二级栏目名称)
	 */
	public final static String INDIVIDUALITY_PARENT_APPRAISAL="94";
	
	/**
	 * 特长与成果展示(个性发展)(初中综素二级栏目名称)
	 */
	public final static String INDIVIDUALITY_RECORD_BAG="95";
	/**
	 * 查询初中科目SQLkey
	 */
	public final static String MIDDLESCHOOL_SUBJECT_KEY="SubjectServiceExtImpl.querySubjectDtoByLevelCode.querym";
	/**
	 * 查询高中科目SQLkey
	 */
	public final static String HIGHTSCHOOL_SUBJECT_KEY="SubjectServiceExtImpl.querySubjectDtoByLevelCode.queryh";
	/**
	 * 违反唯一约束条件
	 */
	public final static String MISSING_ONLY_MSG="违反唯一约束条件";
	
	public final static String  BEGIN_OF_THE_NEW_TERM="10";  //新学期伊始的我
	
	public final static String  AT_THE_END_OF_THE_TERM="20";  //学期结束时的我
	
	public final static String  IDEOLOGICAL_MORALITY="30";  //思想道德
	
	public final static String  COOPERATION_AND_EXCHANGE="40";  //合作与交流
	
	public final static String  SPORTS_AND_HEALTH="50";  //运动与健康
	
	public final static String  AESTHETIC_AND_PERFORMANCE="60";  //审美与表现
	
	public final static String  PERSONALITY_DEVELOPMENT="70";  //个性发展
	
	public final static String  ACADEMIC_ACHIEVEMENT="80";  //学业成就
	
	public final static String  COMPREHENSIVE_PRACTICAL_ACTIVITIES="90";  //综合实践活动
	
	
	//初中一级栏目号
	public final static String JUNIOR_NEWTERM="1";//新学期伊始的我
	
	public final static String JUNIOR_TERN_END="2";//学期结束时的我
	
	public final static String JUNIOR_MORALITY="3";//思想道德
	
	public final static String JUNIOR_STUDY="4";//学业成就
	
	public final static String JUNIOR_COOPERATION="5";//合作与交流
	
	public final static String JUNIOR_SPROT="6";// 运动与健康
	
	public final static String JUNIOR_AESTHETIC="7";// 审美与表现
	
	public final static String JUNIOR_PRACTICE="8";// 综合实践活动
	
	public final static String JUNIOR_DEVELOP="9";//  个性发展
	
	//课改
	
	public final static String KG_ARRANGE_12 = "1230030";//高一高二
	
	public final static String KG_ARRANGE_13 = "1230040";//高一高三
	
	public final static String KG_ARRANGE_23 = "1230050";//高二高三
	
	public final static String KG_ARRANGE_123 = "1230060";//高一高二高三
	
	public final static String KG_ARRANGE_1 = "1230001";//高一
	
	public final static String KG_ARRANGE_2 = "1230010";//高二
	
	public final static String KG_ARRANGE_3 = "1230020";//高三
	
	//课改学段
	public final static String KG_ARRANSEGMENT_1 = "1230101";//第一学段
	
	public final static String KG_ARRANSEGMENT_2 = "1230110";//第二学段
	
	public final static String KG_ARRANSEGMENT_3 = "1230120";//第三学段
	
	public final static String KG_ARRANSEGMENT_4 = "1230130";//第四学段
	
	public final static String KG_COURSE_KIND = "1230320";//校本课程
	
	public final static String KG_COURSE_BX = "1230301";//必修
	
	public final static String KG_COURSE_BX1 = "1230310";//必选
	
	public final static String KG_COURSE_XX = "1230315";//选修
	
	public final static String KG_COURSE_NEIZHI = "1230321";//内置课程模块
	
	public final static String KG_AUDIT_STATUS = "1231510";//审核状态
	
	/******学分来源*************/
	public final static String KG_CREDIT_SOURCE_EXEMPTION="1231710";//免修
	
	public final static String KG_CREDIT_SOURCE_STUDY="1231701";  //学习
	
	public final static String KG_CREDIT_SOURCE_EXPORT="1231720";  //导入
	
	public final static String R_REDIS_OUSER="_ouser";//用户表放入redis缓存里面的key
	
	public final static String R_REDIS_LOGINUSER="login_user";//登录用户放入缓存的Key
	//远程学生身份标识号
	public final static String S_STUDENT_ROLETYPE="533d73c1e2eb4becacbf52861d43d19d";
	//远程教师身份标识号
	public final static String T_THEACHER_ROLETYPE="8882db75395c4fc0bf3eb1acf92defa0";
	
	public final static String R_READINFORM_KEY="inform";
	/**
	 * 通用的最新评价字符串
	 */
	public final static String R_RECORD_MSG="{0}在{1}栏目对{2}进行了评价_{3}_{4}@{5}";
	/**
	 * 班主任最新评价记录容器，班主任标记
	 */
	public final static String M_MASTER_FLAG="zm{0}";
	/**
	 * 任课老师最新评价记录容器，任课老师标记
	 */
	public final static String T_TEACHER_FLAG="zt{0}";
	/**
	 * 学生最新评价记录容器，学生标记
	 */
	public final static String S_STUDENT_FLAG="zs{0}";
	/**
	 * 缓存评价类所有的key所对应的总key
	 */
	public final static String R_REDIS_CACHE_TOTALKEY="totalAppraiseKey";
	/**
	 * 放置附件类所有的key所对应的总key及附件类每一个key的前缀
	 */
	public final static String R_REDIS_CACHE_ATTACHFILE="totalAttachFileKey";
	/**
	 * 放置附表类所有的key所对应的总key及附表类每一个key的前缀
	 */
	public final static String R_REDIS_CACHE_CHILDRENKEY="totalChildrenObjectKey";
	/**
	 * 放置登录所用的key
	 */
	public final static String R_REDIS_CACHE_LOGINKEY="loginKey";
	/**
	 * value：评价记录Map<key={new,old},value={评价记录}>
	 */
	public final static String R_REDIS_OLD_CACHEKEY="old";
	/**
	 * value：评价记录Map<key={new,old},value={评价记录}>
	 */
	public final static String R_REDIS_NEW_CACHEKEY="new";
	
	public final static String S_SQL_QUEUE="sqlDemo";
	
	public final static String S_SQL_QUEUE_ATTACHFILE="sqlDemoAttachfile";
	
	public final static String S_SQL_QUEUE_CHILDREN="sqlDemoChildren";
	
	public final static String R_READINFORM_KEYS="informs";
	
	public final static String GZ_XIAOBENKECHENG_TEACHER="1502032";
	/**
	 * a_apprasial 分类标识号
	 */
	public final static String GZ_APPRAISE_TOW_NUM="1010,1020,2010,2020,2030,2040,3010,3020,4010,4020,5010,5020,6010,6020,7020,7030,8020,8040,7050,7040";
	/**
	 * 记录袋 分类标识
	 */
	public final static String GZ_A_RECORDPACKAG="3030,4030,6030";
	/**
	 * a_practices 分类标识
	 */
	public final static String GZ_A_PRACITES="9010,9020,9030";
	/**
	 * a_personality 分类标识
	 */
	public final static String GZ_a_personality="7010";
	/**
	 * a_learnprocess_works 分类标识
	 */
	public final static String GZ_a_learnprocess_works="8010";
	/**
	 * 连接统一认证接口失败，错误信息
	 */
	public final static String SERVER_RENZHEN_ERROR="服务连接错误！";
	/**
	 * 连接统一认证接口失败，错误状态码
	 */
	public final static String SERVER_RENZHEN_ERROR_STATUS="107";
	
}