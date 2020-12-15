package com.gomnitrix.commons.utils;

import java.lang.reflect.Array;
import java.util.Collection;

public class ArrayUtil {

    public static <T> T[] newArray(Class<T> componentType, int newSize) {
        return (T[])( Array.newInstance(componentType, newSize));
    }

    public static <T> T[] toArray(Collection<T> collection, Class<T> componentType) {
        return collection.toArray(newArray(componentType, 0));
    }
}
