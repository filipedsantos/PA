package model.data;

import java.io.*;
import java.util.*;
import model.data.Cards.Card;
import model.data.Cards.CardFactory;
import model.data.Cards.EventCard.*;
import model.data.Cards.SystemCard.*;

public class DataGame implements Constants, Serializable {

    static final long serialVersionUID = 1l;

    //Board Game iNFO
    private int metalStorage;
    private int wealthStorage;
    private int militaryStrenght;
    private int metalProduction;
    private int wealthProduction;
    static int Score;

    private String log; // String to save information processed in states
    private String currentEvent;
    //Technologies
    Technology[][] technology;

    // Arraylists to save card games
    private List<NearSystem> nearSystems;
    private List<DistantSystem> distantSystems;
    private List<SystemCard> empire;
    private List<SystemCard> unalignedSystems;
    private List<EventCard> events;
    //private List<EventCard> discardedEvents;

    private int year; // save the actual year of the game

    private boolean strikeEvent;

    public DataGame() throws IOException {
        this.nearSystems = new ArrayList<>();
        this.distantSystems = new ArrayList<>();
        this.empire = new ArrayList<>();
        this.unalignedSystems = new ArrayList<>();
        this.events = new ArrayList<>();
        this.strikeEvent = false;
        this.log = "";

        this.Score = 0;

        // Read System Cards from files
        buildStartingSystemFromFile(this, STARTING_SYSTEM_FILE);
        buildNearSystemsFromFile(this, NEAR_SYSTEMS_FILE);
        buildDistantSystemsFromFile(this, DISTANT_SYSTEMS_FILE);

        // Create technologies
        this.technology = createTechnologies();
        // Create Event Cards 
        createEventCards(this);

        // Shuffle cards
        Collections.shuffle(nearSystems);
        Collections.shuffle(distantSystems);
        Collections.shuffle(events);

        //Board info
        this.metalProduction = 1;
        this.wealthProduction = 1;

        this.metalStorage = 3;
        this.wealthStorage = 3;
        this.militaryStrenght = 10;

        this.year = 1; // start the game in 1s year
    }

