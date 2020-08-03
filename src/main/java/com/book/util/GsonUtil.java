package com.book.util;

import com.google.gson.*;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午12:27
 */
public class GsonUtil {


    /**
     * 获取gson实例
     *
     * @return
     */
    public static Gson getGsonInstance() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return fieldAttributes.getName().startsWith("pass");
                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<String>())
                .registerTypeAdapter(Date.class, new MyDateTypeAdapter())
//            .registerTypeAdapterFactory(new NullNumberToEmptyAdapterFactory())
                .serializeNulls()
                .create();
    }


    public static Gson getSimpleGsonInstance() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new MyDateTypeAdapter())
                .create();
    }
    // 处理字符串开始

    /**
     * 处理字符串为null
     *
     * @param <T>
     */
    static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringNullAdapter();
        }
    }

    static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.value("");
                return;
            }
            writer.value(value);
        }
    }
    // 处理字符串结束

    /**
     * 处理字符串为null
     *
     * @param <T>
     */
    static class NullNumberToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (!Number.class.isAssignableFrom(rawType)) {
                return null;
            }
            return (TypeAdapter<T>) new NumberNullAdapter();
        }
    }

    static class NumberNullAdapter extends TypeAdapter<Number> {
        @Override
        public Number read(JsonReader reader) throws IOException {
            return 0;
        }

        @Override
        public void write(JsonWriter writer, Number value) throws IOException {
            if (value == null) {
                writer.value(-1);
                return;
            }
            writer.value(value);
        }
    }

    /**
     * 转换date
     */
    static class MyDateTypeAdapter extends TypeAdapter<Date> {
        public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
            @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.getRawType() == Date.class ? (TypeAdapter<T>) new DateTypeAdapter() : null;
            }
        };

        private final DateFormat enUsFormat
                = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
        private final DateFormat localFormat
                = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
        private final DateFormat iso8601Format = buildIso8601Format();

        private static DateFormat buildIso8601Format() {
            DateFormat iso8601Format = new SimpleDateFormat(MyDateFormat.DEFAULT_FORMAT, Locale.CHINA);
            iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return iso8601Format;
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
//                try {
//                    return MyDateFormat.parse("0000-00-00 00:00:00");
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                return null;
            }
            return deserializeToDate(in.nextString());
        }

        private synchronized Date deserializeToDate(String json) {
            try {
                return localFormat.parse(json);
            } catch (ParseException ignored) {
            }
            try {
                return enUsFormat.parse(json);
            } catch (ParseException ignored) {
            }
            try {
                return iso8601Format.parse(json);
            } catch (ParseException e) {
                throw new JsonSyntaxException(json, e);
            }
        }

        @Override
        public synchronized void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
//                out.value("0000-00-00 00:00:00");
                return;
            }
            String dateFormatAsString = MyDateFormat.format(value);
            out.value(dateFormatAsString);
        }
    }


}
