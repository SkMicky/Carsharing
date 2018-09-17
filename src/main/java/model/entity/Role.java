package model.entity;

public enum Role {
    ADMIN("Admin"), CLIENT("Client");

    private String userRole;

    Role(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
