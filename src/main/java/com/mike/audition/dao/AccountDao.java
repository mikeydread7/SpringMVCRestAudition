package com.mike.audition.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mike.audition.model.LookUpForm;

/**
 * @author Michael Somers Date: 11/3/2017
 *
 */

@Repository
public class AccountDao {

	private final static Map<String, LookUpForm> LOOKUP_FORM_MAP = new HashMap<>();
	private final static String SYSTEM_PATH = System.getProperty("user.dir");
	private final static String FILE_PATH = SYSTEM_PATH.concat("/src/main/resources/account-data");

	private final static Logger LOG = LoggerFactory.getLogger(AccountDao.class);

	/*The PostConstruct annotation is used on a method that needs to be executed after dependency injection 
	 *is done to perform any initialization. This method MUST be invoked before the class is put into service.
	 *This will ensure that your code will run when all the dependencies of your component are initialized
	 */
	@PostConstruct
    public  void init(){
		fillMap(FILE_PATH.concat("/qaaccounts"));
	}
	

	/**
	 * A static map AKA a database for demo, a h2 db could have been used also
	 * instead
	 * 
	 * @param fileName
	 */
	private static void fillMap(String fileName) {

		try {
			Files.lines(new File(fileName)
					.toPath())
			        .map(s ->s.trim())
					.filter(s ->!s.isEmpty())
					.forEach(line ->{
						LookUpForm lookForm = new LookUpForm(true, UUID.randomUUID().toString());
						LOOKUP_FORM_MAP.put(line,lookForm);
					});

		} catch (IOException e) {
			LOG.error("Unable to Load DB {} ", e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Description get the account from the map or null
	 * 
	 * @param account
	 * @return
	 */
	public LookUpForm getAccount(String account) {

    	return LOOKUP_FORM_MAP.get(account);
    }
	/**
	 * Description return the entire copy of the map
	 * 
	 * @return
	 */
	public Map<String, LookUpForm> getAllResults() {
		return LOOKUP_FORM_MAP;
	}

}
