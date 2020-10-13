package com.blz.AddressBook;

import java.io.IOException;
import java.util.*;

public class AddressBookMain {

	public static void AddressBookList() {
		Scanner sc = new Scanner(System.in);
		if (AddressBook.hm.isEmpty()) {
			System.out.println("No AddressBook Exists, add new AddressBook First");
			System.out.println("Enter name of address book to be created");
			String name = sc.next();
			AddressBook obj = new AddressBook();
			AddressBook.hm.put(name, obj);
			System.out.println("Address Book Created");
		}
		for (Map.Entry<String, AddressBook> ab : AddressBook.hm.entrySet())
			System.out.println(ab.getKey());
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		Scanner sc = new Scanner(System.in);
		String firstName, lastName, address, city, state, ph_no, email;
		int zip;
		while (true) {

			System.out.println("Choose your option");
			System.out.println("1.Add a contact");
			System.out.println("2.Edit a contact");
			System.out.println("3.Delete a contact");
			System.out.println("4.Create new address book");
			System.out.println("5.Search Contact in particular city");
			System.out.println("6.Search Contact in particular state");
			System.out.println("7.View Contact in particular city");
			System.out.println("8.View Contact in particular state");
			System.out.println("9.Total Contacts in particular city");
			System.out.println("10.Total Contacts in particular state");
			System.out.println("11.View Alphabetically sorted contacts in a particular address book by name");
			System.out.println("12.View Alphabetically sorted contacts in a particular address book by city");
			System.out.println("13.View Alphabetically sorted contacts in a particular address book by state");
			System.out.println("14.View Alphabetically sorted contacts in a particular address book by zip");
			System.out.println("15.Write Address Book to file");
			System.out.println("16.Read Address Book from file");
			System.out.println("17.Exit");

			int choice = sc.nextInt();

			if (choice == 17)
				break;

			switch (choice) {

			case 1:

				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook1 = sc.next();
				AddressBook a1 = AddressBook.hm.get(abook1);
				boolean flag = true;
				while (flag) {
					System.out.println("Enter the first name");
					firstName = sc.next();
					System.out.println("Enter the last name");
					lastName = sc.next();
					System.out.println("Enter the Address");
					address = sc.next();
					System.out.println("Enter the city");
					city = sc.next();
					System.out.println("Enter the state");
					state = sc.next();
					System.out.println("Enter the zip");
					zip = sc.nextInt();
					System.out.println("Enter the Phone Number");
					ph_no = sc.next();
					System.out.println("Enter the Email Id");
					email = sc.next();
					AddressBookContacts c = a1.create(firstName, lastName, address, city, state, zip, ph_no, email);
					a1.addContactDetails(c);

					System.out.println("Do You want to add more Contacts?(Y/N)");
					String response = sc.next();
					if (response.equals("Y"))
						flag = true;
					else
						flag = false;
				}

				break;
			case 2:
				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook2 = sc.next();
				AddressBook a2 = AddressBook.hm.get(abook2);
				System.out.println("Enter the first name to edit");
				firstName = sc.next();
				a2.editContactDetails(firstName);
				System.out.println("Contact Details Editted");
				break;

			case 3:
				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook3 = sc.next();
				AddressBook a3 = AddressBook.hm.get(abook3);
				System.out.println("Enter the first name to Delete");
				firstName = sc.next();
				a3.deleteContactDetails(firstName);
				System.out.println("Contact Details Deleted");
				break;

			case 4:

				System.out.println("Enter name of address book to be created");
				String name = sc.next();
				AddressBook obj = new AddressBook();
				AddressBook.hm.put(name, obj);
				System.out.println("Address Book Created");
				break;

			case 5:

				System.out.println("Enter the city to search contacts");
				AddressBook.searchByCity(sc.next());
				break;

			case 6:

				System.out.println("Enter the state to search contacts");
				AddressBook.searchByState(sc.next());
				break;

			case 7:

				System.out.println("Enter the city to view contacts");
				AddressBook.viewByCity(sc.next());
				break;

			case 8:

				System.out.println("Enter the state to view contacts");
				AddressBook.viewByState(sc.next());
				break;

			case 9:
				System.out.println("Enter the city to view total contacts");
				int city_count = AddressBook.cityList.get(sc.next()).size();
				System.out.println(city_count);
				break;

			case 10:
				System.out.println("Enter the state to view total contacts");
				int state_count = AddressBook.stateList.get(sc.next()).size();
				System.out.println(state_count);
				break;

			case 11:
				System.out.println("Enter the address book to view its sorted contacts by Name");
				AddressBook.viewSortedContactsByFirstNameInAddressBook(sc.next());
				break;

			case 12:
				System.out.println("Enter the address book to view its sorted contacts by City");
				AddressBook.viewSortedContactsByCityInAddressBook(sc.next());
				break;

			case 13:
				System.out.println("Enter the address book to view its sorted contacts by State");
				AddressBook.viewSortedContactsByStateInAddressBook(sc.next());
				break;

			case 14:
				System.out.println("Enter the address book to view its sorted contacts by Zip");
				AddressBook.viewSortedContactsByZipInAddressBook(sc.next());
				break;

			case 15:
				AddressBookList();
				System.out.println("Enter the name of address Book to write");
				String abook4 = sc.next();
				AddressBook a4 = AddressBook.hm.get(abook4);
				System.out.println("Writing to file");
				try {
					a4.writeToFile(abook4);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 16:
				AddressBookList();
				System.out.println("Enter the name of address Book to read");
				String abook5 = sc.next();
				AddressBook a5 = AddressBook.hm.get(abook5);
				System.out.println("Reading from file");
				try {
					a5.readFromFile(abook5);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}

		}
	}

}
