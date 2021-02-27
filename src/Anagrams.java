import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printMenu();
        String input = reader.readLine();
        while (!input.equals("")) {
            switch (input) {
                case "1" -> addListOfWords(words);
                case "2" -> words.add(addWord());
                case "3" -> {
                    Map<String, String> anagramsResult = checkWords(words);
                    printResult(anagramsResult);
                }
            }
            printMenu();
            input = reader.readLine();
        }
    }

    private static void printMenu() {
        System.out.println("Please choose option:");
        System.out.println("1 - for input list of words.");
        System.out.println("2 - for input word");
        System.out.println("3 - show result for anagrams");
        System.out.println("'Enter' for exit.");
    }

    private static void addListOfWords(List<String> words) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter list of word separated by ',':");
        List<String> inputWords = Arrays.stream(reader.readLine().split(",")).collect(Collectors.toList());
        words.addAll(inputWords);
    }

    private static String addWord() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter word:");
        return reader.readLine();
    }

    private static Map<String, String> checkWords(List<String> words) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String firstWord = words.get(i);
                String secondWord = words.get(j);
                List<String> secondWordAsList = new ArrayList<>(Arrays.asList(secondWord.split("")));
                if (firstWord.length() == secondWordAsList.size()) {
                    boolean flag = true;
                    for (int k = 0; k < firstWord.length(); k++) {
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

    private static void printResult(Map<String, String> result) {
        if (result.isEmpty()) {
            System.out.println("There are no anagrams");
            return;
        }
        System.out.println("The Anagrams pair are:");
        result.keySet().forEach(key -> System.out.printf("%s - %s%n", key, result.get(key)));
        System.out.println();
    }
}
