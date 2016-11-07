package com.alex.deminer.controller;

import com.alex.deminer.view.PopUpLose;
import com.alex.deminer.view.PopUpWin;

public class Manager {

    public static void end() {
        new PopUpLose();
    }

    public static void win() {
        new PopUpWin();
    }
}
