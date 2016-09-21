public class Summer {
	public static void main(String[]args){
		long total = 0;
		for (int i = 0; i < args.length; i = i + 1) {
			total = total + Long.parseLong(args[i]);
		}

		System.out.println((double)total / (double)args.length);
	}
}