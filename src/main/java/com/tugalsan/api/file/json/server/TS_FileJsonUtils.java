package com.tugalsan.api.file.json.server;

import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.api.union.client.TGS_UnionExcuse;


public class TS_FileJsonUtils {

    public static <T> T[] toArray(Path filePath, Class<T[]> innerClassNameArray_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(filePath.toFile(), innerClassNameArray_dot_class);
        });
    }

    public static <T> T[] toArray(CharSequence jsonString, Class<T[]> innerClassNameArray_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString.toString(), innerClassNameArray_dot_class);
        });
    }

    public static <T> List<T> toList(Path filePath, Class<T> innerClassName_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(filePath.toFile(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class));
        });
    }

    public static <T> List<T> toList(CharSequence jsonString, Class<T> innerClassName_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString.toString(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class));
        });
    }

    //ATTENTION ALL POJO FIELDS MUST BE PUBLIC. 
    //OR U WILL GET SERIALIZATION EXCEPTION
    //a = objectMapper.readValue(jsonString, Aligel.class);
    public static <T> TGS_UnionExcuse<T> toObject(CharSequence jsonString, Class<T> className_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var obj = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                    .readValue(jsonString.toString(), className_dot_class);
            return TGS_UnionExcuse.of(obj);
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static String toJSON(Object o, boolean pretty) {
        return TGS_FuncMTCUtils.call(() -> {
            return new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValueAsString(o);
        });
    }

    public static Path toFile(CharSequence sourceJSON, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        return TS_FileTxtUtils.toFile(sourceJSON, destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }

    public static Path toFile(Object o, boolean pretty, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        return TS_FileTxtUtils.toFile(toJSON(o, pretty), destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }
}
