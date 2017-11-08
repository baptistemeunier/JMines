package State;

import java.awt.Graphics;

public interface GameState {
    
	public void init();
    
	void update(double deltatime);

	void draw(Graphics g);

    public void handleEvent();
    
    public void pause();
    
    public void resume();
    
    public void release();


}
