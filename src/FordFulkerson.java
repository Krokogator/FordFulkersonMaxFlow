/**
 * Created by Michał(Krokogator) on 29.11.2017.
 */
import java.util.LinkedList;

class FordFulkerson
{
    private int V;
    private int maxFlow;

    /**
     * Zwraca 'true' gdy znajdzie możliwą ścieżkę (taką o niezerowym przepływie)
     * Wypełnia tablicę path odnalezioną ścieżką
     */
    boolean bfs(int matrix[][], int start, int end, int path[])
    {
        // Lista odwiedzonych wierzchołków (początkowo false, gdyż nie odwiedziliśmy żadnego
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i]=false;

        // Tworzymy kolejkę i oznaczamy początkowy wierzchołek 'start' jako odwiedzony
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        path[start]=-1;

        // Główna pętla
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<V; v++)
            {
                //Jeśli kolejny wierzchołek (sąsiad) nie został odwiedzony i jego przepływ jest większy od 0
                //wówczas dodajemy go do kolejki
                if (visited[v]==false && matrix[u][v] > maxFlow)
                {
                    queue.add(v);
                    path[v] = u;
                    visited[v] = true;
                }
            }
        }

        //Jeśli odwiedziliśmy ostatni wierzchołek oznacza, że znaleźliśmy ścieżkę
        //Zwracamy wtedy 'true'
        return (visited[end] == true);
    }

    // Zwracamy maksymalny przepływ z punktu s do punktu t
    int fordFulkerson(int graph[][], int s, int t)
    {
        this.V = graph.length;
        int u, v;

        int matrix[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                matrix[u][v] = graph[u][v];

        // Przechowujemy tutaj ścieżkę
        int path[] = new int[V];

        int max_flow = 0;

        // Dopóki odnajdujemy przepływ - dodajemy go do naszego sumarycznego przepływu, i aktualizujemy macierz
        while (bfs(matrix, s, t, path))
        {

            for(int i = 0;i<15;i++){
                for(int j = 0;j<15;j++){
                    System.out.print(matrix[i][j]+"; ");
                }
                System.out.print("\n");
            }

            int path_flow = Integer.MAX_VALUE;

            //Sprawdzamy minimalny przepływ na ścieżce
            for (v=t; v!=s; v=path[v])
            {
                u = path[v];
                path_flow = Math.min(path_flow, matrix[u][v]);
            }

            //Pomniejszamy dopuszczalny przepływ na ścieżce
            for (v=t; v != s; v=path[v])
            {
                u = path[v];
                matrix[u][v] -= path_flow;
                //matrix[v][u] += path_flow;
            }

            // Aktualizujemy maksymalny przepływ
            max_flow += path_flow;
            System.out.println("Znaleziony przepływ "+path_flow);
            System.out.println("____________________________________________");
        }

        return max_flow;
    }

    public int findMaxFlow(int graph[][], int s, int t){
        maxFlow = 0;

        this.V = graph.length;
        int u, v;

        int matrix[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                matrix[u][v] = graph[u][v];

        // Przechowujemy tutaj ścieżkę
        int path[] = new int[V];

        // Dopóki odnajdujemy przepływ - dodajemy go do naszego sumarycznego przepływu, i aktualizujemy macierz
        while (bfs(matrix, s, t, path))
        {

            int path_flow = Integer.MAX_VALUE;

            //Sprawdzamy minimalny przepływ na ścieżce
            for (v=t; v!=s; v=path[v])
            {
                u = path[v];
                path_flow = Math.min(path_flow, matrix[u][v]);
            }

            /*
            for(int i=1;i<path.length;i++){ System.out.print(path[i]+", ");}
            System.out.print("\n"+"Flow = "+path_flow+"\nN");
            */

            maxFlow = path_flow;
        }

        return maxFlow;
    }
}