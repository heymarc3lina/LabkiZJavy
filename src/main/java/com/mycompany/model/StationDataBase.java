/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.model.Exceptions.StationDoesNotExistException;
import java.util.ArrayList;

/**
 * This is class with list of all weather stations
 *
 * @version 2.2
 * @author Agata Pietrzycka
 */
public class StationDataBase {

    /**
     * List is a field with list of all weather station which we observe on this
     * program
     */
    private ArrayList<WheatherStation> list;

    /**
     * Class constructor
     */
    public StationDataBase() {
        list = new ArrayList<>();
    }

    /**
     * Method responsible for add weather station to list
     *
     * @param station it's a station which we want to add to list
     */
    public void addStation(WheatherStation station) {
        if (station != null) {
            list.add(station);
            station.setId(list.size());
        }
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
     * Method responsible for finding a station
     *
     * @param town it's a town where searching station is located
     * @return boolean it is dependent on find station
     */
    public boolean findStation(String town) {
        if (town == null || town.isEmpty()) {
            return false;
        }
        return list.stream().anyMatch(station -> (station.getTown().equals(town)));
    }

    /**
     * Method responsible for returning station which has id equals stationId
     *
     * @param stationId it's id of chosen station
     * @return WheatherStation is null or is a station. It is dependending on
     * existence of the chosen station
     *
     * @throws StationDoesNotExistException it is throws when list has no
     * station with recived id
     */
    public WheatherStation getStationByTown(String town) throws StationDoesNotExistException {

       if(town != null || town.isEmpty() ==false){
        for (WheatherStation station : list) {
            if (station.getTown().equals(town)) {
                return station;
            }
        }
       }
        throw new StationDoesNotExistException(town);
    }

}
