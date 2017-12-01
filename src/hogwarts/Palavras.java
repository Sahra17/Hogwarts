/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;

/**
 *
 * @author sahra.flohr
 */
public class Palavras {
    // um array constante que contém todos os comandos válidos
    private static final String[] VALID_COMMANDS = {
        "ir_para", "sair", "ajuda", "examinar", "comer", "voltar"
    };

    /**
     * Construtor - inicializa os comandos
     */
    public Palavras()
    {
        // nada a fazer no momento...
    }
    
    public String getComandoList()
    {
        return String.join(" ", VALID_COMMANDS);
    }

    /**
     * Checa se uma string é uma palavra válida. 
     * @param aString uma string 
     * @return true se a string é um comando válido, falso se não
     */
    public boolean isComando(String aString)
    {
        for (String comando : VALID_COMMANDS) {
            if (comando.equals(aString)) {
                return true;
            }
        }
        // se chegamos aqui, a string não foi encontrada nos comandos
        return false;
    }
}
