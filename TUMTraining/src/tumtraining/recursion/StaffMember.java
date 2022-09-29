package tumtraining.recursion;

import java.util.List;

public class StaffMember {
	// Attribute
	private String name;
	private int age;
	private List<StaffMember> subjects;

	public int getHighest() {

		int max = age;

		for (StaffMember member : subjects) {
			int subAge = member.getHighest();
			if (subAge > max) {
				max = subAge;
			}
		}
		
		return max;
	}
}
