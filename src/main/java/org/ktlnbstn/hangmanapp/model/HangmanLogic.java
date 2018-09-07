//KATE'S UPDATED HMLogic
package org.ktlnbstn.hangmanapp.model;

import java.util.List;

public class HangmanLogic {

    // validate input, return true if
    public static boolean validateUserInput(String userInput, List<String> triedChars, String originalWord) {

        // turn input to lowercase
        String lowerCaseCharacter = userInput.toLowerCase();

        // check input is only 1 userInput
        if (lowerCaseCharacter.length() == 1) {

            // check if input is a valid char
            char alphaChar = lowerCaseCharacter.charAt(0);

            if (alphaChar >= 'a' && alphaChar <= 'z') {

                // else: check if input is already present in triedChars
                if (triedChars.contains(lowerCaseCharacter)) {

                    // if so, char is already present. break and go back to game
                    return false;
                } else {

                    // else: input is not a repeat, add string to triedChars
                    triedChars.add(lowerCaseCharacter);
                    return true;
                }
            }
            return false;
        } else {
            // check if multi-char userInput is actual originalWord
            if(originalWord.contains(lowerCaseCharacter)) {
                triedChars.add(lowerCaseCharacter);
                return true;
            }
            // false
            triedChars.add(lowerCaseCharacter);
        }
        return false;
    }

    // compare tries to letters in the originalWord string
    // returns a hiddenWord with underlines where unguessed letters hide
    public static String createHiddenWord(String originalWord, List<String> tries) {

        // turn tries into a char[]
//        String triesString = tries.toString().replaceAll(",", "");
//        char[] triesAsChars = triesString.substring(1, triesString.length()-1).replaceAll(" ", "").toCharArray();

        StringBuilder hiddenWord = new StringBuilder();

        // for each letter in originalWord,
        for (String originalWordLetter : originalWord.split("")) {

            boolean isFound = false;

            // if it matches a letter in tries,
            for (String triesAttempt : tries) {

                // if more than 1 letter, go to else, do not add to hiddenWordj
                if (triesAttempt.length() == 1) {

                    // if the "string" letters match in originalWord and the triesAttempt
                    if (originalWordLetter.equals(triesAttempt)) {

                        // add/reveal the letter in the hiddenWord
                        hiddenWord.append(triesAttempt).append(" ");
                        isFound = true;
                        break;
                    }
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

    public static int countBadTries(String originalWord, String userInput) {

        int badTries = 0;

        if(userInput.length() == 1) {

            // if string (of size 1) in not in originalWord, add to badTries
            if(!originalWord.contains(userInput)){
                badTries++;

            }
        } else{
            // if string is in originalWord, do not add to badTries
            if (!originalWord.equals(userInput)) {
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
