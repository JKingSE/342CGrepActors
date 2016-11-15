package com.CGrepActors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.ArrayList;

/***
 * Each file (or the standard input if no files are given) will be scanned by a ScanActor
 * for occurrences of the pattern, using one actor per file. A ScanActor expects to receive
 * exactly one immutable Configure message containing (a) a String with the name of the
 * file to scan (or null for the standard input), and (b) an ActorRef to a CollectionActor,
 * which collects and prints scan results
 *
 */
public class ScanActor extends UntypedActor{
    //private final Configure message;
    private final ActorRef ref;

    public ScanActor( ActorRef ref){
        this.ref = ref;
    }

    @Override
    public void onReceive(Object o){
        if(o instanceof Configure){
            // scan file
        }
    }

    public static Props createWorker(){
        return Props.create(ScanActor.class, new ArrayList<Object>(0));
    }
}
