import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class AddOneMillionInForHyperLogLog {
    public static void main(String[] args) {
        String dataFileOne = "/home/local/ZOHOCORP/pangaj-6204/Documents/JavaGit/Redis/HyperLogLog/DataFiles/urlFile1.csv";
        String dataFileTwo = "/home/local/ZOHOCORP/pangaj-6204/Documents/JavaGit/Redis/HyperLogLog/DataFiles/urlFile2.csv";
        String dataFileThree = "/home/local/ZOHOCORP/pangaj-6204/Documents/JavaGit/Redis/HyperLogLog/DataFiles/urlFile3.csv";
        String hyperLogLogOne = "hyperLogLogOne";
        String hyperLogLogTwo = "hyperLogLogTwo";
        String hyperLogLogThree = "hyperLogLogThree";
        String hyperLogLogMerge = "hyperLogLogMerge";
        long startTime, endTime;
        float totalTime, timeInSecs;
        int totalCount;

        BufferedReader bufferedReader = null;
        String line;
        String splitKey = ",";
        Jedis jedis = new Jedis();

        //clear all existing keys from db
        jedis.del(hyperLogLogOne);
        jedis.del(hyperLogLogTwo);
        jedis.del(hyperLogLogMerge);

        totalCount = 0;
        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileOne));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogOne, url);
                totalCount++;
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
        System.out.println("HyperLogLogOne Total : " + totalCount);
        System.out.println("HyperLogLogOne PFCount : " + jedis.pfcount(hyperLogLogOne));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        totalCount = 0;
        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileTwo));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogTwo, url);
                totalCount++;
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
        System.out.println("HyperLogLogTwo Total : " + totalCount);
        System.out.println("HyperLogLogTwo PFCount : " + jedis.pfcount(hyperLogLogTwo));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        totalCount = 0;
        startTime = new Date().getTime();
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFileThree));
            while ((line = bufferedReader.readLine()) != null) {
                // separate using comma - we need only url which is present @ 2nd data. so we are using [1] for split function
                String url = line.split(splitKey)[1];
                jedis.pfadd(hyperLogLogThree, url);
                totalCount++;
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
        System.out.println("HyperLogLogThree Total : " + totalCount);
        System.out.println("HyperLogLogThree PFCount : " + jedis.pfcount(hyperLogLogThree));
        endTime = new Date().getTime();
        totalTime = endTime - startTime;
        timeInSecs = totalTime / 1000;
        System.out.println("Total time : " + timeInSecs + " secs, or : " + totalTime + " mSecs");
        System.out.println();

        jedis.pfmerge(hyperLogLogMerge, hyperLogLogOne, hyperLogLogTwo, hyperLogLogThree);
        System.out.println("HyperLogLogMerge : " + jedis.pfcount(hyperLogLogMerge));
    }
}