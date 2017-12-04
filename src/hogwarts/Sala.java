/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;

import java.util.HashMap;

/**
 *
 * @author sahra.flohr
 */
public class Sala {
    private String description;
    private HashMap<String, Sala> exits;
    private Item item;

    /**
     * Create a sala described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The sala's description.
     * @param item
     */
    public Sala(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        this.item = null;
    }
    
    public Sala(String description, Item item)
    {
        this(description);
        this.item = item;    
    }
    
    public void setItem(Item item) {
        this.item = item;
    }
    
    public Item getItem(Item nomeDoItem){
        return nomeDoItem;
    }

    /**
     * Define the exits of this sala.  Every direction either leads
     * to another sala or is null (no exit there).
     * @param direction A direção da saída.
     * @param neighbor A sala vizinha.
     */
    public void setExit(String direction, Sala neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the sala.
     */
    
    
    /**
     * Retorna uma das saídas da sala, a partir de uma String.
     * @param direction Uma string com a direção a retornar
     * @return A sala 
     */
    public Sala getExit(String direction)
    {    
        return exits.get(direction);
    }
    
    /**
     * Retorna uma descrição das saídas da sala,
     * por exemplo: "Saídas: norte oeste".
     * @return Uma descrição das saídas disponíveis
     */
    public String getExitString()
    {
        String exitString = "Saídas:";
        for(String exit : exits.keySet()) {
            exitString += " " + exit;
        }
        return exitString;
    }
    
    /**
     * Retorna uma descrição longa dessa sala.
     * Na forma
     *   Você está na cozinha.
     *   Saídas: norte oeste
     * @return Uma descrição da sala, incluindo saídas.
     */
    public String getLongDescription()
    {
        String itemStr = (item != null) 
                ? "Que contém " + item.getDescription() + ".\n"
                : "";
        
        return "Você está " + description + ".\n" +
                itemStr + 
                getExitString();
    }
}
