import java.awt.*;

/// Ein GameTile ist ein Feld des Spielfeldes, speichert aber nur den Inhalt des Feldes, und Infos über das Feld
/// (etwa die Koordinaten). Alles optische wird vom JPanel abgehandelt.
public class GameTile {
    int[] coordinates;
    /// Das Objekt das möglicherw. auf dem Feld steht, etwa ein Spieler oder Hindernis.
    GameObject object;
    GameTile(int y, int x) {
        coordinates = new int[]{y, x};
    }

    /// Bestimmt die Farbe, die das JPanel nutzen soll. Abhängig davon, ob ein Objekt auf dem Feld ist.
    /// Ansonsten enscheidet es auf Basis von Zeile/Spalte zwischen schwarz und weiß, für das
    /// Schachbrettmuster.
    public Color GetColor() {
        if (object != null) return object.color;
        if ((coordinates[0] + (coordinates[1] % 2)) % 2 == 0) return Color.BLACK;
        return Color.WHITE;
    }
}
