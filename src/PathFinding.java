import java.util.*;

/**
 * Pathfinding in a grid composed of 0's and 1's, where 0, 0 is the top left corner
 * cell value of 0 indicates walkable, 1 indicates that it is blocked
 * start and end cells will always be 0
 */
public class PathFinding {
    static class Node {
        int x;
        int y;

        // fields for A* path
        double distance = 0; // estimated distance
        Node parent = null;

        // for A* pathLength
        int count = 0;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Easier than overriding both hash and equals to use in visited set
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /**
     * Find whether a path exists from point start to end on a grid
     * using depth first search
     */
    public static boolean pathExistsDFS(int[][] grid, Node start, Node end) {
        Set<String> visited = new HashSet<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visited.add(start.toString());
        while (!stack.isEmpty()) {
            Node node = stack.removeFirst();
            if (node.x == end.x && node.y == end.y) {
                return true;
            }
            List<Node> neighbors = getNeighbors(grid, node);
            for (Node neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    stack.addFirst(neighbor);
                    visited.add(neighbor.toString());
                }
            }
        }
        return false;
    }

    /**
     * Find whether a path exists from point start to end on a grid
     * using breadth first search
     */
    public static boolean pathExistsBFS(int[][] grid, Node start, Node end) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start.toString());
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.x == end.x && node.y == end.y) {
                return true;
            }
            List<Node> neighbors = getNeighbors(grid, node);
            for (Node neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    queue.add(neighbor);
                    visited.add(neighbor.toString());
                }
            }
        }
        return false;
    }

    /**
     * Use A* to find the shortest path between two points on grid
     */
    public static Node shortestPath(int[][] grid, Node start, Node end) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new PriorityQueue<>(16, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.distance, o2.distance);
            }
        });
        start.distance = calcDistance(start, end);
        queue.add(start);
        visited.add(start.toString());
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.x == end.x && node.y == end.y) {
                return node;
            }
            List<Node> neighbors = getNeighbors(grid, node);
            for (Node neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    neighbor.parent = node;
                    neighbor.distance = calcDistance(neighbor, end);
                    queue.add(neighbor);
                    visited.add(neighbor.toString());
                }
            }
        }
        return null;
    }

    /**
     * Use A* to find the shortest path between two points on grid
     */
    public static int shortestPathLength(int[][] grid, Node start, Node end) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new PriorityQueue<>(16, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.distance, o2.distance);
            }
        });
        start.distance = calcDistance(start, end);
        queue.add(start);
        visited.add(start.toString());
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.x == end.x && node.y == end.y) {
                return node.count;
            }
            List<Node> neighbors = getNeighbors(grid, node);
            for (Node neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    neighbor.count = node.count + 1;
                    neighbor.distance = calcDistance(neighbor, end);
                    queue.add(neighbor);
                    visited.add(neighbor.toString());
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param node
     * @return List of neighbors
     */
    public static List<Node> getNeighbors(int[][] grid, Node node) {
        List<Node> result = new ArrayList<>();
        int j = node.y - 1;
        // row above
        for (int i = node.x - 1; i <= node.x + 1; i++) {
            if (canWalk(grid, i, j)) {
                result.add(new Node(i, j));
            }
        }
        j = node.y + 1;
        // row below
        for (int i = node.x - 1; i <= node.x + 1; i++) {
            if (canWalk(grid, i, j)) {
                result.add(new Node(i, j));
            }
        }
        // row containing node
        if (canWalk(grid, node.x - 1, node.y)) {
            result.add(new Node(node.x - 1, node.y));
        }
        if (canWalk(grid, node.x + 1, node.y)) {
            result.add(new Node(node.x + 1, node.y));
        }
        return result;
    }

    public static boolean canWalk(int[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && x < grid[0].length && y < grid.length && grid[y][x] == 0;
    }

    private static double calcDistance(Node start, Node end) {
        int x = end.x - start.x;
        int y = end.y - start.y;
        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        };

        int[][] grid2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 0}
        };

        Node start = new Node(0, 0);
        Node end = new Node(3, 3);

        // path exists
        System.out.println(pathExistsDFS(grid1, start, end));
        System.out.println(pathExistsBFS(grid1, start, end));
        System.out.println(shortestPathLength(grid1, start, end));
        Node node = shortestPath(grid1, start, end);
        while (node != null) {
            System.out.print(node + ",");
            node = node.parent;
        }
        System.out.println("");

        // path does not exist
        System.out.println(pathExistsDFS(grid2, start, end));
        System.out.println(pathExistsBFS(grid2, start, end));
        System.out.println(shortestPathLength(grid2, start, end));
        System.out.println(shortestPath(grid2, start, end));
    }
}
