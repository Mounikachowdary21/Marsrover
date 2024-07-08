abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Request1")) {
            System.out.println("Handler 1 handling request.");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Request2")) {
            System.out.println("Handler 2 handling request.");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}

// Client code
public class Behavioralchainofresponsibility{
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);

        handler1.handleRequest("Request1"); // Output: Handler 1 handling request.
        handler1.handleRequest("Request2"); // Output: Handler 2 handling request.
    }
}
