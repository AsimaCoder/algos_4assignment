import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(10000);
            String name = "Name_" + i;
            MyTestingClass key = new MyTestingClass(id, name);
            table.put(key, "Value_" + i);
        }

        Object[] chainArray = table.getChainArray();
        HashNode<MyTestingClass, String>[] nodes = Arrays.copyOf(chainArray, chainArray.length, HashNode[].class);
        int[] bucketSizes = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            HashNode<MyTestingClass, String> node = nodes[i];
            while (node != null) {
                bucketSizes[i]++;
                node = node.getNext();
            }
        }
        MyTestingClass searchKey = new MyTestingClass(42, "Name_42");
        String oldValue = "Value_42";
        String newValue = "New_Value_42";

        K replacedKey = table.replace(oldValue, newValue);
        if (replacedKey != null) {
            System.out.println("Replaced value for key: " + replacedKey);
        } else {
            System.out.println("Value not found in the hash table.");
        }

        String valueForKey = table.get(searchKey);
        System.out.println("Value for key " + searchKey + ": " + valueForKey);


        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + " size: " + bucketSizes[i]);
        }
    }
}



