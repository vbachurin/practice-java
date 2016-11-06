package lambdas;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WantToUseStreamsEverywhere {

	class UseOldPlainMap {
		public Map<String, Object> getJpaProperties() {
			Map<String, Object> properties = new HashMap<>();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "true");
			return Collections.unmodifiableMap(properties);
		}
	}
	
	//ugly
	class StreamToBuildMap {
		public Map<String, Object> getJpaProperties() {
			return Collections.unmodifiableMap(
					Stream.of(
								new AbstractMap.SimpleEntry<>("hibernate.show_sql", "true"),
								new AbstractMap.SimpleEntry<>("hibernate.format_sql", "true")
							).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
		}
	}

}

