package com.lilystu.service;

import com.lilystu.entity.Monster;

import java.util.List;

public interface IMonsterService {
    List<Monster> listMonsters();
    List<Monster> findMonstersByName(String name);
}
