package open.gpesce.toyredis.test;

import open.gpesce.toyredis.api.dto.ToyRedisApiRequest;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisKeyImpl;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
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
	public void testConcurrencyOnService() throws InterruptedException {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("UNO", -1, null), new ToyRedisValueImpl(1), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("UNO", -1, null), new ToyRedisValueImpl(2), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("TRES", -1, null), new ToyRedisValueImpl(3), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("CUATRO", -1, null), new ToyRedisValueImpl(4), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("CINCO", -1, null), new ToyRedisValueImpl(5), 3));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("SEIS", -1, null), new ToyRedisValueImpl(6), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("SIETE", -1, null), new ToyRedisValueImpl(7), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("OCHO", -1, null), new ToyRedisValueImpl(8), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("NUEVE", -1, null), new ToyRedisValueImpl(9), -1));
		executor.execute(() -> toyRedisApi.SET(new ToyRedisKeyImpl("D10S", -1, null), new ToyRedisValueImpl(10), -1));

		Thread.sleep(10000);
		executor.shutdownNow();

		System.out.println(toyRedisApi.DBSIZE());
		System.out.println(dataCollection.toString());
	}
}
