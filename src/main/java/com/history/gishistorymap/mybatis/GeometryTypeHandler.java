// 声明该类所在的包名为 com.history.gishistorymap.mybatis
package com.history.gishistorymap.mybatis;

// 引入 JTS（Java Topology Suite）库中的 Geometry 类，用于表示几何对象
import com.vividsolutions.jts.geom.Geometry;
// 引入 JTS 库中的 ParseException 类，用于处理解析几何数据时可能出现的异常
import com.vividsolutions.jts.io.ParseException;
// 引入 JTS 库中的 WKBReader 类，用于从 WKB（Well-Known Binary）格式读取几何对象
import com.vividsolutions.jts.io.WKBReader;
// 引入 MyBatis 框架的 BaseTypeHandler 类，自定义类型处理器需要继承该类
import org.apache.ibatis.type.BaseTypeHandler;
// 引入 MyBatis 框架的 JdbcType 类，用于表示 JDBC 类型
import org.apache.ibatis.type.JdbcType;
// 引入 MyBatis 框架的 MappedTypes 注解，用于指定该类型处理器处理的 Java 类型
import org.apache.ibatis.type.MappedTypes;
// 引入 PostgreSQL 驱动中的 PGobject 类，用于处理 PostgreSQL 特定的数据类型
import org.postgresql.util.PGobject;

import java.sql.CallableStatement; // 引入 JDBC 的 CallableStatement 类，用于执行存储过程
import java.sql.PreparedStatement; // 引入 JDBC 的 PreparedStatement 类，用于执行预编译的 SQL 语句
import java.sql.ResultSet; // 引入 JDBC 的 ResultSet 类，用于存储查询结果
import java.sql.SQLException; // 引入 JDBC 的 SQLException 类，用于处理 SQL 操作时可能出现的异常

// 使用 MappedTypes 注解，指定该类型处理器处理的 Java 类型为 Geometry 类
@MappedTypes(Geometry.class)
// 定义一个名为 GeometryTypeHandler 的类，继承自 BaseTypeHandler<Geometry>，用于处理 Geometry 类型与数据库之间的映射
public class GeometryTypeHandler extends BaseTypeHandler<Geometry> {

    /**
     * 当参数不为空时，将 Geometry 对象设置到 PreparedStatement 中
     *
     * @param preparedStatement 预编译的 SQL 语句对象
     * @param i                 参数的索引位置
     * @param geometry          要设置的 Geometry 对象
     * @param jdbcType          JDBC 类型
     * @throws SQLException 如果在设置参数过程中出现 SQL 异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Geometry geometry, JdbcType jdbcType) throws SQLException {
        // 创建一个 PGobject 对象，用于存储 PostgreSQL 特定的数据类型
        PGobject pGobject = new PGobject();
        // 将 Geometry 对象转换为字符串并设置到 PGobject 中
        pGobject.setValue(geometry.toString());
        // 设置 PGobject 的类型为 "geometry"
        pGobject.setType("geometry");
        // 将 PGobject 设置到 PreparedStatement 的指定位置
        preparedStatement.setObject(i, pGobject);
    }

    /**
     * 从 ResultSet 中根据列名获取可空的 Geometry 对象
     *
     * @param resultSet    结果集对象
     * @param columnName   列名
     * @return 解析得到的 Geometry 对象，如果为空则返回 null
     * @throws SQLException 如果在获取数据过程中出现 SQL 异常
     */
    @Override
    public Geometry getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        // 从结果集中根据列名获取 PGobject 对象
        PGobject pGgeometry = (PGobject) resultSet.getObject(columnName);
        // 如果 PGobject 为空，直接返回 null
        if (pGgeometry == null) {
            return null;
        } else {
            // 创建一个 WKBReader 对象，用于解析 WKB 格式的几何数据
            WKBReader wkbReader = new WKBReader();
            try {
                // 将 PGobject 的值（WKB 格式的十六进制字符串）转换为字节数组，并使用 WKBReader 解析为 Geometry 对象
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                // 处理解析异常，打印异常堆栈信息
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 从 ResultSet 中根据列索引获取可空的 Geometry 对象
     *
     * @param resultSet    结果集对象
     * @param columnIndex  列索引
     * @return 解析得到的 Geometry 对象，如果为空则返回 null
     * @throws SQLException 如果在获取数据过程中出现 SQL 异常
     */
    @Override
    public Geometry getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // 从结果集中根据列索引获取 PGobject 对象
        PGobject pGgeometry = (PGobject) resultSet.getObject(columnIndex);
        // 如果 PGobject 为空，直接返回 null
        if (pGgeometry == null) {
            return null;
        } else {
            // 创建一个 WKBReader 对象，用于解析 WKB 格式的几何数据
            WKBReader wkbReader = new WKBReader();
            try {
                // 将 PGobject 的值（WKB 格式的十六进制字符串）转换为字节数组，并使用 WKBReader 解析为 Geometry 对象
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                // 处理解析异常，打印异常堆栈信息
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 从 CallableStatement 中根据索引获取可空的 Geometry 对象
     *
     * @param callableStatement 存储过程调用语句对象
     * @param i                 索引位置
     * @return 解析得到的 Geometry 对象，如果为空则返回 null
     * @throws SQLException 如果在获取数据过程中出现 SQL 异常
     */
    @Override
    public Geometry getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        // 从 CallableStatement 中根据索引获取 PGobject 对象
        PGobject pGgeometry = (PGobject) callableStatement.getObject(i);
        // 如果 PGobject 为空，直接返回 null
        if (pGgeometry == null) {
            return null;
        } else {
            // 创建一个 WKBReader 对象，用于解析 WKB 格式的几何数据
            WKBReader wkbReader = new WKBReader();
            try {
                // 将 PGobject 的值（WKB 格式的十六进制字符串）转换为字节数组，并使用 WKBReader 解析为 Geometry 对象
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                // 处理解析异常，打印异常堆栈信息
                e.printStackTrace();
                return null;
            }
        }
    }
}