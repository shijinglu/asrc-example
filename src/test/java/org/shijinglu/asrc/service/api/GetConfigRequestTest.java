package org.shijinglu.asrc.service.api;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import org.shijinglu.asrc.service.utils.Mapper;
import org.shijinglu.lure.core.BoolData;
import org.shijinglu.lure.core.DoubleData;
import org.shijinglu.lure.core.StringData;
import org.shijinglu.lure.extensions.IData;

public class GetConfigRequestTest {

    private static final String REQUEST_JSON =
            "{\n"
                    + "  \"namespace\": \"my_application\",\n"
                    + "  \"context\": {\n"
                    + "    \"PI\": 3.14,\n"
                    + "    \"NATURAL_CONSTANT_E\": 2.718,\n"
                    + "    \"first_name\": \"Alice\",\n"
                    + "    \"last_name\": \"Liddell\",\n"
                    + "    \"toggle_flag_on\": true,\n"
                    + "    \"toggle_flag_off\": false\n"
                    + "  }\n"
                    + "}";

    @Test
    public void buildContext() throws IOException {
        GetConfigRequest request =
                new ObjectMapper().readValue(REQUEST_JSON, GetConfigRequest.class);
        assertEquals(request.getNamespace(), "my_application");

        Map<String, IData> context = Mapper.mapRequestConfig(request.getContext());
        assertEquals(BoolData.TRUE, context.get("toggle_flag_on"));
        assertEquals(BoolData.FALSE, context.get("toggle_flag_off"));
        assertEquals(new DoubleData(3.14), context.get("PI"));
        assertEquals(new DoubleData(2.718), context.get("NATURAL_CONSTANT_E"));
        assertEquals(new StringData("Alice"), context.get("first_name"));
        assertEquals(new StringData("Liddell"), context.get("last_name"));
    }
}
