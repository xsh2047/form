package controllers;

import models.SeshUser;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.User.login;

/**
 * Created by Xavier on 8/10/2015.
 */
public class LoginController extends Controller {
    private static final Form<SeshUser> loginForm = Form.form(SeshUser.class);


    public Result userLogin(){
        Form<SeshUser> boundForm = loginForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(login.render(boundForm));
        }
        SeshUser user = boundForm.get();
        if(User.Authenticate(user)){
            return ok(views.html.welcome.render());
        }
        flash("error", "Incorrect Username/Password.");
        return badRequest(login.render(loginForm));
    }

    public Result login(){
        return ok(views.html.User.login.render(loginForm));
    }
}
