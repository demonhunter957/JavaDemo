package lombok;

import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Book {

    private int id;
    private String name;

    public static void main(String[] args) {
        Book book = new Book();
        book.setId(12).setName("java");
        System.out.println(book.getName());
    }
}
