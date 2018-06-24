package leetcode_solutions;

public class PoorPigs458 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int minNum=minutesToTest/minutesToDie;
        if(0==minNum){
            return Integer.MAX_VALUE;
        }
        System.out.println(minNum);
        int pigsNum= (int) (Math.log(buckets)/Math.log(2));
//        System.out.println(pigsNum);
        if(pigsNum<=minNum){
            return pigsNum;
        }else if(pigsNum>minNum){
            return pigsNum*2-minNum;
        }
        return 0;
//        int re=pigsNum+minNum-1;
//        if(re<0){
//            return 0;
//        }else{
//            return re;
//        }
    }

    //split
    public int poorPigs0(int buckets, int minutesToDie, int minutesToTest) {
        if(buckets==1){
            return 0;
        }
        int length=minutesToTest/minutesToDie+1;
        int pigNum=1;
        int total=length;
        while(total<buckets){
            total=total*length;
            pigNum++;
        }
        return pigNum;
    }


}
