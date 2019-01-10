import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testSquareArray();
        //testBinarySearch();
        //testSort();
        //testKnapSack();
    }

    private static void testSquareArray() {
        Integer[][] a = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = i * 3 + j;
            }
        }
        SquareArray<Integer> sa = new SquareArray<>(a);
        sa.print();
        sa.rotateByCopy();
        //sa.rotate(true);
        sa.print();
        sa.rotateByCopy();
        //sa.rotate(true);
        sa.print();
        sa.rotateByCopy();
        //sa.rotate(true);
        sa.print();
        sa.rotateByCopy();
        //sa.rotate(true);
        sa.print();
    }

    private static void testSort() {
        List list = randomList(10, 100);
        System.out.println("Unsorted: " + list);
        long startTime = System.currentTimeMillis();

        //list = SortHelper.mergeSort(list);
        //QuickSort.quickSort(list, 0, list.size() - 1);
        QuickSort.quickSortFatPartition(list, 0, list.size() - 1);

        System.out.println("Sorted in " + (System.currentTimeMillis() - startTime) + "ms : " + list);
    }

    private static void testBinarySearch() {
        List<Integer> list = randomList(10, 100);
        QuickSort.quickSort(list, 0, list.size() - 1);
        Integer item = list.get(new Random().nextInt(list.size()));
        //Integer item = list.get(new Random().nextInt(list.size())) - 1;
        System.out.println("Sorted List: " + list);
        System.out.println("Item: " + item);
        long startTime = System.currentTimeMillis();

        int result = SearchHelper.binarySearch(list, item);

        System.out.println("Index: " + result + " found in " + (System.currentTimeMillis() - startTime) + "ms : ");
    }

    private static void testKnapSack() {
        int capacity = 20;
        List items = KnapSack.randomItems(5, 1,1, 1, 4);
        System.out.println("Capacity :" + capacity);
        System.out.println("Items: " + items);
        long startTime = System.currentTimeMillis();

        //int maxValue = KnapSack.knapSackZeroOneDP(capacity, items, items.size());
        int maxValue = KnapSack.knapSackDP(capacity, items, items.size());
        System.out.println("MaxValue: " + maxValue + " found in " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static List<Integer> smallList() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(5);
        list.add(1);
        return list;
    }

    private static List<Integer> randomList(int size, int bound) {
        List<Integer> list = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }
}
