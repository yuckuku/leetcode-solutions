/**
 * Created by Administrator on 2017/10/13.
 */
public class BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits(int n) {

        if(n==0||n==1){
            return true;
        }

        int preBit =n%2;
        n=n/2;
        int thisBit =0;
        while(n!=0){
            thisBit = n%2;
            if(preBit == thisBit ){
                return false;
            }
            preBit =n%2;
            n=n/2;
        }
        return true;
    }
}
