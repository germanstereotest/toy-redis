package open.gpesce.toyredis.test;

import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisKeyImpl;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import open.gpesce.toyredis.core.service.ToyRedis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.standard.StandardAPIAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {SpringShellAutoConfiguration.class,
		JLineShellAutoConfiguration.class,
		StandardAPIAutoConfiguration.class,
		StandardCommandsAutoConfiguration.class})
class ToyRedisApiTest {

	@Autowired
	private ToyRedis<ToyRedisKey, ToyRedisValue> toyRedisApi;

	@Autowired
	ConcurrentHashMap<ToyRedisKey, ToyRedisValue> dataCollection;

	@Test
	@DisplayName("Testing SET without key")
	public void testSetExceptionFromKey() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.SET(null, new ToyRedisValueImpl(1), -1));
	}

	@Test
	@DisplayName("Testing SET without value")
	public void testSetExceptionFromValue() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.SET(element1, null, -1));
	}

	@Test
	@DisplayName("Testing SET OK")
	public void testSetOK() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		toyRedisApi.SET(element1, new ToyRedisValueImpl(1), -1);
		Assertions.assertNotNull(dataCollection.get(element1));
	}

	@Test
	@DisplayName("Testing GET without Key")
	public void testGetWithoutKey() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.GET(null));
	}

	@Test
	@DisplayName("Testing GET with invalid Key")
	public void testGetWithInvalidKey() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		var element2 = ToyRedisKeyImpl.builder().key("651651651asdasdasd").build();
		dataCollection.put(element1, new ToyRedisValueImpl(1));
		Assertions.assertNull(toyRedisApi.GET(element2));
	}

	@Test
	@DisplayName("Testing GET OK")
	public void testGetOk() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		dataCollection.put(element1, new ToyRedisValueImpl(1));
		Assertions.assertNotNull(toyRedisApi.GET(element1));
	}

	@Test
	@DisplayName("Testing DEL without Key")
	public void testDelWithoutKey() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.DEL(null));
	}

	@Test
	@DisplayName("Testing DEL with invalid Key")
	public void testDelWithInvalidKey() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		var element2 = ToyRedisKeyImpl.builder().key("651651651asdasdasd").build();
		dataCollection.put(element1, new ToyRedisValueImpl(1));
		Assertions.assertDoesNotThrow(() -> toyRedisApi.DEL(element2));
	}

	@Test
	@DisplayName("Testing DEL OK")
	public void testDelOk() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		var element2 = ToyRedisKeyImpl.builder().key("DOS").build();
		dataCollection.put(element1, new ToyRedisValueImpl(1));
		dataCollection.put(element2, new ToyRedisValueImpl(2));
		toyRedisApi.DEL(element1);
		Assertions.assertNull(toyRedisApi.GET(element1));
		Assertions.assertNotNull(toyRedisApi.GET(element2));
	}

	@Test
	@DisplayName("Testing DBSIZE")
	public void testDbSize() {
		dataCollection.clear();;
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		var element2 = ToyRedisKeyImpl.builder().key("DOS").build();
		dataCollection.put(element1, new ToyRedisValueImpl(1));
		dataCollection.put(element2, new ToyRedisValueImpl(2));
		toyRedisApi.DEL(element1);
		Assertions.assertEquals(1, toyRedisApi.DBSIZE());
	}

	@Test
	@DisplayName("Testing INCR without key")
	public void testIncrWithoutKey() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.INCR(null));
	}

	@Test
	@DisplayName("Testing INCR with invalid key")
	public void testIncrWithInvalidKey() {
		var element1 = ToyRedisKeyImpl.builder().key("C3P0").build();
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.INCR(element1));
	}

	@Test
	@DisplayName("Testing INCR with no numeric value")
	public void testIncrWithNoNumericValue() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		dataCollection.put(element1, new ToyRedisValueImpl("UNO"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> toyRedisApi.INCR(element1));
	}

	@Test
	@DisplayName("Testing INCR OK")
	public void testIncrOK() {
		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		dataCollection.put(element1, new ToyRedisValueImpl(159));
		var result = (ToyRedisValueImpl) toyRedisApi.INCR(element1);
		Assertions.assertEquals("160", result.getValue());
	}

	@Test
	@DisplayName("Simple concurrency test")
	public void testConcurrencyOnService() throws InterruptedException {

		var element1 = ToyRedisKeyImpl.builder().key("UNO").build();
		var element2 = ToyRedisKeyImpl.builder().key("DOS").build();
		var element3 = ToyRedisKeyImpl.builder().key("TRES").build();
		var element4 = ToyRedisKeyImpl.builder().key("CUATRO").build();
		var element5 = ToyRedisKeyImpl.builder().key("CINCO").build();
		var element6 = ToyRedisKeyImpl.builder().key("SEIS").build();
		var element7 = ToyRedisKeyImpl.builder().key("SIETE").build();
		var element8 = ToyRedisKeyImpl.builder().key("OCHO").build();
		var element9 = ToyRedisKeyImpl.builder().key("NUEVE").build();
		var element10 = ToyRedisKeyImpl.builder().key("D10S").build();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
		executor.execute(() -> toyRedisApi.SET(element1, new ToyRedisValueImpl(1), -1));
		executor.execute(() -> toyRedisApi.SET(element2, new ToyRedisValueImpl(2), -1));
		executor.execute(() -> toyRedisApi.SET(element3, new ToyRedisValueImpl(3), -1));
		executor.execute(() -> toyRedisApi.SET(element4, new ToyRedisValueImpl(4), -1));
		executor.execute(() -> toyRedisApi.SET(element5, new ToyRedisValueImpl(5), -1));
		executor.execute(() -> toyRedisApi.SET(element6, new ToyRedisValueImpl(6), -1));
		executor.execute(() -> toyRedisApi.SET(element7, new ToyRedisValueImpl(7), -1));
		executor.execute(() -> toyRedisApi.SET(element8, new ToyRedisValueImpl(8), -1));
		executor.execute(() -> toyRedisApi.SET(element9, new ToyRedisValueImpl(9), -1));
		executor.execute(() -> toyRedisApi.SET(element10, new ToyRedisValueImpl(10), -1));

		Thread.sleep(2000);
		executor.shutdownNow();

		Assertions.assertEquals(toyRedisApi.DBSIZE(), 10);
		Assertions.assertTrue(dataCollection.contains(new ToyRedisValueImpl(1)));
		Assertions.assertTrue(dataCollection.contains(new ToyRedisValueImpl(10)));
	}
}
