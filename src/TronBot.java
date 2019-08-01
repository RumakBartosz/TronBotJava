public class TronBot {
    int MyXCoordinate;
    int MyYCoordinate;
    char MyHead;

    int EnemyXCoordinate;
    int EnemyYCoordinate;
    char EnemyHead;

    char[][] CurrentParsedMap;

    String[] getEveryAvailableMove(char[][] Map) {
        return new String[]{"up", "down", "left", "right"};
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
