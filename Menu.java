/** Kaitlyn Chandler
  * CMSC 255-901
  * Programming Project 6, PieShop
  * Spring 2017
  * This is a program designed to take in a menu from "Pop's Pie Shop" and organize it into specific categories such as:
  * name, category, special diet, and availbility. This class, Menu, is for constructing the menu into an actual 
  * menu as an arrayList.**/


import java.util.*;
import java.time.LocalDate;

public class Menu{
  private List <FoodItem> SavoryItems;
  private List <FoodItem> SweetTreats;
  private List  <FoodItem> SweetPie;
  private LocalDate date;
  
  public Menu(List <FoodItem> SavoryItems, List <FoodItem> SweetTreats, List  <FoodItem> SweetPie, LocalDate date){
    
  this.SavoryItems = SavoryItems;
  this.SweetTreats = SweetTreats;
  this.SweetPie= SweetPie;
  this.date= date;
  
  }  
  // Default
  //other defaults for array list not necessary
  public Menu(){
    this.setDate();
  }
    
  
  //setter method
  public void setDate(){
      date = LocalDate.now();
      
  }
  
  public  List <FoodItem> getSavory(){
   return this.SavoryItems;
  }
  
  public  List <FoodItem> getSweetTreats(){
    return this.SweetTreats;
  }
  public List <FoodItem> getSweetPie(){
    return this.SweetPie;
  }
  public List <FoodItem> getAvailItems(){
   List <FoodItem> AvailItems = new ArrayList<FoodItem>();
   List <FoodItem> SavoryItems = getSavory();
   List <FoodItem> SweetTreats = getSweetTreats();
   List <FoodItem> SweetPie = getSweetPie();
   
   for(int i=0; i < SavoryItems.size(); i++){
     FoodItem currentSavoryItem = SavoryItems.get(i);
     if(currentSavoryItem.getAvail()){
       AvailItems.add(currentSavoryItem);
     }
   }
      for(int j=0; j < SweetTreats.size() ; j++){
     FoodItem currentSweetTreatsItem = SweetTreats.get(j);
     if(currentSweetTreatsItem.getAvail()){
       AvailItems.add(currentSweetTreatsItem);
     }
  }
       for(int k=0; k < SweetPie.size() ; k++){
     FoodItem currentSweetPieItem = SweetPie.get(k);
     if(currentSweetPieItem.getAvail()){
       AvailItems.add(currentSweetPieItem);
     }
       }

  return AvailItems;
  }
  public FoodItem findItem(String category, String name) {
    switch(category){
      case "savory":
        for(int i=0; i < SavoryItems.size(); i++){
     FoodItem currentSavoryItem = SavoryItems.get(i);
     if(Objects.equals(name,currentSavoryItem.getNames())){
       return currentSavoryItem;  
      }
      }
      System.out.println("Item not found.");  
        break;
      case "sweet treat":
        for(int j=0; j < SweetTreats.size() ; j++){
     FoodItem currentSweetTreatsItem = SweetTreats.get(j);
     if (Objects.equals(name,currentSweetTreatsItem.getNames())){
       return currentSweetTreatsItem;
     }
      }
        System.out.println("Item not found.");  
        break;
      case "sweet pie":
        for(int k=0; k < SweetPie.size() ; k++){
     FoodItem currentSweetPieItem = SweetPie.get(k);
     if(Objects.equals(name,currentSweetPieItem.getNames())){
       return currentSweetPieItem;
     }  
    }
     System.out.println("Item not found.");  
     break;
      }
    return null;
  }
  
}
