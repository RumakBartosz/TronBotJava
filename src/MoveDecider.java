public class MoveDecider {

    char[][] ParsedMap;
    String Color;

    public void setParsedMap(char[][] parsedMap) {
        ParsedMap = parsedMap;
    }

    public void setColor(String color) {
        Color = color;
    }

    String GiveMeANaiveMove() {
        if (Color != null && Color.equals("red"))
            return "up";
        else
            return "down";
    }
}
