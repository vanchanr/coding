//https://www.codechef.com/LP1TO202/problems/VACCINQ

import java.util.*;
import java.util.stream.*;

class IoWithStreams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; ++i) {
            List<Integer> inp = Stream.of(sc.nextLine().trim().split(" "))
            			.map(s -> Integer.parseInt(s))
            			.collect(Collectors.toList());
            int n = inp.get(0);
            int p = inp.get(1);
            int x = inp.get(2);
            int y = inp.get(3);
            inp = Stream.of(sc.nextLine().trim().split(" "))
            		.map(s -> Integer.parseInt(s))
            		.collect(Collectors.toList());
		
    	    int numMinutes = 0;
    	    for (int j = 0; j < p; ++j) {
    	    	if (inp.get(j) == 0) {
    	    	    numMinutes += x;
    	    	} else {
    	            numMinutes += y;
    	    	}
    	    }
            System.out.println(numMinutes);
        }
        sc.close();
    }
}
