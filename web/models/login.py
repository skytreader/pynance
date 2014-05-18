from web.orm import orm_base
from web.orm.mappings import Users
import cherrypy

def login_check(username, password):  
    return cherrypy.request.db.query(Users).filter(Users__username=username).\
      filter(Users__password=password)
