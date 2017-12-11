package Game;

import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.xml.ws.soap.MTOM;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import com.sun.corba.se.spi.activation._ActivatorImplBase;

import javafx.scene.layout.Background;
import net.java.games.input.Mouse;
import obj.Candy;
import obj.DrawCandy;
import obj.Matrix2D;
import obj.Pos;
import obj.TextEffect;
import sun.misc.GC;
import sun.security.util.ECKeySizeParameterSpec;
import sun.security.util.Length;
import sun.tools.jar.resources.jar;

public class InGame extends BasicGameState{
	
	
	private Image inGame;
	public static Matrix2D matrix2d;
	
	DrawCandy  drawCandy;
	String mouse = "No Input!";
	String scoreString = "No";
	int lengthColum = 9;
	int lengthRow = 9;
	public static boolean isClickLeftMouse;
	public static int posX;
	public static int posY; 
	public static int activeX;
	public static int activeY;
	
	public static ArrayList<TextEffect> textEffectsArr;
	TrueTypeFont fo ;
	
	
	// bien tam thoi 
	public static int saveType;
	public static int k = 0;
	boolean isMatching = false;
	public int stateInGame = 0;
	public int countTime = 0;
	public int Score = 0;
	public int minute = 60;
	// font def
	public MyFont fontScore;
	public MyFont scoreApear;
	private String time;
	public static Pos[][] ofCandy=new Pos [9][9];
	

	public static void setOfCandy(int i , int j) {
		Pos newPos = new Pos( 150 + 80 * i, 150 + 80 * j);
		ofCandy[i][j] = newPos;
	}
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		
		inGame = new Image("images/mainMenu.jpg");

		matrix2d = new Matrix2D();
		textEffectsArr = new ArrayList<TextEffect>();
		fontScore = new MyFont("font/bannerInGame.ttf", 25);
		
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		// draw BackGound
		inGame.draw(0, 0);	
		
		// draw Score & Time 
		fontScore.myFontDraw(250, 50,  scoreString + "      " + time , Color.blue);
		
		g.setColor(Color.black);
		drawCandy = new DrawCandy();
		g.drawString(mouse, 50, 50);
		
		
	
	}
		
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		countTime += delta;
		time = "Time:  " + (minute - (countTime / 1000));
		scoreString = "Score: " + Score; 
		//System.out.println(delta);
		if(gc.getInput().isKeyDown(Input.KEY_BACK)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
		posX = gc.getInput().getMouseX();
		posY = gc.getInput().getMouseY();
		mouse = "X:" + posX + "Y:" + posY; 
		isClickLeftMouse = gc.getInput().isMousePressed(0);
		//test();
		deleteText(); 
		testFalling();
		
		if(stateInGame == 0) {
			detectClickCandy(gc, sbg);
			detectMove();
			
		}
		
		if(stateInGame == 1) {
			detectMatch();
			
		}
		if(stateInGame == 2) {
			Falling();
			
		}
		
				
		
		
			
	}
	int h = 0;
	public void test() {
		
		for(int i = 0; i < lengthRow ; i++) {
			for(int j = 0; j < lengthColum; j ++) {
				if(matrix2d.MT[i][j].isFalling == true) {
					matrix2d.MT[i][j].x+=2;
				}
			}
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
							System.out.println("vi tri X = " + matrix2d.MT[i][j].y + " Vi tri Y =" + matrix2d.MT[i][j].x );
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
						stateInGame = 1;
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
						stateInGame = 1;
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
						stateInGame = 1;
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
						stateInGame = 1;
						matrix2d.showMatrix2D();
						
					}
					
		
				}
		}
	}
	

	public void addtext( float x, float y, int type, float moveSpeedY, String text) {
		textEffectsArr.add(new TextEffect(x, y, type, moveSpeedY, text));
	}
	
	 
	public void deleteText() {
		boolean rp = false;
		for(TextEffect t : textEffectsArr ) {
			if(t.isDestroy) {
				textEffectsArr.remove(t);
				rp = true;
				break;
			}
		}
		if(rp) {
			deleteText();
		}
	}
	
	
	void detectMatch3Y() {
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 2) < lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy ) {
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "30 Score");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 0.5f, "+ 2 second");
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println("match 3Y at " + i + " " + j);
							
							isMatching = true;
							Score += 30;
							minute+=2;
							
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
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "30 Score");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 2 second");
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println("match 3X at: " + i + " " + j);
							isMatching = true;
							Score += 30;
							minute+=2;
							
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
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "40 Scoreee");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 4 second");
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println("match 4Y at:  " + i + " " + j);
							isMatching = true;
							Score += 40;
							minute+=4;
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
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "40 Scoreee");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 4 second");
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println("match is  4X" + i + " " + j);
							isMatching = true;
							Score += 40;
							minute+=4;
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
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "50 Scoreeeee");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 5 second");
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println(" find match 5Y at" + i + " " + j);
							isMatching = true;
							Score += 50;
							minute+=5;
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
							addtext(matrix2d.MT[i][j].y, matrix2d.MT[i][j].x, matrix2d.MT[i][j].typeCandy, 1, "50 Scoreee");
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 5 second");
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println(" find  match 5X at" + i + " " + j);
							isMatching = true;
							Score += 50;
							minute+=5;
						}
					}
				}
			}
		}
	}

