/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Measurement;
import com.mycompany.model.StationDataBase;
import com.mycompany.model.Exceptions.StationDoesNotExistException;
import com.mycompany.model.ValidDataModel;
import com.mycompany.model.WheatherStation;
import com.mycompany.view.AddNewStationGui;
import com.mycompany.view.ChangeDataGui;
import com.mycompany.view.GuiView;
import com.mycompany.view.ShowStationGui;
import com.mycompany.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import java.util.StringTokenizer;

import javax.swing.JOptionPane;


/**
 * This is a class with main methods in this project. It's responsible for
 * comunication between view and model
 *
 * @version 2.3
 * @author Agata Pietrzycka
 */
public class MainLoopController {

    GuiView gui;
    AddNewStationGui addNewStationGui;
    ChangeDataGui changeDataGui;
    ShowStationGui showStationGui;

    /**
     * It is a name of file where data are stored
     */
    private String fileName;
    /**
     * It contains view. It is responsible for showing infromation to user.
     */
    private View view;
    /**
     * It contains a second class from controller. It is responsible for taking
     * and validing data from user.
     */
    private DataController userData;
    /**
     * It contains a model. It is responsible to give to program data about
     * weather stations.
     */
    private StationDataBase base;
    private ValidDataModel valid;

    /**
     * Class conctsuctor
     *
     */
    public MainLoopController() {
        fileName = "plikzBaza.txt";
        view = new View();
        userData = new DataController();
        base = new StationDataBase();
        gui = new GuiView();
        addNewStationGui = new AddNewStationGui();
        changeDataGui = new ChangeDataGui();
        showStationGui = new ShowStationGui();
        valid = new ValidDataModel();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            gui.setVisible(true);
            addNewStationGui.setVisible(false);
            changeDataGui.setVisible(false);
            showStationGui.setVisible(false);

        });

    }

    /**
     * Method responsible for working this program.
     */
    public void mainProgmaLoop() {

        try {
            loadFromFile(fileName);
        } catch (IOException e) {
            view.showExceptionText(e.getMessage());
        }
        gui.getAddStationButton().addActionListener(e -> addNewStation());
        gui.getChangeDataButton().addActionListener(e -> updateDataForChoosenStation());
        gui.getShowStationsButton().addActionListener(e -> showList());
        gui.getExitButton().addActionListener(e -> closeProgram());
        boolean inProgress = true;
//        while (inProgress) {
//            view.showMenu();
//            while (userData.checkOptionsFromUserInMainLoop() == false) {
//                view.showIncorrectDataText();
//            }
//
//            if (userData.getUserChoice().equals("1")) {
//                view.showOutList(base);
//            } else if (userData.getUserChoice().equals("2")) {
//                addNewStation();
//            } else if (userData.getUserChoice().equals("3")) {
//                updateDataForChoosenStation();
//            } else if (userData.getUserChoice().equals("4")) {
//                view.sayGoodbyeToUser();
//                try {
//                    saveBaseToFile(fileName);
//                } catch (IOException e) {
//                    view.showExceptionText(e.getMessage());
//                }
//
//                inProgress = false;
//            }
//        }

    }

    /**
     * This method is responsible for save data to file after exit button is
     * clicked.
     */
    private void closeProgram() {
        try {
            saveBaseToFile(fileName);
        } catch (IOException e) {
            view.showExceptionText(e.getMessage());
        }

        gui.dispose();
        addNewStationGui.dispose();
        changeDataGui.dispose();
        showStationGui.dispose();
    }

    /**
     * Method responsible for showing list with weather station and parameters
     * for user
     */
    private void showList() {
        gui.setVisible(false);
        showStationGui.setVisible(true);

        if (base.getList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No station to show", "Base is empty", JOptionPane.INFORMATION_MESSAGE);

        } else {
            showStationGui.showOutList(base);
        }
        showStationGui.getBackButton().addActionListener((ActionEvent ea) -> {
            gui.setVisible(true);
            showStationGui.setVisible(false);
        });
    }
    
    private void handeChangeDataGui(){
         
        if(changeDataGui.getSelectedValue()!= null || changeDataGui.getSelectedValue().isEmpty()==false  ){
        WheatherStation station = base.getStationByTown(changeDataGui.getSelectedValue());
        changeDataGui.setValueOnTextField(station.getTown(), station.getMeasurement().getTemperature(), station.getMeasurement().getPressure(), 
                station.getMeasurement().getHumidity(), station.getMeasurement().getSmog(), station.getMeasurement().getTheAmountOfPrecipitation(),
                station.getMeasurement().getSunlight(), station.getMeasurement().getVisibility());
        }
    }

    /**
     * Method responsible for update data for choosen station.
     */
    private void updateDataForChoosenStation() {
        gui.setVisible(false);
        changeDataGui.setVisible(true);
        changeDataGui.insertStationToList( base);
        changeDataGui.getStationList().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                handeChangeDataGui();
            }
        });
        changeDataGui.getBackButton().addActionListener((ActionEvent ea) -> {
            gui.setVisible(true);
            changeDataGui.setVisible(false);
        });
        
