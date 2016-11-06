package lambdas;

import static java.util.Optional.ofNullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class NewStreamStyleWithMethodReference {
	public void registerUsers(List<User> users) {
		users.stream()
			.filter(Objects::nonNull)
			.forEach(this::registerUser);
			
	}
	
	private void registerUser(User user) {
		// register user
	}
}

class NoMoreThanSameOldLoopWithIf {
	public void registerUsers(Collection<User> users) {
		users.stream().forEach(user -> ofNullable(user).ifPresent(u -> {
			// register user
		}));
	}
}