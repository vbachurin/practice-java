package optional;

import java.util.Optional;

class OrElseThrowUsage {
	public String getUserName(Long userId) {
		return findById(userId).orElseThrow(() -> new IllegalStateException("User not found"))
				.getName();
	}

	private Optional<User> findById(Long userId) {
		return null;
	}
}

public class StrictCheckOfValuePresense {
	public String getUserName(Long userId) {
		Optional<User> user = findById(userId);
		if (user.isPresent()) {
			return user.get().getName();
		}
		throw new IllegalStateException("User not found");
	}

	private Optional<User> findById(Long userId) {
		return null;
	}
	
}

class User {
	private String name;
	
	public String getName() {
		return name;
	}
}
