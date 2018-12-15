import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import app.Command;
import app.DoPlay;
import core.GameManager;

public class App {
    public static void main(String[] args) {
        GameManager receiver = new GameManager(4);
		Command cmd = new DoPlay(receiver);
        cmd.execute();        
    }
}