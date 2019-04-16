/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Symbols;

import java.util.ArrayList;

/**
 *
 * @author Binod
 */
public class Tup extends Rand {
    public ArrayList<Symbol> symbols;
    
    public Tup() {
        super("tup");
        this.symbols = new ArrayList<Symbol>();
    }
}
