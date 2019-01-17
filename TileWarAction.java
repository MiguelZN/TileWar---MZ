package game.update.free.tilewar;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */
import java.util.Scanner;

public class TileWarAction implements Action<TileWarGame> {
    TileWarGame game;

    TileWarAction(TileWarGame game){
        this.game = game;
    }


    public boolean isValid(TileWarGame game){
        return false;
    }
    public void update(TileWarGame game){
        ;
    }

    /*
    public boolean isValid(TileWarGame game,String action, int population, int row, int column, int otherrow, int othercolumn){
        int max = game.getBoard().getBoard().length;
        int min = 0;

        if((game.getBoard().getTile(row,column).getPopulation()<0)&&(game.getBoard().getTile(otherrow,othercolumn).getPopulation())>(game.getBoard().getTile(row,column).getPopulation())){
            return false;
        }

        if(action.equals("left")&&(othercolumn>0)){
            return true;
        }
        else if ((action.equals("right")&&(othercolumn<max))){
            return true;
        }

        else if ((action.equals("up")&&(otherrow<0))){
            return true;
        }

        else if((action.equals("down"))&&(otherrow<max)){
            return true;
        }

        else{
            return false;
        }
    }


    public void start(){
        System.out.println("Starting game");
    }


    public void update(TileWarGame game, String action, int population, int row, int column, int otherrow, int othercolumn){
        if(isValid(game,action,population,row,column,otherrow,othercolumn)){
            if(action.equals("up")){
                game.getBoard().setTile(row-1,column,
                        (population)-(game.getBoard().getTile(otherrow,othercolumn).getPopulation()),game.getCurrentTurn().getPlayer(),
                game.getBoard().getTile(otherrow,othercolumn).getType());
            }

            else if(action.equals("down")){
                game.getBoard().setTile(row+1,column,
                        (population)-(game.getBoard().getTile(otherrow,othercolumn).getPopulation()),game.getCurrentTurn().getPlayer(),
                        game.getBoard().getTile(otherrow,othercolumn).getType());
            }

            else if(action.equals("right")){
                game.getBoard().setTile(row,column+1,
                        (population)-(game.getBoard().getTile(otherrow,othercolumn).getPopulation()),game.getCurrentTurn().getPlayer(),
                        game.getBoard().getTile(otherrow,othercolumn).getType());
            }

            else if(action.equals("left")){
                game.getBoard().setTile(row,column-1,
                        (population)-(game.getBoard().getTile(otherrow,othercolumn).getPopulation()),game.getCurrentTurn().getPlayer(),
                        game.getBoard().getTile(otherrow,othercolumn).getType());
            }

            else{
                System.out.println("Invalid");
            }

           // if(game.getBoard().getTile(otherrow,othercolumn).getOwner().equals(game.getCurrentTurn()))

            //moves population one tile to another
           // game.getBoard().setTile(otherrow,othercolumn,
               //     (population)-(game.getBoard().getTile(otherrow,othercolumn).getPopulation()),game.getCurrentTurn(),
                //    game.getBoard().getTile(otherrow,othercolumn).getType());

            game.getBoard().setTile(row,column,game.getBoard().getTile(row,column).getPopulation()-population,game.getBoard().getTile(row,column).getOwner(),
                    game.getBoard().getTile(row,column).getType());

            game.getGamestate().newTurn();
            System.out.println(game.getGamestate().toString());

        }

        else{
            System.out.println("Not a valid move");
        }

    }
    */

    //checks if the user inputted tracker is one of their tiles
    public boolean isCurrentPlayerTile(int tracker){
        boolean valid = false;
       /* for(Tile i : game.getGamestate().getCurrentPlayer().getTiles()){
            if(i.getTracker()==tracker){
                valid = true;
            }
            else{
                valid = false;
            }
        }
        return true;*/
       for(int i =0;i<game.getBoard().getBoard().length;i++){
           for(int k = 0;k<game.getBoard().getBoard()[i].length;k++){
               if(game.getBoard().getTileByTracker(tracker).getOwner().equals(game.getCurrentTurn().getPlayer())){
                   valid = true;
               }

           }
       }
       return valid;
    }

    public boolean isValid(String action, int population, int tracker){
        if(isCurrentPlayerTile(tracker)==false){ //if the tile player inputs is not their tile then it's not a valid move
            return false;
        }


        int row = game.getBoard().getPosition(game.getBoard().getTileByTracker(tracker)).get(0); //retrieves the row from inputted tracker tile
        int column = game.getBoard().getPosition(game.getBoard().getTileByTracker(tracker)).get(1); //retrieves the column from inputted tracker tile


        if(population>game.getBoard().getTileByTracker(tracker).getPopulation()){
            return false;
        }

        if(action.equals("up")&&(row>0)){ //if the user wants to go up, their tile must not be in row 0
            int newrow= row-1;
            //column stays the same
            return true;
        }
        else if(action.equals("down")&&(row<game.getBoard().getBoard().length)){ //if the user wants to go down their tile must be less than the row length of board
            return true;
        }

        else if (action.equals("right")&&(column<game.getBoard().getBoard().length)){ //if the user wants to move the selected tile right must be less than length of board
            return true;
        }
        else if (action.equals("left")&&(column>0)){ //if the user wants to move the tile left, the selected tile cannot be in the 0 column
            return true;
        }
        else{
            return false;
        }
    }

