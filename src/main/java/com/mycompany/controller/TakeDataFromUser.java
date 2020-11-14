/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Measurement;
import com.mycompany.model.WheatherStation;
import java.util.Scanner;
import java.util.regex.Pattern;

/**This class is a controller. It's takes data from user and give it to model - MainProgramLoop
 *
 * @author Agata Pietrzycka
 */
public class TakeDataFromUser {
  
    private String userChoice; //zrobić komentarz

    public String getUserChoice() {
        return userChoice;
    }
    
    
    
    /** The method responsible for taking data from user and create a new wheather station with measurement and returning it
    *@return station it's a station which is create by user
    */
   public WheatherStation addNewStation()throws NumberFormatException { //spróbować zastąpić to swoim własnym wyjątkiem i wgl trzeba to trochę zmienić
        Scanner scan = new Scanner(System.in);
        String town = scan.next();
        double measurementTab[] = new double[7];
        for(int i=0 ; i< measurementTab.length ; i++){
            measurementTab[i] = Double.parseDouble(scan.next());
        }
        Measurement measure = new Measurement(measurementTab);
        WheatherStation station = new WheatherStation(town, measure);
       
       return station;
        
    }
   
    
   /** The method responsible for valid option which user choose from menu
    *@return true or false - it's depending on user's choice 
    */
   public boolean checkOptionsFromUserInMainLoop(){
      Scanner scan= new Scanner(System.in);
      String choice = scan.next();
       if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") ){
           userChoice = choice;
           return true; 
       }
        return false;
    }
   
    /** Method where user chose which  parameters he want to change 
   *    @return true or false - it's dependig on user choice 
   */
   public boolean choseParametersToChange(){
       Scanner scan= new Scanner(System.in);
       String choice = scan.next();
       if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7")
               || choice.equals("8")){
           userChoice= choice;
           return true;
       }
       return false;
   }
   
   /** Method where user chose station or ammount of parameter which he want to updeate or change
   *   @return true or false - it's dependig on user choice 
   */
   public boolean choseStationToUpdate(){
       Scanner scan= new Scanner(System.in);
       String choice = scan.next();
       Pattern pattern = Pattern.compile("[0-9]{1,}");
       if(choice == null){
           return false;
       }
       else if(pattern.matcher(choice).matches()){
           userChoice = choice;
           return true;
           
       }else{
             
       return false;
   }
       
   }
   
   public boolean userGetParametersToChange(){
       Scanner scan= new Scanner(System.in);
       String choice = scan.next();
       Pattern pattern = Pattern.compile("[0-9]{1,}[.]{0,1}[0-9]{0,}");
       if(choice == null){
           return false;
       }
       else if(pattern.matcher(choice).matches()){
           userChoice = choice;
           return true;
           
       }else{
             
       return false;
   }
   }
   
   
}
 


   
