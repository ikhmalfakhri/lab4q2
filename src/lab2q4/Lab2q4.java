/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2q4;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
public class Lab2q4 {

    static int n = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        getArray();

    }

    public static void getArray() {

        int maxXor = 0;
        int sum = 0;
        int max = 0;
        int k = 0;

        Scanner s = new Scanner(System.in);

        System.out.print("Enter array size: ");
        n = s.nextInt();
        int arr3[][] = new int[n][n];
        int arr4[][] = new int[n][n];
        boolean arrb[][] = new boolean [n][n];
        System.out.println("");

        int[] arr1 = new int[10];
        for (int i = 0; i < n; i++) {
            System.out.printf("Element (a) #%d:", i + 1);
            arr1[i] = s.nextInt();
        }
        System.out.println("");

        int[] arr2 = new int[10];
        for (int i = 0; i < n; i++) {
            System.out.printf("Element (b) #%d:", i + 1);
            arr2[i] = s.nextInt();
        }
        System.out.println("");

        System.out.print("a: ");
        {
            System.out.print("{");
            for (int i = 0; i < n - 1; i++) {
                System.out.print(arr1[i] + ",");
            }
            System.out.println(arr1[n - 1] + "}");
        }

        System.out.print("b: ");
        {
            System.out.print("{");
            for (int i = 0; i < n - 1; i++) {
                System.out.print(arr2[i] + ",");
            }
            System.out.println(arr2[n - 1] + "}");
        }
        System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr3[i][j] = (arr2[i] ^ arr1[j]);
                arr4[i][j] = (arr2[i] ^ arr1[j]);
                if (max < arr3[i][j]) {
                    max = arr3[i][j];
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr3[i][j] = max - arr3[i][j];
            }
        }

        subRow(arr3);
        subCol(arr3);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d ", arr3[i][j]);
            }
            System.out.println("");
        }

        lineMarker(arr3);
        
        if (solveuntil(arr3, 0) == false) {
            System.out.print("FAILED");
        } else{
        getOutput(arr1,arr2,arr3,arr4);
        }
        

    }

    public static void subRow(int[][] arr3) {
        int n = arr3.length;
        int min1 = arr3[0][0];
            for (int i = 0; i < n; i++) {
                min1 = arr3[i][0];
                for (int j = 0; j < n; j++) {
                    if (min1 > arr3[i][j]) {
                        min1 = arr3[i][j];
                    }
                }
                for (int j = 0; j < n; j++) {
                    arr3[i][j] = arr3[i][j] - min1;
                }
            }
    }
    
    public static void subCol(int[][] arr3){
        int n = arr3.length;
        int min1 = arr3[0][0];
        for (int i = 0; i < n; i++) {
                min1 = arr3[0][i];
                for (int j = 0; j < n; j++) {
                    if (min1 > arr3[j][i]) {
                        min1 = arr3[j][i];
                    }
                }
                for (int j = 0; j < n; j++) {
                    arr3[j][i] = arr3[j][i] - min1;
                }
            }
    }

    public static void lineMarker(int[][] arr3) {
        int n = arr3.length;
        int c = 0;
        int lineCount = 0;
        int[] rowMarked = new int[n];
        int[] colMarked = new int[n];
        for (c = 0; c < n; c++) {
            lineCount = lineCover(arr3, rowMarked, colMarked, lineCount);

        }
        if (lineCount < n) {
            addSub(arr3, rowMarked, colMarked);
        }

    }

    public static void addSub(int[][] arr3, int[] rowMarked, int[] colMarked) {
        int n = arr3.length;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (min > arr3[i][j] && rowMarked[i] == 0 && colMarked[j] == 0) {
                    min = arr3[i][j];
                }
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rowMarked[i] == 0) {
                    if (colMarked[j] == 0) {
                        arr3[i][j] = arr3[i][j] - min;
                    }
                }
                if (colMarked[j] == 1) {
                    if (rowMarked[i] == 1) {
                        arr3[i][j] = arr3[i][j] + min;
                    }
                }
            }
        }

        lineMarker(arr3);
    }

    public static int lineCover(int[][] arr3, int[] rowMarked, int[] colMarked, int lineCount) {
        int n = arr3.length;
        int[] zeroCount = new int[n+n]; 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr3[i][j] == 0 && rowMarked[i] == 0 && colMarked[j] == 0) {
                    zeroCount[i]++;
                    zeroCount[j + n]++;
                }
            }
        }

        int rmax = zeroCount[0];
        int cmax = zeroCount[n];
        int r = 0, c = 0;
        //int r1[] = new int[n];
        //int c1[] = new int[n];
        int c1 = 0, r1 = 0;
        for (int i = 0; i < n; i++) {
            if (rmax < zeroCount[i]) {
                rmax = zeroCount[i];
                r = i;
            }
            if (zeroCount[i] == 1) {
                r1++;
            }

            if (cmax < zeroCount[n + i]) {
                cmax = zeroCount[n + i];
                c = i;
            }
            if (zeroCount[n + i] == 1) {
                c1++;
            }

        }
        if (rmax > 0 || cmax > 0) {
            if (rmax == cmax && rmax != 1 && rmax != 2)
            {
                colMarked[c] = 1;
                lineCount++;
                rowMarked[r] = 1;
                lineCount++;
            } else if (rmax > cmax) {
                rowMarked[r] = 1;
                lineCount++;
            } else if (cmax > rmax) {
                colMarked[c] = 1;
                lineCount++;
            } else if (rmax == cmax && (rmax == 1 || rmax == 2)) {
                if (r1 > c1) {
                    colMarked[c] = 1;
                    lineCount++;
                } else {
                    rowMarked[r] = 1;
                   lineCount++;
                }
            } else {
                colMarked[c] = 1;
                lineCount++;
            }
        }
        return lineCount;
    }

    static boolean isSafe(int[][] arr3, int row, int col) {
        int i, j;
        if (arr3[row][col] != 0) {
            return false;
        }

        for (i = 0; i < col; i++) {
            if (arr3[row][i] == -1) {
                return false;
            }
        }

        return true;
    }

    static boolean solveuntil(int[][] arr3, int col) {

        if (col >= arr3.length) {
            return true;
        }

        for (int row = 0; row < arr3.length; row++) {

            if(isSafe(arr3, row, col)){

                arr3[row][col] = -1;

                if (solveuntil(arr3, col + 1) == true) {
                    return true;
                }
                arr3[row][col] = 0;
            }
        }

        return false;
    }

    static void getOutput(int arr1[], int arr2[], int arr3[][], int arr4[][]) {
        int n = arr3.length;
        int arr5[] = new int[10];
        int arr6[] = new int[10];
        int k = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.print(" " + board[i][j]
                //   + " ");
                if (arr3[i][j] == -1) {
                    sum += arr4[i][j];
                    arr5[k] = i;
                    arr6[k] = j;
                    k++;
                }

            }
            //System.out.println();
        }
        for (int i=0;i<n;i++){
            System.out.print("("+arr1[arr5[i]]+"^"+arr2[arr6[i]]+") ");
        }
        System.out.println("");
        System.out.println("max=  "+sum);
    }

}
