package me.zhongmingmao;

import me.zhongmingmao.web.DemoController;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
    
    
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
    
    @Test
    public void demoControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/200"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        mockMvc.perform(MockMvcRequestBuilders.get("/403"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(StringContains.containsString("403")));
    }
    
}
