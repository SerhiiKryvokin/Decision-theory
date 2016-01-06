package packing;

import packing.packer.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final int SIZE = 20;
    private static final int CARRYING = 100;
    private static final String INPUT_DATA_FILE_NAME = "inputDataPacking";
    Scanner in;
    PrintWriter out;

    private void run() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(INPUT_DATA_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fileReader);
        in = new Scanner(br);
        out = new PrintWriter(System.out);

        int[] a1 = readIntArr(SIZE);
        int[] a2 = readIntArr(SIZE);
        int[] a3 = readIntArr(SIZE);
        int[] a4 = new int[SIZE * 3];
        System.arraycopy(a1, 0, a4, 0, SIZE);
        System.arraycopy(a2, 0, a4, SIZE, SIZE);
        System.arraycopy(a3, 0, a4, SIZE * 2, SIZE);

        List<int[]> arrays = new ArrayList<int[]>() {
            {
                add(a1);
                add(a2);
                add(a3);
                add(a4);
            }
        };

        for (int i = 0; i < arrays.size(); i++) {
            int sum = 0;
            int mMin = 0;
            int[] arr = arrays.get(i);
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j];
            }
            mMin = sum / CARRYING;
            System.out.println(i + 1 + " array needs at least " + mMin + " containers");
        }
        System.out.println();

        List<Packer> packers = new ArrayList<Packer>() {
            {
                add(new NFAPacker(CARRYING));
                add(new FFAPacker(CARRYING));
                add(new WFAPacker(CARRYING));
                add(new BFAPacker(CARRYING));
            }
        };

        for (int i = 0; i < arrays.size(); i++) {
            out.println(i + 1 + " array:");
            for (int j = 0; j < packers.size(); j++) {
                Packer packer = packers.get(j);
                String packerName = packer.getClass().getSimpleName();
                int containersUsed = packer.pack(arrays.get(i));
                int compares = packer.getComparesCount();

                out.println("Unsorted " + packerName);
                out.println("Containers used: " + containersUsed);
                out.println("Compares performed: " + compares);

                packer = new SortingPacker(packer);
                containersUsed = packer.pack(arrays.get(i));
                compares = packer.getComparesCount();

                out.println("Sorted " + packerName);
                out.println("Containers used: " + containersUsed);
                out.println("Compares performed: " + compares);
            }
            out.println("-------------------------------------------");
        }


        out.close();
    }

    private int[] readIntArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
