import dev.dumble.helper.config.impl.ConfigurationHelper;
import org.junit.jupiter.api.Test;

public class TestConfiguration {

//	@Getter @Setter
//	private static Configuration configuration = Configuration.builder()
//			.setConfigurationPath("configuration")
//			.setResourcesStream(ConfigurationHelper.asResourcesStream("config"))
//			.build();

	@Test
	public void testGet() {
//		final String string = configuration.get("telegram.bot.name");

//		Assertions.assertEquals(string, "test");
	}

	@Test
	public void testGetAsInteger() {
//		final Integer integer = configuration.get("reboot.schedule_time", Integer.class);

//		System.out.println(integer + 10);

//		Assertions.assertEquals(integer, 60);
	}

	@Test
	public void testGetAsList() {


//		final List<LinkedHashMap> strings = configuration.getAsList("items", LinkedHashMap.class);
//
//		for (LinkedHashMap<String, Object> string : strings) {
//
//		}

//		Assertions.assertFalse(strings.isEmpty());
	}

	@Test
	public void testGetNode() {
		System.out.println(ConfigurationHelper.getFiles("config"));

//		final String path = "test.string";
//		final Node<Object> string = configuration.getNode(path);

//		configuration.loadConfiguration();
	}
}
