from models.operations import DBOperations
from orm.orm_base import DBSessionTool, SAEngine
from web.models.login import login_check

import cherrypy
import unittest

class LoginTest(unittest.TestCase):
    
    def test_login(self):
        pass

if __name__ == "__main__":
    SAEngine(cherrpy.engine).subscribe()
    cherrypy.tools.db = DBSessionTool()
    cherrypy.tree.mount(None)
    cherrypy.engine.start()
    unittest.main()
