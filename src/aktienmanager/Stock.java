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
public class Stock {
    
    public String name;
    public String fullname;
    
    public double[][] course;
    
    public Stock(String name, String fullname) {
        this.name = name;
        this.fullname = fullname;
    }
    
}
