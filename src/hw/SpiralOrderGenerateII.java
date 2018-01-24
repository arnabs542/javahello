package hw;

import utils.ConsolePrinter;

/**
 *
 * Recursion
 Spiral Order Generate II

 Generate an M * N 2D array in spiral order clock-wise starting from the top left corner,
 using the numbers of 1, 2, 3, …, M * N in increasing order.

 Assumptions

 M >= 0, N >= 0
 Examples

 M = 3, N = 4, the generated matrix is

 { {1,  2,  3,  4}

 {10, 11, 12, 5},

 {9,  8,  7,  6} }

 */

public class SpiralOrderGenerateII {

    public int[][] spiralGenerate(int m, int n) {
        // Assumptions: M >= 0, N >= 0
        int total = m * n;
        int[][] matrix = new int[m][n];
        int left = 0, right = n - 1;
        int up = 0, down = m - 1;
        int num = 1;
        while (left < right && up < down) {  // note!!! remember the overlapping points!!!
            for (int i = left; i < right; i++) {
                matrix[up][i] = num++;
            }
            for (int i = up; i < down; i++) {
                matrix[i][right] = num++;
            }
            for (int i = right; i > left; i--) {
                matrix[down][i] = num++;
            }
            for (int i = down; i > up; i--) {
                matrix[i][left] = num++;
            }
            left++;
            right--;
            up++;
            down--;
        }
//        if (num >= total) {
//        		return matrix;
//        }
        // !!! before it's only these two if after loop, wrong for case m = 1, n = 1 !!!, wrong out put as [[2]]
        // since no limitation on 
        // modify: either 1) add the num >= total check  to return or 2) chang sinle row / column to if else relationship
        if (up == down) {
            // possible single row
            for (int i = left; i <= right; i++) {
                matrix[up][i] = num++;
            }
        } else if (left == right) {
            // possible single column
            for (int i = up; i <= down; i++) {
                matrix[i][left] = num++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        SpiralOrderGenerateII sol = new SpiralOrderGenerateII();
        int m = 3, n = 2;
        int[][] result = sol.spiralGenerate(m, n);
        ConsolePrinter.printIntMatrix(result);
    }
}


