package stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流（Stream）是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列
 * 集合讲的是数据，流讲的是计算！
 *
 * 题目：请按照给出的数据，找出同时满足以下条件的用户
 * 1.偶数ID
 * 2.年龄大于24
 * 3.用户名转为大写
 * 4.用户名字母倒排序
 * 5.只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        Stream stream = list.stream().filter(user ->
                user.getId()%2 == 0).filter(user ->
                user.getAge() >24).map(user ->
                user.getName().toUpperCase()).sorted((o1, o2) ->
                o2.compareTo(o1)).limit(1);
        stream.forEach(t -> System.out.println(t));
//        List<String> list2 = (List<String>) stream.collect(Collectors.toList()); // 把stream转回List
//        for (String s : list2) {
//            System.out.println(s);
//        }

//        List<Integer> list2 = Arrays.asList(1,2,3);
//        list2 = list2.stream().map(x -> x*2).collect(Collectors.toList());
//        for (Integer integer : list2) {
//            System.out.println(integer);
//        }
    }
}

@Data
@AllArgsConstructor
class User{

    private int id;
    private String name;
    private int age;
}
