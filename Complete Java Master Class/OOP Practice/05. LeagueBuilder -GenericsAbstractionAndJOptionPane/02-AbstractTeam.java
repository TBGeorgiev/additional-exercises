import java.util.concurrent.ThreadLocalRandom;

public abstract class G_AbstractTeam {
    private String name;
    private int age;

    public G_AbstractTeam(String name) {
        this.name = name;
        this.age = ThreadLocalRandom.current().nextInt(18, 40);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
