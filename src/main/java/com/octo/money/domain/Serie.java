package com.octo.money.domain;

import java.io.Serializable;
import java.util.List;

/**
 * A OperationCategorie.
 */
public class Serie implements Serializable {

    private String key;

    private List<SerieValue> values;

    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<SerieValue> getValues() {
		return values;
	}

	public void setValues(List<SerieValue> values) {
		this.values = values;
	}

	@Override
    public String toString() {
        return "Serie{" +
                "key=" + key +
                ", values={" + values + "}" +
                '}';
    }
}