    /**
     * Gets and Sets
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getEventStrike() {
        return this.strikeEvent;
    }

    public void setStrikeEvent(boolean s) {
        this.strikeEvent = s;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log += log;
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

    public void addMetalStorage(int m) {
        this.metalStorage += m;
    }

    public void setCurrentEvent(String event){
        this.currentEvent = event;
    }
    
    public String getCurrentEvent(){
        return this.currentEvent;
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

    public int getNearSystemsSize() {
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

    public int getDistantSystemsSize() {
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

    public int getEmpireSize() {
        return empire.size();
    }

    public List<SystemCard> getUnalignedSystems() {
        return unalignedSystems;
    }

    public void setUnalignedSystems(List<SystemCard> unalignedSystems) {
        this.unalignedSystems = unalignedSystems;
    }

    public boolean addUnalignedSystems(SystemCard c) {
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

    public boolean addEvent(EventCard e) {
        return this.events.add(e);
    }

    public int getEventsSize() {
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
        Technology[][] tec = new Technology[4][2];

        tec[0][0] = new Technology("Capital Ships", 3, "Advance beyond military strength 3.");
        tec[0][1] = new Technology("Forward Starbases", 4, "Required to explore distant systems.");
        tec[1][0] = new Technology("Robot Workers", 2, "Recive 1/2 production during strike.");
        tec[1][1] = new Technology("Planetary Defenses", 4, "+1 to resistance during invasion.");
        tec[2][0] = new Technology("Hyper Television", 3, "+1 resistence during revolt.");
        tec[2][1] = new Technology("Interstellar Diplomacy", 5, "Next planet is conquered for free.");
        tec[3][0] = new Technology("Interspecies Commerce", 2, "Exchange 2 of one resource for 1 of the other.");
        tec[3][1] = new Technology("Interstellar Banking", 3, "Advance beyond storage value 3.");

        return tec;
    }

    public void createEventCards(DataGame dataGame) {
        this.addEvent(new Asteroid(dataGame));
        this.addEvent(new DerelictShip(dataGame));
        this.addEvent(new LargeInvasionForce(dataGame));
        this.addEvent(new PeaceAndQuiet(dataGame));
        this.addEvent(new Revolt(dataGame));
        this.addEvent(new Revolt2(dataGame));
        this.addEvent(new SmallInvasionForce(dataGame));
        this.addEvent(new Strike(dataGame));
    }

    public NearSystem getNearSystems(int i) throws EmptyException {
        if (nearSystems.isEmpty()) {
            throw new EmptyException("Near system");
        }

        return nearSystems.get(i);
    }

    public DistantSystem getDistantSystems(int i) throws EmptyException {
        if (distantSystems.isEmpty()) {
            throw new EmptyException("Distant system");
        }

        return distantSystems.get(i);
    }

    public void adjustResources(SystemCard s) {
        this.metalProduction += s.getMetalProdution();
        this.wealthProduction += s.getWealthProdution();
    }

    public void reduceMilitaryForceOneunit() {
        if (militaryStrenght > 0) {
            this.militaryStrenght--;
        }
    }

    public boolean verifyNearSystemsOnUnalignedSystems() {
        for (int i = 0; i < getUnalignedSystemsSize(); i++) {
            if (getUnalignedSystems().get(i) instanceof NearSystem) {
                return false;
            }
        }

        return true;
    }

    public boolean isTechnologyPurchased(String name) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (getTechnology()[i][j].getName().equals(name)) {
                    return getTechnology()[i][j].isBought();
                }
            }
        }
        return false;
    }

    public boolean validateTecName(String tecName) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.technology[i][j].getName().equalsIgnoreCase(tecName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Technology getTechnologyByName(String tecName) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.technology[i][j].getName().equalsIgnoreCase(tecName)) {
                    return technology[i][j];
                }
            }
        }
        return null;
    }

    public boolean isTechnology1sGeneration(String tecName) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.technology[i][j].getName().equalsIgnoreCase(tecName)) {
                    if (j == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean getTechnology1sGenerationPurchased(String tecName) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.technology[i][j].getName().equalsIgnoreCase(tecName)) {
                    if (j == 1) {
                        return isTechnologyPurchased(technology[i][0].getName());
                    }
                }
            }
        }
        return false;
    }

    public void swapResources(int i) {

        switch (i) {
            case 1:
                this.metalStorage -= 2;
                this.wealthStorage++;
                break;
            case 2:
                this.metalStorage++;
                this.wealthStorage -= 2;
                break;
            default:
                break;
        }
    }

    public int getDiceNumber() {
        return (int) (Math.random() * 6) + 1;
    }

    public void swapResourcesForMilitary() {
        this.metalStorage--;
        this.wealthStorage--;
        this.militaryStrenght++;
    }

    public boolean checkUnalignedDistantSystems(SystemType sysType) {
        for (SystemCard a : unalignedSystems) {
            if (a.getSystemType() == sysType) {
                return true;
            }
        }
        return false;
    }

    public void refreshLog() {
        this.log = "";
    }

    public boolean canAttackPlanets(SystemType s) {
        switch (s) {
            case NEAR_SYSTEM:
                if (this.nearSystems.isEmpty()) {
                    this.log = "\n\nNo near systems to attack!\n\n";
                    return false;
                }
                break;
            case DISTANT_SYSTEM:
                if (this.distantSystems.isEmpty()) {
                    this.log = "\n\nNo distant systems to attack!\n\n";
                    return false;
                }
                if (!this.isTechnologyPurchased("Forward Starbases")) {
                    this.log = "\n\nYou need to buy Forward Starbases technology first!\n\n";
                    return false;
                }
                if (this.verifyNearSystemsOnUnalignedSystems()) {
                    this.log = "\n\nYou need to conquer all near systems first!\n\n";
                    return false;
                }
                break;
        }
        return true;
    }

    public EventCard getEvent(int i) throws EmptyException {
        if (this.events.isEmpty()) {
            throw new EmptyException("Events");
        }

        return this.events.get(i);
    }

    public void addWealthFromEvent(int i) {
        int wLimit;

        if (this.isTechnologyPurchased("Interstellar Banking")) {
            wLimit = WEALTH_STOCK_UPGRADED_LIMIT;
        } else {
            wLimit = WEALTH_STOCK_LIMIT;
        }

        int wStorage = wealthStorage + i;

        if (wStorage < wLimit) {
            wealthStorage = wStorage;
        } else {
            wealthStorage = wLimit;
        }

        setLog("Event Card Asteroid!\n you got plus 1 wealth");
    }

    public void addMetalFromEvent(int i) {
        int mLimit;

        if (this.isTechnologyPurchased("Interstellar Banking")) {
            mLimit = METAL_STOCk_UPGRADED_LIMIT;
        } else {
            mLimit = METAL_STOCK_LIMIT;
        }

        int mStorage = metalStorage + i;

        if (mStorage < mLimit) {
            metalStorage = mStorage;
        } else {
            metalStorage = mLimit;
        }

        setLog("Event Card Derelict Ship!\n you got plus 1 metal");
    }

    // 1st Parameter: type of system to attack - (0) Least Resistance, (1) Last Empire added
    // 2nd parameter: Force
    // 3rd parameter: String techology used
    public void fightAgainstSystem(int i, int force, String tec) throws EmptyException {

        SystemCard c = null;
        if (empire.size() > 1) {
            switch (i) {
                case 0:
                    c = getSystemWithLowerResistance();
                    break;
                case 1:
                    c = this.getEmpire().get(getEmpire().size() - 1);
                    break;
                default:
                    break;
            }

            int actualResistance = c.getResistance();

            if (isTechnologyPurchased(tec)) {
                actualResistance++;
            }

            int actualForce = getDiceNumber() + force;

            if (actualForce > actualResistance) {
                unalignedSystems.add(c);
                empire.remove(c);
                adjustResources(c);
                if (i == 1) {
                    setLog("Event Card Revolt!\nYou lose the fight against people (card Force: " + actualForce + ")");
                    setLog("\nPlanet: " + c.getName() + " resistance: " + c.getResistance());
                } else {
                    setLog("Event Card Invasion!\nYou lose the fight against an unknwon enemy (card Force: " + actualForce + ")");
                    setLog("\nPlanet: " + c.getName() + " resistance: " + c.getResistance());
                }
            } else if (i == 1) {
                setLog("Event Card Revolt!\nYou win the fight against people (card Force: " + actualForce + ")");
                setLog("\nPlanet: " + c.getName() + " resistance: " + c.getResistance());
            } else {
                setLog("Event Card Invasion!\nYou win the fight against an unknwon enemy (card Force: " + actualForce + ")");
                setLog("\nPlanet: " + c.getName() + " resistance: " + c.getResistance());
            }
        } else {
            setLog("INVASION,You only have your Home World, so nothing happen here");
        }

    }

    private SystemCard getSystemWithLowerResistance() throws EmptyException {

        if (empire.isEmpty()) {
            throw new EmptyException("Empire");
        }

        int resistance = getEmpire().get(1).getResistance();
        int planet = 1;

        for (int i = 1; i < empire.size(); i++) {
            if (getEmpire().get(i).getResistance() < resistance) {
                resistance = getEmpire().get(i).getResistance();
                planet = i;
            }

        }

        return empire.get(planet);
    }

    public static int getScore() {
        return Score;
    }
    
    public static String getScoreToString() {
        String stringScore = "";
        return stringScore += Score;
    }

    public static void setScore(int Score) {
        DataGame.Score += Score;
    }

    public void generateScore() {
        int score = 0;
        int pointsOfThischeck = 0;
        setLog("SCOREBOARD: \n");

        //1 point for every card on system
        for (int i = 0; i < getEmpire().size(); i++) {
            score += getEmpire().get(i).getPoints();
        }

        setLog("\n" + score + " points for sum victory point of all cards in your Empire.");

        //1 point for every technologies purchased
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (getTechnology()[i][j].isBought()) {
                    pointsOfThischeck++;
                }
            }
        }
        score += pointsOfThischeck;
        setLog("\n" + pointsOfThischeck + " points for sum of technologies purchased.");

        pointsOfThischeck = 0;

        //1 point if 0 systems near & distant cards 
        if (getNearSystems().size() == 0 && getDistantSystemsSize() == 0) {
            score += 1;
            setLog("\n" + 1 + " points for having all card turned up.");
        }

        //3 points if every card are on Empire
        if (getNearSystems().size() == 0 && getDistantSystemsSize() == 0 && getUnalignedSystemsSize() == 0) {
            score += 3;
            setLog("\n" + 3 + " for having all system cards on your Empire!");
        }

        //1 point if every technology purchased
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (getTechnology()[i][j].isBought()) {
                    pointsOfThischeck++;
                }
            }
        }
        if (pointsOfThischeck == 8) {
            score += 1;
            setLog("\n" + 1 + " point for having all technologies discovered.");
        }

        setScore(score);
        setLog("\n\nThe final Score is: " + getScore());
    }

    public void collect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
