package com.flyrish.hades.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.flyrish.hades.exception.ManagerException;

public class AntZip {

	public static void main(String[] args) {
		try {
//			AntZip.zip("d:\\testZIP", "d:\\压缩文件.zip");
//			AntZip.unzip("d:\\test\\王良（0108404021102171）.zip", "d:\\test");
//			File file = new File("d:\\test\\王良（0108404021102171）.zip");
//			String fileName = file.getName();
//			int firstLen = fileName.lastIndexOf("（");
//			int lastLen = fileName.lastIndexOf("）");
//			//fileName = fileName.substring(0, firstLen);
//			//System.out.println(fileName);
//			fileName = fileName.substring(firstLen+1, lastLen);
//			System.out.println(fileName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩zip文件
	 */
	public static void zip(String filePath, String zipFile)
	throws IOException, FileNotFoundException {
		File file = new File(filePath);
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
		compressFiles(file, zos);
		zos.close();
	}

	@SuppressWarnings("unused")
	public static void createExcelZip(List<File> fileList,String zipFile) 
	throws ManagerException{
		ZipOutputStream zos = null;
		FileInputStream fin = null;
		BufferedInputStream in = null;
		try{
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			zos.setEncoding("GBK");
			for(int i=0;i<fileList.size();i++){
				File file = fileList.get(i);
				byte[] buf = new byte[2048];
				int len;
				ZipEntry zipEntry = new ZipEntry(file.getName());

				fin = new FileInputStream(file);
				in = new BufferedInputStream(fin);
				zos.putNextEntry(zipEntry);
				while ((len = in.read(buf)) >= 0) {
					zos.write(buf, 0, len);
				}
				fin.close();
				in.close();
				zos.closeEntry();
			}
			zos.close();
		}catch(Exception e){
			if(zos!=null){
				try {
					zos.close();
				} catch (IOException e1) {
					throw new ManagerException();
				}
			}
			if(fin!=null){
				try {
					fin.close();
				} catch (IOException e1) {
					throw new ManagerException();
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					throw new ManagerException();
				}
			}
			throw new ManagerException();
		}
	}

	/**
	 * 递归压缩文件
	 */
	private static void compressFiles(File file, ZipOutputStream zos)
	throws IOException, FileNotFoundException {
		if (file.isDirectory()) {
			File[] innerFile = file.listFiles();
			if (innerFile != null) {
				for (int i = 0; i < innerFile.length; i++) {
					compressFiles(innerFile[i], zos);
				}
			}
		} else {
			String fileName = file.getName();
			String fn = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			byte[] buf = new byte[2048];
			int len;
			ZipEntry zipEntry = new ZipEntry(file.getName());
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fin);
			zos.putNextEntry(zipEntry);
			while ((len = in.read(buf)) >= 0) {
				zos.write(buf, 0, len);
			}
			in.close();
			zos.closeEntry();
		}
	}

	/**
	 * 解压缩zip文件
	 */
	public static List<File> unzip(File zipFileName, String outputDirectory) throws ManagerException{
		InputStream in = null;
		FileOutputStream out = null;
		List<File> fileList = new ArrayList<File>();
		try {
			ZipFile zipFile = new ZipFile(zipFileName,"GBK");
			java.util.Enumeration e = zipFile.getEntries();
			String fileName = zipFileName.getName();
			ZipEntry zipEntry = null;
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
				} else {
					String name = zipEntry.getName();
					String dir = fileName.substring(0, fileName.lastIndexOf("."));
					File f = new File(outputDirectory+ File.separator + dir);
					if(!f.exists()){
						f.mkdirs();
					}
					f = new File(outputDirectory + File.separator
							+ zipEntry.getName());
					f.createNewFile();
					in = zipFile.getInputStream(zipEntry);
					out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[1024];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
					fileList.add(f);
				}
			}
		} catch (Exception ex) {
			if(in!=null){
				try{
					in.close();
				}catch(Exception e){
					throw new ManagerException();
				}
			}
			if(out!=null){
				try{
					out.close();
				}catch(Exception e){
					throw new ManagerException();
				}
			}
			throw new ManagerException();
		}
		return fileList;
	}
	/**
	 * 创建目录，包括子目录
	 */
	private static void mkDirs(String dir) throws Exception {
		if (dir == null || dir.equals(""))
			return;
		File f1 = new File(dir);
		if (!f1.exists())
			f1.mkdirs();
	}

	/**
	 * 写文件到目标文件夹 
	 * @param outputst
	 * @param infile
	 */
	public static void outPutStreamFileMethod(String outputst,String fileNewName,File infile)
	throws ManagerException {
		FileInputStream inputstream = null;
		FileOutputStream outputstream = null;  
		try{
			int fCount = fileNewName.lastIndexOf(".");
			String fName = fileNewName.substring(0,fCount);
			String fLast = fileNewName.substring(fCount,fileNewName.length());
			int c;
			File out = new File(outputst);
			if(!out.exists()){
				out.mkdirs();
			}
			String s = (outputst+File.separator+fName.replace('/','\\'))+fLast;
			inputstream = new FileInputStream(infile);
			outputstream = new FileOutputStream(s);
			byte[] by = new byte[1024];
			while ((c = inputstream.read(by)) != -1) {
				outputstream.write(by, 0, c);
			}
		}catch(Exception e){
			throw new ManagerException();
		}finally{
			try{
				if(inputstream!=null){
					inputstream.close();
				}
				if(outputstream!=null){
					outputstream.close();
				}
			}catch(Exception ee){
				throw new ManagerException();
			}
		}
	}
	/**
	 * 删除多文件
	 * @param fileList
	 * @throws ManagerException
	 */
	public static void deleteFileList(List<File> fileList) throws ManagerException{
		try{
			if(fileList!=null&&fileList.size()>0){
				for(File f : fileList){
					if(f.exists())
						f.delete();
				}
			}
		}catch(Exception e){
			throw new ManagerException();
		}
	}
}



