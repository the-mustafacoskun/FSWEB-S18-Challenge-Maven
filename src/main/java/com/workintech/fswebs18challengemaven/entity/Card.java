package com.workintech.fswebs18challengemaven.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "card",schema = "fsweb")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "value")
    private Integer value;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name="color")
    @Enumerated(EnumType.STRING)
    private Color color;

    public Card(long id, Type type, Color color) {

        this.id = id;
        this.value = null;
        this.type = type;
        if(type == Type.JOKER){
            this.color =null;
        }else{
            this.color = color;
        }


    }
    public Card(long id, Integer value, Color color) {
        this.id = id;
        this.value = value;
        this.type=null;
        this.color = color;
    }
}
