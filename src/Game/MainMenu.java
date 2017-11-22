package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {
	
	private Image imgMainMenu;
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		imgMainMenu = new Image("images/mainMenu.jpg");
		
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		imgMainMenu.draw(0, 0);
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
