package game.update.free.tilewar;

//Miguel Zavala, Jack Wilson, Tyler Earley
//CISC181, section 012

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * A typical way to "run" a game would be to:
 * 
 * SomeGame g = new SomeGame();
 * g.addGameListener(new SomeGameListener());  // which we will cover in Checkpoint 3
 * g.start();
 * 
 * This code is adapted from
 * @author jatlas
 */


public abstract class Game {

    // Don't override this method
    public final void start() {
        // we will be adding code to this when we cover ticks and listeners
        onStart();
    }

    /**
     * This is the method to override for game specific start
     * logic.
     */
    protected void onStart() {
    }

    // Don't override this method
    private final void end() {
        // we will be adding code to this when we cover ticks and listeners
        onEnd();
    }

        /**
     * This is the method to override for game specific end
     * logic.
     */
    protected void onEnd() {
    }

    /**
     * return true if the Game is over
     */
    public abstract boolean isEnd();
    
    /**
     * It is expected that the Game return a status
     * or String representation of its current state
     */
    public abstract String toString();
    
    /**
     * Performs an action on the game.
     */
    public final void perform(Action action) {
        if (action.isValid(this)) {
            onPerformAction(action);
            if (isEnd()) {
                end();
            }
        }
    }

    /**
     * This is the method to override if you need to do something
     * different than the default update for the Action.
     */
    protected void onPerformAction(Action action) {
        action.update(this);
    }

}
