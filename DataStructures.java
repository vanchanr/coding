import java.util.*;

public class DataStructures {
    public static void main(String[] args) {
        // dynamicArrayOperations();
        // setOperations();
        miscOperations();
    }

    public static void dynamicArrayOperations() {
        List<String> list = new ArrayList<>();

        System.out.println(list.isEmpty()); //true
        System.out.println(list.size()); //0

        list.add("Alice"); //append element at end
        list.add(0, "Bob"); //insert element at specified index
        System.out.println(list); //[Bob, Alice]
        list.set(0, "Charlie"); //replace element at index 0
        System.out.println(list); //[Charlie, Alice]
        
        System.out.println(list.contains("Alice")); //true
        System.out.println(list.get(0)); //Charlie
        System.out.println(list.indexOf("Alice")); //1 - return index of first occurence of element if present. else return -1
        System.out.println(list.lastIndexOf("Alice")); //1 - return index of last occurence of element if present. else return -1

        String[] arr = list.toArray(new String[0]); //converting list to array
        System.out.println(Arrays.toString(arr)); //[Charlie, Alice]
        
        list.remove(0); //remove element at index 0
        list.remove("Alice"); //remove first occurence of element if present
        System.out.println(list); //[]
    }

    public static void setOperations() {
        //based on hashing
        //time complexity: insertion, search, removal are O(1) on average
        Set<String> hashSet = new HashSet<>();


        //based on balanced binary tree
        //time complexity: insertion, search, removal are guaranteed O(log N)
        //maintains order of elements - functionality which is not available in HashSet
        TreeSet<String> treeSet = new TreeSet<>();

    }

    public static void miscOperations() {
        //Collections: fill, frequency, reverse, max, min, sort, binarySearch
        List<Integer> src = new ArrayList<>();
        src.add(1); src.add(2); src.add(3);
        List<Integer> dest = new ArrayList<>(100);
        System.out.println(dest); //[]
        Collections.copy(dest, src);
        System.out.println(dest); //[]
    }
}
