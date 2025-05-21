package helpers;

import java.util.List;

public class Words {
    private String[] words = {"HTML", "CSS", "JAVASCRIPT", "JAVA", "PYTHON", "RUST", "REACT",
            "NODE", "TYPESCRIPT", "SQL", "POSTGRESQL", "MYSQL", "MONGODB", "NOSQL", "DOCKER",
            "REDIS", "SERVER", "SOFTWARE", "HARDWARE", "SPRING BOOT", "REACT NATIVE", "GITHUB",
            "GITLAB", "PHP", "GO", "SWIFT", "KOTLIN", "WINDOWS", "UBUNTU", "FEDORA", "DEBIAN",
            "TERMINAL"};

    public String generateWord() {
        int randomNumber = (int) (Math.random() * words.length);
        String word = words[randomNumber];
        return word;
    }

    public void updateWordState(String word, List<String> correctLetters) {
        for (String letter : word.split("")) {
            if (letter.equals(" ")) {
                System.out.print("  ");
            } else if (correctLetters.contains(letter)) {
                System.out.print(letter + " ");
            } else {
                System.out.print("_ ");
            }
        }
    }

}
