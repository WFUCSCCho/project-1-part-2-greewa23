/**********************************************************************
 * @file proj1.java
 * @brief This program implements the Proj1 class. The class reads
 * in the two input files from the command line. Kaggle Dataset:
 * https://www.kaggle.com/datasets/ramjasmaurya/volcanoes-on-earth-in-2021
 * @author Wynne Greene
 * @date: September 26, 2024
 ***********************************************************************/
import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        //Check if there are two command line arguments.
        if(args.length != 2){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        //Call Parser with the input.txt and csv file of data.
        new Parser(args[0], args[1]);

    }
}