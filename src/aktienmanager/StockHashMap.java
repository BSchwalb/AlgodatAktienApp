/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

/**
 *
 * @author Super
 */
public class StockHashMap {

    private final Stock[] stockList = new Stock[2003];

    private static final int MAXIMAL_COLLISION_ITERATION = 29;

    public static final RuntimeException COLLISION_EXCEPTION = new RuntimeException("Too many collisions!");
    public static final RuntimeException ELEMENT_EXISTS_EXCEPTION = new RuntimeException("An element with the same name already exists!");
    public static final NullPointerException ELEMENT_NOT_EXISITING_EXCEPTION = new NullPointerException("Hashmap does not contain element.");

    public StockHashMap(String... stocks) {
        for (String line : stocks) {
            String split[] = line.split(":", 2);
            stockList[Integer.parseInt(split[0])] = new Stock(split[1]);
        }
    }

    private int createDownscaledHash(int hash) {
        hash = hash % stockList.length;
        return hash;
    }

    private int createCollidedHash(int hash, int iteration) {
        if (iteration > MAXIMAL_COLLISION_ITERATION)
            throw COLLISION_EXCEPTION;
        return createDownscaledHash(hash + (int) Math.pow(2, iteration));
    }

    private int stringToProtohash(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            hash += letter * (i + 1);
            hash = createDownscaledHash(hash);
        }
        return hash;
    }

    private int stringToHash(String name) {
        int i = 0, protohash = createDownscaledHash(stringToProtohash(name)), hash = protohash;
        Stock existingElement = stockList[hash];

        while (existingElement != null && !existingElement.getName().equals(name)) {
            hash = createCollidedHash(protohash, i++);
            existingElement = stockList[hash];
        }
        return hash;
    }

    public boolean put(Stock element) {
        int hash = stringToHash(element.getName());
        stockList[hash] = element;
        System.out.println("Added \"" + element.getName() + "\" at position " + hash);
        return true;
    }

    public Stock get(String name) {
        return stockList[stringToHash(name)];
    }

    public boolean remove(String name) {
        int hash = stringToHash(name);
        if (stockList[hash] == null)
            return false;
        int protohash = stringToProtohash(name);
        for (int i = MAXIMAL_COLLISION_ITERATION; i > 0; i--) {
            int chash1 = createCollidedHash(protohash, i - 1);
            if (stockList[chash1] == null)
                continue;
            int chash2 = createCollidedHash(protohash, i);
            if (chash2 == hash)
                break;
            stockList[chash1] = stockList[chash2];
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < stockList.length; i++) {
            Stock stock = stockList[i];
            if (stock == null)
                continue;
            if (first)
                first = false;
            else
                builder.append("\n");
            builder.append(String.format("%04d", i)).append(": ").append(stock);
        }
        return builder.toString();
    }
}
