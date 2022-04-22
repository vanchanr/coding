import java.util.*;
import java.util.stream.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = sc.nextInt();
            int x = sc.nextInt();
            List<Pair> list = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                int s = sc.nextInt();
                int r = sc.nextInt();
                list.add(new Pair(s, r));
            }
            System.out.println(bestFilm(list, x));
        }
        sc.close();
    }

    public static int bestFilm(List<Pair> list, int x) {
        list = list.stream()
                .filter(pair -> pair.s <= x)
                .collect(Collectors.toList());
        return Collections.max(list, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.r - p2.r;
            }
        }).r;
    }

    static class Pair {
        int s;
        int r;

        public Pair(int s, int r) {
            this.s = s;
            this.r = r;
        }
    }
}
