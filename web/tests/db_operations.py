from sqlalchemy import create_engine
from web.models.operations import DBOperations
from web.orm.mappings import Users
from web.orm.orm_base import DBSessionTool, SAEngine
from web.main import Pynance

import cherrypy
import unittest

class PynanceDbOpsTest(unittest.TestCase):
    
    
    def test_select(self):
        user = Users()
        user.username = "hsimpson"
        user.password = "donuts"
        user_operations = DBOperations(user)
        user_operations.insert()

        user_selector = DBOperations(Users())
        filter_fn = lambda : User.usernmae == "hsimpson"
        user_selector.select(filter_fn)

class DBOperationsTest(unittest.TestCase):
    
    def setUp(self):
        SAEngine(cherrypy.engine).subscribe()
        db_engine = create_engine("mysql://root:@localhost:3306/pynance")
        cherrypy.tools.db = DBSessionTool(None)
        cherrypy.tools.db.bind_session()
        cherrypy.tree.mount(self, config={"/":{"tools.db.on": True}})
        cherrypy.engine.start()

    def tearDown(self):
        cherrypy.engine.exit()
    
    def insert_test(self):
        pass

    def test_select(self):
        user = Users()
        user.username = "hsimpson"
        user.password = "donuts"
        user_operations = DBOperations(user)
        user_operations.insert()

        user_selector = DBOperations(Users())
        filter_fn = lambda : User.usernmae == "hsimpson"
        user_selector.select(filter_fn)

if __name__ == "__main__":
    unittest.main()
