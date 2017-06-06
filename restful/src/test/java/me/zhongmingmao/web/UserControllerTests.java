package me.zhongmingmao.web;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }
    
    
    @Test
    public void userControllerGetTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/").accept(MediaType.APPLICATION_JSON);
        
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(IsEqual.equalTo("[]")));
        
        requestBuilder = MockMvcRequestBuilders
                .post("/users/").accept(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("name", "zhongmingmao");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(IsEqual.equalTo("success")));
        
        requestBuilder = MockMvcRequestBuilders
                .get("/users/").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        IsEqual.equalTo("[{\"id\":1,\"name\":\"zhongmingmao\"}]")));
        
        requestBuilder = MockMvcRequestBuilders
                .put("/users/1")
                .param("name", "zhongmingwu");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(IsEqual.equalTo("success")));
        
        requestBuilder = MockMvcRequestBuilders
                .get("/users/").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        IsEqual.equalTo("[{\"id\":1,\"name\":\"zhongmingwu\"}]")));
        
        requestBuilder = MockMvcRequestBuilders
                .delete("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(IsEqual.equalTo("success")));
        
        requestBuilder = MockMvcRequestBuilders
                .get("/users/").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(IsEqual.equalTo("[]")));
    }
}
