import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class ProtocolResponder {

    private String move;
    private String color;
    private MapParser MainMapParser = new MapParser();
    private MoveDecider MainMoveDecider = new MoveDecider();

    public String getMove() {
        return move;
    }

    private String getInputFromOutside() {
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public void Respond() throws UnsupportedOperationException {
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
        move = Message.substring(Message.indexOf(" ") + 1);

        String processedMove = MainMapParser.ProcessThisMove(move);
        char[][] ParsedMap = MainMapParser.parseTheMap(12, 12, processedMove);

        MainMoveDecider.setParsedMap(ParsedMap);
        MainMoveDecider.setColor(color);

        MainMapParser.ProcessThisMove(move);
        String ChosenMove = MainMoveDecider.GiveMeANaiveMove();
        System.out.println(ChosenMove);
    }
}
