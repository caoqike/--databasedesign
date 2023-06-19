package Entity;
import User.User;
import User.UserType;
import User.Menu;
/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9 19:29
 */

public class Teacher {
    private String id;//导师工号
    private String name;//导师姓名



    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }



}
