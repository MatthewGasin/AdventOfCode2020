import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataHelper {
    public static String[] getInput(String delim, int day) {
        String[] data = {};
        try (BufferedReader br = new BufferedReader(new FileReader("data\\Day" + day + ".txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            data = sb.toString().split(delim);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
