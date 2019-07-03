package business;

public class Alerts extends Exception {
	private static final long serialVersionUID = 1L;
	public Alerts() {
		super();
	}
	public Alerts(String e) {
		super(e);
	}
	public Alerts(Throwable t) {
		super(t);
	}
}
