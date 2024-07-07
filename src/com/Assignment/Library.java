package com.Assignment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
	private List<Book> books;
	private List<User> users;

	public Library() {
		this.books = new ArrayList<>();
		this.users = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void searchByTitle(String title) {
		books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).forEach(System.out::println);
	}

	public void searchByAuthor(String author) {
		books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).forEach(System.out::println);
	}

	public void searchByGenre(String genre) {
		books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genre)).forEach(System.out::println);
	}

	public void issueBook(String isbn, User user) throws BookUnavailableException, BookNotFoundException {
		Book book = findBookByIsbn(isbn);
		if (book == null) {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
		}
		if (!book.isAvailable()) {
			throw new BookUnavailableException("Book with ISBN " + isbn + " is unavailable.");
		}
		book.setAvailable(false);
		user.issueBook(book);
	}

	public void returnBook(String isbn, User user) throws BookNotFoundException {
		Book book = findBookByIsbn(isbn);
		if (book == null) {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
		}
		book.setAvailable(true);
		user.returnBook(isbn);
	}

	public void displayAllBooks() {
		books.forEach(System.out::println);
	}

	public void displayAvailableBooks() {
		books.stream().filter(Book::isAvailable).forEach(System.out::println);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}

	public void displayIssuedBooks(User user) {
		user.getIssuedBooks().forEach(System.out::println);
	}
	

	public void calculateFine(User user) {
		user.getIssuedBooks().forEach(issuedBook -> {
			long daysIssued = ChronoUnit.DAYS.between(issuedBook.getIssueDate(), LocalDate.now());
			if (daysIssued > 90) {
				double fine = (daysIssued - 90) * issuedBook.getBook().getPrice() * 0.02;
				user.addFine(fine);
			}
		});
	}

	private Book findBookByIsbn(String isbn) {
		return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
	}
}
