package com.CGrepActors;

/**
 * Created by nate on 11/10/16.
 */

/**
 * Contains a count of the number of files being scanned. This class is meant to passed to the
 * CollectionActor for processing.
 */
public class FileCount {
    private final int count;
    public FileCount(int count){
        this.count = count;
    }
    public int getFileCount(){
        return count;
    }
}