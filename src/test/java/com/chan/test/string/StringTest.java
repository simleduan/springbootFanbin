package com.chan.test.string;

/**
 * 通过javap -c 反编译理解String和intern
 * Created by Administrator on 2016/10/10.
 */
public class StringTest {
    public static void main(String[] args) {
//        String s1 = "String";
//        String s2 = new String("String");
//        String s3 = s2.intern();
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
        String baseStr = "baseStr";
        final String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr" + "01";
        String str3 = baseStr + "01";
        String str4 = baseFinalStr + "01";
        String str5 = new String("baseStr01");
        String str6 = str5.intern();

        System.out.println(str1 == str2);//#3 true
        System.out.println(str1 == str3);//#4 false
        System.out.println(str1 == str4);//#5 false
        System.out.println(str1 == str5);//#6 true
        System.out.println(str6 == str1);//#6
    }
}
