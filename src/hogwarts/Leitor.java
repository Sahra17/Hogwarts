/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;

import java.util.Scanner;

/**
 *
 * @author sahra.flohr
 */
public class Leitor {
    private Palavras comandos;  // contém os comandos válidos
    private Scanner reader;         // origem da entrada de comandos

    /**
     * Cria um parser para ler da janela de terminal.
     */
    public Leitor() 
    {
        comandos = new Palavras();
        reader = new Scanner(System.in);
    }

    /**
     * @return O próximo comando do usuário.
     */
    public Comando getComando() 
    {
        String inputLine;   //  vai conter a entrada completa
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // imprime o prompt

        inputLine = reader.nextLine();

        // Encontra até duas palavras na linha.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // pega a segunda palavra
                // nota: simplesmente ignoramos o resto da linha.
            }
        }

        // Agora checa se esta palavra é conhecida. Caso positivo, cria um
        // comando com ela. Se não, cria um comando "null" 
        // (para comando desconhecido).
        if(comandos.isComando(word1)) {
            return new Comando(word1, word2);
        }
        else {
            return new Comando(null, word2); 
        }
    }
    
    public String getComandoList()
    {
        return comandos.getComandoList();
    }
}
