package assign09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Hash Tables of MapEntry
 * 
 * @author Ranbir Singh & Anuvesha Chilwal
 * @version April 6, 2023
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for values type
 */
public class HashTable<K, V> implements Map<K, V> {
    private static final MapEntry TOMBSTONE = new MapEntry<>(null, null);
    ArrayList<MapEntry<K, V>> table;
    private int size = 0;
    private int coll = 0;

    public HashTable() {
        int primeCapacity = getPrime(3);
        table = new ArrayList<>(primeCapacity);
        for (int i = 0; i < primeCapacity; i++) {
            table.add(null);
        }
    }

    /**
     * Clears the HashTable by creating a new ArrayList
     */
    @Override
    public void clear() {
        ArrayList<MapEntry<K, V>> newTable = new ArrayList<>(table.size());
        for (int i = 0; i < table.size(); i++) {
            newTable.add(null);
        }
        table = newTable;
        size = 0;
    }

    /**
     * Checks if the HashTable contains a specified key
     * 
     * @param key - the key to be searched for in the HashTable
     * @return true if the HashTable contains the key
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null)
            return false;

        int hash = Compress(key.hashCode());

        for (int i = 0; i < table.size(); i++) {
            int index = Compress((hash + i * i));

            if (table.get(index) == null) {
                return false;
            }

            if (table.get(index).getKey() != null && table.get(index).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the HashTable contains a specified value
     * 
     * @param value - the value to be searched for in the HashTable
     * @return true if the HashTable contains the value, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        if (value == null) {
            return false;
        }

        for (int i = 0; i < table.size(); i++) {
            if (table.get(i) != null && table.get(i) != TOMBSTONE) {
                if (table.get(i).getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of all the MapEntries in the HashTable
     * 
     * @return a list of all the MapEntries in the HashTable
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            if (!(table.get(i) == null || table.get(i) == TOMBSTONE))
                list.add(table.get(i));
        }
        return list;
    }

    /**
     * searches table by key and returns value
     * 
     * @return a value associated to a key
     */
    @Override
    public V get(K key) {
        int hash = key.hashCode();

        if (containsKey(key)) {
            for (int i = 0; i < table.size(); i++) {
                int index = Compress((hash + i * i));
                if (table.get(index) == null)
                    return null;

                if (table.get(index).getKey() != null && table.get(index).getKey().equals(key)) {
                    return table.get(index).getValue();
                }
            }
        }
        return null;
    }

    /**
     * Returns are hashtable has any values
     * 
     * @return if hashtable is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts a new key-value pair into the HashTable. If the key already exists in
     * the table,
     * the old value is replaced with the new value. resize if lamda is greater than
     * .5
     * 
     * @param key   - the key to be inserted into the HashTable
     * @param value - the value to be associated with the key in the HashTable
     * @return the previous value associated with the key, or null if there was no
     *         previous value
     * @throws IllegalArgumentException if the key or value is null
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value or Both are null");
        }

        double lambda = ((double) size + 1) / table.size();
        if (lambda > 0.5) {
            resize();
        }

        int hash = Compress(key.hashCode());

        V prev;
        for (int i = 0; i < table.size(); i++) {
            int index = Compress(hash + i * i);
            if (i != 0) {
                coll++;
            }

            if (table.get(index) == null || table.get(index) == TOMBSTONE || table.get(index).getKey().equals(key)) {
                // previous value at index, null for nothing there before
                if (table.get(index) == null || table.get(index) == TOMBSTONE) {
                    prev = null;
                    size++;
                } else {
                    prev = table.get(index).getValue();
                }

                // Place the new entry at the index
                table.set(index, new MapEntry<>(key, value));

                if (table.get(index) == null || table.get(index) == TOMBSTONE) {
                    // size++;
                }
                return prev;
            }
        }
        return null;
    }

    /**
     * Returns the next prime number after the specified size
     * 
     * @param size - the size to find the next prime number after
     * @return the next prime number after the specified size
     */
    private int getPrime(int size) {
        BigInteger bi1 = BigInteger.valueOf(size);
        bi1 = bi1.nextProbablePrime(); // find next prime number after min size
        int nextPrimeInt = bi1.intValue();
        return nextPrimeInt;
    }

    /**
     * Resize table if called, this will create a new table and copy over the values
     */
    private void resize() {
        ArrayList<MapEntry<K, V>> oldTable = table;
        int newSize = getPrime(oldTable.size() * 2);
        table = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            table.add(null);
        }
        size = 0;

        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null && entry != TOMBSTONE) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Removes the key-value pair with the specified key from the HashTable, if it
     * exists. 
     * @param key - the key of the entry to remove from the HashTable
     * @return the value associated with the key that was removed, or null if the
     *         key was not found
     * @throws IllegalArgumentException if the key is null
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is NULL");
        }
        int hash = Compress(key.hashCode());

        for (int i = 0; i < table.size(); i++) {
            int index = Compress((hash + i * i));
            if (table.get(index) == null) {// probing never made it this far, doesn't exist beyond
                return null;
            }

            if (table.get(index).getKey() != null && table.get(index).getKey().equals(key)) {
                V prev = table.get(index).getValue();

                // Place a tombstone at the slot where the key was found
                table.set(index, TOMBSTONE);
                size--;
                return prev;
            }
        }
        // Key not found or reached the end of probing attempts
        return null;
    }

    /* 
     * This will return the current size
     */
    @Override
    public int size() {
        return size;

    }

    /*
     * This will compress the hash and return value to be indexed
     */
    public int Compress(int hashCode) {
        int index = hashCode % table.size();
        if (index < 0) {
            index += table.size();
        }
        return index;
    }

    /*
     * This will return a string representation of the the hashtable
     */
    public String toString() {
        String str = "[ ";
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i) == null || table.get(i) == TOMBSTONE) {
                str += "null ";
            } else {
                str += table.get(i).getKey() + ": ";
                str += table.get(i).getValue();
            }
            if (i != table.size() - 1)
                str += ", ";
        }
        return str += "]";
    }

    /*
     * This will return the number of collisons
     */
    public int coll() {
        return coll;
    }
}
