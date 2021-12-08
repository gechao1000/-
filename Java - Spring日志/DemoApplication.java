package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.convert.Property;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
//		ApplicationHome home = new ApplicationHome(DemoApplication.class);
//		if (home.getSource().getAbsolutePath().endsWith("jar")) {
//			String[] newArgs = new String[args.length + 1];
//			System.arraycopy(args, 0, newArgs, 0, args.length);
//			newArgs[args.length] = "-Dlogging.file.path=" + home.getSource().getParent();
//			args = newArgs;
//			String logPath = System.getProperty("-Dlogging.file.path");
//			System.out.println(logPath);
//			System.setProperty("-Dlogging.file.path", home.getSource().getParent());
//		}

//		try {
//			Properties prop = new Properties();
//			prop.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
//			System.out.println(prop);
//		} catch (IOException e) {
//
//		}

//		ClassPathResource r = new ClassPathResource("application.properties");
//
//		String logPath = System.getProperty("-Dlogging.file.path");
//		System.out.println(logPath);
		ApplicationHome home = new ApplicationHome(DemoApplication.class);
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setDefaultProperties(Collections.singletonMap("logging.file.path", home.getSource().getParent()));
		app.run(args);

//		SpringApplication.run(DemoApplication.class, args);
	}

	Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

//	@Value("${user.dir}")
//	private Object dir;
//
	@Value("${logging.file.path}")
	private String path;

//	@Value("${server.port}")
//	private String port;

	@PostConstruct
	public void init() {
//		URL url = ResourceUtils.getURL("");
//		System.out.println(url);

//		File file = ResourceUtils.getFile("classpath:application.properties");
//		System.out.println(file.toURI());

//		ClassPathResource r = new ClassPathResource("application.properties");
//		System.out.println(r.getURL());
//		System.out.println(r.getURI());
//		Path path = Path.of(r.getURI());
//		System.out.println(path);

//		File file = new File(".");
//		System.out.println(file.isDirectory());
//		System.out.println(file.getAbsolutePath());

		System.out.println("---------get log path----------");
		System.out.println(path);
//		ApplicationHome home = new ApplicationHome(this.getClass());
//		System.out.println(home.getSource().getAbsolutePath());
//		System.out.println(home.getSource().getParent());
//		System.out.println(home.getSource().getPath());
//		LOG.info(home.getSource().getParent());
//		System.out.println(dir);
	}
}
