package com.Assignment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<IssuedBook> issuedBooks;
	private double fine;

	public User(String name) {
		this.name = name;
		this.issuedBooks = new ArrayList<>();
		this.fine = 0.0;
	}

	public String getName() {
		return name;
	}

	public List<IssuedBook> getIssuedBooks() {
		return issuedBooks;
	}

	public double getFine() {
		return fine;
	}

	public void addFine(double amount) {
		this.fine += amount;
	}

	public void issueBook(Book book) {
		issuedBooks.add(new IssuedBook(book, LocalDate.now()));
	}

	public void returnBook(String isbn) {
		issuedBooks.removeIf(issuedBook -> issuedBook.getBook().getIsbn().equals(isbn));
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", issuedBooks=" + issuedBooks + ", fine=" + fine + "]";
	}
}
