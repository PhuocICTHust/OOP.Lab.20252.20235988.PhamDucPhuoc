package Lab01;

import java.util.Scanner;

public class Ngay_thang_Ex6_4 {
    public static void main(String[] args) {
        Scanner phuoc = new Scanner(System.in);
        String[] t1 = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] t2 = {"Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
        String[] t3 = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] t4 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        while (true) {
            System.out.print("Hay nhap thang va nam: ");
            String s = phuoc.next();
            if (!phuoc.hasNextInt()) { phuoc.next(); continue; }
            int nam = phuoc.nextInt();
            if (nam < 0) continue;
            int thang = -1;
            for (int i = 0; i < 12; i++) {
                if (s.equals(t1[i]) || s.equals(t2[i]) || s.equals(t3[i]) || s.equals(t4[i])) {
                    thang = i + 1;
                    break;
                }
            }
            if (thang == -1) continue;
            int d = 31;
            if (thang == 2) {
                if ((nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0)) d = 29;
                else d = 28;
            }
            else if (thang == 4 || thang == 6 || thang == 9 || thang == 11) d = 30;
            System.out.println("So ngay: " + d);
            break;
        }
    }
}