package tumtraining.enums;

import java.util.Date;

import tumtraining.poly.Animal;
import tumtraining.poly.Bird;

public class Order {

	public static void main(String[] args) {
		for (OrderStatus ord : OrderStatus.values()) {
			System.out.println(ord.name() + " - " + ord.ordinal());
		}
		

		OrderStatus firstOrderStatus = OrderStatus.DELIVERED;
		OrderStatus secondOrderStatus = OrderStatus.ORDERED;
		OrderStatus thirdOrderStatus = OrderStatus.getByValue(0);
		OrderStatus fourthOrderStatus = OrderStatus.CANCELLED;
		OrderStatus fifthOrderStatus = OrderStatus.READY;
		
		if (OrderStatus.DELIVERED == firstOrderStatus) {
			System.out.println("1. -> Yes");
		} else {
			System.out.println("1. -> No");
		}

		if (OrderStatus.DELIVERED.equals(firstOrderStatus)) {
			System.out.println("1. -> Yes");
		} else {
			System.out.println("1. -> No");
		}

		if (fourthOrderStatus == thirdOrderStatus) {
			System.out.println("4 == 5 -> Yes");
		} else {
			System.out.println("4 == 5 -> No");
		}
		
		System.out.println("is delivered:");
		
		System.out.println("" + firstOrderStatus.isDelivered());
		System.out.println("" + secondOrderStatus.isDelivered());
		System.out.println("" + thirdOrderStatus.isDelivered());
		System.out.println("" + fourthOrderStatus.isDelivered());
		System.out.println("" + fifthOrderStatus.isDelivered());

		System.out.println("values:");
		
		System.out.println("" + firstOrderStatus.getValue());
		System.out.println("" + secondOrderStatus.getValue());
		System.out.println("" + thirdOrderStatus.getValue());
		System.out.println("" + fourthOrderStatus.getValue());
		System.out.println("" + fifthOrderStatus.getValue());
		
	}

}
