/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author alexe
 */
public class Jogador {
    private Leitor parser;
    
    private Stack<Sala> previousSalas;
    private Jogador jogador;
    private Item item;  
    private Sala currentSala;
    private HashMap<String, Item> mochila;
// currentRoom
    // salas anteriores
    
      public void addItem(String nomeDoItem){
        
         item = currentSala.getItem(nomeDoItem);
         if(item == null){
             
         }else{
             mochila.put(item);
         }
          // item = salaAtual.getItem(nomeDoItem)
          // se item é nulo erro
          // senao adiciona item na mochila
          
          // throw Exception("Item não existe");
          
          
          /*if (currentSala.equals("salao")){
            arrayItens.put("Capa de invisibilidade", item);
        } else if (currentSala.equals("cozinha")) {
            arrayItens.put("Biscoito", item);
        } else {
            System.out.println("Não existe objeto disponível para pegar.");
        }*/
    }
}
