package tron_bot;

import java.util.ArrayList;
import java.util.List;

public class TronBot {
    private int myXCoordinate;
    private int myYCoordinate;
    private char myHead;

    private int enemyXCoordinate;
    private int enemyYCoordinate;
    private char enemyHead;
    private char[][] currentParsedMap;

    TronBot(String color) {
        if (color.equals("red")) {
            myHead = 'R';
            enemyHead = 'B';
        } else {
            myHead = 'B';
            enemyHead = 'R';
        }
    }

    public char[][] getCurrentParsedMap() {
        return currentParsedMap;
    }

    public void setCurrentParsedMap(char[][] currentParsedMap) {
        this.currentParsedMap = currentParsedMap;
    }

    private void retrieveMyYCoordinate() {
        for (int i = 0; i < currentParsedMap.length; i++)
            for (int j = 0; j < currentParsedMap[0].length; j++)
                if (currentParsedMap[i][j] == myHead) {
                    myYCoordinate = i;
                    return;
                }
    }

    private void retrieveMyXCoordinate() {
        for (char[] chars : currentParsedMap)
            for (int j = 0; j < currentParsedMap[0].length; j++)
                if (chars[j] == myHead) {
                    myXCoordinate = j;
                    return;
                }
    }

    private void retrieveEnemyYCoordinate() {
        for (int i = 0; i < currentParsedMap.length; i++)
            for (int j = 0; j < currentParsedMap[0].length; j++)
                if (currentParsedMap[i][j] == enemyHead) {
                    enemyYCoordinate = i;
                    return;
                }
    }

    private void retrieveEnemyXCoordinate() {
        for (char[] chars : currentParsedMap)
            for (int j = 0; j < currentParsedMap[0].length; j++)
                if (chars[j] == enemyHead) {
                    enemyXCoordinate = j;
                    return;
                }
    }


    List<String> getEveryAvailableMove(char[][] map) {
        return new ArrayList<>();
    }

    int getEvaluationOfPosition(char[][] map) {
        return -1000;
    }

    char[][] getMapAfterMove(String move, char[][] mapToMakeMoveOn) {
        return new char[][]{{}};
    }

    char[][] getMapBeforeMove(String lastMove, char[][] mapToTakeMoveFrom) {
        return new char[][]{{}};
    }
}
