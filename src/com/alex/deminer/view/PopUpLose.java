package com.alex.deminer.view;

import com.alex.deminer.data.Model;
import javax.swing.*;
import static com.alex.deminer.Main.gameWindow;

public class PopUpLose extends JFrame {

    JButton button;

    public PopUpLose() {
        int a;
        a = JOptionPane.showConfirmDialog(button, "Вы проиграли. Хотите еще раз сыграть?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (a == 1) {
            System.exit(0);
        } else {
            Model.count = 0;
            Model.falseCount = 0;
            Model.fieldInit();
            Model.fieldReCount();
            gameWindow.setVisible(false);
            gameWindow = new View();
            gameWindow.setVisible(true);
        }
    }
}