//cuuu	
//	public void testFalling() {
//		for(int i = 0; i < lengthColum; i++) {
//			for(int j = 0; j < lengthRow; j++) {
//				if(matrix2d.MT[i][j].isFalling) {
//					
//					
////					while(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x ) {
////						matrix2d.MT[i][j].x += 0.5f;
////					}
////					matrix2d.MT[i][j].isFalling = false;
//					
//					
//						if(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x ) {
//							matrix2d.MT[i][j].x += 15;
//							
//							
//						}
//						else if(matrix2d.MT[i][j].x == matrix2d.locateCandy[i][j].x) {
//							matrix2d.MT[i][j].isFalling = false;
//						}
//				}
//			}
//		}
//	}
	//moi
	
	public void testFalling() {
		for(int j = 0; j < lengthColum; j++) {
			for(int i = 0; i < lengthRow; i++) {
				if(matrix2d.MT[i][j].isFalling) {
//					while(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x ) {
//						matrix2d.MT[i][j].x += 0.5f;
//					}
//					matrix2d.MT[i][j].isFalling = false;
					if( i < 8 && matrix2d.MT[i][j].x==70 && (matrix2d.MT[i+1][j].x-matrix2d.MT[i][j].x==80||matrix2d.MT[i+1][j].typeCandy!=10 )&& matrix2d.MT[i][j].x==70 && matrix2d.MT[i+1][j].x ==matrix2d.locateCandy[i+1][j].x) {
						matrix2d.MT[i][j].typeCandy = Candy.getTypeCandy();
					} 
					
						if(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x && matrix2d.MT[i][j].typeCandy!=10 ) {
							matrix2d.MT[i][j].x += 40;
							
							
						}
						else if(matrix2d.MT[i][j].x == matrix2d.locateCandy[i][j].x) {
							matrix2d.MT[i][j].isFalling = false;
						}
						
				}
			}
		}
	}
	
	
	
//	public void Falling() {
//		boolean rp = false;
//		for(int i = 0; i < lengthColum; i++) {
//			for(int j = 0; j < lengthRow; j++) {
//				if(matrix2d.MT[i][j].typeCandy != 10) {
//					if((i + 1 ) < lengthRow ) {
//						if(matrix2d.MT[i + 1][j].typeCandy == 10) {
//						 
//							saveType = matrix2d.MT[i][j].typeCandy;
//							matrix2d.MT[i][j].typeCandy = matrix2d.MT[i + 1][j].typeCandy;
//							matrix2d.MT[i + 1][j].typeCandy = saveType;
//							
//							matrix2d.MT[i + 1][j].x = matrix2d.MT[i][j].x;
//							matrix2d.MT[i + 1][j].isFalling = true; 
//							
//							rp = true;
//							matrix2d.showMatrix2D();
//							
//						}
//					}
//				}
//			}
//			
//		}
//		
//	//	saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
//	//	matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
//	//	matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
//		
//		for(int i = 0; i < lengthColum; i++) {
//			for(int j = 0; j < lengthRow; j++) {
//				if(matrix2d.MT[i][j].typeCandy == 10) {
//					if(i == 0) {
//						matrix2d.MT[i][j].x = 70;
//						matrix2d.MT[i][j].isFalling = true;
//						
//						matrix2d.MT[i][j].typeCandy = Candy.getTypeCandy();
//						System.out.println("Add new random candy at "+ i + " " + j);
//						
//						rp = true;
//						matrix2d.showMatrix2D();
//					}
//					
//				}
//			}
//		}
//		
//		stateInGame = 3;
//		
//		if(rp) {
//			Falling();
//			
//		}
//		
//		
//		
//		stateInGame = 1;
//		System.out.println("falling!!!");
//		
//	}
//	
	
	public void Falling() {
		boolean rp = false;
		for(int j = 0; j < lengthColum; j++) {
			for(int i = 0;i < lengthRow; i++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 1 ) < lengthRow ) {
						if(matrix2d.MT[i + 1][j].typeCandy == 10) {
						 
							saveType = matrix2d.MT[i][j].typeCandy;
							matrix2d.MT[i][j].typeCandy = matrix2d.MT[i + 1][j].typeCandy;
							matrix2d.MT[i + 1][j].typeCandy = saveType;
							
							matrix2d.MT[i + 1][j].x = matrix2d.MT[i][j].x;
							matrix2d.MT[i +1][j].isFalling = true; 
							
							rp = true;
							matrix2d.showMatrix2D();
							
						}
					}
				}setOfCandy(i,j);
			}
			
		}
		
	//	saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
	//	matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
	//	matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
		
		for(int i = 0; i < lengthColum; i++) {
			for(int j = 0; j < lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy == 10) {
					
						matrix2d.MT[i][j].x = 70;
					
						matrix2d.MT[i][j].isFalling = true;
						
						
						System.out.println("Add new random candy at "+ i + " " + j);
						
						
						matrix2d.showMatrix2D();
					
					
				}
			}
		}
		
		stateInGame = 3;
		
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
