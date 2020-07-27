package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class DBCPInitListener
 *
 */

public class DBCPInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBCPInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // 583쪽 23~32줄
    	String poolConfig = 
    			sce.getServletContext().getInitParameter("poolConfig");
    	Properties prop = new Properties();
    	
    	try {
    		prop.load(new StringReader(poolConfig));
    	} catch (IOException e) {
    		throw new RuntimeException("config load fail", e);
    	}
    	loadJDBCDriver(prop);
    	initConnectionPool(prop);
    }
	
}










