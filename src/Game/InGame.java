package Game;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import obj.Candy;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	//private Image[][] imgCandy;
	private Image inGame;
	//Candy[][] cdy;// = new Candy[5][5];
	Candy[][] candy;
	
	//public static final int WIDTH = 894;
	//public static final int HEIGHT = 894;
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		/*Random rd = new Random();
		for(int i = 0; i < 5; i++) {
			for(int j = 0 ; j < 5; j++) {
				imgCandy[i][j] = new Image("images/candy" + Integer.toString(rd.nextInt(4) + 0) + ".jpg");
			}
			
		}*/
		
		inGame = new Image("images/inGame2.jpg");
	//	cdy = new Candy[5][5];
		candy = new Candy[6][9];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 9; j++) {
				candy[i][j] = new Candy();
				System.out.print(candy[i][j].getTypeCandy() + " ");
			}		
			System.out.println();
		}
		
		
		
		
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		
		inGame.draw(0, 0);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0 ; j < 9; j++) {
				candy[i][j].myDraw( 50 + 90 * i, 70 + 90 * j);
				
				
			}
			
				
		}
		
	
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		if(gc.getInput().isKeyDown(Input.KEY_BACK)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
		
	}

	@Override
	public int getID() {
		
		return 1;
	}
	
}
