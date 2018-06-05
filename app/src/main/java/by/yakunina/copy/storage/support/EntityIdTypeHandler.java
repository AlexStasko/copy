/*
 * COPYRIGHT:     Copyright © 2016-2018 Xplorie LLC
 * Warning:       This product is protected by United States and international copyright laws.
 *                Unauthorized use or duplication of this software, in whole
 *                or in part, is prohibited.
 */

package by.yakunina.copy.storage.support;

import by.yakunina.copy.model.support.EntityId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.*;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedTypes(EntityId.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class EntityIdTypeHandler extends BaseTypeHandler<EntityId> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EntityId parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getId(), JdbcType.OTHER.TYPE_CODE);
    }

    @Override
    public EntityId getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new EntityId((UUID) rs.getObject(columnName));
    }

    @Override
    public EntityId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new EntityId((UUID) rs.getObject(columnIndex));
    }

    @Override
    public EntityId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new EntityId((UUID) cs.getObject(columnIndex));
    }
}
