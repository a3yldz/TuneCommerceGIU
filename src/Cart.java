import java.util.ArrayList;
import java.util.List;

class Cart {
    private List<String> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }
}
