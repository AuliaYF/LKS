/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auliayf.bn.libs;

import java.util.HashMap;

/**
 * Base Database Model
 *
 * @author auliayf
 */
public class db_model {

    private final HashMap<String, Object> mObjs = new HashMap<>();

    /**
     * Setter for field data
     *
     * @param key Field Name
     * @param val Field Value
     */
    public void set(String key, Object val) {
        this.mObjs.put(key, val);
    }

    /**
     * Getter for field data
     *
     * @param key Field Name
     * @return Field Value
     */
    public Object get(String key) {
        return this.mObjs.get(key);
    }
}
