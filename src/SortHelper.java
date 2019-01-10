import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SortHelper {

    // Simple quicksort with Lomuto partition scheme
    public static <T extends Comparable> void quickSort(List<T> list, int lo, int hi) {
        if (lo < hi) {
            int p = partition(list, lo, hi);
            quickSort(list, lo, p - 1);
            quickSort(list, p + 1, hi);
        }
    }

    private static <T extends Comparable> int partition(List<T> list, int lo, int hi) {
        T pivot = list.get(hi);
        int lastLowIndex = lo - 1;

        for (int i = lo; i < hi; i++) {
            T item = list.get(i);
            if (item.compareTo(pivot) < 0) {
                lastLowIndex++;
                swap(list, lastLowIndex, i);
            }
        }
        int newPivotPoint = lastLowIndex + 1;
        swap(list, newPivotPoint, hi);
        return newPivotPoint;
    }

    public static <T extends Comparable> List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        List left = list.subList(0, list.size() / 2);
        List right = list.subList(list.size() / 2, list.size());

        // Unlike quicksort, mergesort does not sort in place, so be sure to assign returned value
        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static <T extends Comparable> List<T> merge(List<T> left, List<T> right) {
        //System.out.println("Left: " + left);
        //System.out.println("Right: " + right);
        List<T> result = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            T l = left.get(0);
            T r = right.get(0);
            if (l.compareTo(r) < 0) {
                result.add(l);
                left = left.subList(1, left.size());
            } else {
                result.add(r);
                right = right.subList(1, right.size());
            }
        }

        if (!left.isEmpty()) {
            result.addAll(left);
        }
        if (!right.isEmpty()) {
            result.addAll(right);
        }
        //System.out.println("Result: " + result);
        return result;
    }

    public static <T> List<T> swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        return list;
    }
}
