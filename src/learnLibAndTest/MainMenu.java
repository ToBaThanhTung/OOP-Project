package learnLibAndTest;

import java.awt.Polygon;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.sun.prism.Graphics;

public class MainMenu extends BasicGameState {
	public static final int ID = 0;

	
	private Image img;


	private Input input;
	public MainMenu() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sgb) throws SlickException {
		img = new Image("images/mainMenu.jpg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) throws SlickException {
		img.draw(0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sgb, int i) throws SlickException {
			if(gc.getInput().isKeyDown(Input.KEY_SPACE)) {
				sgb.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
	}

	@Override
	public int getID() {
		return MainMenu.ID;
	}
}
