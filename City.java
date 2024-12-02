public class City {
    private  String cityName;

    public City(){
        cityName="unkown";
    }
    public City(String cityName){
        this.cityName=cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void diplayCityName(){
        System.out.println("Displaying city name:"+this.cityName);
    }
}