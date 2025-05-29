
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

                if ( data.get(mid).getPrice() == price) { //Si el juego que está en la posición mid tiene el precio buscado
                    int aux = mid;
                    while (data.get(aux).getPrice() == price && aux >= 0){ //ve si hay más juegos para la izquierda con los mismos valores
                        resultado.add(data.get(aux)); //Saqué lo de addFirst o add(0,dato) porque es de complejidad lineal
                        aux --;
                    }
                    aux = mid + 1 ;
                    while(data.get(aux).getPrice() == price && aux < data.size()){ //ve si hay mas juegos para la derecha con los mismos valores
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

    //----------------------- Ordenamiento de data -------------------------
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

    //----------------------- insertionSort ------------------------

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

    //------------------------- selectionSort--------------------------
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

    //------------------------------ mergeSort ----------------------------
    public void mergeSort(Comparator<Game> comparator){ //método que llama a metodo recursivo interno
        data = sort(data, comparator);
    }
    private ArrayList<Game> sort (ArrayList<Game> lista, Comparator<Game> comparator){
        if (lista.size() <= 1) { return lista; } //cuando la lista no se pueda dividir más

        int mid = lista.size() / 2;
        ArrayList<Game> izq = new ArrayList<>(lista.subList(0, mid)); //separa la lista en 2 partes
        ArrayList<Game> der = new ArrayList<>(lista.subList(mid, lista.size()));

        izq = sort(izq, comparator); //se llama a si misma para volver a separar cada lista resultante hasta que no se pueda separar más
        der = sort(der, comparator);

        return merge(izq, der, comparator);//llama a método que unirá las listas en orden
    }
    private ArrayList<Game> merge(ArrayList<Game> izq, ArrayList<Game> der, Comparator<Game> comparator) {
        ArrayList<Game> resultado = new ArrayList<>();
        int i = 0, j = 0;
        while (i < izq.size() && j < der.size()) {//mientras no llegue al final de alguna lista
            if (comparator.compare(izq.get(i), der.get(j)) <= 0) { //si el atributo del juego que está en mi lista izquierda es "menor" o igual que atrbuto del juego de la lista derecha
                resultado.add(izq.get(i++));
            }
            else { resultado.add(der.get(j++)); } //si el atributo del juego que está en la lista izquierda es "mayor" que el atributo del juego que está a en la lista derecha
        }
        while (i < izq.size()) { resultado.add(izq.get(i++)); } //cuando haya llegado al final de la lista derecha y aún me quedan elementos de la lista izquierda
        while (j < der.size()) { resultado.add(der.get(j++)); } //cuando haya llegado al final de la lista izquierda y aún me quedan elementos de la lista derecha
        return resultado;
    }


    //--------------------------- quickSort ----------------------------
    public void quickSort(Comparator<Game> comparator){
        sort(0, data.size() - 1, comparator);
    }
    private void sort(int lo, int hi, Comparator<Game> comparator){ //metodo recursivo
        if (lo < hi){
            int pivoteIndex = partition(lo, hi, comparator); //posición retornada donde se encuentra el pivote, o sea, donde los juegos tienen valores menores (izquierda del pivote) y donde tienen mayores valores (derecha del pivote)
            sort(lo, pivoteIndex - 1, comparator); //mueve todos los juegos cuyos atributos sean "menores" o iguales al atributo del juego al que apunta el pivote
            sort(pivoteIndex + 1, hi, comparator); //mueve todos los juegos cuyos atributos sean "mayores" al atributo del juego al que apunta el pivote
        }
    }
    private int partition (int lo, int hi, Comparator<Game> comparator){
        Game pivote = data.get(hi); //se elige un pivote (al comienzo es el ultimo juego de la lista, luego uno de valor mayor)
        int i = lo-1; //será el indice de los juegos cuyo atributo tenga un valor "menor" o igual al juego apuntado por el pivote
        for (int j = lo; j < hi; j++){ //recorre los juegos desde el más bajo hasta el final
            if (comparator.compare(data.get(j), pivote) <= 0){ //si el atributo del juego que está en la posicion j (baja) de la lista es "menor" o igual que el último de la lista
                i++;
                Collections.swap(data, i, j); //mueve el juego en j a la posicion anterior (a la izquierda)
            } //cuando haya un juego mayor al juego del pivote, i no se actualiza, pero sí j, entonces cuando se encuentre un juego menor al del pivote, se cambiará i (juego mayor a pivote) con j (juego menor a pivote), dejando los juegos menores a la izquierda
        }
        Collections.swap(data, i + 1, hi); //detrás de i están todos los juegos menores al juego del pivote
        // entonces se cambia el juego que está después de i (mayor que el pivote) con el pivote, dejando así al pivote donde corresponde (antes que i, el cual representa los juegos mayores que el pivote)
        return i + 1; //retorna a pivoteIndex la posición de los juegos cuyo atributo es menor que el atributo del juego en el pivote
    }
}

//------------------------Clase GenerateData-----------------------

class GenerateData {

    private static final Random random = new Random();// primero para generar palabras

    private static String randomId(int length)
//ns como seguirlo

    public static ArrayList<Game> generateGames(int amount) { //para general los juegos
        ArrayList<Game> games = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            String name = "Game" + randomId(5);        // generar juego con letras randoms como nombre
            String category = "Accion" + randomId(4);     //  categoria con letras randoms como nombre
            int price = 1 + random.nextInt(1000);      // precio desde hasta 1000
            int quality = 1 + random.nextInt(10); // calidad desde 1 a 10
        }

//-----------------------Main------------------------

        public class Main {

            public static void main(String[] args) {
                int[] testSizes = {10, 100, 1_000, 10_000, 100_000, 1_000_000}; // crear arreglo con las cantidades que queremos de juegos

                for (int size : testSizes) {
                    System.out.println("Generando " + size + " juegos...");  // luego recorremos el arreglo
                    long start = System.currentTimeMillis();

                    ArrayList<Game> games = GenerateData.generateGames(size);
//ns como seguirlo //
                }
            }
        }
    }
}