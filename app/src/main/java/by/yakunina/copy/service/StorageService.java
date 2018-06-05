package by.yakunina.copy.service;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.dao.FileDao;
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
            FileEntity fileEntity = new FileEntity(new EntityId("1"), "file1", file.getBytes());
            return fileDao.create(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> loadAll() {
        List<String> list = new ArrayList();
        list.add("file1");
        list.add("file2");
        return list;
    }


    public Resource loadAsResource(String filename) {
        byte[] array = new byte[0];
        return new ByteArrayResource(array);
    }

}