//        view.showOutList(base);
//        view.showChoseStationText();
//        while (userData.choseStationToUpdate() == false) {
//            view.showIncorrectDataText();
//        }
//        int stationId = Integer.parseInt(userData.getUserChoice());
//        try {
//            WheatherStation station = base.getStationById(stationId);
//            updateChooseParameter(station);
//        } catch (StationDoesNotExistException exeption) {
//            view.showExceptionText(exeption.getMessage());
//        }

    }

    /**
     * Method responsible for update choosen parameter by user.
     *
     * @param station it's a choosen station where user wants to change
     * parameters
     */
    private void updateChooseParameter(WheatherStation station) {
        view.showParametersToChange();
        while (userData.choseParametersToChange() == false) {
            view.showIncorrectDataText();
        }
        view.showChangeParametersText();
        switch (userData.getUserChoice()) {
            case "1":

                station.setMeasurement(setsParameters());
                break;
            case "2":

                // station.getMeasurement().setTemperature(Double.parseDouble(userData.temperatureValidator()));
                break;
            case "3":

                station.getMeasurement().setHumidity(Double.parseDouble(userData.humidityOrSmogValidator()));
                break;
            case "4":
                station.getMeasurement().setPressure(Double.parseDouble(userData.pressureValidator()));
                break;
            case "5":
                station.getMeasurement().setVisibility(Double.parseDouble(userData.visabilityValidator()));
                break;
            case "6":
                station.getMeasurement().setAmountOfPrecipitation(Double.parseDouble(userData.theAmmoutofPrecipitationValidator()));
                break;
            case "7":
                station.getMeasurement().setSunlight(Double.parseDouble(userData.sunlightValidator()));
                break;
            case "8":
                station.getMeasurement().setSmog(Double.parseDouble(userData.humidityOrSmogValidator()));
                break;
            default:
                break;
        }
    }

    /**
     * Method responsible for adding new weather station
     *
     */
    private void addNewStation() {
        addNewStationGui.setVisible(true);
        gui.setVisible(false);
        

        addNewStationGui.getConfirmButtom().addActionListener((ActionEvent ae) -> {
            String town = addNewStationGui.getTownTextField().getText();
            String temperature = addNewStationGui.getTemperatureTextField().getText();
            String humidity = addNewStationGui.getHumidityTextField().getText();
            String smog = addNewStationGui.getSmogTextField().getText();
            String visability = addNewStationGui.getVisabilityTextField().getText();
            String pressure = addNewStationGui.getPressureTextField().getText();
            String sunlight = addNewStationGui.getSunlightTextField().getText();
            String rainfall = addNewStationGui.getRainfallTextField().getText();

            if (valid.townValidator(town) == false || valid.temperatureValidator(temperature) == false || valid.smogOrHumidityValidator(humidity) == false
                    || valid.smogOrHumidityValidator(smog) == false || valid.visabilityValidator(visability) == false || valid.pressureValidator(pressure) == false
                    || valid.sunlightValidator(sunlight) == false || valid.theAmmoutofPrecipitationValidator(rainfall) == false) {
                JOptionPane.showMessageDialog(null, "Value or values are not correct", "Data incorrect", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (base.findStation(town)) {
                    JOptionPane.showMessageDialog(null, "Station in " + town + " exist.", "Data incorrect", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<Double> parameters = new ArrayList<>();
                    parameters.add(Double.parseDouble(temperature));
                    parameters.add(Double.parseDouble(humidity));
                    parameters.add(Double.parseDouble(pressure));
                    parameters.add(Double.parseDouble(visability));
                    parameters.add(Double.parseDouble(rainfall));
                    parameters.add(Double.parseDouble(sunlight));
                    parameters.add(Double.parseDouble(smog));
                    Measurement measure = new Measurement(parameters);
                    base.addStation(createStation(town, measure.getTemperature(), measure.getHumidity(), measure.getPressure(), measure.getVisibility(),
                            measure.getTheAmountOfPrecipitation(), measure.getSunlight(), measure.getSmog()));
                    addNewStationGui.setVisible(false);
                    gui.setVisible(true);
                    addNewStationGui.clearaddNewStationGui();
                }
            }
        });
        addNewStationGui.getBackButton().addActionListener((ActionEvent ea) -> {
            gui.setVisible(true);
            addNewStationGui.setVisible(false);
        });

    }

    /**
     * Method responsible for setting new parameters.
     *
     * @return Measurement it's an object contains all parameters of measurement
     */
    private Measurement setsParameters() {
        ArrayList<Double> parameters = new ArrayList<>();

        view.showParameterToCreateNewStation(1);
        //parameters.add(Double.parseDouble(userData.temperatureValidator()));

        view.showParameterToCreateNewStation(2);
        parameters.add(Double.parseDouble(userData.humidityOrSmogValidator()));

        view.showParameterToCreateNewStation(3);
        parameters.add(Double.parseDouble(userData.pressureValidator()));

        view.showParameterToCreateNewStation(4);
        parameters.add(Double.parseDouble(userData.visabilityValidator()));

        view.showParameterToCreateNewStation(5);
        parameters.add(Double.parseDouble(userData.theAmmoutofPrecipitationValidator()));

        view.showParameterToCreateNewStation(6);
        parameters.add(Double.parseDouble(userData.sunlightValidator()));

        view.showParameterToCreateNewStation(7);
        parameters.add(Double.parseDouble(userData.humidityOrSmogValidator()));

        Measurement measure = new Measurement(parameters);
        return measure;
    }

    /**
     * Method responsible for load list to our base from file
     *
     * @param fileName it's a name of file where is our base
     * @throws IOException when this method has problem with file
     */
    public void loadFromFile(String fileName) throws IOException {

        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
            String line = inputFile.readLine();
            ArrayList<Double> parameters = new ArrayList<>();
            while (line != null) {
                StringTokenizer preparedString = new StringTokenizer(line, " ");
                int id = Integer.parseInt(preparedString.nextToken());
                String town = preparedString.nextToken();
                for (int i = 0; i < 7; i++) {
                    parameters.add(Double.parseDouble(preparedString.nextToken()));
                }
                Measurement measure = new Measurement(parameters);
                WheatherStation station = new WheatherStation(town.replace("_", " "), measure);
                station.setId(id);
                base.addStation(station);
                parameters.clear();
                line = inputFile.readLine();
            }
        }

    }

    /**
     * Method responbile for saving data from list to file
     *
     * @param fileName it's a file where base is saved
     * @throws IOException when method has problem with file
     */
    public void saveBaseToFile(String fileName) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            base.getList().forEach(station -> {
                printWriter.print(station.getId() + " " + station.getTown().replace(" ", "_") + " " + station.getMeasurement().getTemperature() + " " + station.getMeasurement().getHumidity()
                        + " " + station.getMeasurement().getPressure() + " " + station.getMeasurement().getVisibility() + " " + station.getMeasurement().getTheAmountOfPrecipitation() + " "
                        + station.getMeasurement().getSunlight() + " " + station.getMeasurement().getSmog() + "\n");
            });
            printWriter.close();

        }

    }

    /**
     * Method responsible for create new station with recived parameters.
     *
     * @param town it's a town where station is located
     * @param parameters it's paremetrs which station measure
     * @return WheatherStation which is null or station
     */
    public WheatherStation createStation(String town, double... parameters) {

        if (parameters.length != 7 || town == null || town.isEmpty()) {
            return null;
        }

        ArrayList<Double> listOfParameters = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            listOfParameters.add(parameters[i]);
        }

        WheatherStation station = new WheatherStation(town, new Measurement(listOfParameters));
        return station;

    }
}
