
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
import model.data.Cards.Card;
import model.data.Cards.CardFactory;
import model.data.Cards.EventCard.EventCard;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.StartingSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;


public class DataGame implements Constants{
    
    static int turn;
    int metalStorage;
    int walthStorage;
    int militaryStrenght;
    
    // Arraylists to save card games
    private List<NearSystem> nearSystems;
    private List<DistantSystem> distantSystems;
    private List<SystemCard> empire;
    private List<SystemCard> unalignedSystems;
    private List<EventCard> events;
    
    public DataGame() throws IOException{
        this.nearSystems = new ArrayList<>();
        this.distantSystems = new ArrayList<>();
        this.empire = new ArrayList<>();
        this.unalignedSystems = new ArrayList<>();
        this.events = new ArrayList<>();
        
        
        buildStartingSystemFromFile(STARTING_SYSTEM_FILE);
        
        turn=1;
    }
    
    /**
     * 
     * Gets and Sets
     * 
     */

    public int getMetalStorage() {
        return metalStorage;
    }

    public void setMetalStorage(int metalStorage) {
        this.metalStorage = metalStorage;
    }

    public int getWalthStorage() {
        return walthStorage;
    }

    public void setWalthStorage(int walthStorage) {
        this.walthStorage = walthStorage;
    }

    public int getMilitaryStrenght() {
        return militaryStrenght;
    }

    public void setMilitaryStrenght(int militaryStrenght) {
        this.militaryStrenght = militaryStrenght;
    }

    public List<NearSystem> getNearSystems() {
        return nearSystems;
    }

    public void setNearSystems(List<NearSystem> nearSystems) {
        this.nearSystems = nearSystems;
    }

    public List<DistantSystem> getDistantSystems() {
        return distantSystems;
    }

    public void setDistantSystems(List<DistantSystem> distantSystems) {
        this.distantSystems = distantSystems;
    }

    public List<SystemCard> getEmpire() {
        return empire;
    }

    public void setEmpire(List<SystemCard> empire) {
        this.empire = empire;
    }
    
    public boolean addEmpire(SystemCard c){
        return empire.add(c);
    }

    public List<SystemCard> getUnalignedSystems() {
        return unalignedSystems;
    }

    public void setUnalignedSystems(List<SystemCard> unalignedSystems) {
        this.unalignedSystems = unalignedSystems;
    }

    public List<EventCard> getEvents() {
        return events;
    }

    public void setEvents(List<EventCard> events) {
        this.events = events;
    }
    
    public void countTurn(){
        this.turn++;
    }
    
    public int getTurn(){
        return this.turn;
    }
    
    /**
     * 
     * Functions 
     *  
     */
    
    public void buildStartingSystemFromFile(String fileName) throws FileNotFoundException, IOException{
        
        FileInputStream fin = new FileInputStream(new File(fileName));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = null;
        
      
        while( ( line = br.readLine() ) != null){
            Scanner s = new Scanner(line);
            s.useDelimiter("\"");
            
            String cardName = s.next().trim();
            int cardType = Integer.parseInt( s.next().trim() );
            int systemType = Integer.parseInt( s.next().trim() );
            int resistance = Integer.parseInt( s.next().trim() );
            int metalProdution = Integer.parseInt( s.next().trim() );
            int wealthProduction = Integer.parseInt( s.next().trim() );
            int points = Integer.parseInt( s.next().trim() );
            
            Card card = CardFactory.buildCardSystem(SystemType.STARTING_SYSTEM, cardName, cardType, systemType, resistance, metalProdution, wealthProduction, points);
            //System.out.print(card.toString());
            this.addEmpire((SystemCard)card);
        }
        
        br.close();
    }

}
