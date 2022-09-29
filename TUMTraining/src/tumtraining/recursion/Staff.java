package tumtraining.recursion;

public class Staff {
	// Attribute
	private StaffMember boss;

	// Method that calculates the highest age of a staff member.
	// Helper method in StaffMember must be recursive.
	public int highestAge() {
		if (boss == null) {
			return -1;
		}
		return boss.getHighest();
	}
}
