from web.orm import orm_base
from web.orm.mappings import Users
import cherrypy

def login_check(username, password):  
    q =  cherrypy.request.db.query(Users).filter(Users.username==username, \
      Users.password==password)
    return cherrypy.request.db.query(q.exists())[0][0]
