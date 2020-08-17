/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package cn.tll.redisson;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tanglilei
 * @since 2020/8/3 11:17
 */
public class YuanZhouLvTest {
   private static StringBuffer stringBuilder=new StringBuffer();
   private static StringBuffer stringBufferTxt=new StringBuffer();
   private static List<String> list=new ArrayList<>();



    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    private synchronized static void getFile(String path,int deep){
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for(int i=0;i<array.length;i++)
        {
            if(array[i].isFile())//如果是文件
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字
//                System.out.println( array[i].getName());
                // 输出当前文件的完整路径
                // System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                System.out.println(array[i].getPath());
                list.add(array[i].getPath());
            }
            else if(array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                System.out.println( array[i].getName());
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(),deep+1);
            }
        }
    }















    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     *
     * @param filePath
     */
    public synchronized static boolean readTxtFile(String filePath) {
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                stringBuilder.delete( 0, stringBuilder.length());
                System.out.println(stringBuilder+"test");
                while ((lineTxt = bufferedReader.readLine()) != null) {
                     stringBuilder.append(lineTxt);
                }
                System.out.println(stringBuilder);
                System.out.println(stringBuilder.length());
                read.close();
                return true;
            } else {
                System.out.println("找不到指定的文件");
                return false;
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return false;
    }

    /*
    * Kmp算法计算指定字符串在圆周率的具体位置
    *
    * */
    public synchronized static int getIndexOf(String str1,String str2){
        if (str1==null||str1.length()==0||str2==null||str2.length()==0){
            return 0;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] next=getNextArray(s2);
        int i=0;
        int j=0;
        while (i<s1.length&&j<s2.length){
            if (s1[i]==s2[j]){
                i++;
                j++;
            }else if (next[j]==-1){
                i++;
            }else {
                j=next[j];
            }
        }
        if (j==s2.length){
            if ((i-j)>9&&(i-j+20)<str1.length()){
                String substring = str1.substring(i - j - 9, i - j + 20);
                stringBufferTxt.append("3.1415926....");
                stringBufferTxt.append(substring);
                stringBufferTxt.append(".......");
            }else if ((i-j)>9){
                String substring = str1.substring(i - j - 9, str1.length()-1);
                stringBufferTxt.append("3.1415926....");
                stringBufferTxt.append(substring);
                stringBufferTxt.append(".......");
            }

        }
        return j==s2.length?i-j:-1;
    }

    public synchronized static int[] getNextArray(char[] s2) {
        if (s2.length==1){
            return new int[]{-1};
        }
        int[] next = new int[s2.length];
        next[0]=-1;
        next[1]=0;
        int i=2;
        int cn=0;
        while (i<s2.length){
            if (s2[i-1]==s2[cn]){
                next[i++]=++cn;
            }else if(cn>0){
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }



    public synchronized static String getIndex(List<String> list,String date) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();


        for (String s : list) {
            synchronized (s){
                boolean flag = readTxtFile(s);
                if (flag){
                    int indexOf = getIndexOf(stringBuilder.toString(), date);
                    list1.add(indexOf);
                    if (indexOf!=-1){
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)!=-1){
                long size=0;
                if (i>0){
                    size=i;
                }
                Long num=size*100000000;
                Long index=num+list1.get(i)+1;
                return String.valueOf(index);
            }
        }
        return null;
    }









    public static void main(String[] args) throws InterruptedException {
/*        String filePath = "E:\\π\\1.txt";
//      "res/";圆周率小数点后00000000001到00100000000一共1亿位
        readTxtFile(filePath);
        System.out.println(stringBuilder);
        String s1=stringBuilder.toString();
        String s2="0581120187751592";
//        System.out.println(Arrays.toString(getNextArray(s2.toCharArray())));
        System.out.println(s1.length());
        System.out.println(getIndexOf(s1,s2));
*//*        String filePath = "E:\\π";
        getFile(filePath,0);*/

        String filePath = "E:\\π";
        getFile(filePath,0);

        String str="19970926";
        String index = getIndex(list, str);
        System.out.println(index);
        System.out.println(stringBufferTxt);

    }


}