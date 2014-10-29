package com.octo.money.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OperationCategorie.
 */
public class PieValue implements Serializable {

    private String key;

    private String y;

    public PieValue(String key, BigDecimal y){
    	this(key, y.toString());
    }
    
    public PieValue(int key, BigDecimal y){
    	this(Integer.toString(key), y.toString());
    }
    
    public PieValue(String key, String y){
    	this.key = key;
    	this.y = y;
    }
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Override
    public String toString() {
        return "Serie{" +
                "key=" + key +
                ", y=" + y + "" +
                '}';
    }
}
