package ru.vladrus13.textNovel.core;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm {
    private JPanel MainForm;
    private JButton a1thChooseButton;
    private JButton a2thChooseButton;
    private JButton a3thChooseButton;
    private JButton a4thChooseButton;
    private JLabel Name;
    private JLabel Text;
    private Action current;

    public MainForm(Action action) {
        current = action;
        paint();
        a1thChooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                imi(1);
            }
        });
        a2thChooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                imi(2);
            }
        });
        a3thChooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                imi(3);
            }
        });
        a4thChooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                imi(4);
            }
        });
        MainForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (current.getActions().length == 0) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(Action action) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm(action).MainForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void imi(int i) {
        current = current.getActions()[i - 1];
        paint();
    }

    // some dirty code. i will fix this later))))
    private void paint() {
        boolean[] buttonsHider = new boolean[4];
        Text.setText(current.getText());
        Name.setText(current.getWho());
        if (current.getActions().length == 0) {
            a1thChooseButton.setVisible(false);
            a2thChooseButton.setVisible(false);
            a3thChooseButton.setVisible(false);
            a4thChooseButton.setVisible(false);
        } else {
            if (current.getActions().length == 1) {
                a1thChooseButton.setText(current.getAnswers()[0]);
                a1thChooseButton.setVisible(true);
                a2thChooseButton.setVisible(false);
                a3thChooseButton.setVisible(false);
                a4thChooseButton.setVisible(false);
            } else {
                if (current.getActions().length == 2) {
                    a1thChooseButton.setText(current.getAnswers()[0]);
                    a2thChooseButton.setText(current.getAnswers()[1]);
                    a1thChooseButton.setVisible(true);
                    a2thChooseButton.setVisible(true);
                    a3thChooseButton.setVisible(false);
                    a4thChooseButton.setVisible(false);
                } else {
                    if (current.getActions().length == 3) {
                        a1thChooseButton.setText(current.getAnswers()[0]);
                        a2thChooseButton.setText(current.getAnswers()[1]);
                        a3thChooseButton.setText(current.getAnswers()[2]);
                        a1thChooseButton.setVisible(true);
                        a2thChooseButton.setVisible(true);
                        a3thChooseButton.setVisible(true);
                        a4thChooseButton.setVisible(false);
                    } else {
                        a1thChooseButton.setText(current.getAnswers()[0]);
                        a2thChooseButton.setText(current.getAnswers()[1]);
                        a3thChooseButton.setText(current.getAnswers()[2]);
                        a4thChooseButton.setText(current.getAnswers()[3]);
                        a1thChooseButton.setVisible(true);
                        a2thChooseButton.setVisible(true);
                        a3thChooseButton.setVisible(true);
                        a4thChooseButton.setVisible(true);
                    }
                }
            }
        }

    }
}
