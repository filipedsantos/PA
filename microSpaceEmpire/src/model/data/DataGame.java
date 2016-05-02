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
import model.data.Cards.EventCard.Asteroid;
import model.data.Cards.EventCard.DerelictShip;
import model.data.Cards.EventCard.EventCard;
import model.data.Cards.EventCard.LargeInvasionForce;
import model.data.Cards.EventCard.PeaceAndQuiet;
import model.data.Cards.EventCard.Revolt;
import model.data.Cards.EventCard.SmallInvasionForce;
import model.data.Cards.EventCard.Strike;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;

public class DataGame implements Constants {

    //Board Game iNFO
    private int metalStorage;
    private int wealthStorage;
    private int militaryStrenght;
    private int metalProduction;
    private int wealthProduction;
    
    private String log; // String to save information processed in states
    
    

    //Technologies
    Technology [][] technology;
    
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
        this.log = "";
        
        // Read System Cards from files
        buildStartingSystemFromFile(this, STARTING_SYSTEM_FILE);
        buildNearSystemsFromFile(this, NEAR_SYSTEMS_FILE);
        buildDistantSystemsFromFile(this, DISTANT_SYSTEMS_FILE);
        
        // Shuffle cards
        Collections.shuffle(nearSystems);
        Collections.shuffle(distantSystems);
        Collections.shuffle(events);

        //Board info
        this.metalProduction=1;
        this.wealthProduction=1;
        this.metalStorage=0;
        this.wealthStorage=0;
        this.militaryStrenght=0;
        
        // Create technologies
        this.technology = createTechnologies();
        // Create Event Cards 
        createEventCards(this);
    }
 

    /**
     * Gets and Sets
     */
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    
    public Technology[][] getTechnology() {
        return technology;
    }
    
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
    
    public void addMetalStorage(int m){
        this.metalStorage += m;
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
    
    public void addWealthStorage(int w) {
        this.wealthStorage += w;
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

    public boolean addEvent(EventCard e){
        return this.events.add(e);
    }
    
    public int getEventsSize(){
        return this.events.size();
    }

    /**
     * ToString
     */
    
    @Override
    public String toString() {
        String s;
        
        s = "Info:";
        s += "\nMetal Storage: " + getMetalStorage();
        s += "\nWealth Storage: " + getWealthStorage();
        s += "\nMilitary Strength: " + getMilitaryStrenght();
        s += "\nMetal Production: +" + getMetalProduction();
        s += "\nWealth Production: +" + getWealthProduction();
        s += "\nEmpire: " + getEmpireSize();
        s += "\n\nNear systems: " + getNearSystemsSize();
        s += "\nDistant systems: " + getDistantSystemsSize();
        s += "\nUnaligned systems: " + getUnalignedSystemsSize();
        s += "\nEvents: " + getEventsSize();
        return s;
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

    private Technology[][] createTechnologies() {
        Technology [][] tec = new Technology[4][2];
        
        tec[0][0] = new Technology("Capital Ships", 3, "Advance beyond military strength 3.");
        tec[0][1] = new Technology("Forward Starbases", 4, "Required to explore distant systems.");
        tec[1][0] = new Technology("Robot Workers", 2, "Recive 1/2 production during strike.");
        tec[1][1] = new Technology("Planetary Defenses", 4, "+1 to resistance during invasion.");
        tec[2][0] = new Technology("Hyper Television", 3, "+1 resistence during revolt.");
        tec[2][1] = new Technology("Interstaller Diplomacy", 5, "Next planet is conquered for free.");
        tec[3][0] = new Technology("Interspecies Commerce", 2, "Exchange 2 of one resource for 1 of the other.");
        tec[3][1] = new Technology("Interstellar Banking", 3, "Advance beyond storage value 3.");
       
        return tec;
    }

    private void createEventCards(DataGame dataGame) {
        this.addEvent(new Asteroid(dataGame));
        this.addEvent(new DerelictShip(dataGame));
        this.addEvent(new LargeInvasionForce(dataGame));
        this.addEvent(new PeaceAndQuiet(dataGame));
        this.addEvent(new Revolt(dataGame));
        this.addEvent(new SmallInvasionForce(dataGame));
        this.addEvent(new Strike(dataGame));
    }

    public NearSystem getNearSystems(int i) throws EmptyException{
        if(nearSystems.isEmpty())
            throw new EmptyException("Near system");
        
        return nearSystems.get(i);
    }
      
    public DistantSystem getDistantSystems(int i) throws EmptyException{
        if(distantSystems.isEmpty())
            throw new EmptyException("Distant system");
        
        return distantSystems.get(i);
    }

    public void adjustResources(SystemCard s) {
        this.metalProduction += s.getMetalProdution();
        this.wealthProduction += s.getWealthProdution();
    }

    public void reduceMilitaryForceOneunit() {
        if(militaryStrenght > 0)
            this.militaryStrenght--;
    }

    public boolean verifyNearSystemsOnUnalignedSystems() {
        for (int i = 0; i < getUnalignedSystemsSize(); i++) {
            if(getUnalignedSystems().get(i) instanceof NearSystem)
                return false;
        }
        
        return true;
    }

    public boolean isTechnologyPurchased(String name) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if( getTechnology()[i][j].getName().equals(name)){
                    return getTechnology()[i][j].isBought();
                }
            }
        }
        return false;
    }

    public void upgradeStocklimits() {
        this.setMetalStorage(METAL_STOCk_UPGRADED_LIMIT);
        this.setWealthStorage(WEALTH_STOCK_UPGRADED_LIMIT);
    }

    public void addProductionToStock() {
        this.metalStorage += getMetalProduction();
        this.wealthStorage += getWealthProduction();
    }

    public void collectResources() {
        if(isTechnologyPurchased("Interstellar Banking")){
            upgradeStocklimits();
        }
        addProductionToStock();
    }

    public void swapResources(int i) {
        if(i == 1){
            this.metalStorage += 2;
            this.wealthStorage--;
        }
        else{
            this.metalStorage--;
            this.wealthStorage += 2;
        }
    }
    
    public void swapResourcesForMilitary(){
        this.metalStorage--;
        this.wealthStorage--;
        this.militaryStrenght++;
    }

    public SystemCard getUnalignedSystemsCard(int i) throws ArrayIndexOutOfBoundsException {
        if(unalignedSystems.isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        
        return unalignedSystems.get(i);
    }

    public boolean checkUnalignedDistantSystems(SystemType sysType) {
        for (SystemCard a:unalignedSystems) {
            if(a.getSystemType() == sysType)
                return true;
	}
        return false;
    }

}
