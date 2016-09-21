class Box {
	private Integer[][] box;

	public Box () {
		this.box = new Integer[3][3];
	}

	public void changeValue (int across, int down, Integer x) {
		this.box[across][down] = x;
	}
}