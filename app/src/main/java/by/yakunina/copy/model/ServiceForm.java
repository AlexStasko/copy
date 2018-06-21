package by.yakunina.copy.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ServiceForm {

    private String serviceType;
    private String material;
    private int copiesNumber;
    private int pagesPerSheet;
    private String pages;
    private String comment;

    public ServiceForm() {
        this.serviceType = null;
        this.material = null;
        this.copiesNumber = 0;
        this.pagesPerSheet = 0;
        this.pages = null;
        this.comment = null;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCopiesNumber() {
        return copiesNumber;
    }

    public void setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    public int getPagesPerSheet() {
        return pagesPerSheet;
    }

    public void setPagesPerSheet(int pagesPerSheet) {
        this.pagesPerSheet = pagesPerSheet;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("serviceType", serviceType)
                .append("material", material)
                .append("copiesNumber", copiesNumber)
                .append("pagesPerSheet", pagesPerSheet)
                .append("pages", pages)
                .append("comment", comment)
                .toString();
    }

}
