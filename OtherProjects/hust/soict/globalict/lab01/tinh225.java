package hust.soict.globalict.lab01;

import java.util.Scanner;

public class tinh225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("First input: ");
        String strNum1 = sc.nextLine();

        System.out.print("Second input: ");
        String strNum2 = sc.nextLine();

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        System.out.println("Sum = " + (num1 + num2));
        System.out.println("Difference = " + (num1 - num2));
        System.out.println("Product = " + (num1 * num2));

        if (num2 == 0) {
            System.out.println("Can't divide by zero");
        } else {
            System.out.println("Quotient = " + (num1 / num2));
        }

        sc.close();
    }
}
