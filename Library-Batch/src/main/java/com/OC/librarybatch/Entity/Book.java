package com.OC.librarybatch.Entity;

public class Book {


    private int id;

    private String name;

    private String author;

    private String category;

    private int copies;

    private int max_copies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getMax_copies() {
        return max_copies;
    }

    public void setMax_copies(int max_copies) {
        this.max_copies = max_copies;
    }

    public Book() {
    }

    public Book(int id, String name, String author, String category, int copies, int max_copies) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.copies = copies;
        this.max_copies = max_copies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", copies=" + copies +
                ", max_copies=" + max_copies +
                '}';
    }
}
