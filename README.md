#MyHashTable
___
##Fields
___
`chainArray` - Array to hold chains

`M` - Number of chains

`size` - size of the hash table, number of elements in all the chains
```java
private HashNode<K, V>[] chainArray;
private int M = 11;
private int size = 0;
```
##Private Methods
___
`hash(K key)`

Description: Returns the bucket index for a given key of type K in the hash table, by performing a bitwise AND operation with 0x7fffffff to ensure a non-negative value, and then taking the modulus of that value with M.
```java
    private int hash(K key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

```
##Public Methods
___
`put(K key, V value)`

Description: Adds a new key-value pair to the hash table
```java
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
```
___
`get(K key)`

Description:  Retrieve the value associated with a given key
```java
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
```
___
`remove(K key)`
Description: Remove a key-value pair corresponding to a given key
```java
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
```
___
`contains(V value)`
Description: Checks if the hash table contains a node with the given value
```java
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
```
`getKey(V value)`

Description: Retrieves the key associated with a given value
```java
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
```
