import java.util.LinkedList;
import java.util.Scanner;

interface RecordManager {
    void add(Record record);

    boolean find(int idNumber);

    void delete(int idNumber);

    void update(int id, Scanner input);

    void display();
}

class StudentRecordManagement implements RecordManager {

    private LinkedList<Record> list;

    public StudentRecordManagement() {
        list = new LinkedList<>();
    }

    @Override
    public void add(Record record) {
        if (!find(record.getIdNumber())) {
            list.add(record);
        } else {
            System.out.println("Record already exists");
        }
    }

    @Override
    public boolean find(int idNumber) {//used to search for a student record in the database based on the provided ID
        for (Record record : list) {
            if (record.getIdNumber() == idNumber) {
                System.out.println(record);
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(int idNumber) {
        Record recordDel = null;
        for (Record record : list) {
            if (record.getIdNumber() == idNumber) {
                recordDel = record;
                break;
            }
        }
        if (recordDel == null) {
            System.out.println("Invalid record Id");
        } else {
            list.remove(recordDel);
            System.out.println("Successfully removed record");
        }
    }

    @Override
    public void update(int id, Scanner input) {
        if (find(id)) {
            Record record = findRecord(id);
            System.out.print("New Student id Number? ");
            int idNumber = input.nextInt();
            System.out.print("New Student contact Number? ");
            int contactNumber = input.nextInt();
            input.nextLine();
            System.out.print("New Student Name? ");
            String name = input.nextLine();
            record.setIdNumber(idNumber);
            record.setName(name);
            record.setContactNumber(contactNumber);
            System.out.println("Record Updated Successfully");
        } else {
            System.out.println("Record Not Found");
        }
    }

    public Record findRecord(int idNumber) {
        for (Record record : list) {
            if (record.getIdNumber() == idNumber) {
                return record;
            }
        }
        return null;
    }

    @Override
    public void display() {
        if (list.isEmpty()) {//checks is the list is empty 
            System.out.println("The list has no records");
        }
        for (Record record : list) {
            System.out.println(record);
        }
    }
}

class Record {
    private String name;
    private int idNumber;
    private int contactNumber;

    public Record(String name, int idNumber, int contactNumber) {
        this.name = name;//this is a reference to current object and we are accessing the instance variable of the class
        this.idNumber = idNumber;
        this.contactNumber = contactNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", idNumber=" + idNumber +
                ", contactNumber=" + contactNumber +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {
        RecordManager recordManager = new StudentRecordManagement();
        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            menu();
            option = input.nextInt();
            switch (option) {
                case 1:
                    // Adding a new student record
                    System.out.print("Student Name: ");
                    input.nextLine();
                    String name = input.nextLine();
                    System.out.print("Student ID: ");
                    int idNumber = input.nextInt();
                    System.out.print("Contact Number: ");
                    int contactNumber = input.nextInt();
                    Record record = new Record(name, idNumber, contactNumber);
                    recordManager.add(record);
                    break;
                case 2:
                    // Deleting a student record
                    System.out.print("Enter Student ID to delete: ");
                    int idToDelete = input.nextInt();
                    recordManager.delete(idToDelete);
                    break;
                case 3:
                    // Updating a student record
                    System.out.print("Enter Student ID to update: ");
                    int idToUpdate = input.nextInt();
                    recordManager.update(idToUpdate, input);
                    break;
                case 4:
                    // Searching for a student record
                    System.out.print("Enter Student ID to search: ");
                    int idToSearch = input.nextInt();
                    recordManager.find(idToSearch);
                    break;
                case 5:
                    // Displaying all student records
                    recordManager.display();
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (option != 9);
    }

    public static void menu() {
        System.out.println("MENU");
        System.out.println("1: Add Student");
        System.out.println("2: Delete Student");
        System.out.println("3: Update Student");
        System.out.println("4: Search Student");
        System.out.println("5: Display Students");
        System.out.println("9: Exit program");
        System.out.print("Enter your selection: ");
    }
}
//@override-method in a subclass is intended to override a method in its superclass
//benifits of using @override-code remains compatible with changes in superclass methods 

//OOP's concepts:
//Inheritance-abstract class("Person") is a base class for ("student")
//The Student class inherits from the Person class using the extends keyword.

//Polymorphism-method overriding is an example of polymorphism.
//The Student class has a method displayInfo() which overrides the abstract method from the Person class.

//Abstraction:RecordManager-managing records without specifying the actual implementation details.

//Encapsulation:classes like Student and Record encapsulate data (attributes) and methods that operate on the data.

//Interfaces:The RecordManager interface is an example of defining a contract for classes like StudentRecordManagement.

//Classes and Objects: Classes are the blueprints for creating objects, and objects are instances of classes.
//The Student class represents students, and the StudentRecordManagement class manages student records.
//contract-set of rules  that must be followed by a class or component when implementing certain methods
