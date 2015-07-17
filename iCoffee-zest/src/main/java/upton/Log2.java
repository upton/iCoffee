package upton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log2 {
    private static final Logger logger = LoggerFactory.getLogger(Log2.class);
    
    public void test(){
        logger.debug("log2 debug");
        logger.info("log2 info");
        logger.warn("log2 warn");
        logger.error("log2 error 中文");
    }
}
