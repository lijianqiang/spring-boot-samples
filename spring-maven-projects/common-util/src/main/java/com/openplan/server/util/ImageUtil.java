package com.openplan.server.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 */
public final class ImageUtil {

	/**
	 * 图片缩放
	 * 
	 * @param src
	 *            原图路径
	 * @param dest
	 *            缩放图路径
	 * @param height
	 *            高度
	 * @param width
	 *            宽度
	 */
	public static boolean resize(String src, String dest, int height, int width) {
		boolean result = false; // 是否进行了压缩
		String pictype = "";
		if (!"".equals(src) && src != null) {
			pictype = src.substring(src.lastIndexOf(".") + 1, src.length());
		}
		double ratio = 0; // 缩放比例
		File fs = new File(src);
		File fd = new File(dest);
		BufferedImage bi;
		try {
			bi = ImageIO.read(fs);
			Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			int itempWidth = bi.getWidth();
			int itempHeight = bi.getHeight();

			// 计算比例
			if ((itempHeight > height) || (itempWidth > width)) {
				ratio = Math.min((new Integer(height)).doubleValue() / itempHeight,
						(new Integer(width)).doubleValue() / itempWidth);
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
				ImageIO.write((BufferedImage) itemp, pictype, fd);
				result = true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public static void resizeWidth(String src, String dest, int height, int width) {
		String pictype = "";
		if (!"".equals(src) && src != null) {
			pictype = src.substring(src.lastIndexOf(".") + 1, src.length());
		}
		double ratio = 0; // 缩放比例
		File fs = new File(src);
		File fd = new File(dest);
		BufferedImage bi;
		try {
			bi = ImageIO.read(fs);
			Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			int itempWidth = bi.getWidth();

			// 计算比例
			if (itempWidth != width) {
				ratio = ((new Integer(width)).doubleValue() / itempWidth);
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
				ImageIO.write((BufferedImage) itemp, pictype, fd);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

		
	public static boolean isImage(File imageFile) {
		if (!imageFile.exists()) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}
	
	public static boolean isImage(InputStream imageFile) {
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}

//	public static void main(String[] args) throws IOException {
//		long start = System.nanoTime();
////		resize("/home/lijianqiang/Pictures/Screenshot.png", "/home/lijianqiang/Pictures/200.png", 200, 500);
//		File file = new File("/home/lijianqiang/t.txt");
//		boolean is = isImage(file);
//		System.out.println("out:" + (is ? "yes" : "no"));
//		System.out.println("time:" + (System.nanoTime() - start));
//		
//	}
}
