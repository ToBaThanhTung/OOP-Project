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
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import net.java.games.input.Mouse;
import obj.Candy;
import obj.Matrix2D;
import obj.Pos;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	//private Image[][] imgCandy;
	private Image inGame;
	//Candy[][] cdy;// = new Candy[5][5];
	Candy[][] candy;
	Matrix2D matrix2d;
	String mouse = "No Input!";
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
//		candy = new Candy[9][9];
//		for(int i = 0; i < 9; i++) {
//			for(int j = 0; j < 9; j++) {
//				candy[i][j] = new Candy();
				//System.out.print(candy[i][j].getTypeCandy() + " ");
		//	}		
			//System.out.println();
	//	}
	//	int candyX = 150 + 80
		matrix2d = new Matrix2D();
		matrix2d.createMatrix();
		matrix2d.showMatrix2D();
		
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		
		inGame.draw(0, 0);	
		g.setColor(Color.black);
		g.drawString(mouse, 50, 50);
		
//		for(int i = 0; i < 9; i++) {
//			for(int j = 0 ; j < 9; j++) {
//				
//				candy[i][j].myDraw( 150 + 80 * i, 150 + 80 * j);
//				
//				
//			}
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
		
//		for(int i = 0; i < 9; i++) {
//			for(int j = 0; j < 9; j++) {
//				
//				if( (((posX > (150 + 80 * i) && posX < ((150 + 80 * i) + 80 ))) && ( posY > (150 + 80 * j) && posY < ((150 + 80 * j) + 80)))) 
//					if(isClickLeftMouse) {					
//						candy[i][j] = new Candy();
//						mouse =  " checkkkkkkkk "; 
//				} 
//			
//			}
//		}
		
		
		
	}

	@Override
	public int getID() {
		
		return 1;
	}
	
}
