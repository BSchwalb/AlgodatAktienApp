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

public class MyHashMap {

    private int[] offsets = new int[]{1, 3, 5, 7, 11, 13, 17};
    
    private int size = 100;
    
    private Stock[] stocklist;
    
    public MyHashMap() {
        stocklist = new Stock[size];
    }
    
    private int validHash(int hash) {
        hash = hash % size;
        return hash;
    }
    
    private int nextHash(int hash, int iteration) {
        return hash + offsets[iteration];
    }

    public void put(Stock element) {
    // get the hashcode and regenerate it to be optimum
    int userHash = element.name.hashCode();
    
    
    int hash = validHash(userHash);
    
    Stock existingElement = stocklist[hash];
 
    int i = 0;
    while (existingElement != null) {
        hash = nextHash(hash, ++i);
        existingElement = stocklist[hash];
    }
    
    stocklist[hash] = element;
 
    System.out.println("Added new Stock: " + element.name + " at position: " + hash);

    }
}
