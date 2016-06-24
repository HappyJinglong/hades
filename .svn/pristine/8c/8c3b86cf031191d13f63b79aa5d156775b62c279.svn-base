package com.flyrish.hades.util;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.nestframework.action.FileItem;
import org.nestframework.commons.utils.DateUtil;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.exception.ManagerException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class NoServiceUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NoServiceUtil.class);

	/**
	 * 获取本地IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException ex) {
					ex.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static void copyFile(File resourceFile, File resourceNewFile)
			throws Exception {
		try {
			// 使用FileInputStream打开一个文件输入流
			FileInputStream fis = new FileInputStream(resourceFile);
			// 使用FileOutputStream打开一个文件输出流
			FileOutputStream fos = new FileOutputStream(resourceNewFile);
			// 得到文件输入流的通道
			FileChannel ifc = fis.getChannel();
			// 得到文件输出流的通道
			FileChannel ofc = fos.getChannel();
			// 分配一个字节缓冲区，大小为1024
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) {
				// 清空缓冲区，使其处于可接受字节状态
				buffer.clear();
				// 从文件输入流通道里读取数据，大小取决于缓冲区大小，以及文件剩余字节大小
				int i = ifc.read(buffer);
				// 如果返回值为-1表示已读取完毕
				if (i == -1) {
					break;
				}
				// 反转缓冲区，使其处于可写入通道状态
				buffer.flip();
				// 把缓冲区数据写入文件输出流通道
				ofc.write(buffer);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			throw e;
		}

	}

	public static String trim(String s) {
		if (StringUtil.isNotEmpty(s)) {
			s = s.trim();
		} else {
			s = "";
		}
		return s;
	}

	/**
	 * 将一个字符串转化成对应的日期时间格式.
	 * 
	 * @param dt字符串表示的日期.
	 * @return date.
	 */
	public static Date StringToDateTime(String dt) {

		Date date = null;

		if (dt == null || dt.equals("") || dt.equals("null")) {
			return date;
		}

		if (dt != null && dt.length() > 0 && dt.length() < 11) {
			dt = dt.trim() + " 00:00:00";
		}
		DateFormat formatter = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.MEDIUM, new Locale("zh", "CN"));

		try {
			date = formatter.parse(dt);

		} catch (java.text.ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 修正日期的时分秒为"23:59:59"
	 * 
	 * @param date
	 *            要修正的日期.
	 * @return Date.
	 */
	public static Date correctDate(Date date) {
		Date ret = date;
		try {
			String dateStr = DateUtil.dateToStr(date, 4);
			ret = stringToDateTime(dateStr + " 23:59:59");
		} catch (Exception ex) {
			logger.error("correctDate(Date)",ex);
		}
		return ret;
	}

	/**
	 * 将一个字符串转化成对应的日期时间格式.
	 * 
	 * @param dt
	 *            字符串表示的日期.
	 * @return date.
	 * 
	 */
	public static Date stringToDateTime(String dt) {

		Date date = null;

		if (dt == null || dt.equals("") || dt.equals("null")) {
			return date;
		}

		if (dt != null && dt.length() > 0 && dt.length() < 11) {
			dt = dt.trim() + " 00:00:00";
		}
		DateFormat formatter = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.MEDIUM, new Locale("zh", "CN"));

		try {
			date = formatter.parse(dt);

		} catch (java.text.ParseException ex) {
			date = null;
			logger.error("stringToDateTime(String)",ex);
		}
		return date;
	}

	/**
	 * 一次md5加密
	 * 
	 * @param primalPass
	 *            原始密码.
	 * @return String.
	 */
	public static String md5(String primalPass) {
		String ret = primalPass;
		try {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			ret = md5.encodePassword(primalPass, null);
		} catch (Exception ex) {
			logger.error("md5(String)",ex);
		}
		return ret;
	}

	/**
	 * 3次md5加密
	 * 
	 * @param primalPass
	 *            原始密码.
	 * @return String.
	 */
	public static String md53(String primalPass) {

		String ret = primalPass;
		try {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			ret = md5.encodePassword(primalPass, null);
			ret = md5.encodePassword(ret, null);
			ret = md5.encodePassword(ret, null);
		} catch (Exception ex) {
			logger.error("md53(String)",ex);
		}
		return ret;
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * 加密
	 * 
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static String password = "12345678";

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("gbk");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			logger.error("md53(String)",e);
		} catch (NoSuchPaddingException e) {
			logger.error("md53(String)",e);
		} catch (InvalidKeyException e) {
			logger.error("md53(String)",e);
		} catch (UnsupportedEncodingException e) {
			logger.error("md53(String)",e);
		} catch (IllegalBlockSizeException e) {
			logger.error("md53(String)",e);
		} catch (BadPaddingException e) {
			logger.error("md53(String)",e);
		}
		return null;
	}

	public static byte[] decrypt(byte[] content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			logger.error("decrypt(byte[])",e);
		} catch (NoSuchPaddingException e) {
			logger.error("decrypt(byte[])",e);
		} catch (InvalidKeyException e) {
			logger.error("decrypt(byte[])",e);
		} catch (IllegalBlockSizeException e) {
			logger.error("decrypt(byte[])",e);
		} catch (BadPaddingException e) {
			logger.error("decrypt(byte[])",e);
		}
		return null;
	}

	// 检测两个日期之间的间隔是多少天
	public static float intervalBetweenTwoDate(Date date1, Date date2) {
		float ret = 0;
		try {
			Calendar cal = Calendar.getInstance();

			cal.setTime(date1);
			long millisOfdate1 = cal.getTimeInMillis();

			cal.setTime(date2);
			long millisOfDate2 = cal.getTimeInMillis();

			float intervalOfTime = new Float(millisOfDate2 - millisOfdate1);
			float baseTime = new Float(3600 * 1000 * 24);

			ret = intervalOfTime / baseTime;

		} catch (Exception ex) {
			logger.error("intervalBetweenTwoDate(Date, Date)",ex);
		}
		return ret;
	}

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	// 检测两个日期之间的间隔是多少分钟
	public static float minuteBetweenTwoDate(Date date1, Date date2) {
		float ret = 0;
		try {
			Calendar cal = Calendar.getInstance();

			cal.setTime(date1);
			long millisOfdate1 = cal.getTimeInMillis();

			cal.setTime(date2);
			long millisOfDate2 = cal.getTimeInMillis();

			float intervalOfTime = new Float(millisOfDate2 - millisOfdate1);
			float baseTime = new Float(1000);

			ret = intervalOfTime / baseTime;

			ret = Float.parseFloat(div(ret + "", "60", 0));
		} catch (Exception ex) {
			logger.error("minuteBetweenTwoDate(Date, Date)",ex);
		}
		return ret;
	}

	// 根据出生日期得到年龄
	public static int getAge(Date birthday, int examYear) {
		if (birthday == null || birthday.equals("") || birthday.equals("null")) {
			return 0;
		}
		Calendar birthDayCal = Calendar.getInstance();
		birthDayCal.setTime(birthday);
		// 出生日期和9月1比较
		// 月*100+日 =>9月1日=901
		int date = (birthDayCal.get(Calendar.MONTH) + 1) * 100
				+ birthDayCal.get(Calendar.DAY_OF_MONTH);
		// 考试年度-出生年份，再将9-01与出生日期对比，如果出生日期大于9.1则，年龄=考试年度-出生年份，否则=考试年度-出生年份-1
		if ((9 * 100 + 1) <= date) {
			return examYear - birthDayCal.get(Calendar.YEAR) - 1;
		} else {
			return examYear - birthDayCal.get(Calendar.YEAR);
		}
	}

	// 从给定日期中取年
	public static int getYearForDate(Date examdate) {
		int ret = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(examdate);
			return cal.get(Calendar.YEAR);
		} catch (Exception ex) {
			logger.error("getYearForDate(Date)",ex);
		}
		return ret;
	}

	// 从给定日期中取月份
	public static int getMonthForDate(Date examdate) {
		int ret = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(examdate);
			return cal.get(Calendar.MONTH) + 1;
		} catch (Exception ex) {
			logger.error("getMonthForDate(Date)",ex);
		}
		return ret;
	}

	// 从给定日期中取出天
	public static int getDayForDate(Date examdate) {
		int ret = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(examdate);
			return cal.get(Calendar.DAY_OF_MONTH);
		} catch (Exception ex) {
			logger.error("getDayForDate(Date)",ex);
		}
		return ret;
	}

	// 当前时间（now）的几年前的时间
	public static Date dateBeforeYears(Date now, int years) {
		Date ret = now;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			cal.add(Calendar.YEAR, -years);

			ret = cal.getTime();

		} catch (Exception ex) {
			logger.error("dateBeforeYears(Date,int)",ex);
		}
		return ret;
	}

	// 当前时间（now）的几年后的时间
	public static Date dateAfterYears(Date now, int years) {
		Date ret = now;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			cal.add(Calendar.YEAR, +years);

			ret = cal.getTime();

		} catch (Exception ex) {
			logger.error("dateAfterYears(Date,int)",ex);
		}
		return ret;
	}

	// 当前时间（now）的几天前的时间
	public static Date dateBeforeDates(Date now, int dates) {
		Date ret = now;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			cal.add(Calendar.DATE, -dates);

			ret = cal.getTime();

		} catch (Exception ex) {
			logger.error("dateBeforeDates(Date,int)",ex);
		}
		return ret;
	}

	/**
	 * 将由逗号分割的功能号转换成List集合.
	 * 
	 * @param funcs
	 *            角色对应的功能集合.
	 * @return
	 */
	public static List<String> convertToList(String funcs) {
		List<String> ret = new ArrayList<String>();
		if (StringUtil.isNotEmpty(funcs)) {
			try {
				String[] temp = funcs.split(",");
				for (String funcId : temp) {
					if (StringUtil.isNotEmpty(funcId)) {
						ret.add(funcId.trim());
					}
				}
			} catch (Exception ex) {
				logger.error("convertToList(String)",ex);
			}
		}
		return ret;
	}

	/**
	 * 相对路径转绝对路径
	 * 
	 * @param fileRelativePath
	 *            相对于classes目录的文件路径(含文件全名)
	 * @return String.
	 * @throws IOException
	 */
	public static String getAbsolutePath(String fileRelativePath)
			throws IOException {
		String ret = "";
		ClassPathResource classPathResource = new ClassPathResource(
				fileRelativePath);
		try {
			ret = classPathResource.getURL().getPath();
		} catch (IOException ioe) {
			throw ioe;
		}

		return ret;
	}

	/**
	 * 相对路径转绝对路径
	 * 
	 * @param sc
	 *            ServletContext.
	 * @param fileRelativePath
	 *            相对于web应用的根目录的文件路径(含文件全名)
	 * @return String.
	 * @throws IOException
	 */
	public static String getAbsolutePath(ServletContext sc,
			String fileRelativePath) throws IOException {
		String ret = "";
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		Resource resource = ctx.getResource(fileRelativePath);
		try {
			ret = resource.getFile().getAbsolutePath();
		} catch (IOException ioe) {
			throw ioe;
		}
		return ret;
	}

	// 获得文件的扩展名
	public static String getExtName(String fileName) {
		String ret = "";
		try {
			if (StringUtil.isNotEmpty(fileName)) {
				int position = fileName.lastIndexOf(".");
				if (position >= 0) {
					ret = fileName.substring(position + 1);
				}
			}
		} catch (Exception ex) {
			// 不处理
			logger.error("getExtName(String)",ex);
		}
		return ret;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal a = new BigDecimal(v1);
		BigDecimal b = new BigDecimal(v2);
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static String round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, RoundingMode.HALF_UP).toString();
	}

	/**
	 * 有效期从数字转换成字符串
	 * 
	 * @param applyValidate
	 * @return
	 */
	public static String intToStrApplyValidate(Integer applyValidate) {
		String strApplyValidate = null;
		if (applyValidate == 1) {
			strApplyValidate = "半个月";
		}
		if (applyValidate == 2) {
			strApplyValidate = "一个月";
		}
		if (applyValidate == 3) {
			strApplyValidate = "两个月";
		}
		return strApplyValidate;
	}

	/**
	 * 有效期从字符串转换成数字
	 * 
	 * @param applyValidate
	 * @return
	 */
	public static int strToIntApplyValidate(String applyValidate) {
		Integer intApolyValidate = null;
		if (applyValidate.equals("半个月")) {
			intApolyValidate = 1;
		}
		if (applyValidate.equals("一个月")) {
			intApolyValidate = 2;
		}
		if (applyValidate.equals("两个月")) {
			intApolyValidate = 3;
		}
		return intApolyValidate;
	}

	// 生成随即的六位数
	public static String createRandomPass() {
		return RandomStringUtils.random(8, "123456789");
	}

	// 判断字符是否为汉字
	public static boolean ifChinese(char targetChar) {
		boolean ret = false;
		try {
			Matcher mathcer = Pattern.compile("[\u4e00-\u9fa5]").matcher(
					targetChar + "");
			if (mathcer.find()) {
				ret = true;
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	// 判断字符是否为字母或数字
	public static boolean ifEnglishWordOrNumber(char targetChar) {
		boolean ret = false;
		try {
			Matcher mathcer = Pattern.compile("[0123456789a-zA-Z]").matcher(
					targetChar + "");
			if (mathcer.find()) {
				ret = true;
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	/**
	 * 日期月加addMonth
	 * 
	 * @param date
	 * @param addMonth
	 * @return
	 */
	public static Date addMonth(Date date, int addMonth, int setDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MONTH, addMonth - 1);
		instance.set(Calendar.DATE, setDate);
		return instance.getTime();
	}

	/**
	 * 日期天加addDate
	 * 
	 * @param date
	 * @param addDate
	 * @return
	 */
	public static Date addDate(Date date, int addDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.DATE, addDate);
		return instance.getTime();
	}

	/**
	 * 判定图片宽高
	 * 
	 * @param
	 * @param fileWidth
	 * @param fileHeight
	 * @return
	 */
	public static boolean ifLicitumImg(FileItem fileItem, String fileWidth,
			String fileHeight) {
		try {
			BufferedImage image = ImageIO.read(fileItem.getInputStream());
			int width = image.getWidth();
			int height = image.getHeight();
			BigDecimal sysWh = mul(String.valueOf(width), String
					.valueOf(height));
			BigDecimal noSysWh = mul(String.valueOf(fileWidth), String
					.valueOf(fileHeight));
			if (sysWh.compareTo(noSysWh) == 1) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判定图片宽高
	 * 
	 * @param
	 * @param fileWidth
	 * @param fileHeight
	 * @return
	 */
	public static boolean ifLicitumImgWH(InputStream imgIn) {
		boolean ret = false;
		try {
			int LicitumWidth = 172;
			int LicitumHeight = 292;
			BufferedImage image = ImageIO.read(imgIn);
			int width = image.getWidth();
			int height = image.getHeight();
			if (width == LicitumWidth && height == LicitumHeight) {
				ret = true;
			}
		} catch (IOException e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * 判断图片的类型
	 * 
	 * @param fileItem
	 * @return
	 */
	public static boolean ifLicitumImgType(FileItem fileItem) {
		boolean ret = false;
		try {
			String fileType = ".jpg";
			String name = fileItem.getFileName();
			if (StringUtil.isNotEmpty(name)
					&& name.length() > fileType.length()) {
				String type = name.substring(name.lastIndexOf("."), name
						.length());
				type = StringUtil.isEmpty(type) ? null : type.trim()
						.toLowerCase();
				if (fileType.equals(type)) {
					ret = true;
				}
			}
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readFile(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		FileReader fReader = null;
		try {
			String m = "";
			fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			while (((m = reader.readLine()) != null)) {
				list.add(new String(m.getBytes()));
			}
		} catch (IOException e) {
			return null;
		} finally {
			if (fReader != null) {
				try {
					fReader.close();
				} catch (IOException e) {
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return list;
	}

	/**
	 * 将list转换成
	 * 
	 * @param list
	 * @return
	 */
	public static String changeList2Str(List<String> list) {
		if (list == null || list.size() == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str);
			sb.append(",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * 将list转换成
	 * 
	 * @param list
	 * @return
	 */
	public static String[] changeListStr(List<String> list) {
		if (list == null || list.size() == 0)
			return null;
		String[] listStr = new String[list.size()];
		int num = 0;
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			listStr[num] = str;
		}
		return listStr;
	}

	/**
	 * 写入文件
	 * 
	 * @param file
	 * @param params
	 */
	public static void writeString(File file, List<String> params) {
		FileWriter filewriter = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			filewriter = new FileWriter(file, true);
			if (params != null && params.size() > 0) {
				for (String str : params) {
					filewriter.write(str + "\r\n");
				}
			}
		} catch (Exception e) {
		} finally {
			if (filewriter != null) {
				try {
					filewriter.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param dir
	 * @throws Exception
	 */
	public static void mkDirs(String dir) throws Exception {
		if (StringUtil.isEmpty(dir))
			return;
		File f1 = new File(dir);
		if (!f1.exists())
			f1.mkdirs();
	}

	/**
	 * 根据月份获取季度
	 * 
	 * @param month
	 * @return
	 */
	public static int getQuarter(int month) {
		int quarter = 0;
		switch (month) {
		case 1:
		case 2:
		case 3:
			quarter = 1;
			break;
		case 4:
		case 5:
		case 6:
			quarter = 2;
			break;
		case 7:
		case 8:
		case 9:
			quarter = 3;
			break;
		case 10:
		case 11:
		case 12:
			quarter = 4;
			break;
		default:
			break;
		}
		return quarter;
	}

	public static int getStudentType(int param) {
		int quarter = 0;
		switch (param) {
		case 0:
		case 4:
		case 7:
			quarter = 1;
			break;
		case 1:
		case 5:
		case 8:
			quarter = 2;
			break;
		case 2:
		case 6:
		case 9:
			quarter = 3;
			break;
		case 3:
			quarter = 4;
			break;
		default:
			break;
		}
		return quarter;
	}

	/**
	 * 计算年级
	 * 
	 * @param level
	 *            界
	 * @param studentType
	 *            学生类别
	 * @param skDate
	 *            学生刷卡时间
	 * @return
	 */
	public static int getLevel(String level, int studentType, Date skDate) {
		int quarter = 0;
		if (studentType == 1) {// 小学
			quarter = 6;// 六年制
		} else {
			quarter = 3;// 三年制(初中,高中,职高)
		}
		String byYear = "20" + level;
		Calendar cale = Calendar.getInstance();
		cale.setTime(skDate);
		int skYear = cale.get(Calendar.YEAR);
		int cYear = Integer.valueOf(byYear) - skYear;
		int year = quarter - cYear;
		int skMonth = cale.get(Calendar.MONTH) + 1;
		int cMonth = 8 - skMonth;
		if (cMonth < 0) {
			year = year + 1;
		}
		if (year <= 0) {
			year = 1;
		} else if (year > quarter) {
			year = quarter;
		}
		return year;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file != null && file.exists())
			file.delete();
	}

	/**
	 * 删除目录下的所有文件及其子目录
	 * 
	 * @param file
	 */
	public static void deleteFileAndCatalog(File file) {
		if (file.exists() && file.isDirectory())// 如果是目录
		{
			File[] delFile = file.listFiles();
			if (delFile.length == 0) {
				file.delete();
			}
			for (int i = 0; i < delFile.length; i++) {
				if (delFile[i].isDirectory()) {
					deleteFileAndCatalog(delFile[i]);// 递归调用
				}
				delFile[i].delete();
			}
		}
		file.delete();
	}

	/**
	 * 时间减分钟
	 * 
	 * @param date
	 * @param param
	 * @return
	 */
	public static Date getImportFileDate(Date date, int param) {
		try {
			Calendar cale = Calendar.getInstance();
			cale.setTime(date);
			int hour = param / 60;// 获取小时
			int minute = param % 60;// 获取分钟
			cale.add(Calendar.HOUR_OF_DAY, -hour);
			cale.add(Calendar.MINUTE, -minute);
			date = cale.getTime();
		} catch (Exception e) {
			// 异常不处理
		}
		return date;
	}

	/**
	 * 验证字符串是否由数字组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (StringUtil.isNotEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static Date getStringToDate(String date, String time) {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.YEAR, Integer.valueOf(date.substring(0, 4)));
		cale.set(Calendar.MONTH, Integer.valueOf(date.substring(4, 6)) - 1);
		cale.set(Calendar.DATE, Integer.valueOf(date.substring(6, 8)));
		cale.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.substring(0, 2)));
		cale.set(Calendar.MINUTE, Integer.valueOf(time.substring(2, 4)));
		cale.set(Calendar.SECOND, Integer.valueOf(time.substring(4, 6)));
		return cale.getTime();
	}

	public static boolean isContant(Set set, String value) {
		for (Iterator it = set.iterator(); it.hasNext();) {
			if (value.equals(it.next().toString())) {
				return true;
			}
		}
		return false;
	}

	// 获取客户端的IP地址
	public static String getClientIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	// 去掉小数点
	public static String removePoint(float oldFloat) {
		try {
			String tmpStr = oldFloat + "";
			int position = tmpStr.indexOf(".");
			if (position >= 0) {
				return tmpStr.substring(0, position);
			} else {
				return oldFloat + "";
			}
		} catch (Exception ex) {
			return oldFloat + "";
		}
	}

	public static boolean ifInList(List<String> allFuncIds, String oneFuncId) {
		boolean ret = false;
		try {
			if (allFuncIds != null && allFuncIds.size() > 0) {
				for (String tmpFunctId : allFuncIds) {
					if (tmpFunctId.equals(oneFuncId)) {
						ret = true;
						break;
					}
				}
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	public static String getGuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 验证字符串是否为六位的数字组成
	public static boolean ifSixDigtal(String targetStr) {
		boolean ret = false;
		try {
			Matcher matcher = Pattern.compile("^[0-9]{6}$").matcher(targetStr);
			if (matcher.find()) {
				ret = true;
			}
		} catch (Exception ex) {
			// 不处
		}
		return ret;
	}

	public static boolean ifSomeBitDigtal(String targetStr, int bitNum) {
		boolean ret = false;
		try {
			Matcher matcher = Pattern.compile("^[0-9]{" + bitNum + "}$")
					.matcher(targetStr);
			if (matcher.find()) {
				ret = true;
			}
		} catch (Exception ex) {
			// 不处
		}
		return ret;
	}

	// 验证是否为数字
	public static boolean checkIfDigtal(String targetStr) {
		boolean ret = false;
		try {
			Matcher matcher = Pattern.compile("^[0-9]+$").matcher(targetStr);
			if (matcher.find()) {
				ret = true;
			}
		} catch (Exception ex) {
			// 不处
		}
		return ret;
	}

	// 去掉小数点后的零
	public static String removeComma(String targetStr) {
		String ret = targetStr;
		try {
			int positon = targetStr.indexOf(".");
			ret = targetStr.substring(0, positon);
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	/**
	 * 将序列号转换为6位的
	 * 
	 * @return
	 */
	public static String getSerial(String serial) {
		String ret = "";
		if (serial.trim().length() == 1) {
			ret = "00000" + serial;
		} else if (serial.trim().length() == 2) {
			ret = "0000" + serial;
		} else if (serial.trim().length() == 3) {
			ret = "000" + serial;
		} else if (serial.trim().length() == 4) {
			ret = "00" + serial;
		} else if (serial.trim().length() == 5) {
			ret = "0" + serial;
		} else {
			ret = serial;
		}
		return ret;
	}

	// 读取dbf文件中内容的公用方法
	public static String getStrByCharToByte(String str) {
		if (StringUtil.isNotEmpty(str)) {
			byte[] temp = new byte[str.length()];
			for (int i = 0; i < str.length(); i++) {
				temp[i] = (byte) str.charAt(i);
			}
			return new String(temp);
		} else {
			return "";
		}
	}

	/**
	 * 获取EXCEL列位
	 * 
	 * @param paramValue
	 * @return
	 */
	public static String conExcelColumnNum(int paramValue) {
		if (paramValue < 27) {
			return digitalToLetters(paramValue);
		} else if (paramValue > 26) {
			int i = paramValue / 26;
			int j = paramValue % 26;
			// 第一位
			String oneStr = digitalToLetters(i);
			if (j != 0) {
				return oneStr + digitalToLetters(j);
			} else {
				return digitalToLetters(i - 1) + digitalToLetters(26);
			}
		}
		return "";
	}

	public static String digitalToLetters(int paramValue) {
		String paramV = "";
		switch (paramValue) {
		case 1:
			paramV = "A";
			break;
		case 2:
			paramV = "B";
			break;
		case 3:
			paramV = "C";
			break;
		case 4:
			paramV = "D";
			break;
		case 5:
			paramV = "E";
			break;
		case 6:
			paramV = "F";
			break;
		case 7:
			paramV = "G";
			break;
		case 8:
			paramV = "H";
			break;
		case 9:
			paramV = "I";
			break;
		case 10:
			paramV = "J";
			break;
		case 11:
			paramV = "K";
			break;
		case 12:
			paramV = "L";
			break;
		case 13:
			paramV = "M";
			break;
		case 14:
			paramV = "N";
			break;
		case 15:
			paramV = "O";
			break;
		case 16:
			paramV = "P";
			break;
		case 17:
			paramV = "Q";
			break;
		case 18:
			paramV = "R";
			break;
		case 19:
			paramV = "S";
			break;
		case 20:
			paramV = "T";
			break;
		case 21:
			paramV = "U";
			break;
		case 22:
			paramV = "V";
			break;
		case 23:
			paramV = "W";
			break;
		case 24:
			paramV = "X";
			break;
		case 25:
			paramV = "Y";
			break;
		case 26:
			paramV = "Z";
			break;
		default:
			paramV = "";
			break;
		}
		return paramV;
	}

	/**
	 * 输出从开始年到结束年的年份列表。
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public static List<String> getRangeYear(int startYear, int endYear) {
		List<String> years = new ArrayList<String>();
		if (endYear < startYear) {
			return null;
		}
		for (int n = startYear; n <= endYear; n++) {
			years.add(n + "");
		}
		return years;
	}

	/**
	 * 返回12月份
	 * 
	 * @return
	 */
	public static List<String> get12Months() {
		List<String> months = new ArrayList<String>();
		for (int n = 1; n <= 12; n++) {
			if (n < 10) {
				months.add("0" + n);
			} else {
				months.add("" + n);
			}
		}
		return months;
	}

	/**
	 * 返回31天。
	 * 
	 * @return
	 */
	public static List<String> get31Days() {
		List<String> days = new ArrayList<String>();
		for (int n = 1; n <= 31; n++) {
			if (n < 10) {
				days.add("0" + n);
			} else {
				days.add("" + n);
			}
		}
		return days;
	}

	/**
	 * 取身份证号中的性别(字典：男：118001, 女：118002)
	 * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
	 * --18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
	 */
	public static Integer getSexFromCardNo(String cardNo) {
		Integer ret = null;
		try {
			String sex = "";
			if (cardNo.length() == 15) {
				sex = cardNo.substring(14);
			}
			if (cardNo.length() == 18) {
				sex = cardNo.substring(16, 17);
			}
			if (new Integer(sex).intValue() % 2 == 0) {
				ret = 118002;
			} else {
				ret = 118001;
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	public static Date getBirthdayFromCardNo(String cardNo) {
		Date ret = null;
		try {
			// 年份
			String year = "";
			// 月份
			String month = "";
			// 日
			String day = "";

			if (cardNo.length() == 15) {
				year = cardNo.substring(6, 8);
				month = cardNo.substring(8, 10);
				day = cardNo.substring(10, 12);
				ret = DateUtil.stringToDate("19" + year + "-" + month + "-"
						+ day);
			}
			if (cardNo.length() == 18) {
				year = cardNo.substring(6, 10);
				month = cardNo.substring(10, 12);
				day = cardNo.substring(12, 14);
				ret = DateUtil.stringToDate(year + "-" + month + "-" + day);
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	/**
	 * 从报名号中获得相关信息 1 : 区县代码 2 : 招生年度 3 : 学校代码 4 : 班号 5 : 学号 6 : 届别
	 */
	public static String getSomeInfoFromReportNo(String reportNo, int infoType) {
		if (infoType == 1) {
			// 报名号中的区县
			return reportNo.substring(0, 2);
		} else if (infoType == 2) {
			// 报名号中的招生年度
			return reportNo.substring(2, 4);
		} else if (infoType == 3) {
			// 报名号中的学校代码
			return reportNo.substring(5, 7);
		} else if (infoType == 4) {
			// 报名号中的班号
			return reportNo.substring(7, 9);
		} else if (infoType == 5) {
			// 报名号中的学号
			return reportNo.substring(9, 11);
		} else if (infoType == 6) {
			// 报名号中的届别
			return reportNo.substring(4, 5);
		} else if (infoType == 7) {
			// 报名号中的学校代码
			return reportNo.substring(0, 2) + reportNo.substring(5, 7);
		}
		return null;
	}

	// key的格式为：(学校代码)学校名称(错误类型)
	// 获得key中的错误类型,并去掉“为空”两字
	public static String getErrorContentFromKey(String key) {
		String ret = "";
		try {
			String tempStr1 = key.substring(0, key.lastIndexOf(")"));
			ret = tempStr1.substring(tempStr1.lastIndexOf("(") + 1);
			if (ret.indexOf("为空") >= 0) {
				ret = ret.substring(0, ret.indexOf("为空"));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage() + ex.fillInStackTrace());
		}
		return ret;
	}

	/**
	 * 判断由输入流确定的图片是否能在网页上正常显示.
	 * 
	 * @param impageInputStream
	 *            图片输入流.
	 * @return boolean.
	 */
	public static boolean checkImgIfCanDisplayInWebPage(
			InputStream impageInputStream) {
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		try {
			BufferedImage image = ImageIO.read(impageInputStream);
			int width = image.getWidth();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static String getAppIpAndPort(HttpServletRequest request) {
		String ret = "";
		try {
			// IP
			String ip = request.getLocalAddr();
			// port
			String port = request.getLocalPort() + "";
			return "http://" + ip + ":" + port;
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	// 获得图片的宽
	public static int getWidthOfImage(InputStream impageInputStream) {
		try {
			BufferedImage image = ImageIO.read(impageInputStream);
			return image.getWidth();
		} catch (Exception ex) {
			return 0;
		}
	}

	// 获得图片的高
	public static int getHeightOfImage(InputStream impageInputStream) {
		try {
			BufferedImage image = ImageIO.read(impageInputStream);
			return image.getHeight();
		} catch (Exception ex) {
			return 0;
		}
	}

	// 从高中校代码中取区县代码
	public static String getTownFromSenSchoolCode(String code) {
		String town = null;
		try {
			if (StringUtil.isNotEmpty(code) && code.length() > 2) {
				town = code.substring(1, 3);
			}
		} catch (Exception e) {
			town = null;
		}
		return town;
	}

	// 处理ie和fireFox下载的文件名的乱码问题
	public static String fileNameForEveryBrower(HttpServletRequest req,
			String fileName) {
		String ret = fileName;
		try {
			String agent = req.getHeader("USER-AGENT");
			if (StringUtil.isNotEmpty(agent) && agent.indexOf("MSIE") >= 0) {
				ret = URLEncoder.encode(ret, "UTF-8");
				ret = URLDecoder.decode(ret, "UTF-8");
			}else if(StringUtil.isNotEmpty(agent) && agent.indexOf("Trident") >= 0
					&& agent.indexOf("rv:11") >= 0){
				ret = URLEncoder.encode(ret, "UTF-8");
				ret = URLDecoder.decode(ret, "UTF-8");
			}else if (StringUtil.isNotEmpty(agent)
					&& agent.indexOf("Mozilla") >= 0) {
				ret = MimeUtility.encodeText(ret, "UTF8", "B");
			}else {
				ret = URLEncoder.encode(ret, "UTF-8");
				ret = URLDecoder.decode(ret, "UTF-8");
			}
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	/**
	 * 从招生校代码中取学校类型
	 * 
	 * @return 招生校类型；无法解析时返回null
	 */
	public static Integer getSeniorSchoolKindFromCode(String schoolCode) {
		Integer kind = null;
		try {
			if (StringUtil.isNotEmpty(schoolCode) && schoolCode.length() > 1) {
				kind = Integer.parseInt(schoolCode.substring(0, 1));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + e.fillInStackTrace());
			kind = null;
		}
		return kind;
	}

	/**
	 * 创建报名号
	 * 
	 * @param townNo
	 * @param currentDate
	 * @param param
	 * @param schoolCode
	 * @param classNo
	 * @param studentNo
	 * @return 报名号
	 */
	public static String createReportNo(String townNo, String year,
			String param, String schoolCode, String classNo, String studentNo) {
		StringBuffer reportNo = new StringBuffer();
		;
		try {
			if (StringUtil.isNotEmpty(townNo) && StringUtil.isNotEmpty(year)
					&& StringUtil.isNotEmpty(param)
					&& StringUtil.isNotEmpty(schoolCode)
					&& StringUtil.isNotEmpty(classNo)
					&& StringUtil.isNotEmpty(studentNo)) {
				if (classNo.length() == 1) {
					classNo = "0" + classNo;
				}
				if (studentNo.length() == 1) {
					studentNo = "0" + studentNo;
				}
				reportNo.append(townNo).append(year.substring(2, 4)).append(
						param).append(schoolCode.substring(2, 4)).append(
						classNo).append(studentNo);
				if (reportNo.toString().length() != 11) {
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return reportNo.toString();
	}

	/**
	 * 比较时间
	 * 
	 * @param comparedate
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareDate(Date comparedate, Date beginDate,
			Date endDate) {
		if (comparedate != null && beginDate != null && endDate != null) {
			if (comparedate.compareTo(beginDate) == 0) {
				return true;
			} else if (comparedate.compareTo(endDate) == 0) {
				return true;
			} else if (comparedate.compareTo(beginDate) == 1
					&& comparedate.compareTo(endDate) == -1) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public static List<Date> getDateBetweenDate(Date beginDate, Date endDate) {
		List<Date> ret = new ArrayList<Date>();
		try {
			ret.add(beginDate);
			Date tempDate = addDate(beginDate, 1);
			while (tempDate.compareTo(endDate) <= 0) {
				ret.add(tempDate);
				tempDate = addDate(tempDate, 1);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage() + ex.fillInStackTrace());
		}
		return ret;
	}

	public static String getWeekName(Date date) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "周日");
		map.put(2, "周一");
		map.put(3, "周二");
		map.put(4, "周三");
		map.put(5, "周四");
		map.put(6, "周五");
		map.put(7, "周六");

		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return map.get(calendar.get(Calendar.DAY_OF_WEEK));
	}

	// 5月6日（周一）
	public static String getMonthDayWeek(Date date) {
		int intMonth = getMonthForDate(date);
		int intDay = getDayForDate(date);
		String weekName = getWeekName(date);

		return intMonth + "月" + intDay + "日（" + weekName + "）";
	}

	/**
	 * 将一个数字自动用0填充为指定位数的字符串 例如：num = 1; zeroBitNum = 8 结果为：00000001
	 * 
	 * @param num
	 *            要填充的数字.
	 * @param zeroBitNum
	 *            位数.
	 * @return String.
	 */
	public static String autoFillZero(int num, int zeroBitNum) {
		String ret = num + "";
		try {
			String formantStr = "%0" + zeroBitNum + "d";
			ret = String.format(formantStr, num);
		} catch (Exception ex) {
			// 不处理
		}
		return ret;
	}

	public static void main(String[] args) throws ParseException {

	}

	public static String[] strsplit(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return str.split(",");
		}
		return null;
	}

	/**
	 * 替换文件路径中的分割符 为当前系统支持的分割
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static String replaceFileSeparator(String filePath) {
		if (StringUtil.isNotEmpty(filePath)) {
			return filePath.replaceAll("[\\\\|/]+", File.separator
					+ File.separator).replace("\\", File.separator
					+ File.separator);
		}
		return filePath;
	}

	/**
	 * 格式化HTML文本
	 * 
	 * @param content
	 * @return
	 */
	public static String html(String content) {
		if (content == null)
			return "";
		String html = content;
		html = StringUtils.replace(html, "&apos;", "'");
		html = StringUtils.replace(html, "&quot;", "\"");
		html = StringUtils.replace(html, "&nbsp;&nbsp;", "\t");// 替换跳格
		html = StringUtils.replace(html, "&nbsp;", " ");// 替换空格
		html = StringUtils.replace(html, "&lt;", "<");
		html = StringUtils.replace(html, "&gt;", ">");
		html = StringUtils.replace(html, "&amp;", "&");
		html = StringUtils.replace(html, "&times;", "?¨￠");
		html = StringUtils.replace(html, "&divide;", "??");
		html = StringUtils.replace(html, "&ensp;", "         ");
		html = StringUtils.replace(html, "&emsp;", "         ");
		return html;
	}


	/**
	 * 当前学年开始年-出生日期年，如果大于6，不返回任何信息；
	 * 如果小于6，抛出异常；
	 * 如果等于6，再看出生日期，如果早于等于8月31日，不返回任何信息；
	 * 如果晚于8月31，抛出异常。
	 * @param birthday 出生日期年
	 * @param currentYear 学年开始年
	 * @return
	 * @throws ManagerException 不符合要求，抛出异常
	 */
	public static void isGetMoreSixYear(Date birthday,Integer currentYear){
		if(birthday==null) throw new ManagerException();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String birthdayYearStr=sdf.format(birthday);
		Integer birthdayYear=Integer.parseInt(birthdayYearStr);
		int result=currentYear-birthdayYear;
		if(result>6){
			return;
		}else if(result<6){
			throw new ManagerException();
		}else{
			sdf.applyPattern("MM-dd");
			String MMdd=sdf.format(birthday);
			if("08-31".compareTo(MMdd)>=0){
				return; 
			}else{
				throw new ManagerException();
			}
		}
	}
	public static String queryBrithBy15IdCard(String idCard15){   
	    String year =  idCard15.substring(6,8);   
	    String month = idCard15.substring(8,10);   
	    String day = idCard15.substring(10,12);   
	    return year+month+day;
	}
	public static String queryBrithBy18IdCard(String idCard18){   
	    String year =  idCard18.substring(6,10);   
	    String month = idCard18.substring(10,12);   
	    String day = idCard18.substring(12,14);   
	    return year+month+day;
	}
	   static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',

	        '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	   /**
	    * 对文件进行Md5加密
	    * @param file
	    * @return
	    */
	   public static String getMD5(File file) {
	      FileInputStream fis = null;

	      try {

	        MessageDigest md = MessageDigest.getInstance("MD5");

	        fis = new FileInputStream(file);

	        byte[] buffer = new byte[2048];

	        int length = -1;

	        long s = System.currentTimeMillis();

	        while ((length = fis.read(buffer)) != -1) {

	           md.update(buffer, 0, length);

	        }
	        byte[] b = md.digest();
	        return byteToHexString(b);
	      } catch (Exception ex) {
	        logger.error("getMD5()",ex);
	        return null;
	      } finally {

	        try {

	           fis.close();

	        } catch (IOException ex) {
	        	  logger.error("getMD5()",ex);
	  	          return null;
	        }

	      }

	   }

	 

	   /** */

	   /**

	    * 把byte[]数组转换成十六进制字符串表示形式

	    * @param tmp    要转换的byte[]

	    * @return 十六进制字符串表示形式

	    */

	   private static String byteToHexString(byte[] tmp) {

	      String s;

	      // 用字节表示就是 16 个字节

	      char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

	      // 所以表示成 16 进制需要 32 个字符

	      int k = 0; // 表示转换结果中对应的字符位置

	      for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

	        // 转换成 16 进制字符的转换

	        byte byte0 = tmp[i]; // 取第 i 个字节

	        str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

	        // >>> 为逻辑右移，将符号位一起右移

	        str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

	      }

	      s = new String(str); // 换后的结果转换为字符串

	      return s;

	   }
	   
	   public static<T> String transforJson(T t){
		   if(t==null)return null;
		   JSONObject obj=JSONObject.fromObject(t);
		   return obj.toString();
	   }
	   public static <T> T transforObj(String jsonStr,Class clazz){
		   if(NestUtil.isEmpty(jsonStr))return null;
		   return (T)JSONObject.toBean(JSONObject.fromObject(jsonStr),clazz);
	   }
		public static String getAbsolutePath2(
				ServletContext sc, String fileRelativePath)
		{
			String ret = "";
			ApplicationContext ctx = WebApplicationContextUtils.
				getWebApplicationContext(sc);
			Resource resource = ctx.getResource(fileRelativePath);
			try
			{
				ret = resource.getFile().getAbsolutePath();
			}
			catch (IOException ioe)
			{
				logger.error("系统:qasss;" + ioe.getMessage() + ";"
						+ ioe.fillInStackTrace());
			}
			return ret;
		}
}
