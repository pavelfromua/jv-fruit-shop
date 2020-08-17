import java.util.List;

public interface DbActions<T> {
    public List getData();

    public void saveData(List<T> data);
}
