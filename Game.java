package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	//game state ID
	public static final int InGame = 1;
	public static final int MainMenu = 0;
	public static final int GameOver = 3;
	// App Properties
	
	public static int WIDTH = 894;
	public static int HEIGHT = 894;
	public static final int FPS = 60;
	
	
	// Constructor
	
	public Game(String appName) {
		super(appName);
	}


	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenu());
		addState(new InGame());
		
		addState(new GameOver());
	}
	
	
	// Main
	public static void main(String[] args) {	
		try {
			
			AppGameContainer appGameContainer = new AppGameContainer(new Game("CandyCrush Project"));
			appGameContainer.setDisplayMode(WIDTH, HEIGHT, false);
			appGameContainer.setTargetFrameRate(FPS);
			appGameContainer.setShowFPS(true);
			appGameContainer.start();
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
