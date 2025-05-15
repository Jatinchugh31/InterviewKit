package src.dataStructure.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//You are given a set of N jobs where each job comes with a deadline and profit. The profit can only be earned upon completing the job within its deadline. Find the number of jobs done and the maximum profit that can be obtained. Each job takes a single unit of time and only one job can be performed at a time.
public class JobScheduling {
    public  static  void main(String[] args) {
        Job[] arr = new Job[4];
        arr[0] = new Job(1, 4, 20);
        arr[1] = new Job(2, 1, 10);
        arr[2] = new Job(3, 2, 40);
        arr[3] = new Job(4, 2, 30);

        int maxDays = Arrays.stream(arr).max(Comparator.comparing(job -> job.deadLine)).get().deadLine;
        int[] res = new int[maxDays];
        Arrays.fill(res, -1);
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparing(job -> -job.bonus));
        pq.addAll(Arrays.asList(arr));
        int result=0;
        int daysLeft = maxDays-1;
        while (!pq.isEmpty()) {
            Job job = pq.poll();
            int high  = job.deadLine-1;
            while (high >=0) {
                if(res[high] == -1) {
                    res[high] = job.id;
                    result += job.bonus;
                    daysLeft--;
                    break;
                }
                high--;
            }
            if(daysLeft == 0) {
                break;
            }
        }
        System.out.println(result);


    }


}

class Job {
    int id;
    int deadLine;
    int bonus;
    public Job(int id, int deadLine, int bonus) {
        this.id = id;
        this.deadLine = deadLine;
        this.bonus = bonus;
    }
}