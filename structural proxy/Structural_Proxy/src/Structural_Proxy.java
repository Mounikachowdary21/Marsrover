interface Service {
    void request();
}

class RealService implements Service {
    @Override
    public void request() {
        System.out.println("Real service request.");
    }
}

class ProxyService implements Service {
    private RealService realService;

    @Override
    public void request() {
        if (realService == null) {
            realService = new RealService();
        }
        System.out.println("Proxy control.");
        realService.request();
    }
}

// Client code
public class Structural_Proxy {
    public static void main(String[] args) {
        Service service = new ProxyService();
        service.request(); // Output: Proxy control. Real service request.
    }
}
