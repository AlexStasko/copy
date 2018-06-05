package by.yakunina.copy.service;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.dao.FileDao;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StorageService {

    @javax.annotation.Resource
    private FileDao fileDao;

    public EntityId store(MultipartFile file) {
        try {
            return fileDao
                    .create(new FileEntity(new EntityId(KeyGenerator.getUUID()), file.getOriginalFilename(), file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> loadAll() {
        return fileDao.readAll();
    }


    public Resource loadAsResource(String filename) {
        return new ByteArrayResource(fileDao.readData(filename));
    }

}
