import java.util.List;

public interface DBActions<T> {
    public List getData();

    public void saveData(List<T> data);
}
