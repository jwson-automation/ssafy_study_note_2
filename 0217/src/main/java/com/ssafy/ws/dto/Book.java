package com.ssafy.ws.dto;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String price;
	private String content;
	private String img;
	private String id;
	private String rating;
	private String comment;

	public Book() {

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + ", content="
				+ content + ", img=" + img + ", id=" + id + ", rating=" + rating + ", comment=" + comment + "]";
	}

	public Book(String isbn, String title, String author, String price, String content, String img, String id,
			String rating, String comment) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
		this.img = img;
		this.id = id;
		this.rating = rating;
		this.comment = comment;
	}


}
