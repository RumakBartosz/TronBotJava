package tron_bot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxBot {

    Coordinates myCoordinates = new Coordinates();
    Coordinates enemyCoordinates = new Coordinates();

    char[][] mapState;

    WhoseMove whoseMoveItIs;

    MiniMaxBot(char[][] mapState, @NotNull String color) {
        this.mapState = mapState;
        whoseMoveItIs = WhoseMove.ME;

        if (color.equals("red")) {
            myCoordinates.color = 'R';
            enemyCoordinates.color = 'B';
        } else {
            myCoordinates.color = 'B';
            enemyCoordinates.color = 'R';
        }

        setCoordinates();
    }

    public void retrieveMyYCoordinate() {
        for (int i = 0; i < mapState.length; i++)
            for (int j = 0; j < mapState[0].length; j++)
                if (mapState[i][j] == myCoordinates.color) {
                    myCoordinates.y = i;
                    return;
                }
    }

    public void retrieveMyXCoordinate() {
        for (char[] chars : mapState)
            for (int j = 0; j < mapState[0].length; j++)
                if (chars[j] == myCoordinates.color) {
                    myCoordinates.x = j;
                    return;
                }
    }

    public void retrieveEnemyYCoordinate() {
        for (int i = 0; i < mapState.length; i++)
            for (int j = 0; j < mapState[0].length; j++)
                if (mapState[i][j] == enemyCoordinates.color) {
                    enemyCoordinates.y = i;
                    return;
                }
    }

    public void retrieveEnemyXCoordinate() {
        for (char[] chars : mapState)
            for (int j = 0; j < mapState[0].length; j++)
                if (chars[j] == enemyCoordinates.color) {
                    enemyCoordinates.x = j;
                    return;
                }
    }

    void setCoordinates() {
        retrieveEnemyXCoordinate();
        retrieveEnemyYCoordinate();
        retrieveMyXCoordinate();
        retrieveMyYCoordinate();
    }

    boolean isMoveUpPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            return mapState[myCoordinates.y - 1][myCoordinates.x] == ' ';
        } else {
            return mapState[enemyCoordinates.y - 1][enemyCoordinates.x] == ' ';
        }
    }

    boolean isMoveDownPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            return mapState[myCoordinates.y + 1][myCoordinates.x] == ' ';
        } else {
            return mapState[enemyCoordinates.y + 1][enemyCoordinates.x] == ' ';
        }
    }

    boolean isMoveLeftPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            return mapState[myCoordinates.y][myCoordinates.x - 1] == ' ';
        } else {
            return mapState[enemyCoordinates.y][enemyCoordinates.x - 1] == ' ';
        }
    }

    boolean isMoveRightPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            return mapState[myCoordinates.y][myCoordinates.x + 1] == ' ';
        } else {
            return mapState[enemyCoordinates.y][enemyCoordinates.x + 1] == ' ';
        }
    }

    List<String> getEveryPossibleMove() {
        List<String> moveList = new ArrayList<>();

        if (isMoveUpPossible())
            moveList.add("up");
        if (isMoveDownPossible())
            moveList.add("down");
        if (isMoveLeftPossible())
            moveList.add("left");
        if (isMoveRightPossible())
            moveList.add("right");

        return moveList;

    }

    boolean isTheGameOver() {
        return getEveryPossibleMove().isEmpty();
    }

}
