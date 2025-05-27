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
       Arraylist<Game> resultado = newArraylist<>();   //creamos arreglo
        if (this.sortedByAtribute.equals("price")){
        int left = 0 ;  // auxiliar de inicio
        int right = Data.size() - 1 ;  //auxiliar 2 de inicio
            while (left <= right){      // condicion para crear la mitad 
                int mid = (left + right ) / 2 ; 
                int midPrice = data.get(mid).getPrice();  // valor del medio 
                    if ( midPrice == price ) {
                        int i = mid;
                            while ( i >= 0 && data.get(i).getPrice() == price ){ //ver si hayy mas juegos para la izquierda con los mismos valores 
                        result.add(0 , data.get(i));
                        i --;
                    }
                     int i = mid + 1 ; 
                        while( i < data.size() && data.get(i).getPrice() == price ){ //ver si hayy mas juegos para la derecha con los mismos valores 
                            result.add( data.get(i));
                            i ++;
                        }  
                        break; 
                    }
                else if ( midPirce < price ){
                    left = mid + 1 ;
                    
                }
                else {
                    right = mid - 1;
                }
            } 
        } 
        else // lineal 
            for(game g : data){
                if(g.getPrice == price){
                    result.add(g);
                }
            }  
        return result;            
    }
       

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice){
    Arraylist<Game> resultado = newArraylist<>();
         if (sortedByAtribute.equals("price")) {
        // Búsqueda binaria para el límite inferior
        int left = 0, right = data.size() - 1;
        int start = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midPrice = data.get(mid).getPrice();

            if (midPrice >= lowerPrice) {
                start = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (start != -1) {
            for (int i = start; i < data.size(); i++) {
                int price = data.get(i).getPrice();
                if (price > higherPrice) break;
                result.add(data.get(i));
            }
        }

    } else {
        for (Game g : data) {
            int price = g.getPrice();
            if (price >= lowerPrice && price <= higherPrice) {
                result.add(g);
            }
        }
    }

    return result;
}



        
    public ArrayList<Game> getGamesByCategory(String category){
Arraylist<game> resultado = newArraylist<>();
        if(thissortedByAtribute.equals("category")){
            int left  = 0;
                int right = data.size() - 1 ;
            while(left <= right){
                int mid = (left + right) / 2 ;
                String midCategory = data.get(mid).getCategory(); 
                int comparacion = midCategory.compareTo(category);
                if( comparacion == 0 ) {
                    int i = mid ;
                    while( i >= data.get(i).getCategory().equals(category)){
                        resultado.add(0 , data.get(i));
                        i--;
                }
                    i = mid + 1;
                    while( i < data.size() && data.get(i).getCategory().equals(category)){
                    resultado.add(o , data.get(i))
                    i++;    
                    }
                    break;
        }
                else if (comparacion < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
            else{
            for(game g : data){
                if(g.getCategory().equals("category")){
                    resultado.add(g);
                }
            }

                
    }
      return resultado;
    }





        
    public ArrayList<Game> getGamesByQuality(int quality){
       Arraylist<Game> resultado = nesArraylist<>();
        if(this.sortedByAtribute.equals("quality")){
            int left = 0 ;
            int right = data.size() - 1 ;
            while(left < right ){
                int mid = (left + right ) /2 ;
                    String midQuality = data.get(mid).getQuality();
                        int comparacion = midQuality.comparteTo(quality);
                        if(comparacion == 0 ){
                            int i = mid ;
                        }
            }
                            while(i >= 0 && data.get(i).getQuality().equals(quality)){
                               resultado.add(0 , data.get(i));
                                i--;
                            }
                            i = mid + 1 ;
                            while(i < data.size() && data.get(i).getQuality().equals(quality) ){
                                resultado.add(0 , data.get(i));
                                i++;
                                
                            }
                               break; 
                        
                        }
                          else if (comparacion < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }    


        else{
           for(Game g : data){
               if(g.getQuality().equals("quality"){
                   resultado.add(g);
        }
               
           }
        }
        return resultado;
    }

    
  public void sortByAlgorithm(String algorithm, String attribute) {
    Comparator<Game> comparator;

    // Seleccionar el atributo por el cual se ordenará
    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        case "price":
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            attribute = "price"; // por defecto
            break;
    }

    // Ordenar usando el algoritmo especificado
    switch (algorithm) {
        case "bubbleSort":
            bubbleSort(comparator);
            break;
        case "insertionSort":
            insertionSort(comparator);
            break;
        case "selectionSort":
            selectionSort(comparator);
            break;
        case "mergeSort":
            data = mergeSort(data, comparator);
            break;
        case "quickSort":
            quickSort(0, data.size() - 1, comparator);
            break;
        default:
            Collections.sort(data, comparator);
            break;
    }

    this.sortedByAtribute = attribute;
}
//------------------------Clase GenerateData-----------------------
class GenerateData{

}

//-----------------------Main------------------------

public class Main {

    public static void main(String[] args) {


    }
}
