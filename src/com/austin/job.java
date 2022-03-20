package com.austin;

public class job {
    private int weight = 1;     // Value of job
    private int start  = 0;     // Start time
    private int end    = 0;     // End time

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
}
