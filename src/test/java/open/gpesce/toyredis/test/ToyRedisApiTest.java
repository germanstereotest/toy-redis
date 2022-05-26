package open.gpesce.toyredis.test;

import open.gpesce.toyredis.api.dto.ToyRedisApiKey;
import open.gpesce.toyredis.api.dto.ToyRedisApiRequest;
import open.gpesce.toyredis.api.dto.ToyRedisApiValue;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.service.ToyRedis;
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
import org.springframework.util.Assert;

import java.util.ArrayList;
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
	private ToyRedis toyRedisApi;

	@Autowired
	ConcurrentHashMap<ToyRedisKey, ToyRedisValue>  dataCollection;

	@Test
	void contextLoads() {
		Assert.notNull(toyRedisApi, "context error");
	}

	@Test
	public void testService() {
		//GIVEN
		var request = new ToyRedisApiRequest();
		var key = new ToyRedisApiKey("UNO");
		var item = new ArrayList<String>();
		var value = new ToyRedisApiValue(item);
		item.add("PIS");
		item.add("CACA");
		value.setValue(item);
		request.setKey(key);
		request.setValue(value);

		//WHEN

		//THAN
		toyRedisApi.SET(request.getKey(), request.getValue(), -1);
		var result = toyRedisApi.GET(request.getKey());
		System.out.println(result);
	}

	@Test
	public void testConcurrencyOnService() throws InterruptedException {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("UNO"), new ToyRedisApiValue(1), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("UNO"), new ToyRedisApiValue(2), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("TRES"), new ToyRedisApiValue(3), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("CUATRO"), new ToyRedisApiValue(4), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("CINCO"), new ToyRedisApiValue(5), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("SEIS"), new ToyRedisApiValue(6), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("SIETE"), new ToyRedisApiValue(7), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("OCHO"), new ToyRedisApiValue(8), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("NUEVE"), new ToyRedisApiValue(9), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisApiKey("D10S"), new ToyRedisApiValue(10), -1));

		Thread.sleep(10000);
		executor.shutdownNow();

		System.out.println(toyRedisApi.DBSIZE());
		System.out.println(dataCollection.toString());
	}
}
