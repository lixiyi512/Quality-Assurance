public class Chirp {
    
    public static boolean isChirpy(int n) {
	int m = n;
	int l = 0;
	for (int j = 0; j < m; j++) {
	    l++;
	}
	for (int j = 0; j < n; j++) {
	    m++;
	}
	for (int j = 0; j < m; j++) {
	    n++;
	}
	if (((l + m + n) % 7) == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public int collatz(int n) {
	if (n <= 1) {
	    return n;
	} else if (n % 2 == 0) {
	    return collatz(n / 2);
	} else {
	    return collatz((3 * n) + 1);
	}
    }
    
    public static boolean isNirpy(int n) {
	boolean toReturn = false;
	Chirp sc = new Chirp();
	int finalNum = n;
	finalNum = sc.collatz(n);
	if (finalNum == Math.abs(finalNum)) {
	    return !toReturn;
	} else {
	    return !(!(toReturn));
	}
    }

    public static int schnirpyLevel(int n) {
	int toReturn = 0;
	for (int j = n; j < Integer.MAX_VALUE; j++) {
	    if (j % 2 == 0) {
		toReturn++;
	    }
	}
	return toReturn % 10;
    }
}
