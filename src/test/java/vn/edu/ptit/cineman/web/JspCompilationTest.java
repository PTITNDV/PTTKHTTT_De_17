package vn.edu.ptit.cineman.web;

import java.io.File;
import org.apache.jasper.JspC;
import org.junit.jupiter.api.Test;

class JspCompilationTest {
    @Test void allInterfacePagesCompile() throws Exception {
        JspC compiler = new JspC();
        compiler.setUriroot(new File("src/main/webapp").getAbsolutePath());
        compiler.setOutputDir(new File("target/jspc").getAbsolutePath());
        compiler.setClassPath(System.getProperty("java.class.path"));
        compiler.setCompile(true);
        compiler.execute();
    }
}
