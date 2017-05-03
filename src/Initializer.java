import controller.ControllerLogin;
import model.ModelLogin;
import view.LoginUI;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class Initializer {
    public static void main(String[] args) {
        // Create objects.
        ModelLogin ml = new ModelLogin();
        ControllerLogin cl = new ControllerLogin(ml);
        LoginUI lu = new LoginUI();

        lu.setMVC(ml, cl);
        lu.initLoginUI();
    }
}
