public class Main {
    public static void main(String[] args) {
        System.out.println(solution(1));

    }

    static public int solution(int N) {
        int newN=N;
        Integer value=0,temp2=null,temp1;
        while (true){
            if(newN==0){
                //if(value<temp2-1)value=temp2;
                return value;
            }
            if(newN==1){
                if(temp2==null)return value;
                if(value<(temp2-1))value=temp2-1;
                return value;
            }

            if(temp2==null){
                Double db=(Math.log(newN)/Math.log(2));
                temp1=db.intValue();
                newN-=Math.pow(2,temp1);
                temp2=temp1;
            }
            if(temp2!=null&&newN>1){
                Double db=(Math.log(newN)/Math.log(2));
                temp1=db.intValue();
                newN-=Math.pow(2,temp1);
                if(value<temp2-temp1)value=temp2-temp1-1;
                temp2=temp1;
            }
        }
    }

}
