import java.awt.*;

public class GameTile {
    int[] coordinates;
    GameObject object;
    GameTile(int y, int x) {
        coordinates = new int[]{y, x};
    }

    public Color GetColor() {
        if (object != null) return object.color;
        if ((coordinates[0] + (coordinates[1] % 2)) % 2 == 0) return Color.BLACK;
        return Color.WHITE;
    }
}
