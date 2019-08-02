import java.util.ArrayList;
import java.util.List;

public class TronBot {
    private int MyXCoordinate;
    private int MyYCoordinate;
    private char MyHead;

    private int EnemyXCoordinate;
    private int EnemyYCoordinate;
    private char EnemyHead;
    private char[][] CurrentParsedMap;

    TronBot(String color) {
        if (color.equals("red")) {
            MyHead = 'R';
            EnemyHead = 'B';
        } else {
            MyHead = 'B';
            EnemyHead = 'R';
        }
    }

    public char[][] getCurrentParsedMap() {
        return CurrentParsedMap;
    }

    public void setCurrentParsedMap(char[][] currentParsedMap) {
        CurrentParsedMap = currentParsedMap;
    }

    private void retrieveMyYCoordinate() {
        for (int i = 0; i < CurrentParsedMap.length; i++)
            for (int j = 0; j < CurrentParsedMap[0].length; j++)
                if (CurrentParsedMap[i][j] == MyHead) {
                    MyYCoordinate = i;
                    return;
                }
    }

    private void retrieveMyXCoordinate() {
        for (char[] chars : CurrentParsedMap)
            for (int j = 0; j < CurrentParsedMap[0].length; j++)
                if (chars[j] == MyHead) {
                    MyXCoordinate = j;
                    return;
                }
    }

    private void retrieveEnemyYCoordinate() {
        for (int i = 0; i < CurrentParsedMap.length; i++)
            for (int j = 0; j < CurrentParsedMap[0].length; j++)
                if (CurrentParsedMap[i][j] == EnemyHead) {
                    EnemyYCoordinate = i;
                    return;
                }
    }

    private void retrieveEnemyXCoordinate() {
        for (char[] chars : CurrentParsedMap)
            for (int j = 0; j < CurrentParsedMap[0].length; j++)
                if (chars[j] == EnemyHead) {
                    EnemyXCoordinate = j;
                    return;
                }
    }


    List<String> getEveryAvailableMove(char[][] Map) {
        return new ArrayList<>();
    }

    int getEvaluationOfPosition(char[][] Map) {
        return -1000;
    }

    char[][] getMapAfterMove(String move, char[][] mapToMakeMoveOn) {
        return new char[][]{{}};
    }

    char[][] getMapBeforeMove(String lastMove, char[][] mapToTakeMoveFrom) {
        return new char[][]{{}};
    }
}
