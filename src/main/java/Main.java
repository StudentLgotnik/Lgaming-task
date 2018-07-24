
import com.project.lgaming.MyConnection;

import java.io.IOException;
import java.security.SignatureException;

public class Main {
    private static final String VERIFY_PATH = "D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\verify.xml";
    private static final String PAYMENT_PATH = "D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\payment.xml";
    private static final String STATUS_PATH = "D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\status.xml";

    public static void main(String[] args) {
        try {
            MyConnection.connectLGaming(VERIFY_PATH);
            MyConnection.connectLGaming(PAYMENT_PATH);
            MyConnection.connectLGaming(STATUS_PATH);
        } catch (SignatureException | IOException e) {
            e.printStackTrace();
        }
    }
}
