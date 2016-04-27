
package model.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.data.Cards.EventCard.EventCard;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.StartingSystem;


public class DataGame implements Constants{
    
    static int turn;
    int metalStorage;
    int walthStorage;
    int militaryStrenght;
    
    // Arraylists to save card games
    private List<EventCard> events;
    private List<NearSystem> nearSystems;
    private List<DistantSystem> distantSystems;
    private StartingSystem startingSystem;
    
    public DataGame(){
        this.events = new ArrayList<>();
        this.nearSystems = new ArrayList<>();
        this.distantSystems = new ArrayList<>();
        this.startingSystem = new StartingSystem();
        
        try {
            buildStartingSystemFromFile(STARTING_SYSTEM_FILE);
        } catch (IOException ex) {}
        
        turn=1;
    }
    
    public void countTurn(){
        this.turn++;
    }
    
    public int getTurn(){
        return this.turn;
    }

    public StartingSystem getStartingSystem() {
        return startingSystem;
    }

    public void setStartingSystem(StartingSystem startingSystem) {
        this.startingSystem = (StartingSystem)startingSystem;
    }
    
    
    
    public void buildStartingSystemFromFile(String fileName) throws FileNotFoundException, IOException{
        
        System.out.println(">> buildStartingSystemFromFile");
        
        FileInputStream fin = new FileInputStream(new File(fileName));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = "";
        
      
        while( ( line = br.readLine() ) != null){
            Scanner s = new Scanner(line);
            s.useDelimiter("\"");
            
            System.out.println(">> " + line);
            System.out.println();
           
//            System.out.println(s.next());
//            System.out.println(s.next());
//            System.out.println(s.next());
           
            System.out.println();
            
            //setStartingSystem(CardFactory.buildCardSystem(SystemType.STARTING_SYSTEM));
        }
        
        br.close();
    }

}
