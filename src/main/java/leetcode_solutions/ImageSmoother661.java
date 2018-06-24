package leetcode_solutions;

/**
 * Created by Administrator on 2017/11/29.
 */
public class ImageSmoother661 {

    //time limit exceeded
    public int[][] imageSmoother(int[][] M) {
        if (M == null)
            return null;
        int[][] re = new int[M.length][M[0].length];

        for (int i = 0; i < M.length; i++)
            for (int j = 0; j < M[0].length; j++) {
                System.out.println("i and j "+i+" "+j);
                int sum = 0;
                int denominator = 0;
                for (int m = i - 1; m < i + 2; m++) {
                    for (int n = j - 1; n < j + 2; n++) {
                        if (m < 0 || m >= M.length || n < 0 || n >= M[0].length)
                            continue;
                        else {
                            denominator++;
                            sum += M[m][n];
                        }
                    }
                }
                if(denominator==0)
                    System.out.println(i+" "+j);
                System.out.println(sum);

                re[i][j] = sum / denominator;
            }
        return re;
    }

    //use 00000s bytes
    public int[][] imageSmoother1(int[][] M) {
        int m = M.length, n = M[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int I = i - 1; I < i + 2; I++)
                    for (int J = j - 1; J < j + 2; J++)
                        if (I >= 0 && I < m && J >= 0 && J < n)
                            M[i][j] += 256 + M[I][J] % 256 * 4096;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                M[i][j] = M[i][j] / 4096 / (M[i][j] / 256 % 16);
        return M;
    }
}
