//TODO: REFACTOR respondToMoveMessage()

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

class ProtocolResponder {

    private String color;
    private MapParser MainMapParser = new MapParser();

    private MoveDecider MainMoveDecider = null;

    private String getInputFromOutside() {
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    void Respond() throws UnsupportedOperationException {
        String Message = getInputFromOutside();

        switch (Message) {
            case "tbi":
                RespondToInitialMessage(Message);
                break;
            case "tbi v1":
                RespondToVersionMessage(Message);
                break;
            case "color red":
            case "color blue":
                RespondToColorMessage(Message);
                break;
            default:
                if (Message.matches("move (.*)")) {
                    RespondToMoveMessage(Message);
                } else {
                    throw new UnsupportedOperationException(Message + "is not a valid message");
                }

        }

    }

    private void RespondToInitialMessage(@NotNull String Message) {
        System.out.println(Message + " ok");
    }

    private void RespondToVersionMessage(@NotNull String Message) {
        System.out.println(Message + " ok");
    }

    private void RespondToColorMessage(@NotNull String Message) {

        color = Message.substring(Message.indexOf(" ") + 1);

        String regex = "(blue|red)";
        Message = Message.replaceAll(regex, "");

        System.out.println(Message + "ok");
    }

    private void RespondToMoveMessage(@NotNull String Message) {

        String move = Message.substring(Message.indexOf(" ") + 1);
        String processedMove = MainMapParser.ProcessThisMove(move);

        int xSize = MainMapParser.getMapXSize(processedMove);
        int ySize = MainMapParser.getMapYSize(processedMove);

        char[][] ParsedMap = MainMapParser.parseTheMap(ySize, xSize, processedMove);

        if (MainMoveDecider == null)
            MainMoveDecider = new MoveDecider(ParsedMap, color);

        MainMoveDecider.setParsedMap(ParsedMap);

        String ChosenMove = MainMoveDecider.GiveMeARandomMove();
        System.out.println(ChosenMove);
    }
}
