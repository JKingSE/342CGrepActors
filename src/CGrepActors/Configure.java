package CGrepActors;

/**
 * created with a message containing (a) a String with the name of the file
 * to scan (or null for the standard input)
 */
public class Configure {

    private String fileName = null;
    private String pattern = null;
    // default constructor to simulate sending null
    public Configure(){}

    // normal constructor to used to assign the message to this object.
    public Configure(String input, String pattern){
        this.fileName = input;
        this.pattern = pattern;
    }
    public String getPattern(){
        return pattern;
    }
    public String getMessage() {
        return fileName;
    }

}
