import com.project.lgaming.MyConnection;


import java.io.IOException;

public class Main {

    private static final String REUEST_PATH = "D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\request.xml";

    public static void main(String[] args) {
        try {

//            System.out.println(Convertors.PemToPrivateKey("D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\private.pem"));
            MyConnection.connectLGaming(REUEST_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
