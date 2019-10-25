package jvm;

public class MyObject {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader()); // 打印值为null，原因返回的是根加载器bootStrap，是最高级别的类加载器

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader()); //AppClassLoader
        System.out.println(myObject.getClass().getClassLoader().getParent()); //ExtClassLoader
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent()); //null
    }
}
