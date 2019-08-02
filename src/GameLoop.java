public class GameLoop {

    public static void main(String[] args) {

        ProtocolResponder responder = new ProtocolResponder();

        while (true) {
            responder.respond();

        }

    }
}
