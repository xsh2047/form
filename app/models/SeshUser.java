package models;

import play.data.validation.Constraints;

/**
 * Created by Xavier on 8/10/2015.
 */
public class SeshUser {

    @Constraints.Required
    public String uname;
    @Constraints.Required
    public String pword;

    public SeshUser(){}

    public SeshUser(String uname, String pword){
        this.uname = uname;
        this.pword = pword;
    }

    public String getUname(){
        return uname;
    }

    public String getPword(){
        return pword;
    }
}
