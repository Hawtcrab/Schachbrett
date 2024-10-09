import java.awt.*;

/// Ein Spieler ist eine besondere Form des GameObject, und hat andere Regeln dafür, welche Farbe es
/// annehmen soll.
public class Spieler extends GameObject{

    public Spieler() {
        this.color = Color.RED;
    }
}
