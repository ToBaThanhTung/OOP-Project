package obj;

import java.nio.file.attribute.PosixFileAttributes;
import java.security.PublicKey;
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
	
	public Candy[][] MT;
	public static int mt[][] =new int[lengthRow][lengthColum];
	private static Random random;
	Image cdImg;
	InGame inGame;
	private Color Color;
	public int x;
	public int y;
	public int activeX;
	public int activeY;
	public boolean isActive = false;
	public boolean isClickOutSide;
	public int check = 0;
	//Candy candy;
	DrawCandy DrawCandy;
	
	
	public Matrix2D() throws SlickException {
		//DrawCandy = new DrawCandy();
		//DrawCandy.matrix2d = this;
		MT = new Candy[lengthRow][lengthColum];
		for(int i = 0 ; i < 9; i++) {
			for(int j = 0; j < lengthColum; j++) {
				int type = Candy.getTypeCandy();
				Candy newCandy = new Candy(type , 150 + 80 * i, 150 + 80 * j);
				MT[i][j] = newCandy; 
				mt[i][j] = type;
			}
		}
		showMatrix2D();
	}
	
	
	public void setActiveXY(int activeX, int activeY) {
		this.activeX = activeX;
		this.activeY = activeY;
	}
	
	
	
	public void setIsClickOutside(boolean a) {
		this.isClickOutSide = a;
	}
	

	public void updateClickCandy() throws SlickException {
			getImg(x, y);
			cdImg.draw( 150 + 80 * y, 150 + 80 * x, Color.gray);
	}
	
	public void myDraw(int i, int j) throws SlickException {
		getImg(i, j);
		cdImg.draw( 150 + 80 * j, 150 + 80 * i);
	}
	public void getImg(int i, int j) throws SlickException {
				int n = MT[i][j].typeCandy;
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
	
	
	
	
	public static void showMatrix2D() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(mt[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("");
	}
}
