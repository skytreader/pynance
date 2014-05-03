import cherrypy
import jinja2
import os

TEMPLATE_LOADER = jinja2.FileSystemLoader(searchpath=os.getcwd())
TEMPLATE_ENVIRONMENT = jinja2.Environment(loader=TEMPLATE_LOADER)

class Pynance(object):
    
    @cherrypy.expose
    def index(self):
        template = TEMPLATE_ENVIRONMENT.get_template("views/index.jinja")
        return template.render()

if __name__ == "__main__":
    cherrypy.quickstart(Pynance())
