package controllers;

import models.SeshUser;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.User.login;
import views.html.User.signup;

/**
 * Created by Xavier on 8/12/2015.
 */
public class UsersCtrl extends Controller {
    private static final Form<User> userForm = Form.form(User.class);
    private static final Form<SeshUser> loginForm = Form.form(SeshUser.class);


    public Result index(){
        return  ok(views.html.User.signup.render(userForm));
    }

    public Result store(){
        Form<User> boundForm = userForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            Logger.debug(boundForm.errorsAsJson().asText());
            return badRequest(signup.render(boundForm));
        }
        User user = boundForm.get();
        if(user.exists()){
            flash("error", "User already exists, Try Again.");
            Logger.debug("Second Error");
            return badRequest(signup.render(userForm));
        }else{
            flash("success", String.format("Successfully added User %s", user));
            user.save();
        }
        return redirect(routes.UsersCtrl.index());
    }

    public Result enter(){
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
