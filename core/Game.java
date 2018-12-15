package core;

import java.util.Random;

public class Game {
	private Matrix _matrix;
	private int _currentScore = 0;
	private boolean _move = false;
	private boolean _validMove = true;
	
	public Game(int matrixSize) {_matrix = new Matrix(matrixSize);}
	
	public int getScore() {return _currentScore;}
	public void addScore(int value) {_currentScore += value;}
	public String showGame() {return _matrix.toString();}
	public int getMatrixSize() {return _matrix.getSize();}
	public String printLine(int line) {return _matrix.printLine(line);}
	public int getIndice(int l, int c) {return _matrix.getIndice(l, c);}
	public boolean hasZeroes() {return _matrix.hasZeroes();}
	public boolean validMove() {return _validMove;}

	public boolean endGame() {
		if ((_matrix.getNZeroes() == 0) && (_move == false) && (possibleMove() == false)) return true;
		return false;
	}

	public void addNumber() {
		Random rand = new Random();
		while (true) {
			int l = rand.nextInt(_matrix.getSize());
			int c = rand.nextInt(_matrix.getSize());
			int value = (rand.nextInt(2) + 1) * 2;
			if (_matrix.getIndice(l, c) == 0) {
				_matrix.setIndice(l, c, value);
				break;
			}
		}
	}
	
	public void moveRight() {
		int size = _matrix.getSize(), moves = 0, finish, totalMoves = 0;
		_validMove = false;
		for (int l = 0; l < size; l++) {
			finish = 0;
			while (finish == 0) {
				for (int c = (size - 2); c >= 0; c--) {
					if ((_matrix.getIndice(l, c + 1) == 0) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l, c + 1, _matrix.getIndice(l, c));
						_matrix.setIndice(l, c, 0);	
						moves++;
						totalMoves++;
						_validMove = true;
					}
				
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l, c + 1)) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l, c + 1, _matrix.getIndice(l, c) * 2);
						_matrix.setIndice(l, c, 0);
						moves++;	
						totalMoves++;
						addScore(_matrix.getIndice(l, c + 1));
						_validMove = true;
					}
				}
				
				if (moves == 0) finish = 1;
				moves = 0;
			}		
		}
		if (totalMoves > 0) _move = true;
		else _move = false;
		totalMoves = 0;
	}
	public void moveLeft() {
		int size = _matrix.getSize(), moves = 0, finish, totalMoves = 0;
		_validMove = false;
		for (int l = 0; l < size; l++) {
			finish = 0;
			while (finish == 0) {
				for (int c = 1; c < size; c++) {
					if ((_matrix.getIndice(l, c - 1) == 0) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l, c - 1, _matrix.getIndice(l, c));
						_matrix.setIndice(l, c, 0);	
						moves++;
						totalMoves++;
						_validMove = true;
					}
					
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l, c - 1)) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l, c - 1, _matrix.getIndice(l, c) * 2);
						_matrix.setIndice(l, c, 0);
						moves++;
						totalMoves++;
						addScore(_matrix.getIndice(l, c - 1));
						_validMove = true;
					}
				}
				
				if (moves == 0) finish = 1;
				moves = 0;
			}
		}
		if (totalMoves > 0) _move = true;
		else _move = false;
		totalMoves = 0;
	}	
	
	public void moveUp() {
		int size = _matrix.getSize(), moves = 0, finish, totalMoves = 0;
		_validMove = false;
		for (int c = 0; c < size; c++) {
			finish = 0;
			while (finish == 0) {
				for (int l = 1; l < size; l++) {
					if ((_matrix.getIndice(l - 1, c) == 0) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l - 1, c, _matrix.getIndice(l, c));
						_matrix.setIndice(l, c, 0);	
						moves++;
						totalMoves++;
						_validMove = true;
					}
					
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l - 1, c)) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l - 1, c, _matrix.getIndice(l, c) * 2);
						_matrix.setIndice(l, c, 0);
						moves++;
						totalMoves++;
						addScore(_matrix.getIndice(l - 1, c));
						_validMove = true;
					}
				}
				
				if (moves == 0) finish = 1;
				moves = 0;
			}
		}
		if (totalMoves > 0) _move = true;
		else _move = false;
		totalMoves = 0;
	}	
	
	public void moveDown() {
		int size = _matrix.getSize(), moves = 0, finish, totalMoves = 0;
		_validMove = false;
		for (int c = 0; c < size; c++) {
			finish = 0;
			while (finish == 0) {
				for (int l = size - 2; l >= 0; l--) {
					if ((_matrix.getIndice(l + 1, c) == 0) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l + 1, c, _matrix.getIndice(l, c));
						_matrix.setIndice(l, c, 0);	
						moves++;
						totalMoves++;
						_validMove = true;
					}
					
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l + 1, c)) && (_matrix.getIndice(l, c) != 0)) {
						_matrix.setIndice(l + 1, c, _matrix.getIndice(l, c) * 2);
						_matrix.setIndice(l, c, 0);
						moves++;
						totalMoves++;
						addScore(_matrix.getIndice(l + 1, c));
						_validMove = true;
					}
				}
				
				if (moves == 0) finish = 1;
				moves = 0;
			}
		}
		if (totalMoves > 0) _move = true;
		else _move = false;
		totalMoves = 0;
	}	

	public boolean possibleMove() {
		int finish, size = _matrix.getSize();
		/* move right */
		for (int l = 0; l < size; l++) {
			finish = 0;
			while (finish == 0) {
				for (int c = (size - 2); c >= 0; c--) {
					if ((_matrix.getIndice(l, c + 1) == 0) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l, c + 1)) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				}
				finish = 1;
			}		
		}
		/* move left */
		for (int l = 0; l < size; l++) {
			finish = 0;
			while (finish == 0) {
				for (int c = 1; c < size; c++) {
					if ((_matrix.getIndice(l, c - 1) == 0) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
					
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l, c - 1)) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				}
				finish = 1;
			}
		}
		/* move up */
		for (int c = 0; c < size; c++) {
			finish = 0;
			while (finish == 0) {
				for (int l = 1; l < size; l++) {
					if ((_matrix.getIndice(l - 1, c) == 0) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l - 1, c)) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				}
				finish = 1;
			}
		}
		/* move down*/
		for (int c = 0; c < size; c++) {
			finish = 0;
			while (finish == 0) {
				for (int l = size - 2; l >= 0; l--) {
					if ((_matrix.getIndice(l + 1, c) == 0) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
					else if ((_matrix.getIndice(l, c) == _matrix.getIndice(l + 1, c)) && (_matrix.getIndice(l, c) != 0)) {
						return true;
					}
				}
				finish = 1;
			}
		}
		return false;
	}
}
