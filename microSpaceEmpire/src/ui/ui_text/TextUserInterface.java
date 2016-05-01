package ui.ui_text;

import java.io.IOException;
import java.util.Scanner;
import model.Game;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;
import model.data.EmptyException;
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
        while (!(game.getState() instanceof Ending)) {
            if (game.getState() instanceof AwaitBeginning) {
                WhileAwaitingBeginning();
            }
            if (game.getState() instanceof AwaitOption) {
                WhileAwaitingOption();
            }
            if (game.getState() instanceof Collecting) {
                WhileCollecting();
            }
            if (game.getState() instanceof Upgrading) {
                Upgrading u = (Upgrading) game.getState();
                WhileUpgrading(u);
            }
        }

        System.out.println("");
        System.out.println("Game Over");
    }

    //incompleto 
    public void WhileAwaitingBeginning() {
        //showGame();
        game.start();
    }

    //incompleto
    public void WhileAwaitingOption() {
        int opt;
        this.showGame();

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

        if (opt == 1) {
            System.out.println("Explore near system (1) or distant system (2) ?");
            System.out.print("\n>> ");

            int sc = s.nextInt();
            this.clearScreen();

            if (sc == 1) //game.exploreAttack(SystemType.NEAR_SYSTEM);
            {
                this.uiExploreAttack(SystemType.NEAR_SYSTEM);
            } else {
                this.uiExploreAttack(SystemType.DISTANT_SYSTEM);
            }
        }
        if (opt == 2) {
            
            if(game.getDataGame().getUnalignedSystems().size() != 0){
                    System.out.println("You want try conquer a near system (1) or distant system (2)?");
                    System.out.print("\n>> ");

                    int sc = s.nextInt();
                    this.clearScreen();

                    if(sc == 1)
                        this.uiConquer(SystemType.NEAR_SYSTEM);
                    else{
                        if(game.getDataGame().checkUnalignedDistantSystems(SystemType.DISTANT_SYSTEM) == true)
                        this.uiConquer(SystemType.DISTANT_SYSTEM);
                    }
            }else{
                System.out.println("NOTHING TO CONQUER!!!\n");
            }
            
        }
        
        if (opt == 3) 
            game.pass();
        
        if (opt == 0) 
            game.gameOver();
        
    }

    /*
    *     
    **/
    
    private void uiExploreAttack(SystemType s) {

        if (s == SystemType.NEAR_SYSTEM) {
            try {
                NearSystem ns = game.getNearSystem();
                System.out.println(ns);
                int randomForce = game.getDiceNumber();
                int actualForce = game.getActualForce() + randomForce;
                System.out.println("\nMilitary force (actual): " + actualForce);
                game.exploreAttack(ns, actualForce);
            } catch (EmptyException e) {
                System.err.println(e);
            }

        } else if (s == SystemType.DISTANT_SYSTEM) {
            if (game.verifyNearSystemsOnUnalignedSystems() && game.isTechnologyPurchased("Forward Starbases")) {
                try {
                    DistantSystem ds = game.getDistantSystem();
                    System.out.println(ds);
                    int randomForce = game.getDiceNumber();
                    int actualForce = game.getActualForce() + randomForce;
                    System.out.println("\nMilitary force (actual): " + actualForce);
                    game.exploreAttack(ds, actualForce);

                } catch (EmptyException e) {
                    System.err.println(e);
                }

            } else {
                System.out.println("Not possible explore Distant Systems yet!\n\n");
            }

        } else {
            System.out.println("[ERROR]: Type of system."); // Never should happen.
        }
    }

    private void uiConquer(SystemType s){
        
        for(int i=0; i< game.getDataGame().getUnalignedSystemsSize(); i++){
            
            
            if(s == game.getUnalignedSystemCardType(i)){

                System.out.println(i+1 +" - " + game.getDataGame().getUnalignedSystems().get(i).getName()+ 
                         ", Resistence: " +  game.getDataGame().getUnalignedSystems().get(i).getResistance());
            }
        }   
            
        System.out.println("What is the planet you want to conquer?");
        System.out.println(">> ");
            
        int opt = this.s.nextInt()-1;
                
        if (s == game.getUnalignedSystemCardType(opt)) {
            try {
                System.out.println("\nActual militar stregth: "+ game.getActualForce());
                
                SystemCard ns = game.getUnaligedNearSystemCard(opt);
                int randomForce = game.getDiceNumber();
                int actualForce = game.getActualForce() + randomForce;
                System.out.println("\nMilitary force (actual): " + actualForce);
                game.conquer(ns, actualForce);
            }catch(ArrayIndexOutOfBoundsException e){
                
            }
        }
    }
    
    
    /**
    *
    */
    
    public void WhileCollecting() {

        game.collectResources();

        if (game.isTechnologyPurchased("Interspecies Commerce")) {
            
            showGame();
            
            System.out.println("Exchange 2 units for 1 of: ");
            System.out.println("1 - Metal for wealth");
            System.out.println("2 - Wealth for metal");
            System.out.println("3 - Pass");

            while (s.hasNextInt()) {
                s.next();
            }
            int opt = s.nextInt();
            
            if(opt == 3)
                game.pass();

            game.change(opt);
        }
        game.pass();

    }

    public void WhileUpgrading(Upgrading u) {
        int opt;

        boolean m = u.getMilitary();
        boolean t = u.getTechnology();

        System.out.println("");
        System.out.println("Build Military e Discover Technology phase");
        System.out.println("");

        if (m == true) {
            System.out.println("1 - Build Military");
        }
        if (t == true) {
            System.out.println("2 - Discover Technology");
        }

        System.out.println("3 - Pass");
        System.out.println("0 - Exit");
        System.out.print("\n>> ");

        opt = s.nextInt();
        clearScreen();

        if (opt == 1 && m == true) {
            u.setMilitary(false);
            this.upgradeMilitary();
        } else if (opt == 2 && t == true) {
            u.setTechnology(false);
            showTechnologies();
            game.discoverTechnology();
        } else if (opt == 3) {
            u.setMilitary(true);
            u.setTechnology(true);
            eventphase();
            game.newTurn();
        } else {
            game.gameOver();
        }
    }

    /**
     * functions
     */
    public void showGame() {
        System.out.println(game);
    }

    public void showTechnologies() {

        System.out.println("Technologies:\n");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(game.getDataGame().getTechnology()[i][j]);

            }
        }
    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //processamento da fase de eventos
    private void eventphase() {
        //rever
        System.out.println(game.getDataGame().getEvents().get(0));
    }

    private void upgradeMilitary() {
        game.getDataGame().swapResourcesForMilitary();
        game.buildMilitary();    
    }

}
