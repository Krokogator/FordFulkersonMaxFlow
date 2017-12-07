import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int graph[][] =new int[][] {
                { 0, 8, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 8, 0, 8,10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 5, 8, 0,11, 4,10, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0,10,11, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 4, 5, 0,16, 0, 0, 0, 5, 0, 4, 0, 0, 0},
                { 5, 0,10, 0,16, 0,10, 5, 8, 5, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0,10, 0, 5, 8, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 5, 5, 0,11, 0,16, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 8, 8,11, 0,11,10, 5, 4, 0, 0},
                { 0, 0, 0, 0, 5, 5, 0, 0,11, 0,12, 5, 8, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0,16,10,12, 0, 0, 5, 0,16},
                { 0, 0, 0, 0, 4, 0, 0, 0, 5, 5, 0, 0, 9, 4, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 4, 8, 5, 9, 0, 8, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 8, 0,10},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,16, 0, 0,10, 0}
        };
        String path = "";
        FordFulkerson m = new FordFulkerson();
        CSVReader csvReader = new CSVReader();

        Scanner reader = new Scanner(System.in);
        System.out.println("Wczytac z pliku? (Y/N)");
        String answer = reader.next();
        switch (answer){
            case "Y":
                System.out.println("Podaj ścieżkę do pliku: ");
                path = reader.next();
                graph = csvReader.getMatrix(path);
                System.out.println("a) Największy możliwy przepływ wynosi: " +
                        m.fordFulkerson(graph, 0, graph.length-1));
                System.out.println("b) Największy możliwy przepływ bez przeładowywania: " +
                        m.findMaxFlow(graph, 0, graph.length-1));

            break;
            case "N":
                System.out.println("a) Największy możliwy przepływ wynosi: " +
                m.fordFulkerson(graph, 0, graph.length-1));
                System.out.println("b) Największy możliwy przepływ bez przeładowywania: " +
                m.findMaxFlow(graph, 0, graph.length-1));
            break;
        }
        reader.close();

    }
}
