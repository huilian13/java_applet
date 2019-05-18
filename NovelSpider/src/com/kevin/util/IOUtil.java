package com.kevin.util;

import java.io.*;

/**
 * 2018-06-17 12:39
 * io流工具
 *
 * @author kevin
 **/
public class IOUtil {
    /**
     * 不允许实例化
     */
    private IOUtil(){}

    /**
     * 写出文件
     * @param filename
     */
    public static void writeToFile(File filename,String content){
        BufferedWriter bw=null;
        try {
            //字符输出流
            bw=new BufferedWriter( new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            bw.write(content);
            //刷新
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
