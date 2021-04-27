package com.itcast.test;

import com.itcast.utils.WaterMarkUtils;

import java.awt.*;
import java.io.*;

public class fileUpdate {
    public static void main(String[] args) {
        String filePath = "D:/test/";


        imgupdate(filePath);

    }


    private static void imgupdate(String filePath) {
        File file = new File(filePath);

        if (file.isDirectory()){
            int a =0;

            String[] list = file.list();
            Color color = new Color(115, 189, 109, 231);
            Font font = new Font("微软雅黑", Font.PLAIN, 66);
            for (int i = 0; i < list.length; i++) {
                a++;
                System.out.println(list[i]);
                String path = filePath + list[i];
                System.out.println(path);
                WaterMarkUtils.mark(path,"D:/test/test/"+a+".jpg",color,"我爱你",font);
                try {
                    InputStream inputStream = new FileInputStream(path);
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    inputStream.close();

                    OutputStream outputStream = new FileOutputStream(filePath+a+".jpg");
                    outputStream.write(bytes);
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
