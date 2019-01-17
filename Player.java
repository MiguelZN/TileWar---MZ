package game.update.free.tilewar;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */
import java.util.ArrayList;

public class Player {
    final static int START_POPULATION = 50;
    private int population;   //the player's total population
    private int numberOfTiles;       //the number of tiles the player owns
    private String player;
    private ArrayList<Tile> tiles;
    private String name;


    Player(String player){
        population = 0;
        numberOfTiles = 0;
        this.player = player;
        tiles = new ArrayList<>();

        if(player.equals("p1")){
            name = "Player1";
        }

        else if (player.equals("p2")){
            name = "Player2";
        }

    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public int getPopulation() {
        return population;
    }


    public void setPopulation(int population) {
        this.population = population;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void printTiles(){
        for(Tile i : getTiles()){
            System.out.println(i.toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.getPlayer();
    }


}
