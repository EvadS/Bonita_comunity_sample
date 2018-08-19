package org.bonitasoft.connectors.chain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Asset implements Serializable {


    // linear id
    // private String id;
    // my model
    private Map<String,Serializable> definition;

    public Asset(Map<String, Object> definition) {
        this.definition= new HashMap<String, Serializable>();
        for (Map.Entry<String, Object> e: definition.entrySet()) {
            this.definition.put(e.getKey(), (Serializable)e.getValue());
        }
    }
}
