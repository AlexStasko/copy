package by.yakunina.copy.service;

import by.yakunina.copy.model.Material;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaterialService {

    private static List<Material> materials = new ArrayList<>();

    static {
        Material material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Обычная бумага");
        materials.add(material);

        material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Глянцевая бумага");
        materials.add(material);

        material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Матовая бумага");
        materials.add(material);
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public Material findMaterial(EntityId id) {
        for (Material material : materials) {
            if (material.getId().getId().equals(id.getId())) {
                return material;
            }
        }
        return null;
    }

    public Material findMaterialByName(String name) {
        for (Material material : materials) {
            if (material.getName().equals(name)) {
                return material;
            }
        }
        return null;
    }
}
