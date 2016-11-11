package com.CGrepActors;

import akka.actor.ActorRef;

/**
 * Each file (or the standard input if no files are given) will be scanned by a ScanActor
 * for occurrences of the pattern, using one actor per file. A ScanActor expects to receive
 * exactly one immutable Configure message containing (a) a String with the name of the
 * file to scan (or null for the standard input), and (b) an ActorRef to a CollectionActor,
 * which collects and prints scan results
 */
public class ScanActor extends Thread{
    private final Configure message;
    private final ActorRef ref;

    public ScanActor(Configure message, ActorRef ref){
        this.message = message;
        this.ref = ref;
    }

    @Override
    public void run() {
        //TODO: scan the file for matches and create Found objects
    }
}
