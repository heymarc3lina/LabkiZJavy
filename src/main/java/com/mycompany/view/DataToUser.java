/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.view;


import com.mycompany.model.StationDataBases;
import com.mycompany.model.WheatherStation;


/** The class responsible for contact with the user
 *@version 1.5
 * @author Agata Pietrzycka
 */
public class DataToUser {
        
        
    /** The method responsible for saying hello to the user and showing him which options are available to choose 
     * 
     */
    public void hellotoUser(){
        System.out.println("Witaj w bazie stacji meterologicznych w polsce!");
        System.out.println("Co chcesz zrobić?");
        System.out.println("Wybierz 1 jeśli chcesz wyświetlić wszystkie stacje?");
        System.out.println("Wybierz 2 jeśli chcesz dodać nową stację");
        System.out.println("Wybierz 3 jeśli chcesz zmienić dane stacji.");
        System.out.println("Wybierz 4 jeśli chcesz wyjść.");
             
        }
       
    
    /**The method responsible for informing the user that the data entered by him are incorrect
     * 
     */    
    public void ifDateIncorrect(){
        System.out.println("Niepoprawne dane!");
    }
    
     /** The method responsible for informing the user that he chose to add a new station and what the user should to do next
      * 
     */  
    public void textToAddNewStation(){
        System.out.println("Wybrana opcja to dodanie nowej stacji!");
        System.out.println("Najpierw podaj miasto i zatwierdz klawiszem Enter");
        System.out.println("Następnie podaj temperaturę, wilgotność, ciśnienie, widoczność, wysokość opadów, promieniowanie słoneczne oraz smog. ");
    }
    
     /** The method responsible for displaying the list of stations with measurements
      * @param  list its a list with all wheather station  which type is StationDataBases. 
     */ 
    public void showOutList(StationDataBases list){
          for( WheatherStation db : list.getList()){
              System.out.println("Id stacji: " + db.getId());
              System.out.println("Misto: " + db.getTown() );
              System.out.println("Temperatura : " + db.getMeasurement().getTemperature() + " stopni Celsjusza.");
              System.out.println("Wilgotność : " + db.getMeasurement().getHumidity() + "%");
              System.out.println("Ciśnienie: " + db.getMeasurement().getPressure() + " hPa");
              System.out.println("Widoczność: " + db.getMeasurement().getVisibility()+ " m");
              System.out.println("Opad atmosferyczny: " + db.getMeasurement().getTheAmountOfPrecipitation() + " mm");
              System.out.println("Promieniowanie słoneczne: " + db.getMeasurement().getSunlight()+ " W/m2");
              System.out.println("Poziom smogu:  " + db.getMeasurement().getSmog() + "%");
          }
     }
    
    /** The method responsible for displaying the list of parameters to change
      * 
     */ 
    public void showParametersToChange(){
        System.out.println("Co chcesz zmienić?");
        System.out.println("WYbierz 1, jeśli wszystko.");
        System.out.println("WYbierz 2, jeśli temperaturę.");
        System.out.println("WYbierz 3, jeśli wilgotnosć.");
        System.out.println("WYbierz 4, jeśli ciśnienie.");
        System.out.println("WYbierz 5, jeśli widocznosc.");
        System.out.println("WYbierz 6, jeśli opad atmosferyczny.");
        System.out.println("WYbierz 7, jeśli promieniowanie słoneczne.");
        System.out.println("WYbierz 8, jeśli poziom smogu.");
        
    }
    
    /** The method responsible for displaying text with information that user should chose which parameter want to change
      *  
     */ 
    public void changeParametersText(){
        System.out.println("Podaj wartość wybarnego parametru/paramterów.");
    }
    
      /** The method responsible for displaying text with information that user should chose station where he want to change parameters
      *  
     */ 
    public void choseStationText(){
        System.out.println("Wybierz numer stacji, której parametry chcesz zmienić");
    }
            
            
    
    /**
     * Method responsible for say goodbye to user when he want to quit this program
     */
    public void sayGoodbyeToUser(){
     System.out.println("Już nas opuszczasz? Szkoda. Dozobaczenia innym razem");
    }

    /**
     * Method responsible for informing user which parameter he should to give to this program
     * @param i it's a position of parameter which user should to give
     */
    public void showParameter(int i){
        String parametersTab[] = {"Temperatura", "Wilgotność" , "Ciśnienie " , "Widoczność" , "Wysokosć opadów atmosferycznych" , "Promieniowanie słoneczne" , "Smog"};
        System.out.println(parametersTab[i]);
    }
}
    

