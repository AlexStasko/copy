package by.yakunina.copy.storage.dao;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.storage.support.CrudDao;

import java.util.List;

public interface FileDao extends CrudDao<FileEntity> {

    List<String> readAll();

    byte[] readData(String name);
}
