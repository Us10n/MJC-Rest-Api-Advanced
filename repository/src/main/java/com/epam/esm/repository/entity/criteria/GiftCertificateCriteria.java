package com.epam.esm.repository.entity.criteria;

import java.util.Set;

/**
 * Class that used to hold find criteria.
 */
public class GiftCertificateCriteria {
    private Set<String> tagNames;
    private String partName;
    private String partDesc;
    private String sortBy;
    private String sortOrder;

    /**
     * Instantiates a new Gift certificate criteria.
     */
    public GiftCertificateCriteria() {
    }

    /**
     * Instantiates a new Gift certificate criteria.
     *
     * @param tagNames   the tag names
     * @param partName  the part name
     * @param partDesc  the part desc
     * @param sortBy    the sort by
     * @param sortOrder the sort order
     */
    public GiftCertificateCriteria(Set<String> tagNames, String partName,
                                   String partDesc, String sortBy, String sortOrder) {
        this.tagNames = tagNames;
        this.partName = partName;
        this.partDesc = partDesc;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
     */
    public Set<String> getTagNames() {
        return tagNames;
    }

    /**
     * Sets tag name.
     *
     * @param tagNames the tag name
     */
    public void setTagNames(Set<String> tagNames) {
        this.tagNames = tagNames;
    }

    /**
     * Gets part name.
     *
     * @return the part name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets part name.
     *
     * @param partName the part name
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Gets part desc.
     *
     * @return the part desc
     */
    public String getPartDesc() {
        return partDesc;
    }

    /**
     * Sets part desc.
     *
     * @param partDesc the part desc
     */
    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    /**
     * Gets sort by.
     *
     * @return the sort by
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets sort by.
     *
     * @param sortBy the sort by
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Gets sort order.
     *
     * @return the sort order
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets sort order.
     *
     * @param sortOrder the sort order
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftCertificateCriteria criteria = (GiftCertificateCriteria) o;

        if (tagNames != null ? !tagNames.equals(criteria.tagNames) : criteria.tagNames != null) return false;
        if (partName != null ? !partName.equals(criteria.partName) : criteria.partName != null) return false;
        if (partDesc != null ? !partDesc.equals(criteria.partDesc) : criteria.partDesc != null) return false;
        if (sortBy != null ? !sortBy.equals(criteria.sortBy) : criteria.sortBy != null) return false;
        return sortOrder != null ? sortOrder.equals(criteria.sortOrder) : criteria.sortOrder == null;
    }

    @Override
    public int hashCode() {
        int result = tagNames != null ? tagNames.hashCode() : 0;
        result = 31 * result + (partName != null ? partName.hashCode() : 0);
        result = 31 * result + (partDesc != null ? partDesc.hashCode() : 0);
        result = 31 * result + (sortBy != null ? sortBy.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftCertificateCriteria{");
        sb.append("tagNames=").append(tagNames);
        sb.append(", partName='").append(partName).append('\'');
        sb.append(", partDesc='").append(partDesc).append('\'');
        sb.append(", sortBy='").append(sortBy).append('\'');
        sb.append(", sortOrder='").append(sortOrder).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
