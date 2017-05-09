package sql_project2;
import java.io.*;
import java.util.*;
public class SQL_Project2
{

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        //Welcome message
        System.out.println("Welcome to the SQL formatter.");
        System.out.println("This program will format your comma separated file to an INSERT SQL file.");
        
        //create an array to store the data
        ArrayList<String> inputs = new ArrayList();
        
        //create buffered reader to read file
         BufferedReader in = null;
         
         //ask user for input file
         System.out.println("Enter the location of the source file");
         System.out.println("i.e. ---- C:\\java\\text.txt -----");
         String userInput = scan.nextLine();
         
         //create file using user input
         File source = new File(userInput);
         
         //ask user for output file
         System.out.println("Enter the name for output file");
         System.out.println("i.e. ---- formatted_text -----");
         String userOutput = scan.nextLine();
         
         //create output file using user input
        String output = new File(System.getProperty("user.home"), "/Desktop") + "\\" + userOutput + ".sql";
         
         //ask the user for the name of the table
         System.out.println("Enter the name of the table: ");
         String table = scan.nextLine();
         
         //start the work
            try 
            {
                in = new BufferedReader(
                     new FileReader(source));

                //read line by ine
                String line = in.readLine();
                String[] first = line.split(",");
                while(line != null) 
                {
                    //add parenthesis needed for SQL INPUT    
                    inputs.add( "(" + line + ")");
  
                    line = in.readLine();
                }
                
                // create the printer
                PrintWriter out = new PrintWriter(
                                  new BufferedWriter(
                                  new FileWriter(output)));
                
                //print to file 
                //adding a comma to end of each line 
                //except for last add a semi-colon
                out.println("INSERT INTO");
                out.println("[dbo].[" + table + "]");
                
                out.print("([");
                for(int i = 0; i < first.length; i++)
                {
                    if(first[i].startsWith(" "))
                        {
                            first[i] = first[i].substring(1);
                        }
                    
                    if(i < (first.length - 1))
                    {
                        
                        out.print(first[i] + "]\n,[");
                    }
                    else
                    {
                        out.print(first[i]);
                    }
                }
                out.println("])");
                
                out.println("VALUES");
                for(int i = 1; i < inputs.size(); i++)
                {
                    if(i < (inputs.size() - 1))
                    {
                        out.println(inputs.get(i) + ",");
                    }
                    else 
                    {
                        out.println(inputs.get(i) + ";");
                    }
                     
                } 
                //close the streams
                in.close();
                out.close();
                
                //output the file location
                System.out.println("Your file was created and saved to your desktop,\nlocation is...");
                System.out.println(output);
            }
            
            //exception handling
            catch(IOException ioe) 
            {
                System.out.println(ioe);
            }
            
    }
    
}
