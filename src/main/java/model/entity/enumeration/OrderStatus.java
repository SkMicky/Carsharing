package model.entity.enumeration;

public enum OrderStatus {
    IS_EXECUTING(1),
    IS_DONE(2);

    private int id;

    OrderStatus(int id){
        this.id = id;
    }

    OrderStatus(){}

    public int getId() {
        return id;
    }

    public static OrderStatus getOrderStatus(int value){
        for(OrderStatus status : values())
            if(status.getId() == value){
                return status;
            }
        throw new IllegalArgumentException();
    }
}
