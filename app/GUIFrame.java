package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import core.GameManager;
import java.util.List;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;

public class GUIFrame {
    private GameManager _receiver;
    private List<JButton> _matrixDisplay = new LinkedList<JButton>();
    private Command _command;
    private JLabel _score;
    private JButton _w, _s, _a, _d;

    public GUIFrame(GameManager receiver ,Command cmd) {
        _receiver = receiver;
        _command = cmd;
        JFrame frame = new JFrame();
        frame.setTitle("The 2048 Game");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _score = new JLabel("Score: 0");
        _score.setBounds(20, 10, 500, 100);
        _score.setFont(_score.getFont().deriveFont(30f)); 

        JButton _w = new JButton("Up");
        _w.setBounds(200, 450, 90, 90);
        class ButtonUp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveUp();
                _command.execute();
            }
        }
        _w.addActionListener(new ButtonUp());

        JButton _s = new JButton("Down");
        _s.setBounds(200, 550, 90, 90);
        class ButtonDown implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveDown();
                _command.execute();
            }
        }
        _s.addActionListener(new ButtonDown());

        JButton _a = new JButton("Left");
        _a.setBounds(100, 550, 90, 90);
        class ButtonLeft implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveLeft();
                _command.execute();
            }
        }
        _a.addActionListener(new ButtonLeft());

        JButton _d = new JButton("Right");
        _d.setBounds(300, 550, 90, 90);
        class ButtonRight implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveRight();
                _command.execute();
            }
        }
        _d.addActionListener(new ButtonRight());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(_w);
        panel.add(_s);
        panel.add(_a);
        panel.add(_d);

        int size = _receiver.getMatrixSize();
        int pos = 100;

        int x = 125, y = 100;
        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                _matrixDisplay.add(new JButton(""));
                JButton button = _matrixDisplay.get(c + l*size);
                button.setBounds(x, y, 50, 50);
                panel.add(button);
                x += 60;
            }
            y += 60;
            x = 125;
        }

        panel.add(_score);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void print() {
        _score.setText("Score: " + _receiver.getScore());
        _score.setFont(_score.getFont().deriveFont(30f));
        int size = _receiver.getMatrixSize();
        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                JButton button = _matrixDisplay.get(c + l*size);
                button.setText("");
                int value = _receiver.getIndice(l, c);
                if (value != 0) button.setText("" + value);
            }
        }
    }

    public void endGame() {
        _score.setText("YOU LOST");
        _score.setFont(_score.getFont().deriveFont(30f));
    }
}