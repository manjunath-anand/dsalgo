package com.dl.array;

public class Test {
    static Foo o = new Foo(0);
    static class Foo {
        private int i = 0;
        Foo(int i) {
            this.i = i;
        }
        public void addOnce() {
            this.i++;
        }
        public String toString() {
            return String.valueOf(i);
        }
    }
    public static void main(String args[]) {
        test1();
        try {Thread.sleep(5000);} catch (Exception e) {}
        test2();
    }
    public static void test1() {
        Runnable r = new Runnable() {
            public void run() {
                synchronized (o) {
                    System.out.println("1------>"+o);
                    o = new Foo(1);
                    try {Thread.sleep(10000);} catch (Exception e) {}
                    System.out.println("1------>"+o);
                }
            }
        };
        new Thread(r).start();
       // new Thread(r).start();
    }
    public static void test2() {
        Runnable r = new Runnable() {
            public void run() {
                synchronized (o) {
                    System.out.println("2------>"+o);
                    o.addOnce();
                    try {Thread.sleep(3000);} catch (Exception e) {}
                    System.out.println("2------>"+o);
                }
            }
        };
        new Thread(r).start();
        //new Thread(r).start();
    }
}
