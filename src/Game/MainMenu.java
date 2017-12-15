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

import com.sun.xml.internal.ws.developer.StreamingAttachment;

public class MainMenu extends BasicGameState {
	
	private Image imgMainMenu;
	String string = "Press Space To Play!!!";
	MyFont strInGame;
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		imgMainMenu = new Image("images/mainMenu.jpg");
		strInGame = new MyFont("font/Snowdrift.ttf", 30);
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		imgMainMenu.draw(0, 0);
		strInGame.myFontDraw(300, 500, string, Color.black);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		
		if(gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}
	
	
}
