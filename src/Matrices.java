/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author klaus
 */
public class Matrices {

    static int multiplyCount = 0;

    public static void printMatrix(int[][] matrix) {
        int dimension = matrix.length;
        for (int[] ints : matrix) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        multiplyCount++;
        int dimension = A.length;
        int[][] result = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) // row in A
        {
            for (int j = 0; j < dimension; j++) {  // column in B
                result[i][j] = 0;
                for (int k = 0; k < dimension; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] slowPower(int[][] matrix, int exponent) {
        int dimension = matrix.length;
        int[][] result = new int[dimension][dimension];
        // copy matrix to result
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, dimension);
        }
        for (int i = 1; i < exponent; i++) {
            result = multiply(result, matrix);
        }
        return result;
    }

    public static int[][] fastPower(int[][] matrix, int exponent) {
        if (exponent == 1)
        {
            return matrix;
        }
        int[][] result_half = fastPower(matrix, exponent / 2);
        // Then square it 
        int[][] result_even = multiply(result_half, result_half);
        if (exponent % 2 == 0) {
            return result_even;
        }
        return multiply(result_even, matrix);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {3, 4}};
        int exponent = 1000000;

        System.out.println();
        System.out.println("Using slowPower, with exponent: " + exponent);
        System.out.println();

        multiplyCount = 0;
        long start = System.currentTimeMillis();
        printMatrix(slowPower(matrix, exponent));
        long middle = System.currentTimeMillis();

        System.out.println();
        System.out.println("Used " + multiplyCount + " multiplications, " + (middle - start) + " milliseconds");
        System.out.println();

        System.out.println();
        System.out.println("Using fastPower, with exponent: " + exponent);
        System.out.println();

        multiplyCount = 0;
        printMatrix(fastPower(matrix, exponent));
        long end = System.currentTimeMillis();

        System.out.println();
        System.out.println("Used " + multiplyCount + " multiplications, " + (end - middle) + " milliseconds");
        System.out.println();

    }

}
