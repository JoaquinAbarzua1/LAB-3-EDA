import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

//-----------------------Clase Game-----------------------
class Game{
    private String name;
    private String category;
    private int price;
    private int quality;

    public Game(String name, String category, int price, int quality){
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }
    //Métodos Getters
    public String getName(){ return this.name; }
    public String getCategory(){ return this.category; }
    public int getPrice(){ return this.price; }
    public int getQuality(){ return this.quality; }

    //Creo que no son necesarios los sets, pero los dejaré por si acaso, después se borran en caso de no usarse

    public void setName(String name){ this.name = name; }
    public void setCategory(String category){ this.category = category; }
    public void setPrice(int price){ this.price = price; }
    public void setQuality(int quality){ this.quality = quality; }

}
//-----------------------Clase Dataset-----------------------

class Dataset{
    private ArrayList<Game> data;
    private String sortedByAtribute;

    public Dataset (ArrayList<Game> data){
        this.data = data;
        this.sortedByAtribute = "ninguno";
    }



    public ArrayList<Game> getGamesByPrice(int price){}

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice){} //No sé qué tipo de dato debe ir, lo dejo así por mientras

    public ArrayList<Game> getGamesByCategory(String category){}

    public ArrayList<Game> getGamesByQuality(int quality){}

    public void sortByAlgorithm(String algorithm, String attribute){}

}
//------------------------Clase GenerateData-----------------------
class GenerateData{

}

//-----------------------Main------------------------

public class Main {

    public static void main(String[] args) {


    }
}