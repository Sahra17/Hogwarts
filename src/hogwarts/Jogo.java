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
    private Item item;
        
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
        Sala fora, hall, salao, escadaria, torre, cozinha, corredorProibido, salaProibida, salaGrifinoria;
        Item capa, biscoito;
      
        // cria os itens
        capa = new Item("Capa de invisibilidade", 50);
        biscoito = new Item("Biscoito magico", 20);
        
        
        // create the salas
        fora = new Sala("você está na entrada de Hogwarts!");
        hall = new Sala("no hall de entrada");
        salao = new Sala("no Grande Salão", capa);
        escadaria = new Sala("na escadaria");
        torre = new Sala("na torre de astronômia");
        cozinha = new Sala("na cozinha", biscoito);
        corredorProibido = new Sala("no corredor proibido");
        salaProibida = new Sala("na sala proibida");
        salaGrifinoria = new Sala("na salão comunal da grifinória");
        
        // initialise sala exits
        if(item.arrayItens==null){
            fora.setExit("frente", hall);

            hall.setExit("atras", fora);
            hall.setExit("frente", salao);
            hall.setExit("direita", escadaria);

            salao.setExit("sul", hall);

            escadaria.setExit("esquerda", corredorProibido);
            escadaria.setExit("cima", torre);
            escadaria.setExit("direita", salaGrifinoria);
            escadaria.setExit("baixo", cozinha);

            salaGrifinoria.setExit("esquerda", escadaria);

            corredorProibido.setExit("direita", escadaria);
            corredorProibido.setExit("frente", salaProibida);

            cozinha.setExit("cima", escadaria);

            torre.setExit("oeste", escadaria);
        } else if (item.arrayItens)

        currentSala = fora;  // Começa o jogo fora 
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
        System.out.println("AVADA KEDAVRA!");
        /*SE ENTRAR NA SALA PROIBIDA, O JOGADOR MORRE
        if (currentSala.equals(createSalas())){
            System.out.println("Você foi atacado pelo cachorro e morreu!");
        }*/
    }

    /**
     * Imprime a mensagem de boas vindas ao usuário.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo a Hogwarts!");
        System.out.println("Aqui você vai ter a oportunidade de deixar de ser trouxa");
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
        else if (commandWord.equals("comer"))
            eat();
        else if (commandWord.equals("pegar"))
            take();
        else if (commandWord.equals("examinar"))
            look();
        else if (commandWord.equals("voltar"))
            returnSala(command);
        else if (commandWord.equals("largar"))
            drop();

        return wantToQuit;
    }
    
    public void eat() {
        System.out.println("Agora você pode carregar dois itens.");
    }
    //adicionar itens num array 
    public void take(){
       item.add("Capa de invisibilidade");
    }
    //retirar itens num array
    public void drop(){
    
    }

    // implementations of user commands:

    /**
     * Imprime informações de ajuda.
     * Aqui imprimimos ua mensagem estúpida e listamos os comandos
     * disponíveis.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdido e não há o Mapa do Maroto");
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
