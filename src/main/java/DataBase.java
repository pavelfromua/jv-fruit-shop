import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBase implements DbActions<Good> {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data == null ? new ArrayList<>() : data;
    }

    private List<Good> prepareData(List<String> data) throws Exception {
        List<Good> table = new ArrayList<>();

        try {
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i).split(",");

                Type type = Enum.valueOf(Type.class, row[0].toUpperCase());
                String fruit = row[1];
                int quantity = Integer.parseInt(row[2]);
                LocalDate date = LocalDate.parse(row[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                table.add(new Good(type, fruit, quantity, date));
            }
        } catch (Exception e) {
            //here we have problem
        }

        return table;
    }

    public boolean displayInfo() {
        for (Good good: getData()) {
            System.out.println(good);
        }

        return true;
    }
}
