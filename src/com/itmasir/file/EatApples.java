package com.itmasir.file;

public class EatApples {

    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        // 一万次循环
        for (int i = 0; i < 1000000; i++) {
            while (true) {
                // 女孩子
                if (Math.random() > 0.5) {// 正面
                    a++;
                    continue;
                } else {
                    b++;
                    break;
                }
            }
        }
        System.out.println("A:" + a + "次,B:" + b + "次");
    }

}
