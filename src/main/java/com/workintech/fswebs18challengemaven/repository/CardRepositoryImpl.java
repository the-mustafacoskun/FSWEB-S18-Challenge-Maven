package com.workintech.fswebs18challengemaven.repository;


import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository {

    EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Card save(Card card) {
        CardValidation.isNull(card);
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        CardValidation.isNull(color);
        Color colorEnum = Color.valueOf(color);
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color=:color ", Card.class);
        query.setParameter("color", colorEnum);
        List<Card> cards = query.getResultList();
        if (cards.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return cards;

    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    public List<Card> findByValue(Integer value) {
        CardValidation.isNull(value);
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value=:value ", Card.class);
        query.setParameter("value", value);
        List<Card> cards = query.getResultList();
        if (cards.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return cards;
    }

    @Override
    public List<Card> findByType(String type) {
        CardValidation.isNull(type);
        Type typeEnum = Type.valueOf(type);
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type=:type ", Card.class);
        query.setParameter("type", typeEnum);
        List<Card> cards = query.getResultList();
        if (cards.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return cards;
    }

    @Override
    public Card findById(Long id) {
        CardValidation.isIdValid(id);
       Card card = entityManager.find(Card.class, id);
       if (card == null) {
           throw new CardException("Card not found", HttpStatus.NOT_FOUND);
       }
       return card;
    }

    @Override
    @Transactional
    public Card update(Card card) {
        CardValidation.isNull(card);

        Card updatedCard = entityManager.merge(card);
        if(updatedCard==null){
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return updatedCard;
    }

    @Override
    @Transactional
    public Card remove(Long id) {
        CardValidation.isIdValid(id);
        Card card = entityManager.find(Card.class, id);
        entityManager.remove(card);
        return card;
    }


}
