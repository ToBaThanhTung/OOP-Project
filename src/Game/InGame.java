package Game;

import javax.swing.text.AbstractDocument.LeafElement;

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
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import net.java.games.input.Mouse;
import obj.Candy;
import obj.Matrix2D;
import obj.Pos;
import sun.security.util.Length;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	
	private Image inGame;

	Candy[][] candy;
	Matrix2D matrix2d;
	String mouse = "No Input!";
	int lengthColum = 9;
	int lengthRow = 9;
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {

		inGame = new Image("images/inGame2.jpg");

		matrix2d = new Matrix2D();
		matrix2d.createMatrix();
		matrix2d.showMatrix2D();
		
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		
		inGame.draw(0, 0);	
		g.setColor(Color.black);
		g.drawString(mouse, 50, 50);
		matrix2d.drawCandy(); 
		
		
				
	}
		
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int k) throws SlickException {
		if(gc.getInput().isKeyDown(Input.KEY_BACK)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
		
		
		int posX = gc.getInput().getMouseX();
		int posY = gc.getInput().getMouseY();
		mouse = "X:" + posX + "Y:" + posY; 
		boolean isClickLeftMouse = gc.getInput().isMousePressed(0);
		

		
		
	}

	@Override
	public int getID() {
		
		return 1;
	}
	
}
