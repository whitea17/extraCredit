# Extra Credit Project

### A few words:

I chose the option to implement a dynamic programming weighted interval algorithm. I decided to use Java for my project.

## Introduction:

This program takes a set of jobs with a weight or value, a start time, and a end time. It will produce a optimal schedule where the most value or weight can be acquired without overlapping jobs. The input is in the format of a csv file. Each line is as follows: "start time, end time, weight". Each section is a number in the form of a integer.

## Usage:

See above or "input.csv" for the format that should be used for the input file.

### Requirments:

A Linux computer with a recent Java jdk installed.

### To Build:

From the root of the repo, run: `javac src/com/austin/*.java `

This will create the needed class files to run the program.

### To Run:

From the root of the repo, after following the build instructions, run: `java -cp ./src com.austin.Main input.csv`

**Note:** In the above command, the input file is given as a argument. This is crucial to the operation of this program. Without a path to a csv file being given, the program will exit with a error message. Please replace "input.csv", with a absolute or relative path to your input file. Be aware the relative context is the root of the repo.

## Reflection:

**Youtube video**: [https://youtu.be/gY_vPo-aIhk](https://youtu.be/gY_vPo-aIhk)

I largest struggle was understanding the symbols in the textbook and power point slides. For example, from your power point slides:

![exampleDiff](/home/mountain/IdeaProjects/extraCredit/screenshots/exampleDiff.png)

Once I was able to understand this part, the rest was much easier.