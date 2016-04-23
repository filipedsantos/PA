
package ui.ui_text;

import java.util.Scanner;
import model.Game;
import model.states.AwaitBeginning;
import model.states.AwaitOption;
import model.states.Collecting;
import model.states.Ending;
import model.states.Upgrading;
import ui.UI;

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
            if(game.getState() instanceof Upgrading)
                getUserInputWhileUpgrading();
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
             
        if(opt == 3){
            game.pass();
        }
        /*****
          restantes submenus aqui
        *****/
    }
    
    public void getUserInputWhileCollecting(){
        game.pass();
    }
    
    public void getUserInputWhileUpgrading(){
        int opt;
        
        System.out.println("");
        System.out.println("Build Military e Discover Technology phase");
        System.out.println("");
        
        System.out.println("1 - Build Military");
        System.out.println("2 - Discover Technology");
        System.out.println("3 - Pass");
        System.out.print(">> ");
        opt = s.nextInt();
        clearScreen();
        
        if(opt == 1)
            game.buildMilitary();
        else if(opt ==2)
            game.discoverTechnology();
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
