package tron_bot;

import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {

        ProtocolResponder responder = new ProtocolResponder();

        Scanner sc = new Scanner(System.in);

        while (true) {
            responder.respond(sc.nextLine());
        }

    }
}
