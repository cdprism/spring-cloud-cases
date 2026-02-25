package org.shancm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * 基于 Jackson 3.x 的 JSON 工具类
 * <p>
 * 提供线程安全、高性能的 JSON 序列化与反序列化操作。
 * 内部使用单例 ObjectMapper，并配置了常用的特性：
 * <ul>
 *     <li>反序列化时忽略未知属性</li>
 *     <li>序列化时只包含非空字段</li>
 *     <li>统一日期格式为 "yyyy-MM-dd HH:mm:ss" 并设置为 UTC 时区</li>
 *     <li>支持 Java 8 日期时间类型（如 LocalDateTime）</li>
 *     <li>允许 JSON 中包含注释（非标准，但方便调试）</li>
 * </ul>
 * 所有方法均将受检异常转换为运行时异常，简化调用。
 *
 * @author your-name
 * @since 1.0.0
 */
public final class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 默认日期格式
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时区：UTC
     */
    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("UTC");

    /**
     * 线程安全的 ObjectMapper 单例
     */
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        // 使用 JsonMapper.builder() 构建 ObjectMapper（Jackson 3.x 推荐方式）
        OBJECT_MAPPER = JsonMapper.builder()
                // 反序列化时，遇到未知属性（JSON 中有而 Java 对象中没有）不抛出异常
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 序列化时，遇到空 Bean 不抛出异常（返回空对象）
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // 允许 JSON 中包含注释（如 // 或 /* */）
                // ⭐设置为不允许了 不允许应该是default的
                .configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false)
                // 允许字段名不带引号（较少用，但可增加灵活性）
                .configure(JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES, true)
                // 设置日期格式
                .defaultDateFormat(new SimpleDateFormat(DEFAULT_DATE_PATTERN))
                // 设置时区
                .defaultTimeZone(DEFAULT_TIME_ZONE)
                // 注册 Java 8 时间模块（处理 LocalDate, LocalDateTime 等）
                //⭐这里不需要了应该是
