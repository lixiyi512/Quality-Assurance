public class Other {

    private int val;

    public Other(int val) {
	this.val = val;
    }

    public String levelifier(int val, String toReturn) {
	Other o = new Other(val % 13);
	if (val < 1500) {
	    return toReturn + o.levelifier(++val, toReturn + "2");
	} else {
	    return "3";
	}
    }
    
    public String threatLevel(int val) {
	String toReturn = "Midnight";
	String conv = levelifier(val, toReturn).substring(0, Math.abs(val));
	return conv;
    }

    public long defcon(long val) {
	val = (val > 2000) ? 2000 : val;
	if (val > 0) {
	    return val + defcon(val - 1);
	} else {
	    return val;
	}
    }
}
