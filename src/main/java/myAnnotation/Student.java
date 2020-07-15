package myAnnotation;

public class Student {
    private long id;

    private String name;

    @MyLength(min = 11, max = 11, errorMessage = "errorMessage")
    private String mobile;

    public Student(long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }


}
