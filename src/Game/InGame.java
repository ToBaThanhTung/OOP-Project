package Game;

import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;



import org.newdawn.slick.font.effects.ColorEffect;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import com.sun.corba.se.spi.activation._ActivatorImplBase;
import com.sun.javafx.font.t2k.T2KFactory;

import javafx.scene.layout.Background;


import net.java.games.input.Mouse;


import obj.Candy;
import obj.DrawCandy;
import obj.Matrix2D;
import obj.Pos;
import obj.TextEffect;
import sun.misc.Signal;
import sun.net.www.content.audio.x_aiff;
import sun.util.logging.resources.logging;


public class InGame extends BasicGameState{
	
	
	private Image inGame;
	// de class matrix 2d
	public static Matrix2D matrix2d;
	// de class draw candy
	DrawCandy  drawCandy;
	
	// sign game
	public static final String sign = "Game by IU-007";
	private float signX = 81;
	private float signY = 118;
	
	// draw mouse pos to debug game
	String mouse = "No Input!";
	String scoreString = "No";
	
	// is click left mouse
	public static boolean isClickLeftMouse;
	
	// position of mouse
	public static float posX;
	public static float posY;
	
	
	// random text score
	Random random = new Random();
	
	// list obj text score and time
	public static ArrayList<TextEffect> textEffectsArr;

	
	// candy temp
	public static int saveType;
	
	// boolean variable
	public static boolean isMatching = false;
	public static boolean gameOver = false;
	
	// state game
	public int stateInGame = 0;
	
	// time
	public static int countTime = 0;
	public static int minute = 60;

	// score game
	public static int Score = 0;
	
	
	// font define	
	private boolean isSignMoveRight = true;
	public MyFont fontScore;
	public static MyFont fontSign;
	public static MyFont scoreApear; 
	private String time;
	
	
	// sound
	MySound soundClickButton;
	MySound soundSwap;
	MySound match3Sound;
	MySound match4Sound;
	MySound match5Sound;
	MySound collectScoreSound;
	MySound themeSound;
	
	// fix bug with boolean variable
	public boolean checkbug = false;
	
	
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		
		// back ground inGame
		inGame = new Image("images/Frosting_Springs_background.jpg");
		
		
		// install obj matrix2d
		matrix2d = new Matrix2D();
		
		// install arr obj text effect
		textEffectsArr = new ArrayList<TextEffect>();
		
		// in stall obj font score
		fontScore = new MyFont("font/1979_dot_matrix.ttf", 25);
		fontSign = new MyFont("font/scoreApear.ttf", 20);
		scoreApear = new MyFont("font/Snowdrift.ttf", 25 );
		
		// init sound
		soundClickButton = new MySound("sound/Button_press.ogg");
		soundSwap = new MySound("sound/swapSound.ogg");
		match3Sound = new MySound("sound/match3Sound.ogg");
		match4Sound = new MySound("sound/match4Sound.ogg");
		match5Sound = new MySound("sound/match5Sound.ogg");
		collectScoreSound = new MySound("sound/collectScore.ogg");
		themeSound = new MySound("sound/theme.ogg");
		// play & loop theme song 
		themeSound.myPlayLoopSound();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		// draw a background behind candy
		g.setColor(Color.white);
        g.fillRect(0, 0, 900, 900);
        inGame.draw(0, 0);	
        g.setColor(new Color(0, 0, 70, 100));
        g.fillRoundRect(80, 140, 730, 730, 30, 30);
      
		
       
		// draw Score & Time 
		fontScore.myFontDraw(250, 50,  scoreString + "    " + time , Color.blue);
		
		// draw my Sign
		fontSign.myFontDraw(signX, signY, sign, Color.red);
		
		g.setColor(Color.black);
		
