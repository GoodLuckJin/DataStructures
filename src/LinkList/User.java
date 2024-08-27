package LinkList;

/**
 * 人物Entity
 * @ClassName User
 * @Author jjjson
 * @Date 2024/8/25 21:27
 */
public class User extends Node<User>{

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

}
