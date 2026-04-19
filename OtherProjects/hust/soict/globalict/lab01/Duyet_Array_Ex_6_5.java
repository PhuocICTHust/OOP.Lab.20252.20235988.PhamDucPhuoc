package hust.soict.globalict.lab01;

import java.util.Arrays;
import java.util.Scanner;

public class Duyet_Array_Ex_6_5 {
    public static void main(String[] args) {
        Scanner p = new Scanner(System.in);
        System.out.print("Nhap so phan tu: ");
        int n = p.nextInt();
        double[] a = new double[n];
        double t = (double)0.0F;

        for(int i = 0; i < n; ++i) {
            a[i] = p.nextDouble();
            t += a[i];
        }

        Arrays.sort(a);
        System.out.println("Ket qua: " + Arrays.toString(a));
        System.out.println("Tong: " + t + ", Trung binh: " + t / (double)n);
    }
}