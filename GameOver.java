package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOver extends BasicGameState{

	public Image overGameImage;
	//public InGame inGame;
	public MyFont myFont;
	public int score;
	private int posX;
	private int posY;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		overGameImage = new Image("images/inGame2.jpg");
		myFont = new MyFont("font/scoreApear.ttf", 50);
		//score = inGame.Score;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		overGameImage.draw(0, 0);
		myFont.myFontDraw(200, 300, "GAME OVER !!!", Color.black);
		
		myFont.myFontDraw(200,400, "your score: " + score, Color.black);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		score = InGame.Score;
		posX = gc.getInput().getMouseX();
		posY = gc.getInput().getMouseY();
		if(gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			InGame.Score = 0;
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			
		}
		
	}

	@Override
	public int getID() {
		
		return 3;
	}
	
}
