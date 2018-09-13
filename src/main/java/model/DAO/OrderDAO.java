package model.DAO;

public class OrderDAO {

    private final String GET_ALL_ORDERS = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID";

    private final String GET_ORDER_BY_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE ORDER_ID = ?";

    private final String GET_ORDER_BY_USER_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE USER_ID = ?";

    private final String GET_ORDER_BY_CAR_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE USER_ID = ?";

    private final String ADD_ORDER = "INSERT INTO ORDER ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?, DISCOUNT = ?" +
            "INSERT INTO ORDER_DETAIL ORDER_ID = ?, CAR_ID = ?, TARIF = ?";

    private final String UPDATE_ORDER = "UPDATE ORDER SET ORDER_ID = ?, ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?, DISCOUNT = ? " +
            "UPDATE ORDER_DETAIL SET ORDER_DETAIL_ID = ?, ORDER_ID = ?, CAR_ID = ?, TARIF = ? " +
            "WHERE ORDER_ID = ?";

    private final String DELETE_ORDER = "DELETE FROM ORDER WHERE ORDER_ID = ?";


}
