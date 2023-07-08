package com.lilystu.furns.service.impl;

import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.dao.impl.FurnDAOImpl;
import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.entity.Page;
import com.lilystu.furns.service.FurnsService;

import java.util.List;

public class FurnsServiceImpl implements FurnsService {
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> queryFurns() {
        return furnDAO.queryFurns();
    }

    @Override
    public int addFurn(Furn furn) {
        return furnDAO.addFurn(furn);
    }

    @Override
    public int deleteFurnById(int id) {
        return furnDAO.deleteFurnById(id);
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    @Override
    public Page<Furn> page(int pageNO, int pageSize) {
        //先创建一个page对象再一个一个填充数据
        Page<Furn> furnPage = new Page<>();
        furnPage.setPageNo(pageNO);
        furnPage.setPageSize(pageSize);

        int totalRow = furnDAO.getTotalRow();
        furnPage.setTotalRow(totalRow);

        //一共有多少页，涉及一个小小的算法
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            pageTotalCount += 1;
        }
        furnPage.setPageTotalCount(pageTotalCount);

        //每页显示的数据
        int begin = (pageNO - 1) * pageSize;
        List<Furn> pageItem = furnDAO.getPageItem(begin, pageSize);
        furnPage.setItems(pageItem);

        //url与分页导航相关先放一放

        return furnPage;
    }

    @Override
    public Page<Furn> pageByName(int pageNO, int pageSize, String name) {
        //先创建一个page对象再一个一个填充数据
        Page<Furn> furnPage = new Page<>();
        furnPage.setPageNo(pageNO);
        furnPage.setPageSize(pageSize);

        int totalRow = furnDAO.getTotalRowByName(name);
        furnPage.setTotalRow(totalRow);

        //一共有多少页，涉及一个小小的算法
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            pageTotalCount += 1;
        }
        furnPage.setPageTotalCount(pageTotalCount);

        //每页显示的数据
        int begin = (pageNO - 1) * pageSize;
        List<Furn> pageItem = furnDAO.getPageItemByName(begin, pageSize, name);
        furnPage.setItems(pageItem);

        //url相关先放一放
        return furnPage;
    }
}
