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
	
	public static Candy[][] MT;
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
	public int [] image= new int[6];
	public int [][] b;
	//Candy candy;
	DrawCandy DrawCandy;
	
	
	
	public Matrix2D() throws SlickException {
		//DrawCandy = new DrawCandy();
		//DrawCandy.matrix2d = this;
		MT = new Candy[lengthRow][lengthColum];
		for(int i = 0 ; i < 9; i++) {
			for(int j = 0; j < lengthColum; j++) {
				int type= choosecandy(i,j,MT);
				setMT(i,j,type);
			}
		}
		showMatrix2D();
	}
	public void setMT(int i, int j,int type)
	{
		
		Candy newCandy = new Candy(type , 150 + 80 * i, 150 + 80 * j);
		MT[i][j] = newCandy; 
		
	}
	public void swap(int x1,int y1,int x2, int y2)
	{
		Candy old= MT[x1][y1];
		MT[x1][y1]=MT[x2][y2];
		MT[x2][y2]=old;
		
	}
	void checkphai(int i, int j,Candy [][] a)
	{
	    if(j<7)
	    {
	        if(a[i][j+1].typeCandy==a[i][j+2].typeCandy)
	        {
	            image[a[i][j+1].typeCandy]=1;
	        }
	    }
	}
	void checktrai(int i, int j,Candy [][] a)
	{
	    if(j>1)
	    {
	        if(a[i][j-1].typeCandy==a[i][j-2].typeCandy)
	        {
	            image[a[i][j-1].typeCandy]=1;
	        }
	    }
	}
	void checktren(int i, int j,Candy [][] a)
	{
	    if(i>1)
	    {
	        if(a[i-1][j].typeCandy==a[i-2][j].typeCandy)
	        {
	            image[a[i-1][j].typeCandy]=1;
	        }
	    }
	}
	void checkduoi(int i, int j,Candy [][] a)
	{
	    if(i<7)
	    {
	        if(a[i+1][j]==a[i+2][j])
	        {
	            image[a[i+1][j].typeCandy]=1;
	        }
	    }
	}
	int choosecandy(int i, int j,Candy [][] a)
	{
	
	        for(int r=0;r<6;++r)
	        {
	            image[r]=0;
	        }
	        checktrai(i,j,a);
	        checktren(i,j,a);
	        int type = Candy.getTypeCandy()+6;
	        while(image[type%6]==1)
	        {
	            --type;
	        }
	        return type%6;
	    
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
				System.out.print(MT[i][j].typeCandy + " ");
			}
			System.out.println();
		}
		System.out.println("");
	}
}