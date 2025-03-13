// 声明该类所在的包名为 com.history.gishistorymap.service.impl
package com.history.gishistorymap.service.impl;

// 引入自定义的 MapDao 接口，用于与数据库交互获取数据
import com.history.gishistorymap.dao.MapDao;
// 引入自定义的 GeometryCache 类，该类用于缓存几何数据
import com.history.gishistorymap.localcache.GeometryCache;
// 引入自定义的 GeometryModel 类，用于封装几何数据相关的信息
import com.history.gishistorymap.model.GeometryModel;
// 引入自定义的 MapService 接口，定义了地图数据服务的方法
import com.history.gishistorymap.service.MapService;
// 引入 Spring 框架的 Autowired 注解，用于自动注入依赖的 Bean
import org.springframework.beans.factory.annotation.Autowired;
// 引入 Spring 框架的 Service 注解，将该类标记为一个服务组件，Spring 会将其作为一个 Bean 进行管理
import org.springframework.stereotype.Service;

// 引入 Java 标准库中的 ArrayList 类，用于创建可变大小的数组列表
import java.util.ArrayList;
// 引入 Java 标准库中的 HashMap 类，用于创建键值对映射的集合
import java.util.HashMap;
// 引入 Java 标准库中的 List 接口，用于表示一个有序的集合
import java.util.List;
// 引入 Java 标准库中的 Map 接口，用于表示键值对的映射
import java.util.Map;

// 使用 Spring 框架的 Service 注解，将该类标记为一个服务组件
@Service
// 定义一个名为 MapServiceImpl 的类，实现了 MapService 接口
public class MapServiceImpl implements MapService {
    // 使用 Spring 框架的 Autowired 注解，自动注入 GeometryCache 类型的 Bean
    @Autowired
    private GeometryCache geometryCache;

    /**
     * 根据给定的类别、起始时间和终止时间，从缓存中获取符合条件的几何数据
     * 该方法实现了 MapService 接口中定义的 getDynastyGeom 方法
     * @param category 数据的类别，如 "cntypts"、"prefpts"、"prefpgn" 等
     * @param start 起始时间
     * @param end 终止时间
     * @return 返回一个包含符合条件的 GeometryModel 对象的列表
     */
    @Override
    public List<GeometryModel> getDynastyGeom(String category, Integer start, Integer end) {
        // 调用 GeometryCache 类的 getDynastyGeometry 方法，从缓存中获取符合条件的几何数据
        return geometryCache.getDynastyGeometry(category, start, end);
    }
}