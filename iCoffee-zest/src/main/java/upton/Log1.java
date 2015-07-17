package upton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log1 {
    private static final Logger logger = LoggerFactory.getLogger(Log1.class);
    
    public void test(){
        logger.debug("log1 debug");
        logger.info("log1 info");
        logger.warn("log1 warn");
        logger.error("log1 error");
    }
}
