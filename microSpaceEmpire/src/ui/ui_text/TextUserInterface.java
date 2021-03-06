package ui.ui_text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.Game;
import model.data.Cards.SystemCard.SystemType;
import model.data.Constants;
import model.states.AwaitBeginning;
import model.states.AwaitOption;
import model.states.Collecting;
import model.states.Ending;
import model.states.Upgrading;

public class TextUserInterface implements Constants {
    
        static final long serialVersionUID = 1l;

    private Game game;
    private Scanner s;

    public TextUserInterface() throws IOException {
        this.game = new Game();
        s = new Scanner(System.in);
    }

    public void run() {

        while (!(game.getState() instanceof Ending)) {
            if (game.getState() instanceof AwaitBeginning) {
                clearScreen();
                WhileAwaitingBeginning();
            }
            if (game.getState() instanceof AwaitOption) {
                clearScreen();
                WhileAwaitingOption();
            }
            if (game.getState() instanceof Collecting) {
                clearScreen();
                WhileCollecting();
            }
            if (game.getState() instanceof Upgrading) {
                clearScreen();
                Upgrading u = (Upgrading) game.getState();
                WhileUpgrading(u);
            }
        }

        System.out.println(game.getDataGame().getLog());

        System.out.println("");
        System.out.println("Game Over");
    }

    public void WhileAwaitingBeginning() {;

        System.out.println("MICRO SPACE EMPIRE");
        System.out.println("1-NEW GAME");
        System.out.println("2-LOAD GAME");
        System.out.print("\n>> ");
        while (!s.hasNextInt()) {
            s.hasNext();
        }

        int opt = s.nextInt();

        if (opt == 1) {
            game.start();
        }
        if (opt == 2) {
            this.loadGame();
            //return;
            //game.setState(game.loadGame().getState());
        }

    }

    public void WhileAwaitingOption() {
        int opt;

        System.out.println(game.getLog());
        game.refreshlog();

        this.showGame();

        System.out.println("");
        System.out.println("Explore-Attack/ Bide Time/ Conquer phase");
        System.out.println("");

        System.out.println("1 - Explore-Attack");
        System.out.println("2 - Conquer");
        System.out.println("3 - Pass");
        System.out.println("\n\n4 - SaveGame");
        System.out.println("0 - Quit");
        System.out.print("\n>> ");

        while (!s.hasNextInt()) {
            s.next();
        }

        opt = s.nextInt();
        clearScreen();

        if (opt == 1) {
            System.out.println("Explore near system (1) or distant system (2) ?");
            System.out.print("\n>> ");

            while (!s.hasNextInt()) {
                s.next();
            }

            int sc = s.nextInt();
            this.clearScreen();

            if (sc == 1) //game.exploreAttack(SystemType.NEAR_SYSTEM);
            {
                game.exploreAttack(SystemType.NEAR_SYSTEM);
            } else {
                game.exploreAttack(SystemType.DISTANT_SYSTEM);
            }
        }
        if (opt == 2) {

            if (game.getDataGame().getUnalignedSystems().isEmpty()) {
                this.uiConquer();
            } else {
                System.out.println("NOTHING TO CONQUER!!!\n");
            }
        }

        if (opt == 3) {
            game.pass();
        }
        if (opt == 4) {
            this.saveGame();
        }
        if (opt == 0) {
            game.gameOver();
        }

    }

    private void uiConquer() {

        for (int i = 0; i < game.getDataGame().getUnalignedSystemsSize(); i++) {
            System.out.println(i + 1 + " - " + game.getDataGame().getUnalignedSystems().get(i).getName()
                    + ", Resistence: " + game.getDataGame().getUnalignedSystems().get(i).getResistance());
        }

        System.out.println("What is the planet you want to conquer?");
        System.out.print(">> ");

        while (!s.hasNextInt()) {
            s.next();
        }
        int opt = this.s.nextInt() - 1;

        try {
            game.conquer(opt);
        } catch (ArrayIndexOutOfBoundsException e) {
            clearScreen();
            System.err.printf("Opção Inválida!\n");
        }

    }

