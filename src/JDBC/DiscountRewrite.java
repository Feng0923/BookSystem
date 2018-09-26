package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;

public class DiscountRewrite extends Manager {
    public DiscountRewrite(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException,NumberFormatException {

        String discount0 = jsonObject.get("discount0").getAsString();
        String discount1 = jsonObject.get("discount1").getAsString();
        if (!discount0.isEmpty()){
            double v = Double.parseDouble(discount0);
            String sql = "update discount set discount_size = "+v+" where level = 2";
            control.update(sql);
        }
        if (!discount1.isEmpty()){
            double v = Double.parseDouble(discount1);
            String sql = "update discount set discount_size = "+v+" where level = 3";
            control.update(sql);
        }

        return true;
    }
}
