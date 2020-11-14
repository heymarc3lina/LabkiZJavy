/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.model.WheatherStation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class with list of all wheather stations
 *
 * @version 1.3
 * @author Agata Pietrzycka
 */
public class StationDataBases {

    /**
     * List is a field with list of all wheather station which we observe on
     * this program
     */
    private ArrayList<WheatherStation> list = new ArrayList<>();

    /**
     * Method responsible for add weather station to list
     *
     * @param station it's a station which we want to add to list
     */
    public void addStation(WheatherStation station) {
        list.add(station);
        station.setId(list.size());
    }

    /**
     * Method responsible for returning list's size
     *
     * @return int number that is the length of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Method responsible fot returning list of weather station
     *
     * @return ArrayList type which contains list with our wheather stations
     */
    public ArrayList<WheatherStation> getList() {
        return list;
    }

    /**
     * Method responsible for load list to our base from file
     *
     * @param fileName name of file where is our base
     * @throws IOException when this method has problem with file
     */
    //metoda ładująca dane z pliku do bazy
    public void loadFromFile(String fileName) throws IOException {

        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
            String line = inputFile.readLine();
            double tabParameters[] = new double[7];
            while (line != null) {
                StringTokenizer preparedString = new StringTokenizer(line, " ");
                int id = Integer.parseInt(preparedString.nextToken());
                String town = preparedString.nextToken();
                for (int i = 0; i < 7; i++) {
                    tabParameters[i] = Double.parseDouble(preparedString.nextToken());
                }
                Measurement measure = new Measurement(tabParameters);
                WheatherStation station = new WheatherStation(town, measure);
                station.setId(id);
                addStation(station);
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
    public boolean saveBaseToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            list.forEach(station -> {
                printWriter.print(station.getId() + " " + station.getTown() + " " + station.getMeasurement().getTemperature() + " " + station.getMeasurement().getHumidity()
                        + " " + station.getMeasurement().getPressure() + " " + station.getMeasurement().getVisibility() + " " + station.getMeasurement().getTheAmountOfPrecipitation() + " "
                        + station.getMeasurement().getSunlight() + " " + station.getMeasurement().getSmog() + "\n");
            });
            printWriter.close();
            return true;
        }
            
    }

    /**
     * Method responsible for search station and tels the another class that
     * station with givean id exists
     *
     * @param stationId it's a uniquel station number. After which this number
     * station is search
     * @return boolean - true if station exists and false if not
     */
    //metoda do znajdywania stacji meterologicznej
    public boolean findStation(int stationId) {
        for (WheatherStation station : list) {
            if (station.getId() == stationId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method responsible for returning station which has id equals stationId
     *
     * @param stationId it is id of chosen station
     * @return object in WheatherStation type. This object is null or is a
     * station. It is dependending on existence of the chosen station
     */
    public WheatherStation updateDataToStation(int stationId) {

        for (WheatherStation station : list) {
            if (station.getId() == stationId) {
                return station;
            }
        }
        return null;
    }

}
