package game.update.free.tilewar;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */

public class TileWarGame extends Game {
    private Player player1;
    private Player player2;
    private GameBoard board;
    private GameState gamestate;

    TileWarGame(Player player1, Player player2, GameBoard board, GameState gamestate,int numberofAccumulators){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        getBoard().addPlayers(player1,player2); //adds the players to the map
        board.setPlayerPopulation(player1);
        board.setPlayerPopulation(player2);
        this.gamestate = gamestate;
        getBoard().addAccumulators(numberofAccumulators);
    }




    public GameBoard getBoard() {
        return board;
    }

    public GameState getGamestate() {
        return gamestate;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentTurn(){
        return getGamestate().getCurrentPlayer();
    }

    //returns the nonCurrent player
    //(if the current player is player1, return player2 and vise versa)
    public Player getNonCurrentTurn(){
        Player noncurrent;
        if(getCurrentTurn().equals(getPlayer1().toString())== true){
            noncurrent = getPlayer2();
        }
        else{
            noncurrent = player1;
        }

        return noncurrent;
    }



    //determines if the game should end based on if there is a winner or a certain number of turns reached
    @Override
    public boolean isEnd(){
        if((player1.getPopulation()>0) && (player2.getPopulation()<=0)){
            return true;
        }

        else if ((player2.getPopulation()>0) && (player1.getPopulation()<=0)){
            return true;
        }
        //could add once number of turns equals a certain amount of turns
        else if (getGamestate().getNumberOfTurns()==100){  //can comment out if want no turn limit
            return true;
           }


        else{
            return false;
        }
    }

    public int getNumberofTurns(){
        return getGamestate().getNumberOfTurns();
    }

    //the score is number of population each player has
    public String getScore(){
        String score = "Player1 population:"+getPlayer1().getPopulation()+"\nNumber of Tiles owned:"+getPlayer1().getNumberOfTiles()+
                "\nPlayer2 population:"+getPlayer2().getPopulation()+"\nNumber of Tiles owned:"+getPlayer2().getNumberOfTiles();

        return score;
    }

    //returns true if there is a winner by if there is a player with 0 population
    public boolean isWinner(){
        if((player1.getPopulation()>0)&&(player2.getPopulation()<=0)){
            return true;
        }

        else if ((player2.getPopulation()>0)&&(player1.getPopulation()<=0)){
            return true;
        }

        else{
            return false;
        }
    }

    //returns the status of the game
    public String getStatus(){
        return getGamestate().toString();
    }


    //returns the status of the game
    @Override
    public String toString(){
        return board.toString();
    }

}
