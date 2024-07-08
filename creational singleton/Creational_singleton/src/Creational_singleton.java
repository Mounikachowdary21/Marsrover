class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("Singleton instance method.");
    }
}

// Client code
public class Creational_singleton {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.doSomething(); // Output: Singleton instance method.
    }
}
