
import app.Command;
import app.DoPlay;
import core.GameManager;

public class Test {
	public static void main(String[] args) {
		GameManager receiver = new GameManager(4);
		Command cmd = new DoPlay(receiver);
		cmd.execute();
	}	
}
