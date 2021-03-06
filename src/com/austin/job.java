package com.austin;

import java.util.Comparator;

public class job implements Comparable<job> {
    private int weight = 1;     // Value of job
    private int start  = 0;     // Start time
    private int end    = 0;     // End time
    private int nextBestJob = -1;

    job(int w, int s, int e){
        this.weight = w;
        this.start = s;
        this.end   = e;
    }

    //
    // Basic getters for members of this
    public int getWeight(){
        return weight;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }
    public int getNextBestJob(){return nextBestJob;};

    // setter
    public void setNextBestJob(int next){nextBestJob = next;}

    public String toString(){
        return "Start: " + start + ", End: " + end + ", Weight: " + weight;
    }

    @Override
    public int compareTo(job jobToCompare){
        int thisJob = this.end;
        int otherJob = jobToCompare.getEnd();

        return thisJob - otherJob;
    }

}
