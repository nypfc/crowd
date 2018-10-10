package com.gedoumi.crowd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gedoumi.crowd.api.dataobj.vo.ResponseObject;
import org.junit.Test;

import java.math.BigDecimal;

public class NormalTest {

    @Test
    public void dateTest() throws JsonProcessingException {
        System.out.println(new BigDecimal(Math.random()).setScale(4, BigDecimal.ROUND_DOWN));
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = ResponseObject.setSuccessResponse("abcdefg");
        System.out.println(mapper.writeValueAsString(responseObject));
    }

}
