package application;

import enums.Colors;
import helpers.Validators;
import helpers.Words;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {

    Words words = new Words();
    Validators validators = new Validators();
    Scanner scanner = new Scanner(System.in);

    private final int totalChances = 5;
    private int remainingChances = 5;
    private boolean restart = true;
    private final List<String> correctLetters = new ArrayList<String>();
    private final List<String> attemptedLetters = new ArrayList<String>();

    public void play() {
        do {
            resetGameStates();
            startGame();
            playAgain();
        } while (restart);
    }


    private void startGame() {

        String word = words.generateWord();

        while (remainingChances > 0 && !validators.isGuessed(word, correctLetters)) {
            System.out.printf("\n%s%sGuess the word in %d chances:%s ", Colors.BOLD, Colors.BLUE,
                    totalChances, Colors.RESET);
            words.updateWordState(word, correctLetters);
            System.out.printf("\nYou have %s%s%s%d%s chance(s) left. Attempted letters: %s\n",
                    Colors.BOLD, Colors.UNDERLINE, Colors.ORANGE, remainingChances, Colors.RESET,
                    attemptedLetters);


            System.out.print("\nChoose a letter: ");
            String letter = scanner.nextLine().toUpperCase().trim();

            if (!validators.isValidLetter(letter)) {
                System.out.printf("%sERROR: Please, enter a single letter.%s\n", Colors.RED,
                        Colors.RESET);
                continue;
            }

            if (validators.isLetterAlreadyUsed(attemptedLetters, letter)) {
                System.out.printf("%sERROR: You already tried this letter.%s\n", Colors.RED,
                        Colors.RESET);
                continue;
            }

            attemptedLetters.add(letter);

            if (!validators.checkIfWordHasLetter(word, letter)) {
                remainingChances--;
                System.out.printf("%sIncorrect!%s\n", Colors.RED, Colors.RESET);
                continue;
            }

            correctLetters.add(letter);
            System.out.println("Correct!");

            if (validators.isGuessed(word, correctLetters)) {
                System.out.printf("%s%s\nYOU WON! The word was: %s%s", Colors.BOLD, Colors.GREEN,
                        word, Colors.RESET);
                return;
            }
        }
        
        System.out.printf("%s%s\nYOU LOST! The word was: %s%s", Colors.BOLD, Colors.RED, word,
                Colors.RESET);

    }

    private void playAgain() {
        System.out.print("\n\nPress 1 to play again or 0 to quit: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                restart = true;
                break;
            case 0:
                restart = false;
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
            default:
                System.out.print("Invalid option. Try again... ");
                playAgain();
                break;
        }
    }

    private void resetGameStates() {
        remainingChances = totalChances;
        correctLetters.clear();
        attemptedLetters.clear();
        clean();
    }

    private void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
