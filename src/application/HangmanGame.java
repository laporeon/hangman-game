package application;

import enums.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {

    Scanner scanner = new Scanner(System.in);

    private String[] words = {"HTML", "CSS", "JAVASCRIPT", "JAVA", "PYTHON", "RUST", "REACT",
            "NODE", "TYPESCRIPT", "SQL", "POSTGRESQL", "MYSQL", "MONGODB", "NOSQL", "DOCKER",
            "REDIS"};
    private final int totalChances = 5;
    private int remainingChances = 5;
    private boolean restart = true;
    private final List<String> incorrectLetters = new ArrayList<String>();
    private final List<String> correctLetters = new ArrayList<String>();
    private final List<String> attemptedLetters = new ArrayList<String>();

    public void play() {
        while (restart) {
            resetGameStates();
            String word = generateWord();
            startGame(word);

            playAgain();
        }
    }

    private void startGame(String word) {

        while (remainingChances > 0 && !isGuessed(word)) {
            System.out.printf("\n%s%sGuess the word in %d chances:%s ", Colors.BOLD, Colors.BLUE,
                    totalChances, Colors.RESET);
            updateWordState(word);

            System.out.printf("\n%sAttempted letters: %s%s", Colors.GRAY, attemptedLetters,
                    Colors.RESET);

            System.out.print("\nChoose a letter: ");
            String letter = scanner.nextLine().toUpperCase().trim();

            if (!isValidLetter(letter)) {
                System.out.println("Please, enter a single valid letter.");
                continue;
            }

            if (isLetterAlreadyUsed(letter)) {
                System.out.println("You already tried this letter.");
                continue;
            }

            checkIfWordHasLetter(word, letter);
            attemptedLetters.add(letter);

            if (isGuessed(word)) {
                System.out.printf("%s%s\nYOU WON! The word was: %s%s", Colors.BOLD, Colors.GREEN,
                        word, Colors.RESET);
                return;
            }
        }

        if (!isGuessed(word)) {
            System.out.printf("%s%s\nYOU LOST! The word was: %s%s",
                    Colors.BOLD, Colors.RED, word, Colors.RESET);
        }
    }

    private void checkIfWordHasLetter(String word, String letter) {
        if (word.contains(letter)) {
            correctLetters.add(letter);
            System.out.println("Correct!");
        } else {
            incorrectLetters.add(letter);
            remainingChances--;
            System.out.printf("%sIncorrect! You have %d chance(s) left.%s\n",
                    Colors.RED, remainingChances, Colors.RESET);
        }
    }

    private void updateWordState(String word) {
        for (String letter : word.split("")) {
            if (correctLetters.contains(letter)) {
                System.out.print(letter + " ");
            } else {
                System.out.print("_ ");
            }
        }
    }

    private boolean isValidLetter(String letter) {
        return letter.length() == 1 && Character.isLetter(letter.charAt(0));
    }

    private boolean isGuessed(String word) {
        for (String letter : word.split("")) {
            if (!correctLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    private boolean isLetterAlreadyUsed(String letter) {
        return attemptedLetters.contains(letter);
    }


    private String generateWord() {
        int randomNumber = (int) (Math.random() * words.length);
        String word = words[randomNumber];
        return word;
    }

    private void playAgain() {

        while (true) {
            System.out.print("\n\nPress 1 to play again or 0 to quit: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    play();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.print("Invalid option. Try again... ");
                    break;
            }

        }
    }

    private void resetGameStates() {
        remainingChances = totalChances;
        correctLetters.clear();
        incorrectLetters.clear();
        attemptedLetters.clear();
        clean();
    }

    private void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void exit() {
        System.out.println("Exiting...");
        System.out.flush();
        System.exit(0);
    }
}
