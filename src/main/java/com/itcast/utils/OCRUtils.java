package com.itcast.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OCRUtils {

        // 设置使用指定语言命令
        private final String LANG_OPTION = "-l";
        // 获取当前系统的换行符
        private final String EOL = System.getProperty("line.separator");

        // Tesseract-OCR的安装路径
        private String tessPath = "D:\\finally_app\\OCR\\Tesseract-OCR";

        /**
         * @param imageFile : 传入的图像文件
         * @return 识别后的字符串
         */
        public String recognizeText(File imageFile) throws Exception {
            if (!imageFile.exists()){
                System.out.println("imageFile不存在");
            }
            /**
             * 设置输出文件的保存的文件目录
             * 与当前图片在同一目录下
             * getParentFile : 获取当前文件的父目录
             */
            File outputFile = new File(imageFile.getParentFile(), "output");

            // 缓冲流,用于向输出文件写入识别后的数据
            StringBuffer strB = new StringBuffer();
            // 存储cmd命令
            // 注意:不识别空格
            // 如 -psm 6要写成cmd.add("-psm"),cmd.add("6")
            List<String> cmd = new ArrayList<>();

            // 调用tesseract指令
            cmd.add(tessPath + "\\tesseract");
            cmd.add("");
            // 输出文件名称
            cmd.add(outputFile.getName());
            // 指定识别类型
            cmd.add("-psm");
            // 6表示识别一行字符串
            cmd.add("6");
            // 使用指定语言包识别
            cmd.add(LANG_OPTION);
            // 使用英文语言包
            cmd.add("eng");
            // 使用中文识别包
//            cmd.add("chi_sim");

            ProcessBuilder pb = new ProcessBuilder();
            /**
             *Sets this process builder's working directory.
             */
            // 调用cmd命令相关代码
            pb.directory(imageFile.getParentFile());
            cmd.set(1, imageFile.getName());
            pb.command(cmd);
            pb.redirectErrorStream(true);

            // 设置开始时间
            long startTime = System.currentTimeMillis();
            System.out.println("开始时间：" + startTime);
            Process process = pb.start();
            // tesseract.exe 1.jpg 1 -l chi_sim
            //不习惯使用ProcessBuilder的，也可以使用Runtime，效果一致
            // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
            /**
             * the exit value of the process. By convention, 0 indicates normal
             * termination.
             */
            int w = process.waitFor();
            if (w == 0)// 0代表正常退出
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                        "UTF-8"));
                String str;

                while ((str = in.readLine()) != null) {
                    strB.append(str).append(EOL);
                }
                in.close();

                long endTime = System.currentTimeMillis();
                System.out.println("结束时间：" + endTime);
                System.out.println("耗时：" + (endTime - startTime) + "毫秒");
            }
            else {
                // 设置异常信息提醒
                String msg;
                switch (w) {
                    case 1:
                        msg = "Errors accessing files. There may be spaces in your image's filename.";
                        break;
                    case 29:
                        msg = "Cannot recognize the image or its selected region.";
                        break;
                    case 31:
                        msg = "Unsupported image format.";
                        break;
                    default:
                        msg = "Errors occurred.";
                }
                throw new RuntimeException(msg);
            }
            // 删除生成的文件
            new File(outputFile.getAbsolutePath() + ".txt").delete();
            String s = strB.toString().replaceAll("\\s*", "");
            if (StringUtils.isEmpty(s)){
                System.out.println("识别结果是空值");
            }else {
                System.out.println(s);
            }
            // 返回识别后的结果
            return strB.toString().replaceAll("\\s*", "");
        }
    }

