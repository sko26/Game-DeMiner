package com.alex.deminer.view;

import com.alex.deminer.data.*;
import com.alex.deminer.controller.Manager;
import com.alex.deminer.data.Stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static com.alex.deminer.data.Model.r;

public class View extends JFrame {
    private JButton[][] button = new JButton[10][10];

    public View() {
        setSize(500, 560);  //делаем окно
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Игра \"Минёр\"");
        setLayout(new GridLayout(10, 10));

        JMenuBar menuBar = new JMenuBar();   //создаем менюшку
        JMenu myMenu = new JMenu("Игра");
        JMenuItem txtMenu1 = new JMenuItem("Новая игра");
        myMenu.add(txtMenu1);
        myMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Выход");
        myMenu.add(exitItem);
        menuBar.add(myMenu);
        setJMenuBar(menuBar);
        exitItem.addActionListener(new ExitAction());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                button[i][j] = new JButton();
                add(button[i][j]);
                button[i][j].addMouseListener(new OnClick(i, j));
            }
        }
        setLocationRelativeTo(null); //ставим окно по центру
    }


    private class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class OnClick implements MouseListener {
        int a;
        int b;

        OnClick(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void mouseClicked(MouseEvent e) {
            JButton btnTmp = (JButton) e.getSource();
            int tmp = Model.pointValue(a, b);

            if (SwingUtilities.isRightMouseButton(e)) {
//              Нажата правая кнопка
                if (Objects.equals(btnTmp.getText(), "B")) {
                    bombCheckAndCount(false, tmp);
                } else {
                    bombCheckAndCount(true, tmp);
                }
            } else {
//              Нажата левая кнопка
                if (tmp == 9) {
                    button[a][b].setBackground(Color.red);
                    Manager.end();    //Проиграл. Выход или перезапуск
                } else if (tmp == 0) {
                    mapStack.put(n, new Stack(a, b));
                    reDrawOnLeftClick();
                } else {
                    btnTmp.setText(String.valueOf(tmp));
                    btnTmp.setEnabled(false);
                }
            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        void bombCheckAndCount(boolean bmb, int realValue) {
            if (bmb && realValue == 9) {
                button[a][b].setText("B");
                Model.count--;
                System.out.println("count=" + Model.count);
            } else if (bmb && realValue != 9) {
                button[a][b].setText("B");
                Model.falseCount++;
                System.out.println("falsecount=" + Model.falseCount);
            } else if (!bmb && realValue != 9) {
                button[a][b].setText(" ");
                Model.falseCount--;
                System.out.println("falsecount=" + Model.falseCount);
            } else if (!bmb && realValue == 9) {
                button[a][b].setText(" ");
                Model.count++;
                System.out.println("count=" + Model.count);
            }
            if (Model.count == 0 && Model.falseCount == 0) {
                Manager.win();  //победа
            }
        }
    }

    private Map<Integer, Stack> mapStack = new HashMap<>();
    private int n = 1;

    private void reDrawOnLeftClick() {
        Stack tmpSt = mapStack.get(n--);
        int i = tmpSt.getX();
        int j = tmpSt.getY();
        Model.setValue(i, j);
        button[i][j].setText("");
        button[i][j].setEnabled(false);
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                //todo переписать IF наоборот, чтоб не был пустой
                if (x < 0 || x >= Model.size[r] || y < 0 || y >= Model.size[r]) {
//                            улыбаемся и машем, т.к. индекс вне диапозона
                } else {
                    if (Model.pointValue(x, y) == 0) {
                        mapStack.put(++n, new Stack(x, y));
                    } else if (Model.pointValue(x, y) != 33 && Model.pointValue(x, y) != 0 && Model.pointValue(x, y) != 9) {
                        button[x][y].setText(String.valueOf(Model.pointValue(x, y)));
                        button[x][y].setEnabled(false);
                    }
                }
            }
        }
        if (n == 0) {
            //// TODO: здесь была ошибка 
            n = 1;
//            return;
        } else {
            reDrawOnLeftClick();
        }
    }
}
