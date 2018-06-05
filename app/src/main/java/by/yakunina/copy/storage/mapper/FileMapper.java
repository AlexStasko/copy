package by.yakunina.copy.storage.mapper;

import by.yakunina.copy.model.FileEntity;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.support.CrudMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper extends CrudMapper<FileEntity> {

    @Override
    @Select("INSERT INTO copy.file (id, name, data)" +
            " VALUES(#{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}, #{name}, #{data})" +
            " RETURNING id")
    EntityId create(FileEntity entity);

    @Override
    @Select(" SELECT id, name, data FROM copy.file" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    FileEntity read(EntityId id);

    @Override
    @Update("UPDATE copy.file SET name = #{name}, data = #{data}" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void update(FileEntity entity);

    @Override
    @Delete("DELETE FROM copy.file WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void delete(EntityId id);
}
