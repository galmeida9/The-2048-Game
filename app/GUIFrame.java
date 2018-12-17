package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import core.GameManager;
import java.util.List;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GUIFrame {
    private GameManager _receiver;
    private List<JButton> _matrixDisplay = new LinkedList<JButton>();
    private Command _command;
    private JLabel _score;
    private JButton _w, _s, _a, _d;
    private JPanel _panel;

    public GUIFrame(GameManager receiver ,Command cmd) throws IOException{
        _receiver = receiver;
        _command = cmd;
        
        /* -------------------------------------------------------------------------------------------------------------------
        Creating the JFrame
        --------------------------------------------------------------------------------------------------------------------*/

        JFrame frame = new JFrame();
        frame.setTitle("The 2048 Game");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        /* -------------------------------------------------------------------------------------------------------------------
        Creating the Exit popup message when closing thw window
        --------------------------------------------------------------------------------------------------------------------*/

        class ExitMessage extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e) {
                String options[] = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Closing", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                if (result == 0) System.exit(0);
            }
        }

        frame.addWindowListener(new ExitMessage());
  
        /* -------------------------------------------------------------------------------------------------------------------
        Adding the background image
        --------------------------------------------------------------------------------------------------------------------*/

        BufferedImage backgroundIMG = ImageIO.read(getClass().getResource("beige_background.jpg"));
        JLabel background = new JLabel(new ImageIcon(backgroundIMG));
        background.setBounds(0, 0, 500, 800);

        /* -------------------------------------------------------------------------------------------------------------------
        Adding the score label
        --------------------------------------------------------------------------------------------------------------------*/

        _score = new JLabel("Score: 0");
        _score.setBounds(20, 0, 500, 100);
        _score.setFont(_score.getFont().deriveFont(30f)); 

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the reset button
        --------------------------------------------------------------------------------------------------------------------*/

        JButton reset = new JButton("Reset");
        reset.setBounds(350, 35, 100, 30);
        reset.setBackground(Color.RED);
        class Reset implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        }

        class RestartKey implements KeyListener {
            @Override
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    restart();
                }
            }
        }

        reset.addActionListener(new Reset());

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the Up key
        --------------------------------------------------------------------------------------------------------------------*/

        JButton _w = new JButton("Up");
        _w.setBounds(200, 500, 90, 90);
        _w.setBackground(Color.ORANGE);
        _w.setFont(_w.getFont().deriveFont(20f));
        class ButtonUp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                _panel.requestFocusInWindow();
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveUp();
                _command.execute();
            }
        }
        _w.addActionListener(new ButtonUp());

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the Down key
        --------------------------------------------------------------------------------------------------------------------*/

        JButton _s = new JButton("Down");
        _s.setBounds(200, 600, 90, 90);
        _s.setBackground(Color.ORANGE);
        _s.setFont(_s.getFont().deriveFont(20f));
        class ButtonDown implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                _panel.requestFocusInWindow();
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveDown();
                _command.execute();
            }
        }
        _s.addActionListener(new ButtonDown());

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the Left Key
        --------------------------------------------------------------------------------------------------------------------*/

        JButton _a = new JButton("Left");
        _a.setBounds(100, 600, 90, 90);
        _a.setBackground(Color.ORANGE);
        _a.setFont(_a.getFont().deriveFont(20f));
        class ButtonLeft implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                _panel.requestFocusInWindow();
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveLeft();
                _command.execute();
            }
        }
        _a.addActionListener(new ButtonLeft());

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the Right key
        --------------------------------------------------------------------------------------------------------------------*/

        JButton _d = new JButton("Right");
        _d.setBounds(300, 600, 90, 90);
        _d.setBackground(Color.ORANGE);
        _d.setFont(_d.getFont().deriveFont(20f));
        class ButtonRight implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                _panel.requestFocusInWindow();
                if (!(_score.getText().equals("YOU LOST")))
                    _receiver.moveRight();
                _command.execute();
            }
        }
        _d.addActionListener(new ButtonRight());

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the KeyListener for the up, down, left and right keys
        --------------------------------------------------------------------------------------------------------------------*/

        class DPad implements KeyListener {
            @Override
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    if (!(_score.getText().equals("YOU LOST")))
                        _receiver.moveRight();
                    _command.execute();
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    if (!(_score.getText().equals("YOU LOST")))
                        _receiver.moveLeft();
                    _command.execute();
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    if (!(_score.getText().equals("YOU LOST")))
                        _receiver.moveUp();
                    _command.execute();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    if (!(_score.getText().equals("YOU LOST")))
                        _receiver.moveDown();
                    _command.execute();
                }
            }
        }

        /* -------------------------------------------------------------------------------------------------------------------
        Creating the JPanel and adding the matrix to be displayed
        --------------------------------------------------------------------------------------------------------------------*/

        _panel = new JPanel();
        _panel.setLayout(null);
        
        int size = _receiver.getMatrixSize();
        int x = 75, y = 100;
        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                _matrixDisplay.add(new JButton(""));
                JButton button = _matrixDisplay.get(c + l*size);
                button.setBounds(x, y, 80, 80);
                button.setBackground(Color.WHITE);
                _panel.add(button);
                x += 90;
            }
            y += 90;
            x = 75;
        }
        
        /* -------------------------------------------------------------------------------------------------------------------
        Adding everything to the JPanel and the Jframe
        --------------------------------------------------------------------------------------------------------------------*/

        _panel.add(_w);
        _panel.add(_s);
        _panel.add(_a);
        _panel.add(_d);
        _panel.add(reset);
        _panel.add(background);
        _panel.addKeyListener(new DPad());
        _panel.addKeyListener(new RestartKey());
        background.add(_score);
        frame.add(_panel);
        frame.setVisible(true);
        _panel.setFocusable(true);
        _panel.setRequestFocusEnabled(true);
        _panel.requestFocusInWindow();
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
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                _score.setText("Score: " + _receiver.getScore());
            }
        }, 5 * 1000);
    }

    public void restart() {
        _panel.requestFocusInWindow();
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            GameManager newGM = new GameManager(4);
            _receiver = newGM;
            _command.setReceiver(newGM);
            _command.execute();
        }
    }
}