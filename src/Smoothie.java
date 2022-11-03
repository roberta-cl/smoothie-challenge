import java.util.List;

public class Smoothie {
    private static int count = 1;

    private Integer id;
    private String name;
    private List<String> ingredients;

    public Smoothie(String name, List<String> ingredients) {
        this.id = count;
        this.name = name;
        this.ingredients = ingredients;
        Smoothie.count += 1;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Smoothie.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
