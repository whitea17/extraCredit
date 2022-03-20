package com.austin;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Example data
        job a = new job(3, 0, 3);
        job b = new job(2, 1, 4);
        job c = new job(4, 0, 5);
        job d = new job(1, 3, 6);
        job e = new job(2, 4, 7);
        job g = new job(5, 3, 9);
        job h = new job(2, 5, 10);
        job j = new job(1, 8, 10);

        // master list of all jobs
        ArrayList<job> allJobs = new ArrayList<job>();

        // Add example data
        allJobs.add(a);
        allJobs.add(b);
        allJobs.add(c);
        allJobs.add(d);
        allJobs.add(e);
        allJobs.add(g);
        allJobs.add(h);
        allJobs.add(j);

        // Hold value of last best job
        int tmp = ComputeOpt(allJobs, 6);

        // Add 1 for human-readable output. Shift starting number from 0 to 1
        System.out.println(tmp + 1);

        //
        // Calculate total weight of best selected schedule
        //
        int sum = 0;
        int i = tmp;

        while(i != 0){
            sum += allJobs.get(i).getWeight();
            i = allJobs.get(i).getNextBestJob();
        }

        if(i == 0){
            sum += allJobs.get(i).getWeight();
        }

        //
        // End of calculating weight
        //

        // Print out total weight
        System.out.println(sum);

    }

    // P algo/pseudo code from powerpoint slides implemented into code
    public static int p(ArrayList<job> masterList, int j){
        // Check if next best job hasn't been calculated yet
        if(masterList.get(j).getNextBestJob() == -1){

            // Loop through all jobs preceding job j
            for(int i = j - 1 ; i >= 0; i--){
                job nextPossibleJob = masterList.get(i);

                // If next possible job has end time that is greater than job j's start time
                // return it and make note in it's job.
                if(nextPossibleJob.getEnd() <= masterList.get(j).getStart()){
                    masterList.get(j).setNextBestJob(i);    // Save value for next time
                    return i;
                }
            }
        } // If value has already been calculated, avoid expensive calculation steps and simply return the value
        return masterList.get(j).getNextBestJob();
    }

    //
    // Calculates optimal path/schedule. Algo/pseudo code from power point slides implemented into code
    public static int ComputeOpt(ArrayList<job> masterList, int j){
        // If job is zero, return 0. Base case
        if(j <= 0){
            return 0;
        }
        // selected current job
        job current = masterList.get(j);

        // Return either j or j-1, which ever has the largest value. Uses recursion.
        return max(current.getWeight() + ComputeOpt(masterList, p(masterList, j)), ComputeOpt(masterList, j-1));
    }

    // Returns largest of two values, if matching secondVal is returned
    public static int max(int firstVal, int secondVal){
        if(firstVal > secondVal){
            return firstVal;
        }
        return secondVal;
    }
}
