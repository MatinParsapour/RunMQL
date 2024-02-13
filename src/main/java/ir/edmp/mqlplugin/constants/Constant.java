package ir.edmp.mqlplugin.constants;

public class Constant {
	public static final String PROJECTS_LOCATION = "Project_Directory";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String DEFAULT_USERNAME = "Fake_Username";
	public static final String DEFAULT_PASSWORD = "Fake_Password";
	public static final String DEFAULT_PROJECTS_DIRECTORY = "C:\\insert program";
	public static final String CONFIGURATION_MODULE = "Configuration";
	public static final String JAVA_EXTENSION = "java";
	public static final String MQL_EXTENSION = "mql";

	public static final String DIRECTORY_LOGS = "\\logs\\";


	// Errors
	public static final String ERROR_RUN_MQL = "Run MQL Error";
	public static final String ERROR_EMPTY_DIRECTORY = "Incorrect Directory : \nDirectory of projects can not be empty";
	public static final String ERROR_INCORRECT_DIRECTORY = "Incorrect Directory : \nThe directory must contains ${FILE_NAME} file";
	public static final String ERROR_DEFAULT_PASSWORD = "Incorrect password : \nChange default password";
	public static final String ERROR_EMPTY_USERNAME = "Incorrect username : \nUsername can not be empty";
	public static final String ERROR_DEFAULT_USERNAME = "Incorrect username : \nChange default username";
	public static final String ERROR_UNABLE_TO_CREATE_PROPERTIES_FILE = "Error, Unable to create properties file : \n";
	public static final String ERROR_CONFIGURATION_MODULE_NOT_FOUND = "No module name " + CONFIGURATION_MODULE + " found, please create this module.";
	public static final String ERROR_KEY_NOT_FOUND = "No property ${KEY} found in properties file";
	public static final String ERROR_UNABLE_TO_READ_PROPERTIES_FILE = "Error, Unable to read properties file : \n";
	public static final String ERROR_UNABLE_TO_WRITE_PROPERTIES_FILE = "Unable to write properties file : \n";
	public static final String ERROR_UNSUCCESSFUL_MQL_RUNNING = "Error, running mql was not successful : \n";
	public static final String ERROR_SEE_FULL_LOG = "You can see full log in ${PATH}";


	// Files
	public static final String FILE_RESULT = "result.txt";
	public static final String FILE_CONFIG = "\\Config.properties";
	public static final String FILE_INSERT_PROGRAM = "insert_program.py";
	public static final String FILE_RUN_MQL = "run_mql.py";

}
