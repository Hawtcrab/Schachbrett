import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/// Die wichtigste Klasse. Das GameBoard speichert gleichzeitig die Positionen und Daten der Spielfelder und
/// -Objekte, aber auch die visuellen Informationen (JPanel) die zu den Feldern gehören.
public class GameBoard extends JFrame implements KeyListener {

    public GameTile[][] tiles;
    public JPanel[][] panels;
    public GameBoard(String name) {
        super(name);
    }

    /// Iteriert durch alle Felder, um das Feld des Spielers zu finden.
    public GameTile GetPlayerTile() {
        for (var tile1 : tiles) {
            for (var tile2 : tile1) {
                if (tile2.object instanceof  Spieler)
                    return tile2;
            }
        }
        return null;
    }

    /// Versucht, das auf einer GameTile befindliche Objekt zu verschieben.
    public void movePiece(GameTile oldtile, int deltay, int deltax) {

        var newx = clamp(oldtile.coordinates[1] + deltax, 0, tiles[0].length - 1);
        var newy = clamp(oldtile.coordinates[0] + deltay, 0, tiles.length - 1);
        var newtile = tiles[newy][newx];

        if (oldtile != newtile) {
            newtile.object = oldtile.object;
            oldtile.object = null;
        }
        Main.UpdateFrame();
    }

    /// Eine Methode, die dafür sorgt, dass eine Input-Zahl einen Maximal - oder Minimalwert nicht
    /// über- oder unterschreitet.
    private int clamp (int value, int min, int max) {

        return value > max ? max : (value < min ? min : value);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: movePiece(GetPlayerTile(), -1, 0); break;
            case KeyEvent.VK_DOWN: movePiece(GetPlayerTile(), 1, 0);break;
            case KeyEvent.VK_RIGHT: movePiece(GetPlayerTile(), 0, 1);break;
            case KeyEvent.VK_LEFT:movePiece(GetPlayerTile(), 0, -1);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
