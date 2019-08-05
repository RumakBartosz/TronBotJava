package tron_bot;
//TODO: add cognition of whose turn it is as a boolean value, cojoin functions as a violation of DRY

import org.jetbrains.annotations.NotNull;

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

    //Check only our available moves, and decide as a mini max
    List<String> getOurEveryAvailableMove(@NotNull char[][] map) {
        List<String> moveList = new ArrayList<>();

        if (map[myYCoordinate - 1][myXCoordinate] == ' ')
            moveList.add("up");

        if (map[myYCoordinate + 1][myXCoordinate] == ' ')
            moveList.add("down");

        if (map[myYCoordinate][myXCoordinate - 1] == ' ')
            moveList.add("left");

        if (map[myYCoordinate][myXCoordinate + 1] == ' ')
            moveList.add("right");
        return moveList;
    }

    List<String> getTheirsEveryAvailableMove(@NotNull char[][] map) {
        List<String> moveList = new ArrayList<>();

        if (map[enemyYCoordinate - 1][enemyXCoordinate] == ' ')
            moveList.add("up");

        if (map[enemyYCoordinate + 1][enemyXCoordinate] == ' ')
            moveList.add("down");

        if (map[enemyYCoordinate][enemyXCoordinate - 1] == ' ')
            moveList.add("left");

        if (map[enemyYCoordinate][enemyXCoordinate + 1] == ' ')
            moveList.add("right");
        return moveList;
    }

    int getEvaluationOfPosition(char[][] map) {
        //when map is open, prefer going closer to other bot, as it makes sure that you'll cut as much as you can
        //when map is closed, count difference in the number of available places
        //range: [-100, 100]
        return -100;
    }

    char[][] getMapAfterOurMove(@NotNull char[][] mapToMakeMoveOn, @NotNull String move) {
        ///return mapToMakeMoveOn, for move String with value either "up" or "down" or "left" or "right"
        ///where you made according move
        ///assume move is correct, as this operation takes considerable amount of time in MiniMax

        //SRP broken?

        mapToMakeMoveOn[myYCoordinate][myXCoordinate] =
                Character.toLowerCase(mapToMakeMoveOn[myYCoordinate][myXCoordinate]);

        switch (move) {
            case "up":
                mapToMakeMoveOn[myYCoordinate - 1][myXCoordinate] = myHead;
                myYCoordinate -= 1;
                break;
            case "down":
                mapToMakeMoveOn[myYCoordinate + 1][myXCoordinate] = myHead;
                myYCoordinate += 1;
                break;
            case "left":
                mapToMakeMoveOn[myYCoordinate][myXCoordinate - 1] = myHead;
                myXCoordinate -= 1;
                break;
            case "right":
                mapToMakeMoveOn[myYCoordinate][myXCoordinate + 1] = myHead;
                myXCoordinate += 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterOurMove()");
        }
        return mapToMakeMoveOn;
    }

    char[][] getMapAfterTheirsMove(@NotNull char[][] mapToMakeMoveOn, @NotNull String move) {
        ///return mapToMakeMoveOn, for move String with value either "up" or "down" or "left" or "right"
        ///where you made according move
        ///assume move is correct, as this operation takes considerable amount of time in MiniMax

        mapToMakeMoveOn[myYCoordinate][myXCoordinate] =
                Character.toLowerCase(mapToMakeMoveOn[myYCoordinate][myXCoordinate]);

        switch (move) {
            case "up":
                mapToMakeMoveOn[myYCoordinate - 1][myXCoordinate] = enemyHead;
                myYCoordinate -= 1;
                break;
            case "down":
                mapToMakeMoveOn[myYCoordinate + 1][myXCoordinate] = enemyHead;
                myYCoordinate += 1;
                break;
            case "left":
                mapToMakeMoveOn[myYCoordinate][myXCoordinate - 1] = enemyHead;
                myXCoordinate -= 1;
                break;
            case "right":
                mapToMakeMoveOn[myYCoordinate][myXCoordinate + 1] = enemyHead;
                myXCoordinate += 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterOurMove()");
        }
        return mapToMakeMoveOn;
    }

    char[][] getMapBeforeOurMove(@NotNull String lastMove, @NotNull char[][] mapToTakeMoveFrom) {
        //return mapToTakeMoveFrom, for lastMove String with value either "up" or "down" or "left" or "right"
        //where you've taken back according move
        //assume move is correct, as this operation takes considerable amount of time in MiniMax

        mapToTakeMoveFrom[myYCoordinate][myXCoordinate] = ' ';

        switch (lastMove) {
            case "up":
                mapToTakeMoveFrom[myYCoordinate + 1][myXCoordinate] = myHead;
                myYCoordinate += 1;
                break;
            case "down":
                mapToTakeMoveFrom[myYCoordinate - 1][myXCoordinate] = myHead;
                myYCoordinate -= 1;
                break;
            case "left":
                mapToTakeMoveFrom[myYCoordinate][myXCoordinate + 1] = myHead;
                myXCoordinate += 1;
                break;
            case "right":
                mapToTakeMoveFrom[myYCoordinate][myXCoordinate - 1] = myHead;
                myXCoordinate -= 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterOurMove()");
        }
        return mapToTakeMoveFrom;
    }

    char[][] getMapBeforeTheirsMove(@NotNull String lastMove, @NotNull char[][] mapToTakeMoveFrom) {
        //return mapToTakeMoveFrom, for lastMove String with value either "up" or "down" or "left" or "right"
        //where you've taken back according move
        //assume move is correct, as this operation takes considerable amount of time in MiniMax

        mapToTakeMoveFrom[myYCoordinate][myXCoordinate] = ' ';

        switch (lastMove) {
            case "up":
                mapToTakeMoveFrom[myYCoordinate + 1][myXCoordinate] = enemyHead;
                myYCoordinate += 1;
                break;
            case "down":
                mapToTakeMoveFrom[myYCoordinate - 1][myXCoordinate] = enemyHead;
                myYCoordinate -= 1;
                break;
            case "left":
                mapToTakeMoveFrom[myYCoordinate][myXCoordinate + 1] = enemyHead;
                myXCoordinate += 1;
                break;
            case "right":
                mapToTakeMoveFrom[myYCoordinate][myXCoordinate - 1] = enemyHead;
                myXCoordinate -= 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterOurMove()");
        }
        return mapToTakeMoveFrom;
    }
}
