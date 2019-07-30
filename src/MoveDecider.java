import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MoveDecider {

    private char[][] ParsedMap;
    private String Color;
    private char TronHead;
    private int XCoordinate;
    private int YCoordinate;

    @Contract(pure = true)
    MoveDecider(char[][] parsedMap, @NotNull String color) {
        ParsedMap = parsedMap;
        Color = color;

        if (color.equals("red"))
            TronHead = 'R';
        else
            TronHead = 'B';
    }

    void setParsedMap(char[][] parsedMap) {
        ParsedMap = parsedMap;
    }

    private void retrieveYCoordinate() {
        for (int i = 0; i < ParsedMap.length; i++)
            for (int j = 0; j < ParsedMap[0].length; j++)
                if (ParsedMap[i][j] == TronHead) {
                    YCoordinate = i;
                    return;
                }
    }


    private void retrieveXCoordinate() {
        for (char[] chars : ParsedMap)
            for (int j = 0; j < ParsedMap[0].length; j++)
                if (chars[j] == TronHead) {
                    XCoordinate = j;
                    return;
                }
    }

    String GiveMeANaiveMove() {
        if (Color != null && Color.equals("red"))
            return "up";
        else
            return "down";
    }


    String GiveMeARandomMove() {
        retrieveXCoordinate();
        retrieveYCoordinate();

        List<Integer> moveList = new ArrayList<>();

        //UP, DOWN, LEFT, RIGHT
        if (ParsedMap[YCoordinate - 1][XCoordinate] == ' ')
            moveList.add(1);

        if (ParsedMap[YCoordinate + 1][XCoordinate] == ' ')
            moveList.add(2);

        if (ParsedMap[YCoordinate][XCoordinate - 1] == ' ')
            moveList.add(3);

        if (ParsedMap[YCoordinate][XCoordinate + 1] == ' ')
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
