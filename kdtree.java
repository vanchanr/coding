import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class kdtree {

    class Point {
        public int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Range {
        public int x1,x2,y1,y2;

        public Range(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    class TreeNode {
        public TreeNode left;
        public TreeNode right;
        int numPoints;
        Range range;
    }

    public Point[] convertListToArr(ArrayList<Point> list) {
        int len = list.size();
        Point[] arr = new Point[len];
        for (int i=0; i<len; i++) {
            Point p = list.get(i);
            arr[i] = new Point(p.x, p.y);
        }
        return arr;
    }

    //sort points list based on given dimension
    public Point[] sort (Point[] pointArr, int dim) {
        int len = pointArr.length;

        //create copy so that original array won't be changed
        Point[] copy = new Point[len];
        for (int i=0; i<len; i++) {
            Point p = pointArr[i];
            copy[i] = new Point(p.x, p.y);
        }

        //sort copy
        for (int i=0; i<len; i++) {
            for (int j=0; j<len-1-i; j++) {
                if (dim == 1) { //sort based on x
                    if (copy[j].x > copy[j+1].x) {
                        Point temp = copy[j];
                        copy[j] = copy[j+1];
                        copy[j+1] = temp;
                    }
                } else if (dim == 2) { //sort based on y
                    if (copy[j].y > copy[j+1].y) {
                        Point temp = copy[j];
                        copy[j] = copy[j+1];
                        copy[j+1] = temp;
                    }
                }
            }
        }

        //return sorted copy
        return copy;
    }

    //if depth is even, use sortedByX to create node
    //if depth is odd, use sortedByY to create node
    //use (min to max) of sortedByX or sortedByY to calculate median
    public TreeNode createKdTree(Point[] sortedByX, Point[] sortedByY, int depth, int min, int max, Range range) {
        TreeNode tn = new TreeNode();
        tn.numPoints = max - min + 1;
        tn.range = range;

        if (min == max) { //leaf node
            tn.left = null;
            tn.right = null;
        } else {
            int median = min + (max - min)/2;
            Point p;
            Range rangeLeft, rangeRight;
            if (depth%2 == 0) {
                p = sortedByX[median];
                rangeLeft = new Range(range.x1, p.x, range.y1, range.y2);
                rangeRight = new Range(p.x, range.x2, range.y1, range.y2);

            } else {
                p = sortedByY[median];
                rangeLeft = new Range(range.x1, range.x2, range.y1, p.y);
                rangeRight = new Range(range.x1, range.x2, p.y, range.y2);
            }
            tn.left = createKdTree(sortedByX, sortedByY, depth+1, min, median, rangeLeft);
            tn.right = createKdTree(sortedByX, sortedByY, depth+1, median+1, max, rangeRight);
        }
        return tn;
    }

    //return 0: if r1 fully outside r2
    //return 1: if r1 intersects r2
    //return 2: if r1 fully contained in r2
    public int compareRanges(Range r1, Range r2) {
        if (r1.x1 >= r2.x1 && r1.x2 <= r2.x2 && r1.y1 >= r2.y1 && r1.y2 <= r2.y2) {
            return 2;
        }
        else if (r1.x2 < r2.x1 || r1.x1 > r2.x2 || r1.y2 < r2.y1 || r1.y1 > r2.y2) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public int getCountOfPointsInRange(TreeNode treeNode, Range range) {
        if (treeNode == null) {
            return 0;
        }
        int rangeCompare = compareRanges(treeNode.range, range);
        if (rangeCompare == 2) {
            return treeNode.numPoints;
        } else if (rangeCompare == 0) {
            return 0;
        } else {
            return getCountOfPointsInRange(treeNode.left, range) + getCountOfPointsInRange(treeNode.right, range);
        }
    }

    public void start() throws IOException {
        FileInputStream file1 = new FileInputStream("restaurants.txt");
        FileInputStream file2 = new FileInputStream("queries.txt");
        FileOutputStream file3 = new FileOutputStream("output.txt");

        Scanner sc1 = new Scanner(file1);
        Scanner sc2 = new Scanner(file2);
        PrintWriter pw = new PrintWriter(file3);

        ArrayList<Point> restaurantsList = new ArrayList<Point>();
        sc1.nextLine(); //read first line "latitude,longitude"
        while (sc1.hasNextLine()) {
            String inp = sc1.nextLine();
            String[] arr = inp.split(",");
            Point p = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            restaurantsList.add(p);
        }

        Point[] restaurantsArr = convertListToArr(restaurantsList);
        Point[] sortedByX = sort(restaurantsArr, 1);
        Point[] sortedByY = sort(restaurantsArr, 2);

        Range rootRange = new Range(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        TreeNode kdTreeRoot = createKdTree(sortedByX, sortedByY, 0, 0, restaurantsArr.length-1, rootRange);

        sc2.nextLine();
        while (sc2.hasNextLine()) {
            String inp = sc2.nextLine();
            String[] arr = inp.split(",");
            Point p = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            Range r = new Range(p.x-100, p.x+100, p.y-100, p.y+100);
            int count = getCountOfPointsInRange(kdTreeRoot, r);
            pw.println(count);
        }

        sc1.close();
        file1.close();

        sc2.close();
        file2.close();

        pw.close();
        file3.close();
    }

    public static void main(String[] args) throws IOException {
        kdtree kdtree = new kdtree();
        kdtree.start();
    }
}


