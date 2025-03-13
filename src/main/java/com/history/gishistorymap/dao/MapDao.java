// 声明该类所在的包名为 com.history.gishistorymap.dao
package com.history.gishistorymap.dao;

// 引入自定义的 GeometryModel 类，该类可能用于封装几何数据相关的信息
import com.history.gishistorymap.model.GeometryModel;
// 引入 MyBatis 框架的 Param 注解，用于为方法参数指定名称，以便在 SQL 语句中使用
import org.apache.ibatis.annotations.Param;
// 引入 Spring 框架的 Service 注解，通常用于标记服务层组件，但这里用在接口上不太规范，一般 DAO 层接口使用 @Mapper 注解
import org.springframework.stereotype.Service;

// 引入 Java 标准库中的 List 接口，用于表示一个有序的集合
import java.util.List;

// 使用 Spring 框架的 Service 注解，不过在 DAO 层接口中更推荐使用 @Mapper 注解
// 这里标记后 Spring 会将其作为一个 Bean 进行管理
@Service
// 定义一个名为 MapDao 的接口，作为数据访问对象（DAO）接口
public interface MapDao {
    /**
     * 根据给定的 gId 查询县级点数据
     * @param gId 唯一标识符，用于定位具体的县级点数据
     * @return 返回一个 GeometryModel 对象，包含查询到的县级点数据的相关信息
     */
    // 定义一个抽象方法，用于根据 gId 获取县级点数据
    // @Param("gId") 注解为方法参数指定名称，在对应的 SQL 映射文件中可以通过该名称引用该参数
    GeometryModel getCntyPoint(@Param("gId") Integer gId);

    /**
     * 根据给定的 gId 查询府级点数据
     * @param gId 唯一标识符，用于定位具体的府级点数据
     * @return 返回一个 GeometryModel 对象，包含查询到的府级点数据的相关信息
     */
    // 定义一个抽象方法，用于根据 gId 获取府级点数据
    GeometryModel getPrefPoint(@Param("gId") Integer gId);

    /**
     * 根据给定的 gId 查询府级多边形数据
     * @param gId 唯一标识符，用于定位具体的府级多边形数据
     * @return 返回一个 GeometryModel 对象，包含查询到的府级多边形数据的相关信息
     */
    // 定义一个抽象方法，用于根据 gId 获取府级多边形数据
    GeometryModel getprefPolygon(@Param("gId") Integer gId);
}