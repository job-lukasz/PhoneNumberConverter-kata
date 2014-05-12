package converter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class converterTest {
	@DataProvider(name = "TestingNumbers")
	public Object[][] numbersArabicRoman() {
		return new Object[][] { { "000000000", "000000000" }, { "111111111", "111111111" },{ "111111112","11111111A\n11111111B\n11111111C" } };
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
