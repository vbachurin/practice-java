package lambdas;

import java.util.HashSet;
import java.util.Set;

public class PreferSpecializedStreams {
	private Set<User> users = new HashSet<>();

	//good
	class SpecializedStreamUsage {
		
		public int getAverageAge() {
			return users.stream()
					.mapToInt(User::getAge)
					.sum();
		}
	}

	//bad
	class GeneralStreamUsage {

		public int getAverageAge() {
			return users.stream()
					.map(User::getAge)
					.reduce(0, Integer::sum);
			// to big int then to small int (AUTOBOXING)
		}
	}

}
