package by.yakunina.copy.storage.support;

import by.yakunina.copy.model.FileEntity;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.BINARY)
public class FileEntityTypeHandler extends BaseTypeHandler<FileEntity> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FileEntity parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getData(), JdbcType.BINARY.TYPE_CODE);
    }

    @Override
    public FileEntity getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new FileEntity(null, null, rs.getBytes(columnName));
    }

    @Override
    public FileEntity getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new FileEntity(null, null, rs.getBytes(columnIndex));
    }

    @Override
    public FileEntity getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new FileEntity(null, null, cs.getBytes(columnIndex));
    }
}
