package tron_bot;
//TODO: REFACTOR respondToMoveMessage()

import org.jetbrains.annotations.NotNull;

public class ProtocolResponder {

    private String color;
    private MapParser mainMapParser = new MapParser();

    private MoveDecider mainMoveDecider = null;

    void respond(String message) {

        switch (message) {
            case "tbi":
                respondToInitialMessage(message);
                break;
            case "tbi v1":
                respondToVersionMessage(message);
                break;
            case "color red":
            case "color blue":
                respondToColorMessage(message);
                break;
            default:
                if (message.matches("move (.*)")) {
                    respondToMoveMessage(message);
                } else {
                    throw new UnsupportedOperationException(message + "is not a valid message");
                }

        }

    }

    private void respondToInitialMessage(@NotNull String message) {
        System.out.println(message + " ok");
    }

    private void respondToVersionMessage(@NotNull String message) {
        System.out.println(message + " ok");
    }

    private void respondToColorMessage(@NotNull String message) {

        color = message.substring(message.indexOf(' ') + 1);

        String regex = "(blue|red)";
        message = message.replaceAll(regex, "");

        System.out.println(message + "ok");
    }

    private void respondToMoveMessage(@NotNull String message) {

        String move = message.substring(message.indexOf(' ') + 1);
        String processedMove = mainMapParser.processThisMove(move);

        int xSize = mainMapParser.getMapXSize(processedMove);
        int ySize = mainMapParser.getMapYSize(processedMove);

        char[][] parsedMap = mainMapParser.parseTheMap(ySize, xSize, processedMove);

        if (mainMoveDecider == null)
            mainMoveDecider = new MoveDecider(parsedMap, color);

        mainMoveDecider.setParsedMap(parsedMap);

        String chosenMove = mainMoveDecider.giveMeARandomMove();
        System.out.println(chosenMove);
    }
}
