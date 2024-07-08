// Legacy class
class LegacySystem {
    public void legacyMethod() {
        System.out.println("Legacy system method.");
    }
}

// Target interface
interface Target {
    void request();
}

// Adapter class
class Adapter implements Target {
    private LegacySystem legacySystem;

    public Adapter(LegacySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void request() {
        legacySystem.legacyMethod();
    }
}

// Client code
public class Structural_Adapter{
    public static void main(String[] args) {
        LegacySystem legacySystem = new LegacySystem();
        Target target = new Adapter(legacySystem);
        target.request(); // Output: Legacy system method.
    }
}
