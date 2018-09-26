package JDBC;

import Const.Level;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Manager implements QueryAble,Verify,InsertAble{

    protected Database control;

    public void setControl(Database control) {
        this.control = control;
    }
    public Manager(Database control){
        this.control = control;
    }

    @Override
    public int verify(JsonObject object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        return false;
    }
}
