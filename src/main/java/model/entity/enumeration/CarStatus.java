package model.entity.enumeration;

public enum CarStatus {
    IN_RENT(1),
    IS_FREE(2);

    private int id;

    CarStatus(int id){
        this.id = id;
    }

    CarStatus(){}

    public int getId() {
        return id;
    }

    public static CarStatus getCarStatus(int value){
        for(CarStatus status : values())
            if(status.getId() == value){
                return status;
            }
        throw new IllegalArgumentException();
    }
}
