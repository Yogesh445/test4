package com.fynd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse_items")
@Setter
@Getter
public class WarehouseItems {

    @Id
    private Long id;

    @JoinColumn(name = "warehouse_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_color")
    private String itemColor;

}