    public void updategame(String action,int population, int tracker){

        //do all tile checks at the end such as how many tiles? how much player population? etc
        int row = game.getBoard().getPosition(game.getBoard().getTileByTracker(tracker)).get(0); //retrieves the row from inputted tracker tile
        int column = game.getBoard().getPosition(game.getBoard().getTileByTracker(tracker)).get(1); //retrieves the column from inputted tracker tile
        int currenttilepopulation;
        String currentowner;
        char currenttype;
        int currenttracker;

        int newrow;
        int newcolumn;
        int othertilepopulation;
        String othertileowner;
        char othertiletype;
        int othertiletracker;

        currenttilepopulation = game.getBoard().getTile(row,column).getPopulation();
        currentowner = game.getBoard().getTile(row,column).getOwner();
        currenttype = game.getBoard().getTile(row,column).getType();
        currenttracker = game.getBoard().getTile(row,column).getTracker();

        if(isValid(action,population,tracker)){
            if(action.equals("up")){

                newrow = row-1;
                othertilepopulation = game.getBoard().getTile(newrow,column).getPopulation();
                othertileowner = game.getBoard().getTile(newrow,column).getOwner();
                othertiletype = game.getBoard().getTile(newrow,column).getType();
                othertiletracker = game.getBoard().getTile(newrow, column).getTracker();

                game.getBoard().setTile(row,column,currenttilepopulation-population,currentowner,currenttype,currenttracker); //sets the current tile by removing user population

                if(othertileowner.equals(currentowner)){
                    game.getBoard().setTile(newrow, column, othertilepopulation+population,othertileowner,othertiletype,othertiletracker); //adds to tile population if othertile is player owned
                }

                else if(othertileowner.equals(currentowner)==false){ //if the othertile is not the player's take population away from it
                    String newtileowner;
                    int newpopulation;

                    if(othertilepopulation>population){
                        newpopulation = othertilepopulation-population;
                        game.getBoard().setTile(newrow, column, newpopulation,othertileowner,othertiletype,othertiletracker);
                 //       game.getCurrentTurn().setPopulation(game.getCurrentTurn().getPopulation()-population); //subtracts the population from current player
                 //       game.getNonCurrentTurn().setPopulation(game.getNonCurrentTurn().getPopulation()-population); //subtracts the population from Non Current player


                    }
                    else if (othertilepopulation==population){
                        newpopulation = 0;
                        game.getBoard().setTile(newrow,column, newpopulation,"null",othertiletype,othertiletracker); //if the populations equal, then makes the tile with no owner
                    }

                    else if (othertilepopulation<population){
                        newpopulation = Math.abs(othertilepopulation-population);
                        game.getBoard().setTile(newrow,column,newpopulation,currentowner,othertiletype,othertiletracker);
                    }

                    else {
                        System.out.println("error at changing population/tile characteristics");
                    }

                }

                else{
                    System.out.println("error at finding owner");
                }



            }





            else if (action.equals("down")){

                newrow = row+1;
                othertilepopulation = game.getBoard().getTile(newrow,column).getPopulation();
                othertileowner = game.getBoard().getTile(newrow,column).getOwner();
                othertiletype = game.getBoard().getTile(newrow,column).getType();
                othertiletracker = game.getBoard().getTile(newrow,column).getTracker();

                game.getBoard().setTile(row,column,currenttilepopulation-population,currentowner,currenttype,currenttracker); //sets the current tile by removing user population

                if(othertileowner.equals(currentowner)){
                    game.getBoard().setTile(newrow, column, othertilepopulation+population,othertileowner,othertiletype,othertiletracker); //adds to tile population if othertile is player owned
                }

                else if(othertileowner.equals(currentowner)==false){ //if the othertile is not the player's take population away from it
                    String newtileowner;
                    int newpopulation;

                    if(othertilepopulation>population){
                        newpopulation = othertilepopulation-population;
                        game.getBoard().setTile(newrow, column, newpopulation,othertileowner,othertiletype,othertiletracker);
                        //       game.getCurrentTurn().setPopulation(game.getCurrentTurn().getPopulation()-population); //subtracts the population from current player
                        //       game.getNonCurrentTurn().setPopulation(game.getNonCurrentTurn().getPopulation()-population); //subtracts the population from Non Current player


                    }
                    else if (othertilepopulation==population){
                        newpopulation = 0;
                        game.getBoard().setTile(newrow,column, newpopulation,"null",othertiletype,othertiletracker); //if the populations equal, then makes the tile with no owner
                    }

                    else if (othertilepopulation<population){
                        newpopulation = Math.abs(othertilepopulation-population);
                        game.getBoard().setTile(newrow,column,newpopulation,currentowner,othertiletype,othertiletracker);
                    }

                    else {
                        System.out.println("error at changing population/tile characteristics");
                    }

                }

                else{
                    System.out.println("error at finding owner");
                }
            }






            else if (action.equals("right")){


                newcolumn = column+1;
                othertilepopulation = game.getBoard().getTile(row,newcolumn).getPopulation();
                othertileowner = game.getBoard().getTile(row,newcolumn).getOwner();
                othertiletype = game.getBoard().getTile(row,newcolumn).getType();
                othertiletracker = game.getBoard().getTile(row, newcolumn).getTracker();

                game.getBoard().setTile(row,column,currenttilepopulation-population,currentowner,currenttype,currenttracker); //sets the current tile by removing user population

                if(othertileowner.equals(currentowner)){
                    game.getBoard().setTile(row, newcolumn, othertilepopulation+population,othertileowner,othertiletype, othertiletracker); //adds to tile population if othertile is player owned
                }

                else if(othertileowner.equals(currentowner)==false){ //if the othertile is not the player's take population away from it
                    String newtileowner;
                    int newpopulation;

                    if(othertilepopulation>population){
                        newpopulation = othertilepopulation-population;
                        game.getBoard().setTile(row, newcolumn, newpopulation,othertileowner,othertiletype, othertiletracker);
                        //       game.getCurrentTurn().setPopulation(game.getCurrentTurn().getPopulation()-population); //subtracts the population from current player
                        //       game.getNonCurrentTurn().setPopulation(game.getNonCurrentTurn().getPopulation()-population); //subtracts the population from Non Current player


                    }
                    else if (othertilepopulation==population){
                        newpopulation = 0;
                        game.getBoard().setTile(row,newcolumn, newpopulation,"null",othertiletype, othertiletracker); //if the populations equal, then makes the tile with no owner
                    }

                    else if (othertilepopulation<population){
                        newpopulation = Math.abs(othertilepopulation-population);
                        game.getBoard().setTile(row,newcolumn,newpopulation,currentowner,othertiletype, othertiletracker);
                    }

                    else {
                        System.out.println("error at changing population/tile characteristics");
                    }

                }

                else{
                    System.out.println("error at finding owner");
                }
            }
            else if (action.equals("left")){

                newcolumn = column-1;
                othertilepopulation = game.getBoard().getTile(row,newcolumn).getPopulation();
                othertileowner = game.getBoard().getTile(row,newcolumn).getOwner();
                othertiletype = game.getBoard().getTile(row,newcolumn).getType();
                othertiletracker = game.getBoard().getTile(row,newcolumn).getTracker();

                game.getBoard().setTile(row,column,currenttilepopulation-population,currentowner,currenttype, currenttracker); //sets the current tile by removing user population

                if(othertileowner.equals(currentowner)){
                    game.getBoard().setTile(row, newcolumn, othertilepopulation+population,othertileowner,othertiletype,othertiletracker); //adds to tile population if othertile is player owned
                }

                else if(othertileowner.equals(currentowner)==false){ //if the othertile is not the player's take population away from it
                    String newtileowner;
                    int newpopulation;

                    if(othertilepopulation>population){
                        newpopulation = othertilepopulation-population;
                        game.getBoard().setTile(row, newcolumn, newpopulation,othertileowner,othertiletype,othertiletracker);
                        //       game.getCurrentTurn().setPopulation(game.getCurrentTurn().getPopulation()-population); //subtracts the population from current player
                        //       game.getNonCurrentTurn().setPopulation(game.getNonCurrentTurn().getPopulation()-population); //subtracts the population from Non Current player


                    }
                    else if (othertilepopulation==population){
                        newpopulation = 0;
                        game.getBoard().setTile(row,newcolumn, newpopulation,"null",othertiletype, othertiletracker); //if the populations equal, then makes the tile with no owner
                    }

                    else if (othertilepopulation<population){
                        newpopulation = Math.abs(othertilepopulation-population);
                        game.getBoard().setTile(row,newcolumn,newpopulation,currentowner,othertiletype, othertiletracker);
                        System.out.println("took over");
                    }

                    else {
                        System.out.println("error at changing population/tile characteristics");
                    }

                }

                else{
                    System.out.println("error at finding owner");
                }
            }
            else{
                System.out.println("Invalid Move");
            }
        }

        else{
            System.out.println("Invalid tracker pick");
        }

        //updates the game after user inputs what they want to do
        game.getGamestate().increasePopulationbyTurn();     //goes through board and increases populations at base and accumulator tiles
        game.getBoard().setPlayerPopulation(game.getPlayer1());  //goes through board and adds up all the Player1 population and sets it to Player1's population field
        game.getBoard().setPlayerPopulation(game.getPlayer2());  //goes through board and adds up all the Player2 population and sets it to Player2's population field
        game.getBoard().setTiles(game.getPlayer1());  //
        game.getBoard().setTiles(game.getPlayer2());
        game.getGamestate().newTurn();
        System.out.println(game.getGamestate().toString());





    }

}
