package Game;

import javax.swing.text.AbstractDocument.LeafElement;
import javax.xml.ws.soap.MTOM;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
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
	
	
	// bien tam thoi 
	public static int saveType;
	public static int k = 0;
	boolean isMatching = false;
	public int stateInGame = 0;
	
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
		if(stateInGame == 0) {
			detectClickCandy(gc, sbg);
			detectMove();
			stateInGame = 1;
			
		}
		
		if(stateInGame == 1) {
			detectMatch();
		}
		if(stateInGame == 2) {
			Falling();
		}
		
				
		
		
			
	}
	public void detectClickCandy(GameContainer gc, StateBasedGame sbg) throws SlickException{

			for(int i = 0; i <= lengthRow ; i++) {
				for(int j = 0; j < lengthColum; j ++) {
					if(isClickLeftMouse) {
						if(posX > 150 + 80 * j && posX < 230 + 80 * j && posY > 150 + 80 * i && posY < 230 + 80 * i) {						
							matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
							
							
							matrix2d.MT[i][j].isActived = true;
							matrix2d.isActive = true;
							matrix2d.activeX = i;
							matrix2d.activeY = j;
							
							
							System.out.println("Active : x = " + matrix2d.activeX + ", y  = " + matrix2d.activeY + "  typeCandy: " + matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy);
							
						}
				
						
					}
				}
			}
			
	}
	
	public void detectMove() {
		
			detectMoveRight();
			detectMoveLeft();
			detectMoveUp();
			detectMoveDown();
			
		}
	
	

	void detectMatch() {
		// state hien tai = 1 
		isMatching = false;
		detectMatch5X();
		detectMatch5Y();
		detectMatch4X();
		detectMatch4Y();
		detectMatch3X();
		detectMatch3Y();
		
		
		if(isMatching) {
			stateInGame = 2;
		}
		else {
			stateInGame = 0;
		}
	}

	
	public void swapTypeCandy(int temp, int firstCandy, int lastCandy) {
		temp = firstCandy;
		firstCandy = lastCandy;
		lastCandy = temp;
	}
	
	public void detectMoveRight() {
		if(matrix2d.isActive) {
			if(matrix2d.activeY  + 1  <= lengthColum - 1) {
				if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy != 10)
					if(posY > matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].x && posY < (matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].y &&  posX < matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].y + 80 ) {
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						//Swap
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy + "  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
	//					swapTypeCandy(saveType, matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy, matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy);
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy +"  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
						
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy = saveType;
						System.out.println("after swap: ");
						matrix2d.showMatrix2D();
						
					}

			}
		}
	}
	
	
	public void detectMoveLeft() {
		if(matrix2d.isActive) {
			
				if(matrix2d.activeY - 1 >= 0) {
					if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy != 10)
					if(posY > matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].x && posY < (matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].y &&  posX < matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].y + 80 ) {
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						//Swap
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy + "  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
	//					swapTypeCandy(saveType, matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy, matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy);
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy +"  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
						
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy = saveType;
						System.out.println("after swap: ");
						matrix2d.showMatrix2D();
					}
				
	
			}
		}
	}
	
	public void detectMoveUp() {
		if(matrix2d.isActive) {
			
				if(matrix2d.activeX - 1  >= 0) {
					if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy != 10)
					if(posY > matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].x && posY < (matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].y &&  posX < matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].y + 80 ) {
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						//Swap
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy + "  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
	//					swapTypeCandy(saveType, matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy, matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy);
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy +"  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
						
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy = saveType;
						System.out.println("after swap: ");
						matrix2d.showMatrix2D();
					}
					
		
				}
		}
	}
	
	
	public void detectMoveDown() {
		if(matrix2d.isActive) {
		
				if(matrix2d.activeX + 1  <= lengthRow - 1) {
					if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy != 10)
					if(posY > matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].x && posY < (matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].y &&  posX < matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].y + 80 ) {
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						//Swap
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy + "  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
	//					swapTypeCandy(saveType, matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy, matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy);
	//					System.out.println( matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy +"  " +  matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].typeCandy);
						
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
						System.out.println("after swap: ");
						matrix2d.showMatrix2D();
						
					}
					
		
				}
		}
	}
	
	
	
	void detectMatch3Y() {
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 2) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy ) {
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println("match 3Y at " + i + " " + j);
							isMatching = true;
						}
					}
				}
			}
		}
	}
	
	public void detectMatch3X() {
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 2) < lengthRow) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy ) {
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println("match 3X at: " + i + " " + j);
							isMatching = true;
						}
						
					}
				}
			}
		}
	}
	
	
	public void detectMatch4Y() {
		for(int i = 0 ; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 3) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 3][j].typeCandy) {
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println("match 4Y at:  " + i + " " + j);
							isMatching = true;
						}
					}
				}
			}
		}
	}
	
	public void detectMatch4X() {
		for(int i = 0 ; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 3) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy &&
																								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 3].typeCandy ) {
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println("match is  4X" + i + " " + j);
							isMatching = true;
						}
					}
				}
			}
		}
	}
	
	public void detectMatch5Y() {
		for(int i = 0 ; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 4) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy && 
								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 3][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 4][j].typeCandy) {
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println(" find match 5Y at" + i + " " + j);
							isMatching = true;
						}
					}
				}
			}
		}
	}
	
	
	public void detectMatch5X() {
		for(int i = 0 ; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 4) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy &&
								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 3].typeCandy  && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 4].typeCandy ) {
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println(" find  match 5X at" + i + " " + j);
							isMatching = true;
						}
					}
				}
			}
		}
	}
	
	public void Falling() {
		boolean rp = false;
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 1 ) < lengthRow - 1) {
						if(matrix2d.MT[i + 1][j].typeCandy == 10) {
							saveType = matrix2d.MT[i][j].typeCandy;
							matrix2d.MT[i][j].typeCandy = matrix2d.MT[i + 1][j].typeCandy;
							matrix2d.MT[i + 1][j].typeCandy = saveType;
							rp = true;
							//matrix2d.showMatrix2D();
						}
					}
				}
			}
			
		}
		
	//	saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
	//	matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
	//	matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
		
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy == 10) {
					if(i == 0) {
						matrix2d.MT[i][j].typeCandy = Candy.getTypeCandy();
						System.out.println("Add new random candy at "+ i + " " + j);
						
						rp = true;
						//matrix2d.showMatrix2D();
					}
					
				}
			}
		}
		if(rp) {
			Falling();
		}
		
		stateInGame = 1;
		System.out.println("falling!!!");
		
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
