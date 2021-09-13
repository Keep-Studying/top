/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * TestReadCSV
 *
 * @author boyan
 * @version : TestReadCSV.java, v 0.1 2021-09-02 11:08 boyan
 */
public class TestReadCSV {

    //@SHP mycat--主键自增
    //static String ResultDynPara=DynamicParameter.getGuid();

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        String csvFilePath = "D:\\signmsg.csv";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://LocalHost:3306/testspringcloud", "root","root");
        System.out.println("数据库连接成功！");
        readCsv(csvFilePath,con);
        System.out.println("数据导入完成!");
    }

    /**
     * map.put("cardNum", split[2].trim());//卡号
     map.put("idCard", split[7].trim());//证件号码
     map.put("opertionId", split[8].trim());//柜员号
     map.put("orgNum",split[11].trim());//网点号
     map.put("mobile",split[15].trim() );//手机号
     map.put("bankServiceType",split[3].trim());//行内业务类型
     map.put("serviceType",split[17].trim());//外部业务类型
     map.put("bankProduct", split[12].trim());//3位银行产品代码
     * @param csvFilePath
     * @param con
     * @throws Exception
     */
    private static void readCsv(String csvFilePath, Connection con) throws Exception {
        int lineNumber=countLines();
        System.out.println("lineNumber="+lineNumber);
        BufferedReader br = null;
        try {
            String sql="Insert into b_signmsg(signMsgId,cardNum,idCard,opertionId,orgNum,mobile,bankServiceType,serviceType,bankProduct) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pStatement=con.prepareStatement(sql);

            br = new BufferedReader(new FileReader(csvFilePath));
            String line =null;
            int i=1;
            while((line = br.readLine())!=null){
                String ResultDynPara=DynamicParameter.getGuid();
                //读取到的内容给line变量
                //line = br.readLine();//加上这一行会导致readLine()方法被执行了两次,导致读取数据不全
                //System.out.println("line="+line);
                //System.out.println("line="+line);
                //ArrayList<String> csvList = new ArrayList<String>();
                //CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("gbk")); //一般用这编码读就可以了
                //reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
                //逐条读取记录，直至读完
                //System.out.println(reader.readRecord());
                //while(reader.readRecord()){
                //csvList.add("123");
                //}
                //System.out.println("csvList="+csvList);
                Map map=new HashMap<>();
                //System.out.println(br.readLine());//读取每一行数据
                //replaceAll的参数是regex,即基于规则表达式的替换,比如,可以通过replaceAll("\\d", "*")把一个字符串所有的数字字符都换成星号;
                String[] split = line.replaceAll("\"", "").split(",");//去掉存入数据库中的""
                System.out.println("split.length="+split.length);
                System.out.println(split);
                String cardNum="";
                if (split[2].trim().length()!=0) {
                    cardNum=split[2].trim();
                }
                String idCard="";
                if (split[7].trim().length()!=0) {
                    idCard= split[7].trim();
                }
                String opertionId="";
                if (split[8].trim().length()!=0) {
                    opertionId=split[8].trim();
                }
                String orgNum="";
                if (split[11].trim().length()!=0) {
                    orgNum=split[11].trim();
                }
                String mobile="";
                if (split[15].trim().length()!=0) {
                    mobile=split[15].trim();
                }
                String bankServiceType="";
                if (split[3].trim().length()!=0) {
                    bankServiceType=split[3].trim();
                }
                String serviceType="";
                if (split[17].trim().length()!=0) {
                    serviceType=split[17].trim();
                }
                String bankProduct="";
                if (split[12].trim().length()!=0) {
                    bankProduct=split[12].trim();
                }
                pStatement.setString(1, ResultDynPara);
                pStatement.setString(2, cardNum);
                pStatement.setString(3,idCard);
                pStatement.setString(4, opertionId);
                pStatement.setString(5, orgNum);
                pStatement.setString(6, mobile);
                pStatement.setString(7,bankServiceType );
                pStatement.setString(8, serviceType);
                pStatement.setString(9, bankProduct);
                pStatement.execute();
                System.out.println("pStatement="+pStatement);
                System.out.println("第"+(i++)+"次循环");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static int countLines() throws Exception {
        int lineNumber=0;
        File file4=new File("D:\\signmsg.csv");
        if(file4.exists()) {
            FileReader fr=new FileReader(file4);
            LineNumberReader lnr=new LineNumberReader(fr);

            long timeS=System.currentTimeMillis();//时间单位为ms
            while(null != lnr.readLine()) {
                lineNumber+=1;
            }
            long timeE=System.currentTimeMillis();
            System.out.println("此种方法所耗时间为："+(timeE-timeS)+"ms,文件总条数为："+lineNumber+"条");
            lnr.close();
        }
        return lineNumber;
    }
}