package tron_bot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MiniMaxBot {
    static final String MOVE_UP = "up";
    static final String MOVE_DOWN = "down";
    static final String MOVE_LEFT = "left";
    static final String MOVE_RIGHT = "right";
    static final char HEAD_RED = 'R';
    static final char HEAD_BLUE = 'B';
    static final String ERROR_CODE = "Unknown move code at move function: ";

    Coordinates myCoordinates = new Coordinates();
    Coordinates enemyCoordinates = new Coordinates();

    void setMapState(char[][] mapState) {
        this.mapState = mapState;
        setCoordinates();
    }

    char[][] mapState;

    WhoseMove whoseMoveItIs;

    MiniMaxBot(char[][] mapState, @NotNull String color) {
        this.mapState = mapState;
        whoseMoveItIs = WhoseMove.ME;

        if (color.equals("red")) {
            myCoordinates.color = HEAD_RED;
            enemyCoordinates.color = HEAD_BLUE;
        } else {
            myCoordinates.color = HEAD_BLUE;
            enemyCoordinates.color = HEAD_RED;
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

    //bounds check probably not needed
    boolean isMoveUpPossible() {

        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            if (myCoordinates.y <= 0)
                return false;
            return mapState[myCoordinates.y - 1][myCoordinates.x] == ' ';
        } else {
            if (enemyCoordinates.y <= 0)
                return false;
            return mapState[enemyCoordinates.y - 1][enemyCoordinates.x] == ' ';
        }
    }

    boolean isMoveDownPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            if (myCoordinates.y >= mapState.length - 1)
                return false;
            return mapState[myCoordinates.y + 1][myCoordinates.x] == ' ';
        } else {
            if (enemyCoordinates.y >= mapState.length - 1)
                return false;
            return mapState[enemyCoordinates.y + 1][enemyCoordinates.x] == ' ';
        }
    }

    boolean isMoveLeftPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            if (myCoordinates.x <= 0)
                return false;
            return mapState[myCoordinates.y][myCoordinates.x - 1] == ' ';
        } else {
            if (enemyCoordinates.x <= 0)
                return false;
            return mapState[enemyCoordinates.y][enemyCoordinates.x - 1] == ' ';
        }
    }

    boolean isMoveRightPossible() {
        if (whoseMoveItIs.equals(WhoseMove.ME)) {
            if (myCoordinates.x >= mapState[0].length - 1)
                return false;
            return mapState[myCoordinates.y][myCoordinates.x + 1] == ' ';
        } else {
            if (enemyCoordinates.x >= mapState[0].length - 1)
                return false;
            return mapState[enemyCoordinates.y][enemyCoordinates.x + 1] == ' ';
        }
    }

    List<String> getEveryPossibleMove() {
        List<String> moveList = new ArrayList<>();

        if (isMoveUpPossible())
            moveList.add(MOVE_UP);
        if (isMoveDownPossible())
            moveList.add(MOVE_DOWN);
        if (isMoveLeftPossible())
            moveList.add(MOVE_LEFT);
        if (isMoveRightPossible())
            moveList.add(MOVE_RIGHT);

        return moveList;

    }

    boolean isTheGameOver() {
        return getEveryPossibleMove().isEmpty();
    }

    void move(String whatMove) {
        setCoordinates();
        if (whoseMoveItIs == WhoseMove.ME) {

            mapState[myCoordinates.y][myCoordinates.x] =
                    Character.toLowerCase(mapState[myCoordinates.y][myCoordinates.x]);

            switch (whatMove) {
                case MOVE_UP:
                    mapState[myCoordinates.y - 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y -= 1;
                    break;
                case MOVE_DOWN:
                    mapState[myCoordinates.y + 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y += 1;
                    break;
                case MOVE_LEFT:
                    mapState[myCoordinates.y][myCoordinates.x - 1] = myCoordinates.color;
                    myCoordinates.x -= 1;
                    break;
                case MOVE_RIGHT:
                    mapState[myCoordinates.y][myCoordinates.x + 1] = myCoordinates.color;
                    myCoordinates.x += 1;
                    break;
                default:
                    System.out.println(ERROR_CODE + whatMove);
                    break;
            }

            whoseMoveItIs = WhoseMove.ENEMY;
        } else {
            mapState[enemyCoordinates.y][enemyCoordinates.x] =
                    Character.toLowerCase(mapState[enemyCoordinates.y][enemyCoordinates.x]);

            switch (whatMove) {
                case MOVE_UP:
                    mapState[enemyCoordinates.y - 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y -= 1;
                    break;
                case MOVE_DOWN:
                    mapState[enemyCoordinates.y + 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y += 1;
                    break;
                case MOVE_LEFT:
                    mapState[enemyCoordinates.y][enemyCoordinates.x - 1] = enemyCoordinates.color;
                    enemyCoordinates.x -= 1;
                    break;
                case MOVE_RIGHT:
                    mapState[enemyCoordinates.y][enemyCoordinates.x + 1] = enemyCoordinates.color;
                    enemyCoordinates.x += 1;
                    break;
                default:
                    System.out.println(ERROR_CODE + whatMove);
            }

            whoseMoveItIs = WhoseMove.ME;
        }


    }

    void debugPrintMap(@NotNull char[][] map) {
        for (char[] chars : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void unmove(String whatMove) {

        if (whoseMoveItIs == WhoseMove.ENEMY) {

            mapState[myCoordinates.y][myCoordinates.x] = ' ';

            switch (whatMove) {
                case MOVE_UP:
                    mapState[myCoordinates.y + 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y += 1;
                    break;
                case MOVE_DOWN:
                    mapState[myCoordinates.y - 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y -= 1;
                    break;
                case MOVE_LEFT:
                    mapState[myCoordinates.y][myCoordinates.x + 1] = myCoordinates.color;
                    myCoordinates.x += 1;
                    break;
                case MOVE_RIGHT:
                    mapState[myCoordinates.y][myCoordinates.x - 1] = myCoordinates.color;
                    myCoordinates.x -= 1;
                    break;
                default:
                    System.out.println(ERROR_CODE + whatMove);
                    break;
            }

            whoseMoveItIs = WhoseMove.ME;
        } else {
            mapState[enemyCoordinates.y][enemyCoordinates.x] = ' ';

            switch (whatMove) {
                case MOVE_UP:
                    mapState[enemyCoordinates.y + 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y += 1;
                    break;
                case MOVE_DOWN:
                    mapState[enemyCoordinates.y - 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y -= 1;
                    break;
                case MOVE_LEFT:
                    mapState[enemyCoordinates.y][enemyCoordinates.x + 1] = enemyCoordinates.color;
                    enemyCoordinates.x += 1;
                    break;
                case MOVE_RIGHT:
                    mapState[enemyCoordinates.y][enemyCoordinates.x - 1] = enemyCoordinates.color;
                    enemyCoordinates.x -= 1;
                    break;
                default:
                    System.out.println(ERROR_CODE + whatMove);
            }

            whoseMoveItIs = WhoseMove.ENEMY;
        }
    }

    int evaluation() {
        if (isTheGameOver())
            return whoseMoveItIs == WhoseMove.ME ? -1000 : 1000;
        else
            return 0;
    }


    int minimize(int ply) {
        int worst = 1000;

        if (ply == 0 || isTheGameOver())
            return evaluation();

        List<String> whichMovesArePossibleAtMinimize = getEveryPossibleMove();

        for (String currentMove : whichMovesArePossibleAtMinimize) {
            move(currentMove);
            int current = maximize(ply - 1);
            unmove(currentMove);

            if (current < worst)
                worst = current;
        }

        return worst;
    }


    int maximize(int ply) {
        if (ply == 0 || isTheGameOver())
            return evaluation();

        int best = -1000;

        List<String> whichMovesArePossibleAtMaximize = getEveryPossibleMove();

        for (String currentMove : whichMovesArePossibleAtMaximize) {
            move(currentMove);
            int current = minimize(ply - 1);
            unmove(currentMove);

            if (current > best)
                best = current;
        }

        return best;

    }


    String whichMoveShallITake(int ply) {
        int bestValue = -1000;

        List<String> whichMovesArePossible = getEveryPossibleMove();

        String bestMove = whichMovesArePossible.get(0);

        for (String move : whichMovesArePossible) {
            int current = -1001;


            if (whoseMoveItIs == WhoseMove.ME)
                current = maximize(ply - 1);
            else if (whoseMoveItIs == WhoseMove.ENEMY)
                current = minimize(ply - 1);
            else
                System.out.println("Error, nobody's move at whichMoveShallITake at ply " + ply);

            if (current > bestValue) {
                bestValue = current;
                bestMove = move;
            }
        }
        return bestMove;
    }


    //This is O(n^2), could be O(n log n)
    //add tests
    //m = my Tron is closer, e = enemy is closer
    char[][] getVoronoiDiagram() {
        char[][] voronoi = new char[mapState.length][mapState[0].length];
        for (int i = 0; i < mapState.length; i++)
            for (int j = 0; j < mapState[0].length; j++)
                if (amICloserToThisPlace(i, j)) {
                    voronoi[i][j] = 'm';
                } else {
                    voronoi[i][j] = 'e';
                }
        return voronoi;
    }

    int getMyReachAmount() {
        int numberOfPlacesICanReach = 0;
        char[][] voronoiState = getVoronoiDiagram();
        for (int i = 0; i < voronoiState.length; i++)
            for (int j = 0; j < voronoiState[0].length; j++)
                if (voronoiState[i][j] == 'm') {
                    numberOfPlacesICanReach++;
                }
        return numberOfPlacesICanReach;
    }

    int getEnemyReachAmount() {
        return mapState.length * mapState[0].length - getMyReachAmount();
    }

    boolean amICloserToThisPlace(int x, int y) {
        double myDistance = Math.sqrt(Math.pow((double) x - myCoordinates.x, 2) +
                Math.pow((double) y - myCoordinates.y, 2));
        double enemyDistance = Math.sqrt(Math.pow((double) x - enemyCoordinates.y, 2) +
                Math.pow((double) y - enemyCoordinates.y, 2));

        return myDistance <= enemyDistance;
    }
}
