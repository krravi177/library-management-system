package com.Assignment;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private String genre;
	private boolean available;
	private double price;

	public Book(String title, String author, String isbn, String genre, double price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.available = true;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getGenre() {
		return genre;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", genre=" + genre + ", available="
				+ available + ", price=" + price + "]";
	}
}
