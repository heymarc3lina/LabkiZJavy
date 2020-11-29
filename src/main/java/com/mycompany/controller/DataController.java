/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.ValidDataModel;
import com.mycompany.view.GuiView;
import com.mycompany.view.View;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class is a controller. It's takes data from user and give it to second
 * controller class - MainLoopController
 *
 * @version 2.3
 * @author Agata Pietrzycka
 */
public class DataController {
    
    /**
     * It contains a validator. It is responsible for valid data
     */
    private ValidDataModel validator;
    /**
     * It is an answer which user gives to program.
     */
    private String userChoice; 
    /**
     * It contains view. It is responsible for showing infromation to user.
     */
    private View view;

    /**
     * Class constructor
     */
    public DataController() {
        validator = new ValidDataModel();
        view = new View();
        
    }

    /**
     * It is a getter for user's choice
     *
     * @return String which is user's answer
     */
    public String getUserChoice() {
        return userChoice;
    }

    /**
     * The method responsible for valid option which user choose from menu
     *
     * @return boolean true or false - it's depending on user's choice
     */
    public boolean checkOptionsFromUserInMainLoop() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
            userChoice = choice;
            return true;
        }
        return false;
    }

    /**
     * Method where user chose which parameters he want to change
     *
     * @return boolean true or false - it's dependig on user choice
     */
    public boolean choseParametersToChange() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7")
                || choice.equals("8")) {
            userChoice = choice;
            return true;
        }
        return false;
    }

    /**
     * Method where user chose station or ammount of parameter which he want to
     * updeate or change
     *
     * @return boolean true or false - it's dependig on user choice
     */
    public boolean choseStationToUpdate() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        if (pattern.matcher(choice).matches()) {
            userChoice = choice;
            return true;
        }
        return false;
    }

    /**
     * Method responsible for validating temperature
     *
     * @return String with correct ammout of temperature
     */
    public boolean temperatureValidator(String temperature) {
        if(validator.temperatureValidator(temperature) == false) {
            return false;
        }
        return true;
    }

    /**
     * Method responsible for validating humidity or smog
     *
     * @return String with correct ammout of humidity or smog
     */
    public String humidityOrSmogValidator() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        while (validator.smogOrHumidityValidator(choice) == false) {
            view.showIncorrectDataText();
            scan = new Scanner(System.in);
            choice = scan.next();
        }
        return choice;
    }

    /**
     * Method responsible for validating town
     *
     * @return String with correct ammout of town
     */
    public boolean townValidator(String town) {
        
       if(validator.townValidator(town) == false) {
           return false;
        }
        return true;
    }

    /**
     * Method responsible for validating pressure
     *
     * @return String with correct ammout of pressure
     */
    public String pressureValidator() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();

        while (validator.pressureValidator(choice) == false) {
            view.showIncorrectDataText();
            scan = new Scanner(System.in);
            choice = scan.next();
        }

        return choice;

    }

    /**
     * Method responsible for validating visibility
     *
     * @return String with correct ammout of visibility
     */
    public String visabilityValidator() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        while (validator.visabilityValidator(choice) == false) {
            view.showIncorrectDataText();
            scan = new Scanner(System.in);
            choice = scan.next();
        }

        return choice;
    }

    /**
     * Method responsible for validating the ammout of precipitation
     *
     * @return String with correct ammount of the ammout of precipitation
     */
    public String theAmmoutofPrecipitationValidator() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();

        while (validator.theAmmoutofPrecipitationValidator(choice) == false) {
            view.showIncorrectDataText();
            scan = new Scanner(System.in);
            choice = scan.next();
        }

        return choice;
    }

    /**
     * Method responsible for validating sunlight
     *
     * @return String with correct ammount of sunlight
     */
    public String sunlightValidator() {
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        while (validator.sunlightValidator(choice) == false) {
            view.showIncorrectDataText();
            scan = new Scanner(System.in);
            choice = scan.next();
        }

        return choice;
    }

}
