package game.update.free.tilewar;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */

public class Tile {
    private int population;
    private String Owner; // can be either player1, player2, or null
    private char Type; //what type of tile is this tile
    private int tracker; //keeps track of which tile it is
    private int imageID;

    Tile(){
        population = 0;
        Owner = "";
        Type = ' ';
        tracker = 0;
        imageID = 0;
    }

    Tile(int population,String owner, char Type){
        this.population = population;
        this.Owner = owner;
        this.Type = Type;
        tracker = getTracker();
        imageID = 0;
    }

    Tile(int population,String owner, char Type,int tracker){
        this.population = population;
        this.Owner = owner;
        this.Type = Type;
        this.tracker = tracker;
        imageID = 0;
    }

    Tile(int population,String owner, char Type,int tracker, int imageID){
        this.population = population;
        this.Owner = owner;
        this.Type = Type;
        this.tracker = tracker;
        this.imageID = imageID;
    }

    public int getPopulation() {
        return this.population;
    }

    public String getOwner() {
        return Owner;
    }

    public char getType(){
        return this.Type;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public void setType(char Type) {
        this.Type = Type;
    }

    public int getTracker() {
        return tracker;
    }

    public void setTracker(int tracker) {
        this.tracker = tracker;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public boolean equals(Tile othertile){
        if((getType()== othertile.getType())&&(getPopulation() == othertile.getPopulation())&&(getOwner().equals(othertile.getOwner()))){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        String tile = "("+getPopulation()+","+getOwner()+","+getType()+")";
        return tile;
    }

}
