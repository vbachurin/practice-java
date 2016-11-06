package lambdas;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DoNotNeglectDataStructures {

	//good
	class UseOfDataStructure {
		public List<Order> filterOrdersByStatuses(List<Order> orders, Set<Status> appropriateStatuses) {
			return orders.stream()
					.filter(order -> appropriateStatuses.contains(order.getStatus()))
					.collect(Collectors.toList());
		}
	}
	
	//ugly
	class UnnecessaryUseOfNestedStreamOperations {
		public List<Order> filterOrdersByStatuses(List<Order> orders, Set<Status> appropriateStatuses) {
			return orders.stream()
					.filter(order -> 
						appropriateStatuses.stream().anyMatch(status -> 
							status.equals(order.getStatus())))
					.collect(Collectors.toList());
		}
	}

	class Order {

		private Status status;

		public Status getStatus() {
			return status;
		}

	}

	class Status {}
}
