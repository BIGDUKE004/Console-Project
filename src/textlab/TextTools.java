package textlab;

import java.util.ArrayList;

class TextTools {

    static boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c);
    }

    static boolean isOneWord(String text) {
        if (text.length() == 0) return false;
        for (int i = 0; i < text.length(); i++) {
            if (!isWordChar(text.charAt(i))) return false;
        }
        return true;
    }

    static int countWithSpaces(String text) {
        return text.length();
    }

    static int countWithoutSpaces(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i))) count++;
        }
        return count;
    }


    static String cleanSpaces(String text) {
        StringBuilder result = new StringBuilder();
        boolean needSpace = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isWhitespace(c)) {
                if (result.length() > 0) needSpace = true;
            } else {
                if (needSpace) {
                    result.append(' ');
                    needSpace = false;
                }
                result.append(c);
            }
        }
        return result.toString();
    }

    static String[] getWords(String text) {
        StringBuilder spaced = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isWordChar(c)) {
                spaced.append(c);
            } else {
                spaced.append(' ');
            }
        }
        String cleaned = cleanSpaces(spaced.toString());
        if (cleaned.length() == 0) {
            return new String[0];   // no words
        }
        return cleaned.split(" ");
    }

    static int countWords(String text) {
        return getWords(text).length;
    }

    static int countSentences(String text) {
        int count = 0;
        boolean seenWord = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                if (seenWord) {
                    count++;
                    seenWord = false;
                }
            } else if (isWordChar(c)) {
                seenWord = true;
            }
        }
        if (seenWord) count++;
        return count;
    }

    static int countWord(String text, String word) {
        String[] words = getWords(text);
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(word)) count++;
        }
        return count;
    }

    static String replaceWord(String text, String target, String replacement) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isWordChar(c)) {
                word.append(c);
            } else {
                result.append(swap(word.toString(), target, replacement));
                word.setLength(0);
                result.append(c);
            }
        }
        result.append(swap(word.toString(), target, replacement));
        return result.toString();
    }

    static String swap(String word, String target, String replacement) {
        if (word.equalsIgnoreCase(target)) {
            return replacement;
        }
        return word;
    }

    static boolean isPalindrome(String text) {
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isWordChar(c)) letters.append(Character.toLowerCase(c));
        }
        if (letters.length() == 0) return false;

        String forward = letters.toString();
        String backward = letters.reverse().toString();
        return forward.equals(backward);
    }

    static ArrayList<String> longestWords(String text) {
        String[] words = getWords(text);

        int biggest = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > biggest) biggest = words[i].length();
        }

        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == biggest) {
                boolean alreadyAdded = false;
                for (int j = 0; j < result.size(); j++) {
                    if (result.get(j).equalsIgnoreCase(words[i])) alreadyAdded = true;
                }
                if (!alreadyAdded) result.add(words[i]);
            }
        }
        return result;
    }

    static int firstDifference(String a, String b) {
        int shortest = Math.min(a.length(), b.length());
        for (int i = 0; i < shortest; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        if (a.length() == b.length()) return -1;
        return shortest;
    }
}