//                .addModule(new JavaTimeModule())
                // 禁用将日期写为时间戳的行为（统一使用字符串格式）
                .configure(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();


    }

    private JsonUtils() {
        // 工具类，私有构造器
    }

    // ============================ 序列化方法 ============================

    /**
     * 将对象转换为 JSON 字符串（格式化输出，带缩进）
     *
     * @param obj 要转换的对象
     * @return JSON 字符串，若对象为 null 则返回 "null"
     */
    public static String toJsonStringPretty(Object obj) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JacksonException e) {
            log.error("序列化对象为格式化 JSON 字符串失败: {}", obj, e);
            throw new JsonRuntimeException("序列化失败", e);
        }
    }

    /**
     * 将对象转换为 JSON 字符串（紧凑格式）
     *
     * @param obj 要转换的对象
     * @return JSON 字符串，若对象为 null 则返回 "null"
     */
    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JacksonException e) {
            log.error("序列化对象为 JSON 字符串失败: {}", obj, e);
            throw new JsonRuntimeException("序列化失败", e);
        }
    }

    /**
     * 将对象转换为字节数组（UTF-8 编码的 JSON）
     *
     * @param obj 要转换的对象
     * @return 字节数组
     */
    public static byte[] toJsonBytes(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (JacksonException e) {
            log.error("序列化对象为 JSON 字节数组失败: {}", obj, e);
            throw new JsonRuntimeException("序列化失败", e);
        }
    }

    // ============================ 反序列化方法 ============================

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标类型 Class
     * @param <T>   泛型
     * @return 目标对象
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JacksonException e) {
            log.error("反序列化 JSON 到对象失败, json: {}, class: {}", json, clazz, e);
            throw new JsonRuntimeException("反序列化失败", e);
        }
    }

    /**
     * 将字节数组（UTF-8 编码的 JSON）转换为指定类型的对象
     *
     * @param bytes JSON 字节数组
     * @param clazz 目标类型 Class
     * @param <T>   泛型
     * @return 目标对象
     */
    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (JacksonException e) {
            log.error("反序列化 JSON 字节数组到对象失败, class: {}", clazz, e);
            throw new JsonRuntimeException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串转换为泛型集合/复杂类型，如 List<Xxx>、Map<String, Xxx> 等
     * <pre>
     * 示例：
     * List<User> userList = parseObject(jsonString, new TypeReference&lt;List&lt;User&gt;&gt;() {});
     * Map<String, Object> map = parseObject(jsonString, new TypeReference&lt;Map&lt;String, Object&gt;&gt;() {});
     * </pre>
     *
     * @param json          JSON 字符串
     * @param typeReference TypeReference 子类型实例
     * @param <T>           泛型
     * @return 目标对象
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JacksonException e) {
            log.error("反序列化 JSON 到复杂类型失败, json: {}, type: {}", json, typeReference.getType(), e);
            throw new JsonRuntimeException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串转换为 List
     *
     * @param json  JSON 字符串
     * @param clazz List 内元素的类型
     * @param <T>   泛型
     * @return List 对象
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JacksonException e) {
            log.error("反序列化 JSON 到 List 失败, json: {}, elementClass: {}", json, clazz, e);
            throw new JsonRuntimeException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串转换为 Map <String, Object>
     *
     * @param json JSON 字符串
     * @return Map 对象
     */
    public static Map<String, Object> parseMap(String json) {
        return parseObject(json, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * 将 JSON 字符串转换为 Map《String, T》
     *
     * @param json  JSON 字符串
     * @param clazz Map 值类型
     * @param <T>   泛型
     * @return Map 对象
     */
    public static <T> Map<String, T> parseMap(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json,
                    OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, String.class, clazz));
        } catch (JacksonException e) {
            log.error("反序列化 JSON 到 Map 失败, json: {}, valueClass: {}", json, clazz, e);
            throw new JsonRuntimeException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串解析为 Jackson 的 JsonNode，可用于动态读取部分字段
     *
     * @param json JSON 字符串
     * @return JsonNode
     */
    public static JsonNode parseTree(String json) {
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (JacksonException e) {
            log.error("解析 JSON 树失败, json: {}", json, e);
            throw new JsonRuntimeException("解析 JSON 树失败", e);
        }
    }

    /**
     * 将对象转换为 JsonNode 树
     *
     * @param obj 源对象
     * @return JsonNode
     */
    public static JsonNode toTree(Object obj) {
        return OBJECT_MAPPER.valueToTree(obj);
    }

    // ============================ 转换与判断 ============================

    /**
     * 判断字符串是否为合法的 JSON 格式（通过尝试解析为 JsonNode 判断）
     *
     * @param json 待检查字符串
     * @return true 如果是合法 JSON
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.isEmpty()) {
            return false;
        }
        try {
            OBJECT_MAPPER.readTree(json);
            return true;
        } catch (JacksonException e) {
            return false;
        }
    }

    /**
     * 将对象转换为指定类型（深度转换，例如将 Map 转换为 POJO）
     *
     * @param obj   源对象（如 Map、JsonNode）
     * @param clazz 目标类型
     * @param <T>   泛型
     * @return 转换后的对象
     */
    public static <T> T convertValue(Object obj, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.convertValue(obj, clazz);
        } catch (IllegalArgumentException e) {
            log.error("对象转换失败, obj: {}, class: {}", obj, clazz, e);
            throw new JsonRuntimeException("对象转换失败", e);
        }
    }

    /**
     * 将对象转换为泛型类型（深度转换）
     *
     * @param obj           源对象
     * @param typeReference 目标类型引用
     * @param <T>           泛型
     * @return 转换后的对象
     */
    public static <T> T convertValue(Object obj, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.convertValue(obj, typeReference);
        } catch (IllegalArgumentException e) {
            log.error("对象转换失败, obj: {}, type: {}", obj, typeReference.getType(), e);
            throw new JsonRuntimeException("对象转换失败", e);
        }
    }

    // ============================ 内部异常类 ============================

    /**
     * JSON 处理运行时异常，包装受检异常
     */
    public static class JsonRuntimeException extends RuntimeException {
        public JsonRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}