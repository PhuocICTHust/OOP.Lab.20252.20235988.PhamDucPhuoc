package Lab01;

import java.util.Scanner;

public class solveeq226 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.First-degree equation (ax + b = 0)");
        System.out.println("2.First-degree equations (linear system) with two variables");
        System.out.println("3.Second-degree equation with one variable  (ax^2 + bx + c = 0)");
        System.out.print("Please chóose 1,2 or 3: ");

        int chon = sc.nextInt();

        if (chon == 1) {
            System.out.print("Enter a: ");
            double a = sc.nextDouble();
            System.out.print("Enter b: ");
            double b = sc.nextDouble();

            if (a == 0) {
                if (b == 0) {
                    System.out.println("Infinite solution");
                } else {
                    System.out.println("No solution");
                }
            } else {
                double x = -b / a;
                System.out.println("The solution x = " + x);
            }

        } else if (chon == 2) {
            System.out.println("Linear system: a11*x1 + a12*x2 = b1 and a21*x1 + a22*x2 = b2");
            System.out.print("Enter a11: "); double a11 = sc.nextDouble();
            System.out.print("Enter a12: "); double a12 = sc.nextDouble();
            System.out.print("Enter b1: ");  double b1 = sc.nextDouble();
            System.out.print("Enter a21: "); double a21 = sc.nextDouble();
            System.out.print("Enter a22: "); double a22 = sc.nextDouble();
            System.out.print("Enter b2: ");  double b2 = sc.nextDouble();

            double D = a11 * a22 - a21 * a12;
            double D1 = b1 * a22 - b2 * a12;
            double D2 = a11 * b2 - a21 * b1;

            if (D == 0) {
                if (D1 == 0 && D2 == 0) {
                    System.out.println("Infinitely many solutions");
                } else {
                    System.out.println("No solution");
                }
            } else {
                double x1 = D1 / D;
                double x2 = D2 / D;
                System.out.println("Solution for the linear system: x1 = " + x1 + ", x2 = " + x2);
            }

        } else if (chon == 3) {
            System.out.print("Enter a: "); double a = sc.nextDouble();
            System.out.print("Enter b: "); double b = sc.nextDouble();
            System.out.print("Enter c: "); double c = sc.nextDouble();

            if (a == 0) {
                if (b == 0) {
                    if (c == 0) {
                        System.out.println("Infinitely many solutions");
                    } else {
                        System.out.println("No solution");
                    }
                } else {
                    System.out.println("The solution x = " + (-c / b));
                }
            } else {
                double delta = b * b - 4 * a * c;

                if (delta < 0) {
                    System.out.println("No solution");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    System.out.println("The equation has double root x = " + x);
                } else {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    System.out.println("The equation has 2 distinct roots:");
                    System.out.println("x1 = " + x1);
                    System.out.println("x2 = " + x2);
                }
            }
        } else {
            System.out.println("Can only choose 1,2 or 3");
        }

        sc.close();
    }
}
