package Cowlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FigureReader {

    private ArrayList<Figure> figures;
    private final String DIR = "./src/Cowlog/figures";

    public FigureReader() {
        this.initVariables();
        this.addFigures();
    }

    private void initVariables(){
        this.figures = new ArrayList<Figure>();
    }

    private void addFigures(){
        // Add all figures in figures directory as Figures(Class) to the ArrayList figures
        ArrayList<File> textFiles = this.finder(this.DIR);
        for (File textFile : textFiles) {
            Scanner s;
            try {
                s = new Scanner(textFile);
            } catch (FileNotFoundException e) {
                System.out.println(e);
                continue;
            }
            ArrayList<String> list = new ArrayList<>();
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            Figure figure = new Figure();
            figure.setContent(list);
            this.figures.add(figure);
            s.close();
        }
    }
    public ArrayList<Figure> getFigures(){
        return this.figures;
    }

    private ArrayList<File> finder( String dirName){
        // Reads all txt in figures directory
        ArrayList<File> results = new ArrayList<File>();
        File[] files = new File(this.DIR).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        for (File file : files) {
            if (file.isFile()) {
                results.add(file);
            }
        }
        return  results;
    }
}
