package lambdas;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExternalCollectorForGrouping {
	
	private Set<User> users = new HashSet<>();
	
	//good
	class TuplesAreUsedWhenStateIsNeededOnLaterPhase {
		public Map<String, Set<User>> findEditors() {
			return users.stream()
				.flatMap(u -> u.getRoles().stream()
						.filter(r -> r.getPermissions().contains(Permission.EDIT))
						.map(r -> new Pair<>(r, u))
						).collect(groupingBy(p -> p.getKey().getName(),
								mapping(Pair::getValue, toSet())));
		}
	}
	
	class Pair<K, V> {
		private final K key;
		private final V value;
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}

	//ugly
	class ExternalStateIsUsedForStreamOperations {

		public Map<String, Set<User>> findEditors() {
			Map<String, Set<User>> editors = new HashMap<>();
			users.forEach(u -> u.getRoles().stream()
					.filter(r -> r.getPermissions().contains(Permission.EDIT))
					.forEach(r -> {
						Set<User> usersInRole = editors.get(r.getName());
						if (usersInRole == null) {
							usersInRole = new HashSet<>();
							editors.put(r.getName(), usersInRole);
						}
						usersInRole.add(u);
					})
				);
			return editors;
		}
	}
}
