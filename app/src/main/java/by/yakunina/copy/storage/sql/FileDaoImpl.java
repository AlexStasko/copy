package by.yakunina.copy.storage.sql;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.storage.dao.FileDao;
import by.yakunina.copy.storage.mapper.FileMapper;
import by.yakunina.copy.storage.support.AbstractCrudDao;
import by.yakunina.copy.storage.support.CrudDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FileDaoImpl extends AbstractCrudDao<FileEntity> implements FileDao {

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<String> readAll() {
        return fileMapper.readAll();
    }

    @Override
    public byte[] readData(String name) {
        return fileMapper.readData(name);
    }

    @Override
    protected CrudDao<FileEntity> getMapper() {
        return fileMapper;
    }
}
