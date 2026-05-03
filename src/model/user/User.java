package model.user;

import java.util.Objects;

public class User {
    protected int id;
    protected String login;
    protected String password;
    protected String name;

    public User(){}

    public User(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getName(){ return name; }
    public String getLogin(){ return login; }
    public String getPassword(){ return password; }

    @Override
    public String toString(){
        return "User: "+name;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;

    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
