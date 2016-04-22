
package ui.ui_text;

import java.util.Scanner;
import model.Game;
import model.states.AwaitBeginning;
import model.states.AwaitOption;
import model.states.Ending;

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
        
        System.out.println("1 - Explore-Attack");
        System.out.println("2 - Conquer");
        System.out.println("3 - Pass");
        
        while(this.s.hasNextInt()) 
            s.next();
            
        opt = s.nextInt();
        
        if(opt == 3)
            game.pass();
        /*****
          restantes submenus aqui
        *****/
    }
}
