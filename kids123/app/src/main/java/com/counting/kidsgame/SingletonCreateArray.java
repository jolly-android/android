package com.counting.kidsgame;

public class SingletonCreateArray {
    public static MemoryClass n1;
    public static MemoryClass n10;
    public static MemoryClass n11;
    public static MemoryClass n12;
    public static MemoryClass n13;
    public static MemoryClass n14;
    public static MemoryClass n15;
    public static MemoryClass n16;
    public static MemoryClass n17;
    public static MemoryClass n18;
    public static MemoryClass n19;
    public static MemoryClass n2;
    public static MemoryClass n20;
    public static MemoryClass n3;
    public static MemoryClass n4;
    public static MemoryClass n5;
    public static MemoryClass n6;
    public static MemoryClass n7;
    public static MemoryClass n8;
    public static MemoryClass n9;
    private static volatile SingletonCreateArray singletonCreateArray = new SingletonCreateArray();

    private SingletonCreateArray() {
        createAllObjects();
    }

    private void createAllObjects() {
        createNumbersImages();
    }

    public static SingletonCreateArray getInstance() {
        return singletonCreateArray;
    }

    public void createNumbersImages() {
        n1 = new MemoryClass(1, R.drawable.b1, R.raw.memory_correct, R.raw.n_1);
        n2 = new MemoryClass(2, R.drawable.b2, R.raw.memory_correct, R.raw.n_2);
        n3 = new MemoryClass(3, R.drawable.b3, R.raw.memory_correct, R.raw.n_3);
        n4 = new MemoryClass(4, R.drawable.b4, R.raw.memory_correct, R.raw.n_4);
        n5 = new MemoryClass(5, R.drawable.b5, R.raw.memory_correct, R.raw.n_5);
        n6 = new MemoryClass(6, R.drawable.b6, R.raw.memory_correct, R.raw.n_6);
        n7 = new MemoryClass(7, R.drawable.b7, R.raw.memory_correct, R.raw.n_7);
        n8 = new MemoryClass(8, R.drawable.b8, R.raw.memory_correct, R.raw.n_8);
        n9 = new MemoryClass(9, R.drawable.b9, R.raw.memory_correct, R.raw.n_9);
        n10 = new MemoryClass(10, R.drawable.b10, R.raw.memory_correct, R.raw.n_10);
        n11 = new MemoryClass(11, R.drawable.b11, R.raw.memory_correct, R.raw.n_11);
        n12 = new MemoryClass(12, R.drawable.b12, R.raw.memory_correct, R.raw.n_12);
        n13 = new MemoryClass(13, R.drawable.b13, R.raw.memory_correct, R.raw.n_13);
        n14 = new MemoryClass(14, R.drawable.b14, R.raw.memory_correct, R.raw.n_14);
        n15 = new MemoryClass(15, R.drawable.b15, R.raw.memory_correct, R.raw.n_15);
        n16 = new MemoryClass(16, R.drawable.b16, R.raw.memory_correct, R.raw.n_16);
        n17 = new MemoryClass(17, R.drawable.b17, R.raw.memory_correct, R.raw.n_17);
        n18 = new MemoryClass(18, R.drawable.b18, R.raw.memory_correct, R.raw.n_18);
        n19 = new MemoryClass(19, R.drawable.b19, R.raw.memory_correct, R.raw.n_19);
        n20 = new MemoryClass(20, R.drawable.b20, R.raw.memory_correct, R.raw.n_20);
    }
}
