package tron_bot;
//TODO: why are array like this

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MiniMaxBotTest {


    char[][] currentMap = {
            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
            {'o', ' ', ' ', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'b', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
    };

    MiniMaxBot TestBot = new MiniMaxBot(currentMap, "red");


    @Test
    void setCoordinates() {
        Assertions.assertTrue(TestBot.myCoordinates.x == 3 && TestBot.myCoordinates.y == 1 &&
                TestBot.enemyCoordinates.x == 9 && TestBot.enemyCoordinates.y == 7);

    }

    @Test
    void isMoveUpPossible() {
        Assertions.assertFalse(TestBot.isMoveUpPossible());

        TestBot.whoseMoveItIs = WhoseMove.ENEMY;
        Assertions.assertTrue(TestBot.isMoveUpPossible());

        //cleanup
        TestBot.whoseMoveItIs = WhoseMove.ME;
    }

    @Test
    void isMoveDownPossible() {

        Assertions.assertFalse(TestBot.isMoveDownPossible());

        TestBot.whoseMoveItIs = WhoseMove.ENEMY;
        Assertions.assertTrue(TestBot.isMoveDownPossible());

        //cleanup
        TestBot.whoseMoveItIs = WhoseMove.ME;
    }

    @Test
    void isMoveLeftPossible() {

        Assertions.assertTrue(TestBot.isMoveLeftPossible());

        TestBot.whoseMoveItIs = WhoseMove.ENEMY;
        Assertions.assertTrue(TestBot.isMoveLeftPossible());

        //cleanup
        TestBot.whoseMoveItIs = WhoseMove.ME;
    }

    @Test
    void isMoveRightPossible() {

        Assertions.assertTrue(TestBot.isMoveRightPossible());

        TestBot.whoseMoveItIs = WhoseMove.ENEMY;
        Assertions.assertFalse(TestBot.isMoveRightPossible());

        //cleanup
        TestBot.whoseMoveItIs = WhoseMove.ME;
    }

    @Test
    void getEveryPossibleMove() {
        String[] myMoves = {"left", "right"};
        Assertions.assertArrayEquals(TestBot.getEveryPossibleMove().toArray(), myMoves);

        String[] enemyMoves = {"up", "down", "left"};
        TestBot.whoseMoveItIs = WhoseMove.ENEMY;
        Assertions.assertArrayEquals(TestBot.getEveryPossibleMove().toArray(), enemyMoves);

        char[][] overMap = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'R', 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };

        String[] emptyArray = {};
        MiniMaxBot gameOver = new MiniMaxBot(overMap, "red");
        Assertions.assertArrayEquals(gameOver.getEveryPossibleMove().toArray(), emptyArray);
    }

    @Test
    void isTheGameOver() {
        Assertions.assertFalse(TestBot.isTheGameOver());

        char[][] overMap = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', 'r', 'R', 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };
        MiniMaxBot gameOver = new MiniMaxBot(overMap, "red");

        Assertions.assertTrue(gameOver.isTheGameOver());

    }

    @Test
    void move() {

        char[][] afterFirstMove = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', ' ', 'r', 'R', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'b', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };

        TestBot.move("right");
        Assertions.assertArrayEquals(TestBot.mapState, afterFirstMove);

        char[][] afterSecondMove = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', ' ', 'r', 'R', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'b', 'b', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };

        TestBot.move("left");
        Assertions.assertArrayEquals(TestBot.mapState, afterSecondMove);
    }

    @Test
    void unmove() {

        char[][] beforeFirstMove = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'b', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };

        TestBot.unmove("up");
        Assertions.assertArrayEquals(beforeFirstMove, TestBot.mapState);

        char[][] afterSecondMove = {
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', 'r', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
        };

        TestBot.unmove("left");
        Assertions.assertArrayEquals(afterSecondMove, TestBot.mapState);

    }

    @Test
    void whichMoveShallITake() {
        String takenMove = TestBot.whichMoveShallITake(10);

        Assertions.assertTrue(takenMove.equals("left") || takenMove.equals("right"));

        String nextTakenMove = TestBot.whichMoveShallITake(10);

        Assertions.assertTrue(nextTakenMove.equals("left") || nextTakenMove.equals("up")
                || nextTakenMove.equals("down"));
    }

    @Test
    void getVoronoiDiagram() {
        char[][] voronoi = TestBot.getVoronoiDiagram();


        char[][] assumedCorrectVoronoi = {
                {'m', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'm', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'m', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}
        };

        Assertions.assertArrayEquals(assumedCorrectVoronoi, voronoi);
    }


    @Test
    void getMyReachAmount() {
        int assumedCountOfReach = TestBot.getMyReachAmount();

        Assertions.assertEquals(51, assumedCountOfReach);
    }

    @Test
    void getEnemyReachAmount() {
        int assumedCountOfReach = TestBot.getEnemyReachAmount();

        Assertions.assertEquals(93, assumedCountOfReach);
    }
}