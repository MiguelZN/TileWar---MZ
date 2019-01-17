package game.update.free.tilewar;

import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

//Miguel Zavala Jack Wilson Tyler Earley Section 012

public class MainActivity extends AppCompatActivity {


    EditText boardsizeinput;
    EditText accumulatorsinput;
    int size;
    int accumulators;
    Button button;

    String sizestring;
    String accstring;


    final int MAXSIZE = 8;
    final int MINSIZE = 3;

    private int current_image__index =0;
    public int[] images = {R.drawable.tilewarlogo,R.drawable.tilewarlogo};
    public static final String EXTRA_MESSAGE = "edu.udel.wassil.katie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      //  boardsizeinput = (EditText) findViewById(R.id.settingsize);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked();
                //openActivity2();
            }
        });



}




    public void onButtonClicked(){
        //sends user to new activity
        Intent send = new Intent (MainActivity.this,Main2Activity.class);

        //Takes out the user input for the text boxes
     /*   EditText sizeinput = (EditText) findViewById(R.id.settingsize);
        EditText accinput = (EditText) findViewById(R.id.settingaccumulator);

        String s1, s2;
        int n1, n2;

        //takes the user input and turns it into a string
        sizestring = sizeinput.getText().toString();
        accstring = accinput.getText().toString();



*/

        size = 3;
        accumulators = 2;

        //transfers the user data to the next activity
        send.putExtra("size",size);
        send.putExtra("acc",accumulators);
        send.putExtra(EXTRA_MESSAGE,images[current_image__index]);

        startActivity(send);


    }
}
