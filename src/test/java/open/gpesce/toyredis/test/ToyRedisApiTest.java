package open.gpesce.toyredis.test;

import open.gpesce.toyredis.api.dto.ToyRedisApiKey;
import open.gpesce.toyredis.api.dto.ToyRedisApiRequest;
import open.gpesce.toyredis.api.dto.ToyRedisApiValue;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {SpringShellAutoConfiguration.class,
		JLineShellAutoConfiguration.class,
		StandardAPIAutoConfiguration.class,
		StandardCommandsAutoConfiguration.class})
class ToyRedisApiTest {

	@Autowired
	private ToyRedis toyRedisApi;

	@Test
	void contextLoads() {
		Assert.notNull(toyRedisApi, "context error");
	}

	@Test
	public void testService() {
		//GIVEN
		var request = new ToyRedisApiRequest();
		var key = new ToyRedisApiKey();
		key.setKey("UNO");
		var value = new ToyRedisApiValue();
		var item = new ArrayList<String>();
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

}
