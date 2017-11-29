package obj;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.ws.soap.MTOM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sun.javafx.image.IntPixelAccessor;
import com.sun.javafx.scene.paint.GradientUtils.Point;
import Game.InGame;
import MyLog.MyLog;



public class Matrix2D {
	public static final int lengthColum = 9;
	public static final int lengthRow = 9;
	
	public static int[][] MT =new int[lengthRow][lengthColum];
	public static int mt[][] =new int[lengthRow][lengthColum];
	private static Random random;
	Image cdImg;
	InGame inGame;
	private Color Color;
	public int x;
	public int y;
	public static boolean isClickCandy;
	//Candy candy;
	public static void createMatrix() throws SlickException {
		for(int i = 0 ; i < lengthRow; i++) {
			for(int j = 0; j < lengthColum; j++) {
				mt[i][j] = Candy.getTypeCandy();
			}
		}
	}
	
	
	public void getXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void updateMatrix() throws SlickException {
		if(isClickCandy) {
			getImg(x, y);
			cdImg.draw( 150 + 80 * y, 150 + 80 * x, Color.gray);
		}
	}
	
	public void getImg(int i, int j) throws SlickException {
				int n = mt[i][j];
				if(n == 5 ) {
				cdImg = new Image("images/Redcandy.png");				
				}
				else if(n == 1) {
					cdImg = new Image("images/blueCandy.png");
				}
				else if(n == 0) {
					cdImg = new Image("images/Greencandy.png");
				}
				else if(n == 2) {
					cdImg = new Image("images/Yellowcandy.png");
				}
				else if(n == 3) {
					cdImg = new Image("images/Orangecandy.png");
				}
				else if(n == 4) {
					cdImg = new Image("images/Lightbluecandy.png");
				}
			
		
	}
	
	public void drawCandy() throws SlickException {
		
		for(int i = 0; i < lengthRow; i++) {
			for(int j = 0 ; j < lengthColum; j++) {
				int n = mt[i][j];
				if(n == 5 ) {
				cdImg = new Image("images/Redcandy.png");				
				}
				else if(n == 1) {
					cdImg = new Image("images/blueCandy.png");
				}
				else if(n == 0) {
					cdImg = new Image("images/Greencandy.png");
				}
				else if(n == 2) {
					cdImg = new Image("images/Yellowcandy.png");
				}
				else if(n == 3) {
					cdImg = new Image("images/Orangecandy.png");
				}
				else if(n == 4) {
					cdImg = new Image("images/Lightbluecandy.png");
					
				}
				cdImg.draw( 150 + 80 * j, 150 + 80 * i);				
			
			}
		}
		
	}
	
	
	
	
	public static void showMatrix2D() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(mt[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
	}
}
