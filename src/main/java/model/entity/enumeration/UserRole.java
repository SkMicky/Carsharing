package model.entity.enumeration;

public enum UserRole {
    GUEST(0),
    CLIENT(2),
    ADMIN(1);

    private int id;

    UserRole(int id){
        this.id = id;
    }

    UserRole(){}

    public int getId() {
        return id;
    }

    public static UserRole getUserRole(int value){
        for(UserRole role : values())
            if(role.getId() == value){
                return role;
            }
            throw new IllegalArgumentException();
    }
}
