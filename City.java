public class City {
    private String cityName;

    public City(String name) {
        this.cityName = name;
    }

    public String getName() {
        return cityName;
    }

    public void setName(String name) {
        this.cityName = name;
    }
    public void diplayCityName(){
        System.out.println("Displaying city name:"+this.cityName);
    }
}