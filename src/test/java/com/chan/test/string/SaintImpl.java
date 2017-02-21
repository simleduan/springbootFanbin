package com.chan.test.string;

/**
 * 子类跟父类有同名属性，直接用对象.filed的值跟对象的引用类型有关（如果是直接new Son().filed获取的是子类的filed值，
 * 父类的被隐藏了，如果Father f = new Son();f.filed则是父类的属性值）
 * 子类方法中可以用super.filed获取父类的值
 * Created by Administrator on 2016/10/10.
 */
public class SaintImpl extends Hehe implements Saint {
    //    public String name;
//    public String hehe="saint";
    public int hehe = 100;

    @Override
    public String getName() {
        return name;
    }

    public void getHehe() {
        System.out.println(">>>" + hehe);
        System.out.println(">>>" + super.hehe);
    }

    public static void main(String[] args) {
        SaintImpl impl = new SaintImpl();
        System.out.println(impl.getName());
        System.out.println(impl.hehe);//saint
        Hehe hehe = new SaintImpl();
        impl.getHehe();
        System.out.println(hehe.hehe);//hehe
        System.out.println(impl.getName());
    }
}
