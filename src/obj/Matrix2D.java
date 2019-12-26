package obj;

import java.nio.file.attribute.PosixFileAttributes;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.ws.soap.MTOM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


import Game.CongfigGame;
import Game.InGame;
import MyLog.MyLog;
import sun.text.resources.cldr.bn.FormatData_bn_IN;



public class Matrix2D {
	
	public static Candy[][] MT;
	public static int mt[][] =new int[CongfigGame.lengthRow][CongfigGame.lengthColum];
	private static Random random;
	Image cdImg;
	InGame inGame;
	private Color Color;
	public int x;
	public int y;
	public int activeX;
	public int activeY;
	
	public int fallingX;
	public int fallingY;
	public boolean notDetectRight = false;
	public boolean isActive = false;
	public boolean isFalling = false;
	public boolean isClickOutSide;
	public int check = 0;
	public int [] image= new int[6];
	public int [][] b;
	public boolean notDetectLeft = false;
	public boolean notDetectUp = false;
	public boolean notDetectDown = false 
			;
	public static Pos[][] locateCandy;	
	
	
	
	
	public Matrix2D() throws SlickException {
	
		MT = new Candy[CongfigGame.lengthRow][CongfigGame.lengthColum];
		locateCandy = new Pos[CongfigGame.lengthRow][CongfigGame.lengthRow];
		for(int i = 0 ; i < CongfigGame.lengthColum; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				int type= choosecandy(i,j,MT);
				setMT(i,j,type);
				setPos(i, j);
			}
		}
		showMatrix2D();
		showPos();
	}
	
	public void setPos(int i, int j) {
		Pos newPos = new Pos( 150 + 80 * i, 95 + 80 * j);
		locateCandy[i][j] = newPos; 
	}
	
	public void setMT(int i, int j,int type)
	{
		
		Candy newCandy = new Candy(type , 150 + 80 * i, 95 + 80 * j);
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
	
	public void setFallingXY(int fallingX, int  fallingY) {
		this.fallingX =  fallingX;
		this.fallingY =  fallingY;
	}
	
	
	
	
	public static void showPos() {
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j < CongfigGame.lengthColum; j++) {
				System.out.print(locateCandy[i][j].x + "," + locateCandy[i][j].y + "  ") ;
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void showMatrix2D() {
		for(int i = 0; i < CongfigGame.lengthRow; i++) {
			for(int j = 0; j < CongfigGame.lengthRow; j++) {
				System.out.print(MT[i][j].typeCandy + " ");
			}
			System.out.println();
		}
		System.out.println("");
	}
}