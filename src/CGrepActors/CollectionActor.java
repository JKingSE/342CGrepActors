package CGrepActors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor{
    private FileCount fileCount;
    boolean hasFileCount = false;
    private ActorRef ref;

    public CollectionActor(){
    }


    public void accept(FileCount fileCount) throws Throwable {
        if(!hasFileCount){ // this way we can only set the file count once
            this.fileCount = fileCount;
            hasFileCount = true;

            ref = this.getContext().actorOf(ScanActor.createWorker());
        }
    }


    /***
     * upon receipt, are printed by the CollectionActor. Printout consists of
     * the file name (or "-" for standard input) and the list of matching lines
     * @param foundObject
     */
    public void accept(Found foundObject) throws Throwable {
        /**
         * When all the Found messages have been processed, the CollectionActor
         * shuts down all actors using Actors.registry().shutdown()
         */
    }



    @Override
    public void onReceive(Object message){
        if(message instanceof FileCount){
            try {
                accept((FileCount) message );       // accepts a filecount object
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        else{
            try {
                accept((Found) message);            // accepts a found object
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
