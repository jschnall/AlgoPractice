import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KnapSack {

    static class Item implements Comparable<Item> {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(@NotNull Item item) {
            if (weight < item.weight) {
                return -1;
            } else if (weight > item.weight) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    public static List<Item> randomItems(int size, int minWeight, int maxWeight, int minValue, int maxValue)  {
        List<Item> list = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int weight = maxWeight - minWeight > 0 ? random.nextInt(maxWeight - minWeight) + minWeight : minWeight;
            int value = maxValue - minValue > 0 ? random.nextInt(maxValue - minValue) + minValue : minValue;
            list.add(new Item(weight, value));
        }
        Collections.sort(list);

        return list;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W, using each item only once
    static int knapSackZeroOne(int capacity, List<Item> items, int n) {
        if (capacity <= 0 || n <= 0) {
            return 0;
        }

        Item item = items.get(n - 1);
        if (item.weight > capacity) {
            // This item is too heavy to add
            return knapSackZeroOne(capacity, items, n - 1);
        }

        // Return the greater of 2 possible cases:
        // 1: The Nth item is included
        // 2: Not included
        return Math.max(item.value + knapSackZeroOne(capacity - item.weight, items,n - 1), knapSackZeroOne(capacity, items,n - 1));
    }

    static int knapSackZeroOneDP(int capacity, List<Item> items, int n) {
        int[][] memo = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j <= 0 || i <= 0) {
                    memo[i][j] = 0;
                } else {
                    Item item = items.get(i - 1);
                    if (item.weight > j) {
                        memo[i][j] = memo[i - 1][j];
                    } else {
                        memo[i][j] = Math.max(item.value + memo[i - 1][j - item.weight], memo[i - 1][j]);
                    }
                }
            }
        }

        return memo[n][capacity];
    }

    // Returns the maximum value that can be put in a knapsack of capacity W, allowing duplicate items
    static int knapSackDP(int capacity, List<Item> items, int n) {
        // memoize max value for each capacity
        int[] memo = new int[capacity + 1];

        for (int j = 0; j <= capacity; j++) {
            memo[j] = 0;
            for (int i = 0; i < n; i++) {
                Item item = items.get(i);
                if (item.weight <= j) {
                    int val = memo[j - item.weight] + item.value;
                    if (val > memo[j]) {
                        memo[j] = val;
                    }
                }
            }
        }

        return memo[capacity];
    }
}
