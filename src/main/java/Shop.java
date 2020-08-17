import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Shop {
    final public static Path path = Path.of("C:\\Projects\\JavaStudy\\jv-fruit-shop\\DB.csv");

    public static void displayActualData(List<Good> inputData) {
        Map<String, Integer> data = getActualData(inputData);

        data.forEach((key, value) -> System.out.println(key + "," + value));
    }

    public static Map<String, Integer> getActualData(List<Good> data) {
        Map<String, Integer> result = new HashMap<>();

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

    public static void main(String[] args) {
        DataBase db = new DataBase(path);

        displayActualData(db.getData());
    }
}
