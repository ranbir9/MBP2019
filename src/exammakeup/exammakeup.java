package exammakeup;

public class exammakeup {

    // partitions the array elements in indices [begin, end)
    // arr[begin] is included, but arr[end] is NOT included
    // returns the index of the pivot after partitioning
    static int partition(int[] arr, int begin, int end, int pivotIndex) {
        return 0;
    }

    // somehow pick the index for a pivot from between [begin, end)
    // this method rums in 0(1) time
    static int getPivotIndex(int[] arr, int begin, int end) {
        return 0;
    }

    // (a) (5 points) Fill in the missing parts of the 3 return statements below to
    // complete the method so
    // that it correctly computes the median of the array
    // returns the median value of the unsorted array
    private static int median(int[] arr, int begin, int end) {
        int pivotIndex = getPivotIndex(arr, begin, end);
        int updatedPivotIndex = partition(arr, begin, end, pivotIndex);
        int middleIndex = arr.length / 2;
        if (updatedPivotIndex == middleIndex) {
            // £ill in the return statement

            return 0;

        } else if (updatedPivotIndex < middleIndex) {
            // fill in parameters to recursive call

            return 0;

        } else {
            // fill in parameters to recursive call

            return 0;
        }

    }
    
}
