package tumtraining.enums;

public enum OrderStatus {
	ORDERED(3, "new order"), CANCELLED(18, "damn customer"), READY(7, "finished work"), DISPATCHED(123, "on its way"),
	DELIVERED(0, "what a relief") {
		@Override
		public boolean isDelivered() {
			return true;
		}
	};

	private int value;
	private String message;

	OrderStatus(int value, String message) {
		this.value = value;
		this.setMessage(message);
	}

	public static OrderStatus getByValue(int value) {
		for (OrderStatus ord : OrderStatus.values()) {
			if (value == ord.value) {
				return ord;
			}
		}
		return null;
	}

	public static int add(int a, int b) {
		return a + b;
	}

	public boolean isDelivered() {
		return false;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
