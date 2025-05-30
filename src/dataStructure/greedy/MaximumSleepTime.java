package src.dataStructure.greedy;

//codeSignal test jatin has many meeting in the week back to back  find the window where her can sleep maximum time

import java.util.*;

public class MaximumSleepTime {
  public static void main(String[] args) {
      String   schedule =
              "Mon 01:00-23:00\n" +
                      "Tue 01:00-23:00\n" +
                      "Wed 01:00-23:00\n" +
                      "Thu 01:00-23:00\n" +
                      "Fri 01:00-23:00\n" +
                      "Sat 01:00-23:00\n" +
                      "Sun 01:00-21:00";

      Map<String,Integer> map = new HashMap<>();
      map.put("Mon",0);
      map.put("Tue",1);
      map.put("Wed",2);
      map.put("Thu",3);
      map.put("Fri",4);
      map.put("Sat",5);
      map.put("Sun",6);

      String[] days = schedule.split("\n");
      List<int[]> intervals = new ArrayList<>();
      for (String day : days) {
          System.out.println("Day: " + day);
          String dayName = day.split(" ")[0];
          System.out.println("Day Name: " + dayName);
          String[] timeArray = day.split(" ")[1].split("-");
          System.out.println("Time Array: " + Arrays.toString(timeArray));
          int startTime = map.get(dayName) * 1440 + parseToMinutes(timeArray[0]);
          int endTime = map.get(dayName) * 1440 + parseToMinutes(timeArray[1]);
          intervals.add(new int[]{startTime, endTime});
      }

      intervals.sort(Comparator.comparingInt(a -> a[0]));
      int prevTime=0;
      int maxSleepTime = 0;
      for (int[] interval : intervals) {
          int gap = interval[0] - prevTime;
          maxSleepTime = Math.max(maxSleepTime, gap);
          prevTime = Math.max(prevTime, interval[1]);

      }
      // Check last gap to end of week  because jatin can sleep on sunday and wake on monday
      int finalMaxSleepTime = 10080 - prevTime;
      maxSleepTime = Math.max(maxSleepTime, finalMaxSleepTime);
      System.out.println(maxSleepTime);
  }

 static  int parseToMinutes(String s) {
      String[] timeArray = s.split(":");
      return Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
  }

}
