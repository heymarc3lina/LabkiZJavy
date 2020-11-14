/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

/**
 * It's a class with all parameters which every wheather station has.
 *
 * @author Agata Pietrzycka
 * @version 1.2 
 *
 */
public class Measurement {

    /**
     * Temperature which is noted by station
     *
     */
   private double temperature;
    /**
     * humidity- humidity which is noted by station
     */
    private double humidity;
    /**
     * pressure - pressure which is noted by station
     */
    private double pressure;
    /**
     * visibility - visibility which is noted by station
     */
    private double visibility;
    /**
     * theAmountOfPrecipitation - theAmountOfPrecipitation which is noted by
     * station
     */
    private double theAmountOfPrecipitation;
    /**
     * sunlight- sunlight which is noted by station
     *
     */
    private double sunlight;
    /**
     * smog - smog ammount which is noted by station
     */
    private double smog;

    /**
     * It's class constructor.
     * @param tabWithParemeter table with all parameters which station has
     */
    public Measurement(double tabWithParemeter[]) {
        this.temperature = tabWithParemeter[0];
        this.humidity = tabWithParemeter[1];
        this.pressure = tabWithParemeter[2];
        this.visibility = tabWithParemeter[3];
        this.theAmountOfPrecipitation = tabWithParemeter[4];
        this.sunlight = tabWithParemeter[5];
        this.smog = tabWithParemeter[6];
    }
/**
 * It's a setter for temperature.
 * @param newTemperature it's a new ammount of temperature 
 */
    public void setTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }
/**
 * It's a setter for humidity
 * @param newHumidity it's a new ammount of himanity 
 */
    public void setHumidity(double newHumidity) {
        this.humidity = newHumidity;
    }
/**
 * It's a setter for pressure
 * @param newPressure it's a new ammount of pressure 
 */
    public void setPressure(double newPressure) {
        this.pressure = newPressure;
    }
/**
 * It's a setter for visibility
 * @param newVisibility it's a new ammount of visibilyty 
 */
    public void setVisibility(double newVisibility) {
        this.visibility = newVisibility;
    }
/**
 *  It's a setter for Amount Of Precipitation
 * @param newtheAmountOfPrecipitation - it's a new ammount of Amount Of Precipitation
 */
    public void setAmountOfPrecipitation(double newtheAmountOfPrecipitation) {
        this.theAmountOfPrecipitation = newtheAmountOfPrecipitation;
    }
/**
 * It's a setter for smog
 * @param smog it's a new ammout of smog
 */
    public void setSmog(double smog) {
        this.smog = smog;
    }
/**
 * It's a setter for sunlight
 * @param timeOfSunshine it's a new ammout of sunlight
 */
    public void setSunlight(double timeOfSunshine) {
        this.sunlight = timeOfSunshine;
    }
/**
 * It's a getter for temperature.
 * @return double which is temperature ammount
 */
    public double getTemperature() {
        return temperature;
    }
/**
 * It's a getter for humidity.
 * @return double which is humidity ammount
 */
    public double getHumidity() {
        return humidity;
    }
/**
 *  It's a getter for pressure.
 * @return double which is pressure ammount
 */
    public double getPressure() {
        return pressure;
    }
/**
 *  It's a getter for visibility.
 * @return double which is visibility ammount
 */
    public double getVisibility() {
        return visibility;
    }
/**
 * It's a getter for Amount Of Precipitation.
 * @return double which is Amount Of Precipitation
 */
    public double getTheAmountOfPrecipitation() {
        return theAmountOfPrecipitation;
    }
/**
 *  It's a getter for sulnight
 * @return  double which is sunlight ammount
 */
    public double getSunlight() {
        return sunlight;
    }
/**
 *   It's a getter for smog
 * @return double which is smog ammount
 */
    public double getSmog() {
        return smog;
    }

}
