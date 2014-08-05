from cherry_tester import BaseCherryPyTestCase
from sqlalchemy import create_engine
from web.models.operations import DBOperations
from web.orm.mappings import Users
from web.orm.orm_base import DBSessionTool, SAEngine
from web.main import Pynance

import cherrypy
import unittest

def setUpModule():
    config = {
        "/":
            {
                "tools.db.on": True,
                "tools.session.on": True
            }
    }
    SAEngine(cherrypy.engine).subscribe()
    cherrypy.tools.db = DBSessionTool(None)
    cherrypy.tree.mount(PynanceDbOpsTest(), "/", config=config)
    cherrypy.engine.start()
setup_module = setUpModule

def tearDownModule():
    cherrypy.engine.exit()
teardown_module = tearDownModule

class PynanceDbOpsTest(object):
    
    def insert_select(self):
        user = Users()
        user.username = "hsimpson"
        user.password = "donuts"
        user_operations = DBOperations(user)
        user_operations.insert()

        user_selector = DBOperations(Users())
        filter_fn = lambda : User.username == "hsimpson"
        user_selector.select(filter_fn)

class DBOperationsTest(BaseCherryPyTestCase):
    
    def setUp(self):
        SAEngine(cherrypy.engine).subscribe()
        db_engine = create_engine("mysql://root:@localhost:3306/pynance")
        cherrypy.tools.db = DBSessionTool(None)
        cherrypy.tools.db.bind_session()
        self.context_operator = PynanceDbOpsTest()
        cherrypy.tree.mount(self.context_operator, config={"/":{"tools.db.on": True}})
        cherrypy.engine.start()

    def tearDown(self):
        cherrypy.engine.exit()
    
    def insert_test(self):
        pass

    def test_select(self):
        self.context_operator.insert_select()

if __name__ == "__main__":
    unittest.main()
