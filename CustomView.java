package game.update.free.tilewar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Miguel Zavala, Tyler Earley, Jack Wilson CISC181 Section 012
 */

/**
 * This view extends the basic android.view.View to provide implementations for
 * drawing directly to the Canvas.  Adapted code from Dr. Atlas
 *
 */
public class CustomView extends View {

    DisplayMetrics displayMetrics = new DisplayMetrics();

    int screenheight = displayMetrics.heightPixels;
    int screenwidth = displayMetrics.widthPixels;

    // the activity
    protected Main2Activity activity;


    //image passed to this view
    private int xImage;

    // the width and height of the current game view
    private int width;
    private int height;

    // num rows and columns for your grid - this should be
    // obtained from the size of your 2D grid in later steps
    // for now - just set it here
    int numRows = Main2Activity.size;
    int numCols = Main2Activity.size;

    // the scale of the game board grid, how many pixels per col (x) and row (y)
    private float scale_x;
    private float scale_y;

    public CustomView(Main2Activity context, int xImage) {
        super(context);
        activity = context;
        setBackgroundColor(Color.WHITE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.xImage = xImage;
        }

    public Main2Activity getActivity() {
        return activity;
    }

    public void setActivity(Main2Activity activity) {
        this.activity = activity;
    }

    /**
     * Provides specific implementation for a TicTacToeView.
     *   - a grid
     *   - a set of pieces
     */


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        drawPieces(canvas);
    }

    /**
     * Draws a grid using numRows and numCols private variables
     *
     * @param canvas
     */
    protected void drawGrid(Canvas canvas) {

        // Paint object to paint the grid lines
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.BLACK);
        gridPaint.setStrokeWidth(4); // the "weight" of the lines
        gridPaint.setStyle(Style.FILL_AND_STROKE);

        // draw horizontal lines for each row
        for (int i = 0; i <= numRows; i++) {
            canvas.drawLine(0, i*scale_y, width, i*scale_y, gridPaint);
        }
        // draw vertical lines for each row
        for (int i = 0; i <= numCols; i++){
            canvas.drawLine(i*scale_x, 0, i*scale_x, height, gridPaint);
        }

        // draw filled middle square
        //int row = 1;  int col = 1;
       // gridPaint.setColor(Color.BLUE);
       // canvas.drawRect(scale_x*col, scale_y*row, scale_x*(col+1), scale_y*(col+1), gridPaint);


        for(int i =0; i<numRows;i++){
            for(int k=0; k<numCols; k++){
                if((Main2Activity.action.game.getBoard().getBoard()[i][k].getType() =='a')
                        &&(Main2Activity.action.game.getBoard().getBoard()[i][k].getOwner() =="p1"))
                {
                    drawImages(canvas,R.drawable.redcircle,k,i);
                    drawString(String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getPopulation()),
                            String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getTracker()),k,i,canvas);

                }

