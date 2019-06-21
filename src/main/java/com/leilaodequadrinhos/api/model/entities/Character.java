package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer characterID;
    private String name;

    public Character() {
        super();
    }

    public Character(Integer characterID, String name) {
        super();
        this.characterID = characterID;
        this.name = name;
    }

    public Integer getCharacterID() {
        return characterID;
    }

    public void setCharacterID(Integer characterID) {
        this.characterID = characterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((characterID == null) ? 0 : characterID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Character other = (Character) obj;
        if (characterID == null) {
            return other.characterID == null;
        } else return characterID.equals(other.characterID);
    }

    @Override
    public String toString() {
        return "Character [characterID=" + characterID + ", name=" + name + "]";
    }
}