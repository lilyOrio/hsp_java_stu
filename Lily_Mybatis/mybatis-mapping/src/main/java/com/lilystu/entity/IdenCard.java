package com.lilystu.entity;

public class IdenCard {
    private Integer id;
    private String card_sn;

    public IdenCard() {
    }

    public IdenCard(Integer id, String card_sn) {
        this.id = id;
        this.card_sn = card_sn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_sn() {
        return card_sn;
    }

    public void setCard_sn(String card_sn) {
        this.card_sn = card_sn;
    }

    @Override
    public String toString() {
        return "IdenCard{" +
                "id=" + id +
                ", card_sn='" + card_sn + '\'' +
                '}';
    }
}
