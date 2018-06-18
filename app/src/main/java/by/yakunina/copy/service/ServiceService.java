package by.yakunina.copy.service;

import by.yakunina.copy.model.Service;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceService {

    private static List<Service> services = new ArrayList<>();

    public EntityId createService(Service newService) {
        newService.setId(new EntityId(KeyGenerator.getUUID()));
        services.add(newService);
        return newService.getId();
    }
}
