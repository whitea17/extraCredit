package com.austin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // master list of all jobs
        ArrayList<job> allJobs = new ArrayList<job>();
        int[] calculatedPossibilities;

        if(args.length != 1){
            System.out.println("Please specify a file in cmd arguments. Exiting...");
            System.exit(1);
        }

        try {
            // Set up file object to read from

            File inputData = new File(args[0]); // Get file name from cmd args
            Scanner readLines = new Scanner(inputData); // setup for reading indiv. lines

            // Read every line
            while (readLines.hasNextLine()) {
                parseValidateAndAddToList(allJobs, readLines.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file. Exiting....");
            e.printStackTrace();
        }
        calculatedPossibilities = new int[allJobs.size()]; // init to size of allJobs

        // Set all elements to -1 to symbolize them being "empty"
        for(int i = 0; i < calculatedPossibilities.length; i++){
            calculatedPossibilities[i] = -1;
        }


        // Hold value of last best job
        int lastJob = ComputeOpt(allJobs, calculatedPossibilities, allJobs.size() - 1);

        // Add 1 for human-readable output. Shift starting number from 0 to 1
        System.out.println(lastJob + 1);

        //
        // Calculate total weight of best selected schedule
        //
        int totalWeight = 0;
        int i = lastJob;

        while(i != 0){
            totalWeight += allJobs.get(i).getWeight();
            i = allJobs.get(i).getNextBestJob();
        }

        if(i == 0){
            totalWeight += allJobs.get(i).getWeight();
        }


        //
        // End of calculating weight
        //

        // Print out total weight
        System.out.println(totalWeight);

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
    public static int ComputeOpt(ArrayList<job> masterList, int[] calculatedPossibilities, int j){
        // If job is zero, return 0. Base case
        if(j <= 0){
            return 0;

        }else if(calculatedPossibilities[j] != -1){
            return calculatedPossibilities[j];  // Calculated value already exists, return it

        }else{
            // selected current job
            job current = masterList.get(j);

            // Return either j or j-1, which ever has the largest value. Uses recursion.
            int retVal = max(current.getWeight() + ComputeOpt(masterList, calculatedPossibilities, p(masterList, j)), ComputeOpt(masterList, calculatedPossibilities, j-1));
            calculatedPossibilities[j] = retVal;    // Save calculated value for future use to avoid recalculations.

            return retVal;  // return value
        }

    }

    // Parse and validate each line and add it to master list of jobs. If parsing or validation fails, exit program.
    public static void parseValidateAndAddToList(ArrayList<job> masterList, String lineToParse){
        String[] items = lineToParse.split(","); // Break line up via commas

        // Make sure there are three sections
        if(items.length != 3){
            // Don't fail on blank lines
            if(lineToParse.isBlank()){
                return;
            }

            // Failed to parse line
            System.out.println("Failed to parse: " + lineToParse);
            System.out.println("Exiting...");
            System.exit(1);
        }

        // Wraps conversion of string to numbers in try/catch. If it isnt a number the program will exit with fail
        try {
            // Convert strings to their respective numbers
            int w = Integer.parseInt(items[2]); // Weight
            int s = Integer.parseInt(items[0]); // Start
            int e = Integer.parseInt(items[1]); // End

            job newJob = new job(w, s, e);  // Create new job
            masterList.add(newJob); // Add new job to list of jobs

        }catch (Exception NumberFormatException){
            // Failed to parse line
            System.out.println("Failed to parse: " + lineToParse);
            System.out.println("Exiting...");
            System.exit(1);
        }

    }

    // Returns largest of two values, if matching secondVal is returned
    public static int max(int firstVal, int secondVal){
        if(firstVal > secondVal){
            return firstVal;
        }
        return secondVal;
    }

}
