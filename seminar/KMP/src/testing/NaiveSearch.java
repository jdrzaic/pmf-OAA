package testing;

public class NaiveSearch {

	public static final int REPEAT = 1000;

	public int search(String text, String pattern) {
		int n  = text.length();
		int m  = pattern.length();
		int j = 0, i = 0;
		while(i < n - m) {
			if(pattern.charAt(j) == text.charAt(i + j)) {
				++j;
				if(j >= m) return i;
			}else {
				++i;
				j = 0;
			}
		}
		return -1;
	}
	 public void test2(int N, int M) {
	    for(int i = N; i < 500000; i += 10000) {
	    	test2c(i, M);
	    }  
	}
	 
	public void test2c(int N, int M) {
		RandomString randText = new RandomString(N);
		String pattern = new RandomString(M).nextString();
		//System.out.println(pattern);
    	double sum = 0.0;
		for(int i = 0; i < REPEAT; ++i) {
			String text = randText.nextString();
			long startTime = System.nanoTime();    
        	int offset = search(text, pattern);
        	long execTime = System.nanoTime() - startTime;
        	double constant = execTime / (M + N);
        	sum += constant;
    	}
    	System.out.println("" + N + "   " + (sum / REPEAT));
		
	}
	
	public void test(int N, int M) {
    	RandomString randText = new RandomString(N);
    	RandomString randPatt = new RandomString(M);
    	double sum = 0.0;
    	for(int i = 0; i < REPEAT; ++i) {
    		String text = randText.nextString();
    		String pattern = randPatt.nextString();
            long startTime = System.nanoTime();    
            //for(int j = 0; j < 1000; ++j) {
        		int offset = search(text, pattern);

           // }
        	long execTime = System.nanoTime() - startTime;
        	//System.out.println("time:" + execTime);
        	double constant = execTime / (M + N);
        	sum += constant;
            //System.out.println("time/m+n:" + constant);
            //System.out.println("offset:" + offset);
    	}
    	System.out.println("" + N + "   " + (sum / REPEAT));
    	/*String toSave = "M: " + M + "  N: " + N + " average: " + (sum / REPEAT); 
		FileWriter out;
		try {
			out = new FileWriter("results.txt", true);
			out.write(toSave + "\n");
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	
    }
    
    // test client
    public static void main(String[] args) {
        int N = Integer.valueOf(args[0]);
        int M = Integer.valueOf(args[1]);
        NaiveSearch ns = new NaiveSearch();
        ns.test2(N, M);
        //long startTime = System.nanoTime(); 
        //ns.search("papapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapapaapapapapapaaapaapapapapapapar", "papar");
        //kmp.test(N, M);
    	//long execTime = System.nanoTime() - startTime;
    	//System.out.println(execTime);
    }

}
