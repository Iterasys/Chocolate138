package entities;

public class BookStoreEntity {

    public String userId;
    public ISBN[] collectionOfIsbns;

    public String isbn;

    public static class ISBN {

        public String isbn;

        public ISBN (String isbn) {
            this.isbn = isbn;
        }
    }
}