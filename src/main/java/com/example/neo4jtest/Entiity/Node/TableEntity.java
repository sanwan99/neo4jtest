package com.example.neo4jtest.Entiity.Node;

import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NodeEntity("Table")
public class TableEntity extends EntityStruct implements Serializable {

    private static final long serialVersionUID=1L;

    public static final String KEY_GUID            = "guid";
    public static final String KEY_CREATED_BY      = "createdBy";
    public static final String KEY_UPDATED_BY      = "updatedBy";
    public static final String KEY_CREATE_TIME     = "createTime";
    public static final String KEY_UPDATE_TIME     = "updateTime";
    public static final String KEY_VERSION         = "version";



    private String type;
//    private  String name           =null;
    private String  createdBy      = null;
    private String  updatedBy      = null;
    private Date    createTime     = null;
    private Date    updateTime     = null;
    private Long    version        = 0L;

    private Map<String, Object> relationshipAttributes;
    private Map<String, Map<String, Object>> businessAttributes;

    @Relationship(type = "Process_INPUT_EDGE")
    private List<ProcessRelation> processRelationList;

    public void addProcessRelation(ProcessRelation processRelationList){
        if(this.processRelationList == null){
            this.processRelationList = new ArrayList<>();
        }
        this.processRelationList.add(processRelationList);
    }

    public TableEntity() {
        this(null, null);
    }


    public TableEntity(String typeName) {

        this(typeName, null);

    }
    public TableEntity(String typeName, Map<String, Object> attributes) {
        super(typeName, attributes);

        init();
    }
    public TableEntity(String typeName, String attrName, Object attrValue) {
        super(typeName, attrName, attrValue);

        init();
    }
    public TableEntity(TableEntity other) {
        super(other);

        if (other != null) {
            setGuid(other.getGuid());
            setCreatedBy(other.getCreatedBy());
            setUpdatedBy(other.getUpdatedBy());
            setCreateTime(other.getCreateTime());
            setUpdateTime(other.getUpdateTime());
            setVersion(other.getVersion());
//            setClassifications(other.getClassifications());
            setRelationshipAttributes(other.getRelationshipAttributes());
            setBusinessAttributes(other.getBusinessAttributes());

        }
    }

    private void init() {
        setGuid(nextInternalId());
        setCreatedBy(null);
        setUpdatedBy(null);
        setCreateTime(null);
        setUpdateTime(null);
        setRelationshipAttributes(null);
        setBusinessAttributes(null);

    }
    public Map<String, Object> getRelationshipAttributes() { return relationshipAttributes; }

    public void setRelationshipAttributes(Map<String, Object> relationshipAttributes) {
        this.relationshipAttributes = relationshipAttributes;
    }

    public void setRelationshipAttribute(String name, Object value) {
        Map<String, Object> r = this.relationshipAttributes;

        if (r != null) {
            r.put(name, value);
        } else {
            r = new HashMap<>();
            r.put(name, value);

            this.relationshipAttributes = r;
        }
    }

    public Object getRelationshipAttribute(String name) {
        Map<String, Object> a = this.relationshipAttributes;

        return a != null ? a.get(name) : null;
    }

    public boolean hasRelationshipAttribute(String name) {
        Map<String, Object> r = this.relationshipAttributes;

        return r != null ? r.containsKey(name) : false;
    }
    @Override
    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("TableEntity{");
        super.toString(sb);
        sb.append("guid='").append(getGuid()).append('\'');
        sb.append(", createdBy='").append(createdBy).append('\'');
        sb.append(", updatedBy='").append(updatedBy).append('\'');
        dumpDateField(", createTime=", createTime, sb);
        dumpDateField(", updateTime=", updateTime, sb);
        sb.append(", version=").append(version);
        sb.append(", relationshipAttributes=[");
        dumpObjects(relationshipAttributes, sb);
        sb.append("]");
        sb.append(", classifications=[");
        sb.append(']');
        sb.append(", businessAttributes=[");
        dumpObjects(businessAttributes, sb);
        sb.append("]");
        sb.append('}');

        return sb;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }

        TableEntity that = (TableEntity) o;
        return Objects.equals(getGuid(), that.getGuid()) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(version, that.version) &&
                Objects.equals(relationshipAttributes, that.relationshipAttributes) &&
                Objects.equals(businessAttributes, that.businessAttributes) ;
    }
    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
}
