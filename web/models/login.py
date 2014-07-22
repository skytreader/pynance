from web.orm import orm_base
from web.orm.mappings import Users
import cherrypy

def login_check(username, password):  
    """
    Returns the user id of the queried user if user exists; otherwise, returns
    None.
    """
    # Create a query object
    q =  cherrypy.request.db.query(Users).filter(Users.username==username, \
      Users.password==password).limit(1)

    # Execute the query object; userdata is a ResultProxy object
    userdata = cherrypy.request.db.execute(q)
    the_user = dict(userdata.first())
    if the_user:
        return the_user["users_userid"]

    return None
