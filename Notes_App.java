package notes.app;

import java.io.*;
import java.util.Scanner;


public class Notes_App {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Notes App ====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        sc.close();
    }

    // Add note (append to file)
    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
               bw.write(note);
               bw.newLine();
               System.out.println("✅ Note saved successfully!");
        } catch (IOException e) {
               System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // View notes (read file)
    private static void viewNotes() {
        System.out.println("\n---- Your Notes ----");
        try (FileReader fr = new FileReader(FILE_NAME);
	             BufferedReader br = new BufferedReader(fr)) {
	             String line;
	             int count = 1;
	             while ((line = br.readLine()) != null) {
	                System.out.println(count++ + ". " + line);
	             }
         } catch (FileNotFoundException e) {
              System.out.println("⚠ No notes found! Add some first.");
         } catch (IOException e) {
              System.out.println("❌ Error reading file: " + e.getMessage());
         }
     }
}
