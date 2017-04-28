/** Kaitlyn Chandler
  * CMSC 255-901
  * Programming Project 6, PieShop
  * Spring 2017
  * This is a program designed to take in a menu from "Pop's Pie Shop" and organize it into specific categories such as:
  * name, category, special diet, and availbility. This class, PieShop, is for taking in the file, validating the file, and allowing
  * the user to update the menu, close the menu, or save the menu.**/

import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.lang.String;
import java.io.PrintWriter;

public class PieShop{
   public static void main(String[] args) throws FileNotFoundException
      {
     printHeading(6, "Pop's Pie Shop");
     File inputFile= null;
     String filename="";
 //Command Line Arguments
     
     if(args.length>0){
       filename=args[0];
     }
     Scanner in=new Scanner(System.in) ;
     inputFile= new File(filename);
 //File Validator    
     while(!inputFile.exists()){
       System.out.println("Please enter a valid file name");
          
         filename=in.nextLine();
         inputFile= new File(filename);
     }
 // creating new scanner 
   Scanner file = new Scanner(inputFile);
   
         List<FoodItem> savory= new ArrayList<FoodItem>();
         List<FoodItem> sweetTreat= new ArrayList<FoodItem>();
         List<FoodItem> sweetPie= new ArrayList<FoodItem>();
         LocalDate date = LocalDate.now();
       while (file.hasNextLine()) // loops until end of file
       {
         String line = file.nextLine();
         String [] parts= line.split(":");
         if (parts.length>3){
         parts[0]= parts[0].trim();
         parts[1]= parts[1].trim();
         parts[2]= parts[2].trim();
         parts[3]= parts[3].trim();
      
         FoodItem item = new FoodItem(parts[0],parts[1],parts[2],parts[3]);
         switch(parts[1])
         {
           case "savory":
             savory.add(item);
             break;
           case "sweet pie":
             sweetPie.add(item);
             break;
             case "sweet treat":
               sweetTreat.add(item);
             break;
           default:
             throw new IllegalArgumentException (parts[1] + "is not valid input.");
         }  
         }
         //System.out.print("Pop's Pie Shop" + date);
      //System.out.println(item);
     
   }
       Menu menu = new Menu(savory, sweetTreat, sweetPie, date);
       
       
 //loops until false or close program is chosen      
       boolean keepGoing = true;
       
       while(keepGoing){
       System.out.println("Select from the following options:");
       System.out.println("1- Update an existing item on the menu");
       System.out.println("2- Save the menu");
       System.out.println("3- Close Program");
      
       
       switch(in.nextLine()){
         case "1":
           editFoodItem(menu, in);
           break;
         case "2":
          saveMenu(menu, in);
          break;
         case "3":
           //System.exit(0);
           keepGoing= false;
           break;
         default:
           System.out.println("Invalid input, try again.");
       }
       }
    }

//editFoodItem method
private static void editFoodItem(Menu menu, Scanner in){
 
 boolean keepGoing = true;
 while(keepGoing){
 System.out.println("To update an item, select from the following categories:");
 System.out.println("1- Savory");
 System.out.println("2- Sweet Pie");
 System.out.println("3- Sweet Treat");
 System.out.println("4- Exit");
 
   switch(in.nextLine()){
     
     case "1":
       editSavory(menu, in);
       break;
     case "2":
       editSweetPie(menu, in);
       break;
     case "3":
       editSweetTreat(menu, in);
       break;
     case "4":
       keepGoing= false;
       break;
      default:
       System.out.println("Invalid input, try again.");
    
 }
 }
 
}


//save menu to desired output file (method)
private static void saveMenu(Menu menu, Scanner in){

  
  try{
    PrintWriter writer = new PrintWriter("menu.txt", "UTF-8");
    List <FoodItem> AvailbleItems = menu.getAvailItems();
      List<FoodItem> savory= new ArrayList<FoodItem>();
      List<FoodItem> sweetTreat= new ArrayList<FoodItem>();
      List<FoodItem> sweetPie= new ArrayList<FoodItem>();
   for(int i=0; i < AvailbleItems.size() ; i++){
     FoodItem currentAvailItemsItem = AvailbleItems.get(i);
     switch(currentAvailItemsItem.getCategory()){
       case "Savory":
         savory.add(currentAvailItemsItem);
         break;
       case "Sweet Pie":
         sweetPie.add(currentAvailItemsItem);
         break;
       case "Sweet Treat":
         sweetTreat.add(currentAvailItemsItem);
         break;
     }
     
     //System.out.println(currentAvailItemsItem.getNames() + currentAvailItemsItem.getCategory() + currentAvailItemsItem.getAvail() 
                        // + currentAvailItemsItem.getDietType());
  }
   for (int j=0; j< savory.size(); j++){
     FoodItem currentItem = savory.get(j);
     if(j==0){
       writer.println("****Savory****");
     }
     writer.println(currentItem.getNames() + " " + currentItem.getDietType());
}
   for (int k=0; k< sweetTreat.size(); k++){
     FoodItem currentItem = sweetTreat.get(k);
     if(k==0){
       writer.println("****Sweet Treat****");
     }
     writer.println(currentItem.getNames() + " " + currentItem.getDietType());
}
   for (int l=0; l< sweetPie.size(); l++){
     FoodItem currentItem = sweetPie.get(l);
     if(l==0){
       writer.println("****Sweet Pie****");
     }
     writer.println(currentItem.getNames() + " " + currentItem.getDietType());
}
   writer.close();
  }
  // catches any errors
  catch(IOException e){
    System.out.println("yikes");
}
}

public static void editSavory(Menu menu, Scanner in){
     List<FoodItem> savory= menu.getSavory(); 
      
   for (int j=0; j< savory.size(); j++){
     FoodItem currentItem = savory.get(j);
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}

 System.out.println("Enter the item name");
 String name= in.nextLine();
 FoodItem foodItem= menu.findItem("savory", name);
 if(foodItem != null){
   if(foodItem.getAvail()==true){
     foodItem.setAvail("n");
 }
   else{
    foodItem.setAvail("y");
   }
}
}


public static void editSweetTreat(Menu menu, Scanner in){
     List<FoodItem> sweetTreat= menu.getSweetTreats(); 
      
   for (int j=0; j< sweetTreat.size(); j++){
     FoodItem currentItem = sweetTreat.get(j);
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}
 System.out.println("Enter the item name");
 String name= in.nextLine();
 FoodItem foodItem= menu.findItem("sweet treat", name);
 if(foodItem != null){
   if(foodItem.getAvail()==true){
     foodItem.setAvail("n");
 }
   else{
    foodItem.setAvail("y");
   }
}
}


public static void editSweetPie(Menu menu, Scanner in){
     List<FoodItem> sweetPie= menu.getSweetPie(); 
      
   for (int j=0; j< sweetPie.size(); j++){
     FoodItem currentItem = sweetPie.get(j);
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}
 System.out.println("Enter the item name");
 String name= in.nextLine();
 FoodItem foodItem= menu.findItem("sweet pie", name);
 if(foodItem != null){
   if(foodItem.getAvail()==true){
     foodItem.setAvail("n");
 }
   else{
    foodItem.setAvail("y");
   }
}
}
 
/**private static void printMenu(Menu menu, Scanner in){

  
  //try{
    //PrintWriter writer = new PrintWriter("menu.txt", "UTF-8");
    List <FoodItem> AvailbleItems = menu.getAvailItems();
      List<FoodItem> savory= new ArrayList<FoodItem>();
      List<FoodItem> sweetTreat= new ArrayList<FoodItem>();
      List<FoodItem> sweetPie= new ArrayList<FoodItem>();
   for(int i=0; i < AvailbleItems.size() ; i++){
     FoodItem currentAvailItemsItem = AvailbleItems.get(i);
     switch(currentAvailItemsItem.getCategory()){
       case "Savory":
         savory.add(currentAvailItemsItem);
         break;
       case "Sweet Pie":
         sweetPie.add(currentAvailItemsItem);
         break;
       case "Sweet Treat":
         sweetTreat.add(currentAvailItemsItem);
         break;
     }
     
     //System.out.println(currentAvailItemsItem.getNames() + currentAvailItemsItem.getCategory() + currentAvailItemsItem.getAvail() 
                        // + currentAvailItemsItem.getDietType());
  }
   for (int j=0; j< savory.size(); j++){
     FoodItem currentItem = savory.get(j);
     if(j==0){
       System.out.println("****Savory****");
     }
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}
   for (int k=0; k< sweetTreat.size(); k++){
     FoodItem currentItem = sweetTreat.get(k);
     if(k==0){
       System.out.println("****Sweet Treat****");
     }
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}
   for (int l=0; l< sweetPie.size(); l++){
     FoodItem currentItem = sweetPie.get(l);
     if(l==0){
       System.out.println("****Sweet Pie****");
     }
     System.out.println(currentItem.getNames() + " " + currentItem.getDietType());
}
  // writer.close();
  }
  // catches any errors
  //catch(IOException e){
    //System.out.println("yikes");

//}**/


//Print Heading Method
  private static void printHeading(int projectNumber, String projectName){
      System.out.println("Kaitlyn Chandler");
      System.out.println("CMSC 255-901, Spring 2017");
      System.out.println("Programming Project " + projectNumber);
      System.out.println(projectName);
      System.out.println();
}
}
