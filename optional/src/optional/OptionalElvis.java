package optional;

import static java.util.Optional.*;

public class OptionalElvis {

	class UsingOrElse {
		public String getUserName(User user) {
			return ofNullable(user)
					.map(User::getName)
					.orElse("default");
		}
	}
	
	class UsingOptionalIsPresent {
		public String getUserName(User user) {
			if (ofNullable(user).isPresent()) {
				if(ofNullable(user.getName()).isPresent()) {
					return user.getName();
				}
			}
			return "default";
		}
	}
}
