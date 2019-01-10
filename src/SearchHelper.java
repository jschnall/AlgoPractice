import java.util.List;

public class SearchHelper {
    // List must already be sorted
    // returns the index of the item, or - of insertion point if not found
    public static <T extends Comparable> int binarySearch(T[] array, T item) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int center = (int) Math.floor((left + right) / 2);
            T centerItem = array[center];
            if (centerItem.compareTo(item) < 0) {
                left = center + 1;
            } else if (centerItem.compareTo(item) > 0) {
                right = center - 1;
            } else {
                return center;
            }
        }
        // not found, return - of insertion point
        return - (array[left].compareTo(item) < 0 ? left + 1 :  left);
    }

    // List must already be sorted
    // returns the index of the item, or - of insertion point if not found
    public static <T extends Comparable> int binarySearch(List<T> list, T item) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int center = (int) Math.floor((left + right) / 2);
            T centerItem = list.get(center);
            if (centerItem.compareTo(item) < 0) {
                left = center + 1;
            } else if (centerItem.compareTo(item) > 0) {
                right = center - 1;
            } else {
                return center;
            }
        }
        // not found, return - of insertion point
        return - (list.get(left).compareTo(item) < 0 ? left + 1 :  left);
    }
}
