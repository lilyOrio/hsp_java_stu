package com.lilystu.furns.service;

import com.lilystu.furns.entity.Furn;

import java.util.List;

public interface FurnsService {
    List<Furn> queryFurns();
    int addFurn(Furn furn);
    int deleteFurnById(int id);
    Furn queryFurnById(int id);
    int updateFurn(Furn furn);
}