		g.drawString(mouse, 50, 50);
		
		
		// draw candy & text effect
		drawCandy = new DrawCandy();
		
		
	}
		
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		// make Sign String move
		if(isSignMoveRight == true) {
			signX += 0.5f;
			//System.out.println(signX);
			if(signX == 631.0) {
				isSignMoveRight =  false;
			}
		}
		else {
			signX -= 0.5f;
			if(signX == 81) {
				isSignMoveRight = true;
			}
		}
		
		// delta = 1000ms
		
		countTime += delta;
		
		// tao hieu ung dem nguoc
		time = "Time:  " + (minute - (countTime / 1000));
		
		// update score
		scoreString = "Score: " + Score; 
		
		
		if(gc.getInput().isKeyDown(Input.KEY_BACK)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
		
		// update postion of mouse 
		posX = gc.getInput().getMouseX();
		posY = gc.getInput().getMouseY();
		mouse = "X:" + posX + "Y:" + posY; 
		
		// set true when mouse clicking
		isClickLeftMouse = gc.getInput().isMousePressed(0);
		
		// delete text 
		deleteText(); 
		// detect when mouse hit text score
		collectScore();
		
		// xu li roi candy
		testFalling();
		
		drawSwapRight();
		
		
		// logic game
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
		if(stateInGame == 3) {
			checkIsNotDetect();
		}
		
		
		// check game over
		if(minute - (countTime / 1000) == 0) {
			gameOver = true;
			
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
		
				
		
		resetWhenGameOver();
			
	}
	
	
	
public void detectMove() {
		
		detectMoveRight();
		detectMoveLeft();
		detectMoveUp();
		detectMoveDown();
		
	}



public void detectMatch() {
	
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
			matrix2d.notDetectRight = false;
			matrix2d.notDetectDown = false;
			matrix2d.notDetectLeft = false;
			matrix2d.notDetectUp = false;
			checkbug = false;
		}
		else {
			checkbug = true;
			stateInGame = 3;
		}
}

