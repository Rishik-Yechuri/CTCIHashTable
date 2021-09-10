import java.util.LinkedList;

public class CTCIHashTable {
    public static void main(String[] args) {
        try
        {
            CTCIHashTable obj = new CTCIHashTable();
            obj.run (args);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }
    public void run(String[] args){
        //Calls 'put' and 'get' and prints the results
        MyHashTable newTable = new MyHashTable();
        newTable.put("thiskey","result");
        System.out.println(newTable.get("thiskey"));
        //Calls same methods,but values are hardcoded into a collision
        newTable.put("Name","Bob");
        newTable.put("Someone","Also Bob");
        System.out.println(newTable.get("Name"));
    }
}
class MyHashTable{
    LinkedList[] holdValues;
    public MyHashTable(){
        holdValues = new LinkedList[100];
    }
    public int getIndex(String key){
        int hashCode = key.hashCode();
        int index = (hashCode & 0x7fffffff) % holdValues.length;
        if(key.equals("Name") || key.equals("Someone")){
            index = 5;
        }
        return index;
    }
    public void put(String key,Object objectToAdd){
        int index = getIndex(key);
        Object[] recordToInsert = new Object[]{key,objectToAdd};
        if(holdValues[index] == null){
            holdValues[index] = (new LinkedList<>());
        }
        holdValues[index].add(recordToInsert);
    }
    public Object get(String key){
        int index = getIndex(key);
        LinkedList<Object[]> listOfThings = holdValues[index];
        if(listOfThings.size() == 0){
            return null;
        }else if(listOfThings.size() == 1){
            return ((Object[])(listOfThings.getFirst()))[1];
        }else{
            for(Object[] o: listOfThings){
                if(o[0].equals(key))
                {
                    return o[1];
                }
            }
        }
        return null;
    }
}