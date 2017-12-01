package Game;

import javax.swing.text.AbstractDocument.LeafElement;
import javax.xml.ws.soap.MTOM;

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
import obj.DrawCandy;
import obj.Matrix2D;
import obj.Pos;
import sun.misc.GC;
import sun.security.util.ECKeySizeParameterSpec;
import sun.security.util.Length;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	
	private Image inGame;
	public static Matrix2D matrix2d;
	DrawCandy  drawCandy;
	String mouse = "No Input!";
	int lengthColum = 9;
	int lengthRow = 9;
	public static boolean isClickLeftMouse;
	public static int posX;
	public static int posY; 
	public static int activeX;
	public static int activeY;
	public static int k = 0;
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {

		inGame = new Image("images/inGame2.jpg");

		matrix2d = new Matrix2D();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		inGame.draw(0, 0);	
		g.setColor(Color.black);
		g.drawString(mouse, 50, 50);
		//matrix2d.drawCandy(); 
		drawCandy = new DrawCandy();
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
		detectClickCandy(gc, sbg);
		//detectClickOutSideCandy(gc, sbg);
				
	}
	public void detectClickCandy(GameContainer gc, StateBasedGame sbg) throws SlickException{

			for(int i = 0; i <= lengthRow ; i++) {
				for(int j = 0; j < lengthColum; j ++) {
					if(isClickLeftMouse) {
						if(posX > 150 + 80 * j && posX < 230 + 80 * j && posY > 150 + 80 * i && posY < 230 + 80 * i) {						
							matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
							
							
							matrix2d.MT[i][j].isActived = true;
							matrix2d.isActive = true;
							matrix2d.setActiveXY(i, j);
							
							
							System.out.println("Active : x = " + i + ", y  = " + j);
						}
				
						
					}
				}
			}
			
	}
	


	public void setActive(int activeX, int activeY) {
		this.activeX = activeX;
		this.activeY = activeY;
	}
	
	@Override
	public int getID() {
		
		return 1;
	}
	
}
