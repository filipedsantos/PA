package model.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

public class DataGame implements Constants {

    static int turn;
    
    //Board Game iNFO
    int metalStorage;
    int wealthStorage;
    int militaryStrenght;
    int metalProduction;
    int wealthProduction;

    // Arraylists to save card games
    private List<NearSystem> nearSystems;
    private List<DistantSystem> distantSystems;
    private List<SystemCard> empire;
    private List<SystemCard> unalignedSystems;
    private List<EventCard> events;

    public DataGame() throws IOException {
        this.nearSystems = new ArrayList<>();
        this.distantSystems = new ArrayList<>();
        this.empire = new ArrayList<>();
        this.unalignedSystems = new ArrayList<>();
        this.events = new ArrayList<>();
        
        // Read System Cards from files
        buildStartingSystemFromFile(this, STARTING_SYSTEM_FILE);
        buildNearSystemsFromFile(this, NEAR_SYSTEMS_FILE);
        buildDistantSystemsFromFile(this, DISTANT_SYSTEMS_FILE);
        
        // Read Event Cards 
        
        // Shuffle cards
        Collections.shuffle(nearSystems);
        Collections.shuffle(distantSystems);
        Collections.shuffle(events);

        turn = 1;
        this.metalProduction=1;
        this.wealthProduction=1;
        this.metalStorage=0;
        this.wealthStorage=0;
        this.militaryStrenght=0;
    }

    /**
     *
     * Gets and Sets
     *
     */
    public int getMetalProduction() {
        return metalProduction;
    }
    
    public void setMetalProduction(int metalP) {
        this.metalProduction = metalP;
    }
    
    public int getMetalStorage() {
        return metalStorage;
    }

    public void setMetalStorage(int metalStorage) {
        this.metalStorage = metalStorage;
    }

     public int getWealthProduction() {
        return wealthProduction;
    }

    public void setWealthProduction(int wealthP) {
        this.wealthProduction = wealthP;
    }
    
    public int getWealthStorage() {
        return wealthStorage;
    }

    public void setWealthStorage(int wealthStorage) {
        this.wealthStorage = wealthStorage;
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

    private boolean addNearSystems(NearSystem n) {
        return nearSystems.add(n);
    }
    
    public int getNearSystemsSize(){
        return nearSystems.size();
    }

    public List<DistantSystem> getDistantSystems() {
        return distantSystems;
    }

    public void setDistantSystems(List<DistantSystem> distantSystems) {
        this.distantSystems = distantSystems;
    }

    private boolean addDistantSystems(DistantSystem n) {
        return distantSystems.add(n);
    }
    
    public int getDistantSystemsSize(){
        return distantSystems.size();
    }

    public List<SystemCard> getEmpire() {
        return empire;
    }

    public void setEmpire(List<SystemCard> empire) {
        this.empire = empire;
    }

    public boolean addEmpire(SystemCard c) {
        return empire.add(c);
    }
    
    public int getEmpireSize(){
        return empire.size();
    }

    public List<SystemCard> getUnalignedSystems() {
        return unalignedSystems;
    }

    public void setUnalignedSystems(List<SystemCard> unalignedSystems) {
        this.unalignedSystems = unalignedSystems;
    }
    
    public boolean addUnalignedSystems(SystemCard c){
        return this.unalignedSystems.add(c);
    }
    
    public int getUnalignedSystemsSize() {
        return this.unalignedSystems.size();
    }

    public List<EventCard> getEvents() {
        return events;
    }

    public void setEvents(List<EventCard> events) {
        this.events = events;
    }

    public void countTurn() {
        this.turn++;
    }

    public int getTurn() {
        return this.turn;
    }

    /**
     *
     * Functions
     *
     */
    public void buildStartingSystemFromFile(DataGame d, String fileName) throws FileNotFoundException, IOException {

        FileInputStream fin = new FileInputStream(new File(fileName));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = null;

        while ((line = br.readLine()) != null) {
            Scanner s = new Scanner(line);
            s.useDelimiter("\"");

            String cardName = s.next().trim();
            int cardType = Integer.parseInt(s.next().trim());
            int systemType = Integer.parseInt(s.next().trim());
            int resistance = Integer.parseInt(s.next().trim());
            int metalProdution = Integer.parseInt(s.next().trim());
            int wealthProduction = Integer.parseInt(s.next().trim());
            int points = Integer.parseInt(s.next().trim());

            Card card = CardFactory.buildCardSystem(d, SystemType.STARTING_SYSTEM, cardName, cardType, systemType, resistance, metalProdution, wealthProduction, points);
            //System.out.print(card.toString());
            this.addEmpire((SystemCard) card);
        }

        br.close();
    }

    private void buildNearSystemsFromFile(DataGame d, String fileName) throws FileNotFoundException, IOException {

        FileInputStream fin = new FileInputStream(new File(fileName));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = null;

        while ((line = br.readLine()) != null) {
            Scanner s = new Scanner(line);
            s.useDelimiter("\"");

            String cardName = s.next().trim();
            int cardType = Integer.parseInt(s.next().trim());
            int systemType = Integer.parseInt(s.next().trim());
            int resistance = Integer.parseInt(s.next().trim());
            int metalProdution = Integer.parseInt(s.next().trim());
            int wealthProduction = Integer.parseInt(s.next().trim());
            int points = Integer.parseInt(s.next().trim());

            Card card = CardFactory.buildCardSystem(d, SystemType.NEAR_SYSTEM, cardName, cardType, cardType, resistance, metalProdution, wealthProduction, points);
            //System.out.println(card.toString());
            this.addNearSystems((NearSystem) card);
        }
        br.close();
    }

    private void buildDistantSystemsFromFile(DataGame d, String fileName) throws FileNotFoundException, IOException {

        FileInputStream fin = new FileInputStream(new File(fileName));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = null;

        while ((line = br.readLine()) != null) {
            Scanner s = new Scanner(line);
            s.useDelimiter("\"");

            String cardName = s.next().trim();
            int cardType = Integer.parseInt(s.next().trim());
            int systemType = Integer.parseInt(s.next().trim());
            int resistance = Integer.parseInt(s.next().trim());
            int metalProdution = Integer.parseInt(s.next().trim());
            int wealthProduction = Integer.parseInt(s.next().trim());
            int points = Integer.parseInt(s.next().trim());

            Card card = CardFactory.buildCardSystem(d, SystemType.DISTANT_SYSTEM, cardName, cardType, cardType, resistance, metalProdution, wealthProduction, points);
            //System.out.println(card.toString());
            this.addDistantSystems((DistantSystem) card);
        }
        br.close();
    }

    
    /**
     * ToString
     */
    
    @Override
    public String toString() {
        String s;
        
        s = "Metal Storage: " + getMetalStorage();
        s += "\nWealth Storage: " + getWealthStorage();
        s += "\nMilitary Strength: " + getMilitaryStrenght();
        s += "\n\nNear systems: " + getNearSystemsSize();
        s += "\nDistant systems: " + getDistantSystemsSize();
        s += "\nUnaligned systems: " + getUnalignedSystemsSize();
        
        return s;
    }

}
