package by.yakunina.copy.storage.mapper;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.support.CrudMapper;
import by.yakunina.copy.storage.support.EntityIdTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper extends CrudMapper<Account> {

    @Override
    @Select("INSERT INTO copy.account(id, username, password, user_id)" +
            " VALUES(#{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}, #{username}, #{password}, #{userId, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler})" +
            " RETURNING id")
    EntityId create(Account entity);

    @Override
    @Results({
            @Result(column = "user_id", typeHandler = EntityIdTypeHandler.class, property = "userId")
    })
    @Select("SELECT id, username, password, user_id" +
            " FROM copy.account WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    Account read(EntityId id);

    @Override
    @Update("UPDATE copy.account SET username = #{username}, password = #{password}" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void update(Account entity);

    @Override
    @Delete("DELETE FROM copy.account" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void delete(EntityId id);

    @ResultMap("AccountResultMap")
    @Select("SELECT acc.id as account_id, acc.username, acc.password, acc.user_id, roles.name, roles.id as role_id" +
            " FROM copy.account acc" +
            " LEFT JOIN copy.role_account_map ram ON ram.account_id = acc.id" +
            " LEFT JOIN copy.role roles ON roles.id = ram.role_id" +
            " WHERE username = #{username}")
    Account findByUsername(String username);

    @Insert({"<script>",
            "INSERT INTO copy.role_account_map(account_id, role_id)",
            "VALUES",
            "<foreach collection='roleIds' item='roleId' separator=',' >",
            "(#{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}, #{roleId, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler})",
            "</foreach>",
            "</script>"
    })
    void assignRoles(EntityId id, List<EntityId> roleIds);

    @Delete("DELETE FROM copy.role_account_map" +
            " WHERE id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    void clearRoles(EntityId accountId);

    @Update("UPDATE copy.account SET password = #{newPassword} WHERE username = #{username}")
    void changePassword(String username, String newPassword);

    @ResultMap("AccountResultMap")
    @Select("SELECT acc.id as account_id, acc.username, acc.password, acc.user_id, roles.name, roles.id as role_id" +
            " FROM copy.account acc" +
            " LEFT JOIN copy.role_account_map ram ON ram.account_id = acc.id" +
            " LEFT JOIN copy.role roles ON roles.id = ram.role_id" +
            " WHERE acc.id = #{id, typeHandler=by.yakunina.copy.storage.support.EntityIdTypeHandler}")
    Account readWithRoles(EntityId id);
}
