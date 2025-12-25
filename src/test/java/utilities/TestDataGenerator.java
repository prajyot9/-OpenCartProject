package utilities;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.text.RandomStringGenerator;

public class TestDataGenerator {

	//Generate random numreoc value between min and max
	public static int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);

	}
	
	//Generate randomm alphatbetic
	public static String getRandomString(int length)
	{
		RandomStringGenerator generator= new RandomStringGenerator.Builder()
				.withinRange('a','z')
				.build();
		return generator.generate(length);
		
	}
}
