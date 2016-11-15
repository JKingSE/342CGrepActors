package CGrepActors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private ActorRef ref;
    private boolean hasActorRef = false;
    private boolean hasConfigureMessage = false;



    @Override
    public void onReceive(Object o){
        if(o instanceof Configure && !hasConfigureMessage){
            // scan file
            String filePath = ((Configure) o).getMessage();
            String pattern = ((Configure) o).getPattern();
            try {
                Search search = new Search(new File(filePath),pattern);
                ListOfFound results = search.call();
            }
            catch (Exception e) { e.printStackTrace();    }

            System.out.println("The message: " + ((Configure) o).getMessage());
            hasConfigureMessage = true;
        }
        else if( o instanceof ActorRef && !hasActorRef){ // only want to be able to recieve exactly one ActorRef
            ref = (ActorRef)o;
            hasActorRef = true;
        }
        else{
            System.err.print("unexpected message type: " + o.getClass());
        }
    }

}
