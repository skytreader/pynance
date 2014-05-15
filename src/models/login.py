from src.orm import orm_base
from src.orm.mappings import Users
import cherrypy

def login(username, password):  
    return cherrypy.request.db.query(Users).filter(Users__username=username).\
      filter(Users__password=password)
