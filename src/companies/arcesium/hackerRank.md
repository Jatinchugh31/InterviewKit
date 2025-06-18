import java.io.*;
import java.math.*;
import java.net.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import com.google.gson.*;;


class Result {

    /*
     * Complete the 'evaluate' function below.
     *
     * The function is expected to return output as STRING_ARRAY.
     * The function accepts STRING api as parameter.
     */

    public static List<String> evaluate(String api) {
       // Write your code here
              List<String> res = new ArrayList<>();
    Group[] groups=null;
       try{
       URL url = new URL(api);
       BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
       StringBuilder jStringBuilder = new StringBuilder();
       String line;
       while((line = in.readLine()) != null){
        jStringBuilder.append(line);
       }
       String json = jStringBuilder.toString();
         Gson gson = new Gson();
        groups = gson.fromJson(json, Group[].class);
       }catch(Exception e){
        System.out.print(e.getMessage());
           return res;
       }
       
       for(Group group : groups){
        Map<String,Expression> eMap = new HashMap<>();
        Map<String,String> evMap = new HashMap<>();
        for(Expression e : group.expressions){
            eMap.put(e.name,e);
        }
        for(Expression e : group.expressions){
            evaluateExpression(e.name,eMap,evMap, new HashSet<>());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(group.groupName).append(":");
        for(Map.Entry<String,String> entry : evMap.entrySet()){
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(":");
        }
        if(!group.expressions.isEmpty() && sb.charAt(sb.length() -1) == ':'){
            sb.deleteCharAt(sb.length() -1);
        }
        res.add(sb.toString());
       }
       return res;
    }
    private static String evaluateExpression(String name,Map<String,Expression> eMap, Map<String,String> cache,Set<String> visitng){
        if(cache.containsKey(name))
           return cache.get(name);
        if(visitng.contains(name))
        throw new RuntimeException("Cycle detected "+ name);   
        visitng.add(name);
           
           Expression expr  = eMap.get(name);
           String value  = expr.expression;
           
           for(String dep : expr.dependencies){
            String depVal  = evaluateExpression(dep,eMap,cache,visitng);
            value = value.replace("${"+dep+"}",depVal);
           }
           if(expr.expressionType.equals("DOLLAR_EXPRESSION")){
            value = "(" + value +") $";
           } else if(expr.expressionType.equals("RS_EXPRESSION")){
             value = "(" + value +") RS";

           }
           visitng.remove(name);
           cache.put(name, value);
           return value;
    }
    
    static class Group{
        String groupName;
        List<Expression> expressions;
    }
    static class Expression{
         String name;
         String expressionType;
         String expression;
         List<String> dependencies ;
    }


}

public class Solution {
public static void main(String[] args) throws IOException {
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String api = bufferedReader.readLine();

        List<String> result = Result.evaluate(api);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
