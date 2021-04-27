package com.itcast.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 給图片添加文字水印
 *
 * @author liqiang
 *
 */
public class WaterMarkUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        new WaterMarkUtils().mark("C:/Users/liqiang/Desktop/图片/kdmt.jpg", "C:/Users/liqiang/Desktop/图片/kdmt1.jpg",
                Color.red, "圖片來源:XXX",new Font("微软雅黑",Font.PLAIN,25));
    }

    /**
     * 图片添加水印
     *
     * @param srcImgPath
     *            需要添加水印的图片的路径
     * @param outImgPath
     *            添加水印后图片输出路径
     * @param markContentColor
     *            水印文字的颜色
     * @param waterMarkContent
     *            水印内容
     * @param markContextFont
     *           水印字体
     */
    public static  void mark(String srcImgPath, String outImgPath, Color markContentColor, String waterMarkContent,Font markContextFont) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);
            Image srcImg = ImageIO.read(srcImgFile);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // Font font = new Font("Courier New", Font.PLAIN, 12);
//            Font font = new Font("宋体", Font.PLAIN, 20);
//            Color color = new Color(255,255,255,255);
            g.setColor(markContentColor); // 根据图片的背景设置水印颜色
            g.setFont(markContextFont);

//            g.setFont(font);
            int x = srcImgWidth - getWatermarkLength(waterMarkContent, g) - 3;
            int y = srcImgHeight - 3;
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            g.drawString(waterMarkContent, x, y);
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent
     *            水印的文字
     * @param g
     * @return 水印文字总长度
     */
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
