import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    /// Da wir nur ein Spielbrett brauchen, macht es Sinn, dieses statisch zu speichern.
    /// So können alle anderen Klassen darauf zugreifen, ohne dass es als Parameter "herumgereicht" werden muss.
    public static GameBoard gameframe;


    /// Diese Methode wird zuerst aufgerufen, und bringt das Spiel in Gang.
    public static void main(String[] args) {

        // Wir erstellen ein neues Schachbrett.
        Main.gameframe = new GameBoard("Schachbrett");
        var rows = 20;
        var columns = 20;
        // Wir befüllen die Spielfelder, mit Blick auf die gewünschte Anzahl von Zeilen und Spalten.
        InitializeGameTiles(rows,columns);
        // Der Spielercharakter wird platziert.
        PlacePlayer();
        // Da nun das Gerüst des Spiels steht, bilden wir eine grafische Darstellung.
        InitializeFrame(gameframe, rows, columns);

    }

    /// Diese Methode wird aufgerufen, wenn die grafische Darstellung verändert wird, etwa wenn sich
    /// eine Figur bewegt.
    public static void UpdateFrame() {
        // Diese Methode stellt die Farben um...
        RepaintPanels();

        // Und wir sagen den Quadraten, dass sie neu gezeichnet werden sollen.
        gameframe.revalidate();
        gameframe.repaint();
    }


    /// Diese Methoden stellt die anfänglichen Optionen für das Spielfenster ein.
    @SuppressWarnings("ReassignedVariable")
    public static void InitializeFrame(JFrame frame, int rows, int columns) {
        // Wenn wir auf X drücken, soll das Programm geschlossen werden.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Wir benutzen zur Darstellung ein Gitter.
        frame.setLayout(new GridLayout(rows,columns));
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                // Pro Feld auf dem Gitter erstellen wir ein neues Quadrat,
                // setzen seine Anfangsfarbe und speichern es auf dem GameBoard.
                JPanel tile = new JPanel();
                tile.setBackground(gameframe.tiles[y][x].GetColor());
                frame.add(tile);
                gameframe.panels[y][x] = tile;
            }
        }
        frame.pack();
        // Wir stellen die Größe des Fensters ein, und ob es am Anfang sichtbar sein soll.
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(gameframe);
    }

    /// Diese Methode setzt die Farbe aller Felder, je nach ihrem Inhalt.
    @SuppressWarnings("ReassignedVariable")
    public static void RepaintPanels() {
        for (int y = 0; y < gameframe.panels.length; y++) {
            for (int x = 0; x < gameframe.panels[y].length;x++) {
                // .GetColor bestimmt die Farbe.
                gameframe.panels[y][x].setBackground(gameframe.tiles[y][x].GetColor());
            }
        }
    }

    /// Wir speichern die Quadrate/JPanel und die GameTiles auf dem GameBoard.
    public static void InitializeGameTiles(int rows, int columns) {
        gameframe.tiles = new GameTile[columns][rows];
        gameframe.panels = new JPanel[columns][rows];
        for (int y = 0; y < columns; y++)
        {
            for (int x = 0; x < rows; x++) {
                gameframe.tiles[y][x] = new GameTile(y,x);
            }
        }

    }

    /// Platziert den Spieler auf einem zusätzlichen Feld des Spielbretts.
    /// Zufallszahlen werden generiert, indem die Klasse Random() instanziiert wird.
    /// Eine Instanz von Random() kann viele Zahlen generieren.
    public static void PlacePlayer() {
        var random = new Random();
        var y = random.nextInt(0, gameframe.tiles.length);
        var x = random.nextInt(0, gameframe.tiles[y].length);

        gameframe.tiles[y][x].object = new Spieler();

    }

}