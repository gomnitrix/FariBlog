package com.gomnitrix.commons.Response;

import com.gomnitrix.commons.utils.JsonUtil;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Response {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data;

    abstract static class Builder<T extends Builder<T>> {
        Map<String, Object> data = new HashMap<>();
        Integer code;

        /**
         * add a pair of key-value to the data
         *
         * @param key
         * @param value
         * @return
         */
        public T addItem(String key, Object value) {
            data.put(key, value);
            return self();
        }

        /**
         * add multiple new data to the data
         *
         * @param newData
         * @return
         */
        public T addItems(Map<String, Object> newData) {
            if (!ObjectUtils.isEmpty(newData)) {
                this.data.putAll(newData);
            }
            return self();
        }

        public T setCode(Integer code) {
            this.code = code;
            return self();
        }

        public void clear() {
            data.clear();
            code = null;
        }

        abstract Response build();

        protected abstract T self();
    }

    Response(Builder<?> builder) {
        code = builder.code;
        data = new HashMap<>(builder.data);
        builder.clear();
    }

    /**
     * turn this response object to JSON format.
     *
     * @return
     */
    public String toJson() {
        return JsonUtil.respToJson(this);
    }
}
