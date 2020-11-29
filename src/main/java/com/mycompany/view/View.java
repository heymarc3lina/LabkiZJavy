/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.view;

import com.mycompany.model.StationDataBase;
import com.mycompany.model.WheatherStation;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The class responsible for contact with the user
 *
 * @version 2.1
 * @author Agata Pietrzycka
 */
public class View {

    /**
     * The method responsible for saying hello to the user and showing him which
     * options are available to choose
     *
     */
    public void showMenu() {
        System.out.println("Witaj w bazie stacji meterologicznych w polsce!");
        System.out.println("Co chcesz zrobić?");
        System.out.println("Wybierz 1 jeśli chcesz wyświetlić wszystkie stacje?");
        System.out.println("Wybierz 2 jeśli chcesz dodać nową stację");
        System.out.println("Wybierz 3 jeśli chcesz zmienić dane stacji.");
        System.out.println("Wybierz 4 jeśli chcesz wyjść.");

    }

    /**
     * The method responsible for informing the user that the data entered by
     * him are incorrect
     *
     */
    public void showIncorrectDataText() {
        System.out.println("Niepoprawne dane!");
    }

    /**
     * The method responsible for informing the user that he chose to add a new
     * station and what the user should to do next
     *
     */
    public void textToAddNewStation() {
        System.out.println("Wybrana opcja to dodanie nowej stacji!");
        System.out.println("Aby dodać nową stację podaj następujące parametry.");

    }

    /**
     * The method responsible for displaying the list of stations with
     * measurements or saying that list is empty
     *
     * @param list its a list with all wheather station which type is
     * StationDataBase.
     */
    public void showOutList(StationDataBase list) {
        if (list.getList().isEmpty()) {
            System.out.println("Przykro mi, ale nie ma jeszcze żadnych stacji.");

        } else {
            Comparator<WheatherStation> compareByTown = Comparator.comparing(WheatherStation::getTown);
            for (WheatherStation db : list.getList().stream().sorted(compareByTown).collect(Collectors.toList())) {
                System.out.println("Misto: " + db.getTown());
                System.out.println("Id stacji: " + db.getId());
                System.out.println("Temperatura : " + db.getMeasurement().getTemperature() + " stopni Celsjusza.");
                System.out.println("Wilgotność : " + db.getMeasurement().getHumidity() + "%");
                System.out.println("Ciśnienie: " + db.getMeasurement().getPressure() + " hPa");
                System.out.println("Widoczność: " + db.getMeasurement().getVisibility() + " m");
                System.out.println("Opad atmosferyczny: " + db.getMeasurement().getTheAmountOfPrecipitation() + " mm");
                System.out.println("Promieniowanie słoneczne: " + db.getMeasurement().getSunlight() + " W/m2");
                System.out.println("Poziom smogu:  " + db.getMeasurement().getSmog() + "%");
                System.out.println("");
            }
        }
    }

    /**
     * The method responsible for displaying the list of parameters to change
     *
     */
    public void showParametersToChange() {
        System.out.println("Co chcesz zmienić?");
        System.out.println("Wybierz 1, jeśli wszystko.");
        System.out.println("Wybierz 2, jeśli temperaturę.");
        System.out.println("Wybierz 3, jeśli wilgotnosć.");
        System.out.println("Wybierz 4, jeśli ciśnienie.");
        System.out.println("Wybierz 5, jeśli widocznosc.");
        System.out.println("Wybierz 6, jeśli opad atmosferyczny.");
        System.out.println("Wybierz 7, jeśli promieniowanie słoneczne.");
        System.out.println("Wybierz 8, jeśli poziom smogu.");

    }

    /**
     * The method responsible for displaying text with information that user
     * should chose which parameter want to change
     *
     */
    public void showChangeParametersText() {
        System.out.println("Podaj wartość wybarnego parametru/paramterów.");
    }

    /**
     * The method responsible for displaying text with information that user
     * should chose station where he want to change parameters
     *
     */
    public void showChoseStationText() {
        System.out.println("Wybierz id stacji, której parametry chcesz zmienić");
    }

    /**
     * Method responsible for say goodbye to user when he want to quit this
     * program
     */
    public void sayGoodbyeToUser() {
        System.out.println("Już nas opuszczasz? Szkoda. Do zobaczenia innym razem");
    }

    /**
     * Method responsible for informing user which parameter he should to give
     * to this program
     *
     * @param i it's a position of parameter which user should to give
     */
    public void showParameterToCreateNewStation(int i) {
        String parametersTab[] = {"Miasto", "Temperatura", "Wilgotność", "Ciśnienie ", "Widoczność", "Wysokosć opadów atmosferycznych", "Promieniowanie słoneczne", "Smog"};
        System.out.println("Podaj: " + parametersTab[i]);
    }

    /**
     * Method responsible for show to user communicate with a problem
     *
     * @param message it's a string wiht information what is a problem
     */
    public void showExceptionText(String message) {
        System.out.print("##################\nProgram napotkał problem! \nWiadomość dla użytkownika: " + message + "\n##################\n\n");
    }

    /**
     * Method responsible for showing information that station which user want
     * to create is in base
     */
    public void showStationExistMessage() {
        System.out.println("Stacja w podanym mieście znajduje się już w bazie.");
        System.out.println("Wybierz opcję trzecią i spróbuj zmienić jej parametry.");
    }

    /**
     * Method responsible for show information that station was added to list
     */
    public void showStationAddMessage() {
        System.out.println("Stacja została dodana do listy.");
    }
}
