package com.example.demo.service;

import java.util.Scanner;

public class TestService {

    public static void main(String[] args) {
        String username = "admin";
        Scanner sc = new Scanner(System.in);
        String str =sc.nextLine().toLowerCase();
        String s = sc.nextLine();
        System.out.print(str.length()-str.replaceAll(s,"").length());

    }
}
