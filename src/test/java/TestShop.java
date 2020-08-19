import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestShop {
    public static Shop shop = new Shop();
    public static Path rightData = Paths.get("DB.csv");
    public static Path wrongData = Path.of("DBw.csv");
    public static final Good good = new Good(Type.S, "peach", 1000, LocalDate.now());

    public TestShop() throws MalformedURLException {
    }

    @Test
    public void isDbHasCorrectData() {
        DataBase db = new DataBase(rightData);

        assertTrue(db.getData().size() > 0);
    }

    @Test
    public void isDbHasWrongData() {
        DataBase db = new DataBase(wrongData);

        assertTrue(db.getData().size() == 0);
    }

    @Test
    public void isDataFromDbPrintable() {
        DataBase db = new DataBase(rightData);

        assertTrue(db.displayInfo());
    }

    @Test
    public void isDataFromDbNonPrintable() {
        DataBase db = new DataBase(wrongData);

        assertTrue(db.displayInfo());
    }

    @Test
    public void checkGetTypeGood() {
        assertEquals(good.getType(), Type.S);
    }

    @Test
    public void checkGetNameGood() {
        assertEquals(good.getFruit(), "peach");
    }

    @Test
    public void checkGetQuantityGood() {
        assertEquals(good.getQuantity(), 1000);
    }

    @Test
    public void checkGetDateGood() {
        assertEquals(good.getDate(), LocalDate.now());
    }

    @Test
    public void isConnectionSet() {
        assertTrue(shop.setDbConnection(rightData));
    }

    @Test
    public void checkAbilityToDisplayActualData() {
        assertTrue(shop.displayActualData());
    }

    @Test
    public void checkAbilityToGetActualData() {
        shop.setDbConnection(rightData);
        assertTrue(shop.getActualData().size() > 0);

        shop.setDbConnection(wrongData);
        assertFalse(shop.getActualData().size() > 0);
    }
}
