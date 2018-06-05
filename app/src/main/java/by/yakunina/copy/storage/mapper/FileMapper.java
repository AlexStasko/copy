package by.yakunina.copy.storage.mapper;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.storage.support.CrudMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper extends CrudMapper<FileEntity> {

    @Override
    @Select("INSERT INTO copy.file (id, name, data) VALUES(#{id}, #{name}, #{data}) RETURNING id")
    int create(FileEntity entity);

    @Override
    @Select(" SELECT id, name, data FROM copy.file WHERE id = #{id}")
    FileEntity read(int id);

    @Override
    @Update("UPDATE copy.file SET name = #{name}, data = #{data}, WHERE id = #{id}")
    void update(FileEntity entity);

    @Override
    @Delete("DELETE FROM copy.file WHERE id = #{id}")
    void delete(int id);
}
