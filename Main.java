import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to SE115 Maps!");
        System.out.println("Please enter the formatted text:");
        String text= sc.nextLine();
        System.out.println();
        String[] pieces=text.split(" ");
        int trial=Integer.parseInt(pieces[0]);
        System.out.println(trial);
        System.out.println(trial+12);
        City city_1=new City(pieces[1]);
        CountryMap map=new CountryMap("SE115 MAP");
        map.addCity(city_1);
        city_1.diplayCityName();


    }
}
