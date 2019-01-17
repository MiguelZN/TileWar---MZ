package game.update.free.tilewar;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.w3c.dom.Text;

//Miguel Zavala Jack Wilson Tyler Earley Section 012

public class Main2Activity extends AppCompatActivity {
    static int size;
    static int acc;
    TextView v;
    private float x;
    private float y;


    private CustomView gameView;
    private int xImageID;
    private MotionEvent simulationEvent;

    static Player player1;
    static Player player2;
    static GameBoard board;
    static GameState gameState;
    static TileWarGame game;
    static TileWarAction action;

    private EditText tilenumber;
    private EditText movement;
    private EditText population;

    int player1wins =0;
    int player2wins =0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //   setContentView(view1);

        Intent receive = getIntent();

        size = receive.getIntExtra("size", 2);
        acc = receive.getIntExtra("acc", 1);
        xImageID = receive.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);

        String tostring = String.valueOf(size + acc);


        this.player1 = new Player("p1");
        this.player2 = new Player("p2");
        this.board = new GameBoard(player1, player2, size);
        this.gameState = new GameState(player1, player2, board);
        this.game = new TileWarGame(player1, player2, board, gameState, acc);
        this.action = new TileWarAction(game);


        gameView = new CustomView(this, xImageID);


        TextView playerturn = (TextView) findViewById(R.id.Playerturn);
        if(Main2Activity.action.game.getCurrentTurn().getPlayer().equals("p1")){
            playerturn.setTextColor(Color.RED);
        }
        else{
            playerturn.setTextColor(Color.BLUE);
        }

        playerturn.setTextSize(30);
        playerturn.setText(action.game.getCurrentTurn().getName() + "'s Turn!");


        LinearLayout grid = findViewById(R.id.boardgame);
        grid.addView(gameView);


        //
        grid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getAction();
                y = event.getAction();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), action.game.getGamestate().gamestatus()+"\n"+"Player1 wins:"+player1wins+"\n"+"Player2 wins:"+player2wins, Toast.LENGTH_LONG).show();

                }


                return false;


            }

        });



        Button submitturn = (Button) findViewById(R.id.submit);
        submitturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onButtonClick();
                }

                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Input all Values and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


            public void onButtonClick(){
        EditText tilenumber = (EditText) findViewById(R.id.tilenumber);
        EditText population = (EditText) findViewById(R.id.Population);
        EditText movement = (EditText) findViewById(R.id.Action);

        int tile;
        int pop;
        String tileselected ="0";
        String populationselected ="0";
        String actionselected ="null";


        tileselected = tilenumber.getText().toString();
        populationselected = population.getText().toString();
        actionselected = movement.getText().toString();

        tile = Integer.parseInt(tileselected);
        pop = Integer.parseInt(populationselected);

        //if the game ends, and there is a winner, create a new game
       if(action.game.isEnd()){
           if((action.game.getPlayer1().getPopulation()>0)&&(action.game.getPlayer2().getPopulation()<=0)){
               gameView.invalidate();
               Toast.makeText(getApplicationContext(),"Player1 Wins!",Toast.LENGTH_LONG).show();
               player1wins++;


               //makes a new game
               this.player1 = new Player("p1");
               this.player2 = new Player("p2");
               this.board = new GameBoard(player1, player2, size);
               this.gameState = new GameState(player1, player2, board);
               this.game = new TileWarGame(player1, player2, board, gameState, acc);
               this.action = new TileWarAction(game);


           }
           else if((action.game.getPlayer2().getPopulation()>0)&&(action.game.getPlayer1().getPopulation()<=0)){
               gameView.invalidate();
               Toast.makeText(getApplicationContext(),"Player2 Wins!",Toast.LENGTH_LONG).show();
               player2wins++;


               //makes a new game
               this.player1 = new Player("p1");
               this.player2 = new Player("p2");
               this.board = new GameBoard(player1, player2, size);
               this.gameState = new GameState(player1, player2, board);
               this.game = new TileWarGame(player1, player2, board, gameState, acc);
               this.action = new TileWarAction(game);




           }

       }

       if(action.game.getNumberofTurns()%6==5){
           action.game.getBoard().resetAccumulators(Main2Activity.acc);
           Toast.makeText(getApplicationContext(), "Resetting Accumulators...", Toast.LENGTH_SHORT).show();
           gameView.invalidate();
       }

       if(actionselected.equals("left")||(actionselected.equals("l"))){
           actionselected = "up";
       }
       else if(actionselected.equals("up")||(actionselected.equals("u"))){
           actionselected ="left";
       }
       else if(actionselected.equals("down")||(actionselected.equals("d"))){
           actionselected = "right";
       }
       else if(actionselected.equals("right")||(actionselected.equals("r"))){
           actionselected = "down";
       }

       if(action.isValid(actionselected,pop,tile)){
           action.updategame(actionselected,pop,tile);
           gameView.postInvalidate();
           TextView playerturn = (TextView) findViewById(R.id.Playerturn);
           if(Main2Activity.action.game.getCurrentTurn().getPlayer().equals("p1")){
               playerturn.setTextColor(Color.RED);
           }
           else{
               playerturn.setTextColor(Color.BLUE);
           }
           playerturn.setTextSize(30);
           playerturn.setText(action.game.getCurrentTurn().getName() + "'s Turn!");

           tilenumber.setText("");
           population.setText("");
           movement.setText("");

           try {
               InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
           } catch (Exception e) {
               // TODO: handle exception
           }
       }
       else{
           gameView.invalidate();
           Toast.makeText(getApplicationContext(),"Invalid selection, Pick again",Toast.LENGTH_LONG).show();
       }







    }







     //  }
    }















