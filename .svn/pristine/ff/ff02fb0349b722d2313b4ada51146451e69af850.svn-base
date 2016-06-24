package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.HuploadOldfile;

public interface IDoWithFileServiceExt {
	/**
	 * 开始处理文件上传
	 * @param uuid 文件的uuid标识号
	 * @param filename 上传的文件名
	 * @param schoolcode 学校代码
	 * @param uploadfiletype 上传的文件类型
	 * @return 如果插入成功，则返回true,反之返回false
	 */
	Boolean doWithStartUploadFile(String uuid,String filename,String schoolcode,String uploadfiletype);
	/**
	 * 文件上传完成后，处理
	 * @param uuid 文件唯一标识号
	 * @param md5 文件密文流
	 * @param filepath 存放路径
	 * @return
	 *//*
	Boolean doWithEndUploadFile(String uuid,String md5,String filepath);*/
	/**
	 * 文件上传完成后，处理文件信息
	 * @param schoolcode 学校代码
	 * @param filetype 文件类型
	 * @param uuid 文件唯一标识号
	 * @param md5 文件密文流
	 * @param filepath 存放路径
	 * @return
	 */
	Boolean doWithEndUploadFileAll(String schoolcode,String filetype,String uuid, String filepath);
	/**
	 * 通过学校代码获取上传文件历史记录
	 * @param schoolcode 学校代码
	 * @return
	 */
	List<HuploadOldfile> queryHuploadOldfileList(String schoolcode);
}
