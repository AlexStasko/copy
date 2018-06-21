package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.swing.plaf.ButtonUI;
import java.util.List;

public class Service implements Identifiable{

    private EntityId id;
    private EntityId fileId;
    private ServiceType serviceType;
    private Material material;
    private int copiesNumber;
    private int pagesPerSheet;
    private String pages;
    private String comment;
    private Equipment equipment;

    public Service() {
        this.id = null;
        this.fileId = null;
        this.serviceType = null;
        this.material = null;
        this.copiesNumber = 0;
        this.pagesPerSheet = 0;
        this.pages = null;
        this.comment = null;
        this.equipment = null;
    }

    public Service(ServiceBuilder builder) {
        this.id = builder.id;
        this.fileId = builder.fileId;
        this.serviceType = builder.serviceType;
        this.material = builder.material;
        this.copiesNumber = builder.copiesNumber;
        this.pagesPerSheet = builder.pagesPerSheet;
        this.pages = builder.pages;
        this.comment = builder.comment;
        this.equipment = builder.equipment;
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

    public EntityId getFileId() {
        return fileId;
    }

    public void setFileId(EntityId fileId) {
        this.fileId = fileId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("fileId", fileId)
                .append("serviceType", serviceType)
                .append("material", material)
                .append("copiesNumber", copiesNumber)
                .append("pagesPerSheet", pagesPerSheet)
                .append("pages", pages)
                .append("comment", comment)
                .append("equipment", equipment)
                .toString();
    }

    public static class ServiceBuilder {

        private EntityId id;
        private EntityId fileId;
        private ServiceType serviceType;
        private Material material;
        private int copiesNumber;
        private int pagesPerSheet;
        private String pages;
        private String comment;
        private Equipment equipment;

        public ServiceBuilder withId(EntityId pId) {
            this.id = pId;
            return this;
        }

        public ServiceBuilder withFileId(EntityId pId) {
            this.fileId = pId;
            return this;
        }

        public ServiceBuilder withServiceType(ServiceType pServiceType) {
            this.serviceType = pServiceType;
            return this;
        }

        public ServiceBuilder withMaterial(Material pMaterial) {
            this.material = pMaterial;
            return this;
        }

        public ServiceBuilder withCopiesNumber(int pCopiesNumber) {
            this.copiesNumber = pCopiesNumber;
            return this;
        }

        public ServiceBuilder withPagesPerSheet(int pPagesPerSheet) {
            this.pagesPerSheet = pPagesPerSheet;
            return this;
        }

        public ServiceBuilder withPages(String pPages) {
            this.pages = pPages;
            return this;
        }

        public ServiceBuilder withComment(String pComment) {
            this.comment = pComment;
            return this;
        }

        public ServiceBuilder withEquipment(Equipment pEquipment) {
            this.equipment = pEquipment;
            return this;
        }

        public Service build() {
            return new Service(this);
        }
    }
}
