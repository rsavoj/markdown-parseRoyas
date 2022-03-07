// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1) {
                break;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket == -1){
                break;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(openParen ==-1 || closeParen == -1 ){
               break;
            }
            if (nextOpenBracket>0  && !(markdown.charAt(nextOpenBracket-1)== '!' ) && openParen-nextCloseBracket == 1){
                System.out.println("execute");
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            else if (nextOpenBracket == 0 && openParen-nextCloseBracket == 1){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            System.out.println("The openParen " + openParen +": the closed Paren " + closeParen + "next closed bra " + nextCloseBracket +" "+ nextOpenBracket);
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
      //  System.out.println("We are timing how long it takes to run all our files it took 1 milute and 15 seconds");
    }
}