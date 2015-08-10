package models;


import play.Logger;
import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String user;
    @Constraints.Required
    public String pword;
    private static List<User> users;
    static {
        users = new ArrayList<User>();
        users.add(new User("johndoe", "john_doe@live.com",
                "password1"));
        users.add(new User("janedoe", "jane_doe@live.com",
                "password2"));
        users.add(new User("johnsmith", "john_smith@live.com",
                "password3"));
        users.add(new User("janesmith", "jane_smith@live.com",
                "password4"));
        users.add(new User("iambob", "iambob@live.com",
                "password5"));
    }

    public User(){}

    public User(String user,String email,String pword){
        this.user = user;
        this.email = email;
        this.pword = pword;
    }

    public String getUsername(){
        return this.user;
    }

    public String getEmail(){
        return this.email;
    }

    public static boolean Authenticate(SeshUser stranger){
        Logger.debug("Auth Working");
        for(User member: users){
            Logger.debug("Loop is Working too");
            Logger.debug(member.getUsername() + " - " + stranger.getUname());
            if(member.getUsername().equals(stranger.getUname()) || member.getEmail().equals(stranger.getUname())){
                Logger.debug("UserName/Email Verified");
                if (member.pword.equals(stranger.getPword())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exists(){
        for(User member: users){
            if(member.getUsername().equals(this.getUsername())){
                return true;
            }
        }
        return false;
    }

    public void save() {
        users.add(this);
    }

    public String toString(){
        return this.user + " - " + this.email;
    }
}
