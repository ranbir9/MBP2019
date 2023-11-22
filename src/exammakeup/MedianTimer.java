package exammakeup;

public class MedianTimer {
    public static void main(String[] args) {
        for (int n = 0; n <= 100000; n += 10000) {
            long quickSelectTotalTime = 0;
            long quickSortTotalTime = 0;

            for (int i = 0; i < 10; i++) {
                int[] arr = new int[n];
                for (int j = 0; j < n; j++) {
                    arr[j] = j;
                }
                Median.shuffle(arr);

                int[] arrCopy = arr.clone();

                long quickSortStartTime = System.nanoTime();
                Median.quickSort(arrCopy);
                long quickSortEndTime = System.nanoTime();
                quickSortTotalTime += quickSortEndTime - quickSortStartTime;

                long quickSelectStartTime = System.nanoTime();
                int median = Median.median(arr);
                long quickSelectEndTime = System.nanoTime();
                quickSelectTotalTime += quickSelectEndTime - quickSelectStartTime;
            }

            double quickSelectAverageTime = quickSelectTotalTime / 10.0;
            double quickSortAverageTime = quickSortTotalTime / 10.0;
            System.out.println("n = " + n + ", quickselect median time = " + quickSelectAverageTime + ", quicksort time = " + quickSortAverageTime);
        }
    }
}
