package org.ktlnbstn.hangmanapp.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HangmanLogic {

    // validate input, return true if
    public static boolean validateUserInput(String character, List<String> triedChars) {

        // check input is only 1 character
        if (character.length() == 1) {

            // turn input to lowercase
            String lowerCaseCharacter = character.toLowerCase();

            // check if input is a valid char
            char alphaChar = lowerCaseCharacter.charAt(0);

            if (alphaChar >= 'a' && alphaChar <= 'z') {

                // else: check if input char is already present in triedChars
                if (triedChars.contains(lowerCaseCharacter)) {

                    // if so, char is already present. break and go back to game
                    return false;
                } else {

                    // else: input is not a repeat, add char to tries array
                    triedChars.add(lowerCaseCharacter);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // compare tries to letters in the originalWord string
    // returns a hiddenWord with underlines where unguessed letters hide
    public static String createHiddenWord(String originalWord, List<String> tries) {

        // turn tries into a char[]
        String triesString = tries.toString().replaceAll(",", "");
        char[] triesAsChars = triesString.substring(1, triesString.length()-1).replaceAll(" ", "").toCharArray();

        StringBuilder hiddenWord = new StringBuilder();

        // if char in originalWord,
        for (char c : originalWord.toCharArray()) {

            boolean isFound = false;

            // if it matches a char in tries,
            for (int i = 0; i < triesAsChars.length; i++) {

                if (triesAsChars[i] == c) {

                    // add/reveal the char in the hiddenWord
                    hiddenWord.append(Character.toString(c)).append(" ");
                    isFound = true;
                    break;
                }

            }

            if(!isFound) {
                // else, if the char in originalWord does not match a char in tries,
                // replace it with underline
                hiddenWord.append("_ ");
            }
        }
        return hiddenWord.toString();
    }

    public static int countBadTries(String originalWord, List<String> tries) {

        // turn originalWord into unique char set
        String[] originalWordIntoArray = originalWord.split("");
        Set<String> originalWordIntoLetters = new HashSet<>(Arrays.asList(originalWordIntoArray));
        int badTries = 0;

        // if char in originalWord,
        for (String trie : tries) {

            // if not present, a bad guess
            if (!originalWordIntoLetters.contains(trie)) {
                badTries++;
            }
        }

        return badTries;
    }

    // check if word is complete:
    public static boolean checkIfCompleteWord(String originalWord, String hiddenWord) {

        String[] splitHiddenWord = hiddenWord.split(" ");
        String wholeHiddenWord = String.join("", splitHiddenWord);

        return wholeHiddenWord.equals(originalWord);
    }

    // basic score calculation
    public static int calculateScore(Game game) {
        return (game.getDifficulty() * 2) + game.getWord().length();
    }

}
