Run MQL

This plugin will help you to work on both JPO and MQL files. you can insert your JPO or you can create new mql file and run it using this plugin.
Installation

In order to use this plugin:

1.Download plugin into your IDE.

2.Create new module name "Configuration".

3.Put run_mql and insert_program python files in Assets folder to the directory where all your projects mqls exist.

4.Running plugin for the first time will asks you to change default username.

5.You need to go through "Configuration" module and click right on it and click on "Reload from disk", new file will be appeared named "Configuration.properties", you need to change username and password to your username and password, this will be used for "Set context user .. " command and change the direcotry to where you put run_mql and insert_program files.

6.Now you're good to good.

7.Have fun.
Consideration

There're some Consideration when you want to use this plugin.

1.Your mqls must be inside a folder .

2.Name of modules must match with name of folder.

3.run_mql and insert_program files must not be inside any folder, in the same directory as other projects folder exist.

4.If there's any issue with inserting program or running mql command, new folder named logs will be created in directory of run_mql file and you can see through insert_program file to know what happened.
