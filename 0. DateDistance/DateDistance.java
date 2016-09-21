public class DateDistance {
	public static void main (String[]args) {
		long month = Long.parseLong(args[0]);
		long day = Long.parseLong(args[1]);
		long year = Long.parseLong(args[2]);
		long month1 = Long.parseLong(args[3]);
		long day1 = Long.parseLong(args[4]);
		long year1 = Long.parseLong(args[5]);
		System.out.println(DateDistance(month, day, year, month1, day1, year1));
		
	}
	public static boolean isCommonYear ( long year ) {
		return !(year%4 == 0 && (year%100 != 0 || year%400 == 0));
	}
	public static long monthLength ( long month, long year ) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 2) {
			int a;
			a = (isCommonYear(year)) ? 28 : 29;
			return a;
		} else {
			return -1;
		}
	}
	public static boolean isRealDate ( long month, long day, long year ) {
		return (month <= 12 && month >= 1 && day >= 1 && day <= monthLength(month, year));
	}
	public static long distance ( long month0, long day0, long year0, long month1, long day1, long year1 ) {
		if (isRealDate(month0, day0, year0) == false || isRealDate(month1, day1, year1) == false) {
			return -1;
		}
		long daysBetween = 0;
		while (!(month0 == month1 && day0 == day1 && year0 == year1)) {
			if (isRealDate(month0, day0, year0)) {
				 if(isRealDate(month0, day0 + 1, year0)) {
				 	day0 += 1;
				 	daysBetween = 1 + daysBetween;
				}else{
					day0 = 1;
					if (isRealDate(month0 + 1, day0, year0)) {
						month0 += 1;
						daysBetween += 1;
					} else {
						month0 = 1;
						year0 += 1;
						daysBetween = 1 + daysBetween;
					}
				}
			}
		}
		return daysBetween;
	}
	public static long DateDistance ( long month0, long day0, long year0, long month1, long day1, long year1 ) {
		if (year0 > year1 || month0 > month1 && year0 == year1 || month0 == month1 && day0 > day1 && year0 == year1) {
			return distance(month1, day1, year1, month0, day0, year0);
		} else {
			return distance(month0, day0, year0, month1, day1, year1);
		}
	}
}