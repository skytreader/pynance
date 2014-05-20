from orm.orm_base import DBSessionTool, SAEngine
from models.login import login_check

import cherrypy
import jinja2
import os

TEMPLATE_LOADER = jinja2.FileSystemLoader(searchpath=os.getcwd())
TEMPLATE_ENVIRONMENT = jinja2.Environment(loader=TEMPLATE_LOADER)

class Pynance(object):
    
    @cherrypy.expose
    def index(self):
        template = TEMPLATE_ENVIRONMENT.get_template("views/index.jinja")
        return template.render(static=os.getcwd())

    @cherrypy.expose
    def login(self, username, password):
        if cherrypy.request.method == "POST":
            if login_check(username, password):
                return "OK"
            else:
                return "FAIL"
        else:
            # raise 405
            cherrypy.response.status = 405
            return "Login with POST."

if __name__ == "__main__":
    config = {
        "/css/bootstrap.css":
            {
                "tools.staticfile.on": True,
                "tools.staticfile.filename": os.getcwd() + "/web/views/css/bootstrap/css/bootstrap.css"
            },
        "/css/pynance.css":
            {
                "tools.staticfile.on": True,
                "tools.staticfile.filename": os.getcwd() + "/web/views/css/pynance.css"
            },
        "/images":
            {
                "tools.staticdir.on": True,
                "tools.staticdir.dir": os.getcwd() + "/web/views/images"
            }
    }
    SAEngine(cherrypy.engine).subscribe()
    cherrypy.tools.db = DBSessionTool()
    # Huh? cherrypy.tree.mount?
    cherrypy.tree.mount(Pynance(), config=config)
    #cherrypy.quickstart(Pynance(), config=config)
    cherrypy.engine.start()
    cherrypy.engine.block()
