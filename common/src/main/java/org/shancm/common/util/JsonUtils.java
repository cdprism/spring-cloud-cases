package org.shancm.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 基于 Jackson 2.x 的 JSON 处理工具类
 * <p>
 * 设计原则：
 * 1. 线程安全：ObjectMapper 是线程安全的，可以全局共享一个实例。
 * 2. 配置合理：禁用反序列化未知属性，注册 Java 8 时间模块，设置日期格式等。
 * 3. 异常处理：将受检异常转换为运行时异常，简化调用。
 * 4. 泛型支持：提供 TypeReference 和 JavaType 支持复杂泛型类型。
 * 5. 常用功能：序列化、反序列化、格式化输出、树模型操作、类型转换等。
 *
 * @author shancm
 * @since 2026-02-22 8:59
 */
public final class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    // 全局共享的 ObjectMapper 实例
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 配置序列化特性
        // 1. 序列化时只包含非空字段，可减少数据体积，避免 null 值干扰
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 2. 禁止将日期写为时间戳，使用 ISO-8601 格式 (如 "2023-01-01T10:20:30")
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 3. 允许单引号字符串（某些非标准 JSON 可能用到）
        // OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 4. 允许未转义的控制字符（慎用，安全考虑一般保持默认）
        // OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        // 配置反序列化特性
        // 1. 忽略 JSON 中存在但 Java 对象不存在的属性，避免 UnknownPropertyException
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 2. 允许空字符串反序列化为 null（某些场景可能需要）
        // OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 3. 对于枚举，如果值不存在，可以使用默认值或抛出异常，这里选择不失败，返回 null
        // OBJECT_MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        // 注册 Java 8 日期时间模块
        OBJECT_MAPPER.registerModule(new JavaTimeModule());

        // 可选：美化输出（默认关闭，需要时使用单独的方法）
        // OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private JsonUtils() {
        // 工具类私有构造
    }

    // ========================= 序列化操作 =========================

    /**
     * 将对象序列化为 JSON 字符串（紧凑格式）
     *
     * @param obj 要序列化的对象
     * @return JSON 字符串，若对象为 null 返回 null
     * @throws JsonException 序列化失败时抛出运行时异常
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("序列化对象为 JSON 字符串失败: {}", obj, e);
            throw new JsonException("序列化失败", e);
        }
    }

    /**
     * 将对象序列化为格式化的 JSON 字符串（带有缩进和换行）
     *
     * @param obj 要序列化的对象
     * @return 格式化后的 JSON 字符串，若对象为 null 返回 null
     * @throws JsonException 序列化失败时抛出运行时异常
     */
    public static String toPrettyJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("序列化对象为格式化 JSON 字符串失败: {}", obj, e);
            throw new JsonException("序列化失败", e);
        }
    }

    /**
     * 将对象写入文件（紧凑格式）
     *
     * @param file 目标文件
     * @param obj  要写入的对象
     * @throws JsonException 写入失败时抛出运行时异常
     */
    public static void writeToFile(File file, Object obj) {
        try {
            OBJECT_MAPPER.writeValue(file, obj);
        } catch (IOException e) {
            logger.error("将对象写入文件失败: file={}, obj={}", file, obj, e);
            throw new JsonException("写入文件失败", e);
        }
    }

    /**
     * 将对象写入文件（格式化格式）
     *
     * @param file 目标文件
     * @param obj  要写入的对象
     * @throws JsonException 写入失败时抛出运行时异常
     */
    public static void writePrettyToFile(File file, Object obj) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (IOException e) {
            logger.error("将对象格式化写入文件失败: file={}, obj={}", file, obj, e);
            throw new JsonException("写入文件失败", e);
        }
    }

    // ========================= 反序列化操作 =========================

    /**
     * 将 JSON 字符串反序列化为指定类型的对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标类型 Class
     * @param <T>   泛型参数
     * @return 反序列化后的对象
     * @throws JsonException 反序列化失败时抛出运行时异常
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("解析 JSON 字符串为对象失败: json={}, class={}", json, clazz.getName(), e);
            throw new JsonException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为泛型类型对象（使用 TypeReference）
     *
     * @param json          JSON 字符串
     * @param typeReference TypeReference 实例，例如 new TypeReference<List<User>>() {}
     * @param <T>           泛型参数
     * @return 反序列化后的对象
     * @throws JsonException 反序列化失败时抛出运行时异常
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            logger.error("解析 JSON 字符串为泛型对象失败: json={}, type={}", json, typeReference.getType(), e);
            throw new JsonException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的 List
     *
     * @param json        JSON 字符串
     * @param elementType List 元素类型
     * @param <T>         元素泛型
     * @return List 对象
     * @throws JsonException 反序列化失败时抛出运行时异常
     */
    public static <T> List<T> parseArray(String json, Class<T> elementType) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, elementType);
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            logger.error("解析 JSON 字符串为 List<{}> 失败: json={}", elementType.getSimpleName(), json, e);
            throw new JsonException("反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为 Map<String, Object>
     *
     * @param json JSON 字符串
     * @return Map 对象
     * @throws JsonException 反序列化失败时抛出运行时异常
     */
    public static Map<String, Object> parseMap(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            logger.error("解析 JSON 字符串为 Map 失败: json={}", json, e);
            throw new JsonException("反序列化失败", e);
        }
    }

    /**
     * 从文件读取 JSON 并反序列化为指定类型对象
     *
     * @param file  源文件
     * @param clazz 目标类型 Class
     * @param <T>   泛型参数
     * @return 反序列化后的对象
     * @throws JsonException 读取或解析失败时抛出运行时异常
     */
    public static <T> T readFromFile(File file, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(file, clazz);
        } catch (IOException e) {
            logger.error("从文件读取 JSON 并解析为对象失败: file={}, class={}", file, clazz.getName(), e);
            throw new JsonException("读取文件失败", e);
        }
    }

    /**
     * 从输入流读取 JSON 并反序列化为指定类型对象
     *
     * @param inputStream 输入流
     * @param clazz       目标类型 Class
     * @param <T>         泛型参数
     * @return 反序列化后的对象
     * @throws JsonException 读取或解析失败时抛出运行时异常
     */
    public static <T> T readFromStream(InputStream inputStream, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, clazz);
        } catch (IOException e) {
            logger.error("从输入流读取 JSON 并解析为对象失败: class={}", clazz.getName(), e);
            throw new JsonException("读取流失败", e);
        }
    }

    // ========================= 树模型操作 =========================

    /**
     * 将 JSON 字符串解析为 JsonNode 树
     *
     * @param json JSON 字符串
     * @return JsonNode 节点，如果输入为空或无效返回 null
     * @throws JsonException 解析失败时抛出运行时异常
     */
    public static JsonNode parseJsonNode(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            logger.error("解析 JSON 字符串为 JsonNode 失败: json={}", json, e);
            throw new JsonException("解析 JsonNode 失败", e);
        }
    }

    /**
     * 将对象转换为 JsonNode 树
     *
     * @param obj 源对象
     * @return JsonNode 节点
     */
    public static JsonNode toJsonNode(Object obj) {
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.valueToTree(obj);
    }

    /**
     * 从 JsonNode 中提取值，并转换为指定类型
     *
     * @param jsonNode JsonNode 节点
     * @param clazz    目标类型 Class
     * @param <T>      泛型参数
     * @return 转换后的对象
     * @throws JsonException 转换失败时抛出运行时异常
     */
    public static <T> T nodeToValue(JsonNode jsonNode, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.treeToValue(jsonNode, clazz);
        } catch (JsonProcessingException e) {
            logger.error("JsonNode 转换为对象失败: node={}, class={}", jsonNode, clazz.getName(), e);
            throw new JsonException("节点转换失败", e);
        }
    }

    // ========================= 类型转换 =========================

    /**
     * 将对象转换为另一种类型（基于 Jackson 的转换器）
     *
     * @param fromValue   源对象
     * @param toValueType 目标类型 Class
     * @param <T>         目标泛型
     * @return 转换后的对象
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    /**
     * 将对象转换为另一种类型（基于 TypeReference）
     *
     * @param fromValue     源对象
     * @param typeReference 目标类型 TypeReference
     * @param <T>           目标泛型
     * @return 转换后的对象
     */
    public static <T> T convertValue(Object fromValue, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.convertValue(fromValue, typeReference);
    }

    // ========================= 其他实用方法 =========================

    /**
     * 检查一个字符串是否是有效的 JSON（仅检查格式，不保证语义）
     *
     * @param json 字符串
     * @return 如果可以解析为树模型，返回 true
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return false;
        }
        try {
            OBJECT_MAPPER.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    /**
     * 获取底层的 ObjectMapper 实例（谨慎使用，通常不推荐暴露）
     * 仅用于需要高级配置的场景，且不应修改其配置以免影响全局
     *
     * @return ObjectMapper 实例
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 自定义运行时异常，包装 Jackson 的受检异常
     */
    public static class JsonException extends RuntimeException {
        public JsonException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
