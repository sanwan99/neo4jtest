package com.example.neo4jtest.Entiity.Lineage;

import java.util.Objects;


public class LineageRelation {
    private String fromEntityName;
    private String toEntityName;
    private Long relationshipId;
    private Long fromEntityId;
    private Long toEntityId;

    public LineageRelation() { }

    public LineageRelation(Long fromEntityId, Long toEntityId, final Long relationshipId) {
        this.fromEntityId = fromEntityId;
        this.toEntityId   = toEntityId;
        this.relationshipId = relationshipId;
    }
    public LineageRelation(String fromEntityName, String toEntityName, final Long relationshipId) {
        this.fromEntityName = fromEntityName;
        this.toEntityName   = toEntityName;
        this.relationshipId = relationshipId;
    }
    public String getFromEntityName() {
        return fromEntityName;
    }

    public void setFromEntityName(String fromEntityName) {
        this.fromEntityName = fromEntityName;
    }

    public String getToEntityName() {
        return toEntityName;
    }

    public void setToEntityName(String toEntityName) {
        this.toEntityName = toEntityName;
    }


    public Long getFromEntityId() {
        return fromEntityId;
    }

    public void setFromEntityId(Long fromEntityId) {
        this.fromEntityId = fromEntityId;
    }

    public Long getToEntityId() {
        return toEntityId;
    }

    public void setToEntityId(Long toEntityId) {
        this.toEntityId = toEntityId;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(final Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineageRelation that = (LineageRelation) o;
        return Objects.equals(fromEntityId, that.fromEntityId) &&
                Objects.equals(toEntityId, that.toEntityId) &&
                Objects.equals(relationshipId, that.relationshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromEntityId, toEntityId, relationshipId);
    }

    @Override
    public String toString() {
        return "LineageRelation{" +
                "fromEntityId='" + fromEntityId + '\'' +
                ", toEntityId='" + toEntityId + '\'' +
                ", relationshipId='" + relationshipId + '\'' +
                '}';
    }
}