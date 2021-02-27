import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem01 {
    public static void main(String[] args) throws IOException {

        List<String> words = getWords();

        Map<String, String> result = checkWords(words);

        printResult(result);

    }

    private static void printResult(Map<String, String> result) {
        if (result.isEmpty()) {
            System.out.println("There are no acronyms");
            return;
        }
        System.out.println("The Acronyms pair are:");
        result.keySet().forEach(key -> {
            System.out.println(String.format("%s - %s", key, result.get(key)));
        });
    }

    private static Map<String, String> checkWords(List<String> words) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size() ; j++) {
                String firstWord = words.get(i);
                String secondWord = words.get(j);
                List<String> secondWordAsList = new ArrayList<String>(Arrays.asList(secondWord.split("")));
                if (firstWord.length() == secondWordAsList.size()) {
                    boolean flag = true;
                    for (int k = 0; k < firstWord.length() ; k++) {
                        int index = secondWordAsList.indexOf("" + firstWord.charAt(k));
                        if (index == -1) {
                            flag = false;
                            break;
                        } else {
                            secondWordAsList.remove(index);
                        }
                    }
                    if (flag) {
                        result.put(firstWord, secondWord);
                    }
                }
            }
        }
        return result;
    }

    private static List<String> getWords() throws IOException {

        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter word ('Enter' for end.)");
        String word = reader.readLine();
        while (!word.equals("")) {
            words.add(word);
            System.out.printf(String.format("List of words: %s%n", words.toString()));
            System.out.println("Enter words ('Enter' for end.)");
            word = reader.readLine();
        }
        return words;
    }
}
