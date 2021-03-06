package com.sky.thread.atomic;

public class TestAtomic {
    private static int n = 0;


    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        while (j < 1000) {
            n = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        n++;
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        n++;
                    }
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("n的最终值：" + n);
            j++;
        }

    }
}
