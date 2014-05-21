from web.orm import orm_base
from web.orm.mappings import Users
import cherrypy

def login_check(username, password):  
    q =  cherrypy.request.db.query(Users).filter(Users.username==username, \
      Users.password==password)
    cherrypy.log(str(q))
    return cherrypy.request.db.query(q.exists()).count()
