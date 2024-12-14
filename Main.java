import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to SE115 Maps!");

        // dosyayı okutmak için path sorması için
        System.out.print("Enter the input file's path: ");
        String filePath = sc.nextLine();

        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
            int lineNumber = 1;

            // şehir isimlerini okuması için
            int cityCount;
            try {
                cityCount = Integer.parseInt(fileScanner.nextLine().trim());
                lineNumber++;
            } catch (NumberFormatException e) {
                System.out.println("Error on line " + lineNumber + ". Wrong number format for the number of cities.");
                return;
            }

            String[] cities = new String[cityCount];

            // şehir isimlerini okuması için
            String cityLine = fileScanner.nextLine().trim();
            lineNumber++;
            String[] cityNames = cityLine.split(" ");
            if (cityNames.length != cityCount) {
                System.out.println("Error on line " + lineNumber + ". Number of city names does not match the number in the text.");
                return;
            }
            System.arraycopy(cityNames, 0, cities, 0, cityCount);

            WayFinder wayFinder = new WayFinder(cityCount, cities);

            // yol sayısını tanımlamak için
            int routeCount;
            try {
                routeCount = Integer.parseInt(fileScanner.nextLine().trim());
                lineNumber++;
            } catch (NumberFormatException e) {
                System.out.println("Error on line " + lineNumber + ". Wrong number format for the number of routes.");
                return;
            }

            for (int i = 0; i < routeCount; i++) {
                String[] routeData = fileScanner.nextLine().trim().split(" ");
                lineNumber++;
                if (routeData.length != 3) {
                    System.out.println("Error on line " + lineNumber + ". Route data must have 3 values.");
                    return;
                }
                try {
                    String city1 = routeData[0];
                    String city2 = routeData[1];
                    int time = Integer.parseInt(routeData[2]);
                    wayFinder.addRoute(city1, city2, time);
                } catch (NumberFormatException e) {
                    System.out.println("Error on line " + lineNumber + ". Time must be an integer.");
                    return;
                }
            }

            // ilk ve son şehri okutmak için
            String[] startEndCities = fileScanner.nextLine().trim().split(" ");
            lineNumber++;
            if (startEndCities.length != 2) {
                System.out.println("Error on line " + lineNumber + ". You must write start and end cities in tje text.");
                return;
            }
            String startCity = startEndCities[0];
            String endCity = startEndCities[1];

            // en hızlı yolu bulmak için
            String fastestRoute = wayFinder.findFastestRoute(startCity, endCity);
            int totalTime = wayFinder.getRouteTime(startCity, endCity);

            // sonuçları göstermek için
            if (fastestRoute.equals("No route found.") || fastestRoute.equals("Invalid city names.")) {
                System.out.println(fastestRoute);
            } else {
                System.out.println("Fastest Way: " + fastestRoute);
                System.out.println("Total Time: " + totalTime + " min");
            }

        } catch (IOException e) {
            System.out.println("Error! Can't find the file. Please check the file's name and it's path.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
        }
    }
}