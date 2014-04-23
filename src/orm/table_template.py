from sqlalchemy import Column, Integer, String, TIMESTAMP
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class TableTemplate(Base):
    __tablename__ = None
    __table_args__ = {"mysql_engine":"InnoDB", "charset":"utf8"}
    
    create_time = Column(TIMESTAMP)
    last_updater = Column(Integer)
    last_update = Column(TIMESTAMP)
