from web.models.operations import DBOperations
from web.orm.mappings import Users
from web.orm.orm_base import DBSessionTool, SAEngine

import cherrypy
import unittest

class DBOperationsTest(unittest.TestCase):
    
    def setUp(self):
        SAEngine(cherrypy.engine).subscribe()
        cherrypy.tools.db = DBSessionTool()
        cherrypy.tree.mount(None)
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
        filter_fn = lambda x : User.username == "hsimpson"
        user_selector.select(filter_fn)

if __name__ == "__main__":
    unittest.main()