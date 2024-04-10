package com.tugalsan.api.file.json.server;

import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;
import java.io.IOException;

public class TS_FileJsonUtils {

    public static <T> TGS_UnionExcuse<T[]> toArray(Path filePath, Class<T[]> innerClassNameArray_dot_class) {
        try {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return TGS_UnionExcuse.of(mapper.readValue(filePath.toFile(), innerClassNameArray_dot_class));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    public static <T> TGS_UnionExcuse<T[]> toArray(CharSequence jsonString, Class<T[]> innerClassNameArray_dot_class) {
        try {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return TGS_UnionExcuse.of(mapper.readValue(jsonString.toString(), innerClassNameArray_dot_class));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    public static <T> TGS_UnionExcuse<List<T>> toList(Path filePath, Class<T> innerClassName_dot_class) {
        try {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return TGS_UnionExcuse.of(mapper.readValue(filePath.toFile(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class)));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    public static <T> TGS_UnionExcuse<List<T>> toList(CharSequence jsonString, Class<T> innerClassName_dot_class) {
        try {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return TGS_UnionExcuse.of(mapper.readValue(jsonString.toString(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class)));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    //ATTENTION ALL POJO FIELDS MUST BE PUBLIC. 
    //OR U WILL GET SERIALIZATION EXCEPTION
    //a = objectMapper.readValue(jsonString, Aligel.class);
    public static <T> TGS_UnionExcuse<T> toObject(CharSequence jsonString, Class<T> className_dot_class) {
        try {
            return TGS_UnionExcuse.of(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                    .readValue(jsonString.toString(), className_dot_class));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    public static TGS_UnionExcuse<String> toJSON(Object o, boolean pretty) {
        try {
            return TGS_UnionExcuse.of(new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValueAsString(o));
        } catch (IOException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }

    public static TGS_UnionExcuseVoid toFile(CharSequence sourceJSON, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        return TS_FileTxtUtils.toFile(sourceJSON, destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }

    public static TGS_UnionExcuseVoid toFile(Object o, boolean pretty, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        var u = toJSON(o, pretty);
        if (u.isExcuse()) {
            return TGS_UnionExcuseVoid.ofExcuse(u.excuse());
        }
        return TS_FileTxtUtils.toFile(u.value(), destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }
}
