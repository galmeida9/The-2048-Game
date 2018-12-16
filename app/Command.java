package app;

import core.GameManager;
import java.io.IOException;

public abstract class Command {
	GameManager _receiver;
	GUIFrame _frame;
	private String _label;
	
	public Command(String label, GameManager gm) {
		_label = label;_receiver = gm;
		try {
            _frame = new GUIFrame(_receiver, this);
        } catch (IOException e) {e.printStackTrace();}
	}
		
	public abstract void execute();
	public void setReceiver(GameManager receiver) {_receiver = receiver;}
}
