/** Kaitlyn Chandler
  * CMSC 255-901
  * Programming Project 6, PieShop
  * Spring 2017
  * This is a program designed to take in a menu from "Pop's Pie Shop" and organize it into specific categories such as:
  * name, category, special diet, and availbility. This class, FoodItem is for declaring those categories and for the toString()
  * method.**/

public class FoodItem {
  
  //enum declarations
  public enum Category { SAVORY, SWEET_PIE, SWEET_TREATS };
  public enum DietType { NONE, VEGAN, VEGETARIAN, GLUTEN_FREE }; 
   
  //other variable declarations
  private Category category;
  private String names;
  private boolean avail;
  private DietType diet; 
  
  
  public FoodItem() //Default Constructor
  {
   
   names = "";
   category = Category.SAVORY;
   //avail= Avail.Y;
   diet = DietType.NONE;
    
  }
  
  
  //setter methods
 public FoodItem (String aNames,String aCategory, String aDietType, String aAvail)
  {
    setDietType(aDietType);
    setNames(aNames);
    setCategory(aCategory);
    setAvail(aAvail);
  }

 public void setCategory (String category) //Mutator Method
 {
   switch(category){
   case "savory":
     this.category= Category.SAVORY;
     break;
   case "sweet pie":
     this.category= Category.SWEET_PIE;
     break;
   case "sweet treat":
     this.category= Category.SWEET_TREATS;
     break;
    default:
     throw new IllegalArgumentException (category + " is not valid input.");
 }
 }
     public void setNames (String names)
     {
       this.names=names;
     }
     
     public void setDietType(String diet)
     {
       switch(diet){
         case "none":
           this.diet=DietType.NONE;
           break;
         case "vegan":
           this.diet=DietType.VEGAN;
           break;
         case "vegetarian":
           this.diet= DietType.VEGETARIAN;
           break;
         case "gluten-free":
         this.diet=DietType.GLUTEN_FREE;
         break;
         default:
            throw new IllegalArgumentException (avail + " is not valid input.");
       }     
     }
     
     public void setAvail(String avail)
     {
       switch(avail){
         case "y": case "y\\r":
         this.avail = true;
         break;
        
         case "n": case "n\\r":
         this.avail= false;
         break;
         default:
     throw new IllegalArgumentException (avail + " is not valid input.");
     } 
     }
     
     public boolean getAvail(){
       return this.avail;
     }
        
 //toString method
 public String toString() //Acessor Method
 {
   //String intro = ("FoodItem {");
   String namesReturn = "Name:" + names.toString() + ", ";
   String categoryReturn = "Category:" + category.toString() + ", ";
   String dietReturn = "Special Diet:" + diet.toString() + ", ";
   String availReturn= "Availibility:" + avail;
  
   return   namesReturn + categoryReturn + dietReturn +  availReturn + "";
   

 }
//getter methods 
 public String getCategory(){
   switch(category){
     case SAVORY:
       return "Savory";
      
     case SWEET_PIE:
       return "Sweet Pie";
       
     case SWEET_TREATS:
       return "Sweet Treat";
       
   }
   return "";
 }
   public String getDietType(){
     switch(diet){
       case NONE:
         return "";
         
       case VEGAN:
         return "(Vegan)";
         
       case GLUTEN_FREE:
         return "(Gluten-free)";
         
       case VEGETARIAN:
           return "(Vegetarian)";
         
      
   }
     return "";
   }
 
     public String getNames(){
       return names;
     }
}
 

  

   

    
  
