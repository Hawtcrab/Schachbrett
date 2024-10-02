import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static GameBoard gameframe;


    public static void main(String[] args) {
        Main.gameframe = new GameBoard("Schachbrett");
        var rows = 20;
        var columns = 20;
        InitializeGameTiles(rows,columns);
        PlacePlayer();
        InitializeFrame(gameframe, rows, columns);

    }

    public static void UpdateFrame() {
        RepaintPanels();
        gameframe.revalidate();
        gameframe.repaint();
    }
    public static void InitializeFrame(JFrame frame, int rows, int columns) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows,columns));
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                JPanel tile = new JPanel();
                tile.setBackground(gameframe.tiles[y][x].GetColor());
                frame.add(tile);
                gameframe.panels[y][x] = tile;
            }
        }
        frame.pack();
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(gameframe);
    }

    public static void RepaintPanels() {
        for (int y = 0; y < gameframe.panels.length; y++) {
            for (int x = 0; x < gameframe.panels[y].length;x++) {
                gameframe.panels[y][x].setBackground(gameframe.tiles[y][x].GetColor());
            }
        }
    }
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

    public static void PlacePlayer() {
        var random = new Random();
        var y = random.nextInt(0, gameframe.tiles.length);
        var x = random.nextInt(0, gameframe.tiles[y].length);

        gameframe.tiles[y][x].object = new Spieler();

    }

}