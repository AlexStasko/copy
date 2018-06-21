package by.yakunina.copy.service;

import by.yakunina.copy.model.Equipment;
import by.yakunina.copy.model.Material;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipmentService {

    private static List<Equipment> equipments = new ArrayList<>();

    static {
        List<Material> materialList1 = new ArrayList<>();
        List<Material> materialList2 = new ArrayList<>();
        List<Material> materialList3 = new ArrayList<>();


        Material material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Обычная бумага");
        materialList1.add(material);
        materialList2.add(material);
        materialList3.add(material);

        material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Глянцевая бумага");
        materialList1.add(material);
        materialList2.add(material);

        material = new Material();
        material.setId(new EntityId(KeyGenerator.getUUID()));
        material.setName("Матовая бумага");
        materialList1.add(material);

        Equipment equipment = new Equipment();
        equipment.setId(new EntityId(KeyGenerator.getUUID()));
        equipment.setName("Принтер 1");
        equipment.setMaterials(materialList1);
        equipments.add(equipment);

        equipment = new Equipment();
        equipment.setId(new EntityId(KeyGenerator.getUUID()));
        equipment.setName("Принтер 2");
        equipment.setMaterials(materialList2);
        equipments.add(equipment);

        equipment = new Equipment();
        equipment.setId(new EntityId(KeyGenerator.getUUID()));
        equipment.setName("Принтер 3");
        equipment.setMaterials(materialList3);
        equipments.add(equipment);
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public List<Equipment> findEquipmentsByMaterial(String material) {
        List<Equipment> equipmentList = new ArrayList<>();
        for (Equipment equipment : equipments) {
//            exit:
//            for (Material m : equipment.getMaterials()) {
//                if (m.getName().equals(material)) {
//                    equipmentList.add(equipment);
//                    continue exit;
//                }
//            }
            if (equipment.getMaterials().stream().anyMatch(material1 -> material1.getName().equals(material))) {
                equipmentList.add(equipment);
            }
        }
        return equipments;
    }

    public Equipment findEquipmentByName(String name) {
        for (Equipment equipment : equipments) {
            if (equipment.getName().equals(name)) {
                return equipment;
            }
        }
        return null;
    }
}
