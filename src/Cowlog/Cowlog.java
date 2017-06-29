package Cowlog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cowlog {

    private ArrayList<Figure> figures;
    private FigureReader figureReader;
    private final int stringLength = 22;

    public Cowlog() {
        this.figureReader = new FigureReader();
        this.figures = figureReader.getFigures();
    }

    private ArrayList<String> makeHeader(String msg) {
        // Creates the message header

        // Split words in muliple line if needed
        ArrayList<String> words = new ArrayList<>(Arrays.asList(msg.split(" ")));
        ArrayList<String> lines = new ArrayList<>();
        String string = "";
        for (String str : words) {
            string = string + str + " ";
            if (string.length() > this.stringLength) {
                lines.add("| " + string);
                string = "";
            }
        }
        lines.add("| " + string);

        // Find longest String in word list
        int largestString = 0;
        for(String str : lines){
            if(str.length()>largestString){
                largestString = str.length();
            }
        }

        // Correct | position at the end of line
        for(int i=0; i<lines.size();i++){
            lines.set(i,lines.get(i) + String.join("", Collections.nCopies(largestString - lines.get(i).length()+1, " ")) + "|");
        }

        // Add top and bottom line
        lines.add(0,String.join("", Collections.nCopies(largestString+2, "_")));
        lines.add("|" + String.join("", Collections.nCopies(largestString, "_")) + "|");
        return lines;
    }

    public static void main(String[] args) {
        // Test case
        Cowlog cowlog = new Cowlog();
        for (String entry : cowlog.makeHeader("Ich bin deine Fehlermeldung - Muuuuuhaha")){
            System.out.println(entry);
        }

        int figureNr = (int) (Math.random() * cowlog.figures.size());
        Figure fig = cowlog.figures.get(figureNr);
        for (String entry : fig.getContent()) {
            System.out.println(entry);
        }
    }
}

