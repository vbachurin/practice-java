package optional;

import java.util.Optional;

public class InternalOptionalUsage {
	
	class ValidInternalOptionalDependency {
		private Optional<Printer> printer = Optional.empty();
		
		public void process(User user) {
			printer.ifPresent(p -> p.print(user));
		}
		
		public void setPrinter(Printer printer) {
			this.printer = Optional.ofNullable(printer);
		}
	}
	
	interface Printer {
		String print(User user);
	}

}
