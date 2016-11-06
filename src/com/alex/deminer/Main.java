package com.alex.deminer;

import com.alex.deminer.view.View;
import com.alex.deminer.data.Model;
import javax.swing.*;

public class Main {
    public static JFrame gameWindow;
    public static void main(String[] args) {
        Model.fieldInit();
        Model.fieldReCount();
        gameWindow = new View();
        gameWindow.setVisible(true);
    }
}
