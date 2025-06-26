package src.companies.PocketPill;
// build a data Storage in which  we need to do insert remove and delete  in O(1)

import java.security.PublicKey;
import java.util.*;

class TempStorage<T>{

    Map<T,Integer> map;
    List<T> list;
   public TempStorage(){
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(  T t){
       if(map.containsKey(t)){
           return false;
       }
        map.put(t,list.size());
        list.add(t);
        return true;
    }

    public boolean remove( T t){
       if(!map.containsKey(t)){
           return false;
       }

       int index = map.get(t);
       T lastValue  = list.getLast();
       list.set(index,lastValue);
       list.removeLast();
       map.remove(t);
       map.put(lastValue,index);
       return true;
    }

    public T getRandom(){
       if(list.isEmpty()){
           throw new NoSuchElementException();
       }
       Random random = new Random();
       return list.get(random.nextInt(list.size()));
    }

}

public class DataStorage {
    public static void main(String[] args) {}
}
