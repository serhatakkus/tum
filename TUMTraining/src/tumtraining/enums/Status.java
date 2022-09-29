package tumtraining.enums;

public enum Status {

	NEW(0, "yeni turnuva") {
		@Override
		public boolean isNew() {
			return true;
		}
	}, REGISTER(10, "kayýt aþamasýnda"), INPLAY(1, "oynanýyor") {
		@Override
		public boolean started() {
return true;
		}
	}, FINISHED(99, "bitti") {
		@Override
		public boolean isFinished() {
			return true;
		}
		
		@Override
		public boolean started() {
			return true;
		}
	}
	, CANCELLED(-1, "iptal edildi");

	private int number;
	private String description;
	
	Status(int number, String description) {
		this.number = number;
		this.description = description;
	}
	
	public static Status getByNumber(int number) {
		for (Status st : Status.values()) {
			if (number == st.getNumber()) {
				return st;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.ordinal() + "-" + this.name() + "-" + this.getNumber() + "-" + this.getDescription();
	}
	
	public boolean isFinished() {
		return false;
	}
	
	public boolean isNew() {
		return false;
	}
	
	public boolean started() {
		return false;
	}

	public static boolean finished(Status prm) {
		return (Status.FINISHED == prm);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
