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


        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + " size: " + bucketSizes[i]);
        }
    }
}



