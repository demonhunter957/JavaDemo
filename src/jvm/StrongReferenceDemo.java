package jvm;

public class StrongReferenceDemo {

    public static void main(String[] args) {

        Object object1 = new Object(); // 这样定义默认就是强引用
        Object object2 = object1; // object2引用赋值
        object1 = null;
        System.gc();
        System.out.println(object2); // object2为强引用，不会被回收
    }
}
