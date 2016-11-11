package com.CGrepActors;

import akka.actor.ActorRef;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by John King on 11-Nov-16.
 */
public class CGrep {

    // Thread Pool Size
    private static final int THREAD_POOL_MAX = 3;
    private static ExecutorService executor;


    public static void main(String args[]){
        int numOfFiles = 3;
        ActorRef[] ref = new ActorRef[2];


        int argLength = args.length;

        if(argLength == 0){
            System.err.println("Missing Search Pattern"); // exit program if no search patter is given
            System.exit(1);
        }

        executor = Executors.newFixedThreadPool(THREAD_POOL_MAX);

        String pattern = args[0];


        if (argLength > 1) { // case where all arguments are passed through program args
            countFiles(args,false);

        } else if (argLength == 1) { // case where the user supplies their own filepath's via keyboard
            System.out.print("Please enter filepath(s) separated by spaces: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] parsed = input.split(" "); // split on all spaces
            countFiles(parsed,true);
        }

        executor.shutdown();
    }

    /**
     * Takes an array of filepaths and the pattern to search for, opens the files
     * and performs the "Main" loop of execution for our program. if manualInput is
     * true, then this tells method that the array of filepath's are coming from the
     * keyboard vs from the program arguments when it's false.
     * @param input
     * @param manualInput tells method where the filepath's String away is coming from.
     */
    public static int countFiles(String[] input, boolean manualInput){
        int length = input.length;
        int count = 0;
        int i = manualInput ? 0 : 1 ; // skips the first element in the array if taking input from program arguments

        for(;i<length;i++){

            String filepath = input[i];
            File file = new File(filepath);

            if(file.isFile()){
                count++;
            }
            else{
                System.err.println("'" + filepath + "'" + " not found");
            }

        }

        System.out.println("filecount: " + count);

        return count;

    }
}
