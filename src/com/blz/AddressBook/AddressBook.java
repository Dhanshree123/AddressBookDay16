package com.blz.AddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
	public static Map<String, AddressBook> hm = new HashMap<String, AddressBook>();
	public static Map<String, ArrayList<AddressBookContacts>> cityList = new HashMap<>();
	public static Map<String, ArrayList<AddressBookContacts>> stateList = new HashMap<>();

	public static String HOME = System.getProperty("user.home");
	public static String DIRECTORY = "AddressBookOutputFile";

	String addressBookName;
	Scanner sc = new Scanner(System.in);

	ArrayList<AddressBookContacts> list;

	public AddressBook() {
		list = new ArrayList<AddressBookContacts>();

	}

	public AddressBook(String addressBookName) {
		list = new ArrayList<AddressBookContacts>();
		this.addressBookName = addressBookName;

	}

	public AddressBookContacts create(String firstName, String lastName, String address, String city, String state,
			int zip, String ph_no, String email) {

		AddressBookContacts contact = new AddressBookContacts(firstName, lastName, address, city, state, zip, ph_no,
				email);
		return contact;
	}

	public void addContactDetails(AddressBookContacts contact) {

		int count = (int) list.stream().filter(i -> i.equals(contact)).count();
		if (count > 0)
			System.out.println("You tried to add duplicate contact, Contact already exits");

		else
			list.add(contact);

		if (cityList.containsKey(contact.getCity())) {
			cityList.get(contact.getCity()).add(contact);
		} else {
			cityList.put(contact.getCity(), new ArrayList<AddressBookContacts>());
			cityList.get(contact.getCity()).add(contact);
		}

		if (stateList.containsKey(contact.getState())) {
			stateList.get(contact.getState()).add(contact);
		} else {
			stateList.put(contact.getState(), new ArrayList<AddressBookContacts>());
			stateList.get(contact.getState()).add(contact);
		}
	}

	public void editContactDetails(String firstName) {

		int pos = 0;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getFirstName().equals(firstName))
				pos = i;

		while (true) {
			System.out.println("Choose the option to edit");
			System.out.println("1.Edit Last name");
			System.out.println("2.Edit Address");
			System.out.println("3.Edit City");
			System.out.println("4.Edit State");
			System.out.println("5.Edit Zip");
			System.out.println("6.Edit Phone Number");
			System.out.println("7.Edit Email");
			System.out.println("8.Exit");

			int choice = sc.nextInt();

			if (choice == 8)
				break;

			switch (choice) {
			case 1:
				System.out.println("Enter Last name for editing");
				list.get(pos).setLastName(sc.next());
				break;

			case 2:
				System.out.println("Enter Address for editing");
				list.get(pos).setaddress(sc.next());
				break;

			case 3:
				System.out.println("Enter city for editing");
				list.get(pos).setCity(sc.next());
				break;

			case 4:
				System.out.println("Enter state for editing");
				list.get(pos).setState(sc.next());
				break;

			case 5:
				System.out.println("Enter Zip for editing");
				list.get(pos).setZip(sc.nextInt());
				break;

			case 6:
				System.out.println("Enter Phone Number for editing");
				list.get(pos).setPh_no(sc.next());
				break;

			case 7:
				System.out.println("Enter email for editing");
				list.get(pos).setEmail(sc.next());
				break;

			}
		}
	}

	public void deleteContactDetails(String firstName) {
		int pos = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFirstName().equals(firstName))
				pos = i;
		}
		list.remove(pos);
	}

	public static void searchByCity(String city) {
		if (AddressBook.hm.isEmpty()) {
			System.out.println("No AddressBook Exists, add new AddressBook First");
			System.exit(0);
		}
		for (Map.Entry<String, AddressBook> ab : AddressBook.hm.entrySet()) {

			List<AddressBookContacts> c = ab.getValue().list.stream().filter(i -> i.getCity().equals(city))
					.collect(Collectors.toList());
			if (c.size() == 0)
				System.out.println("No entry with city name: " + city + " in addressbook " + ab.getKey());

			else
				for (int j = 0; j < c.size(); j++) {
					System.out.println("AddressBook " + ab.getKey() + " Name " + c.get(j).getFirstName() + " "
							+ c.get(j).getLastName());
				}
		}
	}

	public static void searchByState(String state) {

		if (AddressBook.hm.isEmpty()) {
			System.out.println("No AddressBook Exists, add new AddressBook First");
			System.exit(0);
		}
		for (Map.Entry<String, AddressBook> ab : AddressBook.hm.entrySet()) {

			List<AddressBookContacts> c = ab.getValue().list.stream().filter(i -> i.getCity().equals(state))
					.collect(Collectors.toList());

			if (c.size() == 0)
				System.out.println("No entry with state name: " + state + " in addressbook " + ab.getKey());

			else
				for (int j = 0; j < c.size(); j++)
					System.out.println("AddressBook " + ab.getKey() + " Name " + c.get(j).getFirstName() + " "
							+ c.get(j).getLastName());
		}
	}

	public static void viewByCity(String city) {
		List<AddressBookContacts> c = cityList.get(city);
		for (int j = 0; j < c.size(); j++) {
			System.out.println(c.get(j).getCity());
			System.out.println(" Name " + c.get(j).getFirstName() + " " + c.get(j).getLastName());
		}
	}

	public static void viewByState(String state) {
		List<AddressBookContacts> c = stateList.get(state);
		for (int j = 0; j < c.size(); j++) {
			System.out.println(" Name " + c.get(j).getFirstName() + " " + c.get(j).getLastName());
		}
	}

	public static void viewSortedContactsByFirstNameInAddressBook(String AddressBookName) {
		if (hm.get(AddressBookName) == null) {
			System.out.println("No addressBook with this name, enter correct address book");
			return;
		}
		hm.get(AddressBookName).list.stream().sorted(Comparator.comparing(AddressBookContacts::getFirstName))
				.map(i -> i.toString()).forEach(y -> System.out.println(y));
	}

	public static void viewSortedContactsByCityInAddressBook(String AddressBookName) {
		if (hm.get(AddressBookName) == null) {
			System.out.println("No addressBook with this name, enter correct address book");
			return;
		}
		hm.get(AddressBookName).list.stream().sorted(Comparator.comparing(AddressBookContacts::getCity))
				.map(i -> i.toString()).forEach(y -> System.out.println(y));
	}

	public static void viewSortedContactsByStateInAddressBook(String AddressBookName) {
		if (hm.get(AddressBookName) == null) {
			System.out.println("No addressBook with this name, enter correct address book");
			return;
		}
		hm.get(AddressBookName).list.stream().sorted(Comparator.comparing(AddressBookContacts::getState))
				.map(i -> i.toString()).forEach(y -> System.out.println(y));
	}

	public static void viewSortedContactsByZipInAddressBook(String AddressBookName) {
		if (hm.get(AddressBookName) == null) {
			System.out.println("No addressBook with this name, enter correct address book");
			return;
		}
		hm.get(AddressBookName).list.stream().sorted(Comparator.comparing(AddressBookContacts::getZip))
				.map(i -> i.toString()).forEach(y -> System.out.println(y));
	}
	
	///UC 13

	public void writeToFile(String AddressBookName) throws IOException {
		Path pathLoc = Paths.get(HOME + "\\eclipse-workspace\\AddressBookOops\\" + DIRECTORY);
		if (Files.notExists(pathLoc))
			Files.createDirectory(pathLoc);

		Path fileLoc = Paths.get(pathLoc + "\\" + AddressBookName + ".txt");
		if (Files.notExists(fileLoc))
			Files.createFile(fileLoc);
		StringBuffer empBuffer = new StringBuffer();
		list.forEach(book -> {
			String bookDataString = book.toString().concat("\n");
			empBuffer.append(bookDataString);
		});

		try {
			Files.write(fileLoc, empBuffer.toString().getBytes());
			System.out.println("Details succesfully added to address book file");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFromFile(String AddressBookName) throws IOException {
		Path pathLoc = Paths.get(HOME + "\\eclipse-workspace\\AddressBookOops\\" + DIRECTORY);
		Path fileLoc = Paths.get(pathLoc + "\\" + AddressBookName + ".txt");
		try {
			System.out.println("The contacts in the address book are : ");
			Files.lines(fileLoc).map(line -> line.trim()).forEach(line -> System.out.println(line));
			System.out.println("The contacts in the address book are : ");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
