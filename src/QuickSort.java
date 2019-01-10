import java.util.List;
import java.util.Random;

public class QuickSort {
    public static <T extends Comparable> void quickSort(List<T> list, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = pickPivot(list, lo, hi);
            int p = partition(list, lo, hi, pivotIndex);
            quickSort(list, lo, p - 1);
            quickSort(list, p + 1, hi);
        }
    }

    private static <T extends Comparable> int pickPivot (List<T> list, int lo, int hi) {
        // Pick a random index within the range to pivot on
        Random random = new Random();
        return random.nextInt(hi-lo) + lo;
    }

    private static <T extends Comparable> int partition(List<T> list, int lo, int hi, int pivotIndex) {
        // Move pivot element to the end of the list segment being worked on
        SortHelper.swap(list, pivotIndex, hi);
        T pivot = list.get(hi);

        int lastLowIndex = lo - 1;

        for (int i = lo; i < hi; i++) {
            T item = list.get(i);
            if (item.compareTo(pivot) < 0) {
                lastLowIndex++;
                SortHelper.swap(list, lastLowIndex, i);
            }
        }
        int newPivotPoint = lastLowIndex + 1;
        SortHelper.swap(list, newPivotPoint, hi);
        return newPivotPoint;
    }

    public static <T extends Comparable> void quickSortFatPartition(List<T> list, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = pickPivot(list, lo, hi);
            int[] range = fatPartition(list, lo, hi, pivotIndex);
            quickSortFatPartition(list, lo, range[0] - 1);
            quickSortFatPartition(list, range[1] + 1, hi);
        }
    }

    private static <T extends Comparable> int[] fatPartition(List<T> list, int lo, int hi, int pivotIndex) {
        T pivot = list.get(pivotIndex);

        int lastLowIndex = lo - 1;
        int firstHighIndex = hi + 1;

        for (int i = lo; i <= hi; i++) {
            T item = list.get(i);
            if (item.compareTo(pivot) < 0) {
                lastLowIndex++;
                SortHelper.swap(list, lastLowIndex, i);
            }
        }
        for (int i = hi; i > lastLowIndex; i--) {
            T item = list.get(i);
            if (item.compareTo(pivot) > 0) {
                firstHighIndex--;
                SortHelper.swap(list, firstHighIndex, i);
            }
        }
        //System.out.println("pivot: " + pivot);
        //System.out.println("lastLow: " + lastLowIndex);
        //System.out.println("firstHigh: " + firstHighIndex);
        //System.out.println(list);

        int[] result = { lastLowIndex + 1, firstHighIndex - 1 };
        return result;
    }
}
