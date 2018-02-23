package com.openplan.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public static String DOT = ".";
	
	public static void copyFile(String sourcePath, String targetPath) {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			File sourceFile = new File(sourcePath);
			File targetFile = new File(targetPath);
			if (!targetFile.exists()) {
				targetFile.getParentFile().mkdirs();
			}
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (IOException e) {
			// LogRecord.recode(ImageUtils.class, "图片复制异常:", e.getMessage());
		} finally {
			// 关闭流
			try {
				if (inBuff != null) {
					inBuff.close();
				}
				if (outBuff != null) {
					outBuff.close();
				}
			} catch (IOException e) {
				// LogRecord.recode(ImageUtils.class, "文件流关闭异常:",
				// e.getMessage());
			}

		}
	}

	public static void main(String[] args) throws IOException {
//		long start = System.nanoTime();
//		copyFile("/home/lijianqiang/Pictures/Screenshot.png", "/home/lijianqiang/Pictures/300.png");
//		System.out.println("time:" + (System.nanoTime() - start));
		System.out.println(getExtention("Screenshot"));
	}
	
	 /**
     * 功能：提取文件名的后缀
     * 
     * @param fileName
     * @return
     */
	public static  String getExtention(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return "";
		}
        int pos = fileName.lastIndexOf(".");
        if (pos < 0) {
        	return "";
        }
        String substring = fileName.substring(pos + 1);
		return substring.toLowerCase();
    }
	
	/**
     * 功能：提取文件名的后缀，加点
     * 
     * @param fileName
     * @return
     */
	public static  String getExtentionName(String fileName) {
		String ext = getExtention(fileName);
		if (StringUtils.isEmpty(ext)) {
			return "";
		}
        return DOT + ext;
    }

}
