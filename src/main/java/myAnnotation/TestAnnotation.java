package myAnnotation;

import java.lang.reflect.Field;

public class TestAnnotation {

    public static void main(String[] args) throws IllegalAccessException {
        Student student = new Student(11L, "Chris", "111");
        System.out.println(validate(student));
    }

    public static String validate(Object object) throws IllegalAccessException{

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MyLength.class)) {
                MyLength myLength = field.getAnnotation(MyLength.class);
                field.setAccessible(true);
                String string = ((String)field.get(object));
                int value = string.length();
                if(value < myLength.min()){
                    return myLength.errorMessage();
                }
            }
        }
        return null;
    }
}
