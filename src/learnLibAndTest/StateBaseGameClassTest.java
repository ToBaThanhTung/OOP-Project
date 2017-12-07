package learnLibAndTest;

//import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class StateBaseGameClassTest extends StateBasedGame{
	// game state identifiers
	public static final int InGame = 0;
	public static final int MAINMENU = 1;
	public static final int GAME = 2;
	
	// Application Properties
	public static final int WIDTH = 894;
	public static final int HEIGHT = 894;
	public static final int FPS = 60;
	public static final double VERSION = 0.1;
	
	// Constructor
	
	public StateBaseGameClassTest(String appName) {
		super(appName);
	}
	
	// Initialize game states (calls init method of each game state and set state ID)
	
	public void initStatesList(GameContainer gc) throws SlickException {
	//	this.addState(new SplashScreen());
		addState(new MainMenu());
		addState(new InGame());
		
		
	}
	
	// Main Method
	public static void main(String[] args) {
		try {
				AppGameContainer appGameContainer = new AppGameContainer(new StateBaseGameClassTest("test1" + VERSION));
				appGameContainer.setDisplayMode(WIDTH, HEIGHT, false);
				appGameContainer.setTargetFrameRate(FPS);
				appGameContainer.setShowFPS(true);
				appGameContainer.start();
				
		} catch (SlickException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	

