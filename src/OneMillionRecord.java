import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OneMillionRecord {
    public static void main(String[] args) {
        // TODO: 9/2/18 Before Build the file, Please update your current file location here
        String dataFile = "/home/local/ZOHOCORP/pangaj-6204/Documents/JavaGit/Redis/HyperLogLog/DataFiles/urlFile1.csv";
        BufferedReader bufferedReader = null;
        String line;
        String splitKey = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(dataFile));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String[] url = line.split(splitKey);
                System.out.println(url[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
