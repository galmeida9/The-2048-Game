package app;

import java.io.IOException;
import core.GameManager;

public class DoPlay extends Command {
	public DoPlay(GameManager receiver) { super(Label.PLAY, receiver);}

	@Override
	public void execute() {
        if (!_receiver.endGame() && _receiver.hasZeroes() && _receiver.validMove()) _receiver.addNumber();
        _frame.print();
        if (_receiver.endGame()) _frame.endGame();
	}
}