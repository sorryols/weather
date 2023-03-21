package com.example.weather;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class HelloController {
    @FXML
    private Button button_showweather;

    @FXML
    private Label label_feellike;

    @FXML
    private Label label_max;

    @FXML
    private Label label_min;

    @FXML
    private Label label_temp;

    @FXML
    private TextField textfiield_city;

    @FXML
    void initialize() {
        button_showweather.setOnAction(actionEvent -> {
            String getUserCity = textfiield_city.getText().trim();
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&APPID=86e7f5dd554422305543d4d0cb19b615&units=metric");

            change_label(output);
        });
    }

        void change_label(String output){
        if(!output.isEmpty()){
            JSONObject object = new JSONObject(output);
            label_temp.setText("Temperatura: " + object.getJSONObject("main").getDouble("temp"));
            label_feellike.setText("Feel`s like: : " + object.getJSONObject("main").getDouble("feels_like"));
            label_max.setText("Max: " + object.getJSONObject("main").getDouble("temp_max"));
            label_min.setText("Min: " + object.getJSONObject("main").getDouble("temp_min"));
        }
    }
    private static String getUrlContent(String URLAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(URLAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;


            while((line = bufferedReader.readLine())!=null){
                content.append(line +"\n");
            }
            bufferedReader.close();
        }catch(Exception ex) {
            System.out.println("Incorect city");
        }
        return content.toString();
    }
}