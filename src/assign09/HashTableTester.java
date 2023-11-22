package assign09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HashTableTester {
    HashTable<String, Integer> smallTable = new HashTable<>();

    @BeforeEach
    public void setUp() throws Exception {
        smallTable.put("ran", 90);
        smallTable.put("anu", 100);
        smallTable.put("wow", 50);
    }

    @Test
    public void sizeCheck() {
        // check size of small table
        int actualSize = smallTable.size();
        assertEquals(3, actualSize);
    }

    @Test
    public void checkSizeAfterPutResize() {
        // check size of small table after one add, this will resize in order to add
        smallTable.put("Hell2Heaven", 123);
        int actualSize = smallTable.size();
        assertEquals(4, actualSize);
    }

    @Test
    public void checkSizeAfterRemove() {
        // check size of small table after one remove
        smallTable.remove("ran");
        int actualSize = smallTable.size();
        assertEquals(2, actualSize);
    }

    @Test
    public void checkSizeAfterRemoveThenAdd() {
        // check size of small table after one remove and then one add
        smallTable.remove("ran");
        smallTable.put("Hell2Heaven", 123);
        int actualSize = smallTable.size();
        assertEquals(3, actualSize);
    }

    @Test
    public void checkSizeAfterClear() {
        // clear small table then check size
        smallTable.clear();
        int actualSize = smallTable.size();
        assertEquals(0, actualSize);
    }

    @Test
    public void clearTable() {
        // clear table and check values
        smallTable.clear();
        String expected = "[ null , null , null , null , null ]";
        String actual = smallTable.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void clearTableThenAdd() {
        // clear table then add one and check values
        smallTable.clear();
        String expected = "[ null , null , Cool: 1, null , null ]";
        smallTable.put("Cool", 1);
        String actual = smallTable.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void clearTableThenRemove() {
        // clear table and check values
        smallTable.clear();
        smallTable.remove("ran");
        String expected = "[ null , null , null , null , null ]";
        String actual = smallTable.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void clearTableThenCheckKey() {
        // clear table then check if a previous existing key exists
        smallTable.clear();
        boolean actual = smallTable.containsKey("ran");
        assertEquals(false, actual);
    }

    @Test
    public void clearTableThenCheckValue() {
        // clear table then check if a previous existing value exists
        smallTable.clear();
        boolean actual = smallTable.containsValue(100);
        assertEquals(false, actual);
    }

    @Test
    public void containsKeyValid() {
        //check for valid key
        boolean actual = smallTable.containsKey("ran");
        assertEquals(true, actual);
    }

    @Test
    public void containsKeyInvalid() {
        //check for invalid key 
        boolean actual = smallTable.containsKey("the rock");
        assertEquals(false, actual);
    }

    @Test
    public void containsKeyValidAfterResize(){
        //Add until resize happens, then verify add and contains works 
        String expected = "[ wow: 50, ran: 90, null , hi2: 1039, anu: 100]";
        String expected2 = "[ anu: 100, null , hi1: 1039, hi2: 1039, null , null , null , null , null , ran: 90, wow: 50]"; 
        
        System.out.println(smallTable.toString());
        smallTable.put("hi2", 1039);
        assertEquals(expected, smallTable.toString());
        smallTable.put("hi1", 1039);
        System.out.println(smallTable.toString());
        assertEquals(expected2, smallTable.toString());
        assertEquals(true, smallTable.containsKey("hi1"));

        //check valid key 
        assertEquals(true, smallTable.containsKey("hi2"));
    }

    @Test
    public void containsKeyInvalidAfterResize(){
        //Add until resize happens, then verify add and contains works with invalid key 
        String expected = "[ wow: 50, ran: 90, null , hi2: 1039, anu: 100]";
        String expected2 = "[ anu: 100, null , hi1: 1039, hi2: 1039, null , null , null , null , null , ran: 90, wow: 50]"; 
        
        System.out.println(smallTable.toString());
        smallTable.put("hi2", 1039);
        assertEquals(expected, smallTable.toString());
        smallTable.put("hi1", 1039);
        System.out.println(smallTable.toString());
        assertEquals(expected2, smallTable.toString());

        //check invalid key
        assertEquals(false, smallTable.containsKey("invalid"));
    }

    @Test
    public void containsValueValid(){
        //checks if smallTable contains valid value
        boolean actual = smallTable.containsValue(100); 
        assertEquals(true, actual);
    }

    @Test
    public void containsValueInvalid(){
        //checks if smallTable contains invalid value
        boolean actual = smallTable.containsValue(0); 
        assertEquals(false, actual);
    }

    @Test
    public void getValueValid(){
        //check if get(K) works with valid valid that exists
        Integer actual = smallTable.get("ran"); 
        assertEquals(true, actual == Integer.valueOf(90));
    }

    @Test
    public void getValueInvalid(){
        //check if get(K) works with invalid valid that doesn't exists
        Integer actual = smallTable.get("ran"); 
        assertEquals(false, actual == Integer.valueOf(34));
    }

    @Test
    public void getValueQuadraticProbing2ndValue(){
        smallTable.clear();
        //Two keys will result in same slot, "164" will go to next slot over. slot+1
        smallTable.put("146", 10);
        smallTable.put("164", 100);
        System.out.println(smallTable.toString());
        Integer actual = smallTable.get("164");
        assertEquals(true, actual == Integer.valueOf(100));
    }
    @Test
    public void getValueQuadraticProbing1stValue(){
        smallTable.clear();
        //Two keys will result in same slot, "164" will go to next slot over. slot+1
        smallTable.put("146", 10);
        smallTable.put("164", 100);
        System.out.println(smallTable.toString());
        Integer actual = smallTable.get("146");
        assertEquals(true, actual == Integer.valueOf(10));
    }

    @Test
    public void getValueAfterClear(){
        smallTable.clear();
        Integer actual = smallTable.get("ran");
        assertEquals(false, actual == Integer.valueOf(10));
    }

    @Test
    public void IsEmptySmallTable(){
        //check if small table is empty
        assertEquals(false, smallTable.isEmpty());
    }

    @Test
    public void IsEmptyAfterClear(){
        //check if small table is empty after clear
        smallTable.clear();
        assertEquals(true, smallTable.isEmpty());
    }

    @Test
    public void isEmptyAfterResizing(){
        //Add until resize happens, then verify add and contains works with invalid key 
        String expected = "[ wow: 50, ran: 90, null , hi2: 1039, anu: 100]";
        String expected2 = "[ anu: 100, null , hi1: 1039, hi2: 1039, null , null , null , null , null , ran: 90, wow: 50]"; 
        
        System.out.println(smallTable.toString());
        smallTable.put("hi2", 1039);
        assertEquals(false, smallTable.isEmpty());
        assertEquals(expected, smallTable.toString());
        smallTable.put("hi1", 1039);
        assertEquals(false, smallTable.isEmpty());//after resizing
        System.out.println(smallTable.toString());
        assertEquals(expected2, smallTable.toString());
    }

    @Test
    public void putValueInSmallTable(){
        smallTable.put("new World", 10);
        System.out.println(smallTable.toString());
    }

    @Test
    public void putValueAlreadyExistingForKey(){
        //put method on a key that already exists, return previous value  
        Integer prev = smallTable.put("ran", 101);
        assertEquals(true, Integer.valueOf(90) == prev); 
    }

    @Test
    public void putNullValue(){
        //put a null map entry 
        String expectedMessage = "Key or Value or Both are null"; 
        String actualMessage = ""; 
        try{
            smallTable.put(null, null);
        } catch (IllegalArgumentException e){
            actualMessage = e.getMessage(); 
        }
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void removeValidKeyCheckContains(){
        //Remove key "ran", check if table contains key 
        assertEquals(true, smallTable.containsKey("ran"));
        smallTable.remove("ran"); 
        assertEquals(false, smallTable.containsKey("ran"));
    }

    @Test
    public void removeInvalidKey(){
        //Remove key invalid key, check value returned 
        var removed = smallTable.remove("ran11111");
        assertEquals(true, removed == null); 
    }

    @Test
    public void removeValidKey(){
        //Remove key "ran", check value returned 
        var removed = smallTable.remove("ran"); 
        assertEquals(false, removed == null); 
        assertEquals(true, removed == Integer.valueOf(90)); 
    }

    @Test
    public void resizee(){
        for(int i = 0; i < 107; i++){
            smallTable.put("10"+i, i+100); 
        }
        System.out.println(smallTable.size());
    }







}