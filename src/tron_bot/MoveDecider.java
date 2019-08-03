package tron_bot;//TODO: REFACTOR, whole object as a simple agent with naive and random move

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveDecider {

    private char[][] parsedMap;
    private String color;
    private char tronHead;
    private int xCoordinate;
    private int yCoordinate;

    @Contract(pure = true)
    public MoveDecider(char[][] parsedMap, @NotNull String color) {
        this.parsedMap = parsedMap;
        this.color = color;

        if (color.equals("red"))
            tronHead = 'R';
        else
            tronHead = 'B';
    }

    void setParsedMap(char[][] parsedMap) {
        this.parsedMap = parsedMap;
    }

    private void retrieveYCoordinate() {
        for (int i = 0; i < parsedMap.length; i++)
            for (int j = 0; j < parsedMap[0].length; j++)
                if (parsedMap[i][j] == tronHead) {
                    yCoordinate = i;
                    return;
                }
    }

    private void retrieveXCoordinate() {
        for (char[] chars : parsedMap)
            for (int j = 0; j < parsedMap[0].length; j++)
                if (chars[j] == tronHead) {
                    xCoordinate = j;
                    return;
                }
    }

    String giveMeANaiveMove() {
        if (color != null && color.equals("red"))
            return "up";
        else
            return "down";
    }

    public String giveMeARandomMove() {
        retrieveXCoordinate();
        retrieveYCoordinate();

        List<Integer> moveList = new ArrayList<>();

        //UP, DOWN, LEFT, RIGHT
        if (parsedMap[yCoordinate - 1][xCoordinate] == ' ')
            moveList.add(1);

        if (parsedMap[yCoordinate + 1][xCoordinate] == ' ')
            moveList.add(2);

        if (parsedMap[yCoordinate][xCoordinate - 1] == ' ')
            moveList.add(3);

        if (parsedMap[yCoordinate][xCoordinate + 1] == ' ')
            moveList.add(4);

        int rnd = new Random().nextInt(moveList.size());

        if (moveList.get(rnd) == 1) {
            return "up";
        } else if (moveList.get(rnd) == 2) {
            return "down";
        } else if (moveList.get(rnd) == 3) {
            return "left";
        } else {
            return "right";
        }

    }
}
