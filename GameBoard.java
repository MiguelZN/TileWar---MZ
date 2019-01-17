package game.update.free.tilewar;
/**
 * * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private static Tile[][] Board;
    private Player player1;
    private Player player2;

    //default 5x5 blank board
    private static Tile[][] BlankBoard =
            {{new Tile(0, "none", 'n'), new Tile(0, "none", 'n'), new Tile(0, "none", 'n'),new Tile(0, "none", 'n'), new Tile(0, "none", 'n')},
                    {new Tile(0, "none", 'n'), new Tile(0, "none", 'n'), new Tile(0, "none", 'n'),new Tile(0, "none", 'n'), new Tile(0, "none", 'n')},
                    {new Tile(0, "none", 'n'), new Tile(0, "none", 'n'), new Tile(0, "none", 'n'),new Tile(0, "none", 'n'), new Tile(0, "none", 'n')},
                    {new Tile(0, "none", 'n'), new Tile(0, "none", 'n'), new Tile(0, "none", 'n'),new Tile(0, "none", 'n'), new Tile(0, "none", 'n')},
                    {new Tile(0, "none", 'n'), new Tile(0, "none", 'n'), new Tile(0, "none", 'n'),new Tile(0, "none", 'n'), new Tile(0, "none", 'n')}};

    //Tile diagram: population, owner, tiletype

    //Population:
    //int = number of population at this specific tile

    //Owner:
    //null = no owner
    //p1 = player1 owns it
    //p2 = player2 owns it

    //Tile Types:
    //b = base
    //a = accumulator
    //n = normal

    //makes a default 5x5 board
    GameBoard(Player player1, Player player2){
        this.Board = BlankBoard;
        this.player1 = player1;
        this.player2 = player2;
    }

    //makes a custom board with a inputted size
    GameBoard(Player player1, Player player2, int size){
        Tile blanktile = new Tile(0, "null", 'n');
        Tile[][] blankboard = new Tile[size][size];

        int tracker = 0;

        for(int i = 0; i<blankboard.length;i++){

            for(int k = 0; k<blankboard[i].length;k++){
                blankboard[i][k] = new Tile(0,"",' ', 0);
                blankboard[i][k].setPopulation(blanktile.getPopulation());
                blankboard[i][k].setOwner(blanktile.getOwner());
                blankboard[i][k].setType(blanktile.getType());
                blankboard[i][k].setTracker(tracker);
                tracker++;

            }
        }

        this.player1 = player1;
        this.player2 = player2;
        this.Board = blankboard;
     //   addAccumulators(numberofAccumulators);
    }

    //makes a custom board with a inputted size
    GameBoard(Player player1, Player player2, int size, int imageID){
        Tile blanktile = new Tile(0, "null", 'n');
        Tile[][] blankboard = new Tile[size][size];

        int tracker = 0;

        for(int i = 0; i<blankboard.length;i++){

            for(int k = 0; k<blankboard[i].length;k++){
                blankboard[i][k] = new Tile(0,"",' ', 0, imageID);
                blankboard[i][k].setPopulation(blanktile.getPopulation());
                blankboard[i][k].setOwner(blanktile.getOwner());
                blankboard[i][k].setType(blanktile.getType());
                blankboard[i][k].setTracker(tracker);
                tracker++;

            }
        }

        this.player1 = player1;
        this.player2 = player2;
        this.Board = blankboard;
        //   addAccumulators(numberofAccumulators);
    }


    public Tile[][] getBoard() {
        return Board;
    }

    public void setBoard(Tile[][] board) {
        Board = board;
    }

    public boolean iswithinBounds(int row, int column){
        if((row<= getBoard().length)&&(column<=getBoard()[0].length)){
            return true;
        }
        else{
            return false;}}


    //-------------------------------------------------------------------------------------------------------
    //sets Tile at row,column to inputted population, owner, type
     public void setTile(int row, int column, int population, String owner, char type){
        if(iswithinBounds(row, column)){
            getBoard()[row][column]= new Tile(population,owner,type);
        }
        else{
            getBoard()[row][column]= getBoard()[row][column];
        }
     }

     //sets a new tile
    public void setTile(int row, int column, int population, String owner, char type, int tracker){
        if(iswithinBounds(row, column)){
            getBoard()[row][column]= new Tile(population,owner,type,tracker);
        }
        else{
            getBoard()[row][column]= getBoard()[row][column];
        }
    }

    //sets a new tile
    public void setTile(int row, int column, int population, String owner, char type, int tracker, int imageID){
        if(iswithinBounds(row, column)){
            getBoard()[row][column]= new Tile(population,owner,type,tracker);
        }
        else{
            getBoard()[row][column]= getBoard()[row][column];
        }
    }

     //returns Tile from specified row, column
    public Tile getTile(int row, int column){
        if(iswithinBounds(row,column)){
            return getBoard()[row][column];
        }
        else{
            return null;}}

    //returns a tile from an inputted int, EX: 2x2 board the trackers would be ROW1: 0,1 ROW2: 2,3  if you want top left tile you type in 0
    public Tile getTileByTracker(int tracker){
        Tile tilefound = new Tile();
        for(int i = 0;i<getBoard().length;i++){
            for(int k = 0;k<getBoard()[i].length;k++){
                if(getBoard()[i][k].getTracker()==tracker){
                    tilefound.setOwner(getBoard()[i][k].getOwner());
                    tilefound.setTracker(getBoard()[i][k].getTracker());
                    tilefound.setPopulation(getBoard()[i][k].getPopulation());
                    tilefound.setType(getBoard()[i][k].getType());
                }
            }
        }

        return tilefound;

    }
//-----------------------------------------------------------------------------------------------------------------------

    //Functional Board Methods

    //adds players to the board
    public void addPlayers(Player player1, Player player2){
        int trackerp1 = getBoard().length-1;
        int trackerp2 = (getBoard().length*getBoard().length)-getBoard().length;
        getBoard()[0][getBoard().length-1] = new Tile (Player.START_POPULATION,player1.getPlayer(),'b',trackerp1);
        getBoard()[getBoard().length-1][0] = new Tile (Player.START_POPULATION,player2.getPlayer(), 'b',trackerp2);
        player1.getTiles().add(getBoard()[0][getBoard().length-1]);
        player2.getTiles().add(getBoard()[getBoard().length-1][0]);

    }

    //adds the number of accumulators
   public void addAccumulators(int numberofAccumulators) {
       Tile acumulatorTile = new Tile(0, null, 'a');
       Tile nullTile = new Tile (0, null,'n');

       while(numberofAccumulators>0) {
           int randomrow = (int) (Math.random() * getBoard().length);
           int randomcolumn = (int) (Math.random()* getBoard()[0].length);
           if(getBoard()[randomrow][randomcolumn].equals(new Tile(0,"null", 'n'))){
                           setTile(randomrow,randomcolumn,0,"null",'a',getBoard()[randomrow][randomcolumn].getTracker());
                           numberofAccumulators = numberofAccumulators-1;
           }
           else{
               randomrow = (int) (Math.random() * getBoard().length);
               randomcolumn = (int) (Math.random()* getBoard()[0].length);}}}


    //resets the accumulators
    public void resetAccumulators(int numberofAccumulators){
        for(int i =0;i<getBoard().length;i++) {
            for (int k = 0; k < getBoard().length; k++) {
                if (getBoard()[i][k].getType() == 'a') {

                    setTile(i, k, getBoard()[i][k].getPopulation(), getBoard()[i][k].getOwner(), 'n', getBoard()[i][k].getTracker());
                }
            }




        while(numberofAccumulators>0) {
            int randomrow = (int) (Math.random() * getBoard().length);
            int randomcolumn = (int) (Math.random()* getBoard()[0].length);
            if(getBoard()[randomrow][randomcolumn].getType()=='n'){
                setTile(randomrow,randomcolumn,getBoard()[randomrow][randomcolumn].getPopulation(),getBoard()[randomrow][randomcolumn].getOwner(),'a',getBoard()[randomrow][randomcolumn].getTracker());
                numberofAccumulators = numberofAccumulators-1;
            }
            else{
                randomrow = (int) (Math.random() * getBoard().length);
                randomcolumn = (int) (Math.random()* getBoard()[0].length);}}}}


    //goes through the whole board and adds together all of specified player's population
    public int getPlayerPopulation(Player player){
        int population = 0;
        for(int i = 0; i<getBoard().length;i++){
            for(int k = 0; k<getBoard()[i].length;k++){
                if(getBoard()[i][k].getOwner().equals(player.getPlayer())){
                    population = population + getBoard()[i][k].getPopulation();}}}
        return population;}

    //goes through the whole board and adds together all of specified player's population
    public void setPlayerPopulation(Player player) {
        int population = 0;
        for (int i = 0; i < getBoard().length; i++) {
            for (int k = 0; k < getBoard()[i].length; k++) {
                if (getBoard()[i][k].getOwner().equals(player.getPlayer())) {
                    population = population + getBoard()[i][k].getPopulation();
                }
            }
        }
        player.setPopulation(population);
    }


    //iterates through board and checks for all the tiles in board that are Player owned and adds them to an ArrayList<>
    public void setTiles(Player player) {
        ArrayList<Tile> playerowntiles = new ArrayList<Tile>();

        for (int i = 0; i < getBoard().length; i++) {
            for (int k = 0; k < getBoard()[i].length; k++) {
                if ((getBoard()[i][k].getOwner().equals(player.toString()))) {
                    playerowntiles.add(getBoard()[i][k]);
                }
            }
        }

        player.setTiles(playerowntiles);  //sets the player's ArrayList<Tile> field to the new ArrayList<Tile> array which contains all the player owned tiles


        player.setNumberOfTiles(player.getTiles().size()); //sets the number of tiles a player owns to the number of tiles in his possession
    }

    //produces an ArrayList with a specified Tile's row, column ArrayList<Integer> (row,column)
    public ArrayList<Integer> getPosition(Tile tile){
        ArrayList<Integer> position = new ArrayList<>();
        for(int i =0; i<getBoard().length; i++){
            for(int k =0; k<getBoard()[i].length;k++){
                if(getBoard()[i][k].getTracker()==tile.getTracker()){
                    position.add(i);
                    position.add(k);
                }
            }
        }
        return position;
    }




    //-----------------------------------------------------------------------------------------------------

    //Printing Methods

    //prints out the position one line: row, one line: column for each tile the player owns
    public void printPosition(ArrayList<Integer> IntegerArray){
        for(Integer i : IntegerArray){
            System.out.println(i);
        }
    }

    //tells the player which tiles he can move and prints the TileNumber and Tile
    public String printMoveTileOptions(Player player) {
        String statement = player.getName()+"'s Tiles:\n";
        int row = 0;
        int column = 1;
        for (int i = 0; i < player.getTiles().size(); i++) {
           /* statement = statement + i + 1 + ")" + "Row:" + getPosition(player.getTiles().get(i)).get(row) + " Column:"+getPosition(player.getTiles().get(i)).get(column)
            +" Tile:"+player.getTiles().get(i).toString() + "\n";*/

            statement = statement + (i + 1) + ")" + "Tile#"+player.getTiles().get(i).getTracker()
                    +" | "+player.getTiles().get(i).getPopulation()+" population" +"| Type: "+player.getTiles().get(i).getType() + "\n";
        }
        return statement;
    }

    //prints all the trackers line by line of the board
    public void printTrackers(){
        for(int i = 0;i<getBoard().length;i++){
            for(int k = 0;k<getBoard()[i].length;k++){
                System.out.println(getBoard()[i][k].getTracker());
            }
        }
    }


    public String toStringTracker(){
        String board = "";
        for(int i = 0; i<Board.length;i++){
            board= board+ "\n";
            for(int k = 0; k<Board[i].length;k++){
                board = board+ Board[i][k].getTracker()+"){"+ Board[i][k].toString() +"| " ;
            }
        }
        return board;
    }

    //returns String of the board by row:r, column: c { (population,owner,type)
    @Override
    public  String toString(){
        String board = "";
        for(int i = 0; i<Board.length;i++){
            board= board+ "\n";
            for(int k = 0; k<Board[i].length;k++){
                board = board+ "R"+i+"C"+k+"{" + Board[i][k].toString() +" | " ;
            }
        }
        return board;
    }
}
