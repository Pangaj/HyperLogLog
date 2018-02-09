import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class AddOneMillionInForHyperLogLog {
    public static void main(String[] args) {
        String dataFileOne = "/home/local/ZOHOCORP/pangaj-6204/Downloads/top1-1m.csv";
        String dataFileTwo = "/home/local/ZOHOCORP/pangaj-6204/Downloads/top2-1m.csv";
        String dataFileThree = "/home/local/ZOHOCORP/pangaj-6204/Downloads/majestic_million.csv";
        String hyperLogLogOne = "hyperLogLogOne";
        String hyperLogLogTwo = "hyperLogLogTwo";
        String hyperLogLogThree = "hyperLogLogThree";
        String hyperLogLogMerge = "hyperLogLogMerge";
        long startTime, endTime;
        float totalTime, timeInSecs;

        BufferedReader bufferedReader = null;
        String line;
        String splitKey = ",";
        Jedis jedis = new Jedis();

        //clear all existing keys from db
        jedis.del(hyperLogLogOne);
        jedis.del(hyperLogLogTwo);
        jedis.del(hyperLogLogMerge);

        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileOne));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogOne, url);
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
        System.out.println("hyperLogLogOne : " + jedis.pfcount(hyperLogLogOne));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileTwo));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogTwo, url);
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
        System.out.println("hyperLogLogTwo : " + jedis.pfcount(hyperLogLogTwo));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileThree));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogThree, url);
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
        System.out.println("hyperLogLogThree : " + jedis.pfcount(hyperLogLogThree));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        jedis.pfmerge(hyperLogLogMerge, hyperLogLogOne, hyperLogLogTwo, hyperLogLogThree);
        System.out.println("hyperLogLogMerge : " + jedis.pfcount(hyperLogLogMerge));
    }
}