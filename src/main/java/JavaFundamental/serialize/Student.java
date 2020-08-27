package JavaFundamental.serialize;

import java.io.*;

public class Student implements Serializable {

    private String name;
    private Integer age;
    private Integer score;
    // 被transient修饰的属性不会被序列化
    private transient String password;

    public Student() {
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 序列化方法
     */
    public static void serialize() throws IOException {

        Student student = new Student();
        student.setName("chris");
        student.setAge(18);
        student.setScore(100);
        student.setPassword("123");

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(new File("student.txt")));
        objectOutputStream.writeObject(student);
        objectOutputStream.close();

        System.out.println("序列化成功！已经生成student.txt文件");
        System.out.println("==============================================");
    }

    /**
     * 反序列化的方法
     */
    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream(new File("student.txt")));
        Student student = (Student) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("反序列化结果为：");
        System.out.println(student);
    }


    /**
     * 重写readObject方法，对反序列化进行约束
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {

        // 调用默认的反序列化函数
        objectInputStream.defaultReadObject();

        // 手工检查反序列化后学生成绩的有效性，若发现有问题，即终止操作！
        if (0 > score || 100 < score) {
            throw new IllegalArgumentException("学生分数只能在0到100之间！");
        }
    }

    public static void main(String[] args) {

        try {
            serialize();
            deserialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
