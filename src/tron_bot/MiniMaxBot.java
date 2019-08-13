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

    void move(String whatMove) {

        if (whoseMoveItIs == WhoseMove.ME) {

            mapState[myCoordinates.y][myCoordinates.x] =
                    Character.toLowerCase(mapState[myCoordinates.y][myCoordinates.x]);

            switch (whatMove) {
                case "up":
                    mapState[myCoordinates.y - 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y -= 1;
                    break;
                case "down":
                    mapState[myCoordinates.y + 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y += 1;
                    break;
                case "left":
                    mapState[myCoordinates.y][myCoordinates.x - 1] = myCoordinates.color;
                    myCoordinates.x -= 1;
                    break;
                case "right":
                    mapState[myCoordinates.y][myCoordinates.x + 1] = myCoordinates.color;
                    myCoordinates.y += 1;
                    break;
                default:
                    System.out.println("Unknown move code at move function " + whatMove);
                    break;
            }

            whoseMoveItIs = WhoseMove.ENEMY;
        } else {
            mapState[enemyCoordinates.y][enemyCoordinates.x] =
                    Character.toLowerCase(mapState[enemyCoordinates.y][enemyCoordinates.x]);

            switch (whatMove) {
                case "up":
                    mapState[enemyCoordinates.y - 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y -= 1;
                    break;
                case "down":
                    mapState[enemyCoordinates.y + 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y += 1;
                    break;
                case "left":
                    mapState[enemyCoordinates.y][enemyCoordinates.x - 1] = enemyCoordinates.color;
                    enemyCoordinates.x -= 1;
                    break;
                case "right":
                    mapState[enemyCoordinates.y][enemyCoordinates.x + 1] = enemyCoordinates.color;
                    enemyCoordinates.x += 1;
                    break;
                default:
                    System.out.println("Unknown move code at move function " + whatMove);
            }

            whoseMoveItIs = WhoseMove.ME;
        }
    }

    void unmove(String whatMove) {

        if (whoseMoveItIs == WhoseMove.ME) {

            mapState[myCoordinates.y][myCoordinates.x] = ' ';

            switch (whatMove) {
                case "up":
                    mapState[myCoordinates.y + 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y += 1;
                    break;
                case "down":
                    mapState[myCoordinates.y - 1][myCoordinates.x] = myCoordinates.color;
                    myCoordinates.y -= 1;
                    break;
                case "left":
                    mapState[myCoordinates.y][myCoordinates.x + 1] = myCoordinates.color;
                    myCoordinates.x += 1;
                    break;
                case "right":
                    mapState[myCoordinates.y][myCoordinates.x - 1] = myCoordinates.color;
                    myCoordinates.x -= 1;
                    break;
                default:
                    System.out.println("Unknown move code at move function " + whatMove);
                    break;
            }

            whoseMoveItIs = WhoseMove.ENEMY;
        } else {
            mapState[enemyCoordinates.y][enemyCoordinates.x] = ' ';

            switch (whatMove) {
                case "up":
                    mapState[enemyCoordinates.y + 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y += 1;
                    break;
                case "down":
                    mapState[enemyCoordinates.y - 1][enemyCoordinates.x] = enemyCoordinates.color;
                    enemyCoordinates.y -= 1;
                    break;
                case "left":
                    mapState[enemyCoordinates.y][enemyCoordinates.x + 1] = enemyCoordinates.color;
                    enemyCoordinates.x += 1;
                    break;
                case "right":
                    mapState[enemyCoordinates.y][enemyCoordinates.x - 1] = enemyCoordinates.color;
                    enemyCoordinates.x -= 1;
                    break;
                default:
                    System.out.println("Unknown move code at move function " + whatMove);
            }

            whoseMoveItIs = WhoseMove.ME;
        }
    }

    int evaluation() {
        if (isTheGameOver())
            return -1000;
        else
            return 0;
    }


    int minimize(int ply) {
        ///function returns worst value of an evaluation in the game tree
        ///of ply depth
        int worst = 1000;

        if (ply == 0 || isTheGameOver())
            return evaluation();

        List<String> whichMovesArePossible = getEveryPossibleMove();

        for (String currentMove : whichMovesArePossible) {
            move(currentMove);
            int current = maximize(ply - 1);
            unmove(currentMove);

            if (current < worst)
                worst = current;
        }

        return worst;
    }


    int maximize(int ply) {
        ///function returns best value of an evaluation in the game tree
        ///of ply depth
        if (ply == 0 || isTheGameOver())
            return evaluation();

        int best = -1000;

        List<String> whichMovesArePossible = getEveryPossibleMove();

        for (String currentMove : whichMovesArePossible) {
            move(currentMove);
            int current = minimize(ply - 1);
            unmove(currentMove);

            if (current > best)
                best = current;
        }

        return best;

    }


    String whichMoveShallITake(int ply) {
        String bestMove = "up";
        int bestValue = -1000;

        List<String> whichMovesArePossible = getEveryPossibleMove();

        for (String move : whichMovesArePossible) {
            //makeMove from a currentMove
            //maximize(ply)
            //takeBackMove from a currentMove
            //if maximize(ply) better than move, change value of bestValue
            //  and move = bestMove
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
}
