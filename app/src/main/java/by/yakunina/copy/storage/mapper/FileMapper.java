package by.yakunina.copy.storage.mapper;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.support.CrudMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.ByteArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FileMapper extends CrudMapper<FileEntity> {

    @Override
    @Select("INSERT INTO copy.file (id, name, data)" +
            " VALUES(#{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}, #{name}, #{data, typeHandler=org.apache.ibatis.type.ByteArrayTypeHandler})" +
            " RETURNING id")
    EntityId create(FileEntity entity);

    @Override
    @Select("SELECT id, name, data FROM copy.file" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    FileEntity read(EntityId id);

    @Override
    @Update("UPDATE copy.file SET name = #{name}, data = #{data, typeHandler=org.apache.ibatis.type.ByteArrayTypeHandler}" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void update(FileEntity entity);

    @Override
    @Delete("DELETE FROM copy.file WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void delete(EntityId id);

    @Select("SELECT name FROM copy.file")
    List<String> readAll();

    @Results(value = {
            @Result(column = "data", jdbcType = JdbcType.BINARY, typeHandler = ByteArrayTypeHandler.class)
    })
    @Select("SELECT data FROM copy.file WHERE name = #{name}")
    byte[] readData(String name);
}
