/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hogwarts;

import java.util.Stack;

/**
 *
 * @author sahra.flohr
 */
public class Jogo {
    private Leitor parser;
    private Sala currentSala;
    private Stack<Sala> previousSalas;
        
    /**
     * Cria o jogo e inicializa o mapa interno.
     */
    public Jogo() 
    {
        createSalas();
        parser = new Leitor();
        previousSalas = new Stack<>();
    }

    /**
     * Cria todas as salas e liga suas saídas.
     */
    private void createSalas()
    {
        Sala outside, theatre, pub, lab, office, attic;
        Item table, chair, tv;
      
        // cria os itens
        table = new Item("uma mesa", 50);
        chair = new Item("uma cadeira", 20);
        tv = new Item("uma TV", 15);
        
        // create the salas
        outside = new Sala("fora da entrada principal da universidade");
        theatre = new Sala("em um auditório");
        pub = new Sala("na cantina do campus", table);
        lab = new Sala("em um laboratório de informática", chair);
        office = new Sala("na sala dos professores", tv);
        attic = new Sala("no sótão do laboratório");
        
        // initialise sala exits
        outside.setExit("leste", theatre);
        outside.setExit("sul", lab);
        outside.setExit("oeste", pub);
        
        theatre.setExit("oeste", outside);
        
        pub.setExit("leste", outside);
        
        lab.setExit("norte", outside);
        lab.setExit("leste", office);
        lab.setExit("cima", attic);
        
        attic.setExit("baixo", lab);
        
        office.setExit("oeste", lab);

        currentSala = outside;  // Começa o jogo fora 
    }

    /**
     *  A rotina de jogo principal. Faz um loop até o fim do jogo.
     */
    public void play() 
    {            
        printWelcome();

        // Entra o loop principal. Aqui lemos comandos repetidamente e 
        // os executamos até que o jogo termime.
                
        boolean finished = false;
        while (! finished) {
            Comando command = parser.getComando();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar.  Adeus.");
    }

    /**
     * Imprime a mensagem de boas vindas ao usuário.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo ao Mundo de Zuul!");
        System.out.println("Mundo de Zuul é um jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();
        
        printLocationInfo();
        
    }
    
    /**
     * Imprime informação do local atual.
     */
    private void printLocationInfo()
    {
        System.out.println(currentSala.getLongDescription());
    }

    /**
     * Dado um comando, processa (ou seja: executa) o comando.
     * @param command O comando a ser processado.
     * @return true Se o comando finaliza o jogo, senão, falso.
     */
    private boolean processCommand(Comando command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Não sei o que você quer dizer...");
            return false;
        }

        String commandWord = command.getComandoWord();
        if (commandWord.equals("ajuda"))
            printHelp();
        else if (commandWord.equals("ir_para"))
            goSala(command);
        else if (commandWord.equals("sair"))
            wantToQuit = quit(command);
        else if (commandWord.equals("examinar"))
            look();
        else if (commandWord.equals("comer"))
            eat();
        else if (commandWord.equals("voltar"))
            returnSala(command);

        return wantToQuit;
    }
    
    public void eat() {
        System.out.println("Você comeu e agora não está mais com fome.");
    }

    // implementations of user commands:

    /**
     * Imprime informações de ajuda.
     * Aqui imprimimos ua mensagem estúpida e listamos os comandos
     * disponíveis.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdido. Você está só. Você caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Seus comandos são:");
        System.out.println("   " + parser.getComandoList());
    }
    
    private void returnSala(Comando command)
    {
        if (command.hasSecondWord()) {
            System.out.println("Voltar o quê?");
        } else {
            if (previousSalas.empty()) {
                System.out.println("Você já está no início!");
            } else {
                currentSala = previousSalas.pop();

                printLocationInfo();
            }
        }         
    }
    
    /** 
     * Tenta ir para uma direção. Se há uma saída, entra na
     * nova sala, senão imprime uma mensagem de erro.
     */
    private void goSala(Comando command) 
    {
        if(!command.hasSecondWord()) {
            // se não há segunda palavra, não sabemos onde ir...
            System.out.println("Ir para onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current sala.
        Sala nextSala = currentSala.getExit(direction);

        if (nextSala == null) {
            System.out.println("Não há uma porta!");
        }
        else {
            previousSalas.push(currentSala);
            currentSala = nextSala;
            printLocationInfo();
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando para saber
     * se o usuário quer realmente sair do jogo.
     * @return true, se este comando sair do jogo, falso caso contrário.
     */
    private boolean quit(Comando command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair de do quê?");
            return false;
        }
        else {
            return true;  // significa que queremos sair
        }
    }

    private void look() {
        printLocationInfo();
    }
}
