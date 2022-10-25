
package com.example.neo4jtest.Entiity.Lineage;


import com.example.neo4jtest.Entiity.Node.EntityStruct;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class LineageInfo implements Serializable {
    private Long                         baseEntityGuid;
    private LineageDirection               lineageDirection;
    private int                            lineageDepth;
    private Map<Long, EntityStruct> guidEntityMap;
    private Set<LineageRelation>           relations;

    public LineageInfo() {}

    public enum LineageDirection { INPUT, OUTPUT, BOTH }



    public LineageInfo(Long baseEntityGuid, Map<Long, EntityStruct> guidEntityMap,
                       Set<LineageRelation> relations, LineageDirection lineageDirection, int lineageDepth) {
        this.baseEntityGuid   = baseEntityGuid;
        this.lineageDirection = lineageDirection;
        this.lineageDepth     = lineageDepth;
        this.guidEntityMap    = guidEntityMap;
        this.relations        = relations;
    }

    public Long getBaseEntityGuid() {
        return baseEntityGuid;
    }

    public void setBaseEntityGuid(Long baseEntityGuid) {
        this.baseEntityGuid = baseEntityGuid;
    }

    public Map<Long, EntityStruct> getGuidEntityMap() {
        return guidEntityMap;
    }

    public void setGuidEntityMap(Map<Long, EntityStruct> guidEntityMap) {
        this.guidEntityMap = guidEntityMap;
    }

    public Set<LineageRelation> getRelations() {
        return relations;
    }

    public void setRelations(Set<LineageRelation> relations) {
        this.relations = relations;
    }

    public LineageDirection getLineageDirection() {
        return lineageDirection;
    }

    public void setLineageDirection(LineageDirection lineageDirection) {
        this.lineageDirection = lineageDirection;
    }

    public int getLineageDepth() {
        return lineageDepth;
    }

    public void setLineageDepth(int lineageDepth) {
        this.lineageDepth = lineageDepth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineageInfo that = (LineageInfo) o;
        return lineageDepth == that.lineageDepth &&
                Objects.equals(baseEntityGuid, that.baseEntityGuid) &&
                lineageDirection == that.lineageDirection &&
                Objects.equals(guidEntityMap, that.guidEntityMap) &&
                Objects.equals(relations, that.relations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseEntityGuid, lineageDirection, lineageDepth, guidEntityMap, relations);
    }

    @Override
    public String toString() {
        return "AtlasLineageInfo{" +
                "baseEntityGuid=" + baseEntityGuid +
                ", guidEntityMap=" + guidEntityMap +
                ", relations=" + relations +
                ", lineageDirection=" + lineageDirection +
                ", lineageDepth=" + lineageDepth +
                '}';
    }




}