import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class PersonService {
    public static String getUserInput(Scanner scanner) {
        String userInput = scanner.nextLine();
        return userInput;
    }


    public static String provideOptionsType(Scanner scanner) {
        System.out.println("Type 1: if you like to add a person to the list");
        System.out.println("Type 2: if you like to Print a list of current persons");
        System.out.println("Type 3: if you like to exit the program");
        String selection = getUserInput(scanner);
        while (!(selection.equals("1") || selection.equals("2") || selection.equals("3"))) {
            selection = getUserInput(scanner);
        }

        return selection;
    }

    public static String newOrRestoreFile(Scanner scanner) {
        System.out.println("Type 1: Restore from file");
        System.out.println("Type 2: Start new file");
        String selection = getUserInput(scanner);
        while (!(selection.equals("1") || selection.equals("2"))) {
            selection = getUserInput(scanner);
        }
        return selection;
    }

    public static void addPerson(Scanner scanner, String newOrRestore) throws IOException {

        String currentUsers;
        String fileName = null;

        if (newOrRestore.equals("1")) {
            // Get current users that are saved in the file
            currentUsers = PersonService.createCSVStringOfCurrentUsers("person.csv");
        } else {
            System.out.println("Please provide file name");
            fileName = getUserInput(scanner);
            while (fileName.isEmpty() || fileName.isBlank()) {
                fileName = getUserInput(scanner);
            }
            currentUsers = PersonService.createCSVStringOfCurrentUsers(fileName + ".csv");
        }


        // Get all the detail for the new user that will be added
        System.out.println("Please provide first name");
        String firstName = getUserInput(scanner);
        while (firstName.isEmpty() || firstName.isBlank()) {
            firstName = getUserInput(scanner);
        }

        System.out.println("Please provide last name");
        String lastName = getUserInput(scanner);
        while (lastName.isEmpty() || lastName.isBlank()) {
            lastName = getUserInput(scanner);
        }

        System.out.println("Please provide birth year");
        String birthYear = getUserInput(scanner);
        while (birthYear.isEmpty() || birthYear.isBlank()) {
            birthYear = getUserInput(scanner);
        }
        Integer birthYearInt = Integer.parseInt(birthYear);

        System.out.println("Please provide birth month");
        String birthMonth = getUserInput(scanner);
        while (birthMonth.isEmpty() || birthMonth.isBlank()) {
            birthMonth = getUserInput(scanner);
        }
        Integer birthMonthInt = Integer.parseInt(birthMonth);

        System.out.println("Please provide birth day");
        String birthDay = getUserInput(scanner);
        while (birthDay.isEmpty() || birthDay.isBlank()) {
            birthDay = getUserInput(scanner);
        }
        Integer birthDayInt = Integer.parseInt(birthDay);

        Person newPerson = new Person(firstName, lastName, birthYearInt, birthMonthInt, birthDayInt);
        String newPersonCSV = newPerson.formatAsCSV();
        String newListOfPeopleCSV;
        if(currentUsers == null){
            newListOfPeopleCSV = newPersonCSV;
        }  else {
            newListOfPeopleCSV = currentUsers + newPersonCSV;
        }

        if (newOrRestore.equals("1")) {
            FileIORunner.writeToFile("person.csv", newListOfPeopleCSV);
        } else {
            FileIORunner.writeToFile(fileName + ".csv", newListOfPeopleCSV);
        }
    }

    public static String createCSVStringOfCurrentUsers(String fileName) {
        String currentUsers = null;
        try {
            currentUsers = FileIORunner.readFromFile(fileName, true);

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File does not exist so can not display users from it!");
        } catch (IOException ioException){
            return currentUsers;
        }
        return currentUsers;
    }

    public static void printCurrentPeople(String newOrRestore, Scanner scanner) {

        // need to tell the difference between
        String currentUsers;
        String fileName = null;

        if (newOrRestore.equals("1")) {
            // Get current users that are saved in the file
            currentUsers = PersonService.createCSVStringOfCurrentUsers("person.csv");
        } else {
            System.out.println("Please provide the file name");
            fileName = getUserInput(scanner);
            while (fileName.isEmpty() || fileName.isBlank()) {
                fileName = getUserInput(scanner);
            }
            currentUsers = PersonService.createCSVStringOfCurrentUsers(fileName + ".csv");
        }
        if (currentUsers == null) {
            System.out.println("No file was created");
        } else {
            String[] currentUsersArr = currentUsers.split("\n");
            if (currentUsersArr[0].equals("")) {
                System.out.println("No users in the file");
            } else {
                for (int i = 0; i < currentUsersArr.length; i++) {
                    Person newPerson = new Person(currentUsersArr[i]);
                }
            }
        }
    }


}