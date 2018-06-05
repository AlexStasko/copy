package by.yakunina.copy.storage.mapper;

import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.support.CrudMapper;
import by.yakunina.copy.storage.support.EntityIdTypeHandler;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper extends CrudMapper<Role> {

    @Override
    @Select("INSERT INTO copy.role(id, name)" +
            " VALUES(#{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}, #{name}) RETURNING id")
    EntityId create(Role entity);

    @Override
    @Select("SELECT id, name FROM copy.role" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    Role read(EntityId id);

    @Override
    @Update("UPDATE copy.role" +
            " SET name = #{name} WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void update(Role entity);

    @Override
    @Delete("DELETE FROM copy.role WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void delete(EntityId id);


    @Results(value = {
            @Result(column = "id", property = "id", typeHandler = EntityIdTypeHandler.class),
            @Result(column = "name", property = "name")
    })
    @Select("SELECT id, name FROM copy.role WHERE name = #{roleName}")
    Role readByName(String roleName);
}
