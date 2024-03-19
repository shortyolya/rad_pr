public class HelloWorldPrinter {
    public static void main(String[] args) {
        while (true) {
            System.out.println("hello world");
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
