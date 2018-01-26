package app.candycrisis;

public class Piece {

	private char id;
	
	private int position;
	
	private char character;
	
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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
