package app.candycrisis;

public class Piece implements Cloneable {

	private int position;

	private char character;
	
	public final static int OUT_OF_BOUNDS_POSITION = -1;
	
	public Piece(int position, char character) {
		this.position = position;
		this.character = character;
	}

	/**
	 * Returns the relative character id based on the position.
	 *
	 * @return
	 */
	public char getId() {
		return (char) (this.getPosition() + 65);
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

	/**
	 * Returns the top index position of the current piece.
	 *
	 * @return the index position of top
	 */
	public int getNeighboringTopPosition() {
		int top = this.getPosition() - 5;
		
		if (top < 0) {
			top = Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return top;
	}

	/**
	 * Returns the bottom index position of the current piece.
	 *
	 * @return the index position of bottom
	 */
	public int getNeighboringBottomPosition() {
		int bottom = this.getPosition() + 5;
		
		if (bottom >= 15) {
			bottom = Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return bottom;
	}

	/**
	 * Returns the right index position of the current piece.
	 *
	 * @return the index position of right
	 */
	public int getNeighboringRightPosition() {
		int right = this.getPosition() + 1;
		
		if (right % 5 == 0) {
			return Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return right;
	}

	/**
	 * Returns the left index position of the current piece.
	 *
	 * @return the index position of left
	 */
	public int getNeighboringLeftPosition() {
		if (this.getPosition() % 5 == 0) {
			return Piece.OUT_OF_BOUNDS_POSITION;
		}
		
		return this.getPosition() - 1;
	}

	/**
	 * Returns the index positions of the neighboring pieces in the sequence of top, right, bottom, left. If an
	 * index position is out of bounds Piece.OUT_OF_BOUNDS_POSITION is assigned.
	 *
	 * @return an array of index positions.
	 */
	public int[] getNeighboringPositions() {
		return new int[]{
			this.getNeighboringTopPosition(),
			this.getNeighboringRightPosition(),
			this.getNeighboringBottomPosition(),
			this.getNeighboringLeftPosition()
		};
	}

	/**
	 * Checks if piece is similar to the given piece.
	 *
	 * @param piece the piece to compare against.
	 * @return boolean
	 */
	public boolean equals(Piece piece) {
		return this.getPosition() == piece.getPosition() &&
				this.getCharacter() == piece.getCharacter();
	}

	@Override
	public String toString() {
		return (new StringBuilder())
			.append(this.getId())
			.append(" : ")
			.append(this.character).toString();
	}

	@Override
	public Piece clone() {
		return new Piece(this.position, this.character);
	}

}
