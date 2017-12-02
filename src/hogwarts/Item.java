/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;
import java.util.ArrayList;
/**
 *
 * @author sahra.flohr
 */
public class Item {
    private String description;
    private int qntd;
    ArrayList<String> arrayItens = new ArrayList<String>();
   
    public Item(String description, int qntd) {
        this.description = description;
        this.qntd = qntd;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getLongDescription() {
        return description + " que pesa " + qntd;
    }
    
    public void add(String item){
        arrayItens.add(item);
        for (String s : arrayItens) {
            System.out.println(s);
        }
    }
    
    
}
