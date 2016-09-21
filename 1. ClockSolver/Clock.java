public class Clock {
	private double hours;
	private double minutes;
	private double seconds;
	public Clock() {
		this.seconds = 0.0;
		this.minutes = 0.0;
		this.hours = 0.0;
	}
	public void tick(double x) {
		this.seconds += x%60;
		this.minutes += x/60%60;
		this.hours += x/3600;
		if (this.seconds >= 60) {
			this.seconds = this.seconds%60;
		}
		if (this.minutes >= 60) {
			this.minutes = this.minutes%60;
		}
	}
	public double hourAngle() {
		return this.hours / 12 * 360;
	}
	public double minuteAngle() {
		return this.minutes / 60.0 * 360;
	}
	public double angleBetween() {
		if (Math.abs(this.hourAngle() - this.minuteAngle()) > 180) {
			return 360 - Math.abs(this.hourAngle() - this.minuteAngle());
		}
		return Math.abs(this.hourAngle() - this.minuteAngle());
	}
	public double getHours() {
		return this.hours;
	}
	public double getMinutes() {
		return this.minutes;
	}
	public double getSeconds() {
		return this.seconds;
	}
	public String toString() {
		return (int)this.hours + " : " + (int)this.minutes + " : " + this.seconds;
	}
}