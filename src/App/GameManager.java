package App;

import java.util.ArrayList;

import State.GameState;

public class GameManager {
	private static GameManager instance  = null;
	
	ArrayList<GameState> states;
	
	public static GameManager getInstance() {
		if(instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
	
	private GameManager() {
		states = new ArrayList<GameState>();
	};
	
	public GameState getState() {
    	if(states.isEmpty()) {
    		return null;
    	}
    	return states.get(states.size() - 1);
	}
    
    public void pushState(GameState state) {
    	if(state != null) {
        	if(!this.states.isEmpty()) {
        		this.getState().pause();
        	}
        	this.states.add(state);
        	Log.debug("State \"" + state.getClass().getSimpleName() + "\" push");
        	state.init();
    	} else {
    		Log.error("Error when trying to push state (is Null)");
    	}
    }
 
    public void setState(GameState state) {
    	this.popState();
    	this.pushState(state);
    }
 
    public void popState() {
    	if(this.states.isEmpty()) {
    		Log.error("Error when trying to pop state (No state to pop)");
    	} else {
    		int index = this.states.size()-1;
    		GameState state = this.states.get(index);
    		state.release();
        	Log.debug("State \"" + state.getClass().getSimpleName() + "\" pop");
        	this.states.remove(index);
        	if(!this.states.isEmpty()) {
        		this.states.get(index-1).resume();
        	}
    	}
    }

    
}
