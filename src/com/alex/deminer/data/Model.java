package com.alex.deminer.data;

import java.util.Random;

public class Model {
    public static int r = 0;  // если =0 то размер маленький, если =1 то средний
    public static int count = 0; //это сколько бомб на поле
    public static int falseCount = 0;
    public static int[] size = {10, 16};
    static int[][] field = new int[size[r]][size[r]];

    static public void fieldInit() {
        Random random = new Random();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                int tmp = random.nextInt(100);
                if (tmp > 90) {
                    field[i][j] = 9; // 9 - это  поле с миной
                    count++;
                    System.out.println(i + " " + j);
                } else field[i][j] = 0; // 0 - это поле без бомбы
            }
        }
    }

    static public void fieldReCount() {
        int tmpCount = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] != 9) {
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x < 0 || x >= field.length || y < 0 || y >= field.length) {
//                            улыбаемся и машем, т.к. индекс вне диапозона
                            } else {
                                if (field[x][y] == 9) {
                                    tmpCount++;
                                }
                            }
                        }
                    }
                    field[i][j] = tmpCount;
                    tmpCount = 0;
                }
            }
        }
//
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field.length; j++) {
//                System.out.print(field[i][j]);
//            }
//            System.out.println();
//        }

    }

    static public int pointValue(int a, int b) {
        int pv = field[a][b];
        return pv;
    }

    static public void setValue(int a, int b){
        field[a][b] = 33; //поле, открытое игроком, как свободное от мин
    }

}
