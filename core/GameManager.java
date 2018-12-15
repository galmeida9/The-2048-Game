package core;

public class GameManager {
	private Game _game;
	
	public GameManager(int size) {_game = new Game(size);}
	
	public void moveLeft() {_game.moveLeft();}
	public void moveRight() {_game.moveRight();}
	public void moveUp() {_game.moveUp();}
	public void moveDown() {_game.moveDown();}
	public String showGame() {return _game.showGame();}
	public void addNumber() {_game.addNumber();}	
	public int getScore() {return _game.getScore();}
	public int getMatrixSize() {return _game.getMatrixSize();}
	public String printLine(int line) {return _game.printLine(line);}
	public int getIndice(int l, int c) {return _game.getIndice(l, c);}
	
	public boolean endGame() {
		if (_game.endGame()) return true;
		return false;
	}
}
