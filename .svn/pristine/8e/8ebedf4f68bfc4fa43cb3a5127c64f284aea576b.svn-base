package com.flyrish.hades.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.nestframework.commons.utils.StringUtil;

public class FileUtil {
	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separatorChar + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separatorChar + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	public static void createDirAndFile(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			new File(file.getParent()).mkdirs();
			file.createNewFile();
		}
	}
	 /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	/**
	 * 取得文件大小
	 * @return
	 */
	public static long getFileSiz(InputStream fis) {
		long s = 0;
		try {
			s = fis.available();
		} catch (IOException e) {
			return 0;
		}
		return s;
	}
	/**
	 * 
	 * 得到规定的大小
	 * @param name 配置文件的值
	 * @param isM 是mb 
	 * @return
	 */
	public static int getRightSize(boolean isM,String name){
		int size = 0;
		if(StringUtil.isNotEmpty(name)){
			String sizestr=null;
			if(isM){
			  sizestr = name.substring(0,name.indexOf('m'));
			}else{
			  sizestr = name.substring(0,name.indexOf('k'));
			}
			if(sizestr!=null){
				size = Integer.valueOf(sizestr);
			}
		}
		return size;
	}
}
