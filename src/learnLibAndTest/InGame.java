package learnLibAndTest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InGame extends BasicGameState {
	
	public static final int ID = 1;
	//private Graphics imBackYard;
	
	/*void backYard() throws SlickException {
		
	}
*/
	private Image img;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//img.draw(1116, 602);
		g.drawString("DM NGOC", 50, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return InGame.ID;
	}
	
}
