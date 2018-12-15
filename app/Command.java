package app;

import core.GameManager;

public abstract class Command {
	GameManager _receiver;
	private String _label;
	
	public Command(String label, GameManager gm) {_label = label;_receiver = gm;}
		
	public abstract void execute();
}
