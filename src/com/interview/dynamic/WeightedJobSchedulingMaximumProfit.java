package com.interview.dynamic;

import java.util.Arrays;
import java.util.Comparator;

class Job{
    int start;
    int end;
    int profit;
    Job(int start,int end,int profit){
        this.start= start;
        this.end = end;
        this.profit= profit;
    }

    public String toString() {
        return "(start: " + start + ", end: " + end + ", profit: " + profit + ")";
    }
}

class FinishTimeComparator implements Comparator<Job>{

    @Override
    public int compare(Job arg0, Job arg1) {
        if(arg0.end <= arg1.end){
            return -1;
        }else{
            return 1;
        }
    }

}

/**
 * http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
 * Given set of jobs with start and end interval and profit, find out the order to schedule jobs to maximize profit such that
 * jobs in subset do not overlap.
 */
public class WeightedJobSchedulingMaximumProfit {

    /**
     * Sort the jobs by finish time.
     * For every job find the first job which does not overlap with this job
     * and see if this job profit plus profit till last non overlapping job is greater
     * than profit till last job.
     * @param jobs
     * @return
     */
    public int maximum(Job[] jobs){
        int T[] = new int[jobs.length];
        int C[] = new int[jobs.length];

        for (int i = 0;  i < jobs.length; i++) {
            C[i] = -1;
        }

        FinishTimeComparator comparator = new FinishTimeComparator();
        Arrays.sort(jobs, comparator);

        T[0] = jobs[0].profit;
        for(int i=1; i < jobs.length; i++){
            T[i] = Math.max(jobs[i].profit, T[i-1]); // Profit by not starting the job
//            C[i] = i - 1;
            for(int j=i-1; j >=0; j--){

                // Note equality - if finish and start times match - we do not consider them to be overlapping
                if(jobs[j].end <= jobs[i].start){
//                    T[i] = Math.max(T[i], jobs[i].profit + T[j]);
                    if (jobs[i].profit + T[j] > T[i]) {
                        T[i] = jobs[i].profit + T[j];
                        C[i] = j;
                    }
                    break;
                }
            }
        }

        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < T.length; i++) {
            if (maxVal < T[i]) {
                maxVal = T[i];
                maxIndex = i;
            }
        }

        for (int i = maxIndex; i >= 0;) {
            if (T[i] == T[i] -1) {
                i -= 1;
                continue;
            }

            if (i == -1) {
                break;
            }
            System.out.println(jobs[i]);
            i = C[i];
        }
        return maxVal;
    }

    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,6);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
        System.out.println(mp.maximum(jobs));
    }
}
