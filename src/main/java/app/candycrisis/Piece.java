package app.candycrisis;

public class Piece {

	private char id;
	
	private int position;
	
	private char character;
	
	public final static int OUT_OF_BOUNDS_POSITION = -1;
	
	public Piece(char id, int position, char character) {
		this.id = id;
		this.position = position;
		this.character = character;
	}
	
	public char getId() {
		return id;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getNeighboringTopPosition() {
		int top = this.getPosition() - 5;
		
		if (top < 0) {
			top = Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return top;
	}
	
	public int getNeighboringBottomPosition() {
		int bottom = this.getPosition() + 5;
		
		if (bottom >= 15) {
			bottom = Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return bottom;
	}
	
	public int getNeighboringRightPosition() {
		int right = this.getPosition() + 1;
		
		if (right % 5 == 0) {
			return Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return right;
	}
	
	public int getNeighboringLeftPosition() {
		if (this.getPosition() % 5 == 0) {
			return Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return this.getPosition() - 1;
	}
	
	public int[] getNeighboringPositions() {
		return new int[]{
			this.getNeighboringTopPosition(),
			this.getNeighboringRightPosition(),
			this.getNeighboringBottomPosition(),
			this.getNeighboringLeftPosition()
		};
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return (new StringBuilder())
			.append(this.id)
			.append(" : ")
			.append(this.character).toString();
	}
	
}
