package com.family.fmlbase.base;

import java.io.Serializable;

public class BaseDO implements Serializable {
    public BaseDO() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseDO)) {
            return false;
        } else {
            BaseDO other = (BaseDO)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseDO;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "BaseDO()";
    }
}