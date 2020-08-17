import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBase implements DBActions<Good> {
    private Path path;

    public DataBase(Path path) {
        this.path = path;
    }

    @Override
    public List<Good> getData() {
        List<Good> data = null;

        if (Files.exists(path)) {
            try {
                 data = prepareData(Files.lines(path).collect(Collectors.toList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data == null ? new ArrayList<>() : data;
    }

    private List<Good> prepareData(List<String> data) {
        List<Good> table = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i).split(",");

            Type type = Enum.valueOf(Type.class, row[0].toUpperCase());
            String fruit = row[1];
            int quantity = Integer.parseInt(row[2]);
            LocalDate date = LocalDate.parse(row[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            table.add(new Good(type, fruit, quantity, date));
        }

        return table;
    }

    public void displayInfo() {
       for (Good good: getData()) {
            System.out.println(good);
        }
    }

    @Override
    public void saveData(List<Good> data) {
        // method for future
    }
}
