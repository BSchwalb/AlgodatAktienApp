/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Super
 */
public class Stock {

    private final String name;
    private final String wkn;
    private final String fullname;

    public MarketValue marketHistory[] = new MarketValue[30];
    public StockImage image = new StockImage();

    public Stock(String stock) {
        String words[] = stock.trim().split(" ");
        this.name = words[0];
        this.fullname = words[1];
        this.wkn = words[2];
    }

    public Stock(String name, String fullname, String wkn) {
        this.name = name;
        this.wkn = wkn;
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public String getWkn() {
        return wkn;
    }

    public String getFullname() {
        return fullname;
    }

    void importHistory(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();
            for (int i = 0; i < 30; i++) {
                if (!reader.ready())
                    break;
                marketHistory[i] = MarketValue.getInstance(reader.readLine());
            }
            updatePlot();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return name + " " + fullname + " " + wkn;
    }

    public void updatePlot() {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int x = 0; x < marketHistory.length; x++) {
            MarketValue marketValue = marketHistory[x];
            if (marketValue == null)
                break;
            int y = marketValue.getAdjClose();
            minY = y < minY ? y : minY;
            maxY = y > maxY ? y : maxY;
        }
        int deltaY = maxY - minY;
        char color = '+';
        for (int x = 0; x < marketHistory.length; x++) {
            MarketValue marketValue = marketHistory[x];
            if (marketValue == null)
                break;
            int y = marketValue.getAdjClose();
            y -= minY;
            y *= StockImage.HEIGHT - 2;
            y = deltaY != 0 ? y / deltaY : 0;
            image.drawRect(1, color, StockImage.WIDTH - 2 - x, 1 + y, 1, StockImage.HEIGHT - y - 2);
        }
    }

    public String showMeYourMoves() {
        return image.toString();
    }
}
