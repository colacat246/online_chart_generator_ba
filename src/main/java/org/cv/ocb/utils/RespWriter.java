package org.cv.ocb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class RespWriter {

    public static void writeJson(HttpServletResponse resp, Object json) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(json));
        writer.flush();
    }
}
