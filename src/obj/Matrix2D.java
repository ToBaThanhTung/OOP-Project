package obj;

import java.util.Random;

import org.newdawn.slick.SlickException;

import com.sun.javafx.image.IntPixelAccessor;
import com.sun.javafx.scene.paint.GradientUtils.Point;

import MyLog.MyLog;

public class Matrix2D {
	public static Candy[][] MT =new Candy[9][9];
	public static int mt[][] =new int[9][9];
	private static Random random;
	public static void createMatrix() throws SlickException {
		for(int i = 0 ; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				MT[i][j] = new Candy();
				mt[i][j] = MT[i][j].getN();
			}
		}
	}
	
	public void drawCandy() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0 ; j < 9; j++) {
				MT[i][j].myDraw( 150 + 80 * i, 150 + 80 * j);
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
	}
}
