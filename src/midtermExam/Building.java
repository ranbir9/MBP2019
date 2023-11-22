package midtermExam;

class Building { 
    public <Point> Point getLocation() {
        return null; 
    }

    public static <T extends Comparable<T>> T findSmallest(T[] array){
        if(array == null || array.length == 0)
            return null; 

        T smallest = array[0]; 

        for(int i = 0; i < array.length; i++){
            if(array[i].compareTo(smallest) < 0)
                smallest = array[i]; 
        }  
              
        return smallest;     
    }
        
 }
