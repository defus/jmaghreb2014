package com.octo.money.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OperationCategorie.
 */
public class SerieValue implements Serializable {

    private String key;

    private String value;

    public SerieValue(String key, BigDecimal value){
    	this(key, value.toString());
    }
    
    public SerieValue(int key, BigDecimal value){
    	this(Integer.toString(key), value.toString());
    }
    
    public SerieValue(String key, String value){
    	this.key = key;
    	this.value = value;
    }
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
    public String toString() {
        return "Serie{" +
                "key=" + key +
                ", value=" + value + "" +
                '}';
    }
}
