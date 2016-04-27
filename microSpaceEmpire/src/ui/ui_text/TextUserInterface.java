
package ui.ui_text;

import java.util.Scanner;
import model.Game;
import model.states.AwaitBeginning;
import model.states.AwaitOption;
import model.states.Collecting;
import model.states.Ending;
import model.states.Upgrading;

public class TextUserInterface {
    
    private Game game;
    private Scanner s;

    public TextUserInterface() {
        this.game = new Game();
        s = new Scanner(System.in);
    }

    public void run() {
        
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
        System.out.println("estive no primeiro estado vou sair para o proximo");
        game.start();
    }
    
    //incompleto
    public void getUserInputWhileAwaitingOption(){
        int opt;
        
        System.out.println("");
        System.out.println(game.getDataGame().getTurn() +" Turn");
        System.out.println("Explore-Attack, Bide Time/ Conquer phase");
        System.out.println("");
        
        System.out.println("1 - Explore-Attack");
        System.out.println("2 - Conquer");
        System.out.println("3 - Pass");
        System.out.print(">> ");
        
        opt = s.nextInt();
        clearScreen();
        
            if(opt == 1)
                game.exploreAttack();
            if(opt == 2)
                game.conquer();
            if(opt == 3)
                game.pass();
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
        System.out.print(">> ");
        
        opt = s.nextInt();
        clearScreen();
        
        
            if(opt == 1 && m == true){
                u.setMilitary(false);
                game.buildMilitary();
            }
            else if(opt ==2 && t == true){
                u.setTechnology(false);
                game.discoverTechnology();
            }
            else{
                game.newTurn();
                game.getDataGame().countTurn();
            }
        
    }
        
    public void clearScreen(){
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }    
}
