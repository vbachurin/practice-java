package lambdas;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class UseFlatMapForSubCollections {
	Collection<User> users;

	public boolean checkPermission(Permission permission) {
		return users.stream()
			.flatMap(u -> u.getRoles().stream())
			.filter(r -> r.getPermissions().contains(permission))
			.findAny().isPresent();
	}
}

class UseOldSchoolIterationsWithForEachAndExternalBoolean {
	Collection<User> users;

	public boolean checkPermission(Permission permission) {
		AtomicBoolean found = new AtomicBoolean();
		users.forEach(
				u -> u.getRoles().forEach(
						r -> {
							if (r.getPermissions().contains(permission))
								found.set(true);
						})
				);
		return found.get();
	}
}