    /**
     *
     */
    public void WhileCollecting() {

        //game.collect();
        System.out.println(game.getLog());
        game.refreshlog();

        if (game.isTechnologyPurchased("Interspecies Commerce")) {

            showGame();
            System.out.println("\n\nCollecting Phase: ");
            System.out.println("Exchange 2 units for 1 of: ");
            System.out.println("1 - Metal for wealth");
            System.out.println("2 - Wealth for metal");
            System.out.println("3 - Pass");
            System.out.println("\n\n4 - SaveGame");
            System.out.println("0 - Exit");
            System.out.print(">> ");

            while (!s.hasNextInt()) {
                s.next();
            }

            int opt = s.nextInt();

            game.change(opt);
            if (opt == 4) {
                this.saveGame();
            }
            if(opt == 3){
                game.pass();
            }
            if (opt == 0) {
                game.end();
            }

        } else {
            game.pass();
        }

    }

    public void WhileUpgrading(Upgrading u) {
        int opt;

        boolean m = u.getMilitary();
        boolean t = u.getTechnology();

        System.out.print(game.getLog());
        game.refreshlog();

        System.out.println("");
        System.out.println("Build Military e Discover Technology phase");
        System.out.println("");

        if (m == false && t == false) {
            game.newTurn();
        }

        if (m == true) {
            System.out.println("1 - Build Military");
        }
        if (t == true) {
            System.out.println("2 - Discover Technology");
        }

        System.out.println("3 - Pass");
        System.out.println("\n\n4 - SaveGame");
        System.out.println("0 - Exit");
        System.out.print("\n>> ");

        while (!s.hasNextInt()) {
            s.next();
        }

        opt = s.nextInt();
        clearScreen();

        if (opt == 1 && m == true) {
            game.buildMilitary();
        } else if (opt == 2 && t == true) {
            showTechnologies();
        } else if (opt == 3) {
            game.newTurn();
        } else if (opt == 4) {
            this.saveGame();
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
        String tecName = "";
        s.nextLine();

        System.out.println("Technologies:\n");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0 && game.getTechnology(i, j).isBought() == false || j == 1 && game.getTechnology(i, j - 1).isBought() == true) {
                    System.out.print("Wealth - " + game.getTechnology(i, j).getCost()
                            + " " + game.getTechnology(i, j) + "\n");
                }
            }
        }

        System.out.println("\nType the name of the technology");
        System.out.print(">> ");

        tecName = s.nextLine();
        game.discoverTechnology(tecName);

    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Functions to save and load game on file
     */
    boolean loadGame() {
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String opt, name = FILE_SAVE_GAME;

        System.out.println("Default file name: (\"" + name + "\")");

        try {
            opt = bin.readLine();

            if (opt.length() >= 1) {
                if (!new java.io.File(opt).exists()) {
                    System.out.println(" File \"" + opt + "\" does not exist!");
                    return false;
                }
                name = opt;
            }

        } catch (Exception e) {
        }

        try {
            game = Game.loadGame(name);
            System.out.println("Load complete.");
            System.out.println();
            return true;
        } catch (Exception e) {
            System.err.println("Error trying loading game from: \"" + name + "\"!");
            System.err.println(e);
            System.out.println();
            return false;
        }

    }

    boolean saveGame() {
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String opt, name = FILE_SAVE_GAME;

        System.out.println("Default file name: (\"" + name + "\")");

        try {
            opt = bin.readLine();

            if (opt.length() >= 1) {
                name = opt;
            }
        } catch (Exception e) {
            return false;
        }
        
        try {
            game.saveGame(name);
            System.out.println("Game saved.");
            System.out.println();
            return true;
        } catch (Exception e) {
            System.out.println("Error trying loading game from: \"" + name + "\"!");
            System.out.println(e);
            System.out.println();
            return false;
        }

    }
}
