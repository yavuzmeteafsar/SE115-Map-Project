import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class CountryMap {
    private String mapName;
    private List<City> citys;

    public CountryMap(String mapName){
        this.mapName= mapName;
        this.citys= new ArrayList<>();
    }

    public void addCity(City city){
        citys.add(city);
        System.out.println("City added: "+city.getCityName());
    }

}