package com.Assignment;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Library lb = new Library();
		Scanner sc = new Scanner(System.in);

		// Adding sample books to the library
		lb.addBook(new Book("The Hobbit", "T.Zain", "123456789", "Fiction", 500.0));

		lb.addBook(new Book("1984", "George Orwell", "987654321", "Dystopian", 600.0));
		lb.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "456789123", "Classic", 700.0));

		// Adding sample users to the library
		User user1 = new User("Alice");
		User user2 = new User("Bob");
		lb.addUser(user1);
		lb.addUser(user2);

		boolean running = true;
		while (running) {
			System.out.println("\nLibrary Management System");
			System.out.println("1. Add a Book");
			System.out.println("2. Search Book by Title");
			System.out.println("3. Search Book by Author");
			System.out.println("4. Search Book by Genre");
			System.out.println("5. Issue a Book");
			System.out.println("6. Return a Book");
			System.out.println("7. Display All Books");
			System.out.println("8. Display Available Books");
			System.out.println("9. Display Books Issued to User");
			System.out.println("10. Calculate Fine for User");
			System.out.println("11. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				System.out.print("Enter title: ");
				String title = sc.nextLine();
				System.out.print("Enter author: ");
				String author = sc.nextLine();
				System.out.print("Enter ISBN: ");
				String isbn = sc.nextLine();
				System.out.print("Enter genre: ");
				String genre = sc.nextLine();
				System.out.print("Enter price: ");
				double price = sc.nextDouble();
				Book newBook = new Book(title, author, isbn, genre, price);
				lb.addBook(newBook);
				System.out.println("Book added successfully.");
				break;
			case 2:
				System.out.print("Enter title: ");
				title = sc.nextLine();
				lb.searchByTitle(title);
				break;
			case 3:
				System.out.print("Enter author: ");
				author = sc.nextLine();
				lb.searchByAuthor(author);
				break;
			case 4:
				System.out.print("Enter genre: ");
				genre = sc.nextLine();
				lb.searchByGenre(genre);
				break;
			case 5:
				System.out.print("Enter ISBN: ");
				isbn = sc.nextLine();
				System.out.print("Enter user name: ");
				String userName = sc.nextLine();
				User issuingUser = lb.getUsers().stream().filter(user -> user.getName().equalsIgnoreCase(userName))
						.findFirst().orElse(null);
				if (issuingUser == null) {
					System.out.println("User not found.");
				} else {
					try {
						lb.issueBook(isbn, issuingUser);
						System.out.println("Book issued successfully.");
					} catch (BookUnavailableException | BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 6:
				System.out.print("Enter ISBN: ");
				isbn = sc.nextLine();
				System.out.print("Enter user name: ");
				userName = sc.nextLine();
				User returningUser = lb.getUsers().stream().filter(user -> user.getName().equalsIgnoreCase(userName))
						.findFirst().orElse(null);
				if (returningUser == null) {
					System.out.println("User not found.");
				} else {
					try {
						lb.returnBook(isbn, returningUser);
						System.out.println("Book returned successfully.");
					} catch (BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 7:
				lb.displayAllBooks();
				break;
			case 8:
				lb.displayAvailableBooks();
				break;
			case 9:
				System.out.print("Enter user name: ");
				userName = sc.nextLine();
				User user = lb.getUsers().stream().filter(u -> u.getName().equalsIgnoreCase(userName)).findFirst()
						.orElse(null);
				if (user == null) {
					System.out.println("User not found.");
				} else {
					lb.displayIssuedBooks(user);
				}
				break;
			case 10:
				System.out.print("Enter user name: ");
				userName = sc.nextLine();
				User fineUser = lb.getUsers().stream().filter(u -> u.getName().equalsIgnoreCase(userName)).findFirst()
						.orElse(null);
				if (fineUser == null) {
					System.out.println("User not found.");
				} else {
					lb.calculateFine(fineUser);
					System.out.println("Total fine for user " + fineUser.getName() + ": $" + fineUser.getFine());
				}
				break;
			case 11:
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

		sc.close();
	}
}
