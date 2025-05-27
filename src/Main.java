import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random; //para la generacion de nombres aleatorios

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
        this.sortedByAtribute = "unSorted";
    }



    public ArrayList<Game> getGamesByPrice(int price){
        //¿crear arreglo para guardar juegos con este valor?
        if (this.sortedByAtribute.equals("price")){
            //algoritmo búsqueda binaria
        }
        else{
            //busqueda lineal
        }
    }

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice){
        //¿crear arreglo para guardar juegos en este rango de precios?
        if (this.sortedByAtribute.equals("price")){
            //otro algoritmo de busqueda binaria
        }
        else{
            //busqueda lineal
        }
    } //No sé qué tipo de dato debe ir, lo dejo así por mientras

    public ArrayList<Game> getGamesByCategory(String category){
        //¿crear arreglo para guardar juegos con esta categoria?
        if (this.sortedByAtribute.equals("category")){
            //busqueda binaria
        }
        else{
            //busqueda lineal
        }
    }

    public ArrayList<Game> getGamesByQuality(int quality){
        //¿crear arreglo para guardar juegos que tengan esta nota?
        if (this.sortedByAtribute.equals("quality")){
            //busqueda binaria
        }
        else{
            //busqueda lineal
        }
    }

    public void sortByAlgorithm(String algorithm, String attribute){
        this.sortedByAtribute = attribute;
        //se podría hacer de 2 formas, con "case : " o con if
        //con "case : " sería más ordenado, porque se puede escribir cada algoritmo fuera de este metodo y de forma ordenada
    }

}
//------------------------Clase GenerateData-----------------------
class GenerateData{

}

//-----------------------Main------------------------

public class Main {

    public static void main(String[] args) {


    }
}