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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StorageService {

    private static List<FileEntity> files = new ArrayList<>();

    @javax.annotation.Resource
    private FileDao fileDao;

    public EntityId store(MultipartFile file) {
//        try {
//            return fileDao
//                    .create(new FileEntity(new EntityId(KeyGenerator.getUUID()), file.getOriginalFilename(), file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;

        try {
            FileEntity fileEntity = new FileEntity(new EntityId(KeyGenerator.getUUID()), file.getOriginalFilename(), file.getBytes());
            files.add(fileEntity);
            return fileEntity.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<String> loadAll() {
//        return fileDao.readAll();

        return files.stream().map(FileEntity::getName).collect(Collectors.toList());
    }


    public Resource loadAsResource(String id) {
//        return new ByteArrayResource(fileDao.readData(filename));
        for (FileEntity file : files) {
            if (file.getId().getId().equals(id)) {
                return new ByteArrayResource(file.getData());
            }
        }
        return null;
    }

    public FileEntity getFile(String id) {
        for (FileEntity file : files) {
            if (file.getId().getId().equals(id)) {
                return file;
            }
        }
        return null;
    }

}
