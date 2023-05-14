
public class MyHashTable <K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        // To create a new HashNode with the given key-value pair
        private HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    // The chainArray is an array of HashNode objects that serves as the underlying data structure for the hash table
    private HashNode<K, V>[] chainArray;
    private int M = 11; // Default node array size
    private int size; // The size variable keeps track of the number of elements in the hash table


    // To create a new hash table with the default number of buckets
    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }
    // To create a new hash table with the specified number of buckets
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }
    // Takes a key and returns an integer between 0 and M-1 to represent the bucket index
    private int hash(K key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // Adds a key-value pair to the hash table
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    // Returns the value associated with the specified key in the hash table
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    // Removes the key-value pair associated with the specified key from the hash table
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> curr = chainArray[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }
    // Return true if the hash table contains the specified value
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }
    public HashNode<K, V>[] getChainArray() {
        return chainArray;
    }

}


