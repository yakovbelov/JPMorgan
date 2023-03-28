@echo off

:start
set choice=
set /p choice="FilenameValidator "

java -Djava.util.logging.config.file=logging.properties -cp filename_validator.jar;. com.jpmorgan.validator.FilenameValidator %choice%

REM with overriding bundled application properties
rem java -Djava.util.logging.config.file=logging.properties -Dcom.jpmorgan.validator.util.properties=FilenameValidator.properties -cp filename_validator.jar;. com.jpmorgan.validator.FilenameValidator %choice%

goto start