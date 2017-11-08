import App.GameManagerPanel;
import App.Log;
import App.Window;
import Utils.Score;
import Utils.ScoreManager;
/**
 * Class use to launch the program 
 * @author baptiste
 *
 */
public class App {
	public static void main(String[] args) {
		Log.debug("App Launch");
		Log.debug("Window start");
		Window window = Window.getInstance();
		Log.debug("Window end");
		Log.debug("GameManagerPanel start");
		GameManagerPanel  gmp = GameManagerPanel.getInstance();
		Log.debug("GameManagerPanel end");
		window.setContentPane(gmp);
		gmp.init();
	    window.revalidate();
	}
}
