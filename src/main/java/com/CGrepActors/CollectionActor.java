package com.CGrepActors;

import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor{
    private FileCount fileCount;
    boolean hasFileCount = false;

    public CollectionActor(){
    }

    public void sendMessage(FileCount fileCount){
        if(!hasFileCount){ // this way we can only set the file count once
            this.fileCount = fileCount;
            hasFileCount = true;
        }
    }


    /***
     * upon receipt, are printed by the CollectionActor. Printout consists of
     * the file name (or "-" for standard input) and the list of matching lines
     */
    @Override
    public void onReceive(Object o) throws Throwable {

        /**
         * When all the Found messages have been processed, the CollectionActor
         * shuts down all actors using Actors.registry().shutdown()
         */
    }


}
