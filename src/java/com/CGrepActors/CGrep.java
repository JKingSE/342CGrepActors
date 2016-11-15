package com.CGrepActors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by John King on 11-Nov-16.
 */
public class CGrep {

    static private ActorRef [] scanActorRef;
    static private ActorRef collectionActorRef;

    public static void main(String args[]){


        int argLength = args.length;

        if(argLength == 0){
            System.err.println("Missing Search Pattern"); // exit program if no search patter is given
            System.exit(1);
        }


        String pattern = args[0];

        FileCount fileCount;

        int count = countFiles(args, false);

        scanActorRef = new ActorRef[count];

        String[] filesToSearch;

        if (argLength > 1) { // case where all arguments are passed through program args
            fileCount = new FileCount(  count  );
            filesToSearch = Arrays.copyOfRange(args,1, args.length); // copies just the filenames

        } else{ // case where the user supplies their own filepath's via keyboard
            System.out.print("Please enter filepath(s) separated by spaces: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] parsed = input.split(" "); // split on all spaces
            fileCount = new FileCount(  countFiles(parsed,true) );
            filesToSearch = parsed;
        }


        ActorSystem system = ActorSystem.create();
        ActorRef collectionRef = system.actorOf(Props.create(CollectionActor.class));
        ActorRef scanRef = system.actorOf(Props.create(ScanActor.class));

        for(String filename : filesToSearch){
            scanRef.tell(new Configure(filename), ActorRef.noSender());

        }

        system.shutdown();
//
//        collectionActorRef = actorOf(CollectionActor.class);
//        for(int x=0; x<count; x++){
//            scanActorRef[x] = actorOf(ScanActor.class);
//        }
//        collectionActorRef.start();
//        for(int x=0;x<count;x++){
//            scanActorRef[x].start();
//        }
//
//        /*
//        Do Stuff
//         */
//
//        collectionActorRef.stop();
//        for(int x=0;x<count;x++){
//            scanActorRef[x].stop();
//        }
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
