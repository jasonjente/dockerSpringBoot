package project.rest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith( SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {
    private static final String PATH = "localhost:8081/application/api/v1/rest/";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test(){
        System.out.println("{\n" +
                "    \"recipientFirstName\": \"firstName\",\n" +
                "    \"recipientLastName\": \"lastName\",\n" +
                "    \"recipientAddress\": \"address st. 123\",\n" +
                "    \"recipientEmail\": \"email@domain.com\",\n" +
                "    \"orderItems\": [\n" +
                "        {\n" +
                "            \"quantity\": 3,\n" +
                "            \"product\": {\n" +
                "                \"state\": \"IN_STOCK\",\n" +
                "                \"productName\": \"Nvidia 3070TI\",\n" +
                "                \"description\": \"8GB V-RAM, 1500 Cuda cores, 120 RT cores.\",\n" +
                "                \"productCode\": \"286864\",\n" +
                "                \"price\": 700.0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"quantity\": 1,\n" +
                "            \"product\": {\n" +
                "                \"state\": \"IN_STOCK\",\n" +
                "                \"productName\": \"AMD RX6800XT\",\n" +
                "                \"description\": \"16GB V-RAM.\",\n" +
                "                \"productCode\": \"AMD54459hjff\",\n" +
                "                \"price\": 700.0\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"orderState\": \"PROCESSING\"\n" +
                "}");
    }
    @Test
    void getsAllOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "orders/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getASingleOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "orders/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void returnsNotFoundForInvalidSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "orders/9999999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void addsOrder() throws Exception {
        String newOrder = "{\n" +
                "    \"recipientFirstName\": \"FirstdwwqName\",\n" +
                "    \"recipientLastName\": \"LastdqwdqwdName\",\n" +
                "    \"recipientAddress\": \"Addredwqdss 123\",\n" +
                "    \"recipientEmail\": \"Emadqwdqwdil@email.com\",\n" +
                "    \"orderItems\": [\n" +
                "        {\n" +
                "            \"quantity\": 3,\n" +
                "            \"product\": {\n" +
                "                \"state\": \"IN_STOCK\",\n" +
                "                \"productName\": \"Nvidia 3070TI\",\n" +
                "                \"description\": \"8GB V-RAM \\n 1500 Cuda cores \\n 120 RT cores.\",\n" +
                "                \"productCode\": \"286864\",\n" +
                "                \"price\": 700.0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"quantity\": 1,\n" +
                "            \"product\": {\n" +
                "                \"state\": \"IN_STOCK\",\n" +
                "                \"productName\": \"AMD RX6800XT\",\n" +
                "                \"description\": \"16GB V-RAM.\",\n" +
                "                \"productCode\": \"AMD54459hjff\",\n" +
                "                \"price\": 700.0\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"orderState\": \"PROCESSING\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newOrder)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