                else if((Main2Activity.action.game.getBoard().getBoard()[i][k].getType() =='a')
                        &&(Main2Activity.action.game.getBoard().getBoard()[i][k].getOwner() =="p2"))
                {
                    drawImages(canvas,R.drawable.bluecircle,k,i);
                    drawString(String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getPopulation()),
                            String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getTracker()),k,i,canvas);

                }
                else if(Main2Activity.action.game.getBoard().getBoard()[i][k].getType() =='a')
                {
                    gridPaint.setColor(Color.YELLOW);
                    drawImages(canvas,R.drawable.yellow,k,i);

                }

                else if (
                (Main2Activity.action.game.getBoard().getBoard()[i][k].getOwner().equals("p1"))){
                    gridPaint.setColor(Color.RED);
                    drawImages(canvas,R.drawable.redbox,k,i);

                    drawString(String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getPopulation()),
                            String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getTracker()),k,i,canvas);
                }
                else if (
                        (Main2Activity.action.game.getBoard().getBoard()[i][k].getOwner().equals("p2"))){

                    drawImages(canvas,R.drawable.bluebox,k,i);
                    drawString(String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getPopulation()),
                            String.valueOf(Main2Activity.action.game.getBoard().getBoard()[i][k].getTracker()),k,i,canvas);
                }
                else{
                   gridPaint.reset();

                }
            }
        }

    }

    /** for performance only
    * Sets up the location of rectangular area on screen
     * to be used for drawing on Canvas
     */
    RectF rectF = new RectF();
    private void setRectFromRowCol(int row, int col) {
        float left = scale_x * col  ;
        float top = scale_y * row ;
        float right = left + scale_x;
        float bottom = top + scale_y;
        rectF.set(left, top, right, bottom);
    }

    // loads the image based on the name of the image (without the file extension)
    private Bitmap loadImage(String name) {
        return BitmapFactory.decodeResource(activity.getResources(),
                activity.getResources().getIdentifier(name, "drawable", getClass().getPackage().getName()));
    }


    // loads the image based on the resource of the image
    private Bitmap loadImagefromID(int resid) {
        return BitmapFactory.decodeResource(activity.getResources(), resid);
    }


    /** Uses Bitmap class to draw images from drawables folder on the canvas
*/
    public void drawImages(Canvas canvas,String imageName, int row, int col) {

        Bitmap image = loadImage(imageName);
        setRectFromRowCol(row,col);
        canvas.drawBitmap(image, null, rectF, null);
    }

    /** Uses Bitmap class to draw images from drawables folder on the canvas
     */
    public void drawImages(Canvas canvas,int resid, int row, int col) {

        Bitmap image = loadImagefromID(resid);
        setRectFromRowCol(row,col);
        canvas.drawBitmap(image, null, rectF, null);
    }



    /**
     * This method should eventually draw all of the pieces represented by the current game state.
     * For now will just put some pieces on the board
     * Calls a separate method to draw each x and o by computing the correct upper left
     * corner x and y position and passing these into the other methods.
     *
     * @param canvas
     */
    protected void drawPieces(Canvas canvas) {
        // local variables for current row and column
        int row;
        int col;

        // creating one Paint object for all x's
       /* Paint pieceXPaint = new Paint();
        pieceXPaint.setColor(Color.RED);
        pieceXPaint.setStrokeWidth(8);
        pieceXPaint.setStyle(Style.STROKE);*/

        // creating one Paint object for all o's
      /*  Paint pieceOPaint = new Paint();
        pieceOPaint.setColor(Color.GREEN);
        pieceOPaint.setStrokeWidth(8);
        pieceOPaint.setStyle(Style.FILL);*/

        row = 1;
        col = 1;


        //drawString("100", col, row, canvas);
        /** Some examples of bitmap images,
         * drawing an X, drawing an O
         */

        // draw a bitmap (using a resource id) at row 0, col 0
        //  row = 0; col = 0;
        // drawImages(canvas,xImage,row,col);

        // draw a bitmap (using a name)  at row 1, col 2
        //  row = 1;col = 2;
        //  drawImages(canvas,R.drawable.tilewar,row,col);
        //draw an X using Paint at row 2, col 1
        //row = 2;  col =1;
        // drawX(col*scale_x, row*scale_y, canvas, pieceXPaint);
        //draw an O using Paint at row 1, col 1
        // row = 1;  col =0;
        // drawO(col*scale_x, row*scale_y, canvas, pieceOPaint);

        //for(int i )

    }

    /**
     * Draws an X at a square given the x,y position of the upper left corner of the square.
     * Uses a 5 pixel buffer so that the StrokeWidth of the lines does not cause the X
     * to write over the grid into the neighboring squares:
     *
     * -----
     * |   |
     * | X |
     * |   |
     * -----
     *
     * where each of the spaces above are 5 pixels in height and width.
     *
     * @param posx
     * @param posy
     * @param canvas
     * @param paint
     */
    private void drawX(float posx, float posy, Canvas canvas, Paint paint) {
        float xheight = scale_y - 10; // 5 pixel buffer
        float xwidth = scale_x - 10;

        canvas.drawLine(posx + 5, posy + 5, posx + 5 + xwidth, posy + 5 + xheight, paint);
        canvas.drawLine(posx + 5 + xwidth, posy + 5, posx + 5, posy + 5 + xheight, paint);
    }

    private void drawString(String string,String tracker, int row, int col, Canvas canvas){
        float xheight = scale_y - 10; // 5 pixel buffer
        float xwidth = scale_x - 10;
        Paint numberpaint = new Paint();
        numberpaint.setColor(Color.BLACK);
        numberpaint.setStrokeWidth(8);
        numberpaint.setStyle(Style.FILL);

        Paint numberpaint2 = new Paint();
        numberpaint.setColor(Color.BLACK);
        numberpaint.setStrokeWidth(8);
        numberpaint.setStyle(Style.FILL);

        float posnx=0;
        double stringscaley = scale_y*.7;
        double stringscalex = 0;
     //   double stringscalex = scale_x*.25;

        float posny = (float)(row*(scale_y*.65)+scale_y*2);
        numberpaint.setTextSize((float)(scale_x*.50));
        numberpaint2.setTextSize((float)(scale_x*.30)); //tracker
        posnx = (float) (col*scale_x);
        posny = (float) (scale_y*row);

        if(string.length()==1){
            stringscalex = scale_x*.5;
        }
        else if (string.length()==2){
            stringscalex = scale_x*.25;
        }
        else{
            stringscalex = scale_x*.3;
            numberpaint.setTextSize((float)(scale_x*.40));
        }

        if(tracker.length()==1){
            posnx = (float)(posnx*1.05);
        }
        else if (string.length()==2){
            posnx = (float)(posnx);
        }
        else{
            stringscalex = 0;
        }

        // canvas.drawRect(scale_x*i, scale_y*k, scale_x*(i+1), scale_y*(i+1), gridPaint);




        if(row ==0){
            canvas.drawText(string,posnx+(float)stringscalex,(float)stringscaley,numberpaint);
            canvas.drawText(tracker, (float)(posnx), (float)(stringscaley*.48),numberpaint2);
        }
        else{
            canvas.drawText(string,posnx+(float)stringscalex,posny+(float)stringscaley,numberpaint);
            canvas.drawText(tracker, (float)(posnx), posny+(float)(stringscaley*.48),numberpaint2);
        }




    }

    /**
     * Draws an O at a square given the x,y position of the upper left corner of the square.
     * Uses a 5 pixel buffer so that the StrokeWidth of the lines does not cause the O
     * to write over the grid into the neighboring squares:
     *
     * -----
     * |   |
     * | O |
     * |   |
     * -----
     *
     * where each of the spaces above are 5 pixels in height and width.
     *
     * @param posx
     * @param posy
     * @param canvas
     * @param paint
     */
    private void drawO(float posx, float posy, Canvas canvas, Paint paint) {
        float radius = Math.min(scale_y/2-5, scale_x/2-5); // 5 pixel buffer

        canvas.drawCircle(posx + scale_x/2, posy + scale_y/2, radius, paint);

    }

    private void updateScaling() {
        scale_x = (float)width / numCols;
        scale_y = (float)height / numRows;
    }


    /**
     * This method is called by the Android platform when the app window size changes.
     * We store the initial setting of these so that we can compute the exact locations
     * to draw the components of our View.
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        updateScaling();
    }

}




