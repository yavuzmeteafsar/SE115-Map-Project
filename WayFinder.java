public class WayFinder {
    private String[] cities;
    private int[][] matrix;
    private int cityCount;


    public static int getInfinity() {
        return 999999; // yollar arası bağlantı olmadığını ifade ederken kullandım
    }

    public WayFinder(int cityCount, String[] cities) {
        this.cityCount = cityCount;
        this.cities = cities;
        this.matrix = new int[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                matrix[i][j] = (i == j) ? 0 : getInfinity();
            }
        }
    }

    public void addRoute(String city1, String city2, int time) {
        int index1 = getCityIndex(city1);
        int index2 = getCityIndex(city2);
        if (index1 != -1 && index2 != -1) {
            matrix[index1][index2] = time;
            matrix[index2][index1] = time;
        }
    }

    public String findFastestRoute(String startCity, String endCity) {
        int startIndex = getCityIndex(startCity);
        int endIndex = getCityIndex(endCity);

        if (startIndex == -1 || endIndex == -1) {
            return "Invalid city names.";
        }

        boolean[] visited = new boolean[cityCount];
        int[] distances = new int[cityCount];
        int[] previous = new int[cityCount];

        for (int i = 0; i < cityCount; i++) {
            distances[i] = getInfinity();
            previous[i] = -1;
        }
        distances[startIndex] = 0;

        for (int i = 0; i < cityCount; i++) {
            int minDistance = getInfinity();
            int currentCity = -1;

            for (int j = 0; j < cityCount; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    currentCity = j;
                }
            }

            if (currentCity == -1) break;
            visited[currentCity] = true;

            for (int j = 0; j < cityCount; j++) {
                if (matrix[currentCity][j] != getInfinity() && !visited[j]) {
                    int newDistance = distances[currentCity] + matrix[currentCity][j];
                    if (newDistance < distances[j]) {
                        distances[j] = newDistance;
                        previous[j] = currentCity;
                    }
                }
            }
        }

        if (distances[endIndex] == getInfinity()) {
            return "No route found.";
        }

        return constructPath(previous, endIndex);
    }

    public int getRouteTime(String startCity, String endCity) {
        int startIndex = getCityIndex(startCity);
        int endIndex = getCityIndex(endCity);

        if (startIndex == -1 || endIndex == -1) {
            return getInfinity();
        }

        boolean[] visited = new boolean[cityCount];
        int[] distances = new int[cityCount];

        for (int i = 0; i < cityCount; i++) {
            distances[i] = getInfinity();
        }
        distances[startIndex] = 0;

        for (int i = 0; i < cityCount; i++) {
            int minDistance = getInfinity();
            int currentCity = -1;

            for (int j = 0; j < cityCount; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    currentCity = j;
                }
            }

            if (currentCity == -1) break;
            visited[currentCity] = true;

            for (int j = 0; j < cityCount; j++) {
                if (matrix[currentCity][j] != getInfinity() && !visited[j]) {
                    int newDistance = distances[currentCity] + matrix[currentCity][j];
                    if (newDistance < distances[j]) {
                        distances[j] = newDistance;
                    }
                }
            }
        }

        return distances[endIndex];
    }

    private int getCityIndex(String city) {
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equals(city)) return i;
        }
        return -1;
    }

    private String constructPath(int[] previous, int current) {
        if (previous[current] == -1) {
            return cities[current];
        }
        return constructPath(previous, previous[current]) + " -> " + cities[current];
    }
}