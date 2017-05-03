package controller;

import model.ModelLogin;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ControllerLogin {
    private ModelLogin ml;

    public ControllerLogin(ModelLogin ml) {
        this.ml = ml;
    }

    public boolean checkPwd(String pwd) {
        return ml.checkPwd(pwd);
    }
}
