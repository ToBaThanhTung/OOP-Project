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
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import net.java.games.input.Mouse;
import obj.Candy;
import obj.Matrix2D;
import obj.Pos;
import sun.misc.GC;
import sun.security.util.Length;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	
	private Image inGame;
	Matrix2D matrix2d;
	String mouse = "No Input!";
	int lengthColum = 9;
	int lengthRow = 9;
	public static boolean isClickLeftMouse;
	public static int posX;
	public static int posY;
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
		posX = gc.getInput().getMouseX();
		posY = gc.getInput().getMouseY();
		mouse = "X:" + posX + "Y:" + posY; 
		isClickLeftMouse = gc.getInput().isMousePressed(0);
		detect(gc, sbg);

				
	}
	public void detect(GameContainer gc, StateBasedGame sbg) throws SlickException{
		for(int i = 0; i <= lengthRow ; i++) {
			for(int j = 0; j < lengthColum; j ++) {
				if(isClickLeftMouse) {
					if(posX > 150 + 80 * j && posX < 230 + 80 * j && posY > 150 + 80 * i && posY < 230 + 80 * i) {
						matrix2d.updateMatrix(i, j);
					}
				}
			}
		}
	}


	@Override
	public int getID() {
		
		return 1;
	}
	
}
