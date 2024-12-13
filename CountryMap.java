public class CountryMap {
    private String name;
    private City[] cities;
    private int cityCount;

    public CountryMap(String name, int maxCities) {
        this.name = name;
        this.cities = new City[maxCities];
        this.cityCount = 0;
    }

    public void addCity(City city) {
        if (cityCount < cities.length) {
            cities[cityCount++] = city;
        } else {
            System.out.println("City limit reached, can't add more cities.");
        }
    }

    public City[] getCities() {
        return cities;
    }

    public int getCityCount() {
        return cityCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}