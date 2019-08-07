package tron_bot;

import org.junit.jupiter.api.Test;

class TronBotTest {

    TronBot MiniMaxTestBot = new TronBot("red");

    char[][] currentMap = {
            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
            {'o', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'o'},
            {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'o'},
            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}

    };

    @Test
    void getOurEveryAvailableMove() {
    }

    @Test
    void getTheirsEveryAvailableMove() {
    }

    @Test
    void getEvaluationOfPosition() {
    }

    @Test
    void getMapAfterOurMove() {
    }

    @Test
    void getMapAfterTheirsMove() {
    }

    @Test
    void getMapBeforeOurMove() {
    }

    @Test
    void getMapBeforeTheirsMove() {
    }

    @Test
    void isTheGameOver() {
    }

    @Test
    void maximize() {
    }

    @Test
    void minimize() {
    }

    @Test
    void whichMoveShallITake() {
    }
}