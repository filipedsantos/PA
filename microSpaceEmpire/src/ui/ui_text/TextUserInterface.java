
package ui.ui_text;

import java.io.IOException;
import java.util.Scanner;
import model.Game;
import model.data.Cards.CardType;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemType;
import model.states.AwaitBeginning;
import model.states.AwaitOption;
import model.states.Collecting;
import model.states.Ending;
import model.states.Upgrading;

public class TextUserInterface {
    
    private Game game;
    private Scanner s;

    public TextUserInterface() throws IOException {
        this.game = new Game();
        s = new Scanner(System.in);
    }

    public void run() {
        
        clearScreen();
        while(!(game.getState() instanceof Ending)){
            if(game.getState() instanceof AwaitBeginning)
                getUserInputWhileAwaitingBeginning();
            if(game.getState() instanceof AwaitOption)
                getUserInputWhileAwaitingOption();
            if(game.getState() instanceof Collecting)
                getUserInputWhileCollecting();
            if(game.getState() instanceof Upgrading){
                Upgrading u = (Upgrading)game.getState();
                getUserInputWhileUpgrading(u);
            }
        }
        
        System.out.println("");
        System.out.println("Game Over");
    }
    
    //incompleto 
    public void getUserInputWhileAwaitingBeginning(){
        showGame();
        game.start();
    }
    
    //incompleto
    public void getUserInputWhileAwaitingOption(){
        int opt;
        
        System.out.println("");
        System.out.println("Explore-Attack/ Bide Time/ Conquer phase");
        System.out.println("");
        
        System.out.println("1 - Explore-Attack");
        System.out.println("2 - Conquer");
        System.out.println("3 - Pass");
        System.out.println("0 - Quit");
        System.out.print("\n>> ");
        
        opt = s.nextInt();
        clearScreen();
        
            if(opt == 1){
                System.out.println("Explore near system (1) or distant system (2) ?");
                System.out.print("\n>> ");
                
                int sc = s.nextInt();
                
                if(sc == 1)
                    game.exploreAttack(SystemType.NEAR_SYSTEM);
                else
                    game.exploreAttack(SystemType.DISTANT_SYSTEM);
            }
            if(opt == 2){
                game.conquer();
            }
            if(opt == 3){
                game.pass();
            }
            if(opt == 0)
                game.gameOver();
        /*****
          restantes submenus aqui
        *****/
    }
    
    public void getUserInputWhileCollecting(){
        game.pass();
    }
    
    public void getUserInputWhileUpgrading(Upgrading u){
        int opt;
        
        boolean m = u.getMilitary();
        boolean t = u.getTechnology();
        
        System.out.println("");
        System.out.println("Build Military e Discover Technology phase");
        System.out.println("");
        
        if( m == true)
            System.out.println("1 - Build Military");
        if (t == true)
            System.out.println("2 - Discover Technology");
        
        System.out.println("3 - Pass");
        System.out.println("0 - Exit");
        System.out.print("\n>> ");
        
        opt = s.nextInt();
        clearScreen();
        
            if(opt == 1 && m == true){
                u.setMilitary(false);
                game.buildMilitary();
            }
            else if(opt == 2 && t == true){
                u.setTechnology(false);
                showTechnologies();
                game.discoverTechnology();
            }
            else if(opt == 3){
                u.setMilitary(true);
                u.setTechnology(true);
                eventphase();
                game.newTurn();
            }
            else
                game.gameOver(); 
    }
    
    /**
    *functions
    */
    
    public void showGame(){
        System.out.println(game);
    }
    
    public void showTechnologies(){
        
        System.out.println("technologies:\n");
        for(int i = 0; i<4; i++){
            for(int j = 0; j<2; j++){
                System.out.println(game.getDataGame().getTechnology()[i][j]);
                
            }
        }
    }
    
    public void clearScreen(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }    

    //processamento da fase de eventos
    private void eventphase() {
        //rever
        System.out.println(game.getDataGame().getEvents().get(0));
    }
        
}
