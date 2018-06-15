package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Service implements Identifiable{

    private EntityId id;
    private ServiceType serviceType;
    private Material material;
    private int copiesNumber;
    private int pagesPerSheet;
    private String pages;
    private String comment;

    public Service() {
        this.id = null;
        this.serviceType = null;
        this.material = null;
        this.copiesNumber = 0;
        this.pagesPerSheet = 0;
        this.pages = null;
        this.comment = null;
    }
    @Override
    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
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
                .append("id", id)
                .append("serviceType", serviceType)
                .append("material", material)
                .append("copiesNumber", copiesNumber)
                .append("pagesPerSheet", pagesPerSheet)
                .append("pages", pages)
                .append("comment", comment)
                .toString();
    }
}
