
package ui;

import java.util.Scanner;
import ui.ui_text.TextUserInterface;

public class UI {
    private String args [] = null;
    private Scanner s;
    private TextUserInterface ui_text = null;
    //private GraphicUserInterface ui_graphic = null;

    public UI(String args[]) {
        this.args = args;
        this.s = new Scanner(System.in);
        this.ui_text = null;
        //this.ui_graphic = null;
    }
    
    // Option to choose wich interface user wants to run
    public void chooseUserInterface(){
        System.out.println("###################################################");
        System.out.println("#                                                 #");
        System.out.println("#  Micro space empire                             #");
        System.out.println("#                                                 #");
        System.out.println("#  1 - Text                                       #");
        System.out.println("#  2 - Graphic                                    #");
        System.out.println("#  3 - Exit                                       #");
        System.out.println("#                                                 #");
        System.out.println("#  Filipe Santos && Miguel Martins                #");
        System.out.println("#                                                 #");
        System.out.println("###################################################");
        System.out.println();
        System.out.print(">> ");
        
        int op;
        
        while(!s.hasNextInt()) s.next();
        
        op = s.nextInt();
        
        switch(op){
            case 1:
                this.textMode();
                return;
                
            case 2:
                this.graphicMode();
                return;
                
            case 3:
                System.exit(0);                    
        }
        
    }
    
    // Start new game with console or run from IDE
    public void verifyArgsFromCommandLine(){
        if(this.args.length > 0){
            if(this.args[0].toUpperCase().equals("/T") == true) // Start game in text mode
                this.textMode();
            else if(this.args[0].toUpperCase().equals("/G") == true) // Start game in graphic mode
                this.graphicMode();
            else if(this.args[0].toUpperCase().equals("/M") == true) // Choose mode to start game
                this.chooseUserInterface();
            else
                this.showHelp();    // Show help menu        
        }
        else{
            this.chooseUserInterface();
        }
    }
    
    private void showHelp(){
        System.out.println("## Micro space empire ");
        System.out.println();
        System.out.println(" /T  Start game with text mode..");
        System.out.println(" /G  Start game with graphic mode..");
        System.out.println(" /H  Show help..");
    }

    private void textMode() {
        this.ui_text = new TextUserInterface();
        this.ui_text.run();
    }

    private void graphicMode() {
//        this.ui_graphic = new GraphicUserInterface();
//        this.ui_graphic.run();
    }
    
    
    
}
