package tron_bot;//TODO: REFACTOR, consider matrix sizes, why are gets public

import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class MapParser {

    @Contract(pure = true)
    private boolean isDigit(char characterToBeChecked) {
        return characterToBeChecked >= '0' && characterToBeChecked <= '9';
    }

    int getMapXSize(String processedMoveString) {
        int xSize = 0;
        for (int i = 0; i < processedMoveString.length(); i++) {
            if (processedMoveString.charAt(i) == '/') {
                xSize = i;
                break;
            }
        }
        return xSize;
    }

    int getMapYSize(String processedMoveString) {
        int ySize = 0;
        for (int i = 0; i < processedMoveString.length(); i++) {
            if (processedMoveString.charAt(i) == '/') {
                ySize++;
            }
        }
        return ySize + 1;
    }

    public String processThisMove(String move) {

        StringBuilder changedString = new StringBuilder(move);

        for (int i = 0; i < changedString.length(); i++) {
            if (isDigit(changedString.charAt(i))) {
                //if number is greater than 9
                if (isDigit(changedString.charAt(i + 1))) {

                    //get number
                    int numberOfSpaces = (changedString.charAt(i) - '0') * 10 - +(changedString.charAt(i + 1) - '0');

                    //delete digits at index; repeated, as characters contract at deletion
                    changedString.deleteCharAt(i);
                    changedString.deleteCharAt(i);

                    //fill with spaces number of times
                    char[] repeatedSpaces = new char[numberOfSpaces];
                    Arrays.fill(repeatedSpaces, ' ');

                    changedString.insert(i, repeatedSpaces);

                    //as we are jumping 2 positions in string
                    i++;
                } else {
                    int numberOfSpaces = changedString.charAt(i) - '0';

                    changedString.deleteCharAt(i);

                    char[] repeatedSpaces = new char[numberOfSpaces];
                    Arrays.fill(repeatedSpaces, ' ');

                    changedString.insert(i, repeatedSpaces);

                }
            }
        }

        return changedString.toString();
    }

    public char[][] parseTheMap(int height, int width, String preprocessedMove) {

        char[][] theMap = new char[height][width];


        int moveIndex = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (moveIndex < preprocessedMove.length()) {
                    if (preprocessedMove.charAt(moveIndex) == 'o') {
                        theMap[i][j] = 'o';
                        moveIndex++;
                    }

                    //set other elements
                    else if (preprocessedMove.charAt(moveIndex) == 'r' || preprocessedMove.charAt(moveIndex) == 'R'
                            || preprocessedMove.charAt(moveIndex) == 'b' || preprocessedMove.charAt(moveIndex) == 'B'
                            || preprocessedMove.charAt(moveIndex) == ' ') {
                        theMap[i][j] = preprocessedMove.charAt(moveIndex);
                        moveIndex++;
                    }


                    //ignore '/'
                    else if (preprocessedMove.charAt(moveIndex) == '/') {
                        moveIndex++;

                        j--;
                    }
                }

            }
        }

        return theMap;
    }
}