public void checkIsNotDetect() {
	if(matrix2d.notDetectRight) {
		
		saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy = saveType;
	
		matrix2d.notDetectRight = false;
	}
	
	if(matrix2d.notDetectLeft) {
		saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy = saveType;
		
		matrix2d.notDetectLeft = false;
	}
	if(matrix2d.notDetectUp) {
		saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy = saveType;
	
		matrix2d.notDetectUp = false;
	}
	
	if(matrix2d.notDetectDown) {
		saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
		matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
		
		matrix2d.notDetectDown = false;
	}
	
	stateInGame = 0;
	
}
		



	
	public static void resetWhenGameOver() {
		if(gameOver == true) {
			//Score = 0;
			isMatching = false;
			countTime = 0;
			minute = 60;
			try {
				matrix2d = new Matrix2D();
			} catch (SlickException e) {
				
				e.printStackTrace();
			}
			gameOver = false;
			
		}
	}
	
	
	public void drawSwapRight() {
		if(checkbug)
		for(int i = 0; i < CongfigGame.lengthRow ; i++) {
			for(int j = 0; j < CongfigGame.lengthColum; j ++) {
				
					if(matrix2d.MT[i][j].y > matrix2d.locateCandy[i][j].y) {
						matrix2d.MT[i][j].y -= 40;
					}
					
					if(matrix2d.MT[i][j].y < matrix2d.locateCandy[i][j].y) {
						matrix2d.MT[i][j].y += 40;
					}
					
					if(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x) {
						matrix2d.MT[i][j].x += 40;
					}
					
					if(matrix2d.MT[i][j].x > matrix2d.locateCandy[i][j].x) {
						matrix2d.MT[i][j].x -= 40;
					}
					
				
				
			}
		}
	}
	
	
	
	public void detectClickCandy(GameContainer gc, StateBasedGame sbg) throws SlickException{

			for(int i = 0; i <= CongfigGame.lengthRow ; i++) {
				for(int j = 0; j < CongfigGame.lengthColum; j ++) {
					if(isClickLeftMouse) {
						if(posX > 95 + 80 * j && posX < 95 + 80 + 80 * j && posY > 150 + 80 * i && posY < 230 + 80 * i) {		
							soundClickButton.myPlaySound();
							matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
							
							
							matrix2d.MT[i][j].isActived = true;
							matrix2d.isActive = true;
							matrix2d.activeX = i;
							matrix2d.activeY = j;
							
							//System.out.println(150 + 80 * j);
							System.out.println("Active : x = " + matrix2d.activeX + ", y  = " + matrix2d.activeY + "  typeCandy: " + matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy);
							System.out.println("vi tri X = " + matrix2d.MT[i][j].y + " Vi tri Y =" + matrix2d.MT[i][j].x );
						}
				
						
					}
				}
			}
			
	}
	
	
	
	
	
	
	
	



	
	public void detectMoveRight() {
		// check candy is active?
		if(matrix2d.isActive) {
			// check is hit the end of colum
			if(matrix2d.activeY  + 1  <= CongfigGame.lengthColum - 1) {
				// check xem candy va candy ben phai no co dang falling hay ko
				if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy != 10)
					// if mouse hit the right candy
					if(posY > matrix2d.MT[matrix2d.activeX ][matrix2d.activeY + 1].x && posY < (matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].y &&  posX < matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].y + 80 ) {
						
						
						//play sound
						soundSwap.myPlaySound();
						
						//de-active 
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						
						
						
						// swap candy
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].typeCandy = saveType;
						
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].y += 80;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY + 1].y -= 80;
						
						matrix2d.notDetectRight = true;
						
						
						//matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isRight = true;
												
						// move to detect candy
						stateInGame = 1;
						
						// console 
						System.out.println("after swap: ");
						matrix2d.showMatrix2D();
						
					}

			}
		}
	}
	
	
	public void detectMoveLeft() {
		// check candy is active?
		if(matrix2d.isActive) {
			// check is hit the begin of colum
				if(matrix2d.activeY - 1 >= 0) {
					// check xem candy va candy ben trai no co dang falling hay ko
					if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy != 10)
						// if the mouse hit the left candy
						if(posY > matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].x && posY < (matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].y &&  posX < matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].y + 80 ) {
						
						//play sound
						soundSwap.myPlaySound();
						
						
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						// swap candy
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].typeCandy = saveType;
						
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].y -= 80;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY - 1].y += 80;
						
						matrix2d.notDetectLeft = true;
						
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
						//play sound
						soundSwap.myPlaySound();
						
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						// swap
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].typeCandy = saveType;
						
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].x -= 80;
						matrix2d.MT[matrix2d.activeX - 1][matrix2d.activeY].x += 80;
						
						matrix2d.notDetectUp = true;
						
						System.out.println("after swap: ");
						stateInGame = 1;
						matrix2d.showMatrix2D();
					}
					
		
				}
		}
	}
	
	
	public void detectMoveDown() {
		if(matrix2d.isActive) {
				if(matrix2d.activeX + 1  <= CongfigGame.lengthRow - 1) {
					if(matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy != 10 && matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy != 10)
					if(posY > matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].x && posY < (matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].x + 80)
							&& posX > matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].y &&  posX < matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].y + 80 ) {
						//play sound
						soundSwap.myPlaySound();
						
						//de-active
						matrix2d.isActive = false;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].isActived = false;
						
						
						saveType = matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].typeCandy = matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy;
						matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].typeCandy = saveType;
						
						matrix2d.MT[matrix2d.activeX][matrix2d.activeY].x += 80;
						matrix2d.MT[matrix2d.activeX + 1][matrix2d.activeY].x -= 80;
						
						matrix2d.notDetectDown = true;

						
						System.out.println("after swap: ");
						stateInGame = 1;
						matrix2d.showMatrix2D();
						
					}
					
		
				}
		}
	}
	

	
	// add the text to array
	public void addtext( float x, float y, int type, float moveSpeedY, String text, int typeScore) {
		textEffectsArr.add(new TextEffect(x, y, type, moveSpeedY, text, typeScore));
	}
	
	// collect score
	public void collectScore() {
		for(TextEffect t : textEffectsArr ) {
			// if mouse hit the text
			if(posX >= t.x && posX <= (t.x + 70) && posY >= t.y && posY <= (t.y  + 70)) {
				t.moveScore = true;
				t.isDestroy = true;
				collectScoreSound.myPlaySound();
				if(t.typeScore == 3)
					Score += 30;
				else if(t.typeScore == 4)
					Score += 40;
				else if(t.typeScore == 5)
					Score += 50;
			}
		}
	}
	// delete text score & text time
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
		// search the board
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j < CongfigGame.lengthColum; j++) {
				
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 2) < CongfigGame.lengthColum) {
						
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy ) {
							
							//rand.nextFloat() * (max - min) + min
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "30 Score", 3);
							
							// addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 0.5f, "+ 2 second");
							
							 // play sound
							match3Sound.myPlaySound();
							
							// set type 10 for all match candy
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							
							// console
							System.out.println("match 3Y at " + i + " " + j);
							
							 
							isMatching = true;
													
							//minute+=2;
							
						}
					}
				}
			}
		}
	}
	
	public void detectMatch3X() {
		// search board
		for(int i = 0; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 2) < CongfigGame.lengthRow) {
						// neu tim duoc 1 day 3 candy trung nhau
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy ) {
							
							// add text score
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "30 Score", 3);
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 2 second", 0);
							
							// play sound
							match3Sound.myPlaySound();
							
							// danh dau 
							for(int k = 0 ; k < 3; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							
							
							System.out.println("match 3X at: " + i + " " + j);
							isMatching = true;
							
							//Score += 30;
						//	minute+=2;
							
						}
						
					}
				}
			}
		}
	}
	
	
	public void detectMatch4Y() {
		for(int i = 0 ; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 3) < CongfigGame.lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 3][j].typeCandy) {
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "40 Scoreee", 4);
							//addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 1 second");
							
							// play sound
							match4Sound.myPlaySound();
							
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println("match 4Y at:  " + i + " " + j);
							isMatching = true;
							//Score += 40;
							minute+=1;
						}
					}
				}
			}
		}
	}
	
	public void detectMatch4X() {
		for(int i = 0 ; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 3) < CongfigGame.lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy &&
																								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 3].typeCandy ) {
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "40 Scoreee", 4);
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 1 second", 0);
							match4Sound.myPlaySound();
							for(int k = 0; k < 4; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println("match is  4X" + i + " " + j);
							isMatching = true;
						//	Score += 40;
							minute+=1;
						}
					}
				}
			}
		}
	}
	
	public void detectMatch5Y() {
		for(int i = 0 ; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 4) < CongfigGame.lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 1][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 2][j].typeCandy && 
								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 3][j].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i + 4][j].typeCandy) {
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "50 Scoreeeee", 5);
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 2 second", 0);
							match5Sound.myPlaySound();
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i + k][j].typeCandy = 10;
							}
							System.out.println(" find match 5Y at" + i + " " + j);
							isMatching = true;
						//	Score += 50;
							minute+=2;
						}
					}
				}
			}
		}
	}
	
	
	public void detectMatch5X() {
		for(int i = 0 ; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((j + 4) < CongfigGame.lengthColum) {
						if(matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 1].typeCandy && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 2].typeCandy &&
								matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 3].typeCandy  && matrix2d.MT[i][j].typeCandy == matrix2d.MT[i][j + 4].typeCandy ) {
							addtext(random.nextFloat() * 810, random.nextFloat() * (894 - 150 ) + 150 , matrix2d.MT[i][j].typeCandy, 5, "50 Scoreee", 5);
							addtext(500, 50 , matrix2d.MT[i][j].typeCandy, 1, "+ 2 second", 0);
							match5Sound.myPlaySound();
							for(int k = 0; k < 5; k++) {
								matrix2d.MT[i][j + k].typeCandy = 10;
							}
							System.out.println(" find  match 5X at" + i + " " + j);
							isMatching = true;
							
						//	Score += 50;
							minute+=2;
						}
					}
				}
			}
		}
	}

	
	public void testFalling() {
		if(!checkbug) {
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j < CongfigGame.lengthColum; j++) {
				if(matrix2d.MT[i][j].isFalling) {
					
					
						if(matrix2d.MT[i][j].x < matrix2d.locateCandy[i][j].x ) {
							matrix2d.MT[i][j].x += 40;
							
							
						}
						else if(matrix2d.MT[i][j].x == matrix2d.locateCandy[i][j].x) {
							matrix2d.MT[i][j].isFalling = false;
						}
				}
			}
		}
		}
	}
	
	public void Falling() {
		if(!checkbug) {
		boolean rp = false;
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j <	CongfigGame.lengthColum; j++) {
				if(matrix2d.MT[i][j].typeCandy != 10) {
					if((i + 1 ) < CongfigGame.lengthRow ) {
						if(matrix2d.MT[i + 1][j].typeCandy == 10) {
						 
							saveType = matrix2d.MT[i][j].typeCandy;
							matrix2d.MT[i][j].typeCandy = matrix2d.MT[i + 1][j].typeCandy;
							matrix2d.MT[i + 1][j].typeCandy = saveType;
							
							matrix2d.MT[i + 1][j].x = matrix2d.MT[i][j].x;
							matrix2d.MT[i + 1][j].isFalling = true; 
							
							rp = true;
							matrix2d.showMatrix2D();
							
						}
					}
				}
			}
			
		}
	
		
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j < CongfigGame.lengthColum; j++) {
				if(matrix2d.MT[i][j].typeCandy == 10) {
					if(i == 0) {
						matrix2d.MT[i][j].x = 70;
						matrix2d.MT[i][j].isFalling = true;
						
						matrix2d.MT[i][j].typeCandy = Candy.getTypeCandy();
						System.out.println("Add new random candy at "+ i + " " + j);
						
						rp = true;
						matrix2d.showMatrix2D();
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
	}
	
	
	@Override
	public int getID() {
		
		return 1;
	}
	
}
