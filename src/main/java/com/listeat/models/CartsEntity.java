package com.listeat.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "carts", schema = "listeat", catalog = "")
public class CartsEntity {
    private int cartId;
    private Timestamp purchaseTime;

    @Id
    @Column(name = "cart_id")
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "purchase_time")
    public Timestamp getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartsEntity that = (CartsEntity) o;

        if (cartId != that.cartId) return false;
        if (purchaseTime != null ? !purchaseTime.equals(that.purchaseTime) : that.purchaseTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId;
        result = 31 * result + (purchaseTime != null ? purchaseTime.hashCode() : 0);
        return result;
    }
}
