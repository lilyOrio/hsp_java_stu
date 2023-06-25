package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.entity.Furn;

import java.util.List;

public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {
    @Override
    public List<Furn> queryFurns() {
        String sql = "SELECT `id`, `NAME` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn";
        return queryMulti(sql, Furn.class);
    }

    @Override
    public int addFurn(Furn furn) {
        String sql = "INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , " +
                "`stock` , `img_path`) " +
                "VALUES(NULL , ? , ? , ? , ? , ? ,?)";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock()
                , furn.getImgPath());
    }

    @Override
    public int deleteFurnById(int id) {
        String sql = "DELETE FROM `furn` WHERE `id`=?";
        return update(sql, id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql = "SELECT `id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`imgPath" +
                " FROM furn where `id`=?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "UPDATE `furn` SET `name`=?,`maker`=?,`price`=?,`sales`=?,`stock`=?," +
                "`img_path`=? WHERE `id`=?";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(),
                furn.getStock(), furn.getImgPath(), furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql = "select count(*) From `furn`";
        return ((Number) queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItem(int pageBegin, int pageSize) {
        String sql = "SELECT `id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`imgPath" +
                " FROM furn LIMIT ?,?";
        return queryMulti(sql, Furn.class, pageBegin, pageSize);
    }

    @Override
    public int getTotalRowByName(String name) {
        String sql = "select count(*) From `furn` WHERE `name` LIKE ?";
        return ((Number) queryScalar(sql, "%" + name + "%")).intValue();
    }

    @Override
    public List<Furn> getPageItemByName(int pageBegin, int pageSize, String name) {
        String sql = "SELECT `id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`imgPath" +
                " FROM furn WHERE `name` LIKE ? LIMIT ?,?";
        return queryMulti(sql, Furn.class, "%" + name + "%", pageBegin, pageSize);
    }
}
