package app;

import java.io.IOException;
import core.GameManager;

public class DoPlay extends Command {
    private GUIFrame frame;
	public DoPlay(GameManager receiver) {
        super(Label.PLAY, receiver);
        try {
            frame = new GUIFrame(_receiver, this);
        } catch (IOException e) {e.printStackTrace();}
    }
    

	@Override
	public void execute() {
        if (!_receiver.endGame() && _receiver.hasZeroes() && _receiver.validMove()) _receiver.addNumber();
        frame.print();
        if (_receiver.endGame()) frame.endGame();
	}
}