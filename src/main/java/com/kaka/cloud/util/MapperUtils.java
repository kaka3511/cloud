package com.kaka.cloud.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 14:07
 */
public class MapperUtils {
  public MapperUtils() {
  }

  public static String toJson(Object obj) {
    return toJson(obj, true, (SerializeFilter)null);
  }

  public static String toJson(Object obj, boolean excludeNull, SerializeFilter filter) {
    if (obj == null) {
      return "";
    } else if (excludeNull) {
      return filter != null ? JSONObject.toJSONString(obj, filter, new SerializerFeature[]{SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField}) : JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField});
    } else {
      return filter != null ? JSONObject.toJSONString(obj, filter, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField}) : JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField});
    }
  }

  public static String toJson(Object object, SerializeFilter filter) {
    return toJson(object, true, filter);
  }

  public static <T> T map(String jsonString, Class<T> clazz) {
    return StringUtils.isBlank(jsonString) ? null : JSON.parseObject(jsonString, clazz);
  }

  public static <T> List<T> mapToList(String jsonString, Class<T> clazz) {
    return StringUtils.isBlank(jsonString) ? null : JSONArray.parseArray(jsonString, clazz);
  }

  public static <T> Map<String, T> toMap(String jsonString) {
    return StringUtils.isBlank(jsonString) ? null : (Map)JSON.parseObject(jsonString, new TypeReference<Map<String, T>>() {
    }, new Feature[0]);
  }

  public static final String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature... features) {
    SerializeWriter out = new SerializeWriter();

    String var13;
    try {
      JSONSerializer serializer = new JSONSerializer(out);
      SerializerFeature[] arr$ = features;
      int len$ = features.length;

      int i$;
      for(i$ = 0; i$ < len$; ++i$) {
        SerializerFeature feature = arr$[i$];
        serializer.config(feature, true);
      }

      serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
      if (filters != null) {
        SerializeFilter[] arr$_ = filters;
        len$ = filters.length;

        for(i$ = 0; i$ < len$; ++i$) {
          SerializeFilter filter = arr$_[i$];
          if (filter instanceof PropertyPreFilter) {
            serializer.getPropertyPreFilters().add((PropertyPreFilter)filter);
          }

          if (filter instanceof NameFilter) {
            serializer.getNameFilters().add((NameFilter)filter);
          }

          if (filter instanceof ValueFilter) {
            serializer.getValueFilters().add((ValueFilter)filter);
          }

          if (filter instanceof PropertyFilter) {
            serializer.getPropertyFilters().add((PropertyFilter)filter);
          }

          if (filter instanceof BeforeFilter) {
            serializer.getBeforeFilters().add((BeforeFilter)filter);
          }
        }
      }

      serializer.write(object);
      var13 = out.toString();
    } finally {
      out.close();
    }

    return var13;
  }
}

