// 声明该类所在的包名为 com.history.gishistorymap.model
package com.history.gishistorymap.model;

// 引入 JTS（Java Topology Suite）库中的 Geometry 类，用于表示几何对象
import com.vividsolutions.jts.geom.Geometry;
// 引入 Lombok 库的 Getter 注解，自动生成类中所有字段的 getter 方法
import lombok.Getter;
// 引入 Lombok 库的 Setter 注解，自动生成类中所有字段的 setter 方法
import lombok.Setter;
// 引入 Lombok 库的 ToString 注解，自动生成类的 toString 方法
import lombok.ToString;
// 引入 Jackson 库的 JsonProperty 注解，用于指定 JSON 属性名与 Java 字段名的映射关系，这里未实际使用
import com.fasterxml.jackson.annotation.JsonProperty;

// 使用 Lombok 的 Getter 注解，自动生成类中所有字段的 getter 方法
@Getter
// 使用 Lombok 的 Setter 注解，自动生成类中所有字段的 setter 方法
@Setter
// 使用 Lombok 的 ToString 注解，自动生成类的 toString 方法，方便打印对象信息
@ToString
// 定义一个名为 GeometryModel 的类，用于封装与几何数据相关的信息
public class GeometryModel {
    // 定义一个整数类型的字段 gId，可能用于唯一标识几何对象
    private Integer gId;
    // 定义一个字符串类型的字段 namePy，可能用于存储对象名称的拼音
    private String namePy;
    // 定义一个字符串类型的字段 nameCh，可能用于存储对象名称的中文
    private String nameCh;
    // 定义一个字符串类型的字段 nameFt，可能用于存储对象名称的繁体形式
    private String nameFt;
    // 定义一个字符串类型的字段 presLoc，可能用于存储对象的当前位置信息
    private String presLoc;
    // 定义一个字符串类型的字段 typePy，可能用于存储对象类型的拼音
    private String typePy;
    // 定义一个字符串类型的字段 typeCh，可能用于存储对象类型的中文
    private String typeCh;
    // 定义一个字符串类型的字段 levRank，可能用于存储对象的级别或排名信息
    private String levRank;
    // 定义一个整数类型的字段 begYr，可能用于存储对象开始的年份
    private Integer begYr;
    // 定义一个字符串类型的字段 begRule，可能用于存储对象开始的规则或条件
    private String begRule;
    // 定义一个整数类型的字段 endYr，可能用于存储对象结束的年份
    private Integer endYr;
    // 定义一个字符串类型的字段 endRule，可能用于存储对象结束的规则或条件
    private String endRule;
    // 定义一个字符串类型的字段 geoSrc，可能用于存储几何数据的来源信息
    private String geoSrc;
    // 定义一个字符串类型的字段 compiler，可能用于存储数据的编制者信息
    private String compiler;
    // 定义一个字符串类型的字段 gecomplr，可能用于存储几何数据的编制者信息
    private String gecomplr;
    // 定义一个字符串类型的字段 checker，可能用于存储数据的审核者信息
    private String checker;
    // 定义一个字符串类型的字段 entDate，可能用于存储数据的录入日期
    private String entDate;
    // 定义一个字符串类型的字段 begChgTy，可能用于存储对象开始变化的类型信息
    private String begChgTy;
    // 定义一个字符串类型的字段 endChgTy，可能用于存储对象结束变化的类型信息
    private String endChgTy;
    // 定义一个 Geometry 类型的字段 geometry，用于存储实际的几何对象
    private Geometry geometry;
}