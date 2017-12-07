import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał(Krokogator) on 29.11.2017.
 */
public class CSVReader {
    private String splitter = ",";

    public CSVReader(){}

    public int[][] getMatrix(String path) {
        List<String[]> list = new ArrayList();
        String fName = path;
        String thisLine;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataInputStream myInput = new DataInputStream(fis);

        try {
            while ((thisLine = myInput.readLine()) != null) {
                list.add(thisLine.split(splitter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int size = list.size();
        int matrix[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.valueOf(list.get(i)[j]);
                //System.out.print(matrix[i][j] + "; ");
            }
            //System.out.print("\n");
        }

        return matrix;
    }
}
