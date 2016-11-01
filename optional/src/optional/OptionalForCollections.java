package optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalForCollections {
	
	class NiceAndClean {
		private Object ADMIN_ROLE;

		public User findAnyAdmin() {
			return findUsersByRole(ADMIN_ROLE)
					.stream().findFirst()
					.orElseThrow(() -> new IllegalStateException("No admins found"));
		}

		private List<User> findUsersByRole(Object aDMIN_ROLE2) {
			return Collections.emptyList();
		}
	}
	
	class TooVerbose {
		private Object ADMIN_ROLE;

		public User findAnyAdmin() {
			Optional<List<User>> users = findUsersByRole(ADMIN_ROLE);
			if (users.isPresent() && !users.get().isEmpty()) {
				return users.get().get(0);
			}
			throw new IllegalStateException("No admins found");
		}

		private Optional<List<User>> findUsersByRole(Object aDMIN_ROLE2) {
			return Optional.empty();
		}
	}

}
