// 声明该类所在的包名为 com.history.gishistorymap.controller
package com.history.gishistorymap.controller;

// 引入阿里巴巴的 FastJSON 库中的 JSONArray 类，用于处理 JSON 数组
import com.alibaba.fastjson.JSONArray;
// 引入阿里巴巴的 FastJSON 库中的 JSONObject 类，用于处理 JSON 对象
import com.alibaba.fastjson.JSONObject;
// 引入自定义的 GeometryModel 类，该类可能用于封装几何数据相关的信息
import com.history.gishistorymap.model.GeometryModel;
// 引入自定义的 MapService 类，该类可能包含与地图数据处理相关的业务逻辑
import com.history.gishistorymap.service.MapService;
// 引入 JTS（Java Topology Suite）库中的 Coordinate 类，用于表示二维空间中的坐标
import com.vividsolutions.jts.geom.Coordinate;
// 引入 JTS 库中的 Geometry 类，用于表示几何对象
import com.vividsolutions.jts.geom.Geometry;
// 引入 Lombok 库的 Slf4j 注解，用于自动生成日志记录器
import lombok.extern.slf4j.Slf4j;
// 引入 GeoTools 库中的 GeometryJSON 类，用于处理 GeoJSON 格式的几何数据
import org.geotools.geojson.geom.GeometryJSON;
// 引入 Spring 框架的 Autowired 注解，用于自动注入依赖的 Bean
import org.springframework.beans.factory.annotation.Autowired;
// 引入 Spring 框架的 Controller 注解，将该类标记为一个控制器，用于处理 HTTP 请求
import org.springframework.stereotype.Controller;
// 引入 Spring 框架的 RequestMapping 注解，用于映射请求的 URL 路径
import org.springframework.web.bind.annotation.*;

// 引入 Java 标准库中的 StringWriter 类，用于将字符数据写入字符串缓冲区
import java.io.StringWriter;
// 引入 Java 标准库中的 List 接口，用于表示一个有序的集合
import java.util.List;

// 使用 Spring 框架的 Controller 注解，将该类标记为一个控制器
@Controller
// 使用 RequestMapping 注解，将该控制器处理的所有请求的 URL 路径前缀设置为 /history
@RequestMapping(value = "/history")
// 使用 Lombok 的 Slf4j 注解，自动生成日志记录器
@Slf4j
public class MapController {
    // 使用 Spring 框架的 Autowired 注解，自动注入 MapService 类型的 Bean
    @Autowired
    private MapService mapService;

    /**
     * 根据表名和时间区间输出对象
     * @param category 表名简写
     * @param start 起始时间
     * @param end 终止时间
     * @return 包含查询结果的 JSON 对象
     */
    // 使用 ResponseBody 注解，将方法的返回值直接作为 HTTP 响应的内容返回
    @ResponseBody
    // 使用 GetMapping 注解，将该方法映射到 /history/geometry 的 GET 请求
    @GetMapping("/geometry")
    // 使用 CrossOrigin 注解，允许跨域请求访问该接口
    @CrossOrigin
    public JSONObject getPoint(@RequestParam("category") String category, @RequestParam("start") Integer start, @RequestParam("end") Integer end) {
        try {
            // 调用 MapService 中的 getDynastyGeom 方法，根据表名简写、起始时间和终止时间查询数据
            List<GeometryModel> result = mapService.getDynastyGeom(category, start, end);
            // 创建一个新的 JSONObject 对象，用于封装最终的响应数据
            JSONObject jsonObject = new JSONObject();
            // 将查询结果的数量添加到 jsonObject 中，键名为 "number"
            jsonObject.put("number", result.size());
            // 创建一个新的 JSONArray 对象，用于存储每个查询结果的详细信息
            JSONArray jsonArray = new JSONArray();
            // 创建一个 GeometryJSON 对象，用于处理 GeoJSON 格式的几何数据
            GeometryJSON geometryJSON = new GeometryJSON();
            // 遍历查询结果列表
            for (GeometryModel g : result) {
                // 创建一个新的 JSONObject 对象，用于封装每个查询结果的详细信息
                JSONObject geom = new JSONObject();
                // 将 GeometryModel 对象的各个属性添加到 geom 中
                geom.put("gid", g.getGId());
                geom.put("namepy", g.getNamePy());
                geom.put("namech", g.getNameCh());
                geom.put("nameft", g.getNameFt());
                geom.put("presloc", g.getPresLoc());
                geom.put("typepy", g.getTypePy());
                geom.put("typech", g.getTypeCh());
                geom.put("levrank", g.getLevRank());
                geom.put("begyr", g.getBegYr());
                geom.put("begrule", g.getBegRule());
                geom.put("endyr", g.getEndYr());
                geom.put("endrule", g.getEndRule());
                geom.put("geosrc", g.getGeoSrc());
                geom.put("compiler", g.getCompiler());
                geom.put("gecomplr", g.getGecomplr());
                geom.put("checker", g.getChecker());
                geom.put("entdate", g.getEntDate());
                geom.put("begchgty", g.getBegChgTy());
                geom.put("endchgty", g.getEndChgTy());
                // 创建一个 StringWriter 对象，用于将几何数据转换为字符串
                StringWriter writer = new StringWriter();
                // 使用 GeometryJSON 对象将 GeometryModel 中的几何数据写入 StringWriter 中
                geometryJSON.write(g.getGeometry(), writer);
                // 将写入的字符串解析为 JSONObject 并添加到 geom 中，键名为 "geometry"
                geom.put("geometry", JSONObject.parse(writer.toString()));
                // 将 geom 添加到 jsonArray 中
                jsonArray.add(geom);
            }
            // 将 jsonArray 添加到 jsonObject 中，键名为 "list"
            jsonObject.put("list", jsonArray);
            // 使用日志记录器记录入参信息
            log.info("入参类别：{}，起始时间：{}，截至时间：{}", category, start, end);
            // 返回封装好的 jsonObject
            return jsonObject;
        } catch (Exception e) {
            // 使用日志记录器记录异常信息
            log.error("程序错误类型：", e);
            // 发生异常时返回 null
            return null;
        }
    }
}