package com.chan.test.string;

/**
 * 通过javap -c 反编译理解String和intern
 * Created by Administrator on 2016/10/10.
 */
public class StringTest2 {
    public static void main(String[] args) {
        String str1 = "str01";
        String str2 = new String("str") + new String("01");
        str2 = str2.intern();
        System.out.println(str2 == str1);//#7

        /*String str1 = "str01";
        String str2 = new String("str")+new String("01");
        str2.intern();
        System.out.println(str2 == str1);//#8*/
    }
}
