package User;

import java.io.Serializable;

public  abstract  class  User implements Serializable,Menu{
    private static final long serialVersionUID = 1;
        UserType type;
        String loadname;//id
        String passwd;


    public abstract void menu () ;

    public User(UserType type, String loadname, String passwd) {
            this.type = type;
            this.loadname = loadname;
            this.passwd = passwd;
        }


    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLoadname() {
        return loadname;
    }

    public void setLoadname(String loadname) {
        this.loadname = loadname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "type=" + type +
                ", loadname='" + loadname + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
