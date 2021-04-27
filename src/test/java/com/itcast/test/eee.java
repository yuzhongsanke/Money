package com.itcast.test;

import com.alibaba.fastjson.JSONObject;
import com.itcast.domain.User;
import com.itcast.utils.ExcelUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class eee {

    @Test
    public void holdDate() throws ParseException {
        String begin = "2021-03-11";
        String end = "2021-03-16";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = format.parse(begin);
        Date endDate = format.parse(end);
        Long day ;
        day = (endDate.getTime() - beginDate.getTime())/ (24*60*60*1000);
        System.out.println("开始时间和结束时间相差："+day+"天");
    }

    @Test
    public  void holdTomrr(){
        Date today = new Date();//获取今天的日期

        Calendar c = Calendar.getInstance();

        c.setTime(today);


        c.add(Calendar.DAY_OF_MONTH, 1);

        Date tomorrow = c.getTime();//这是明天


        c.setTime(today);

        c.add(Calendar.DAY_OF_MONTH, -1);

        Date yesterday = c.getTime();//这是昨天
    }

    /**
     * 用apache的BeanUtils实现Bean covert to Map
     * @throws Exception
     */
    @Test
    public  void beanToMap() throws Exception {

        User user=new User();
        Map<String,String> keyValues=null;

        user.setCreateTime("password");
        user.setUsername("test method!");
        user.setPassword("wang shisheng");

        keyValues= BeanUtils.describe(user);

        Iterator<Map.Entry<String, String>> iterator = keyValues.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }
    @Test
    public  void FFF(){

            HashMap<String ,Integer> hashMap=new HashMap<>();
            hashMap.put("tom",12);
            hashMap.put("jack",23);
            hashMap.put("rose",9);
            hashMap.put("jim",34);
            HashMap<String,Integer>map=sortHashMap(hashMap);
            System.out.println(map);

        }

        private static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> hashMap) {
            Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
            List<Map.Entry<String, Integer>> lsit = new ArrayList<>(entries);
            Collections.sort(lsit, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue()-o1.getValue();
                }
            });
            LinkedHashMap<String,Integer>linkedHashMap=new LinkedHashMap<>();
            for (Map.Entry<String, Integer> li : lsit) {
                linkedHashMap.put(li.getKey(),li.getValue());
            }
            return linkedHashMap;
        }

        @Test
    public void aaa() throws IOException {
            InputStream stream = null;
            try {
                String imgUrl ="" ;
                URL url = new URL(imgUrl);
                URLConnection conn = url.openConnection();
                 stream = conn.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BufferedImage bufferedImage = ImageIO.read(stream);
        }
}
