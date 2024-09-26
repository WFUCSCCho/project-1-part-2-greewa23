/**********************************************************************
 * @file Parser.java
 * @brief This program implements the Parser class. The class reads
 * an input file of commands and creates an output file.
 * @author Wynne Greene
 * @date: September 26, 2024
 ***********************************************************************/
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser {

    // Create a BST tree of your class type (Note: Replace "Object" with your class type)
    //A BST of type Volcano.
    private BST<Volcano> mybst = new BST<>();

    public Parser(String filename, String csvName) throws FileNotFoundException {
        process(new File(filename), new File(csvName));
    }

    // Removes redundant spaces for each input command. Calls operate_BST for commands
    //to update the output file.
    public void process(File input, File txtInput) throws FileNotFoundException {
        /*
        Read the file line by line and remove redundant spaces and
        ignore blank lines. Split each line into sting array command.
         */
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        FileInputStream csvFileNameStream = null;
        Scanner inputVolcanoScanner = null;

        try {
            //Open the input file.
            inputFileNameStream = new FileInputStream(input);
            inputFileNameScanner = new Scanner(inputFileNameStream);

            //Open the csv file.
            csvFileNameStream = new FileInputStream(txtInput);
            inputVolcanoScanner = new Scanner(csvFileNameStream);

            //The first line is ignored.
            inputFileNameScanner.nextLine();
            inputVolcanoScanner.nextLine();

            //Loop through each line in the csv file.
            while(inputVolcanoScanner.hasNext()) {
                String vData = "insert," + inputVolcanoScanner.nextLine();
                String[] data = vData.split(","); // split the string into multiple parts
                Volcano v;
                //Insert the data into the BST.
                operate_BST(data);
            }

            //Loop through input.txt
            while (inputFileNameScanner.hasNext()) {
                String line = inputFileNameScanner.nextLine();
                //check if it is a blank line.
                line = line.strip();
                int index = line.indexOf(" ");

                //Check if the command is only to print the line or if it
                //is an invalid String.
                if(line.equals("print"))
                    index = 0;
                else if(index<0 && !line.isEmpty()) {
                    operate_BST(new String[] {line});
                    continue;
                }
                else if(index<0)
                    continue;

                //Loop over each character to find where the command and number are.
                for(int i = index; i<line.length(); i++) {
                    if(line.charAt(i)==' ' && !Character.isWhitespace(line.charAt(i+1))) {
                        continue;
                    }
                    else if(line.charAt(i)==' ') {
                        line = line.substring(0, i) + line.substring(i+1);
                        i--;
                    }
                }
                String[] parts = line.split(" ");
                //Call operate_BST method.
                //If the length is not 11 it does not have the correct number of arguments.
                if(parts.length!=12) {
                    operate_BST(new String[] {line});
                }
                else if(parts.length>0) {
                    operate_BST(parts);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        //This block guarantees the file is closed.
        finally {
            if (inputFileNameStream != null) {
                //The input file is closed.
                try {
                    inputFileNameStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (csvFileNameStream != null) {
                //The input file is closed.
                try {
                    csvFileNameStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        Volcano v = new Volcano(); //Store the data in v.
        /*If command is only length two, only the elevation is updated.
        If command is greater than 1 in length, check for any extra entries.
        Some of the lines had names separated with columns, but surrounded by quotes.
        The proper entries must be combined.*/
        if(command.length>1) {
            //data stores the values of each variable.
            ArrayList<String> data = new ArrayList<>();
            //s is updated with entries that need to be combined.
            String s = "";
            for (int i = 1; i < command.length; i++) {
                if (command[i].indexOf("\"") != -1) {
                    s += command[i];
                    i++;
                    //Loop until the last quote is found.
                    while (i < command.length) {
                        s += "," + command[i];
                        if (command[i].indexOf("\"") != -1) {
                            break;
                        }
                        i++;
                    }
                    data.add(s);
                } else {
                    data.add(command[i]);
                }
            }
            //Update v with the data.
            v = new Volcano(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4),
                    Double.parseDouble(data.get(5)), Double.parseDouble(data.get(6)), Integer.parseInt(data.get(7)), data.get(8), data.get(9));
        }
        //Determine which command is being used.
        switch (command[0]) {
            //mybst is of Volvano type. Each case represents a different command.
            case "insert" -> {
                mybst.insert(v);
                writeToFile("insert " + v.toString(), "./result.txt");
            }
            case "search" -> {
                Volcano x = mybst.search(v);
                if(x!=null)
                    writeToFile("found " + v.toString(), "./result.txt");
                else
                    writeToFile("search failed", "./result.txt");
            }
            case "remove" -> {
                Volcano r = mybst.remove(v);
                if(r!=null)
                    writeToFile("removed " + v.toString(), "./result.txt");
                else
                    writeToFile("remove failed", "./result.txt");
            }
            case "print" -> {
                String result = mybst.iterator();
                writeToFile(result, "./result.txt");
            }
            // default case for Invalid Command
            default -> {
                System.out.println("hey");
                writeToFile("Invalid Command", "./result.txt");
            }
        }
    }

    // This is the writeToFile method. The output of a command is written to a file.
    // Generates a result file
    public void writeToFile(String content, String filePath) {
        //Writes BST result into result.txt.
        //Opens the specified file, or creates it if it does not exist.
        //Append new line to the end and write the output.
        FileWriter fw = null;
        //The try block attempts to open the file and write to it. The catch block catches any exceptions.
        try {
            fw = new FileWriter(filePath, true);
            fw.write(content + "\n");
            fw.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
