
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
    private String sortedByAttribute;

    public Dataset (ArrayList<Game> data){
        this.data = data;
        this.sortedByAttribute = "notSorted";
    }

    public ArrayList<Game> getGamesByPrice(int price){
        ArrayList<Game> resultado = new ArrayList<>();   //creamos arreglo
        if (this.sortedByAttribute.equals("price")){ //busqueda binaria si está ordenado
            int lo = 0 ;  // auxiliar de inicio
            int hi = data.size() - 1 ;  //auxiliar 2 de inicio

            while (lo <= hi){      // condicion para crear la mitad
                int mid = (lo + hi ) / 2 ;

                if ( data.get(mid).getPrice() == price ) { //Si el juego que está en la posición mid tiene el precio buscado
                    int aux = mid;
                    while (data.get(aux).getPrice() == price){ //ve si hay más juegos para la izquierda con los mismos valores
                        resultado.add(data.get(aux)); //Saqué lo de addFirst o add(0,dato) porque es de complejidad lineal
                        aux --;
                    }
                    aux = mid + 1 ;
                    while(data.get(aux).getPrice() == price){ //ve si hay mas juegos para la derecha con los mismos valores
                        resultado.add( data.get(aux));
                        aux ++;
                    }
                    break;
                }

                else if ( data.get(mid).getPrice() < price ){ lo = mid + 1 ; } //nos movemos a la derecha

                else { hi = mid - 1; } //nos movemos a la izquierda
            }
        }
        else { // En caso de que el arreglo no esté ordenado por precio
            for (Game g : data) {
                if (g.getPrice() == price) {
                    resultado.add(g);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice){
        ArrayList<Game> resultado = new ArrayList<>();
        if (this.sortedByAttribute.equals("price")){
            int lo = 0;
            int hi = data.size() - 1;

            while (lo <= hi){
                int mid = (lo + hi ) / 2 ;

                if ((data.get(mid).getPrice() >= lowerPrice) && (data.get(mid).getPrice() <= higherPrice)){ //Si el precio del juego está dentro del rango, se agrega
                    int aux = mid;
                    while ((data.get(mid).getPrice() >= lowerPrice) && (data.get(mid).getPrice() <= higherPrice)){ //ve si hay más juegos para la izquierda dentro del rango
                        resultado.add(data.get(aux)); //Saqué el addFirst porque es de complejidad lineal
                        aux --;
                    }
                    aux = mid + 1 ;
                    while((data.get(mid).getPrice() >= lowerPrice) && (data.get(mid).getPrice() <= higherPrice)){ //ve si hay mas juegos para la derecha dentro del rango
                        resultado.add( data.get(aux));
                        aux ++;
                    }
                    break;
                }
                else if ( data.get(mid).getPrice() < lowerPrice ){ lo = mid + 1 ; } //nos movemos a la derecha si estamos bajo el rango

                else if ( data.get(mid).getPrice() >higherPrice ) { hi = mid - 1 ; } //nos movemos a izquierda si estamos sobre el rango
            }
        }
        else { // busqueda lineal en caso de que el arreglo no esté ordenado por precio
            for (Game g : data) {
                if ((g.getPrice() >= lowerPrice) && (g.getPrice() <= higherPrice)) {
                    resultado.add(g);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Game> getGamesByCategory(String category){
        ArrayList<Game> resultado = new ArrayList<>();
        if (this.sortedByAttribute.equals("category")){ //busqueda binaria si está ordenado
            int lo = 0 ;  // auxiliar de inicio
            int hi = data.size() - 1 ;  //auxiliar 2 de inicio

            while (lo <= hi){      // condicion para crear la mitad
                int mid = (lo + hi ) / 2 ;
                int comparacion = data.get(mid).getCategory().compareTo(category); //Compara la categoria del juego que estoy viendo con la categoría buscada, -1 si es menor, 0 si es la que busco, 1 si es mayor

                if (comparacion == 0) { //Si la comparación me retorna 0, es porque el juego que estoy viendo es de la categoría buscada
                    int aux = mid;
                    while (data.get(aux).getCategory().equals(category)){ //ve si hay más juegos para la izquierda con la misma categoría
                        resultado.add(data.get(aux));
                        aux --;
                    }
                    aux = mid + 1 ;
                    while(data.get(aux).getCategory().equals(category)){ //ve si hay mas juegos para la derecha con la misma categoría
                        resultado.add( data.get(aux));
                        aux ++;
                    }
                    break;
                }

                else if ( comparacion < 0){ lo = mid + 1 ; } //nos movemos a la derecha

                else { hi = mid - 1; } //nos movemos a la izquierda
            }
        }
        else { // En caso de que el arreglo no esté ordenado por categoría
            for (Game g : data) {
                if (g.getCategory().equals(category)) {
                    resultado.add(g);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Game> getGamesByQuality(int quality){
        ArrayList<Game> resultado = new ArrayList<>();
        if (this.sortedByAttribute.equals("quality")){ //busqueda binaria si está ordenado por calidad
            int lo = 0 ;  // auxiliar de inicio
            int hi = data.size() - 1 ;  //auxiliar 2 de inicio

            while (lo <= hi){      // condicion para crear la mitad
                int mid = (lo + hi ) / 2 ;

                if ( data.get(mid).getQuality() == quality ) { //Si el juego que está en la posición mid tiene la calidad buscada
                    int aux = mid;
                    while (data.get(aux).getQuality() == quality){ //ve si hay más juegos para la izquierda con la misma calidad
                        resultado.add(data.get(aux));
                        aux --;
                    }
                    aux = mid + 1 ;
                    while(data.get(aux).getQuality() == quality){ //ve si hay mas juegos para la derecha con la misma calidad
                        resultado.add( data.get(aux));
                        aux ++;
                    }
                    break;
                }

                else if ( data.get(mid).getQuality() < quality ){ lo = mid + 1 ; } //nos movemos a la derecha

                else { hi = mid - 1; } //nos movemos a la izquierda
            }
        }
        else { // En caso de que el arreglo no esté ordenado por precio
            for (Game g : data) {
                if (g.getQuality() == quality) {
                    resultado.add(g);
                }
            }
        }
        return resultado;
    }

    public void sortByAlgorithm(String algorithm, String attribute){
        Comparator<Game> comparator;
        switch (attribute){
            case "category":
                comparator = Comparator.comparing(Game::getCategory);
                this.sortedByAttribute = attribute; //actualiza sorted y coloca el atributo con el cual se hará el ordenamiento
                break;
            case "quality":
                comparator = Comparator.comparing(Game::getQuality);
                this.sortedByAttribute = attribute; //actualiza sorted y coloca el atributo con el cual se hará el ordenamiento
                break;
            default:
                comparator = Comparator.comparingInt(Game::getPrice);
                this.sortedByAttribute = "price"; //Caso default, en caso de que el parámetro no corresponda ni a "price", "quality" o "category"
                break;
        }
        switch (algorithm){
            case "bubbleSort" :
                bubbleSort(comparator);
                break;

            case "insertionSort" :
                insertionSort(comparator);
                break;

            case "selectionSort" :
                selectionSort(comparator);
                break;

            case "mergeSort" :
                mergeSort(comparator);
                break;

            case "quickSort" :
                quickSort(comparator);
                break;

            default :
                Collections.sort(data, comparator);
                break;
        }
    }
//--------------revisar si hay que poner data.size() - 1 , porque algunas implementaciones del libro, simplemente usa el largo del arreglo como limite
    public void bubbleSort(Comparator<Game> comparator){
        boolean swapped; //bandera para saber si hubo algun swap
        for (int i = 0; i < data.size(); i++){
            swapped = false;
            for (int j = 0; j < data.size() - i - 1; j++){
                if (comparator.compare(data.get(j), data.get(j+1)) > 0){
                    Collections.swap(data, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped){ break ;} //si no se hizo ningun swap, es porque es arreglo ya estaba ordenado, entonces no vale la pena seguir comparando
        }
    }

    public void insertionSort(Comparator<Game> comparator){
        for (int i = 1; i < data.size(); i++){
            for (int j = i; j > 0; j--){
                if (comparator.compare(data.get(j), data.get(j-1)) < 0){
                    Collections.swap(data, j, j-1);//j es menor que el anterior (j-1), hay que intercambiarlo
                }
                else { break; } //si j no es menor que el anterior, significa que todos los demás que estén antes que él son menores, entonces no vale la pena seguir comparando
            }
        }
    }

    public void selectionSort(Comparator<Game> comparator){
        for (int i = 0; i < data.size(); i++){
            int min = i;
            for (int j = i + 1; j < data.size(); j++){
                if (comparator.compare(data.get(j), data.get(min)) < 0){
                    min = j;
                }
            }
            if (min != i){ Collections.swap(data, i, min); } //si al final del ciclo j encontré un mínimo, hago el cambio
        }
    }
    public void mergeSort(Comparator<Game> comparator){

    }
    public void quickSort(Comparator<Game> comparator){ }


}
//------------------------Clase GenerateData-----------------------

class GenerateData{

}

//-----------------------Main------------------------

public class Main {

    public static void main(String[] args) {


    }
}
