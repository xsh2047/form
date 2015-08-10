package controllers;

import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.User.signup;

public class SignUpController extends Controller {
    private static final Form<User> userForm = Form.form(User.class);

    public Result users(){
        return TODO;
    }

    public  Result signUp(){
        return  ok(views.html.User.signup.render(userForm));
    }

    public Result save(){
        Form<User> boundForm = userForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            Logger.debug(boundForm.errorsAsJson().asText());
            return badRequest(signup.render(boundForm));
        }
        User user = boundForm.get();
        if(user.exists()){
            flash("error", "User already Exists Try Again.");
            Logger.debug("Second Error");
            return badRequest(signup.render(userForm));
        }else{
            flash("success", String.format("Successfully added User %s", user));
            user.save();
        }
        return redirect(routes.SignUpController.signUp());
    }
}
