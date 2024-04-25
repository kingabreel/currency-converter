import controller.CurrencyController;
import model.Currency;
import org.junit.Assert;
import org.junit.Test;

public class TestApp {

    @Test
    public void modelCreation(){
        Currency currency = new Currency("USD", 20, "BRL", 4.502, "10/20/2003");

        Assert.assertEquals("USD", currency.code());
        Assert.assertNotEquals("AUD", currency.currencyToConvert());
        Assert.assertEquals(20, currency.quantity());
    }

    @Test
    public void getRightCurrencyValueConverted(){
        Currency currency = new Currency("USD", 20, "BRL", 4.502, "10/20/2003");

        double result = currency.quantity() * currency.conversion_rate();
        Assert.assertEquals(90.04, result, 0.001);
    }

    @Test
    public void availableCurrencyRightResult(){
        CurrencyController c = new CurrencyController();

        boolean test1 = c.isCurrencyAvailable("USD");
        boolean test2 = c.isCurrencyAvailable("VII");
        boolean test3 = c.isCurrencyAvailable("a");
        boolean test4 = c.isCurrencyAvailable("");
        boolean test5 = c.isCurrencyAvailable("2");
        boolean test6 = c.isCurrencyAvailable("$@");

        Assert.assertEquals(true, test1);
        Assert.assertNotEquals(true, test2);
        Assert.assertEquals(false, test3);
        Assert.assertNotEquals(true, test4);
        Assert.assertNotEquals(true, test5);
        Assert.assertNotEquals(true, test6);
    }

    @Test
    public void testResponseFromAPI(){
        CurrencyController c = new CurrencyController();

        Currency currency = c.startConvertion("USD", 100, "BRL");

        double result = currency.quantity() * currency.conversion_rate();

        Assert.assertEquals(c.getValueConverted(currency), result, 0.001);
    }
}
