from db_config import DBConfig

from sqlalchemy import Column, Integer, String, TIMESTAMP, create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

Base = declarative_base()
DATA_SOURCE = DB_DRIVER + "://" + DB_USERNAME + ":" + DB_PASSWORD + "@" + DB_HOST + "/" + DB_NAME 

# Source http://docs.sqlalchemy.org/en/rel_0_9/orm/session.html
db_engine = create_engine(DATA_SOURCE)

class TableTemplate(Base):
    __tablename__ = None
    __table_args__ = {"mysql_engine":"InnoDB", "charset":"utf8"}
    
    create_time = Column(TIMESTAMP)
    last_updater = Column(Integer)
    last_update = Column(TIMESTAMP)
