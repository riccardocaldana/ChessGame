package model;

/**
 * Classe che rappresenta la posizione in due coordinate
 */
public class Pair {
	private int x;
	private int y;
	
	public Pair(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Ritorna la coordinata x 
	 * @return int x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Ritorna la coordinata y
	 * @return int y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Test di uguaglianza tra Pair
	 * @param other coppia di coordinate
	 * @return boolean: true se uguali, altrimenti false
	 */
	public boolean equals(Object other){
		return other instanceof Pair && this.x==((Pair)other).x && this.y==((Pair)other).y;
	}
}
