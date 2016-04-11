/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

/**
 *
 * @author Link
 */
public class StockImage {

    public static final int DEPTH = 4;
    public static final int HEIGHT = 20;
    public static final int WIDTH = 32;
    public char[][][] image = new char[DEPTH][HEIGHT][WIDTH];

    public StockImage() {
        clearImage();
        drawBackground();
    }

    public void clearImage() {
        for (int z = 0; z < image.length; z++) {
            for (int y = 0; y < image[z].length; y++) {
                for (int x = 0; x < image[z][y].length; x++) {
                    image[z][y][x] = ' ';
                }
            }
        }

    }

    private void drawBackground() {
        for (int y = 2; y < HEIGHT; y += 2) {
            drawRect(0, '.', 1, y, WIDTH - 1, 1);
        }

        for (int x = 1; x < WIDTH; x += 5) {
            drawRect(0, '.', x, 1, 1, HEIGHT - 1);
        }

        drawRect(0, '#', 0, 0, 1, HEIGHT);
        drawRect(0, '#', 0, 0, WIDTH, 1);
        drawRect(0, '#', 0, HEIGHT - 1, WIDTH, 1);
        drawRect(0, '#', WIDTH - 1, 0, 1, HEIGHT);
    }

    public void drawRect(int z, char paint, int x, int y, int w, int h) {
        for (int yi = y; yi < y + h; yi++) {
            for (int xi = x; xi < x + w; xi++) {
                image[z][yi][xi] = paint;
            }
        }
    }

    public void drawDot(int z, char paint, int x, int y) {
        image[z][y][x] = paint;
    }

    @Override
    public String toString() {
        char[][] render = new char[HEIGHT][WIDTH];
        for (int z = 0; z < DEPTH; z++) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    char c = image[z][y][x];
                    if (c != ' ')
                        render[y][x] = c;
                }
            }
        }

        StringBuilder bob = new StringBuilder();
        for (char[] line : render) {
            bob.append(line);
            bob.append("\n");
        }
        return bob.toString();
    }

}
