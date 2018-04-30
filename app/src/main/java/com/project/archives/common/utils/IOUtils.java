package com.project.archives.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}

	/**
	 * 将流转换成字符串
	 * @param file
	 */
	public static String streamToString(File file) {
		String content = "";
		if (null == file) return content;
		try {
			int i = 0;
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((i = fis.read()) != -1) {
				bos.write(i);
			}
			content = bos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
