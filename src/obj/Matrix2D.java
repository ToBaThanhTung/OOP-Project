package obj;

import java.util.Random;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import MyLog.MyLog;

public class Matrix2D {
	public static int[][] MT;
	private static Random random;
	public static void createMatrix() {
		random = new Random();
		for(int i = 0; i < 5; i++) {
			for(int j = 0 ; j < 5; j ++) {
				MT[i][j] = random.nextInt(4);
			}
		}
	}
	
	public Point getPoint(int i, int j) {
		
		//int Px = 
		
		//return new Point(Px, Py);
		
	}
	
	public static void showMatrix2D(int[][] mt) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (mt[i][j] == -1)
					MyLog.print(" " + mt[i][j]);
				else
					MyLog.print("  " + mt[i][j]);
			}
			MyLog.println("");
		}
	}
}
