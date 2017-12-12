package obj;

public class Pos {
	public float x;
	public float y;
	public Pos(float x, float y){
		this.x = x;
		this.y = y;
		
	}
	@Override
	public String toString() {
		return ("x = " + x + "  y = " + y);
	}
	
}
