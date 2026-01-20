package com.wip;



public class MainClass {

    public int add(int... number) {
        int result = 0;
        for (int i : number) {
            result = result + i;
        }
        return result;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Java within Kubernetes K*****8 Jenkins ADDED-- Maven Project!!!");
        System.out.println("Application is running inside Kubernetes...");

        // Keep container alive forever
        while (true) {
            Thread.sleep(10000);
        }
    }
}

