package lambdas;

import java.util.Collection;
import java.util.Objects;

public class ImperativeCodeMix {
	
}

class NiceAndCleanStreamOperationsChain {
	private static final String ADMIN_ROLE = "Admin";
	Collection<User> users;

	public boolean hasAdmin() {
		return users.stream()
				.map(Objects::requireNonNull)
				.flatMap(u -> u.getRoles().stream())
				.map(Role::getName)
				.anyMatch(ADMIN_ROLE::equals);
		
	}
}

class TooVerboseMixOfStreamOperationsAndImperativeCode {
	private static final String ADMIN_ROLE = "Admin";
	Collection<User> users;

	public boolean hasAdmin() {
		users.stream()
				.map(u -> {
					if (u == null) {
						throw new NullPointerException();
					}
					return u;
				})
				.flatMap(u -> u.getRoles().stream())
				.map(Role::getName)
				.anyMatch(name -> ADMIN_ROLE.equals(name));
				
		return false;		
	}
}

