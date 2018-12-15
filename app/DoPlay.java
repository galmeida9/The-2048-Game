package app;

import core.GameManager;

public class DoPlay extends Command {
    private GUIFrame frame;
	public DoPlay(GameManager receiver) {
        super(Label.PLAY, receiver);
        frame = new GUIFrame(_receiver, this);
    }
    

	@Override
	public void execute() {
        if (!_receiver.endGame()) _receiver.addNumber();
        frame.print();
        if (_receiver.endGame()) frame.endGame();
	}
}