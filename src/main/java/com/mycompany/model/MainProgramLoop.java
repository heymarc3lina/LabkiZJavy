/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.controller.TakeDataFromUser;
import com.mycompany.view.DataToUser;
import java.io.IOException;

/**
 * Main class in this project. It's depends for comunication view and controller
 *@version 1.3
 * @author Agata Pietrzycka
 */
public class MainProgramLoop {

    DataToUser text;
    TakeDataFromUser userData;
    StationDataBases base;

    /**
     * it's a MainProgramLoop conctsuctor
     *
     */
    public MainProgramLoop() {
        text = new DataToUser();
        userData = new TakeDataFromUser();
        base = new StationDataBases();
    }

    public void mainProgmaLoop() throws IOException {
        String fileName = "plikzBaza.txt";
        base.loadFromFile(fileName);
        boolean inProgress = true;
        while (inProgress) {
            text.hellotoUser();
            while (userData.checkOptionsFromUserInMainLoop() == false) {
                text.ifDateIncorrect();
            }
            if (userData.getUserChoice().equals("1")) {
                text.showOutList(base);
            } else if (userData.getUserChoice().equals("2")) {
                optionsTwo();
            } else if (userData.getUserChoice().equals("3")) {
                optionThree();
            } else if (userData.getUserChoice().equals("4")) {
                text.sayGoodbyeToUser();
                base.saveBaseToFile(fileName);
                inProgress = false;
            }
        }
        
    }

    public void optionThree() {
        text.showOutList(base);
        text.choseStationText();
        while (userData.choseStationToUpdate() == false) {
            text.ifDateIncorrect();
        }
        int stationId = Integer.parseInt(userData.getUserChoice());
        WheatherStation station = base.updateDataToStation(stationId);
        text.showParametersToChange();
        while (userData.choseParametersToChange() == false) {
            text.ifDateIncorrect();
        }
        handleChooseParameter(station);
    }

    public void handleChooseParameter(WheatherStation station) {
        text.changeParametersText();
        if (userData.getUserChoice().equals("1")) {
            double parameters[] = new double[7];
          for(int i =0; i < 7 ; i++){
              text.showParameter(i);
            while(userData.userGetParametersToChange()== false){
                text.ifDateIncorrect();
            }
                parameters[i] = Double.parseDouble(userData.getUserChoice());
                }
          Measurement measure = new Measurement(parameters);
          station.setMeasurement(measure);
        }else if (userData.getUserChoice().equals("2")) {
            while (userData.userGetParametersToChange() == false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setTemperature(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("3")) {
            while (userData.userGetParametersToChange()== false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setHumidity(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("4")) {
            while (userData.userGetParametersToChange() == false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setPressure(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("5")) {
            while (userData.userGetParametersToChange() == false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setVisibility(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("6")) {
            while (userData.userGetParametersToChange()== false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setAmountOfPrecipitation(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("7")) {
            while (userData.userGetParametersToChange()== false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setSunlight(Double.parseDouble(userData.getUserChoice()));
        } else if (userData.getUserChoice().equals("8")) {
            while (userData.userGetParametersToChange()== false) {
                text.ifDateIncorrect();
            }
            station.getMeasurement().setSmog(Double.parseDouble(userData.getUserChoice()));
        }
    }

    /**
     * Method responsible for option two
     *
     */
    public void optionsTwo() {
        try {
            text.textToAddNewStation();
            base.addStation(userData.addNewStation());
        } catch (NumberFormatException e) {
            text.ifDateIncorrect();

        }
    }

}
