package OSLab;

import java.util.*;

public class Assignment1 {
    public static void FirstComeFirstServe( ArrayList<Integer> ArrivalTime,ArrayList<Integer> BurstTime) {
        System.out.println("No of Process are : " + ArrivalTime.size());
        ArrayList<Integer> CompletionTime = new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1));
        ArrayList<Integer> WaitingTime = new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1));
        ArrayList<Integer> TurnAroundTime = new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1));
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<ArrivalTime.size();i++){
            map.put(i,ArrivalTime.get(i));
        }
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(map.entrySet());
        Collections.sort(
                list,
                (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        ArrayList<Integer> Order=new ArrayList<>();
        for (Map.Entry<Integer, Integer> aa : list) {
            Order.add(aa.getKey());
        }

        for(int i=0;i<ArrivalTime.size();i++){
            if(i-1<0){
                CompletionTime.set(Order.get(i),+ BurstTime.get(Order.get(i)));
            }else {
                CompletionTime.set(Order.get(i), CompletionTime.get(Order.get(i - 1)) + BurstTime.get(Order.get(i)));
            }
        }
        for (int i = 0; i < BurstTime.size(); i++) {
            TurnAroundTime.set(Order.get(i), CompletionTime.get(Order.get(i)) - ArrivalTime.get(Order.get(i)));
            WaitingTime.set(Order.get(i), TurnAroundTime.get(Order.get(i)) - BurstTime.get(Order.get(i)));
        }
        int i=0;
        int j=0;
        System.out.println("Time          Process         Arrival Time            Burst Time         Completion Time          TurnAround Time          Waiting Time");
        while(i<=ArrivalTime.get(Order.getLast())) {
            if(ArrivalTime.get(Order.get(j))==i){
                System.out.println(i+"\t\t\t\tP" + Order.get(j) + "\t\t\t\t" + ArrivalTime.get(Order.get(j)) + "\t\t\t\t\t\t" + BurstTime.get(Order.get(j)) + "\t\t\t\t\t" + CompletionTime.get(Order.get(j)) + "\t\t\t\t\t\t" + TurnAroundTime.get(Order.get(j)) + "\t\t\t\t\t\t" + WaitingTime.get(Order.get(j)));
                if((j+1)<ArrivalTime.size()&&ArrivalTime.get(Order.get(j))!=ArrivalTime.get(Order.get(j+1))){
                    i++;
                }
                if(j+1==ArrivalTime.size()){
                    break;
                }
                j++;
            }
            else{
                System.out.println(i+" ");
                i++;

            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> ArrivalTime=new ArrayList<>(Arrays.asList(1,4,0,1,1,2));
        ArrayList<Integer> BurstTime=new ArrayList<>(Arrays.asList(3,2,9,2,4,3));
        FirstComeFirstServe(ArrivalTime,BurstTime);
    }
}
