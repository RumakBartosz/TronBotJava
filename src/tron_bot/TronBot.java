package tron_bot;
//TODO: add cognition of whose turn it is as a boolean value, cojoin functions as a violation of DRY
//TODO: add tests to every function
//TODO: change currentParsedMap to ,,mapIAmAwareOf'' -- global map state instead of current
//TODO: UP, DOWN, LEFT, RIGHT enum

import org.jetbrains.annotations.Contract;
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

    char whoseMoveItIs;

    @Contract(pure = true)
    TronBot(@NotNull String color) {
        if (color.equals("red")) {
            myHead = 'R';
            enemyHead = 'B';
        } else {
            myHead = 'B';
            enemyHead = 'R';
        }

        setWhoseMoveItIs('R');
    }

    public char getWhoseMoveItIs() {
        return whoseMoveItIs;
    }

    public void setWhoseMoveItIs(char whoseMoveItIs) {
        this.whoseMoveItIs = whoseMoveItIs;
    }

    void setCurrentParsedMap(char[][] currentParsedMap) {
        this.currentParsedMap = currentParsedMap;
    }

    public void retrieveMyYCoordinate(char[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                if (map[i][j] == myHead) {
                    myYCoordinate = i;
                    return;
                }
    }

    public void retrieveMyXCoordinate(char[][] map) {
        for (char[] chars : map)
            for (int j = 0; j < map[0].length; j++)
                if (chars[j] == myHead) {
                    myXCoordinate = j;
                    return;
                }
    }

    public void retrieveEnemyYCoordinate(char[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                if (map[i][j] == enemyHead) {
                    enemyYCoordinate = i;
                    return;
                }
    }

    public void retrieveEnemyXCoordinate(char[][] map) {
        for (char[] chars : map)
            for (int j = 0; j < map[0].length; j++)
                if (chars[j] == enemyHead) {
                    enemyXCoordinate = j;
                    return;
                }
    }

    void retrieveAllCoordinates(char[][] map) {
        retrieveEnemyXCoordinate(map);
        retrieveEnemyYCoordinate(map);
        retrieveMyXCoordinate(map);
        retrieveMyYCoordinate(map);
    }

    //Check only our available moves, and decide as a mini max
    public List<String> getOurEveryAvailableMove(@NotNull char[][] map) {
        //why do coordinates violate map bounds when we aren't checking?
        List<String> moveList = new ArrayList<>();
        retrieveAllCoordinates(map);
        if (myYCoordinate > 0 && myXCoordinate >= 0 && map[myYCoordinate - 1][myXCoordinate] == ' ')
            moveList.add("up");

        if (myYCoordinate < map.length && myXCoordinate <= map[0].length && map[myYCoordinate + 1][myXCoordinate] == ' ')
            moveList.add("down");

        if (myYCoordinate >= 0 && myXCoordinate > 0 && map[myYCoordinate][myXCoordinate - 1] == ' ')
            moveList.add("left");

        if (myYCoordinate <= map.length && myXCoordinate < map[0].length && map[myYCoordinate][myXCoordinate + 1] == ' ')
            moveList.add("right");
        return moveList;
    }

    List<String> getTheirsEveryAvailableMove(@NotNull char[][] map) {
        List<String> moveList = new ArrayList<>();
        retrieveAllCoordinates(map);
        if (enemyYCoordinate > 0 && enemyXCoordinate >= 0 && map[enemyYCoordinate - 1][enemyXCoordinate] == ' ')
            moveList.add("up");

        if (enemyYCoordinate < map.length && enemyXCoordinate <= map[0].length && map[enemyYCoordinate + 1][enemyXCoordinate] == ' ')
            moveList.add("down");

        if (enemyYCoordinate >= 0 && enemyXCoordinate > 0 && map[enemyYCoordinate][enemyXCoordinate - 1] == ' ')
            moveList.add("left");

        if (enemyYCoordinate <= map.length && enemyXCoordinate < map[0].length - 1 && map[enemyYCoordinate][enemyXCoordinate + 1] == ' ')
            moveList.add("right");
        return moveList;
    }

    int getEvaluationOfPosition(char[][] map) {
        //when map is open, prefer going closer to other bot, as it makes sure that you'll cut as much as you can
        //when map is closed, count difference in the number of available places
        //range: [-100, 100]
        return -1000;
    }

    char[][] getMapAfterOurMove(@NotNull char[][] mapToMakeMoveOn, @NotNull String move) {
        ///return mapToMakeMoveOn, for move String with value either "up" or "down" or "left" or "right"
        ///where you made according move
        ///assume move is correct, as this operation takes considerable amount of time in MiniMax

        //SRP broken?
        retrieveAllCoordinates(mapToMakeMoveOn);
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
        retrieveAllCoordinates(mapToMakeMoveOn);
        mapToMakeMoveOn[enemyYCoordinate][enemyXCoordinate] =
                Character.toLowerCase(mapToMakeMoveOn[enemyYCoordinate][enemyXCoordinate]);

        switch (move) {
            case "up":
                mapToMakeMoveOn[enemyYCoordinate - 1][enemyXCoordinate] = enemyHead;
                enemyYCoordinate -= 1;
                break;
            case "down":
                mapToMakeMoveOn[enemyYCoordinate + 1][enemyXCoordinate] = enemyHead;
                enemyYCoordinate += 1;
                break;
            case "left":
                mapToMakeMoveOn[enemyYCoordinate][enemyXCoordinate - 1] = enemyHead;
                enemyXCoordinate -= 1;
                break;
            case "right":
                mapToMakeMoveOn[enemyYCoordinate][enemyXCoordinate + 1] = enemyHead;
                enemyXCoordinate += 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterTheirsMove()");
        }
        return mapToMakeMoveOn;
    }

    char[][] getMapBeforeOurMove(@NotNull char[][] mapToTakeMoveFrom, @NotNull String lastMove) {
        //return mapToTakeMoveFrom, for lastMove String with value either "up" or "down" or "left" or "right"
        //where you've taken back according move
        //assume move is correct, as this operation takes considerable amount of time in MiniMax
        retrieveAllCoordinates(mapToTakeMoveFrom);
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
                System.out.println("Error, wrong move on getMapBeforeOurMove()");
        }
        return mapToTakeMoveFrom;
    }

    char[][] getMapBeforeTheirsMove(@NotNull char[][] mapToTakeMoveFrom, @NotNull String lastMove) {
        //return mapToTakeMoveFrom, for lastMove String with value either "up" or "down" or "left" or "right"
        //where you've taken back according move
        //assume move is correct, as this operation takes considerable amount of time in MiniMax
        retrieveAllCoordinates(mapToTakeMoveFrom);
        mapToTakeMoveFrom[enemyYCoordinate][enemyXCoordinate] = ' ';

        switch (lastMove) {
            case "up":
                mapToTakeMoveFrom[enemyYCoordinate + 1][enemyXCoordinate] = enemyHead;
                enemyYCoordinate += 1;
                break;
            case "down":
                mapToTakeMoveFrom[enemyYCoordinate - 1][enemyXCoordinate] = enemyHead;
                enemyYCoordinate -= 1;
                break;
            case "left":
                mapToTakeMoveFrom[enemyYCoordinate][enemyXCoordinate + 1] = enemyHead;
                enemyXCoordinate += 1;
                break;
            case "right":
                mapToTakeMoveFrom[enemyYCoordinate][enemyXCoordinate - 1] = enemyHead;
                enemyXCoordinate -= 1;
                break;
            default:
                System.out.println("Error, wrong move on getMapAfterTheirsMove()");
        }
        return mapToTakeMoveFrom;
    }

    boolean isTheGameOver(char[][] map) {
        List<String> ourMoves = getOurEveryAvailableMove(map);
        List<String> theirMoves = getTheirsEveryAvailableMove(map);

        return (ourMoves.isEmpty() || theirMoves.isEmpty());
    }

    int maximize(int ply, char[][] fromWhatMap) {
        ///function returns best value of an evaluation in the game tree
        ///of ply depth
        retrieveAllCoordinates(fromWhatMap);

        if (ply == 0 || isTheGameOver(fromWhatMap))
            return getEvaluationOfPosition(fromWhatMap);

        int best = -1000;

        List<String> whichMovesArePossible = getOurEveryAvailableMove(fromWhatMap);

        for (String currentMove : whichMovesArePossible) {
            //makeMove from a currentMove
            //minimize(ply - 1, fromWhatMap)
            // ~takeBackMove from a currentMove~ -- no global map state, so we don't take back
            //if minimize(ply - 1) better than move, change value of best
            char[][] nextMapState = getMapAfterOurMove(fromWhatMap, currentMove);
            int current = minimize(ply - 1, nextMapState);
            if (current > best)
                best = current;
        }

        return best;

    }

    int minimize(int ply, char[][] fromWhatMap) {
        ///function returns best value of an evaluation in the game tree
        ///of ply depth
        retrieveAllCoordinates(fromWhatMap);
        int worst = 1000;

        if (ply == 0 || isTheGameOver(fromWhatMap))
            return getEvaluationOfPosition(fromWhatMap);

        List<String> whichMovesArePossible = getTheirsEveryAvailableMove(fromWhatMap);

        if (whichMovesArePossible.isEmpty())
            System.out.println("null");

        for (String currentMove : whichMovesArePossible) {
            //makeMove from a currentMove
            //minimize(ply - 1, fromWhatMap)
            // ~takeBackMove from a currentMove~ -- no global map state, so we don't take back
            //if minimize(ply - 1) better than move, change value of best
            char[][] nextMapState = getMapAfterTheirsMove(fromWhatMap, currentMove);
            int current = maximize(ply - 1, nextMapState);
            if (current < worst)
                worst = current;
        }

        return worst;
    }

    String whichMoveShallITake(int ply, char[][] fromWhatMap) {
        String bestMove = "up";
        int bestValue = -1000;

        List<String> whichMovesArePossible;

        if (getWhoseMoveItIs() == myHead) {
            whichMovesArePossible = getOurEveryAvailableMove(fromWhatMap);
        } else {
            whichMovesArePossible = getTheirsEveryAvailableMove(fromWhatMap);
        }
        //if ply%2 == 0 whichMovesArePossible = getTheirsAvailableMove(currentParsedMap); ?

        for (String move : whichMovesArePossible) {
            //makeMove from a currentMove
            //maximize(ply)
            //takeBackMove from a currentMove
            //if maximize(ply) better than move, change value of bestValue
            //  and move = bestMove

            retrieveAllCoordinates(fromWhatMap);

            int current;

            if (getWhoseMoveItIs() == 'R') {

                char[][] nextMapState = getMapAfterOurMove(fromWhatMap, move);
                setWhoseMoveItIs('B');
                current = minimize(ply - 1, nextMapState);
            } else {

                char[][] nextMapState = getMapAfterTheirsMove(fromWhatMap, move);
                setWhoseMoveItIs('R');
                current = maximize(ply - 1, nextMapState);
            }

            if (current > bestValue) {
                bestValue = current;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
