from db_config import DBConfig

from cherrypy.process import plugins, wspbus

from sqlalchemy import Column, Integer, String, TIMESTAMP, create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declared_attr

import cherrypy

# Source http://docs.sqlalchemy.org/en/rel_0_9/orm/session.html
# http://www.defuze.org/archives/222-integrating-sqlalchemy-into-a-cherrypy-application.html
# db_engine = create_engine(DATA_SOURCE, pool_size=20, max_overflow=0)

# Session = sessionmaker(bind=db_engine)

# session = Session()

class TableTemplate(object):
    
    @declared_attr
    def __tablename__(cls):
        return cls.__name__.lower()

    __table_args__ = {"mysql_engine":"InnoDB"}
    __mapper_args__ = {"always_refresh": True}
    
    create_time = Column(TIMESTAMP)
    last_updater = Column(Integer)
    last_update = Column(TIMESTAMP)


# TODO Understand this!
class DBSessionTool(cherrypy.Tool):
    """
    Taken from http://www.defuze.org/archives/222-integrating-sqlalchemy-into-a-cherrypy-application.html
    """
    
    def __init__(self):
        super(DBSessionTool, self).__init__(self, "on_start_resource",\
          self.bind_session, priority=20)
        self.session = scoped_session(sessionmaker(autoflush=True, autocommit=False))

    def _setup(self):
        super(DBSessionTool, self)._setup(self)
        cherrypy.request.hooks.attach("on_end_resource", self.commit_transaction,
          priority=80)

    def bind_session(self):
        cherrypy.engine.publish("bind", self.session)
        cherrypy.request.db = self.session

    def commit_transaction(self):
        cherrypy.request.db = None
        try:
            self.session.commit()
        except:
            self.session.rollback()
        finally:
            self.session.remove()


class SAEngine(plugins.SimplePlugin):
    """
    Taken from http://www.defuze.org/archives/222-integrating-sqlalchemy-into-a-cherrypy-application.html
    """
    
    def __init__(self, bus):
        super(SAEngine, self).__init__(bus)
        self.sa_engine = None
        self.bus.subscribe("bind", self.bind)

    def start(self):
        self.sa_engine = create_engine(DBConfig.DSN)
    
    def stop(self):
        if self.sa_engine:
            self.sa_engine.dispose()
            self.sa_engine = None

    def bind(self, session):
        session.configure(bind=self.sa_engine)
