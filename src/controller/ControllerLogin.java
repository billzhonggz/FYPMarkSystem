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
        // TODO: Bug fix: Unreachable statement.
        boolean modelCheckResult = ml.checkPwd(pwd);
        return modelCheckResult;
    }

}
