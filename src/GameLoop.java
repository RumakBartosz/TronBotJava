public class GameLoop {

    public static void main(String[] args) {

        ProtocolResponder Responder = new ProtocolResponder();

        while (true) {
            Responder.Respond();

        }

    }
}
