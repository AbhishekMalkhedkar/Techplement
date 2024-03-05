package com.dx;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

    public class App {
        public static void main(String[] args) throws IOException {
            HashMap<Integer, String> currency = new HashMap<Integer, String>();

            currency.put(1, "USD");
            currency.put(2, "CAD");
            currency.put(3, "EUR");
            currency.put(4, "HKD");
            currency.put(5, "INR");

            String from;


            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to Currency Converter!");
            System.out.println("Which Currency to Convert?");
            System.out.println("1: USD  \t 2: CAD \t 3: EUR \t 4: HKD \t 5: INR");
            from = currency.get(sc.nextInt());

            sendHttpGETRequest(from);

            System.out.println("Thank you for using the currency converter");

        }

        private static void sendHttpGETRequest(String from) throws IOException {
            String GET_URL = "https://api.currencyapi.com/v3/latest?apikey=cur_live_61VizBRailDa3lv3UWszZb8A9cEleHBLzhvb0Ys0&currencies=USD%2CCAD%2CEUR%2CHKD%2CINR&base_currency=" + from;
            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject data = jsonObject.getJSONObject("data");

                // Extract exchange rates
                double usdRate = data.getJSONObject("USD").getDouble("value");
                double cadRate = data.getJSONObject("CAD").getDouble("value");
                double eurRate = data.getJSONObject("EUR").getDouble("value");
                double hkdRate = data.getJSONObject("HKD").getDouble("value");
                double inrRate = data.getJSONObject("INR").getDouble("value");


                switch (from){
                    case "USD":
                        System.out.println("Exchange Rates:");
                        System.out.println("CAD: " + cadRate);
                        System.out.println("EUR: " + eurRate);
                        System.out.println("HKD: " + hkdRate);
                        System.out.println("INR: " + inrRate);
                        break;
                    case "CAD":
                        System.out.println("Exchange Rates:");
                        System.out.println("USD: " + usdRate);
                        System.out.println("EUR: " + eurRate);
                        System.out.println("HKD: " + hkdRate);
                        System.out.println("INR: " + inrRate);
                        break;
                    case "EUR":
                        System.out.println("Exchange Rates:");
                        System.out.println("USD: " + usdRate);
                        System.out.println("CAD: " + cadRate);
                        System.out.println("HKD: " + hkdRate);
                        System.out.println("INR: " + inrRate);
                        break;
                    case "HKD":
                        System.out.println("Exchange Rates:");
                        System.out.println("USD: " + usdRate);
                        System.out.println("CAD: " + cadRate);
                        System.out.println("EUR: " + eurRate);
                        System.out.println("INR: " + inrRate);
                        break;
                    case "INR":
                        System.out.println("Exchange Rates:");
                        System.out.println("USD: " + usdRate);
                        System.out.println("CAD: " + cadRate);
                        System.out.println("EUR: " + eurRate);
                        System.out.println("HKD: " + hkdRate);
                        break;
                }
            }
        }
    }


