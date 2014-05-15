package converter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class converterTest {
	@DataProvider(name = "TestingNumbers")
	public Object[][] numbersArabicRoman() {
		return new Object[][] { { "000000000", "000000000" },
				{ "111111111", "111111111" },
				{ "111111112","11111111A\n11111111B\n11111111C" },
				{ "111111113","11111111D\n11111111E\n11111111F" },
				{ "111111114","11111111G\n11111111H\n11111111I" },
				{ "111111115","11111111J\n11111111K\n11111111L" },
				{ "111111116","11111111M\n11111111N\n11111111O" },
				{ "111111117","11111111P\n11111111Q\n11111111R\n11111111S" },
				{ "111111118","11111111T\n11111111U\n11111111V" },
				{ "111111119","11111111W\n11111111X\n11111111Y\n11111111Z" },
				};
	}

	@Test(dataProvider = "TestingNumbers")
	public void phoneNumberToConvertedNumber(String phoneNumber, String convertedNumber) {
		// Arrange
		NumberConverter converter = new NumberConverter();
		// Act
		String result = converter.convert(phoneNumber);
		// Assert
		Assert.assertEquals(result, convertedNumber);
	}
}
