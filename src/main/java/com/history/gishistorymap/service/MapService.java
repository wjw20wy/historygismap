// 声明该接口所在的包名为 com.history.gishistorymap.service
package com.history.gishistorymap.service;

// 引入自定义的 GeometryModel 类，该类用于封装几何数据相关的信息
import com.history.gishistorymap.model.GeometryModel;

// 引入 Java 标准库中的 List 接口，用于表示一个有序的集合
import java.util.List;

/**
 * 定义一个名为 MapService 的接口，该接口是服务层的接口，用于定义与地图数据相关的服务方法
 * 接口中定义的方法通常代表了业务逻辑的抽象，具体的实现由实现该接口的类完成
 */
public interface MapService {
    /**
     * 根据给定的类别、起始时间和终止时间，获取符合条件的几何数据
     *
     * @param category 数据的类别，用于指定要查询的几何数据的类型，例如可能是 "cntypts"（县级点数据）、"prefpts"（府级点数据）等
     * @param start    起始时间，用于筛选符合时间范围的几何数据
     * @param end      终止时间，用于筛选符合时间范围的几何数据
     * @return 返回一个包含符合条件的 GeometryModel 对象的列表，GeometryModel 对象封装了几何数据的详细信息
     */
    List<GeometryModel> getDynastyGeom(String category, Integer start, Integer end);
}