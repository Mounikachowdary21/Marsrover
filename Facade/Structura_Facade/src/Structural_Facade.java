class SubsystemA {
    public void operationA() {
        System.out.println("Subsystem A operation.");
    }
}

class SubsystemB {
    public void operationB() {
        System.out.println("Subsystem B operation.");
    }
}

class Facade {
    private SubsystemA subsystemA;
    private SubsystemB subsystemB;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
    }

    public void operation() {
        subsystemA.operationA();
        subsystemB.operationB();
    }
}

// Client code
public class  Structural_Facade {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation(); // Output: Subsystem A operation. Subsystem B operation.
    }
}
