package org.shijinglu.asrc.service.utils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.shijinglu.lure.core.BoolData;
import org.shijinglu.lure.core.DoubleData;
import org.shijinglu.lure.core.IntData;
import org.shijinglu.lure.core.StringData;
import org.shijinglu.lure.extensions.IData;

public class Mapper {
    /**
     * {@code value} is read from json, which can be number, string or boolean. This method converts
     * obj to corresponding IData type.
     */
    private static IData parseJsonValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return new StringData((String) value);
        }
        if (value instanceof Integer) {
            return new IntData((Integer) value);
        }
        if (value instanceof Number) {
            return new DoubleData(((Number) value).doubleValue());
        }
        if (value instanceof Boolean) {
            return new BoolData((Boolean) value);
        }
        return new StringData(value.toString());
    }

    public static Map<String, IData> mapRequestConfig(Map<String, Object> rawContext) {
        return rawContext.entrySet().stream()
                .filter(stringObjectEntry -> stringObjectEntry.getValue() != null)
                .map(
                        entry ->
                                new AbstractMap.SimpleEntry<>(
                                        entry.getKey(), parseJsonValue(entry.getValue())))
                .collect(
                        Collectors.toMap(
                                AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue));
    }

    private static Object mapDataToPrimatives(IData data) {
        if (data == null) {
            return null;
        }
        if (data instanceof BoolData) {
            return data.toBool();
        }
        if (data instanceof IntData) {
            return data.toInt();
        }
        if (data instanceof DoubleData) {
            return data.toDouble();
        }
        return data.toString();
    }

    public static Map<String, Object> mapConfigToResponse(Map<String, IData> configs) {
        return configs.entrySet().stream()
                .filter(stringObjectEntry -> stringObjectEntry.getValue() != null)
                .map(
                        entry ->
                                new AbstractMap.SimpleEntry<>(
                                        entry.getKey(), mapDataToPrimatives(entry.getValue())))
                .collect(
                        Collectors.toMap(
                                AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue));
    }
}
