package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import core.GameManager;
import java.util.List;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GUIFrame {
    private GameManager _receiver;
    private List<JButton> _matrixDisplay = new LinkedList<JButton>();
    private Command _command;
    private JLabel _score;
    private JButton _w, _s, _a, _d;

    public GUIFrame(GameManager receiver ,Command cmd) throws IOException{
        _receiver = receiver;
        _command = cmd;
        JFrame frame = new JFrame();
        frame.setTitle("The 2048 Game");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage backgroundIMG = ImageIO.read(new File("app\\images\\beige_background.jpg"));
        JLabel background = new JLabel(new ImageIcon(backgroundIMG));
        background.setBounds(0, 0, 500, 800);

        _score = new JLabel("Score: 0");
        _score.setBounds(20, 0, 500, 100);
        _score.setFont(_score.getFont().deriveFont(30f)); 

        JButton _w = new JButton("Up");
        _w.setBounds(200, 500, 90, 90);
        _w.setBackground(Color.ORANGE);
        _w.setFont(_w.getFont().deriveFont(20f));
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
        _s.setBounds(200, 600, 90, 90);
        _s.setBackground(Color.ORANGE);
        _s.setFont(_s.getFont().deriveFont(20f));
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
        _a.setBounds(100, 600, 90, 90);
        _a.setBackground(Color.ORANGE);
        _a.setFont(_a.getFont().deriveFont(20f));
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
        _d.setBounds(300, 600, 90, 90);
        _d.setBackground(Color.ORANGE);
        _d.setFont(_d.getFont().deriveFont(20f));
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
        
        int size = _receiver.getMatrixSize();
        int pos = 100;

        int x = 80, y = 100;
        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                _matrixDisplay.add(new JButton(""));
                JButton button = _matrixDisplay.get(c + l*size);
                button.setBounds(x, y, 80, 80);
                button.setBackground(Color.WHITE);
                panel.add(button);
                x += 90;
            }
            y += 90;
            x = 80;
        }
        
        panel.add(_w);
        panel.add(_s);
        panel.add(_a);
        panel.add(_d);
        panel.add(background);
        background.add(_score);
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