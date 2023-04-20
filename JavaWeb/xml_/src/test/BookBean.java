package test;

public class BookBean {
    private String id;
    private String name;
    private String author;
    private float price;

    public BookBean(String id, String name, String author, float price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id=" + id + '\'' +
                ",name=" + name + '\'' +
                ", author=" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
