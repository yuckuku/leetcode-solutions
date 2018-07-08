package leetcode_solutions.arrays;

public class ToeplitzMatrix766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (null == matrix || matrix.length < 1) {
            return true;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int k = 0; k < m; k++) {
            int temp = matrix[k][0];
            int x = k, y = 0;
            while (x < m - 1 && y < n - 1) {
                x++;
                y++;
                if (temp != matrix[x][y]) {
                    return false;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            int temp = matrix[0][k];
            int x = 0, y = k;
            while (x < m - 1 && y < n - 1) {
                x++;
                y++;
                if (temp != matrix[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix0(int[][] matrix) {
        int len = matrix.length;
        int len1 = matrix[0].length;


        for(int i=0;i<len-1;i++){
            for(int j=0;j<len1-1;j++){
                if(matrix[i][j]!=matrix[i+1][j+1]){
                    return false;
                }
            }
        }
        return true;

    }
}
