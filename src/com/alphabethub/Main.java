package com.alphabethub;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while(scanner.hasNextLine()){
            str = scanner.nextLine();
            String[] s = str.split(" ");
            for (String s1 : s) {
                System.out.println(s1);
            }
        }

    }
}
