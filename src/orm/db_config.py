# TODO Should get the db details from the config passed to the web app instead
# of in another Python file.

class DBConfig:
    DB_DRIVER = "mysql"
    DB_USERNAME = "root"
    DB_PASSWORD = ""
    DB_HOST = "localhost"
    DB_PORT = 3306
    DB_NAME = "pynance"

    DSN = DBConfig.DB_DRIVER + "://" + DBConfig.DB_USERNAME + ":" + DBConfig.DB_PASSWORD + "@" + DBConfig.DB_HOST + "/" + DBConfig.DB_NAME 
