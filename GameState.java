package game.update.free.tilewar;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */

public class GameState {
    private Player player1;
    private Player player2;
    private GameBoard board;
    private Player currentPlayer;
    private int numberOfTurns;


    final int ACCUMULTATORPOINTS = 10;
    final int BASEPOINTS = 20;


    GameState(Player player1, Player player2, GameBoard board){
        //below creates a list of players, shuffles the player list and picks the first player of the list as the current Player
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        Collections.shuffle(players);

        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        currentPlayer = players.get(0);
        this.numberOfTurns = 0;

    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void increasePopulationbyTurn(){
        for(int i = 0; i<getBoard().getBoard().length;i++){
            for(int k =0;k<getBoard().getBoard()[i].length;k++){
                if((getBoard().getBoard()[i][k].getType()=='b')&&(numberOfTurns>0)&&((getBoard().getBoard()[i][k].getOwner().equals("null")==false))){
                    getBoard().getBoard()[i][k].setPopulation(getBoard().getBoard()[i][k].getPopulation()+BASEPOINTS);
                }
                else if ((getBoard().getBoard()[i][k].getType()=='a')&&(numberOfTurns>0)&&((getBoard().getBoard()[i][k].getOwner().equals("null")==false))){
                    getBoard().getBoard()[i][k].setPopulation(getBoard().getBoard()[i][k].getPopulation()+ACCUMULTATORPOINTS);
                }
            }
        }
    }

    public void newTurn(){
        numberOfTurns++;
        if(currentPlayer.getPlayer().equals(player1.toString())){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }


    @Override
    public String toString() { //was: public String toString()
        if ((player2.getPopulation() <= 0) && player1.getPopulation() > 0) {
            return "Player 1 wins!";
        } else if ((player1.getPopulation() <= 0) && player2.getPopulation() > 0) {
            return "Player 2 wins!";
        }
         else if((player1.getPopulation() > 0) && (player2.getPopulation() > 0)) {
            return getCurrentPlayer().getName()+ "'s Turn!\nTileWar:"+ getBoard().toStringTracker()+"\n"+player1.getName()+"'s population:" +
                    player1.getPopulation() + ", Number of Tiles:"+player1.getNumberOfTiles()+
                    "\n"+player2.getName()+"'s population:" + player2.getPopulation() +", Number of Tiles:"+player2.getNumberOfTiles()
                    + "\nThe current number of turns is: "
                    + numberOfTurns+"\n\n"+board.printMoveTileOptions(getCurrentPlayer());
        }
        else{
            return "";
        }
    }

    public String gamestatus() { //was: public String toString()
        if ((player2.getPopulation() <= 0) && player1.getPopulation() > 0) {
            return "Player 1 wins!";
        } else if ((player1.getPopulation() <= 0) && player2.getPopulation() > 0) {
            return "Player 2 wins!";
        }
        else if((player1.getPopulation() > 0) && (player2.getPopulation() > 0)) {
            return player1.getName()+"'s population:" +
                    player1.getPopulation() + ", Number of Tiles:"+player1.getNumberOfTiles()+
                    "\n"+player2.getName()+"'s population:" + player2.getPopulation() +", Number of Tiles:"+player2.getNumberOfTiles()
                    + "\nThe current number of turns is: "
                    + numberOfTurns+"\n\n"+board.printMoveTileOptions(getCurrentPlayer());
        }
        else{
            return "";
        }
    }

    public int getTotalPopulation(){
        return player1.getPopulation()+player2.getPopulation();
    }

    public Player getHighLow(String highorlow){
        if((highorlow.equals("high")&&(player1.getPopulation()>player2.getPopulation()))){
            return player1;
        }
        else if((highorlow.equals("high")&&(player2.getPopulation()>player1.getPopulation()))){
            return player2;
        }

        else if ((highorlow.equals("low")&&(player1.getPopulation()>player2.getPopulation()))){
            return player2;
        }

        else if ((highorlow.equals("low")&&(player1.getPopulation()<player2.getPopulation()))){
            return player1;
        }

        else{
            return null;
        }
    }

    public Player gethighlowPlayer(ArrayList<Player> playerList, String highorlow){
        Player getplayer = playerList.get(0);
        for(Player i : playerList){
            if((getplayer.getPopulation()<i.getPopulation())&&(highorlow.equals("high"))){
                getplayer = i;
            }

            else if ((getplayer.getPopulation()>i.getPopulation())&&(highorlow.equals("low"))){
                getplayer = i;
            }
        }
        return getplayer;
    }


}



