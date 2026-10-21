import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> history = new Stack<>();
        String text = "";

        while (true) {
            System.out.println("\n--- Text Editor ---");
            System.out.println("1. Type text");
            System.out.println("2. Undo");
            System.out.println("3. Display text");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Save the previous state
                    history.push(text);

                    System.out.print("Enter text to add: ");
                    String input = sc.nextLine();

                    text += input;
                    System.out.println("Text added successfully.");
                    break;

                case 2:
                    if (history.isEmpty()) {
                        System.out.println("Nothing to undo!");
                    } else {
                        text = history.pop();
                        System.out.println("Last change undone.");
                    }
                    break;

                case 3:
                    System.out.println("Current text: " + text);
                    break;

                case 4:
                    System.out.println("Exiting editor...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
