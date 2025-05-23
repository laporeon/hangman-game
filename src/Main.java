import application.HangmanGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean exit = false;

        System.out.println("" +
                "\n" +
                "▗▖ ▗▖ ▗▄▖ ▗▖  ▗▖ ▗▄▄▖▗▖  ▗▖ ▗▄▖ ▗▖  ▗▖     ▗▄▄▖ ▗▄▖ ▗▖  ▗▖▗▄▄▄▖\n" +
                "▐▌ ▐▌▐▌ ▐▌▐▛▚▖▐▌▐▌   ▐▛▚▞▜▌▐▌ ▐▌▐▛▚▖▐▌    ▐▌   ▐▌ ▐▌▐▛▚▞▜▌▐▌   \n" +
                "▐▛▀▜▌▐▛▀▜▌▐▌ ▝▜▌▐▌▝▜▌▐▌  ▐▌▐▛▀▜▌▐▌ ▝▜▌    ▐▌▝▜▌▐▛▀▜▌▐▌  ▐▌▐▛▀▀▘\n" +
                "▐▌ ▐▌▐▌ ▐▌▐▌  ▐▌▝▚▄▞▘▐▌  ▐▌▐▌ ▐▌▐▌  ▐▌    ▝▚▄▞▘▐▌ ▐▌▐▌  ▐▌▐▙▄▄▖\n" +
                "");

        Scanner scanner = new Scanner(System.in);
        HangmanGame hangmanGame = new HangmanGame();

        while (!exit) {
            System.out.print("Press 1 to start or 0 to exit: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    hangmanGame.play();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again...");
                    break;
            }
        }

        scanner.close();
    }
}