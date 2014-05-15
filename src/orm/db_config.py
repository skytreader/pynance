# TODO Should get the db details from the config passed to the web app instead
# of in another Python file.

class DBConfig:
    DB_DRIVER = "mysql"
    DB_USERNAME = "root"
    DB_PASSWORD = ""
    DB_HOST = "localhost"
    DB_PORT = 3306
    DB_NAME = "pynance"

    DSN = DB_DRIVER + "://" + DB_USERNAME + ":" + DB_PASSWORD + "@" + DB_HOST + "/" + DB_NAME 
