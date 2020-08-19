import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private static DataBase db;

    public Shop(Path path) {
        if (!setDbConnection(path)) {
            System.out.println("The connection with database isn't set");
        };
    }

    public Shop() {}

    public boolean displayActualData() {
        Map<String, Integer> data = getActualData();

        data.forEach((key, value) -> System.out.println(key + "," + value));

        return data.size() >= 0 ? true : false;
    }

    public Map<String, Integer> getActualData() {
        Map<String, Integer> result = new HashMap<>();

        if (getDbConnection() == null) {
            return result;
        }

        List<Good> data = db.getData();

        for (Good good: data) {
            String key = good.getFruit();
            int value;

            switch (good.getType()) {
                case S: value = good.getQuantity();
                        break;
                case B: value = -good.getQuantity();
                        break;
                case R: value = good.getQuantity();
                        break;
                default: value = 0;
            }

            if (result.containsKey(key)) {
                result.replace(key, result.get(key) + value);
            } else {
                result.put(key, value);
            }
        }

        return result;
    }

    public boolean setDbConnection(Path path) {
        if (path == null) {
            path = Path.of("C:\\Projects\\JavaStudy\\jv-fruit-shop\\DB.csv");
        }

        return (db = new DataBase(path)) != null;
    }

    public DataBase getDbConnection() {
        return this.db;
    }
}
