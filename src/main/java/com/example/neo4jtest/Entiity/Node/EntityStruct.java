package com.example.neo4jtest.Entiity.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class EntityStruct implements Serializable {
    private static final long serialVersionUID=1L;

    public static final String KEY_TYPENAME="typename";
    public static final String KEY_ATTRIBUTES="attributes";
    public static final String KEY_Name="name";
    public static final String SERIALIZED_DATE_FORMAT_STR="yyyyMMdd-HH:mm:ss.SSS-Z";
    @Deprecated
    public static final DateFormat DATE_FORMATTER =new SimpleDateFormat(SERIALIZED_DATE_FORMAT_STR);
    @Id
    private Long guid;
    private String typeName;
    private String name;

    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private Map<String,Object> attributes;

    public static Long nextInternalId(){
        return s_nextId.getAndIncrement();
    }

    public EntityStruct() {
        this(null, null);
    }

    public EntityStruct(String typeName) {
        this(typeName, null);
    }
    public EntityStruct(EntityStruct other) {
        if (other != null) {
            setTypeName(other.getTypeName());
            setAttributes(new HashMap<>(other.getAttributes()));
        }
    }

    public EntityStruct(String typeName, Map<String, Object> attributes) {
        setTypeName(typeName);
        setAttributes(attributes);
    }

    public EntityStruct(String typeName, String attrName, Object attrValue) {
        setTypeName(typeName);
        setAttribute(attrName, attrValue);
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public void setAttribute(String name, Object value) {
        Map<String, Object> a = this.attributes;

        if (a != null) {
            a.put(name, value);
        } else {
            a = new HashMap<>();
            a.put(name, value);

            this.attributes = a;
        }
    }
    public boolean hasAttribute(String name) {
        Map<String, Object> a = this.attributes;

        return a != null ? a.containsKey(name) : false;
    }
    public Object getAttribute(String name) {
        Map<String, Object> a = this.attributes;

        return a != null ? a.get(name) : null;
    }
    public Object removeAttribute(String name) {
        Map<String, Object> a = this.attributes;

        return a != null ? a.remove(name) : null;
    }
    public static StringBuilder dumpObjects(Map<?, ?> objects, StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        if (objects!=null&&!objects.isEmpty()) {
            int i = 0;
            for (Map.Entry<?, ?> e : objects.entrySet()) {
                if (i > 0) {
                    sb.append(", ");
                }

                sb.append(e.getKey()).append(":").append(e.getValue());
                i++;
            }
        }

        return sb;
    }
    public static StringBuilder dumpDateField(String prefix, Date value, StringBuilder sb) {
        sb.append(prefix);

        if (value == null) {
            sb.append(value);
        } else {
            sb.append(getDateFormatter().format(value));
        }

        return sb;
    }
    public static DateFormat getDateFormatter() {
        return THREAD_LOCAL_DATE_FORMAT.get();
    }

    private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            DateFormat ret = new SimpleDateFormat(SERIALIZED_DATE_FORMAT_STR);

            ret.setTimeZone(TimeZone.getTimeZone("UTC"));

            return ret;
        }
    };
    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("EntityStruct{");
        sb.append("typeName='").append(typeName).append('\'');
        sb.append(", attributes=[");
        dumpObjects(attributes, sb);
        sb.append("]");
        sb.append('}');

        return sb;
    }
    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityStruct that = (EntityStruct) o;

        return Objects.equals(typeName, that.typeName) &&
                Objects.equals(attributes, that.attributes);
    }
}
