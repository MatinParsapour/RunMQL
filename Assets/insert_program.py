import sys
import subprocess
import logging
import os
from datetime import datetime
from subprocess import CalledProcessError

logging.basicConfig(filename=os.getcwd() + '/logs/insert_program.log', level=logging.DEBUG, 
                    format='%(asctime)s %(levelname)s %(name)s %(message)s')
logger=logging.getLogger(__name__)

def logProcess(exception:Exception):
    os.chdir("../")
    logger.critical(exception)
    
def logSubprocess(exception:CalledProcessError):
    os.chdir("../")
    logger.critical(exception.output)

def importJPO():
    try:
        path = sys.argv[1].split(",")[0]
        projectName = sys.argv[1].split(",")[1]
        password = sys.argv[1].split(",")[2]
        username = sys.argv[1].split(",")[3]
        isPasswordExists = password != ""
        if isPasswordExists:
            context = "set context user " + username + " password " + password + ";insert program " + path + ";"
        else:
            context = "set context user " + username + ";insert program " + path + ";"


        path = path.replace("/","\\")
        os.chdir(projectName)
        proc = subprocess.check_output(["mql.exe.lnk", "-c", context ],stderr=subprocess.STDOUT,shell=True)
        return 0
    except CalledProcessError as exception:
        logSubprocess(exception)
        return 1
    except Exception as exception:
        logProcess(exception)
        return 1

if __name__ == '__main__':
    sys.exit(importJPO())

