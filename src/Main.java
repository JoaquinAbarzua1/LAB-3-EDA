
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
        if (!(attribute.equals("price")||attribute.equals("category")||attribute.equals("quality"))){
            attribute = "price";} //Si el atributo no es ninguno de estos 3, "price" será el atributo por defecto

        this.sortedByAttribute = attribute; //se actualiza el atributo por el cual está ordenada la lista
        switch (algorithm){
            case "bubbleSort" :
                bubbleSort(attribute);
                break;
            case "insertionSort" :
                insertionSort(attribute);
                break;
            case "selectionSort" :
                selectionSort(attribute);
                break;
            case "mergeSort" :
                mergeSort(attribute);
                break;
            case "quickSort" :
                quickSort(attribute);
                break;
            default :
                switch (attribute){ //No sé cómo explicarlo, pero Comparator.comparing genera un comparador, con el cual se ordenará el arreglo
                    case "category" :
                        Collections.sort(data, Comparator.comparing(Game::getCategory));
                        break;
                    case "quality" :
                        Collections.sort(data, Comparator.comparingInt(Game::getQuality));
                        break;
                    default :
                        Collections.sort(data, Comparator.comparingInt(Game::getPrice));
                        break;
                }
        }
    }

    public void bubbleSort(String attribute){
         Comparator<Game> comparator;  // comparar y sus condiciones 

    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
    }
// metodo burbuja 
    for (int i = 0; i < data.size() - 1; i++) {
        for (int j = 0; j < data.size() - i - 1; j++) {
            if (comparator.compare(data.get(j), data.get(j + 1)) > 0) {
                Collections.swap(data, j, j + 1);
            }
        }
    }
    }
    public void insertionSort(String attribute){ 
        Comparator<Game> comparator;// comparar y sus condiciones
 

    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
    }
// metodo insertion
    for (int i = 1; i < data.size(); i++) {
        Game key = data.get(i);
        int j = i - 1;

        while (j >= 0 && comparator.compare(data.get(j), key) > 0) {
            data.set(j + 1, data.get(j));
            j--;
        }
        data.set(j + 1, key);
    }
}
    public void selectionSort(String attribute){
         Comparator<Game> comparator;// comparar y sus condiciones

    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
    }
// metodo selection
    for (int i = 0; i < data.size() - 1; i++) {
        int minIndex = i;

        for (int j = i + 1; j < data.size(); j++) {
            if (comparator.compare(data.get(j), data.get(minIndex)) < 0) {
                minIndex = j;
            }
        }

        Collections.swap(data, i, minIndex);
    }
    }
    public void mergeSort(String attribute){ 
        // comparar 
        Comparator<Game> comparator;

    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
    }
//metodo de ordenamiento merge sort
    data = mergeSortHelper(data, comparator);
}

private ArrayList<Game> mergeSortHelper(ArrayList<Game> list, Comparator<Game> comparator) {
    if (list.size() <= 1) return list;

    int mid = list.size() / 2;
    ArrayList<Game> left = new ArrayList<>(list.subList(0, mid));
    ArrayList<Game> right = new ArrayList<>(list.subList(mid, list.size()));

    return merge(mergeSortHelper(left, comparator), mergeSortHelper(right, comparator), comparator);
}

private ArrayList<Game> merge(ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comparator) {
    ArrayList<Game> result = new ArrayList<>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        if (comparator.compare(left.get(i), right.get(j)) <= 0) {
            result.add(left.get(i++));
        } else {
            result.add(right.get(j++));
        }
    }

    while (i < left.size()) result.add(left.get(i++));
    while (j < right.size()) result.add(right.get(j++));

    return result;}
    public void quickSort(String attribute){
        //comparar 
        Comparator<Game> comparator;

    switch (attribute) {
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
    }
// metodo quicksort 
    quickSortHelper(0, data.size() - 1, comparator);
}

private void quickSortHelper(int low, int high, Comparator<Game> comparator) {
    if (low < high) {
        int pi = partition(low, high, comparator);
        quickSortHelper(low, pi - 1, comparator);
        quickSortHelper(pi + 1, high, comparator);
    }
}

private int partition(int low, int high, Comparator<Game> comparator) {
    Game pivot = data.get(high);
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (comparator.compare(data.get(j), pivot) <= 0) {
            i++;
            Collections.swap(data, i, j);
        }
    }

    Collections.swap(data, i + 1, high);
    return i + 1;
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
