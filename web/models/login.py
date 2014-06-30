from web.orm import orm_base
from web.orm.mappings import Users
import cherrypy

def login_check(username, password):  
    """
    Returns the user id of the queried user if user exists; otherwise, returns
    None.
    """
    q =  cherrypy.request.db.query(Users).filter(Users.username==username, \
      Users.password==password).limit(1)

    
    userdata = cherrypy.request.db.execute(q)
    with userdata as data:
        the_user = data.fetchone()

        if the_user:
            return the_user["userid"]
