import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapParserTest {

    @Test
    void processThisMove() {
        MapParser mapParser = new MapParser();

        String move = "oooooooooooo/oR9o/o10o/o10o/o10o/" +
                "o10o/o10o/o10o/o10o/o10o/o9Ro/oooooooooooo";


        String parsed = mapParser.ProcessThisMove(move);

        Assertions.assertEquals("oooooooooooo/oR         o/o          o/o          o/o          o/" +
                "o          o/o          o/o          o/o          o/o          o/o         Ro/oooooooooooo", parsed);

    }

    @Test
    void parseTheMap() {

        MapParser mapParser = new MapParser();

        String move = "oooooooooooo/oR9o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o10o/o9Bo/oooooooooooo";
        String parsed = mapParser.ProcessThisMove(move);


        char[][] ThisMap = new char[12][12];

        ThisMap = mapParser.parseTheMap(12, 12, parsed);

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
}