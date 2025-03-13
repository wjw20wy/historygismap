// 声明该类所在的包名为 com.history.gishistorymap.localcache
package com.history.gishistorymap.localcache;

// 引入自定义的 MapDao 类，它是数据访问对象接口，用于与数据库交互获取数据
import com.history.gishistorymap.dao.MapDao;
// 引入自定义的 GeometryModel 类，用于封装几何数据相关的信息
import com.history.gishistorymap.model.GeometryModel;
// 再次引入自定义的 GeometryModel 类（重复引入，实际可优化去掉一个）
import com.history.gishistorymap.model.GeometryModel;
// 引入 Lombok 库的 Log 注解，用于自动生成日志记录器
import lombok.extern.java.Log;
// 引入 Spring 框架的 InitializingBean 接口，实现该接口的类在 Bean 初始化完成后会调用 afterPropertiesSet 方法
import org.springframework.beans.factory.InitializingBean;
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
// 定义一个名为 GeometryCache 的类，实现 InitializingBean 接口
public class GeometryCache implements InitializingBean {
    // 定义一个 Map 类型的成员变量 historyGis，用于存储几何数据的缓存
    // 键为 String 类型，值为 List<GeometryModel> 类型，即存储 GeometryModel 对象的列表
    Map<String, List<GeometryModel>> historyGis = new HashMap<>();
    // 使用 Spring 框架的 Autowired 注解，自动注入 MapDao 类型的 Bean
    @Autowired
    private MapDao mapDao;

    /**
     * 实现 InitializingBean 接口的 afterPropertiesSet 方法
     * 在 Bean 的属性被设置之后，且在 Bean 初始化完成时调用该方法
     * 用于初始化缓存数据，从数据库中读取相关几何数据并存储到 historyGis 中
     * @throws Exception 如果在初始化过程中发生异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建一个 ArrayList 用于存储县级点的几何数据
        List<GeometryModel> cntyGeometry = new ArrayList<>();
        // 初始化计数器 i 为 1
        int i = 1;
        // 当通过 mapDao 获取的县级点数据不为空时，循环读取数据
        while (mapDao.getCntyPoint(i) != null) {
            // 将获取到的县级点数据添加到 cntyGeometry 列表中
            cntyGeometry.add(mapDao.getCntyPoint(i));
            // 计数器 i 自增
            i++;
        }
        // 将存储县级点数据的列表 cntyGeometry 以键 "cntypts" 存入 historyGis 中
        historyGis.put("cntypts", cntyGeometry);

        // 创建一个 ArrayList 用于存储府级点的几何数据
        List<GeometryModel> prefPtsGeometry = new ArrayList<>();
        // 重置计数器 i 为 1
        i = 1;
        // 当通过 mapDao 获取的府级点数据不为空时，循环读取数据
        while (mapDao.getPrefPoint(i) != null) {
            // 这里存在错误，应该是添加 mapDao.getPrefPoint(i)，而不是 mapDao.getCntyPoint(i)
            // 但此处按原代码注释，实际需要修正该错误
            prefPtsGeometry.add(mapDao.getPrefPoint(i));
            i++;
        }
        // 将存储府级点数据的列表 prefPtsGeometry 以键 "prefpts" 存入 historyGis 中
        historyGis.put("prefpts", prefPtsGeometry);

        // 创建一个 ArrayList 用于存储府级多边形的几何数据
        List<GeometryModel> prefPgnGeometry = new ArrayList<>();
        // 重置计数器 i 为 1
        i = 1;
        // 当通过 mapDao 获取的府级多边形数据不为空时，循环读取数据
        while (mapDao.getprefPolygon(i) != null) {
            // 将获取到的府级多边形数据添加到 prefPgnGeometry 列表中
            prefPgnGeometry.add(mapDao.getprefPolygon(i));
            i++;
        }
        // 将存储府级多边形数据的列表 prefPgnGeometry 以键 "prefpgn" 存入 historyGis 中
        historyGis.put("prefpgn", prefPgnGeometry);
    }

    /**
     * 根据给定的类别、起始时间和终止时间，从缓存中获取符合条件的几何数据
     * @param category 数据的类别，如 "cntypts"、"prefpts"、"prefpgn" 等
     * @param start 起始时间
     * @param end 终止时间
     * @return 返回一个包含符合条件的 GeometryModel 对象的列表
     */
    public List<GeometryModel> getDynastyGeometry(String category, Integer start, Integer end) {
        // 创建一个 ArrayList 用于存储符合条件的几何数据
        List<GeometryModel> result = new ArrayList<>();
        // 遍历指定类别在缓存中对应的几何数据列表
        for (GeometryModel g : historyGis.get(category)) {
            // 判断几何数据的起始年份或结束年份是否在给定的时间区间内
            if ((g.getBegYr() >= start && g.getBegYr() <= end) || (g.getEndYr() >= start && g.getEndYr() <= end)) {
                // 如果符合条件，将该几何数据添加到 result 列表中
                result.add(g);
            }
        }
        // 返回符合条件的几何数据列表
        return result;
    }
}