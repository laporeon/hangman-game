package helpers;

import java.util.List;

public class Validators {
    public boolean isValidLetter(String letter) {
        return letter.length() == 1 && Character.isLetter(letter.charAt(0));
    }

    public boolean isLetterAlreadyUsed(List<String> attemptedLetters, String letter) {
        return attemptedLetters.contains(letter);
    }

    public boolean checkIfWordHasLetter(String word, String letter) {
        return word.contains(letter);
    }

    public boolean isGuessed(String word, List<String> correctLetters) {
        for (String letter : word.split("")) {
            if (!correctLetters.contains(letter) && !letter.equals(" ")) {
                return false;
            }
        }
        return true;
    }
}
