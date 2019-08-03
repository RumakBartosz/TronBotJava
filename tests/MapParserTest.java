import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tron_bot.MapParser;
import tron_bot.MoveDecider;

class MapParserTest {

    @Test
    void processThisMove() {
        MapParser mapParser = new MapParser();

        String move = "oooooooooooo/oR9o/o10o/o10o/o10o/" +
                "o10o/o10o/o10o/o10o/o10o/o9Ro/oooooooooooo";


        String parsed = mapParser.processThisMove(move);

        Assertions.assertEquals("oooooooooooo/oR         o/o          o/o          o/o          o/" +
                "o          o/o          o/o          o/o          o/o          o/o         Ro/oooooooooooo", parsed);

    }

    @Test
    void parseTheMap() {

        MapParser mapParser = new MapParser();

        String move = "oooooooooooo/oR9o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o9Bo/oooooooooooo";
        String parsed = mapParser.processThisMove(move);


        char[][] ThisMap = mapParser.parseTheMap(12, 12, parsed);

        char[][] ExpectedMap = {
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

        Assertions.assertArrayEquals(ExpectedMap, ThisMap);

    }

    @Test
    void universalSimpleBotTest() {
        MapParser mapParser = new MapParser();

        String move = "oooooooooooo/oR9o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o9Bo/oooooooooooo";
        String parsed = mapParser.processThisMove(move);

        char[][] ParsedMap = mapParser.parseTheMap(12, 12, parsed);

        MoveDecider RedMoveDecider = new MoveDecider(ParsedMap, "red");
        MoveDecider BlueMoveDecider = new MoveDecider(ParsedMap, "blue");

        String RedChosenMove = RedMoveDecider.giveMeARandomMove();
        String BlueChosenMove = BlueMoveDecider.giveMeARandomMove();

        boolean CorrectRedMove = (RedChosenMove.equals("right") || RedChosenMove.equals("down"));
        boolean CorrectBlueMove = (BlueChosenMove.equals("up") || BlueChosenMove.equals("left"));

        Assertions.assertTrue(CorrectBlueMove);
        Assertions.assertTrue(CorrectRedMove);
    }


    @Test
    void universalOneMoveSimpleBotTest() {
        MapParser mapParser = new MapParser();

        String move = "ooooo/oR1Bo/ooooo";
        String parsed = mapParser.processThisMove(move);

        char[][] ParsedMap = mapParser.parseTheMap(5, 5, parsed);

        MoveDecider RedMoveDecider = new MoveDecider(ParsedMap, "red");
        MoveDecider BlueMoveDecider = new MoveDecider(ParsedMap, "blue");

        String RedChosenMove = RedMoveDecider.giveMeARandomMove();
        String BlueChosenMove = BlueMoveDecider.giveMeARandomMove();

        boolean CorrectRedMove = RedChosenMove.equals("right");
        boolean CorrectBlueMove = BlueChosenMove.equals("left");

        Assertions.assertTrue(CorrectBlueMove);
        Assertions.assertTrue(CorrectRedMove);
    }
}